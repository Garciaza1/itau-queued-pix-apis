package itau.gateway.queue.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itau.gateway.queue.application.service.PagamentoService;
import itau.gateway.queue.domain.model.pagamento.Pagamento;
import itau.gateway.queue.domain.model.pagamento.PagamentoRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pix/payments")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<Void> processPayment(@Valid @RequestBody PagamentoRequest paymentRequest) {
        Pagamento pagamento = convertToPagamento(paymentRequest);
        pagamentoService.processPayment(pagamento);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private Pagamento convertToPagamento(PagamentoRequest paymentRequest) {
        Pagamento pagamento = new Pagamento();
        pagamento.setAmount(paymentRequest.getAmount());
        pagamento.setSender(paymentRequest.getSender());
        pagamento.setReceiver(paymentRequest.getReceiver());
        return pagamento;
    }

}
