package itau.gateway.queue.infrastructure.adapter.out;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import itau.gateway.queue.domain.model.pagamento.Pagamento;
@Repository
public interface SpringDataPagamentoRepository extends MongoRepository<Pagamento, String> {

}
