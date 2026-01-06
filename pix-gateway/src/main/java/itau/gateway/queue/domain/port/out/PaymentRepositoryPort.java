package itau.gateway.queue.domain.port.out;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import itau.gateway.queue.domain.model.pagamento.Pagamento;

@Repository
public interface PaymentRepositoryPort {
    void save(Pagamento pagamento);
    Optional<Pagamento> findById(String id); // Adicione esta linha
}
