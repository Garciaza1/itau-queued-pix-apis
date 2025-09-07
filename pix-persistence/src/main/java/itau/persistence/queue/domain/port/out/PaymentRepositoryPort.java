package itau.persistence.queue.domain.port.out;

import itau.persistence.queue.domain.model.Pagamento;

public interface PaymentRepositoryPort {
    Pagamento save(Pagamento pagamento);
}
