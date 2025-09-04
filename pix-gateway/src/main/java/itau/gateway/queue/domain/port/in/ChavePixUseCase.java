package itau.gateway.queue.domain.port.in;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import itau.gateway.queue.domain.model.chave.ConsultaChavePixResponse;
import itau.gateway.queue.domain.model.chave.InclusaoChavePixRequest;
import itau.gateway.queue.domain.model.chave.InclusaoChavePixResponse;

public interface ChavePixUseCase {
    InclusaoChavePixResponse cadastrar(InclusaoChavePixRequest request);
    List<ConsultaChavePixResponse> consultar(
            UUID id,
            String tipoChave,
            String numeroAgencia,
            String numeroConta,
            String nomeCorrentista,
            LocalDate dataInclusao,
            LocalDate dataInativacao
    );
}
