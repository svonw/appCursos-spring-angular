# Microservices System - Educational Management

Microservices system built with Spring Boot and Spring Cloud for managing 
students, courses, exams, and answers in an educational environment.

## 🏗️ Architecture

The project consists of the following services:

### Infrastructure Services
- **microservicios-eureka** - Service registry and discovery server
- **microservicios-gateway** - API Gateway for routing and load balancing

### Business Microservices
- **microservicios-usuarios** - Student management
- **microservicios-cursos** - Course management
- **microservicios-examenes** - Exam management
- **microservicios-respuestas** - Exam answers management

### Generic Modules
- **generic-microservicios** - Common functionalities for all 
microservices
- **generic-alumnos** - Common entities and logic for students
- **generic-examenes** - Common entities and logic for exams

## 🛠️ Technologies

- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Cloud 2024.0.1**
- **Spring Data JPA**
- **Netflix Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **MySQL** - Database
- **Maven** - Dependency Management
- **Spring Dotenv** - Environment variables management

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- Port 8761 available for Eureka Server
- Port 8090 available for Gateway

## 🚀 Installation and Setup

### 1. Database Setup

Create the database in MySQL:
```sql
CREATE DATABASE db_microservicios_examenes;
```

### 2. Environment Variables Configuration

Each microservice uses environment variables for database configuration. Create a `.env` file in each microservice 
directory:

#### For each microservice (usuarios, cursos, examenes, respuestas):

Create `.env` file:
```env
# Database Configuration
DB_URL=your_db_url_here
DB_USERNAME=root
DB_PASSWORD=your_password_here
```


### 3. Application Properties

The microservices are configured to use environment variables in 
`application.properties`:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect
spring.jpa.generate-ddl=true
```

### 4. Build Project

```bash
# From project root directory
mvn clean install
```

## 🔐 Security Notes

- **`.env` files are automatically excluded from Git** through 
`.gitignore` files
- Never commit database credentials to version control
- Each microservice manages its own environment configuration 
independently

## 🏃‍♂️ Running the Application

**⚠️ Important startup order:**

1. **Configure Environment Variables**
   - Ensure each microservice has its `.env` file properly configured
   - Verify database credentials and connection details

2. **Eureka Server** (first)
```bash
cd microservicios-eureka
mvn spring-boot:run
```
Verify at: http://localhost:8761

3. **Business Microservices** (any order)
```bash
# Terminal 2
cd microservicios-usuarios
mvn spring-boot:run

# Terminal 3
cd microservicios-cursos
mvn spring-boot:run

# Terminal 4
cd microservicios-examenes
mvn spring-boot:run

# Terminal 5
cd microservicios-respuestas
mvn spring-boot:run
```

4. **Gateway** (last)
```bash
cd microservicios-gateway
mvn spring-boot:run
```

## 🌐 API Endpoints

All endpoints are available through the Gateway at 
`http://localhost:8090`:

### Students
- `GET /api/alumnos` - List students
- `GET /api/alumnos/{id}` - Get student by ID
- `POST /api/alumnos` - Create student
- `PUT /api/alumnos/{id}` - Update student
- `DELETE /api/alumnos/{id}` - Delete student
- `GET /api/alumnos/uploads/img/{id}` - Get student photo
- `POST /api/alumnos/crear-con-foto` - Create student with photo
- `PUT /api/alumnos/editar-con-foto/{id}` - Update student with photo

### Courses
- `GET /api/cursos` - List courses
- `GET /api/cursos/{id}` - Get course by ID
- `POST /api/cursos` - Create course
- `PUT /api/cursos/{id}` - Update course
- `DELETE /api/cursos/{id}` - Delete course

### Exams
- `GET /api/examenes` - List exams
- `GET /api/examenes/{id}` - Get exam by ID
- `POST /api/examenes` - Create exam
- `PUT /api/examenes/{id}` - Update exam
- `DELETE /api/examenes/{id}` - Delete exam

### Answers
- `POST /api/respuestas` - Create answers (batch)
- `GET /api/respuestas/alumno/{alumnoId}/examen/{examenId}` - Get answers by student and exam
- `GET /api/respuestas/alumno/{alumnoId}/examenes-respondidos` - Get exams answered by student

## 📊 Monitoring

- **Eureka Dashboard**: http://localhost:8761

## 🗂️ Project Structure

```
microservicios_test_udemy/
├── microservicios-eureka/          # Registry server

├── microservicios-gateway/         # API Gateway

├── microservicios-usuarios/        # Students service
├── microservicios-cursos/          # Courses service
├── microservicios-examenes/        # Exams service
├── microservicios-respuestas/      # Answers service
│ 
├── generic-microservicios/         # Common generic module
├── generic-alumnos/               # Common student entities
└── generic-examenes/              # Common exam entities
```

## 🔧 Port Configuration

- **Eureka Server**: 8761
- **Gateway**: 8090
- **Other microservices**: Dynamic ports using `${PORT:0}` (automatically configured by Eureka)

## 🛠️ Development Setup

1. **Clone the repository**
2. **Configure your database credentials** in each `.env` file
3. **Follow the installation and running steps above**

## 🔍 Troubleshooting

- **Database Connection Issues**: Verify `.env` files contain correct 
credentials
- **Service Discovery Issues**: Ensure Eureka Server is running first
- **Port Conflicts**: Check that required ports (8761, 8090) are available
- **Environment Variables**: Ensure Spring Dotenv dependency is included 
in each microservice 
