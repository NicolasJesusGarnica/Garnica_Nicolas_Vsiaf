package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Reports;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportsService extends BaseCrudService<Reports, Long> {

    @Autowired
    private ReportsRepository repository;

    @Override
    protected JpaRepository<Reports, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el reporte";
    }

    @Override
    protected String getTableName() {
        return "reportes";
    }

    @Override
    protected String getSequenceName() {
        return "reportes_id_seq";
    }

    @Override
    protected Long getEntityId(Reports entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Reports entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Reports create(Reports entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El reporte con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
