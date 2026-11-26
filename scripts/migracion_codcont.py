

import struct
import os

DBF_PATH = r"C:\vsiaf\dbfs\codcont.dbf"
FPT_PATH = r"C:\vsiaf\dbfs\codcont.fpt"
OUTPUT_SQL = os.path.join(os.path.dirname(__file__), "..", "src", "main", "resources", "codcont.sql")

def migrar():
    print(f"Iniciando migracion desde {DBF_PATH}...")
    records = []

    with open(DBF_PATH, 'rb') as f:
        header = f.read(32)
        num_records, header_len, record_len = struct.unpack('<IHH', header[4:12])
        f.seek(32)
        fields = []
        while True:
            b = f.read(32)
            if not b or b[0] == 0x0D: 
                break
            fields.append((b[:11].replace(b'\x00', b'').decode('latin1').strip(), chr(b[11]), b[16]))

        fpt = open(FPT_PATH, 'rb')
        fpt_block_size = struct.unpack('>H', fpt.read(8)[6:8])[0] or 64

        f.seek(header_len)
        for i in range(num_records):
            rec = f.read(record_len)
            if not rec or rec[0:1] == b'*': 
                continue
            offset = 1
            row = {}
            for fname, ftype, flen in fields:
                val = rec[offset:offset+flen].decode('latin1', errors='replace').strip()
                row[fname] = val
                offset += flen
            
            memo_txt = ''
            if row.get('OBSERV') and row['OBSERV'].isdigit():
                block_num = int(row['OBSERV'])
                if block_num > 0:
                    fpt.seek(block_num * fpt_block_size)
                    m_header = fpt.read(8)
                    m_type, m_len = struct.unpack('>II', m_header)
                    memo_txt = fpt.read(m_len).decode('latin1', errors='replace').strip()
            row['memo'] = memo_txt
            records.append(row)

    print(f"Total registros extraidos de FoxPro: {len(records)}")

    sql_lines = [
        "-- =============================================================================",
        "-- SCRIPT DE BASE DE DATOS POSTGRESQL - TABLA: CODCONT (codigos_contables)",
        "-- PROYECTO: VSIAF - Sistema de Informacion y Administracion Financiera",
        "-- UNIVERSIDAD AUTONOMA TOMAS FRIAS (UATF)",
        "-- NORMATIVA: Decreto Supremo 24051 - Reglamento del IUE (Bolivia)",
        "-- =============================================================================\n",
        "-- 1. CREACION DE LA TABLA (DDL)",
        "CREATE TABLE IF NOT EXISTS codigos_contables (",
        "    id BIGINT PRIMARY KEY,",
        "    cuenta_contable VARCHAR(20) UNIQUE NOT NULL,",
        "    descripcion VARCHAR(200) NOT NULL,",
        "    vida_util_anios INTEGER NOT NULL DEFAULT 0,",
        "    coeficiente_depreciacion DOUBLE PRECISION NOT NULL DEFAULT 0.0,",
        "    deprecia BOOLEAN NOT NULL DEFAULT TRUE,",
        "    actualiza BOOLEAN NOT NULL DEFAULT TRUE,",
        "    observaciones VARCHAR(500)",
        ");\n",
        "-- 2. CREACION DE SECUENCIA PARA AUTOINCREMENTO",
        "CREATE SEQUENCE IF NOT EXISTS codigos_contables_id_seq START WITH 50 INCREMENT BY 1;\n",
        "-- 3. INSERCION DE LOS 39 GRUPOS CONTABLES OFICIALES DE BOLIVIA (DML)",
        "INSERT INTO codigos_contables (id, cuenta_contable, descripcion, vida_util_anios, coeficiente_depreciacion, deprecia, actualiza, observaciones) VALUES"
    ]

    val_rows = []
    for r in records:
        cid = int(r['CODCONT'])
        cta = f'123{cid:02d}'
        nombre = r['NOMBRE'].replace('\xa5', 'Ñ').replace('¥', 'Ñ').replace("'", "''")
        vu = int(r['VIDAUTIL']) if r['VIDAUTIL'].isdigit() else 0
        coef = round(100.0 / vu, 2) if vu > 0 else 0.0
        dep = 'TRUE' if r['DEPRECIAR'].upper() == 'T' else 'FALSE'
        act = 'TRUE' if r['ACTUALIZAR'].upper() == 'T' else 'FALSE'
        memo = r['memo'].replace("'", "''").replace('\n', ' ').strip()
        obs_val = f"'{memo}'" if memo else 'NULL'
        val_rows.append(f"    ({cid}, '{cta}', '{nombre}', {vu}, {coef}, {dep}, {act}, {obs_val})")

    sql_lines.append(',\n'.join(val_rows) + ';')

    with open(OUTPUT_SQL, 'w', encoding='utf-8') as out:
        out.write('\n'.join(sql_lines) + '\n')

    print(f"Migracion completada exitosamente. Script guardado en: {OUTPUT_SQL}")

if __name__ == '__main__':
    migrar()
