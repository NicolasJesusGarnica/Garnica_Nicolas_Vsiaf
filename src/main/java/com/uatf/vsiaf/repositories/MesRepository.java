package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {}