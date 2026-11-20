package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Baja;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.BajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BajaService extends BaseCrudService<Baja, Long> {

    @Autowired
    private BajaRepository repository;

    @Override
    protected JpaRepository<Baja, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la baja";
    }

    @Override
    protected String getTableName() {
        return "bajas";
    }

    @Override
    protected String getSequenceName() {
        return "bajas_id_seq";
    }

    @Override
    protected Long getEntityId(Baja entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Baja entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Baja create(Baja entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La baja con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
