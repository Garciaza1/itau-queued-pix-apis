package itau.gateway.queue.domain.model.pagamento;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagamentoRequest {

    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    private BigDecimal amount;

    @NotBlank(message = "O remetente não pode ser nulo ou vazio.")
    private String sender;

    @NotBlank(message = "O destinatário não pode ser nulo ou vazio.")
    private String receiver;
}
