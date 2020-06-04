/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  USUARIO
 * Created: 04-jun-2020
 */

INSERT INTO APP.USUARIO (ID, DTYPE, DIRECCION, APELLIDOS, CORREO, HASHPASSWORD, NOMBRE, TELEFONO) VALUES (1, 'ALUMNO', 'C/Alguna', 'de la Torre', 'user@user.com', 'user', 'Manolo', '666666666')
INSERT INTO APP.USUARIO (ID, DTYPE, DIRECCION, APELLIDOS, CORREO, HASHPASSWORD, NOMBRE, TELEFONO) VALUES (51, 'Docencia', 'C/Puede', 'de la Torre', 'doc@doc.com', 'doc', 'Pepin', '666666666')
INSERT INTO APP.USUARIO (ID, DTYPE, DIRECCION, APELLIDOS, CORREO, HASHPASSWORD, NOMBRE, TELEFONO) VALUES (52, 'ONG', 'C/Ninguna', 'de la Torre', 'ong@ong.com', 'ong', 'Federico', '666666666')

INSERT INTO APP.ONG (ID, DESCRIPCION, NOMBREONG) VALUES (52, 'Descripción', 'Caritas')
INSERT INTO APP.DOCENCIA (ID, AREA_DE_ESTUDIO, DEPARTAMENTO) VALUES (51, 'Salud', 'Biología')
INSERT INTO APP.ALUMNO (ID, AREA_DE_INTERES, CARRERA, DISPONIBILIDAD) VALUES (1, 'Páginas web', 'Informática', 'Fin de')

INSERT INTO APP.PROYECTO (ID, DESCRIPCION, LOCALIDAD, NOMBRE, ONG_ID) VALUES (3, 'Cuidado de animales callejeros', 'Barcelona', 'Protectora de animales', 52)
INSERT INTO APP.PROYECTO (ID, DESCRIPCION, LOCALIDAD, NOMBRE, ONG_ID) VALUES (251, 'Llevar comida y otros suministros', 'Málaga', 'Ayuda a necesitados', 52)

INSERT INTO APP.ACTIVIDAD (ID, CONOCIMIENTOS_NECESARIOS, DESCRIPCION, FECHA_FINALIZACION, FECHA_INICIO, NOMBRE, PROYECTO_ID) VALUES (5, 'Informatica', 'Programador de páginas web ', '2020-05-26', '2020-05-26', 'Creacion paginas web', 3)
INSERT INTO APP.ACTIVIDAD (ID, CONOCIMIENTOS_NECESARIOS, DESCRIPCION, FECHA_FINALIZACION, FECHA_INICIO, NOMBRE, PROYECTO_ID) VALUES (6, 'Veterinaria', 'Voluntarios veterinarios para cuidados de animales', '2020-05-26', '2020-05-26', 'Rehabilitacion de animales', 3)
INSERT INTO APP.ACTIVIDAD (ID, CONOCIMIENTOS_NECESARIOS, DESCRIPCION, FECHA_FINALIZACION, FECHA_INICIO, NOMBRE, PROYECTO_ID) VALUES (252, 'Cocina', 'Haremos bocatas y llevaremos comida a personas sin hogar', '2020-12-14', '2020-12-01', 'Bocatas solidarios', 251)
INSERT INTO APP.ACTIVIDAD (ID, CONOCIMIENTOS_NECESARIOS, DESCRIPCION, FECHA_FINALIZACION, FECHA_INICIO, NOMBRE, PROYECTO_ID) VALUES (351, 'Psicología', 'Charlar y hacer compañía a personas sin hogar', '2020-12-01', '2020-12-01', 'Compañía', 251)
