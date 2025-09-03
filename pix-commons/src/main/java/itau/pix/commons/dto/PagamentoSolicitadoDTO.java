package itau.pix.commons.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record PagamentoSolicitadoDTO(
    @NotBlank String chavePix,
    BigDecimal valor
) {}