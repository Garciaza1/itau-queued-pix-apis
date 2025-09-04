package itau.gateway.queue.application.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;

import itau.gateway.queue.application.port.PaymentRepositoryPort;
import itau.gateway.queue.domain.model.pagamento.Pagamento;
import itau.gateway.queue.domain.model.pagamento.PagamentoMessage;
import itau.gateway.queue.infrastructure.config.IdGenerator;
import itau.pix.commons.enums.StatusPagamento;
import itau.pix.commons.messaging.RabbitMQConstants;

public class PagamentoService {

    private final IdGenerator idGenerator;
    private final PaymentRepositoryPort paymentRepository;
    private final RabbitTemplate rabbitTemplate;

    public PagamentoService(IdGenerator idGenerator, PaymentRepositoryPort paymentRepository, RabbitTemplate rabbitTemplate) {
        this.idGenerator = idGenerator;
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPayment(Pagamento paymentRequest) {

        String paymentId = idGenerator.generateId();

        Pagamento paymentEntity = new Pagamento();
        paymentEntity.setId(paymentId);
        paymentEntity.setAmount(paymentRequest.getAmount());
        paymentEntity.setSender(paymentRequest.getSender());
        paymentEntity.setReceiver(paymentRequest.getReceiver());
        paymentEntity.setStatus(StatusPagamento.PROCESSANDO);

        // salva asincronamente.
        saveAsync(paymentEntity);
        
        PagamentoMessage paymentMessage = new PagamentoMessage();
        paymentMessage.setId(paymentId);
        paymentMessage.setAmount(paymentRequest.getAmount());
        paymentMessage.setSender(paymentRequest.getSender());
        paymentMessage.setReceiver(paymentRequest.getReceiver());

        rabbitTemplate.convertAndSend(RabbitMQConstants.FILA_PAGAMENTO, paymentMessage);
        System.out.println("Payment with ID " + paymentId + " has been sent to the processing RabbitMQ queue.");
    }

    // dependendo das regras de negocio podemos deixar ou não o save asincrono
    @Async
    public void saveAsync(Pagamento paymentEntity) {
        paymentRepository.save(paymentEntity);
        System.out.println("Payment with ID " + paymentEntity.getId() + " has been saved - PROCESSANDO.");
    }
}
