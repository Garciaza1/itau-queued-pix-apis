package itau.persistence.queue.application.port.in;

import itau.persistence.queue.domain.model.PagamentoMessage;

public interface PagamentoPersistenceUseCase {
    void persist(PagamentoMessage message, boolean sucesso);
}
