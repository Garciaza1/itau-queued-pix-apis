package itau.worker.queue.domain.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import itau.worker.queue.domain.model.ChavePix;

@Repository
public interface ChavePixRepositoryPort {
    Optional<ChavePix> findById(String id);
    boolean existsByValorChave(String valorChave);
    List<ChavePix> findAll();
    List<ChavePix> findByTipoChave(String tipoChave);
    List<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);
    Optional<ChavePix> findByNumeroConta(String numeroConta);
    Optional<ChavePix> findByValorChave(String valorChave);
}