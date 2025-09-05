package itau.worker.queue.application.port.in;

import itau.worker.queue.domain.model.PagamentoMessage;

public interface PagamentoWorkerUseCase {
    void process(PagamentoMessage pagamentoMessage);
}
