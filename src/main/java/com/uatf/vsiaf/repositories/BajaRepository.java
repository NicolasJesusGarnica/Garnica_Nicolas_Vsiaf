package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Baja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BajaRepository extends JpaRepository<Baja, Long> {}