-- =============================================================================
-- SCRIPT OFICIAL DE MIGRACION DE BASE DE DATOS POSTGRESQL (D.S. 24051 - VSIAF)
-- UNIVERSIDAD AUTONOMA TOMAS FRIAS (UATF) - CARRERA DE INGENIERIA DE SISTEMAS
-- TABLAS MIGRADAS: CODCONT (codigos_contables) Y AUXILIAR (auxiliares)
-- =============================================================================

-- 0. ENTIDAD Y UNIDAD ADMINISTRATIVA OFICIAL DE LA BASE VSIAF (entidades.dbf / unidadadmin.DBF)
CREATE TABLE IF NOT EXISTS entidades (
    id BIGINT PRIMARY KEY,
    codigo_entidad VARCHAR(10) UNIQUE,
    descripcion VARCHAR(200),
    sigla VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS unidad_admin (
    id BIGINT PRIMARY KEY,
    codigo_unidad VARCHAR(20) UNIQUE,
    nombre VARCHAR(150),
    ciudad VARCHAR(100),
    entidad_id BIGINT REFERENCES entidades(id),
    cla_depts_id BIGINT
);

INSERT INTO entidades (id, codigo_entidad, descripcion, sigla) VALUES
    (1, '143', 'Universidad Autónoma Tomás Frías', 'UATF')
ON CONFLICT (id) DO NOTHING;

INSERT INTO unidad_admin (id, codigo_unidad, nombre, ciudad, entidad_id) VALUES
    (1, 'UATF', 'UNIVERSIDAD AUTONOMA TOMAS FRIAS', 'POTOSI', 1)
ON CONFLICT (id) DO NOTHING;

-- 1. TABLA MAESTRA: GRUPOS CONTABLES (CODCONT.DBF)
CREATE TABLE IF NOT EXISTS codigos_contables (
    id BIGINT PRIMARY KEY,
    cuenta_contable VARCHAR(20) UNIQUE NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    vida_util_anios INTEGER NOT NULL DEFAULT 0,
    coeficiente_depreciacion DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    deprecia BOOLEAN NOT NULL DEFAULT TRUE,
    actualiza BOOLEAN NOT NULL DEFAULT TRUE,
    observaciones VARCHAR(500)
);

CREATE SEQUENCE IF NOT EXISTS codigos_contables_id_seq START WITH 50 INCREMENT BY 1;

-- 2. TABLA DETALLE: AUXILIARES (auxiliar.DBF)
CREATE TABLE IF NOT EXISTS auxiliares (
    id BIGSERIAL PRIMARY KEY,
    entidad VARCHAR(4) NOT NULL DEFAULT '143',
    unidad VARCHAR(5) NOT NULL DEFAULT 'UATF',
    codigo_auxiliar VARCHAR(20) NOT NULL,
    nombre VARCHAR(200) NOT NULL,
    observaciones VARCHAR(500),
    feult DATE DEFAULT CURRENT_DATE,
    usuar VARCHAR(20) DEFAULT 'admin',
    codcont_id BIGINT REFERENCES codigos_contables(id) ON DELETE CASCADE,
    objgasto_id BIGINT,
    CONSTRAINT uq_codcont_auxiliar UNIQUE (entidad, unidad, codcont_id, codigo_auxiliar)
);

-- Asegurar columnas y restricción compuesta si la tabla ya había sido creada por Hibernate
ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS entidad VARCHAR(4) NOT NULL DEFAULT '143';
ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS unidad VARCHAR(5) NOT NULL DEFAULT 'UATF';
ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS observaciones VARCHAR(500);
ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS feult DATE DEFAULT CURRENT_DATE;
ALTER TABLE auxiliares ADD COLUMN IF NOT EXISTS usuar VARCHAR(20) DEFAULT 'admin';
ALTER TABLE auxiliares DROP CONSTRAINT IF EXISTS ukqnf4on2en762vlcgjy3go7sos;
ALTER TABLE auxiliares DROP CONSTRAINT IF EXISTS uq_codcont_auxiliar;
ALTER TABLE auxiliares ADD CONSTRAINT uq_codcont_auxiliar UNIQUE (entidad, unidad, codcont_id, codigo_auxiliar);

CREATE SEQUENCE IF NOT EXISTS auxiliares_id_seq START WITH 10 INCREMENT BY 1;

-- 3. POBLACION DEL CATALOGO NACIONAL OFICIAL D.S. 24051 (39 RUBROS - CODCONT.DBF)
INSERT INTO codigos_contables (id, cuenta_contable, descripcion, vida_util_anios, coeficiente_depreciacion, deprecia, actualiza, observaciones) VALUES
    (1, '12301', 'EDIFICACIONES', 40, 2.5, TRUE, TRUE, NULL),
    (2, '12302', 'MUEBLES Y ENSERES DE OFICINA', 10, 10.0, TRUE, TRUE, NULL),
    (3, '12303', 'MAQUINARIA EN GENERAL', 8, 12.5, TRUE, TRUE, NULL),
    (4, '12304', 'EQUIPO MEDICO Y DE LABORATORIO', 8, 12.5, TRUE, TRUE, NULL),
    (5, '12305', 'EQUIPO DE COMUNICACIONES', 8, 12.5, TRUE, TRUE, NULL),
    (6, '12306', 'EQUIPO EDUCACIONAL Y RECREATIVO', 8, 12.5, TRUE, TRUE, NULL),
    (7, '12307', 'BARCOS Y LANCHAS EN GENERAL', 10, 10.0, TRUE, TRUE, NULL),
    (8, '12308', 'VEHICULOS AUTOMOTORES', 5, 20.0, TRUE, TRUE, NULL),
    (9, '12309', 'AVIONES', 5, 20.0, TRUE, TRUE, NULL),
    (10, '12310', 'MAQUINARIA PARA LA CONSTRUCCION', 5, 20.0, TRUE, TRUE, NULL),
    (11, '12311', 'MAQUINARIA AGRICOLA', 4, 25.0, TRUE, TRUE, NULL),
    (12, '12312', 'ANIMALES DE TRABAJO', 4, 25.0, TRUE, TRUE, NULL),
    (13, '12313', 'HERRAMIENTAS EN GENERAL', 4, 25.0, TRUE, TRUE, NULL),
    (14, '12314', 'REPRODUCTORES Y HEMBRAS DE PEDIGREE', 8, 12.5, TRUE, TRUE, NULL),
    (15, '12315', 'EQUIPOS DE COMPUTACION', 4, 25.0, TRUE, TRUE, NULL),
    (16, '12316', 'CANALES DE REGADIO Y POZOS', 20, 5.0, TRUE, TRUE, NULL),
    (17, '12317', 'ESTANQUES, BAÑADEROS Y ABREVADEROS', 10, 10.0, TRUE, TRUE, NULL),
    (18, '12318', 'ALAMBRADOS, TRANQUERAS Y VALLAS', 10, 10.0, TRUE, TRUE, NULL),
    (19, '12319', 'VIVIENDAS PARA EL PERSONAL', 20, 5.0, TRUE, TRUE, NULL),
    (20, '12320', 'MUEBLES Y ENSERES EN VIVIENDAS DE PERSONAL', 10, 10.0, TRUE, TRUE, NULL),
    (21, '12321', 'SILOS, ALMACENES Y GALPONES', 20, 5.0, TRUE, TRUE, NULL),
    (22, '12322', 'TINGLADOS Y COBERTIZOS DE MADERA', 5, 20.0, TRUE, TRUE, NULL),
    (23, '12323', 'TINGLADOS Y COBERTIZOS DE METAL', 10, 10.0, TRUE, TRUE, NULL),
    (24, '12324', 'INSTALACION DE ELECTRIFICACION Y TELEFONIA RURAL', 10, 10.0, TRUE, TRUE, NULL),
    (25, '12325', 'CAMINOS INTERIORES', 10, 10.0, TRUE, TRUE, NULL),
    (26, '12326', 'CAÑA DE AZUCAR', 5, 20.0, TRUE, TRUE, NULL),
    (27, '12327', 'VIDES', 8, 12.5, TRUE, TRUE, NULL),
    (28, '12328', 'FRUTALES', 10, 10.0, TRUE, TRUE, NULL),
    (29, '12329', 'LINEAS DE RECOLECCION DE LA INDUSTRIA PETROLERA', 5, 20.0, TRUE, TRUE, NULL),
    (30, '12330', 'POZOS PETROLEROS', 5, 20.0, TRUE, TRUE, NULL),
    (31, '12331', 'EQUIPOS DE CAMPO DE LA INDUSTRIA PETROLERA', 8, 12.5, TRUE, TRUE, NULL),
    (32, '12332', 'PLANTA DE PROCESAMIENTO DE LA INDUSTRIA PETROLERA', 8, 12.5, TRUE, TRUE, NULL),
    (33, '12333', 'DUCTOS DE LA INDUSTRIA PETROLERA', 10, 10.0, TRUE, TRUE, NULL),
    (34, '12334', 'TERRENOS', 0, 0.0, FALSE, TRUE, NULL),
    (36, '12336', 'OTROS ACTIVOS FIJOS', 0, 0.0, FALSE, FALSE, NULL),
    (37, '12337', 'ACTIVOS INTANGIBLES', 5, 20.0, TRUE, TRUE, 'Vida util cambiado de 4 anos a 5 anos en fecha 19/12/2000, por instrucciones de Ximena Rodriguez y Lic. Mario Costas. Existe una nota de respaldo del Colegio de Auditores y/o Contadores.'),
    (38, '12338', 'EQUIPO E INSTALACIONES', 8, 12.5, TRUE, TRUE, NULL),
    (39, '12339', 'OTRAS PLANTACIONES', 8, 12.5, TRUE, TRUE, NULL),
    (40, '12340', 'ACTIVOS MUSEOLOGICOS Y CULTURALES', 0, 0.0, FALSE, FALSE, NULL)
ON CONFLICT (id) DO UPDATE SET
    cuenta_contable = EXCLUDED.cuenta_contable,
    descripcion = EXCLUDED.descripcion,
    vida_util_anios = EXCLUDED.vida_util_anios,
    coeficiente_depreciacion = EXCLUDED.coeficiente_depreciacion,
    deprecia = EXCLUDED.deprecia,
    actualiza = EXCLUDED.actualiza,
    observaciones = EXCLUDED.observaciones;

-- 4. POBLACION DE AUXILIARES MIGRADOS DESDE VSIAF ORIGINAL (auxiliar.DBF)
INSERT INTO auxiliares (id, entidad, unidad, codigo_auxiliar, nombre, observaciones, feult, usuar, codcont_id) VALUES
    (1, '143', 'UATF', '1', 'CASA DE 50 M', NULL, '2026-11-21', 'admin', 1),
    (2, '143', 'UATF', '2', 'OFICINA 40 M', NULL, '2026-11-21', 'admin', 1),
    (3, '143', 'UATF', '1', 'TELEFONO CELULAR J7', NULL, '2026-11-21', 'admin', 5),
    (4, '143', 'UATF', '1', 'TERRENO DE 70 M2', NULL, '2026-11-21', 'admin', 34)
ON CONFLICT (id) DO UPDATE SET
    entidad = EXCLUDED.entidad,
    unidad = EXCLUDED.unidad,
    observaciones = EXCLUDED.observaciones,
    feult = EXCLUDED.feult,
    usuar = EXCLUDED.usuar;

-- 5. ACTIVOS FIJOS REALES VINCULADOS A AUXILIARES (ACTUAL.DBF) PARA VALIDACION DE DEPENDENCIAS
CREATE TABLE IF NOT EXISTS activos_actuales (
    id BIGSERIAL PRIMARY KEY,
    codigo_activo VARCHAR(50) UNIQUE,
    codigo_adi VARCHAR(50),
    descripcion VARCHAR(255),
    fecha_incorporacion DATE,
    costo_inicial DOUBLE PRECISION,
    valor_neto DOUBLE PRECISION,
    estado_bien VARCHAR(50),
    codcont_id BIGINT REFERENCES codigos_contables(id),
    auxiliar_id BIGINT REFERENCES auxiliares(id)
);

INSERT INTO activos_actuales (id, codigo_activo, codigo_adi, descripcion, fecha_incorporacion, costo_inicial, valor_neto, estado_bien, codcont_id, auxiliar_id) VALUES
    (1, '001', 'PT-01', 'EDIFICIO DE 30 M2', '2007-12-11', 11000.00, 11000.00, 'BUENO', 1, 1),
    (2, 'PT-002', 'PTS', 'NINGUNA', '2025-05-15', 1111.00, 1111.00, 'BUENO', 5, 3)
ON CONFLICT (id) DO UPDATE SET
    codcont_id = EXCLUDED.codcont_id,
    auxiliar_id = EXCLUDED.auxiliar_id;

-- Sincronizar secuencias para evitar conflictos de ID al insertar desde el backend
SELECT setval('codigos_contables_id_seq', COALESCE((SELECT MAX(id) FROM codigos_contables), 0) + 1, false);
SELECT setval('auxiliares_id_seq', COALESCE((SELECT MAX(id) FROM auxiliares), 0) + 1, false);
SELECT setval(pg_get_serial_sequence('activos_actuales', 'id'), COALESCE((SELECT MAX(id) FROM activos_actuales), 0) + 1, false);
