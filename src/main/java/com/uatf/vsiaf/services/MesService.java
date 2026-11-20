package com.uatf.vsiaf.services;

import com.uatf.vsiaf.entities.Mes;
import com.uatf.vsiaf.exceptions.BusinessRuleException;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.repositories.MesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MesService extends BaseCrudService<Mes, Long> {

    private static final String[] NOMBRES_MESES = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

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

    /**
     * Apertura el siguiente mes contable de forma autoincremental (1..12).
     * No requiere que el usuario defina qué mes es: el sistema asigna el siguiente correlativo y nombre.
     */
    @Transactional
    public Mes aperturarSiguienteMes(Mes input) {
        List<Mes> existentes = repository.findAll();
        int maxNumero = 0;
        for (Mes m : existentes) {
            if (m.getNumeroMes() != null && m.getNumeroMes() > maxNumero) {
                maxNumero = m.getNumeroMes();
            }
        }
        int siguienteNumero = maxNumero + 1;
        if (siguienteNumero > 12) {
            throw new BusinessRuleException("La gestión fiscal ya cuenta con los 12 períodos mensuales aperturados (Enero a Diciembre). No se pueden aperturar más meses en la gestión.");
        }

        Mes nuevo = (input != null) ? input : new Mes();
        nuevo.setNumeroMes(siguienteNumero);
        nuevo.setNombreMes(NOMBRES_MESES[siguienteNumero - 1]);
        if (nuevo.getCerrado() == null) {
            nuevo.setCerrado(false);
        }

        if (nuevo.getId() == null) {
            nuevo.setId(getNextSequenceValue());
        } else {
            if (repository.existsById(nuevo.getId())) {
                throw new DuplicateResourceException("El mes con ID " + nuevo.getId() + " ya ha sido registrado.");
            }
            syncSequence();
        }

        entityManager.persist(nuevo);
        entityManager.flush();
        return nuevo;
    }

    @Override
    @Transactional
    public Mes create(Mes entity) {
        return aperturarSiguienteMes(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        throw new BusinessRuleException("Regla de negocio VSIAF: Los períodos del calendario contable fiscal son fijos y no pueden ser eliminados.");
    }
}
