package itau.gateway.queue.domain.model.chave;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InclusaoChavePixRequest {

    @NotBlank(message = "O tipo da chave é obrigatório.")
    @Size(max = 9, message = "O tipo da chave deve ter no máximo 9 caracteres.")
    private String tipoChave;

    @NotBlank(message = "O valor da chave é obrigatório.")
    @Size(max = 77, message = "O valor da chave deve ter no máximo 77 caracteres.")
    private String valorChave;

    @NotBlank(message = "O tipo da conta é obrigatório.")
    @Size(max = 10, message = "O tipo da conta deve ter no máximo 10 caracteres.")
    private String tipoConta;

    @NotNull(message = "O número da agência é obrigatório.")
    @Size(min = 4, max = 4, message = "O número da agência deve ter 4 dígitos.")
    private String numeroAgencia;

    @NotNull(message = "O número da conta é obrigatório.")
    @Size(min = 8, max = 8, message = "O número da conta deve ter 8 dígitos.")
    private String numeroConta;

    @NotBlank(message = "O nome do correntista é obrigatório.")
    @Size(max = 30, message = "O nome do correntista deve ter no máximo 30 caracteres.")
    private String nomeCorrentista;

    @NotNull(message = "O saldo é obrigatório.")
    @DecimalMin(value = "0.01", message = "O saldo deve ser maior que zero")
    private BigDecimal saldo;

    // não é obrigatório
    @Size(max = 45, message = "O sobrenome do correntista deve ter no máximo 45 caracteres.")
    private String sobrenomeCorrentista;
}
