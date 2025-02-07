package com.l2code.contato_service.rest;

import com.l2code.contato_service.constants.AppConstants;
import com.l2code.contato_service.dto.contato.ContatoDTO;
import com.l2code.contato_service.dto.contato.ListContatoDTO;
import com.l2code.contato_service.dto.contato.SimpleContatoDTO;
import com.l2code.contato_service.mapper.ContatoMapper;
import com.l2code.contato_service.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/contatos")
public class ContatoResource {

    private final ContatoService contatoService;

    @Autowired
    public ContatoResource(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> create(@RequestBody SimpleContatoDTO dto, UriComponentsBuilder uriBuilder) {

        var entity = ContatoMapper.toEntity(dto);

        this.contatoService.create(entity);

        var contatoDTO = ContatoMapper.toDto(entity);

        var uri = uriBuilder.path("/contatos/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(contatoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> update(@PathVariable Long id, @RequestBody SimpleContatoDTO dto) {

        var entity = ContatoMapper.toEntity(dto);

        entity = this.contatoService.update(id, entity);

        var contatoDTO = ContatoMapper.toDto(entity);

        return ResponseEntity.ok(contatoDTO);
    }

    @GetMapping
    public ResponseEntity<ListContatoDTO> findAll() {

        var response = this.contatoService.findAll();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/favoritar")
    public ResponseEntity<Void> favorite(@PathVariable Long id) {

        this.contatoService.favorite(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desfavoritar")
    public ResponseEntity<Void> unFavorite(@PathVariable Long id) {

        this.contatoService.unFavorite(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {

        this.contatoService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> findById(@PathVariable Long id) {

        var entity = this.contatoService.findById(id);

        var contatoDTO = ContatoMapper.toDto(entity);

        return ResponseEntity.ok(contatoDTO);
    }
}
