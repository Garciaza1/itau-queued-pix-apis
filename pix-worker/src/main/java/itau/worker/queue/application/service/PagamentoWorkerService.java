package itau.worker.queue.application.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import itau.pix.commons.enums.StatusPagamento;
import itau.worker.queue.application.port.in.PagamentoWorkerUseCase;
import itau.worker.queue.domain.model.PagamentoMessage;
import itau.worker.queue.domain.port.out.ChavePixRepositoryPort;

@Service
public class PagamentoWorkerService implements PagamentoWorkerUseCase {

    private final RabbitTemplate rabbitTemplate;
    private final ChavePixRepositoryPort chavePixRepository;
    private final Queue paymentQueue;
    private final Queue failedQueue;
    private final Queue successQueue;

    public PagamentoWorkerService(
            @Lazy RabbitTemplate rabbitTemplate,
            ChavePixRepositoryPort chavePixRepository,
            @Qualifier("paymentQueue") Queue paymentQueue,
            @Qualifier("failedPaymentQueue") Queue failedQueue,
            @Qualifier("successPaymentQueue") Queue successQueue
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.chavePixRepository = chavePixRepository;
        this.paymentQueue = paymentQueue;
        this.failedQueue = failedQueue;
        this.successQueue = successQueue;
    }

    @Override
    public void process(PagamentoMessage message) {
        System.out.println("Processing payment: " + message.getId());

        var senderOpt = chavePixRepository.findByNumeroConta(message.getSenderAccount());
        if (!senderOpt.isPresent()) {
            message.setErrorDescription("Conta do remetente não encontrada");
            handleFailed(message);
            return;
        }

        var receiverOpt = chavePixRepository.findByValorChave(message.getReceiverPixKey());
        if (!receiverOpt.isPresent()) {
            message.setErrorDescription("Chave Pix do destinatário não encontrada");
            handleFailed(message);
            return;
        }

        var sender = senderOpt.get();
        var receiver = receiverOpt.get();

        // valida saldo
        if (sender.getSaldo().compareTo(message.getAmount()) < 0) {
            message.setErrorDescription("Saldo insuficiente");
            handleFailed(message);
            return;
        }

        if (sender.getNumeroConta().equals(receiver.getNumeroConta())) {
            message.setErrorDescription("Não é permitido enviar Pix para a mesma conta");
            handleFailed(message);
            return;
        }

        handleSuccess(message);
    }

    private void handleFailed(PagamentoMessage message) {
        if (message.getRetryCount() < 3) {
            message.setRetryCount(message.getRetryCount() + 1);
            rabbitTemplate.convertAndSend(paymentQueue.getName(), message, new CorrelationData(message.getId()));
            System.out.println("🔄 Retrying payment " + message.getId() + " attempt " + message.getRetryCount());
        } else {
            message.setStatus(StatusPagamento.FALHOU);
            rabbitTemplate.convertAndSend(failedQueue.getName(), message, new CorrelationData(message.getId()));
            System.out.println("❌ Payment " + message.getId() + " failed after 3 attempts: " + message.getErrorDescription());
        }
    }

    private void handleSuccess(PagamentoMessage message) {
        message.setStatus(StatusPagamento.SUCESSO);
        rabbitTemplate.convertAndSend(successQueue.getName(), message, new CorrelationData(message.getId()));
        System.out.println("✅ Payment " + message.getId() + " validated successfully");
    }
}
