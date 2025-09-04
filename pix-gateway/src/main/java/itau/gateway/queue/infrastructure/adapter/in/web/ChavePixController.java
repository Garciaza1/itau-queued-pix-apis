package itau.gateway.queue.infrastructure.adapter.in.web;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import itau.gateway.queue.domain.model.chave.ConsultaChavePixResponse;
import itau.gateway.queue.domain.model.chave.InclusaoChavePixRequest;
import itau.gateway.queue.domain.model.chave.InclusaoChavePixResponse;
import itau.gateway.queue.domain.port.in.ChavePixUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pix")

public class ChavePixController {

    private final ChavePixUseCase pixService;

    public ChavePixController(ChavePixUseCase pixService) {
        this.pixService = pixService;
    }

    @PostMapping
    public ResponseEntity<InclusaoChavePixResponse> incluirChave(
            @Valid @RequestBody InclusaoChavePixRequest request) {

        InclusaoChavePixResponse response = pixService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaChavePixResponse>> consultar(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String tipoChave,
            @RequestParam(required = false) String numeroAgencia,
            @RequestParam(required = false) String numeroConta,
            @RequestParam(required = false) String nomeCorrentista,
            @RequestParam(required = false) LocalDate dataInclusao,
            @RequestParam(required = false) LocalDate dataInativacao) {

        List<ConsultaChavePixResponse> resultado = pixService.consultar(
                id, tipoChave, numeroAgencia, numeroConta, nomeCorrentista, dataInclusao, dataInativacao
        );
        return ResponseEntity.ok(resultado);
    }
}
