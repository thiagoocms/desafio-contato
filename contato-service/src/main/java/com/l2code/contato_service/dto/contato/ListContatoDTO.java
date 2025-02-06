package com.l2code.contato_service.dto.contato;

import com.l2code.contato_service.dto.projection.SimpleContatoProjection;

import java.util.List;

public record ListContatoDTO(
        List<SimpleContatoProjection> favoritos,
        List<ItemListContatoDTO> letras
) {
}
