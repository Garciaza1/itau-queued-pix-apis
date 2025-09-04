package itau.gateway.queue.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import itau.pix.commons.config.DatabaseConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = DatabaseConstants.PIX_COLLECTION)
public class ChavePix {

    @Id
    private UUID id;
    private String tipoChave; // celular | email | cpf | cnpj | aleatorio
    private String valorChave;
    private String tipoConta; // corrente | poupanca
    private String numeroAgencia;
    private String numeroConta;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraInativacao;
    private String status; // ATIVO | INATIVO
}