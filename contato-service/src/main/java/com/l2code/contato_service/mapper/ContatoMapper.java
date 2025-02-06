package com.l2code.contato_service.mapper;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.contato.ContatoDTO;
import com.l2code.contato_service.dto.contato.SimpleContatoDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ContatoMapper {

    public static SimpleContatoDTO toSimpleDto(Contato contato) {
        if (contato == null) {
            return null;
        }

        return new SimpleContatoDTO(
                contato.getNome(),
                contato.getEmail(),
                contato.getCelular(),
                contato.getTelefone()
        );
    }

    public static ContatoDTO toDto(Contato contato) {
        if (contato == null) {
            return null;
        }

        Long dataCadastro = null;
        if (contato.getDataCadastro() != null) {
            dataCadastro = contato.getDataCadastro()
                    .atZone(ZoneOffset.UTC)
                    .toInstant()
                    .toEpochMilli();
        }

        return new ContatoDTO(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getCelular(),
                contato.getTelefone(),
                contato.getSnFavorito() != null ? contato.getSnFavorito() : false,
                contato.getSnAtivo() != null ? contato.getSnAtivo() : false,
                dataCadastro
        );
    }

    public static Contato toEntity(SimpleContatoDTO dto) {
        if (dto == null) {
            return null;
        }

        Contato contato = new Contato();
        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());

        return contato;
    }

    public static Contato toEntity(ContatoDTO dto) {
        if (dto == null) {
            return null;
        }

        Contato contato = new Contato();
        contato.setId(dto.id());
        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());
        contato.setSnFavorito(dto.snFavorito());
        contato.setSnAtivo(dto.snAtivo());

        if (dto.dataCadastro() != null) {
            contato.setDataCadastro(LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.dataCadastro()), ZoneOffset.UTC));
        }

        return contato;
    }
}
