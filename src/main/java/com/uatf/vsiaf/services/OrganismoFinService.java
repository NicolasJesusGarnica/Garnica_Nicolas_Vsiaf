package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.OrganismoFin;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.OrganismoFinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganismoFinService extends BaseCrudService<OrganismoFin, Long> {

    @Autowired
    private OrganismoFinRepository repository;

    @Override
    protected JpaRepository<OrganismoFin, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el organismo financiador";
    }

    @Override
    protected String getTableName() {
        return "organismos_fin";
    }

    @Override
    protected String getSequenceName() {
        return "organismos_fin_id_seq";
    }

    @Override
    protected Long getEntityId(OrganismoFin entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(OrganismoFin entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public OrganismoFin create(OrganismoFin entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El organismo financiador con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
