package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.Backups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupsRepository extends JpaRepository<Backups, Long> {}