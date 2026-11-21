package com.uatf.vsiaf.repositories;

import com.uatf.vsiaf.entities.Codcont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodcontRepository extends JpaRepository<Codcont, Long> {
    boolean existsByCuentaContable(String cuentaContable);
    Optional<Codcont> findByCuentaContable(String cuentaContable);
}
