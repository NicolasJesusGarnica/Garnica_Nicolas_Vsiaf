package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Reval;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.RevalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RevalService extends BaseCrudService<Reval, Long> {

    @Autowired
    private RevalRepository repository;

    @Override
    protected JpaRepository<Reval, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el revalúo";
    }

    @Override
    protected String getTableName() {
        return "revaluos";
    }

    @Override
    protected String getSequenceName() {
        return "revaluos_id_seq";
    }

    @Override
    protected Long getEntityId(Reval entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Reval entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Reval create(Reval entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El revalúo con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
