package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Auxiliar;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.AuxiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuxiliarService extends BaseCrudService<Auxiliar, Long> {

    @Autowired
    private AuxiliarRepository repository;

    @Override
    protected JpaRepository<Auxiliar, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el auxiliar";
    }

    @Override
    protected String getTableName() {
        return "auxiliares";
    }

    @Override
    protected String getSequenceName() {
        return "auxiliares_id_seq";
    }

    @Override
    protected Long getEntityId(Auxiliar entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Auxiliar entity, Long id) {
        entity.setId(id);
    }

    public List<Auxiliar> findByCodcontId(Long codcontId) {
        return repository.findByCodcontIdOrderByCodigoAsc(codcontId);
    }

    @Override
    @Transactional
    public Auxiliar create(Auxiliar entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El auxiliar con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }

        if (entity.getCodcont() != null && entity.getCodcont().getId() != null && entity.getCodigo() != null) {
            if (repository.existsByCodcontIdAndCodigo(entity.getCodcont().getId(), entity.getCodigo())) {
                throw new DuplicateResourceException("El código de auxiliar '" + entity.getCodigo() + "' ya existe para este grupo contable.");
            }
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
