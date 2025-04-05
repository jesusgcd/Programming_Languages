-- ---------------------------------
-- Instrucciones Generales ---------
-- Corre los siguientes comandos: --
-- ---------------------------------
-- select * from paciente;

-- CREATE DATABASE BDHospitalMexico;
-- USE BDHospitalMexico;

-- select * from paciente;
-- select * from medico;
-- select * from cita;
-- select * from expediente;



-- -----------------------------
-- Creacion de Base de Datos ---
-- -----------------------------

-- Tabla Medico
CREATE TABLE Medico (
	id int AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR (12) ,
    nombre VARCHAR (50), 
    apellido VARCHAR (50),
    correo VARCHAR (50),
    contrasenna varchar(50),
    especialidad varchar(50)
);

-- Tabla Paciente
CREATE TABLE Paciente (
	id int AUTO_INCREMENT  PRIMARY KEY,
    cedula VARCHAR (12),
    nombre VARCHAR (50), 
    apellido VARCHAR (50),
    correo VARCHAR (50),
    medico_cabecera VARCHAR (50),
     contrasenna varchar(50)
);




-- Tabla  Cita
CREATE TABLE Cita (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hora VARCHAR (10),
    fecha DATE,
    cedula_medico VARCHAR (12),
    cedula_paciente VARCHAR (12),
    duracion VARCHAR (15)
);

-- Tabla Expediente
CREATE TABLE Expediente (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR (12),
    fecha DATE,
    padecimiento VARCHAR (50),
    procedimiento_realizado VARCHAR (50),
    medicamentos VARCHAR (50)
);

-- --------------------------------
-- Inserciones en Base de Datos ---
-- --------------------------------

-- Insertar 15 registros aleatorios en la tabla Medico

	-- Medicos Generales
INSERT INTO Medico (cedula, nombre, apellido, correo,contrasenna,especialidad)
VALUES
		-- Medicos Generales
    ('1234567890', 'Juan', 'Perez', 'juan.perez@example.com','123', 'General'),
    ('9876543210', 'Maria', 'Gomez', 'maria.gomez@example.com','123', 'General'),
    ('5678901234', 'Carlos', 'Lopez', 'carlos.lopez@example.com','123', 'General'),
    ('4567890123', 'Ana', 'Rodriguez', 'ana.rodriguez@example.com','123', 'General'),
    ('3456789012', 'Pedro', 'Martinez', 'pedro.martinez@example.com','123', 'General'),
    
		-- Medicos Especialistas
    ('2345678901', 'Laura', 'Hernandez', 'laura.hernandez@example.com','123', 'Pediatría'),
    ('6986543210', 'Diego', 'Sanchez', 'diego.sanchez@example.com','123', 'Cardiología'),
    ('8765432109', 'Luis', 'Torres', 'luis.torres@example.com','123', 'Neurología'),
    ('7654321098', 'Isabel', 'Diaz', 'isabel.diaz@example.com','123', 'Gastroenterología'),
    ('6543210987', 'Sofia', 'Ramirez', 'sofia.ramirez@example.com','123', 'Dermatología');
   

-- Insertar 10 registros aleatorios en la tabla Paciente con nombres y apellidos aleatorios
INSERT INTO Paciente (cedula, nombre, apellido, correo, medico_cabecera, contrasenna)
VALUES
    ('1111111111', 'John', 'Smith', 'john.smith@example.com', '1234567890','123'),
    ('2222222222', 'Mary', 'Johnson', 'mary.johnson@example.com', '1234567890','123'),
    ('3333333333', 'David', 'Brown', 'david.brown@example.com', '6986543210','123'),
    ('4444444444', 'Sarah', 'Miller', 'sarah.miller@example.com', '6986543210','123'),
    ('5555555555', 'Michael', 'Davis', 'michael.davis@example.com', '5678901234','123'),
    ('6666666666', 'Jennifer', 'Anderson', 'jennifer.anderson@example.com', '5678901234','123'),
    ('7777777777', 'Robert', 'Wilson', 'robert.wilson@example.com', '4567890123','123'),
    ('8888888888', 'Susan', 'Clark', 'susan.clark@example.com', '4567890123','123'),
    ('9999999999', 'William', 'White', 'william.white@example.com', '3456789012','123'),
    ('1010101010', 'Emily', 'Taylor', 'emily.taylor@example.com', '3456789012','123');


-- Insertar 10 registros en la tabla Cita con duración de 30 minutos
INSERT INTO Cita (hora, fecha, cedula_medico, cedula_paciente, duracion)
VALUES
		-- Citas con medicos generales
    ('08:00 AM', '2023-03-11', '6543210987', '1111111111', '30 minutos'),
    ('09:00 AM', '2023-05-06', '1234567890', '2222222222', '30 minutos'),
    ('10:00 AM', '2023-01-28', '5678901234', '5555555555', '30 minutos'),
    ('11:00 AM', '2023-02-18', '3456789012', '9999999999', '30 minutos'),
    ('02:00 PM', '2023-09-15', '5678901234', '6666666666', '30 minutos'),
    ('03:00 PM', '2023-12-14', '4567890123', '7777777777', '30 minutos'),
    ('04:00 PM', '2023-11-25', '4567890123', '8888888888', '30 minutos'),
    ('05:00 PM', '2023-10-03', '3456789012', '1010101010', '30 minutos'),
    ('10:00 AM', '2023-07-12', '1234567890', '1111111111', '30 minutos'),
    ('11:00 AM', '2023-05-19', '1234567890', '2222222222', '30 minutos');

-- Insertar 10 registros en la tabla Expediente
INSERT INTO Expediente (cedula, fecha, padecimiento, procedimiento_realizado, medicamentos)
VALUES
    ('1111111111', '2023-09-18', 'Dolor de cabeza', 'Examen de sangre', 'Aspirina'),
    ('2222222222', '2023-09-18', 'Fiebre', 'Radiografía', 'Paracetamol'),
    ('3333333333', '2023-09-19', 'Dolor de estómago', 'Endoscopia', 'Omeprazol'),
    ('4444444444', '2023-09-19', 'Fractura de brazo', 'Yeso', 'Ibuprofeno'),
    ('5555555555', '2023-09-20', 'Gripe', 'Descanso', 'Vitamina C'),
    ('6666666666', '2023-09-20', 'Alergia', 'Antihistamínico', 'Loratadina'),
    ('7777777777', '2023-09-21', 'Presión arterial alta', 'Control de dieta', 'Losartán'),
    ('8888888888', '2023-09-21', 'Dolor de espalda', 'Fisioterapia', 'Relaxantes musculares'),
    ('9999999999', '2023-09-22', 'Infección de garganta', 'Antibióticos', 'Amoxicilina'),
    ('1010101010', '2023-09-22', 'Dolor de oído', 'Examen auditivo', 'Gotas para el oído');
    
    