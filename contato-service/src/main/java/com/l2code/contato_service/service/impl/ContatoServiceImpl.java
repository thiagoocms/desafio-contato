package com.l2code.contato_service.service.impl;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.contato.ItemListContatoDTO;
import com.l2code.contato_service.dto.contato.ListContatoDTO;
import com.l2code.contato_service.mapper.ContatoMapper;
import com.l2code.contato_service.repository.ContatoRepository;
import com.l2code.contato_service.service.ContatoService;
import com.l2code.contato_service.validation.ContatoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ContatoValidation contatoValidation;

    @Autowired
    public ContatoServiceImpl(ContatoRepository contatoRepository, ContatoValidation contatoValidation) {
        this.contatoRepository = contatoRepository;
        this.contatoValidation = contatoValidation;
    }

    @Override
    public Contato create(Contato entity) {

        this.contatoValidation.checkMandatoryFields(entity);

        this.contatoValidation.checkValidFields(entity);

        this.contatoRepository.save(entity);

        return entity;
    }

    @Override
    public ListContatoDTO findAll() {

        var map = new HashMap<String, List<Contato>>();

        var itemListContatoList = new ArrayList<ItemListContatoDTO>();

        var favList = this.contatoRepository.findAllBySnFavoritoIsTrueAndSnAtivoIsTrueOrderByNomeAsc();

        var list = this.contatoRepository.findAllBySnAtivoIsTrueOrderByNomeAsc();

        for (Contato contato : list) {

            var letter = contato.getNome().substring(0, 1);

            if (!map.containsKey(letter)) {

                var listLetter = list.stream().filter(item -> item.getNome().substring(0, 1).equals(letter)).toList();

                map.put(letter, listLetter);
            }
        }

        map.forEach((let, contatos) -> itemListContatoList.add(new ItemListContatoDTO(let, contatos.stream().map(ContatoMapper::toDto).toList())));

        return new ListContatoDTO(favList, itemListContatoList);
    }

    @Override
    public Contato update(Long id, Contato entity) {

        entity = this.contatoValidation.checkUpdateConsistence(id, entity);

        this.contatoValidation.checkValidFields(entity);

        this.contatoRepository.save(entity);

        return entity;
    }

    @Override
    public void favorite(Long id) {
        this.modifyFavorite(id, Boolean.TRUE);
    }

    @Override
    public void unFavorite(Long id) {
        this.modifyFavorite(id, Boolean.FALSE);
    }

    @Override
    public void inactivate(Long id) {
        var entity = this.contatoValidation.checkExist(id);
        entity.setSnAtivo(Boolean.FALSE);
        this.contatoRepository.save(entity);
    }

    private void modifyFavorite(Long id, boolean bool) {
        var entity = this.contatoValidation.checkExist(id);
        entity.setSnFavorito(bool);
        this.contatoRepository.save(entity);
    }
}
