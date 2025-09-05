package itau.worker.queue.domain.model;

import java.math.BigDecimal;

import itau.pix.commons.enums.StatusPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoMessage {

    private String id;
    private BigDecimal amount;
    private String sender;
    private String receiver;
    private StatusPagamento status;
    private int retryCount = 0;
}
