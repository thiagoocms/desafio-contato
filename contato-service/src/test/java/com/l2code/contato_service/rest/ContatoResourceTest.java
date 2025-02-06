package com.l2code.contato_service.rest;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.contato.ContatoDTO;
import com.l2code.contato_service.dto.contato.ItemListContatoDTO;
import com.l2code.contato_service.dto.contato.ListContatoDTO;
import com.l2code.contato_service.dto.contato.SimpleContatoDTO;
import com.l2code.contato_service.service.ContatoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.mockito.Mockito.*;

class ContatoResourceTest {
    @Mock
    ContatoService contatoService;
    @InjectMocks
    ContatoResource contatoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        when(contatoService.create(any(Contato.class))).thenReturn(new Contato());

        ResponseEntity<ContatoDTO> result = contatoResource.create(new SimpleContatoDTO("nome", "email", "celular", "telefone"), UriComponentsBuilder.newInstance());
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        when(contatoService.update(anyLong(), any(Contato.class))).thenReturn(new Contato());

        ResponseEntity<ContatoDTO> result = contatoResource.update(1L, new SimpleContatoDTO("nome", "email", "celular", "telefone"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        when(contatoService.findAll()).thenReturn(mock(ListContatoDTO.class));

        ResponseEntity<ListContatoDTO> result = contatoResource.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testFavorite() {
        ResponseEntity<Void> result = contatoResource.favorite(1L);
        verify(contatoService).favorite(anyLong());
        Assertions.assertNotNull(result);
    }

    @Test
    void testUnFavorite() {
        ResponseEntity<Void> result = contatoResource.unFavorite(1L);
        verify(contatoService).unFavorite(anyLong());
        Assertions.assertNotNull(result);
    }

    @Test
    void testInactivate() {
        ResponseEntity<Void> result = contatoResource.inactivate(1L);
        verify(contatoService).inactivate(anyLong());
        Assertions.assertNotNull(result);
    }
}