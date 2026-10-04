package com.uatf.vsiaf.repositories;
import com.uatf.vsiaf.entities.UnidadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdminRepository extends JpaRepository<UnidadAdmin, Long> {}