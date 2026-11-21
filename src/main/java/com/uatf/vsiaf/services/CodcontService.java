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

    private Integer obtenerVidaUtilPorDefecto(String descripcion) {
        if (descripcion == null) return null;
        String desc = descripcion.toUpperCase(Locale.ROOT).trim();

        if (desc.contains("EDIF")) return 40;
        if (desc.contains("MUEBLE") || desc.contains("ENSER")) return 10;
        if (desc.contains("COMPUT") || desc.contains("SISTEMA")) return 4;
        if (desc.contains("VEHIC") || desc.contains("AUTO") || desc.contains("MOTO") || desc.contains("AVION")) return 5;
        if (desc.contains("AGRIC") || desc.contains("HERRAMIENT") || desc.contains("ANIMAL")) return 4;
        if (desc.contains("MAQUIN") || desc.contains("MEDIC") || desc.contains("LABORATOR") || desc.contains("COMUNIC") || desc.contains("EDUCAC") || desc.contains("INSTALACION")) return 8;
        if (desc.contains("BARCO") || desc.contains("LANCHA") || desc.contains("ESTANQUE") || desc.contains("ALAMBRADO") || desc.contains("CAMINO") || desc.contains("FRUTAL") || desc.contains("DUCTO") || desc.contains("ELECTRIFICACION")) return 10;
        if (desc.contains("CANAL") || desc.contains("POZO") || desc.contains("SILO") || desc.contains("ALMACEN") || desc.contains("GALPON") || desc.contains("VIVIENDA")) return 20;
        if (desc.contains("TERRENO") || desc.contains("MUSEO") || desc.contains("CULTURAL")) return 0;
        if (desc.contains("INTANGIB") || desc.contains("CONSTRUC")) return 5;

        return 10;
    }

    private void autocompletarYCalcular(Codcont entity) {
        // 1. Si no enviaron vidaUtilAnios o es 0, deducirla automáticamente según el rubro oficial (D.S. 24051)
        if ((entity.getVidaUtilAnios() == null || entity.getVidaUtilAnios() == 0) && entity.getDescripcion() != null) {
            entity.setVidaUtilAnios(obtenerVidaUtilPorDefecto(entity.getDescripcion()));
        }

        // 2. Si no enviaron cuentaContable y tenemos ID, generar código contable estándar
        if ((entity.getCuentaContable() == null || entity.getCuentaContable().isBlank()) && entity.getId() != null) {
            entity.setCuentaContable("123" + String.format("%02d", entity.getId()));
        }

        // 3. Auto-cálculo de coeficiente de depreciación anual (%) = 100 / vidaUtilAnios
        if (entity.getVidaUtilAnios() != null && entity.getVidaUtilAnios() > 0) {
            double coef = Math.round((100.0 / entity.getVidaUtilAnios()) * 100.0) / 100.0;
            entity.setCoeficienteDepreciacion(coef);
        } else if (entity.getVidaUtilAnios() != null && entity.getVidaUtilAnios() == 0) {
            entity.setCoeficienteDepreciacion(0.0);
        }

        // 4. Casillas de Deprecia y Actualiza como en VSIAF
        if (entity.getDeprecia() == null) {
            entity.setDeprecia(entity.getVidaUtilAnios() != null && entity.getVidaUtilAnios() > 0);
        }
        if (entity.getActualiza() == null) {
            entity.setActualiza(true);
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
