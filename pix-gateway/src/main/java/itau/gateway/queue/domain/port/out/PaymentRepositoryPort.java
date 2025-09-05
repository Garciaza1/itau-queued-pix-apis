package itau.gateway.queue.domain.port.out;

import org.springframework.stereotype.Repository;

import itau.gateway.queue.domain.model.pagamento.Pagamento;
@Repository
public interface PaymentRepositoryPort {
    void save(Pagamento pagamento);
}
