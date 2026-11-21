package com.uatf.vsiaf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uatf.vsiaf.exceptions.BusinessRuleException;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCrudService<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract String getEntityName();
    protected abstract String getTableName();
    protected abstract String getSequenceName();
    protected abstract ID getEntityId(T entity);
    protected abstract void setEntityId(T entity, ID id);

    public List<T> findAll() {
        try {
            return getRepository().findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "id"));
        } catch (Exception e) {
            return getRepository().findAll();
        }
    }

    public T findById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró " + getEntityName() + " con ID " + id));
    }

    @Transactional
    public T create(T entity) {
        ID id = getEntityId(entity);
        if (id != null && getRepository().existsById(id)) {
            throw new DuplicateResourceException(getEntityName() + " con ID " + id + " ya ha sido registrado. Por favor, registre con otro ID o no especifique el ID para que sea generado automáticamente.");
        }

        if (id == null) {
            Long nextId = getNextSequenceValue();
            setEntityId(entity, (ID) nextId);
        } else {
            syncSequence();
        }

        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public T save(T entity) {
        return create(entity);
    }

    @Transactional
    public T update(ID id, T entity) {
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException("No se puede actualizar porque " + getEntityName() + " con ID " + id + " no existe.");
        }
        setEntityId(entity, id);
        T merged = entityManager.merge(entity);
        entityManager.flush();
        return merged;
    }

    @Transactional
    public T patch(ID id, Map<String, Object> campos) {
        T existente = findById(id);
        try {
            Map<String, Object> copia = new HashMap<>(campos);
            copia.remove("id");
            objectMapper.readerForUpdating(existente).readValue(objectMapper.writeValueAsString(copia));
            T merged = entityManager.merge(existente);
            entityManager.flush();
            return merged;
        } catch (Exception e) {
            throw new BusinessRuleException("Error al aplicar actualización parcial en " + getEntityName() + ": " + e.getMessage());
        }
    }

    @Transactional
    public void deleteById(ID id) {
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar porque " + getEntityName() + " con ID " + id + " no existe.");
        }
        T entity = findById(id);
        entityManager.remove(entity);
        entityManager.flush();
    }

    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    protected Long getNextSequenceValue() {
        try {
            Number num = (Number) entityManager.createNativeQuery("SELECT nextval('" + getSequenceName() + "')").getSingleResult();
            return num.longValue();
        } catch (Exception e) {
            // Fallback si la secuencia no existe
            Number max = (Number) entityManager.createNativeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM " + getTableName()).getSingleResult();
            return max.longValue();
        }
    }

    protected void syncSequence() {
        try {
            entityManager.createNativeQuery(
                "SELECT setval('" + getSequenceName() + "', GREATEST((SELECT COALESCE(MAX(id), 0) FROM " + getTableName() + "), nextval('" + getSequenceName() + "')))"
            ).getSingleResult();
        } catch (Exception ignored) {
        }
    }
}
