package itau.persistence.queue.infrastructure.adapter.out;

import org.springframework.stereotype.Component;

import itau.persistence.queue.application.port.out.PagamentoRepositoryPort;
import itau.persistence.queue.domain.model.Pagamento;

@Component
public class PagamentoRepositoryAdapter implements PagamentoRepositoryPort {

    private final SpringDataPagamentoRepository repository;

    public PagamentoRepositoryAdapter(SpringDataPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagamento save(Pagamento pagamento) {
        return repository.save(pagamento);
    }

    @Override
    public java.util.Optional<Pagamento> findById(String id) {
        return repository.findById(id);
    }
}
