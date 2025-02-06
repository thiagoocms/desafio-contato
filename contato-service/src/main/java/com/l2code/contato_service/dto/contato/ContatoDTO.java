package com.l2code.contato_service.dto.contato;

public record ContatoDTO(
        Long id,
        String nome,
        String email,
        String celular,
        String telefone,
        boolean snFavorito,
        boolean snAtivo,
        Long dataCadastro
) {
}
