package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Auxiliar;
import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.exceptions.ResourceNotFoundException;
import com.uatf.vsiaf.repositories.ActualRepository;
import com.uatf.vsiaf.repositories.AuxiliarRepository;
import com.uatf.vsiaf.repositories.CodcontRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuxiliarService extends BaseCrudService<Auxiliar, Long> {

    @Autowired
    private AuxiliarRepository repository;

    @Autowired
    private CodcontRepository codcontRepository;

    @Autowired
    private ActualRepository actualRepository;

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

    public boolean hasDependencies(Long id) {
        return actualRepository.existsByAuxiliarId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar porque el auxiliar con ID " + id + " no existe.");
        }
        if (actualRepository.existsByAuxiliarId(id)) {
            throw new DuplicateResourceException("No se puede Eliminar este Auxiliar por existir Dependencias");
        }
        Auxiliar entity = findById(id);
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    @Transactional
    public Auxiliar create(Auxiliar entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El auxiliar con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }

        if (entity.getCodcont() != null && entity.getCodcont().getId() != null) {
            Codcont codcont = codcontRepository.findById(entity.getCodcont().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("El grupo contable con ID " + entity.getCodcont().getId() + " no existe."));
            if (entity.getCodigo() != null && repository.existsByCodcontIdAndCodigo(codcont.getId(), entity.getCodigo())) {
                throw new DuplicateResourceException("El código de auxiliar '" + entity.getCodigo() + "' ya existe para este grupo contable.");
            }
            entity.setCodcont(codcont);
        }

        if (entity.getEntidad() == null || entity.getEntidad().isBlank()) {
            entity.setEntidad("143");
        }
        if (entity.getUnidad() == null || entity.getUnidad().isBlank()) {
            entity.setUnidad("UATF");
        }
        if (entity.getFeult() == null) {
            entity.setFeult(LocalDate.now());
        }
        if (entity.getUsuar() == null || entity.getUsuar().isBlank()) {
            entity.setUsuar("admin");
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

    @Override
    @Transactional
    public Auxiliar update(Long id, Auxiliar entity) {
        Auxiliar existente = findById(id);
        if (entity.getEntidad() != null && !entity.getEntidad().isBlank()) {
            existente.setEntidad(entity.getEntidad());
        }
        if (entity.getUnidad() != null && !entity.getUnidad().isBlank()) {
            existente.setUnidad(entity.getUnidad());
        }
        if (entity.getCodigo() != null) {
            existente.setCodigo(entity.getCodigo());
        }
        if (entity.getNombre() != null) {
            existente.setNombre(entity.getNombre());
        }
        if (entity.getObservaciones() != null) {
            existente.setObservaciones(entity.getObservaciones());
        }
        if (entity.getUsuar() != null && !entity.getUsuar().isBlank()) {
            existente.setUsuar(entity.getUsuar());
        }
        existente.setFeult(entity.getFeult() != null ? entity.getFeult() : LocalDate.now());
        if (entity.getCodcont() != null && entity.getCodcont().getId() != null) {
            Codcont codcont = codcontRepository.findById(entity.getCodcont().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("El grupo contable con ID " + entity.getCodcont().getId() + " no existe."));
            existente.setCodcont(codcont);
        }
        Auxiliar merged = entityManager.merge(existente);
        entityManager.flush();
        return merged;
    }
}
