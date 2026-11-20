package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.CtaPar;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.CtaParRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CtaParService extends BaseCrudService<CtaPar, Long> {

    @Autowired
    private CtaParRepository repository;

    @Override
    protected JpaRepository<CtaPar, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la cuenta partida";
    }

    @Override
    protected String getTableName() {
        return "cuentas_partidas";
    }

    @Override
    protected String getSequenceName() {
        return "cuentas_partidas_id_seq";
    }

    @Override
    protected Long getEntityId(CtaPar entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(CtaPar entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public CtaPar create(CtaPar entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La cuenta partida con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
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
