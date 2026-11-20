package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Oficina;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OficinaService extends BaseCrudService<Oficina, Long> {

    @Autowired
    private OficinaRepository repository;

    @Override
    protected JpaRepository<Oficina, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "la oficina";
    }

    @Override
    protected String getTableName() {
        return "oficinas";
    }

    @Override
    protected String getSequenceName() {
        return "oficinas_id_seq";
    }

    @Override
    protected Long getEntityId(Oficina entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Oficina entity, Long id) {
        entity.setId(id);
    }

    @Override
    @Transactional
    public Oficina create(Oficina entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("La oficina con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }
        if (entity.getCodigoOficina() != null && !entity.getCodigoOficina().trim().isEmpty() && repository.existsByCodigoOficina(entity.getCodigoOficina())) {
            throw new DuplicateResourceException("La oficina con identificador único '" + entity.getCodigoOficina() + "' ya ha sido registrado.");
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
