package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Passw;
import com.uatf.vsiaf.exceptions.BusinessRuleException;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.PasswRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PasswService extends BaseCrudService<Passw, Long> {

    @Autowired
    private PasswRepository repository;

    @Override
    protected JpaRepository<Passw, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el usuario";
    }

    @Override
    protected String getTableName() {
        return "usuarios_passw";
    }

    @Override
    protected String getSequenceName() {
        return "usuarios_passw_id_seq";
    }

    @Override
    protected Long getEntityId(Passw entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Passw entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Passw create(Passw entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El usuario con ID " + entity.getId() + " ya ha sido registrado.");
        }

        if (entity.getUsername() != null && repository.existsByUsername(entity.getUsername().trim())) {
            throw new DuplicateResourceException("El nombre de usuario '" + entity.getUsername().trim() + "' ya se encuentra en uso. Por favor, elija otro.");
        }

        if (entity.getActivo() == null) {
            entity.setActivo(true);
        }
        if (entity.getGrupo() == null || entity.getGrupo().trim().isEmpty()) {
            entity.setGrupo("User");
        }
        if (entity.getFechaUltimoAcceso() == null) {
            entity.setFechaUltimoAcceso(new Date());
        }

        if (entity.getId() == null) {
            Long nextId = getNextSequenceValue();
            entity.setId(nextId);
        } else {
            syncSequence();
        }

        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        throw new BusinessRuleException("Regla de negocio VSIAF: Por normas de auditoría y control gubernamental, los usuarios del sistema no pueden ser eliminados físicamente. Para inhabilitar un acceso, utilice PATCH con 'activo': false.");
    }
}
