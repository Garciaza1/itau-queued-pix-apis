package itau.persistence.queue.infrastructure.adapter.out;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import itau.persistence.queue.domain.model.Pagamento;

@Repository
public interface SpringDataPagamentoRepository extends MongoRepository<Pagamento, String> {

}
