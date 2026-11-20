package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Resp;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.RespRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespService extends BaseCrudService<Resp, Long> {

    @Autowired
    private RespRepository repository;

    @Override
    protected JpaRepository<Resp, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el responsable";
    }

    @Override
    protected String getTableName() {
        return "responsables";
    }

    @Override
    protected String getSequenceName() {
        return "responsables_id_seq";
    }

    @Override
    protected Long getEntityId(Resp entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Resp entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Resp create(Resp entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El responsable con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }
        if (entity.getCi() != null && !entity.getCi().trim().isEmpty() && repository.existsByCi(entity.getCi())) {
            throw new DuplicateResourceException("El responsable con identificador único '" + entity.getCi() + "' ya ha sido registrado.");
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
