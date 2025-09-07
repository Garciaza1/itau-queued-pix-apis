package itau.persistence.queue.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import itau.persistence.queue.application.port.in.PagamentoPersistenceUseCase;
import itau.persistence.queue.domain.model.PagamentoMessage;
import itau.pix.commons.messaging.RabbitMQConstants;

public class PaymentListener {
    private final PagamentoPersistenceUseCase persistenceService;

    public PaymentListener(PagamentoPersistenceUseCase persistenceService) {
        this.persistenceService = persistenceService;
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_PAGAMENTO_SUCESSO)
    public void receiveSuccess(PagamentoMessage message) {
        persistenceService.persist(message, true);
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_PAGAMENTO_FALHOU)
    public void receiveFailed(PagamentoMessage message) {
        persistenceService.persist(message, false);
    }

}
