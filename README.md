# aduana-reportes-api

## Descripción

Microservicio de gestión de reportes perteneciente al sistema de Aduana. Actúa como orquestador, consumiendo los endpoints del microservicio base (`aduana-api`) mediante Feign Client. Forma parte de una arquitectura de microservicios desarrollada con Spring Boot.

## Integrantes

| Nombre | Rol |
|--------|-----|
| Joaquín Leiva | Líder Técnico / Arquitecto |
| Octavio Echeverría | Desarrollador Backend Senior |
| Thiara Rojas | Desarrolladora Frontend / UX |
| Luna Bustamante | Desarrolladora Junior / QA |

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Cloud OpenFeign
- Spring Boot Validation
- Lombok
- Maven

## Funcionalidades implementadas

- Listar todos los reportes
- Buscar reporte por ID
- Obtener reportes por usuario
- Generar reporte consolidado de pasajeros
- Registrar nuevo reporte
- Eliminar reporte

## Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/reportes` | Lista todos los reportes |
| GET | `/api/v1/reportes/{id}` | Obtiene un reporte por ID |
| GET | `/api/v1/reportes/usuario/{idUsuario}` | Obtiene reportes por usuario |
| GET | `/api/v1/reportes/consolidado/pasajeros` | Reporte consolidado de pasajeros |
| POST | `/api/v1/reportes` | Registra un nuevo reporte |
| DELETE | `/api/v1/reportes/{id}` | Elimina un reporte |

## Estructura del proyecto

```
src/main/java/cl/duocuc/aduana_reportes_api/
├── client/
│   └── AduanaClient.java
├── controller/
│   └── ReporteController.java
├── dto/
│   ├── ApiResponse.java
│   ├── PasajeroResponse.java
│   ├── ReporteRequestDTO.java
│   ├── ReporteResponseDTO.java
│   ├── UsuarioResponse.java
│   └── VehiculoResponse.java
├── service/
│   └── ReporteService.java
└── AduanaReportesApiApplication.java
```

## Pasos para ejecutar

### Requisitos previos

- Java 17 instalado
- Maven instalado
- El microservicio `aduana-api` corriendo en `http://localhost:8080`

### Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/JoaquinLeivaDev/Sistema-Reportes-api.git
cd Sistema-Reportes-api
```

2. Compilar y ejecutar:
```bash
./mvnw spring-boot:run
```

3. El servicio quedará disponible en:
```
http://localhost:8081
```

### Ejemplo de petición

**Registrar reporte:**
```bash
POST http://localhost:8081/api/v1/reportes
Content-Type: application/json

{
  "tipo": "INSPECCION",
  "fecha": "2025-05-23",
  "datos": "Inspección rutinaria completada",
  "idUsuario": 1
}
```
