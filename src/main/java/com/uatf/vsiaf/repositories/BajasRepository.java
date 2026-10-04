package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Bajas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BajasRepository extends JpaRepository<Bajas, Long> {}