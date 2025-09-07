package itau.persistence.queue.application.service;

import org.springframework.stereotype.Service;

import itau.persistence.queue.application.port.in.PagamentoPersistenceUseCase;
import itau.persistence.queue.application.port.out.PagamentoRepositoryPort;
import itau.persistence.queue.domain.model.Pagamento;
import itau.persistence.queue.domain.model.PagamentoMessage;
import itau.pix.commons.enums.StatusPagamento;

@Service
public class PaymentPersistenceService implements PagamentoPersistenceUseCase {

    private final PagamentoRepositoryPort repository;

    public PaymentPersistenceService(PagamentoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void persist(PagamentoMessage message, boolean sucesso) {
        StatusPagamento status = sucesso ? StatusPagamento.SUCESSO : StatusPagamento.FALHOU;

        Pagamento pagamento = new Pagamento(
            message.getId(),
            message.getAmount(),
            message.getSender(),
            message.getReceiver(),
            status
        );

        repository.save(pagamento);
    }
}
