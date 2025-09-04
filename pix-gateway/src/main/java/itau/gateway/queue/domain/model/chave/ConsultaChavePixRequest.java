package itau.gateway.queue.domain.model.chave;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaChavePixRequest {

    private UUID id;
    private String tipoChave;
    private String tipoConta;
    private String numeroAgencia;
    private String numeroConta;
    private String nomeCorrentista;
    private LocalDate dataInclusao;
    private LocalDate dataInativacao;
}
