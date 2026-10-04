package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {}