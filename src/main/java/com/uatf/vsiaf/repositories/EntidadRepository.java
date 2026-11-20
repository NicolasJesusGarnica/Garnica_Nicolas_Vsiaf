package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long> {
    boolean existsByCodigoEntidad(String codigoEntidad);
    Optional<Entidad> findByCodigoEntidad(String codigoEntidad);
}
