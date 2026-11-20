package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Actual;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.exceptions.ResourceNotFoundException;
import com.uatf.vsiaf.repositories.ActualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActualService extends BaseCrudService<Actual, Long> {

    @Autowired
    private ActualRepository repository;

    @Override
    protected JpaRepository<Actual, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "El inmueble/activo";
    }

    @Override
    protected String getTableName() {
        return "activos_actuales";
    }

    @Override
    protected String getSequenceName() {
        return "activos_actuales_id_seq";
    }

    @Override
    protected Long getEntityId(Actual entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Actual entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Actual create(Actual entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El inmueble con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }
        if (entity.getCodigoActivo() != null && !entity.getCodigoActivo().trim().isEmpty() && repository.existsByCodigoActivo(entity.getCodigoActivo())) {
            throw new DuplicateResourceException("El inmueble con código de activo '" + entity.getCodigoActivo() + "' ya ha sido registrado.");
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

    public Actual findByCodigoActivo(String codigoActivo) {
        return repository.findByCodigoActivo(codigoActivo)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el inmueble con código de activo: " + codigoActivo));
    }
}
