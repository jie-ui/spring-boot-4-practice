# spring-boot-4-practice

Personal study notes and practice for **Spring Boot 4**.

## Project Overview

A simple REST API application demonstrating core Spring Boot 4 concepts, built with:

| Technology | Version |
|---|---|
| Spring Boot | 4.0.3 |
| Spring Framework | 7.x |
| Java | 17 |
| Database | H2 (in-memory) |
| Build Tool | Maven |

---

## Quick Start

```bash
# Build and run tests
mvn test

# Run the application
mvn spring-boot:run
```

The application starts at `http://localhost:8080`.

### Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/hello` | Greeting (supports `?name=` param) |
| GET | `/api/users` | List all users |
| GET | `/api/users/{id}` | Get a user by ID |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update a user |
| DELETE | `/api/users/{id}` | Delete a user |
| GET | `/actuator/health` | App health status |
| GET | `/h2-console` | H2 database browser (dev only) |

---

## Study Notes

### 1. Spring Boot 4 Key Changes

- **Spring Framework 7** — Spring Boot 4 is built on Spring Framework 7.
- **Jakarta EE 11** — All `javax.*` packages are `jakarta.*` (migrated in Spring Boot 3, continued in Boot 4).
- **Java 17 baseline** — Java 17 is the minimum supported version.
- **Hibernate 7** — Ships with Hibernate ORM 7, supporting Jakarta Persistence 3.2.
- **Jackson 3** — JSON serialisation library moved from group ID `com.fasterxml.jackson` to `tools.jackson`. Packages changed accordingly (e.g., `tools.jackson.databind.ObjectMapper`).

### 2. `@SpringBootApplication`

```java
@SpringBootApplication
public class PracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
```

`@SpringBootApplication` is a convenience annotation that combines:
- `@Configuration` — marks the class as a source of bean definitions
- `@EnableAutoConfiguration` — tells Spring Boot to start adding beans based on classpath
- `@ComponentScan` — scans the current package and sub-packages for components

### 3. REST Controllers

```java
@RestController           // = @Controller + @ResponseBody
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) { ... }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) { ... }
}
```

Key annotations:
- `@RestController` — all methods return JSON by default (via Jackson)
- `@PathVariable` — binds a URI template variable (e.g., `{id}`)
- `@RequestBody` — deserialises the HTTP request body to a Java object
- `@Valid` — triggers Bean Validation on the annotated parameter

### 4. Bean Validation (Jakarta Validation)

```java
@NotBlank(message = "Name is required")
@Size(min = 2, max = 100)
private String name;

@Email(message = "Email must be a valid email address")
private String email;
```

- Annotations live in `jakarta.validation.constraints.*`
- When `@Valid` is used on a `@RequestBody`, Spring automatically returns `400 Bad Request` on violations.

### 5. Spring Data JPA

```java
// Repository — Spring generates the implementation at runtime
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // derived query
    boolean existsByEmail(String email);
}
```

- `JpaRepository<T, ID>` provides `save`, `findById`, `findAll`, `delete`, etc.
- **Derived queries** — Spring parses the method name and generates SQL automatically.
- No `@Repository` implementation class is needed.

### 6. Service Layer & Transactions

```java
@Service
@Transactional(readOnly = true)   // default: read-only (optimisation)
public class UserService {

    @Transactional   // overrides class-level: write transaction
    public User create(User user) { ... }
}
```

- `@Service` — stereotype annotation; marks the class as a Spring-managed service.
- `@Transactional(readOnly = true)` at class level — tells Hibernate to skip dirty-checking for reads.
- Override with `@Transactional` (writable) on specific write methods.

### 7. Exception Handling with `@RestControllerAdvice`

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handleNotFound(NoSuchElementException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setDetail(ex.getMessage());
        return problem;
    }
}
```

- `@RestControllerAdvice` — a `@ControllerAdvice` that also applies `@ResponseBody`.
- `ProblemDetail` — implements RFC 9457 (Problem Details for HTTP APIs), available since Spring 6.

### 8. Auto-Configuration

Spring Boot auto-configures beans when certain conditions are met (e.g., class on classpath, property set). Key auto-configurations used here:

| Auto-Configuration | Trigger |
|---|---|
| `DataSourceAutoConfiguration` | `H2` on classpath + no `DataSource` bean |
| `HibernateJpaAutoConfiguration` | `spring-boot-starter-data-jpa` dependency |
| `DataSourceInitializationAutoConfiguration` | `data.sql` in `resources/` |
| `ActuatorAutoConfiguration` | `spring-boot-starter-actuator` dependency |

### 9. Testing

```java
@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUser_returnsCreated() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dave\",\"email\":\"dave@example.com\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber());
    }
}
```

- `@SpringBootTest` — loads the full application context.
- `MockMvcBuilders.webAppContextSetup()` — builds a `MockMvc` from the loaded application context.
- `MockMvc` — sends HTTP requests in-process; no real server needed.
- `jsonPath` — asserts JSON response fields using JSONPath expressions.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/practice/
│   │   ├── PracticeApplication.java          # Entry point
│   │   ├── controller/
│   │   │   ├── HelloController.java           # Simple REST demo
│   │   │   ├── UserController.java            # CRUD REST API
│   │   │   └── GlobalExceptionHandler.java    # @RestControllerAdvice
│   │   ├── model/
│   │   │   └── User.java                      # JPA entity
│   │   ├── repository/
│   │   │   └── UserRepository.java            # Spring Data JPA repository
│   │   └── service/
│   │       └── UserService.java               # Business logic + transactions
│   └── resources/
│       ├── application.properties             # Configuration
│       └── data.sql                           # Sample data (loaded on startup)
└── test/
    └── java/com/example/practice/
        ├── PracticeApplicationTests.java      # Context load test
        └── controller/
            ├── HelloControllerTest.java       # Unit tests for HelloController
            └── UserControllerTest.java        # Integration tests for UserController
```
