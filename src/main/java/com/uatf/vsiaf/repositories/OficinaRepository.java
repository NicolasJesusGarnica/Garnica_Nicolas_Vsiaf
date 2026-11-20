package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    boolean existsByCodigoOficina(String codigoOficina);
    Optional<Oficina> findByCodigoOficina(String codigoOficina);
}
