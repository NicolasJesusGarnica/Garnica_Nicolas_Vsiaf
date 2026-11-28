package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Actual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {
    boolean existsByCodigoActivo(String codigoActivo);
    Optional<Actual> findByCodigoActivo(String codigoActivo);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Actual a WHERE a.auxiliar.id = :auxiliarId")
    boolean existsByAuxiliarId(@Param("auxiliarId") Long auxiliarId);
}
