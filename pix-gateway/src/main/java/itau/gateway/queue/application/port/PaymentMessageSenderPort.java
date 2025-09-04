package itau.gateway.queue.application.port;

import itau.gateway.queue.domain.model.pagamento.PagamentoMessage;

public interface PaymentMessageSenderPort {
    void sendPaymentMessage(PagamentoMessage paymentMessage);
}
