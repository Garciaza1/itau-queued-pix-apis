package itau.pix.commons.event;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoProcessadoEvent implements Serializable {
    private String chavePix;
    private BigDecimal valor;
    private String status; 
    private String motivo;

}
