package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
    List<Auxiliar> findByCodcontIdOrderByCodigoAsc(Long codcontId);
    List<Auxiliar> findByCodcontId(Long codcontId);
    boolean existsByCodcontIdAndCodigo(Long codcontId, String codigo);
}
