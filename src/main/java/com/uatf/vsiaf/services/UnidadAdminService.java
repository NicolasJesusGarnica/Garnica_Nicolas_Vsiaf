package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.UnidadAdmin;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.UnidadAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnidadAdminService extends BaseCrudService<UnidadAdmin, Long> {

    @Autowired
    private UnidadAdminRepository repository;

    @Override
    protected JpaRepository<UnidadAdmin, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la unidad administrativa";
    }

    @Override
    protected String getTableName() {
        return "unidad_admin";
    }

    @Override
    protected String getSequenceName() {
        return "unidad_admin_id_seq";
    }

    @Override
    protected Long getEntityId(UnidadAdmin entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(UnidadAdmin entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public UnidadAdmin create(UnidadAdmin entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La unidad administrativa con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }
        if (entity.getCodigoUnidad() != null && !entity.getCodigoUnidad().trim().isEmpty() && repository.existsByCodigoUnidad(entity.getCodigoUnidad())) {
            throw new DuplicateResourceException("La unidad administrativa con identificador único '" + entity.getCodigoUnidad() + "' ya ha sido registrado.");
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
