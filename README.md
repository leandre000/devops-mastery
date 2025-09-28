# DevOps Mastery - Calculator Application

A comprehensive Spring Boot application demonstrating calculator functionality with complete unit testing using JUnit and Mockito.

## Features

### Calculator Operations
- **Addition**: Add two numbers
- **Subtraction**: Subtract two numbers  
- **Multiplication**: Multiply two numbers
- **Division**: Divide two numbers (with zero-division error handling)
- **Power**: Calculate power of a number
- **Square Root**: Calculate square root (with negative number validation)
- **Absolute Value**: Calculate absolute value
- **Percentage**: Calculate percentage of a number

### Student Management System
- **Student CRUD Operations**: Create, Read, Update, Delete students
- **Student Validation**: Email format, GPA range validation
- **Student Search**: Find students by name or email
- **Grade Management**: Calculate average grades and GPA

## Project Structure

```
src/
├── main/
│   ├── java/com/rca/demo_course/
│   │   ├── controller/
│   │   │   └── CalculatorController.java          # REST API endpoints
│   │   ├── service/
│   │   │   ├── CalculatorService.java             # Calculator service interface
│   │   │   ├── StudentService.java                # Student service interface
│   │   │   └── impl/
│   │   │       ├── CalculatorServiceImpl.java     # Calculator service implementation
│   │   │       └── StudentServiceImpl.java        # Student service implementation
│   │   ├── domain/
│   │   │   ├── CalculatorModel.java               # Calculator domain model
│   │   │   └── Student.java                       # Student domain model
│   │   └── DemoCourseApplication.java             # Spring Boot main class
│   └── resources/
│       └── application.properties                 # Application configuration
└── test/
    └── java/com/rca/demo_course/
        ├── controller/
        │   └── CalculatorControllerTest.java      # Controller unit tests
        ├── service/
        │   └── CalculatorServiceImplTest.java     # Service unit tests
        ├── domain/
        │   ├── CalculatorModelTestAB.java         # Domain model tests with Mockito
        │   └── CalculatorModelTest.java           # Basic domain model tests
        └── DemoCourseApplicationTests.java        # Application integration tests
```

## API Endpoints

### Calculator Endpoints

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| GET | `/api/calculator/add` | Add two numbers | `a`, `b` |
| GET | `/api/calculator/subtract` | Subtract two numbers | `a`, `b` |
| GET | `/api/calculator/multiply` | Multiply two numbers | `a`, `b` |
| GET | `/api/calculator/divide` | Divide two numbers | `a`, `b` |
| GET | `/api/calculator/power` | Calculate power | `base`, `exponent` |
| GET | `/api/calculator/sqrt` | Calculate square root | `number` |
| GET | `/api/calculator/abs` | Calculate absolute value | `number` |
| GET | `/api/calculator/percentage` | Calculate percentage | `number`, `percentage` |

### Example API Calls

```bash
# Addition
curl "http://localhost:8080/api/calculator/add?a=5&b=3"
# Response: {"a":5.0,"b":3.0,"result":8.0,"operation":"addition"}

# Division
curl "http://localhost:8080/api/calculator/divide?a=10&b=2"
# Response: {"a":10.0,"b":2.0,"result":5.0,"operation":"division"}

# Division by zero (error handling)
curl "http://localhost:8080/api/calculator/divide?a=10&b=0"
# Response: HTTP 500 - Division by zero is not allowed
```

## How to Test the Application

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher
- Internet connection for dependency download

### 1. Clone and Setup
```bash
git clone <repository-url>
cd devops_mastery
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Unit Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Classes
```bash
# Test Calculator Service
mvn test -Dtest=CalculatorServiceImplTest

# Test Calculator Controller
mvn test -Dtest=CalculatorControllerTest

# Test Calculator Model with Mockito
mvn test -Dtest=CalculatorModelTestAB

# Test Student Service
mvn test -Dtest=StudentServiceTest
```

#### Run Tests with Coverage
```bash
mvn test jacoco:report
```

### 4. Start the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Manual Testing with curl/Postman

#### Test Calculator Operations
```bash
# Test addition
curl "http://localhost:8080/api/calculator/add?a=5&b=3"

