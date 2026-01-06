package itau.gateway.queue.infrastructure.adapter.out;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import itau.gateway.queue.domain.model.chave.ChavePix;
import itau.gateway.queue.domain.port.out.ChavePixRepositoryPort;

@Repository
public class ChavePixRepositoryAdapter implements ChavePixRepositoryPort {

    private final SpringDataChavePixRepository repository;

    public ChavePixRepositoryAdapter(SpringDataChavePixRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByValorChave(String valorChave) {
        return repository.existsByValorChave(valorChave);
    }

    @Override
    public ChavePix save(ChavePix chavePix) {
        return repository.save(Objects.requireNonNull(chavePix, "ChavePix não pode ser nulo"));
    }

    @Override
    public Optional<ChavePix> findById(UUID id) {
        return repository.findById(Objects.requireNonNull(id, "UUID não pode ser nulo"));
    }

    @Override
    public List<ChavePix> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ChavePix> findByTipoChave(String tipoChave) {
        return repository.findByTipoChave(tipoChave);
    }

    @Override
    public List<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta) {
        return repository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
    }

    @Override
    public List<ChavePix> findByNomeCorrentista(String nomeCorrentista) {
        return repository.findByNomeCorrentista(nomeCorrentista);
    }

    @Override
    public List<ChavePix> findByDataHoraInclusaoBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fim) {
        return repository.findByDataHoraInclusaoBetween(inicio, fim);
    }

    @Override
    public List<ChavePix> findByDataHoraInativacaoBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fim) {
        return repository.findByDataHoraInativacaoBetween(inicio, fim);
    }

}
