package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.EstEnt;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.EstEntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstEntService extends BaseCrudService<EstEnt, Long> {

    @Autowired
    private EstEntRepository repository;

    @Override
    protected JpaRepository<EstEnt, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el estado de entidad";
    }

    @Override
    protected String getTableName() {
        return "estado_entidad";
    }

    @Override
    protected String getSequenceName() {
        return "estado_entidad_id_seq";
    }

    @Override
    protected Long getEntityId(EstEnt entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(EstEnt entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public EstEnt create(EstEnt entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El estado de entidad con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
