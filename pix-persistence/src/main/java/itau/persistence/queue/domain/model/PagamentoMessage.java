package itau.persistence.queue.domain.model;

import java.math.BigDecimal;

import itau.pix.commons.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagamentoMessage {

    private String id;
    private BigDecimal amount;
    private String senderAccount;
    private String receiverPixKey;
    private StatusPagamento status;
    private int retryCount = 0;
    private String errorDescription;
}
