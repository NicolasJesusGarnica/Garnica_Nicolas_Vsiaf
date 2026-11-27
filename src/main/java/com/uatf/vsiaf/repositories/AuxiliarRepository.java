package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
    @Query("SELECT a FROM Auxiliar a WHERE a.codcont.id = :codcontId ORDER BY a.codigo ASC")
    List<Auxiliar> findByCodcontIdOrderByCodigoAsc(@Param("codcontId") Long codcontId);

    @Query("SELECT a FROM Auxiliar a WHERE a.codcont.id = :codcontId")
    List<Auxiliar> findByCodcontId(@Param("codcontId") Long codcontId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Auxiliar a WHERE a.codcont.id = :codcontId AND a.codigo = :codigo")
    boolean existsByCodcontIdAndCodigo(@Param("codcontId") Long codcontId, @Param("codigo") String codigo);
}
