package itau.pix.commons.dto;

import java.time.LocalDateTime;

public record EventoChavePixDTO(
    String chavePix,
    String tipoEvento, 
    LocalDateTime dataHora
) {}
