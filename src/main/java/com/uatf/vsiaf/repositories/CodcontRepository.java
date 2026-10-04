package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Codcont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodcontRepository extends JpaRepository<Codcont, Long> {}