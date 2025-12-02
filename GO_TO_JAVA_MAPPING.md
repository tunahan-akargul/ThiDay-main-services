# Go to Java Conversion Mapping

This document shows how each file from the Go project maps to the Java Spring Boot project.

## 📁 File Structure Mapping

| Go File | Java File | Notes |
|---------|-----------|-------|
| `cmd/server/main.go` | `MainServicesApplication.java` | Main entry point |
| `internal/app/app.go` | `application.properties` + `MainServicesApplication.java` | Config & startup logic split between properties and Spring Boot auto-config |
| `internal/app/https/router.go` | `WebConfig.java` + `WordController.java` | Routing handled by Spring annotations |
| `internal/app/https/handlers/word.go` | `words/controller/WordController.java` | HTTP handlers → REST controllers |
| `internal/app/words/model.go` | `words/model/Word.java` + `words/dto/*` | Models split into entities and DTOs |
| `internal/app/words/repository.go` | `words/repository/WordRepository.java` | Repository pattern (Spring Data makes it simpler) |
| `internal/app/words/service.go` | `words/service/WordService.java` | Business logic layer |
| `internal/db/mongo.go` | `config/MongoConfig.java` | MongoDB connection config |
| `go.mod` | `pom.xml` | Dependency management |
| `Makefile` | `Makefile` | Build commands (adapted for Maven) |

## 🔄 Architecture Mapping

### Go Project Structure
```
main-services/
├── cmd/
│   └── server/
│       └── main.go                    # Entry point
├── internal/
│   ├── app/
│   │   ├── app.go                     # App config & startup
│   │   ├── https/
│   │   │   ├── router.go              # HTTP routing
│   │   │   └── handlers/
│   │   │       └── word.go            # HTTP handlers
│   │   └── words/
│   │       ├── model.go               # Domain models
│   │       ├── repository.go          # Data access
│   │       └── service.go             # Business logic
│   └── db/
│       └── mongo.go                   # DB connection
├── go.mod                             # Dependencies
└── Makefile                           # Build scripts
```

### Java Project Structure
```
main-services-java/
├── src/
│   ├── main/
│   │   ├── java/com/thiday/mainservices/
│   │   │   ├── MainServicesApplication.java     # Entry point
│   │   │   ├── config/
│   │   │   │   ├── MongoConfig.java            # DB config
│   │   │   │   └── WebConfig.java              # Web/CORS config
│   │   │   └── words/
│   │   │       ├── controller/
│   │   │       │   └── WordController.java     # REST endpoints
│   │   │       ├── dto/
│   │   │       │   ├── CreateWordRequest.java  # Request DTOs
│   │   │       │   └── CreateWordResponse.java # Response DTOs
│   │   │       ├── model/
│   │   │       │   └── Word.java               # Entity
│   │   │       ├── repository/
│   │   │       │   └── WordRepository.java     # Data access
│   │   │       └── service/
│   │   │           └── WordService.java        # Business logic
│   │   └── resources/
│   │       └── application.properties          # Configuration
│   └── test/
│       └── java/com/thiday/mainservices/
│           └── MainServicesApplicationTests.java
├── pom.xml                                     # Dependencies
└── Makefile                                    # Build scripts
```

## 🔧 Technical Mapping

### Dependency Injection
| Go | Java |
|----|------|
| Manual DI in `router.go` | Spring IoC Container with `@Autowired` / Constructor injection |
| `NewRepository(db)` | `@RequiredArgsConstructor` with Lombok |
| `NewService(repo)` | Spring manages lifecycle |

### HTTP Routing
| Go | Java |
|----|------|
| `router.Post("/post-word", handler.Create)` | `@PostMapping("/post-word")` |
| `router.Get("/get-word/{date}", handler.GetByDate)` | `@GetMapping("/get-word/{date}")` |
| Chi Router | Spring MVC |

### Database Access
| Go | Java |
|----|------|
| `mongo.Collection()` | `MongoRepository` interface |
| Manual BSON queries | Spring Data query methods |
| `collection.InsertOne()` | `repository.save()` |
| `collection.FindOne()` | `repository.findByOwnerIdAndCreatedAtBetween()` |

### Configuration
| Go | Java |
|----|------|
| `os.Getenv("PORT")` | `@Value("${PORT}")` or `application.properties` |
| Manual config struct | Spring Boot auto-configuration |
| `loadConfig()` function | Properties files + environment variables |

### JSON Handling
| Go | Java |
|----|------|
| `json.Decoder(r.Body).Decode(&in)` | `@RequestBody` + Jackson auto-conversion |
| `json.NewEncoder(w).Encode(v)` | Return object directly, Spring handles JSON |
| struct tags: `json:"text"` | `@JsonProperty("text")` |

### Error Handling
| Go | Java |
|----|------|
| `if err != nil { return err }` | Try-catch blocks |
| `http.Error(w, err.Error(), status)` | `ResponseEntity` with status codes |
| Explicit error returns | Exceptions |

### Data Models
| Go | Java |
|----|------|
| `bson:"text"` | `@Field("text")` |
| `json:"text"` | `@JsonProperty("text")` |
| Struct | Class with `@Data` (Lombok) |
| `time.Time` | `Instant` |

## 📊 Framework Comparison

| Feature | Go | Java Spring Boot |
|---------|-----|------------------|
| **Router** | Chi | Spring MVC |
| **ORM** | MongoDB Driver | Spring Data MongoDB |
| **DI** | Manual | Spring IoC |
| **Config** | Manual + env vars | application.properties + @Value |
| **Validation** | Manual | Bean Validation (`@Valid`, `@NotBlank`) |
| **Logging** | log package | SLF4J + Logback |
| **Testing** | testing package | JUnit 5 + Spring Test |
| **Build** | go build | Maven |
| **Server** | net/http | Embedded Tomcat |

## 🎯 Key Differences

### 1. **Boilerplate Code**
- **Go**: More explicit, manual setup
- **Java**: Less boilerplate with Lombok and Spring annotations

### 2. **Type System**
- **Go**: Interfaces, no generics (in this version)
- **Java**: Interfaces, generics, annotations

### 3. **Error Handling**
- **Go**: Explicit error returns
- **Java**: Exception-based

### 4. **Dependency Management**
- **Go**: go.mod, minimal dependencies
- **Java**: pom.xml, more dependencies but more features

### 5. **Repository Pattern**
- **Go**: Manual implementation with collection access
- **Java**: Spring Data auto-implements based on interface

### 6. **Configuration**
- **Go**: Code-based with environment variables
- **Java**: Properties files + environment variables + Spring profiles

## 🚀 Running Both Versions

### Go Version
```bash
cd main-services
make run
# Server on :8083
```

### Java Version
```bash
cd main-services-java
make run
# Server on :8083
```

Both expose the exact same API endpoints with the same behavior!

## 📝 Notes

- The Java version uses more industry-standard patterns (Spring Boot)
- The Go version is more lightweight and explicit
- Both achieve the same functionality
- API is 100% compatible between versions
- Database schema is identical



