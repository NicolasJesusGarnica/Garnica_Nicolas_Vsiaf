package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Config;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigService extends BaseCrudService<Config, Long> {

    @Autowired
    private ConfigRepository repository;

    @Override
    protected JpaRepository<Config, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la configuración";
    }

    @Override
    protected String getTableName() {
        return "configuraciones";
    }

    @Override
    protected String getSequenceName() {
        return "configuraciones_id_seq";
    }

    @Override
    protected Long getEntityId(Config entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Config entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Config create(Config entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La configuración con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
