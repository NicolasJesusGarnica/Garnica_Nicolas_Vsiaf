package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Mes;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.MesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesService extends BaseCrudService<Mes, Long> {

    @Autowired
    private MesRepository repository;

    @Override
    protected JpaRepository<Mes, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el mes";
    }

    @Override
    protected String getTableName() {
        return "meses";
    }

    @Override
    protected String getSequenceName() {
        return "meses_id_seq";
    }

    @Override
    protected Long getEntityId(Mes entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Mes entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Mes create(Mes entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El mes con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }

        if (entity.getNumeroMes() != null && repository.existsByNumeroMes(entity.getNumeroMes())) {
            throw new DuplicateResourceException("El mes número " + entity.getNumeroMes() + " ya ha sido registrado en el sistema. No se puede duplicar un mes en la gestión.");
        }

        if (entity.getNombreMes() != null && !entity.getNombreMes().trim().isEmpty() && repository.existsByNombreMesIgnoreCase(entity.getNombreMes().trim())) {
            throw new DuplicateResourceException("El mes '" + entity.getNombreMes() + "' ya ha sido registrado.");
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
