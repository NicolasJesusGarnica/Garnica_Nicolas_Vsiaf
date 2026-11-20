package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Estado;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService extends BaseCrudService<Estado, Long> {

    @Autowired
    private EstadoRepository repository;

    @Override
    protected JpaRepository<Estado, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el estado del bien";
    }

    @Override
    protected String getTableName() {
        return "estados_bien";
    }

    @Override
    protected String getSequenceName() {
        return "estados_bien_id_seq";
    }

    @Override
    protected Long getEntityId(Estado entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Estado entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Estado create(Estado entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El estado del bien con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
