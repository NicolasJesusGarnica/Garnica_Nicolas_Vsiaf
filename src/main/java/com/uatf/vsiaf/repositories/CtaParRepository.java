package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.CtaPar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtaParRepository extends JpaRepository<CtaPar, Long> {}