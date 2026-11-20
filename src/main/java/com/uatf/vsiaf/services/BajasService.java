package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Bajas;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.BajasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BajasService extends BaseCrudService<Bajas, Long> {

    @Autowired
    private BajasRepository repository;

    @Override
    protected JpaRepository<Bajas, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el detalle de baja";
    }

    @Override
    protected String getTableName() {
        return "bajas_detalle";
    }

    @Override
    protected String getSequenceName() {
        return "bajas_detalle_id_seq";
    }

    @Override
    protected Long getEntityId(Bajas entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Bajas entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Bajas create(Bajas entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El detalle de baja con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
