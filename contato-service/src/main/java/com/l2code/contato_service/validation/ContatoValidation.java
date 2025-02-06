package com.l2code.contato_service.validation;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.exception.BadRequestException;
import com.l2code.contato_service.exception.ConflictException;
import com.l2code.contato_service.exception.ResourceNotFoundException;
import com.l2code.contato_service.repository.ContatoRepository;
import com.l2code.contato_service.utils.ContatoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.function.Consumer;

@Component
public class ContatoValidation {

    private final ContatoRepository contatoRepository;

    @Autowired
    public ContatoValidation(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public void checkMandatoryFields(Contato entity) {

        var notInformedFieldsList = new ArrayList<String>();

        if (!StringUtils.hasText(entity.getNome())) {
            notInformedFieldsList.add("Nome");
        }

        if (!StringUtils.hasText(entity.getEmail())) {
            notInformedFieldsList.add("E-mail");
        }

        if (!StringUtils.hasText(entity.getCelular())) {
            notInformedFieldsList.add("N do celular");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }

    }

    public Contato checkUpdateConsistence(Long id, Contato toUpdateEntity) {

        var persistedEntity = this.checkExist(id);

        this.updateIfNotBlank(toUpdateEntity.getNome(), persistedEntity::setNome, persistedEntity.getNome());
        this.updateIfNotBlank(toUpdateEntity.getEmail(), persistedEntity::setEmail, persistedEntity.getEmail());
        this.updateIfNotBlank(toUpdateEntity.getCelular(), persistedEntity::setCelular, persistedEntity.getCelular());
        this.updateIfNotBlank(toUpdateEntity.getTelefone(), persistedEntity::setTelefone, persistedEntity.getTelefone());

        return persistedEntity;
    }

    public Contato checkExist(Long id) {

        var optional = this.contatoRepository.findByIdAndSnAtivoIsTrue(id);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Contato ativo não encontrado."));
    }

    public void checkValidFields(Contato entity) {

        if (!ContatoUtils.isValid(entity.getEmail(), ContatoUtils.Regex.EMAIL)) {
            throw new BadRequestException("E-mail invalido");
        }

        if (!ContatoUtils.isValid(entity.getCelular(), ContatoUtils.Regex.PHONE)) {
            throw new BadRequestException("N do celular invalido");
        }
    }

    public void checkValidExist(Contato entity) {

        var count = this.contatoRepository.countByCelularAndSnAtivoIsTrue(entity.getCelular());
        if (count > 0) {
            throw new ConflictException("Já existe um contato com esse N do celular");
        }
    }

    private void updateIfNotBlank(String newValue, Consumer<String> setter, String currentValue) {
        setter.accept(StringUtils.hasText(newValue) ? newValue : currentValue);
    }
}
