package itau.persistence.queue.infrastructure.adapter.out;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import itau.persistence.queue.domain.model.ChavePix;

@Repository
public interface SpringDataChavePixMongoRepository extends MongoRepository<ChavePix, UUID> {
    Optional<ChavePix> findByValorChave(String valorChave);
    Optional<ChavePix> findByNumeroConta(String numeroConta);
    Optional<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);
}
