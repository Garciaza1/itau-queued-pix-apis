package itau.worker.queue.infrastructure.adapter.out;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import itau.worker.queue.domain.model.ChavePix;
import itau.worker.queue.domain.port.out.ChavePixRepositoryPort;

@Repository
public class ChavePixRepositoryAdapter implements ChavePixRepositoryPort {

    private final SpringDataChavePixRepository repository;

    public ChavePixRepositoryAdapter(SpringDataChavePixRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ChavePix> findById(String id) {
        return repository.findById(Objects.requireNonNull(id, "ID não pode ser nulo"));
    }

    @Override
    public boolean existsByValorChave(String valorChave) {
        return repository.existsByValorChave(valorChave);
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
    public Optional<ChavePix> findByNumeroConta(String numeroConta) {
        return repository.findByNumeroConta(numeroConta);
    }

    @Override
    public Optional<ChavePix> findByValorChave(String valorChave) {
        return repository.findByValorChave(valorChave);
    }

}
