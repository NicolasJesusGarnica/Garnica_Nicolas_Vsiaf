package com.uatf.vsiaf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Component
public class MigrationRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MigrationRunner.class);
    private final JdbcTemplate jdbcTemplate;

    public MigrationRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            // Backfill seguro por si la BD en la nube ya tenía filas previas
            jdbcTemplate.execute(
                "ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS entidad VARCHAR(4) DEFAULT '143';" +
                "ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS unidad VARCHAR(5) DEFAULT 'UATF';" +
                "ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS observaciones VARCHAR(500);" +
                "ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS feult DATE DEFAULT CURRENT_DATE;" +
                "ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS usuar VARCHAR(20) DEFAULT 'admin';" +
                "UPDATE auxiliares SET entidad = COALESCE(entidad, '143'), unidad = COALESCE(unidad, 'UATF'), feult = COALESCE(feult, CURRENT_DATE), usuar = COALESCE(usuar, 'admin');" +
                "ALTER TABLE auxiliares DROP CONSTRAINT IF EXISTS ukqnf4on2en762vlcgjy3go7sos;"
            );

            Integer codcontCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM codigos_contables", Integer.class);
            Integer auxCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM auxiliares", Integer.class);
            Integer actCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM activos_actuales", Integer.class);

            if ((codcontCount != null && codcontCount < 39)
                    || (auxCount != null && auxCount == 0)
                    || (actCount != null && actCount == 0)) {
                ClassPathResource resource = new ClassPathResource("migration_vsiaf.sql");
                String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
                jdbcTemplate.execute(sql);
                log.info("Migración VSIAF aplicada correctamente desde migration_vsiaf.sql.");
            }
        } catch (Exception e) {
            log.warn("Aviso al verificar migración automática VSIAF: {}", e.getMessage());
        }
    }
}
