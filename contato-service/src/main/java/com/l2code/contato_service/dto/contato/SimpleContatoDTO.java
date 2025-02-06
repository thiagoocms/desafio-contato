package com.l2code.contato_service.dto.contato;

public record SimpleContatoDTO(
        String nome,
        String email,
        String celular,
        String telefone
) {
}
