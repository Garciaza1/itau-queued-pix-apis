package itau.gateway.queue.domain.model.pagamento;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoMessage {

    private String id;
    private BigDecimal amount;
    private String sender;
    private String receiver;
    private int retryCount = 0;
}