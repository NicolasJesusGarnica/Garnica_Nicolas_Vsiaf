package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.CodcontRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class CodcontService extends BaseCrudService<Codcont, Long> {

    @Autowired
    private CodcontRepository repository;

    @Override
    protected JpaRepository<Codcont, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "el código contable";
    }

    @Override
    protected String getTableName() {
        return "codigos_contables";
    }

    @Override
    protected String getSequenceName() {
        return "codigos_contables_id_seq";
    }

    @Override
    protected Long getEntityId(Codcont entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Codcont entity, Long id) {
        entity.setId(id);
    }

    private void autocompletarYCalcular(Codcont entity) {
        // 1. Si no enviaron vidaUtilAnios o es 0, deducirla según el rubro oficial (D.S. 24051)
        if ((entity.getVidaUtilAnios() == null || entity.getVidaUtilAnios() == 0) && entity.getDescripcion() != null) {
            String desc = entity.getDescripcion().toUpperCase(Locale.ROOT);
            if (desc.contains("EDIF")) entity.setVidaUtilAnios(40);
            else if (desc.contains("MUEBLE") || desc.contains("ENSER")) entity.setVidaUtilAnios(10);
            else if (desc.contains("COMPUT") || desc.contains("SISTEMA")) entity.setVidaUtilAnios(4);
            else if (desc.contains("VEHIC") || desc.contains("AUTO") || desc.contains("MOTO")) entity.setVidaUtilAnios(5);
            else if (desc.contains("MAQUIN") && !desc.contains("AGRIC")) entity.setVidaUtilAnios(8);
            else if (desc.contains("AGRIC") || desc.contains("HERRAMIENT")) entity.setVidaUtilAnios(4);
            else if (desc.contains("TERRENO")) entity.setVidaUtilAnios(0);
            else if (desc.contains("CANAL") || desc.contains("POZO") || desc.contains("SILO") || desc.contains("VIVIENDA")) entity.setVidaUtilAnios(20);
        }

        // 2. Si no enviaron cuentaContable y tenemos ID, generar código contable estándar
        if ((entity.getCuentaContable() == null || entity.getCuentaContable().isBlank()) && entity.getId() != null) {
            entity.setCuentaContable("123" + String.format("%02d", entity.getId()));
        }

        // 3. Auto-cálculo de coeficiente de depreciación anual (%) = 100 / vidaUtilAnios
        if (entity.getCoeficienteDepreciacion() == null) {
            if (entity.getVidaUtilAnios() != null && entity.getVidaUtilAnios() > 0) {
                double coef = Math.round((100.0 / entity.getVidaUtilAnios()) * 100.0) / 100.0;
                entity.setCoeficienteDepreciacion(coef);
            } else if (entity.getVidaUtilAnios() != null && entity.getVidaUtilAnios() == 0) {
                entity.setCoeficienteDepreciacion(0.0);
            }
        }
    }

    @Override
    @Transactional
    public Codcont create(Codcont entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new DuplicateResourceException("El código contable con ID " + entity.getId() + " ya ha sido registrado. Por favor, registre con otro ID o no envíe el ID para asignación automática.");
        }

        if (entity.getId() == null) {
            Long nextId = getNextSequenceValue();
            entity.setId(nextId);
        } else {
            syncSequence();
        }

        autocompletarYCalcular(entity);

        // Validación de duplicados en cuentaContable
        if (entity.getCuentaContable() != null && repository.existsByCuentaContable(entity.getCuentaContable())) {
            throw new DuplicateResourceException("Ya existe un código contable con la cuenta '" + entity.getCuentaContable() + "'.");
        }

        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    @Transactional
    public Codcont update(Long id, Codcont entity) {
        autocompletarYCalcular(entity);
        return super.update(id, entity);
    }
}
