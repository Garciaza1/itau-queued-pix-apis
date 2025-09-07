package itau.persistence.queue.infrastructure.adapter.out;

import org.springframework.data.mongodb.repository.MongoRepository;

import itau.persistence.queue.domain.model.Pagamento;

public interface SpringDataPagamentoRepository extends MongoRepository<Pagamento, String> {

}
