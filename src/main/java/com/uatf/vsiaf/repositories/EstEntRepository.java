package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.EstEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstEntRepository extends JpaRepository<EstEnt, Long> {}