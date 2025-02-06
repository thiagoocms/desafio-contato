package com.l2code.contato_service.service;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.contato.ListContatoDTO;

public interface ContatoService {

    Contato create(Contato entity);

    ListContatoDTO findAll();

    Contato update(Long id, Contato entity);

    void favorite(Long id);

    void unFavorite(Long id);

    void inactivate(Long id);
}
