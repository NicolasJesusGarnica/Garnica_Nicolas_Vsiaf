package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Actual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {
    boolean existsByCodigoActivo(String codigoActivo);
    Optional<Actual> findByCodigoActivo(String codigoActivo);
}
