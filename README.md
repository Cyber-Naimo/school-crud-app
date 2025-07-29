# School Database CRUD Application

A comprehensive Spring Boot application for managing school data including students, teachers, subjects, classes, and enrollments. This application provides a RESTful API with OpenAPI documentation and is containerized with Docker.

## Features

- **School Management**: Create, read, update, and delete schools
- **Student Management**: Manage student information and enrollments
- **Teacher Management**: Handle teacher data and assignments
- **Subject Management**: Manage subjects and their details
- **Class Management**: Organize classes and their relationships
- **Enrollment System**: Track student enrollments in subjects and classes
- **RESTful API**: Complete CRUD operations for all entities
- **OpenAPI Documentation**: Interactive API documentation with Swagger UI
- **Docker Support**: Containerized application with docker-compose
- **H2 Database**: In-memory database for development and testing

## Technology Stack

- **Java 24**: Latest LTS version
- **Spring Boot 3.5.4**: Application framework
- **Spring Data JPA**: Data access layer
- **Spring HATEOAS**: Hypermedia support
- **Spring Validation**: Input validation
- **H2 Database**: In-memory database
- **OpenAPI/Swagger**: API documentation
- **Maven**: Build tool
- **Docker**: Containerization

## Database Schema

The application uses a relational database with the following entities and relationships:

```
School (1) ←→ (N) Student
School (1) ←→ (N) Teacher
School (1) ←→ (N) Subject
School (1) ←→ (N) Class
Teacher (1) ←→ (N) Subject
Teacher (1) ←→ (N) Class
Student (N) ←→ (N) Subject (via Enrollment)
Student (1) ←→ (N) Class
Class (1) ←→ (N) Student
```

## Getting Started

### Prerequisites

- Java 24 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (for containerized deployment)

### Running Locally

1. **Clone the repository**

   ```bash
   git clone https://github.com/Cyber-Naimo/school-crud-app
   cd school-crud-app
   ```

2. **Build the application**

   ```bash
   mvn clean install
   ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application: http://localhost:7070
   - H2 Console: http://localhost:7070/h2-console
   - Swagger UI: http://localhost:7070/swagger-ui.html
   - API Docs: http://localhost:7070/api-docs

### Running with Docker

1. **Build and run with Docker Compose**

   ```bash
   docker-compose up --build
   ```

2. **Access the application**
   - Application: http://localhost:7070
   - H2 Console: http://localhost:9092
   - Swagger UI: http://localhost:7070/swagger-ui.html

## API Endpoints

### Schools

- `GET /api/schools` - Get all schools
- `GET /api/schools/{id}` - Get school by ID
- `GET /api/schools/search?name={name}` - Search schools by name
- `GET /api/schools/city/{city}` - Get schools by city
- `POST /api/schools` - Create a new school
- `PUT /api/schools/{id}` - Update a school
- `DELETE /api/schools/{id}` - Delete a school
- `GET /api/schools/count` - Get total schools count

### Students

- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `GET /api/students/school/{schoolId}` - Get students by school
- `GET /api/students/class/{classId}` - Get students by class
- `POST /api/students` - Create a new student
- `PUT /api/students/{id}` - Update a student
- `DELETE /api/students/{id}` - Delete a student

### Teachers

- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `GET /api/teachers/school/{schoolId}` - Get teachers by school
- `POST /api/teachers` - Create a new teacher
- `PUT /api/teachers/{id}` - Update a teacher
- `DELETE /api/teachers/{id}` - Delete a teacher

### Subjects

- `GET /api/subjects` - Get all subjects
- `GET /api/subjects/{id}` - Get subject by ID
- `GET /api/subjects/school/{schoolId}` - Get subjects by school
- `GET /api/subjects/teacher/{teacherId}` - Get subjects by teacher
- `POST /api/subjects` - Create a new subject
- `PUT /api/subjects/{id}` - Update a subject
- `DELETE /api/subjects/{id}` - Delete a subject

### Classes

- `GET /api/classes` - Get all classes
- `GET /api/classes/{id}` - Get class by ID
- `GET /api/classes/school/{schoolId}` - Get classes by school
- `GET /api/classes/teacher/{teacherId}` - Get classes by teacher
- `POST /api/classes` - Create a new class
- `PUT /api/classes/{id}` - Update a class
- `DELETE /api/classes/{id}` - Delete a class

### Enrollments

- `GET /api/enrollments` - Get all enrollments
- `GET /api/enrollments/{id}` - Get enrollment by ID
- `GET /api/enrollments/student/{studentId}` - Get enrollments by student
- `GET /api/enrollments/subject/{subjectId}` - Get enrollments by subject
- `POST /api/enrollments` - Create a new enrollment
- `PUT /api/enrollments/{id}` - Update an enrollment
- `DELETE /api/enrollments/{id}` - Delete an enrollment

## Sample Data

The application comes with sample data including:

- 2 schools (Springfield High School, Lincoln Academy)
- 3 teachers (John Smith, Sarah Johnson, Michael Brown)
- 4 subjects (Mathematics, Physics, English Literature, Chemistry)
- 3 classes (Grade 10A, Grade 11B, Grade 12A)
- 4 students (Alice Johnson, Bob Williams, Charlie Davis, Diana Miller)
- 5 enrollments

## Configuration

### Application Properties

The application can be configured through `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:school_db
spring.datasource.username=sa
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Server Configuration
server.port=7070

# OpenAPI Configuration
springdoc.swagger-ui.path=/swagger-ui.html
```

### Docker Configuration

For Docker deployment, the application uses the `docker` profile:

```properties
# Docker profile configuration
spring.datasource.url=jdbc:h2:mem:school_db
spring.jpa.hibernate.ddl-auto=create-drop
```

## Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/crud_school/
│   │       ├── controller/     # REST controllers
│   │       ├── dto/           # Data Transfer Objects
│   │       ├── entity/        # JPA entities
│   │       ├── repository/    # Data access layer
│   │       ├── service/       # Business logic
│   │       └── config/        # Configuration classes
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/crud_school/
```

### Adding New Features

1. **Create Entity**: Add JPA entity in `entity/` package
2. **Create Repository**: Add repository interface in `repository/` package
3. **Create Service**: Add business logic in `service/` package
4. **Create Controller**: Add REST endpoints in `controller/` package
5. **Add DTOs**: Create DTOs for API responses in `dto/` package

## Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=SchoolControllerTest

# Run tests with coverage
mvn test jacoco:report
```

### API Testing

You can test the API using:

- Swagger UI: http://localhost:7070/swagger-ui.html
- Postman or any REST client
- curl commands

Example curl commands:

```bash
# Get all schools
curl -X GET http://localhost:7070/api/schools

# Create a new school
curl -X POST http://localhost:7070/api/schools \
  -H "Content-Type: application/json" \
  -d '{
    "name": "New School",
    "description": "A new school",
    "address": "123 New Street",
    "phone": "+1-555-000-0000",
    "email": "info@newschool.edu"
  }'
```

## Deployment

### Docker Deployment

1. **Build the image**

   ```bash
   docker build -t crud-school .
   ```

2. **Run with docker-compose**

   ```bash
   docker-compose up -d
   ```

3. **Stop the application**
   ```bash
   docker-compose down
   ```

### Production Deployment

For production deployment, consider:

- Using PostgreSQL instead of H2
- Adding security with Spring Security
- Implementing caching with Redis
- Adding monitoring with Actuator
- Using a reverse proxy like Nginx

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License.

## Support

For support and questions, please open an issue in the repository.
