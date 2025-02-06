package com.l2code.contato_service.dto.contato;

import java.util.List;

public record ItemListContatoDTO(
        String letra,
        List<ContatoDTO> contatos
) {

}
