package itau.gateway.queue.infrastructure.adapter.out;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import itau.gateway.queue.domain.model.pagamento.Pagamento;
import itau.gateway.queue.domain.port.out.PaymentRepositoryPort;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringDataPagamentoRepository repository;

    public PaymentRepositoryAdapter(SpringDataPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Pagamento pagamento) {
        repository.save(Objects.requireNonNull(pagamento, "Pagamento não pode ser nulo"));
    }

    @Override
    public Optional<Pagamento> findById(String id) {
        return repository.findById(Objects.requireNonNull(id, "ID não pode ser nulo"));
    }
}
