package itau.gateway.queue.domain.model.pagamento;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import itau.pix.commons.config.DatabaseConstants;
import itau.pix.commons.enums.StatusPagamento;
import lombok.Data;

@Data
@Document(collection = DatabaseConstants.PAGAMENTO_COLLECTION)
public class Pagamento {

    @Id
    private String id;
    private BigDecimal amount;
    private String sender;
    private String receiver;
    private StatusPagamento status;
    private int retryCount = 0;
}
