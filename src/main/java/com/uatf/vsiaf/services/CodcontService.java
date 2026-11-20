package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.CodcontRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CodcontService extends BaseCrudService<Codcont, Long> {

    @Autowired
    private CodcontRepository repository;

    @Override
    protected JpaRepository<Codcont, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el código contable";
    }

    @Override
    protected String getTableName() {
        return "codigos_contables";
    }

    @Override
    protected String getSequenceName() {
        return "codigos_contables_id_seq";
    }

    @Override
    protected Long getEntityId(Codcont entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Codcont entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Codcont create(Codcont entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El código contable con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
