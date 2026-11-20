package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Entidad;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.EntidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntidadService extends BaseCrudService<Entidad, Long> {

    @Autowired
    private EntidadRepository repository;

    @Override
    protected JpaRepository<Entidad, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la entidad";
    }

    @Override
    protected String getTableName() {
        return "entidades";
    }

    @Override
    protected String getSequenceName() {
        return "entidades_id_seq";
    }

    @Override
    protected Long getEntityId(Entidad entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Entidad entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Entidad create(Entidad entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La entidad con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }
        if (entity.getCodigoEntidad() != null && !entity.getCodigoEntidad().trim().isEmpty() && repository.existsByCodigoEntidad(entity.getCodigoEntidad())) {
            throw new DuplicateResourceException("La entidad con identificador único '" + entity.getCodigoEntidad() + "' ya ha sido registrado.");
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
