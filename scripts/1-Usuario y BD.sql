-- Script para la creación del usuario relacionado a la BD
USE master;
GO

-- login
CREATE LOGIN sudo
WITH PASSWORD = '12345678';
GO

-- asignar permisos a usuario bd interno
CREATE DATABASE almacen_castores_mruiz;
GO
USE almacen_castores_mruiz;
GO
CREATE USER sudo 
FOR LOGIN sudo;
GO
ALTER ROLE db_owner 
ADD MEMBER sudo;
GO