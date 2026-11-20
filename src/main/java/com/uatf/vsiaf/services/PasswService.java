package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Passw;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.PasswRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return "el usuario/credencial";
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
            throw new DuplicateResourceException("El usuario/credencial con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
}
