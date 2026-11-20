package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.ClaDepts;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.ClaDeptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaDeptsService extends BaseCrudService<ClaDepts, Long> {

    @Autowired
    private ClaDeptsRepository repository;

    @Override
    protected JpaRepository<ClaDepts, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el clasificador de departamento";
    }

    @Override
    protected String getTableName() {
        return "cla_depts";
    }

    @Override
    protected String getSequenceName() {
        return "cla_depts_id_seq";
    }

    @Override
    protected Long getEntityId(ClaDepts entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(ClaDepts entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public ClaDepts create(ClaDepts entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El clasificador de departamento con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
