package itau.worker.queue.infrastructure.adapter.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import itau.worker.queue.domain.model.ChavePix;

@Repository
public interface SpringDataChavePixRepository extends MongoRepository<ChavePix, String> {
    boolean existsByValorChave(String valorChave);
    List<ChavePix> findByTipoChave(String tipoChave);
    List<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);
    Optional<ChavePix> findByNumeroConta(String numeroConta);
    Optional<ChavePix> findByValorChave(String valorChave);
}
