package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Reval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevalRepository extends JpaRepository<Reval, Long> {}