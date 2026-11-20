package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Objgasto;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.ObjgastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObjgastoService extends BaseCrudService<Objgasto, Long> {

    @Autowired
    private ObjgastoRepository repository;

    @Override
    protected JpaRepository<Objgasto, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el objeto de gasto";
    }

    @Override
    protected String getTableName() {
        return "objeto_gasto";
    }

    @Override
    protected String getSequenceName() {
        return "objeto_gasto_id_seq";
    }

    @Override
    protected Long getEntityId(Objgasto entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Objgasto entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Objgasto create(Objgasto entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El objeto de gasto con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
