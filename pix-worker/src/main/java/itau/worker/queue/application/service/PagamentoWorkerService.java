package itau.worker.queue.application.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import itau.pix.commons.enums.StatusPagamento;
import itau.pix.commons.messaging.RabbitMQConstants;
import itau.worker.queue.application.port.in.PagamentoWorkerUseCase;
import itau.worker.queue.domain.model.PagamentoMessage;

@Service
public class PagamentoWorkerService implements PagamentoWorkerUseCase {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoWorkerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void process(PagamentoMessage message) {
        System.out.println("Processing payment: " + message.getId());

        if (message.getAmount().doubleValue() < 10.0) {
            handleFailed(message);
            return;
        }

        handleSuccess(message);
    }

    private void handleFailed(PagamentoMessage message) {
        if (message.getRetryCount() < 3) {
            message.setRetryCount(message.getRetryCount() + 1);
            rabbitTemplate.convertAndSend(RabbitMQConstants.FILA_PAGAMENTO, message);
            System.out.println("Retrying payment " + message.getId() + " attempt " + message.getRetryCount());
        } else {
            message.setStatus(StatusPagamento.FALHOU);
            rabbitTemplate.convertAndSend(RabbitMQConstants.FILA_PAGAMENTO_FALHOU, message);
            System.out.println("Payment " + message.getId() + " failed after 3 attempts");
        }
    }

    private void handleSuccess(PagamentoMessage message) {
        message.setStatus(StatusPagamento.SUCESSO);
        rabbitTemplate.convertAndSend(RabbitMQConstants.FILA_PAGAMENTO_SUCESSO, message);
        System.out.println("Payment " + message.getId() + " processed successfully");
    }

}
