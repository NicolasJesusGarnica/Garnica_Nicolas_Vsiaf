package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
    boolean existsByNumeroMes(Integer numeroMes);
    boolean existsByNombreMesIgnoreCase(String nombreMes);
    Optional<Mes> findByNumeroMes(Integer numeroMes);
}