# Test division
curl "http://localhost:8080/api/calculator/divide?a=15&b=3"

# Test division by zero (should return error)
curl "http://localhost:8080/api/calculator/divide?a=10&b=0"

# Test square root
curl "http://localhost:8080/api/calculator/sqrt?number=9"

# Test square root of negative number (should return error)
curl "http://localhost:8080/api/calculator/sqrt?number=-4"
```

#### Test Error Handling
```bash
# Missing parameters
curl "http://localhost:8080/api/calculator/add"

# Invalid parameter format
curl "http://localhost:8080/api/calculator/add?a=invalid&b=3"
```

### 6. Integration Testing

#### Test with Spring Boot Test
```bash
mvn test -Dtest=DemoCourseApplicationTests
```

#### Test Application Context Loading
```bash
mvn test -Dtest=*ApplicationTests
```

## Testing Strategy

### Unit Tests
- **CalculatorServiceImplTest**: Tests all mathematical operations with edge cases
- **CalculatorControllerTest**: Tests REST endpoints and error handling
- **CalculatorModelTestAB**: Tests domain model with Mockito @InjectMocks
- **StudentServiceTest**: Tests student management operations

### Test Coverage Areas
1. **Happy Path Testing**: Normal operation scenarios
2. **Edge Case Testing**: Zero values, negative numbers, decimal precision
3. **Error Handling**: Division by zero, invalid inputs, negative square roots
4. **Mockito Integration**: Dependency injection and service mocking
5. **REST API Testing**: HTTP status codes, JSON responses, parameter validation

### Key Test Scenarios

#### Calculator Service Tests
- ✅ Addition with positive, negative, and decimal numbers
- ✅ Subtraction with various number combinations
- ✅ Multiplication including zero multiplication
- ✅ Division with normal cases and zero-division error handling
- ✅ Power calculations with decimals
- ✅ Square root with positive numbers and negative number error handling
- ✅ Absolute value calculations
- ✅ Percentage calculations

#### Controller Tests
- ✅ All REST endpoints return correct HTTP status codes
- ✅ JSON response format validation
- ✅ Parameter validation and error handling
- ✅ Division by zero error propagation
- ✅ Invalid parameter format handling

#### Mockito Tests
- ✅ Service dependency injection with @InjectMocks
- ✅ Method call verification with verify()
- ✅ Exception handling in mocked services
- ✅ Multiple operation sequence testing

## Error Handling

The application includes comprehensive error handling:

1. **Division by Zero**: Returns `IllegalArgumentException`
2. **Negative Square Root**: Returns `IllegalArgumentException`
3. **Invalid Parameters**: Returns HTTP 400 Bad Request
4. **Missing Parameters**: Returns HTTP 400 Bad Request
5. **Service Errors**: Propagated as HTTP 500 Internal Server Error

## Performance Considerations

- All calculator operations are O(1) time complexity
- Student operations use in-memory storage for demo purposes
- REST endpoints include proper HTTP status codes
- Comprehensive input validation prevents invalid operations

## Technologies Used

- **Spring Boot 3.5.6**: Application framework
- **Spring Web**: REST API development
- **Spring Data JPA**: Data persistence (configured but using in-memory for demo)
- **JUnit 5**: Unit testing framework
- **Mockito**: Mocking framework for dependency injection testing
- **Maven**: Build and dependency management
- **Java 21**: Programming language

## Development Notes

- All methods are properly documented with JavaDoc
- Service layer implements business logic separation
- Controller layer handles HTTP concerns only
- Domain models contain business entities
- Comprehensive test coverage with both unit and integration tests
- Mockito @InjectMocks demonstrates dependency injection testing
- Error handling follows Spring Boot best practices

## Future Enhancements

- Database integration for persistent student storage
- Authentication and authorization
- API rate limiting
- Swagger/OpenAPI documentation
- Docker containerization
- CI/CD pipeline integration
