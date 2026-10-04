package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Resp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespRepository extends JpaRepository<Resp, Long> {}