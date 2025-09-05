package itau.gateway.queue.infrastructure.adapter.out;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import itau.gateway.queue.domain.model.chave.ChavePix;
@Repository
public interface SpringDataChavePixRepository extends MongoRepository<ChavePix, UUID> {

    boolean existsByValorChave(String valorChave);

    List<ChavePix> findByTipoChave(String tipoChave);
    
    List<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);

    List<ChavePix> findByNomeCorrentista(String nomeCorrentista);

    List<ChavePix> findByDataHoraInclusaoBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fim);

    List<ChavePix> findByDataHoraInativacaoBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fim);
}
