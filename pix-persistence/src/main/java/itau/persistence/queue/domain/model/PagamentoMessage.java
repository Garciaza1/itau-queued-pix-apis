package itau.persistence.queue.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagamentoMessage {

    private String id;
    private BigDecimal amount;
    private String sender;
    private String receiver;
    private int retryCount = 0;

}
