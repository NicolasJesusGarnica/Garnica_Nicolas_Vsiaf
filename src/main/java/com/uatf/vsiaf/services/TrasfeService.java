package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Trasfe;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.TrasfeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrasfeService extends BaseCrudService<Trasfe, Long> {

    @Autowired
    private TrasfeRepository repository;

    @Override
    protected JpaRepository<Trasfe, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la transferencia";
    }

    @Override
    protected String getTableName() {
        return "transferencias";
    }

    @Override
    protected String getSequenceName() {
        return "transferencias_id_seq";
    }

    @Override
    protected Long getEntityId(Trasfe entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Trasfe entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Trasfe create(Trasfe entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La transferencia con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
