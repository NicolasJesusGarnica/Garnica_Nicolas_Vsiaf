package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Orguser;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.OrguserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrguserService extends BaseCrudService<Orguser, Long> {

    @Autowired
    private OrguserRepository repository;

    @Override
    protected JpaRepository<Orguser, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el usuario por entidad";
    }

    @Override
    protected String getTableName() {
        return "org_usuarios";
    }

    @Override
    protected String getSequenceName() {
        return "org_usuarios_id_seq";
    }

    @Override
    protected Long getEntityId(Orguser entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Orguser entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Orguser create(Orguser entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El usuario por entidad con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
