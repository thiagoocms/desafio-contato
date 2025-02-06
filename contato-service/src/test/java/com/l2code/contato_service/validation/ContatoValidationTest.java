package com.l2code.contato_service.validation;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.exception.BadRequestException;
import com.l2code.contato_service.exception.ResourceNotFoundException;
import com.l2code.contato_service.repository.ContatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ContatoValidationTest {

    @Mock
    private ContatoRepository contatoRepository;
    @InjectMocks
    private ContatoValidation contatoValidation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckMandatoryFieldsException() {
       Assertions.assertThrows(BadRequestException.class, () -> contatoValidation.checkMandatoryFields(new Contato()));
    }

    @Test
    void testCheckUpdateConsistence() {
        when(contatoRepository.findByIdAndSnAtivoIsTrue(anyLong())).thenReturn(Optional.of(mock(Contato.class)));
        Contato result = contatoValidation.checkUpdateConsistence(1L, new Contato());
        Assertions.assertNotNull(result);
    }

    @Test
    void testCheckExist() {
        when(contatoRepository.findByIdAndSnAtivoIsTrue(anyLong())).thenReturn(Optional.of(mock(Contato.class)));
        Contato result = contatoValidation.checkExist(1L);
        Assertions.assertNotNull(result);
    }

    @Test
    void testCheckExistException() {
        when(contatoRepository.findByIdAndSnAtivoIsTrue(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> contatoValidation.checkExist(1L)) ;
    }

    @Test
    void testCheckValidFields() {
        var contato = new Contato();
        contato.setEmail("thiago@gmail.com");
        contato.setCelular("81995005627");
        contatoValidation.checkValidFields(contato);
    }

    @Test
    void testCheckValidFieldsException() {
        Assertions.assertThrows(BadRequestException.class, () -> contatoValidation.checkValidFields(new Contato()));
    }
}