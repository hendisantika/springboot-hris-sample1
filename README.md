# Spring Boot HRIS Sample Application

A comprehensive Human Resource Information System (HRIS) built with Spring Boot, providing essential HR functionalities
including employee management, department organization, and job tracking.

## 🚀 Features

- **Employee Management**: Complete CRUD operations for employee records
- **Department Management**: Organize employees by departments with hierarchical structure
- **Job Management**: Define and manage job positions and titles
- **Location Tracking**: Manage office locations and geographic information
- **Security & Authentication**: Session-based authentication with interceptors
- **Web Interface**: Responsive UI built with Thymeleaf and Bootstrap
- **Database Management**: JPA/Hibernate integration with H2 (development) and MySQL (production)
- **RESTful APIs**: Backend services for HR operations

## 🛠️ Technology Stack

- **Java**: 21+
- **Spring Boot**: 3.5.6
- **Spring Data JPA**: Database operations and entity management
- **Spring Web**: RESTful web services and MVC
- **Hibernate**: ORM framework
- **Thymeleaf**: Server-side template engine
- **Bootstrap**: 5.3.8 for responsive UI
- **H2 Database**: In-memory database for development
- **MySQL**: Production database
- **Lombok**: Reducing boilerplate code
- **Maven**: Dependency management and build tool

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+ (for production)
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/hendisantika/springboot-hris-sample1.git
cd springboot-hris-sample1
```

### 2. Development Setup (H2 Database)

The application is pre-configured to use H2 in-memory database for easy development:

```bash
# Set Java 21 (if not default)
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8082`

### 3. Production Setup (MySQL Database)

For production deployment with MySQL:

1. **Install and configure MySQL**:

```bash
# macOS with Homebrew
brew install mysql
brew services start mysql

# Create database
mysql -u root -p
CREATE DATABASE hr;
```

2. **Update configuration**:
   Edit `src/main/resources/application.properties`:

```properties
# Comment out H2 configuration
#spring.datasource.url=jdbc:h2:mem:testdb
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=password
#spring.h2.console.enabled=true
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# Uncomment MySQL configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hr?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&useSSL=false
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

3. **Run the application**:

```bash
mvn spring-boot:run
```

## 🗄️ Database Schema

The application includes the following main entities:

### Employee

- Employee ID, First Name, Last Name
- Email, Phone Number, Hire Date
- Salary, Commission Percentage
- Job and Department associations
- Manager relationship (self-referencing)

### Department

- Department ID, Department Name
- Location and Manager references
- Employee collections

### Job

- Job ID, Job Title
- Minimum and Maximum Salary ranges

### Location

- Location ID, Street Address
- City, State/Province, Postal Code
- Country association

### Country & Region

- Geographic hierarchy management

## 🌐 API Endpoints

### Web Interface

- `GET /` - Home/Dashboard page
- `GET /login` - Login page
- `POST /checklogin` - Authentication endpoint
- `GET /employees` - Employee listing page
- `GET /departments` - Department management page

### Development Tools

- `GET /h2-console` - H2 Database console (development mode)

## 🔒 Security

The application implements:

- **Session-based authentication** using Spring Security interceptors
- **CSRF protection** for forms
- **Method-level security** for sensitive operations
- **Input validation** and sanitization

## 💻 Development Tools

### H2 Console Access

When running in development mode, access the H2 console at:

- URL: `http://localhost:8082/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

### Hot Reload

The application supports hot reload during development:

```properties
spring.thymeleaf.cache=false
spring.jpa.show-sql=true
```

## 🚀 Running the Application

### Development Mode

```bash
# Using Maven
mvn spring-boot:run

# Using Java directly
mvn clean package
java -jar target/springboot-hris-sample1-0.0.1-SNAPSHOT.jar
```

### Production Mode

```bash
# Set production profile
mvn spring-boot:run -Dspring.profiles.active=prod

# Or with environment variable
export SPRING_PROFILES_ACTIVE=prod
mvn spring-boot:run
```

## 📱 User Interface

The application features a responsive web interface built with:

- **Bootstrap 5.3.8**: Modern, mobile-first design
- **jQuery & DataTables**: Interactive data tables
- **Thymeleaf**: Server-side templating
- **Font Awesome**: Icons and visual elements

## 🧪 Testing

Run the test suite:

```bash
# Run all tests
mvn test

# Run with coverage
mvn clean test jacoco:report
```

## 📊 Project Structure

```
src/
├── main/
│   ├── java/com/hendisantika/hris/springboothrissample1/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # Web controllers
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Data access layer
│   │   └── service/        # Business logic layer
│   └── resources/
│       ├── static/         # CSS, JS, images
│       ├── templates/      # Thymeleaf templates
│       └── application.properties
└── test/                   # Test classes
```

## 🔄 Configuration Management

### Application Properties

Key configuration options:

```properties
# Server Configuration
server.port=8082
# Database Configuration
spring.jpa.hibernate.ddl-auto=create-drop  # Development
spring.jpa.show-sql=true                   # SQL logging
spring.jpa.properties.hibernate.format_sql=true
# Thymeleaf Configuration
spring.thymeleaf.cache=false              # Development
# H2 Console (Development)
spring.h2.console.enabled=true
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
- GitHub: [@hendisantika](https://github.com/hendisantika)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Bootstrap team for the responsive UI components
- All contributors who help improve this project

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Reference](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf Documentation](https://www.thymeleaf.org/doc/)
- [Bootstrap Documentation](https://getbootstrap.com/docs/)

---

*For more Spring Boot examples and tutorials, visit [hendisantika's repositories](https://github.com/hendisantika)*
