package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Trasfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasfeRepository extends JpaRepository<Trasfe, Long> {}