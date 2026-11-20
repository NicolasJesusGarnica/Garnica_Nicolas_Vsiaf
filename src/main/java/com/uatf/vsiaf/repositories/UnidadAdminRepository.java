package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.UnidadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadAdminRepository extends JpaRepository<UnidadAdmin, Long> {
    boolean existsByCodigoUnidad(String codigoUnidad);
    Optional<UnidadAdmin> findByCodigoUnidad(String codigoUnidad);
}
