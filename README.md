# ThiDay Main Services - Java Spring Boot

This is the Java Spring Boot version of the ThiDay Main Services backend, converted from the original Go implementation.

## 🚀 Features

- **Spring Boot 3.2.0** - Modern Java framework
- **MongoDB** - NoSQL database for storing words
- **RESTful API** - Clean REST endpoints
- **Maven** - Dependency management
- **Lombok** - Reduces boilerplate code
- **Graceful Shutdown** - Proper application lifecycle management

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **MongoDB** running on `localhost:27017` (or configure via environment variables)

## 🛠️ Installation

1. Clone the repository:
```bash
cd main-services-java
```

2. Install dependencies:
```bash
mvn clean install
```

## 🏃 Running the Application

### Using Maven:
```bash
mvn spring-boot:run
```

### Using Makefile:
```bash
make run
```

### With custom port:
```bash
PORT=8080 make run
```

### Build and run JAR:
```bash
make build
java -jar target/main-services-1.0.0.jar
```

## 🌍 Environment Variables

Configure the application using environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | `8083` | Server port |
| `MONGO_URI` | `mongodb://localhost:27017` | MongoDB connection URI |
| `MONGO_DB` | `main-services` | MongoDB database name |

Example:
```bash
export PORT=8080
export MONGO_URI=mongodb://localhost:27017
export MONGO_DB=main-services
mvn spring-boot:run
```

## 📡 API Endpoints

### Create Word
```http
POST /post-word
Content-Type: application/json

{
  "text": "Hello World"
}

Response: 201 Created
{
  "id": "507f1f77bcf86cd799439011"
}
```

### Get Word by Date
```http
GET /get-word/{date}

Example: GET /get-word/2025-10-31

Response: 200 OK
{
  "id": "507f1f77bcf86cd799439011",
  "text": "Hello World",
  "ownerId": "test-user",
  "createdAt": "2025-10-31T12:00:00Z"
}
```

Date formats supported:
- `YYYY-MM-DD` (e.g., `2025-10-31`)
- ISO-8601 (e.g., `2025-10-31T12:00:00Z`)

## 📁 Project Structure

```
main-services-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/thiday/mainservices/
│   │   │       ├── MainServicesApplication.java   # Main entry point
│   │   │       ├── config/
│   │   │       │   ├── MongoConfig.java          # MongoDB configuration
│   │   │       │   └── WebConfig.java            # Web/CORS configuration
│   │   │       └── words/
│   │   │           ├── controller/
│   │   │           │   └── WordController.java   # REST endpoints
│   │   │           ├── dto/
│   │   │           │   ├── CreateWordRequest.java
│   │   │           │   └── CreateWordResponse.java
│   │   │           ├── model/
│   │   │           │   └── Word.java             # Word entity
│   │   │           ├── repository/
│   │   │           │   └── WordRepository.java   # Database layer
│   │   │           └── service/
│   │   │               └── WordService.java      # Business logic
│   │   └── resources/
│   │       └── application.properties            # Configuration
│   └── test/
│       └── java/
├── pom.xml                                       # Maven dependencies
├── Makefile                                      # Build commands
└── README.md
```

## 🧪 Testing

Run tests:
```bash
make test
```

Or:
```bash
mvn test
```

## 🔨 Build

Build the JAR file:
```bash
make build
```

The JAR will be created at `target/main-services-1.0.0.jar`

## 🐳 Docker (Optional)

To containerize the application, create a `Dockerfile`:

```dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/main-services-1.0.0.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t main-services-java .
docker run -p 8083:8083 -e MONGO_URI=mongodb://host.docker.internal:27017 main-services-java
```

## 🔧 Development

### Debug Mode
Run with remote debugging enabled:
```bash
make debug
```

This starts the application with debug port `5005` available for your IDE.

### Hot Reload
Spring Boot DevTools can be added for hot reload during development. Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

## 📝 Notes

- **Authentication**: Currently uses a test user ID (`test-user`). Implement proper authentication by:
  1. Adding Spring Security
  2. Implementing JWT or OAuth2
  3. Updating `getCurrentUserId()` in `WordController`
  
- **CORS**: Currently allows all origins. Update `WebConfig.java` for production.

- **Error Handling**: Consider adding global exception handler for better error responses.

## 🔄 Differences from Go Version

| Aspect | Go | Java Spring Boot |
|--------|-----|------------------|
| Framework | Chi Router | Spring Boot |
| DI | Manual | Spring IoC Container |
| Database | MongoDB Driver | Spring Data MongoDB |
| Config | Environment variables | application.properties + env vars |
| Build | go build | Maven |
| JSON | encoding/json | Jackson |
| Server | net/http | Embedded Tomcat |

## 📄 License

Same as the original Go project.

## 🤝 Contributing

Contributions are welcome! Please follow the existing code style and patterns.

## 📞 Support

For issues or questions, please refer to the project documentation or contact the development team.



# ThiDay-main-services
