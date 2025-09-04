package itau.gateway.queue.application.port;

import itau.gateway.queue.domain.model.pagamento.Pagamento;

public interface PaymentRepositoryPort {
    void save(Pagamento pagamento);
}
