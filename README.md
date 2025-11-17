## General
Proyecto backend construido con **Java**, **Spring Boot** y **Maven**, que
expone servicios REST para ser consumidos por el frontend.

---

## Herramientas utilizadas:
* **DBMS:** SQL Server 2019 (15.0)
* **Lenguaje:** Java 17.0.11
* **IDE:** IntelliJ 2025.2.4
* **Framework:** Spring Boot 3.5.7
* **Gestión Java:** Maven 3.9.6

## Instalación y Ejecución:
1. Clonar el repositorio.
2. Ejecutar scripts de BD en orden en SQL Server.
   1. `1-Usuario y BD.sql`
   2. `2-BD DDL.sql`
   3. `3-BD DML.sql`
3. Instalar dependencias con `mvn clean install`.
4. Mantener el proyecto corriendo con `mvn spring-boot:run`, para 
que lo use el frontend.
5. El servidor corre por defecto en http://localhost:8080

---

## Notas: 
* Las variables de entorno (**application.properties**) ya están
incluidas en el repositorio, no es necesario configurarlas.
* Este backend está preparado para ser consumido directamente 
por el frontend del proyecto.
* Asegurarse de mantener el servidor corriendo mientras se
trabaja en el cliente.
