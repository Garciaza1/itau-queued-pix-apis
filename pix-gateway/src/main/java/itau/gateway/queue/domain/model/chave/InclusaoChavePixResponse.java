package itau.gateway.queue.domain.model.chave;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InclusaoChavePixResponse {

    private UUID id;
    private String tipoChave;
    private String valorChave;
    private String tipoConta;
    private String numeroAgencia;
    private String numeroConta;
    private BigDecimal saldo;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraInativacao;
}
