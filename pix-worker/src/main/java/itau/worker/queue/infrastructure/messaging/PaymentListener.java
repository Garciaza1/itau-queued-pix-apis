package itau.worker.queue.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import itau.pix.commons.messaging.RabbitMQConstants;
import itau.worker.queue.application.port.in.PagamentoWorkerUseCase;
import itau.worker.queue.domain.model.PagamentoMessage;

@Component
public class PaymentListener {

    private final PagamentoWorkerUseCase workerService;

    public PaymentListener(PagamentoWorkerUseCase workerService) {
        this.workerService = workerService;
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_PAGAMENTO)
    public void receive(PagamentoMessage message) {
        workerService.process(message);
    }
}
