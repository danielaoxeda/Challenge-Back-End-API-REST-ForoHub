# Challenge Backend Alura - ForoHub API 📫

API REST desarrollada con **Spring Boot** para la gestión de tópicos en un foro académico.  
Permite registrar, consultar, actualizar y eliminar tópicos, además de gestionar la autenticación de usuarios mediante **JWT** para proteger los endpoints.

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- BCrypt (cifrado de contraseñas)
- Postman (prueba de API)


## Características principales

- Autenticación segura mediante **JWT**
- Contraseñas cifradas utilizando **BCrypt**
- CRUD completo de tópicos
- Eliminación lógica de tópicos
- Paginación y ordenamiento de resultados
- Manejo global de errores y excepciones
- Documentación interactiva con **Swagger**
- Arquitectura basada en **DTOs y entidades**

## Estructura del proyecto

El proyecto está organizado en las siguientes entidades principales:

- **Usuario**
- **Perfil**
- **Curso**
- **Topico**
- **Respuesta**

Relaciones principales:

- Un **usuario** puede crear múltiples tópicos
- Un **tópico** pertenece a un **curso**
- Un **tópico** puede tener múltiples **respuestas**
- Un **usuario** puede tener uno o más **perfiles**


## Seguridad

La API implementa **Spring Security con JWT**.

Flujo de autenticación:

1. El usuario inicia sesión en `/login`
2. Se validan las credenciales
3. El servidor genera un **token JWT**
4. El cliente debe enviar el token en las siguientes peticiones:
  Authorization: Bearer TOKEN

Esto protege todos los endpoints de la API.


### Endpoints principales

## Autenticación
**POST /login**

Permite iniciar sesión y obtener un token JWT.


### Tópicos

| Método | Endpoint | Descripción |
|------|------|------|
| POST | /topicos | Registrar un nuevo tópico |
| GET | /topicos | Listar tópicos con paginación |
| GET | /topicos/{id} | Detallar un tópico |
| PUT | /topicos | Actualizar un tópico |
| DELETE | /topicos/{id} | Eliminación lógica de un tópico |


## Estados de los tópicos

Los tópicos manejan tres estados:

- **ABIERTO** → el problema sigue activo
- **RESUELTO** → el problema fue solucionado
- **CERRADO** → el tópico fue eliminado (borrado lógico)


## Documentación de la API

La documentación interactiva se encuentra disponible en:
[Documentación Swagger](http://localhost:8080/swagger-ui.html)
<img width="1663" height="1016" alt="image" src="https://github.com/user-attachments/assets/781e3ded-77ee-46a4-9dfa-98ee1a4cc0da" />

Permite probar todos los endpoints directamente desde el navegador.


## Base de datos

El proyecto utiliza **MySQL** como sistema de base de datos relacional.

Tablas principales:

- usuarios
- perfiles
- usuarios_perfiles
- cursos
- topicos
- respuestas

---

## Autor

Proyecto desarrollado por [Daniela Ojeda](https://www.linkedin.com/in/danielaoxeda/) como práctica de desarrollo deL Challenger Foro Hub - Prscticando con APIs de Alura Latam.
