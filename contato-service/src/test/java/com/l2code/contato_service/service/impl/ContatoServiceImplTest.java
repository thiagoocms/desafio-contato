package com.l2code.contato_service.service.impl;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.contato.ContatoDTO;
import com.l2code.contato_service.dto.contato.ItemListContatoDTO;
import com.l2code.contato_service.dto.contato.ListContatoDTO;
import com.l2code.contato_service.dto.projection.SimpleContatoProjection;
import com.l2code.contato_service.repository.ContatoRepository;
import com.l2code.contato_service.validation.ContatoValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class ContatoServiceImplTest {
    @Mock
    ContatoRepository contatoRepository;
    @Mock
    ContatoValidation contatoValidation;
    @InjectMocks
    ContatoServiceImpl contatoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(mock(Contato.class));

        Contato result = contatoServiceImpl.create(new Contato());
        verify(contatoValidation).checkMandatoryFields(any(Contato.class));
        verify(contatoValidation).checkValidFields(any(Contato.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        var contato = new Contato();
        contato.setNome("thiago");
        when(contatoRepository.findAllBySnFavoritoIsTrueAndSnAtivoIsTrueOrderByNomeAsc()).thenReturn(List.of(mock(SimpleContatoProjection.class)));
        when(contatoRepository.findAllBySnAtivoIsTrueOrderByNomeAsc()).thenReturn(List.of(contato));

        ListContatoDTO result = contatoServiceImpl.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(mock(Contato.class));
        when(contatoValidation.checkUpdateConsistence(anyLong(), any(Contato.class))).thenReturn(new Contato());

        Contato result = contatoServiceImpl.update(1L, new Contato());
        verify(contatoValidation).checkValidFields(any(Contato.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFavorite() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(mock(Contato.class));
        when(contatoValidation.checkExist(anyLong())).thenReturn(new Contato());

        contatoServiceImpl.favorite(1L);
    }

    @Test
    void testUnFavorite() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(mock(Contato.class));
        when(contatoValidation.checkExist(anyLong())).thenReturn(new Contato());

        contatoServiceImpl.unFavorite(1L);
    }

    @Test
    void testInactivate() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(mock(Contato.class));
        when(contatoValidation.checkExist(anyLong())).thenReturn(new Contato());

        contatoServiceImpl.inactivate(1L);
    }
}