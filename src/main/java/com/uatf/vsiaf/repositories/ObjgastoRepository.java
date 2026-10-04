package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Objgasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjgastoRepository extends JpaRepository<Objgasto, Long> {}