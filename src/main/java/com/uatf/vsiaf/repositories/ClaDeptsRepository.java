package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.ClaDepts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaDeptsRepository extends JpaRepository<ClaDepts, Long> {}