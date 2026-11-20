package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Backups;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.BackupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BackupsService extends BaseCrudService<Backups, Long> {

    @Autowired
    private BackupsRepository repository;

    @Override
    protected JpaRepository<Backups, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el backup";
    }

    @Override
    protected String getTableName() {
        return "backups_log";
    }

    @Override
    protected String getSequenceName() {
        return "backups_log_id_seq";
    }

    @Override
    protected Long getEntityId(Backups entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Backups entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Backups create(Backups entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El backup con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
