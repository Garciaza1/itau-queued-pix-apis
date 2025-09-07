package itau.persistence.queue.application.port.out;

import itau.persistence.queue.domain.model.Pagamento;

public interface PagamentoRepositoryPort {
    Pagamento save(Pagamento pagamento);
}
