package itau.gateway.queue.domain.port.in;

import itau.gateway.queue.domain.model.pagamento.Pagamento;

public interface PagamentoUseCase {
    void processPayment(Pagamento pagamento);
}
