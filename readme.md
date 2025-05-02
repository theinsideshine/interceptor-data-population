# interceptor-data-population

Este proyecto demuestra el uso de interceptores para poblar datos de manera automática en los servicios, separando las responsabilidades de enriquecimiento de datos del controlador. Está diseñado como un ejemplo didáctico para entender cómo inyectar información contextual en una solicitud sin pasarla manualmente desde el controlador.

## 🎯 Objetivos

- **Separación de responsabilidades**: El interceptor se encarga de la lógica transversal de poblar los datos, dejando el controlador limpio y enfocado en la gestión de rutas.
- **Bajo acoplamiento**: El servicio trabaja con objetos ya enriquecidos, manteniendo el código modular y flexible.
- **Inyección contextual de datos**: Los datos necesarios se inyectan automáticamente en la solicitud, sin necesidad de pasarlos explícitamente desde el controlador.
- **Uso de DTOs**: Los DTOs son utilizados para representar vistas específicas del modelo (por ejemplo, básico, completo), en lugar de devolver las entidades directamente.

## 🧱 Estructura del Proyecto

- **Interceptors**: Se encarga de interceptar las solicitudes y poblar los datos necesarios llamando a los servicios correspondientes.
- **Controladores**: Manejan las solicitudes pero no se preocupan por poblar los datos, delegando esa tarea a los interceptores.
- **Servicios**: Reciben los datos ya enriquecidos y se centran solo en la lógica de negocio.
- **DTOs**: Representan las vistas necesarias del modelo, facilitando la creación de diferentes niveles de detalle (e.g., básico, completo).

## 💡 Conceptos clave

- **Interceptor**: Un componente que permite ejecutar lógica transversal (como poblar datos) antes de que la solicitud llegue al controlador.
- **DTO (Data Transfer Object)**: Un objeto que se utiliza para transmitir datos entre procesos, representando solo lo necesario para cada vista (por ejemplo, solo los campos básicos o completos).

---

## ✅ Lo que estás mostrando con tu diseño

- Separación de responsabilidades clara:
    - El interceptor se encarga de la lógica transversal: poblar datos.
    - El controlador no sabe nada del proceso de "enriquecer" al usuario.
    - El servicio trabaja con objetos ya enriquecidos: bajo acoplamiento.

- Demostración de cómo se **inyecta** información contextual en la request sin necesidad de pasársela a mano desde el controlador.

- Los DTOs reflejan lo que se necesita mostrar, no todo el modelo. Es perfecto para explicar:
    - Por qué no devolvemos entidades.
    - Cómo crear distintas vistas del mismo modelo (basic, full, etc.).

---

## 🧭 Interceptores y Filtros: diferencias entre Spring y Quarkus

En este proyecto usamos **interceptores** para poblar datos antes de que lleguen al servicio. Esta lógica transversal puede implementarse de diferentes formas según el framework. Tanto **Spring** como **Quarkus** (JAX-RS) ofrecen herramientas similares, pero con diferentes nombres y enfoques.

### 📌 Tabla comparativa

| Objetivo                          | Spring (Spring MVC)               | Quarkus / JAX-RS (RESTEasy)           |
|----------------------------------|-----------------------------------|----------------------------------------|
| Interceptar lógica del controlador | `HandlerInterceptor`              | `ContainerRequestFilter`              |
| Modificar request/response cruda | `javax.servlet.Filter`            | — (no trabaja sobre `HttpServlet`)     |
| Interceptar lectura/escritura de datos (JSON/XML) | —                             | `ReaderInterceptor` / `WriterInterceptor` |
| Orden de ejecución                | `@Order` o registro en `WebMvcConfigurer` | `@Priority`                         |

---

### 🔁 Ejemplo didáctico

Supongamos que queremos **inyectar automáticamente el usuario autenticado** para que el servicio lo reciba sin que el controlador tenga que pasarlo:

- En **Spring**, lo hacemos con un `HandlerInterceptor`, que toma el token de la request, busca al usuario, y lo guarda en un contexto accesible por los servicios (por ejemplo, con `ThreadLocal` o atributos en el request).
- En **Quarkus**, usamos un `ContainerRequestFilter` que intercepta la request antes de llegar al recurso JAX-RS, y hace lo mismo.

---

### Diagram de flujo de request

Usuario llama:  GET /users/populate/basic/{userId}
↓
UserBasicController
- Valida que userId sea >= 1 automáticamente (por @Min(1))
  ↓
  UserBasicService
- Lógica de negocio local (ej: chequear si el usuario ya está en cache)
  ↓
  UserInfoBasicInterceptor (mientras tanto...)
- Se activa por la anotación @PopulateBasicUserInfo
- Extrae userId de la URL
- Llama a: UserInfoClientService.getFullBasicInfo(userId)
  ↓
  UserInfoClientService
- Llama vía Feign a UserInfoClient.getFullUserInfo(userId)
  ↓
  UserInfoClient (FeignClient)
- Hace el request HTTP a servicio2: /user-info/populateFull/{userId}
  ↓
  Servicio 2 responde
  ↳ Si OK: devuelve datos de UserInfo
  ↳ Si error (404, 400, etc):
  - UserInfoClientService captura el error
  - Lanza excepción propia (ResourceNotFoundException, BadRequestException) con ayuda de ExceptionFactory
  ↓
  ExceptionHandlerGlobal
- Captura la excepción lanzada
- Devuelve respuesta de error customizada al cliente con ApiError


[Request] → [Controller] → [Interceptor] → [Service Local]
↘ [ClientService] → [FeignClient] → [Servicio2]
↘ (manejo de error automático si falla)


Qué beneficios tiene este flujo?
Validaciones tempranas de parámetros (@Min, @NotNull en el controller).

Interceptación de request antes de que llegue al método (con tu interceptor).

Clientes Feign encapsulados en un Service especializado (no se llama FeignClient directamente desde el interceptor).

Errores controlados y normalizados usando ExceptionFactory y ExceptionHandlerGlobal.




### 🎯 Conclusión

Aunque los nombres cambian entre frameworks, el objetivo es el mismo: **ejecutar lógica antes (o después) del controlador para desacoplar responsabilidades y enriquecer la request**.
