package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Data;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataService extends BaseCrudService<Data, Long> {

    @Autowired
    private DataRepository repository;

    @Override
    protected JpaRepository<Data, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el dato del sistema";
    }

    @Override
    protected String getTableName() {
        return "data_sistema";
    }

    @Override
    protected String getSequenceName() {
        return "data_sistema_id_seq";
    }

    @Override
    protected Long getEntityId(Data entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Data entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Data create(Data entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El dato del sistema con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
