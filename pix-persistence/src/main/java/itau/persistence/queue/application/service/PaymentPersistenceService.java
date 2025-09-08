package itau.persistence.queue.application.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import itau.persistence.queue.application.port.in.PagamentoPersistenceUseCase;
import itau.persistence.queue.application.port.out.PagamentoRepositoryPort;
import itau.persistence.queue.domain.model.Pagamento;
import itau.persistence.queue.domain.model.PagamentoMessage;
import itau.pix.commons.enums.StatusPagamento;

@Service
public class PaymentPersistenceService implements PagamentoPersistenceUseCase {

    private final PagamentoRepositoryPort repository;
        private final RabbitTemplate rabbitTemplate;

    public PaymentPersistenceService(PagamentoRepositoryPort repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void persist(PagamentoMessage message, boolean sucesso) {
        StatusPagamento status = sucesso ? StatusPagamento.SUCESSO : StatusPagamento.FALHOU;

        // Verifica se o pagamento já existe no MongoDB
        Optional<Pagamento> existingPagamentoOpt = repository.findById(message.getId());

        Pagamento pagamento;
        if (existingPagamentoOpt.isPresent()) {
            // Atualiza o pagamento existente
            pagamento = existingPagamentoOpt.get();
            pagamento.setAmount(message.getAmount());
            pagamento.setSenderAccount(message.getSenderAccount());
            pagamento.setReceiverPixKey(message.getReceiverPixKey());
            pagamento.setStatus(status);
            pagamento.setErrorDescription(message.getErrorDescription());
        } else {
            pagamento = new Pagamento(
                message.getId(),
                message.getAmount(),
                message.getSenderAccount(),
                message.getReceiverPixKey(),
                status,
                message.getErrorDescription()
            );
        }

        repository.save(pagamento);
    }
}
