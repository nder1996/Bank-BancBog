-- Crear tabla tipo_documento
CREATE TABLE tipo_documento (
    codigo VARCHAR(1) NOT NULL PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL,
    estado VARCHAR(1) NOT NULL
);

-- Crear tabla cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(1) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50),
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    ciudad_residencia VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT uk_documento UNIQUE (tipo_documento, numero_documento),
    CONSTRAINT cliente_ibfk_1 FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(codigo)
);

-- Insertar datos en tipo_documento
INSERT INTO tipo_documento (codigo, descripcion, estado) VALUES
('C', 'Cédula de ciudadanía', 'A'),
('P', 'Pasaporte', 'A');

-- Insertar datos en cliente
INSERT INTO cliente (
    tipo_documento,
    numero_documento,
    primer_nombre,
    segundo_nombre,
    primer_apellido,
    segundo_apellido,
    telefono,
    direccion,
    ciudad_residencia,
    fecha_creacion,
    fecha_actualizacion
) VALUES (
    'C',
    '23445322',
    'Juan',
    'Carlos',
    'López',
    'Rodríguez',
    '3001234567',
    'Calle 123 # 45-67',
    'Bogotá',
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP()
);