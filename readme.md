# interceptor-data-population

Este proyecto demuestra el uso de interceptores para poblar datos de manera automática en los servicios, separando las responsabilidades de enriquecimiento de datos del controlador. Está diseñado como un ejemplo didáctico para entender cómo inyectar información contextual en una solicitud sin pasarla manualmente desde el controlador, **manteniendo tu código limpio y enfocado en la lógica de negocio**.

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

- **Separación de responsabilidades clara**:
  - El interceptor se encarga de la lógica transversal: poblar datos.
  - El controlador no sabe nada del proceso de "enriquecer" al usuario.
  - El servicio trabaja con objetos ya enriquecidos: bajo acoplamiento.

- **Demostración de cómo se inyecta información contextual en la request sin necesidad de pasársela a mano desde el controlador.** Imagina inyectar automáticamente información del usuario autenticado o detalles de configuración basados en la solicitud.

- **Los DTOs reflejan lo que se necesita mostrar, no todo el modelo.** Es perfecto para explicar:
  - Por qué no devolvemos entidades completas directamente a la API.
  - Cómo crear distintas vistas del mismo modelo (basic, full, etc.) para diferentes necesidades.

---

## 🚀 Cómo clonar y levantar el proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd interceptor-data-population
    ```

2.  **Requisitos previos:** Asegúrate de tener instalado lo siguiente:
  * [Especificar aquí los requisitos, por ejemplo: Java JDK (versión), Maven o Gradle, Docker (si aplica), etc.]

3.  **Construir el proyecto:**
  * Si usas Maven:
      ```bash
      mvn clean install
      ```
  * Si usas Gradle:
      ```bash
      ./gradlew build
      ```

4.  **Ejecutar la aplicación:** La forma de ejecutar la aplicación dependerá del framework utilizado (Spring, Quarkus, etc.). Aquí te dejo ejemplos comunes:
  * **Spring Boot (con Maven):**
      ```bash
      mvn spring-boot:run
      ```
  * **Spring Boot (con Gradle):**
      ```bash
      ./gradlew bootRun
      ```
  * **Quarkus (en modo desarrollo):**
      ```bash
      ./mvnw quarkus:dev
      ```
    o
      ```bash
      ./gradlew quarkusDev
      ```
  * **Quarkus (como JAR ejecutable):** Después de construir, ejecuta el JAR que se encuentra en el directorio `target`:
      ```bash
      java -jar target/interceptor-data-population-*.jar
      ```

    [**Nota:** Adapta estos comandos según la configuración específica de tu proyecto.]

5.  **Acceder a la aplicación:** Una vez que la aplicación se esté ejecutando, podrás acceder a los endpoints de prueba. Por ejemplo: `http://localhost:8080/users/populate/basic/123`.

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

  ```java
  public class AuthenticationInterceptor implements HandlerInterceptor {
      @Autowired
      private UserService userService;

      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          String token = request.getHeader("Authorization");
          if (token != null && token.startsWith("Bearer ")) {
              String userId = extractUserIdFromToken(token.substring(7));
              User authenticatedUser = userService.findById(userId);
              request.setAttribute("authenticatedUser", authenticatedUser);
          }
          return true;
      }
  }

  // En el servicio:
  @Service
  public class MyService {
      public void processRequest(HttpServletRequest request) {
          User user = (User) request.getAttribute("authenticatedUser");
          // Lógica de negocio utilizando el usuario autenticado
      }
  }