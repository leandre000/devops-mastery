package com.rca.demo_course.test;

import com.rca.demo_course.domain.Course;
import com.rca.demo_course.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test class for CourseService operations.
 * Tests null validation and error handling scenarios.
 * 
 * @author Leandre
 */
public class Leandre {

    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseServiceImpl();
    }

    @Nested
    @DisplayName("Course Creation Tests")
    class CourseCreationTests {

        @Test
        @DisplayName("Should create course successfully with valid data")
        void shouldCreateCourseSuccessfully() {
            // Given
            Course course = new Course();
            course.setCourseCode("CS101");
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor("Dr. Smith");
            course.setCredits(3);
            course.setMaxStudents(30);
            course.setDepartment("Computer Science");

            // When
            Course createdCourse = courseService.createCourse(course);

            // Then
            assertNotNull(createdCourse);
            assertNotNull(createdCourse.getId());
            assertEquals("CS101", createdCourse.getCourseCode());
            assertEquals("Introduction to Computer Science", createdCourse.getCourseName());
            assertEquals("Dr. Smith", createdCourse.getInstructor());
            assertEquals(3, createdCourse.getCredits());
            assertEquals(30, createdCourse.getMaxStudents());
            assertTrue(createdCourse.isActive());
        }

        @Test
        @DisplayName("Should throw exception when course is null")
        void shouldThrowExceptionWhenCourseIsNull() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(null)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when course code is null")
        void shouldThrowExceptionWhenCourseCodeIsNull() {
            // Given
            Course course = new Course();
            course.setCourseCode(null);
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor("Dr. Smith");
            course.setCredits(3);

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when course code is empty")
        void shouldThrowExceptionWhenCourseCodeIsEmpty() {
            // Given
            Course course = new Course();
            course.setCourseCode("");
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor("Dr. Smith");
            course.setCredits(3);

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when course name is null")
        void shouldThrowExceptionWhenCourseNameIsNull() {
            // Given
            Course course = new Course();
            course.setCourseCode("CS101");
            course.setCourseName(null);
            course.setInstructor("Dr. Smith");
            course.setCredits(3);

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when instructor is null")
        void shouldThrowExceptionWhenInstructorIsNull() {
            // Given
            Course course = new Course();
            course.setCourseCode("CS101");
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor(null);
            course.setCredits(3);

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when credits are invalid")
        void shouldThrowExceptionWhenCreditsAreInvalid() {
            // Given
            Course course = new Course();
            course.setCourseCode("CS101");
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor("Dr. Smith");
            course.setCredits(0); // Invalid credits

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when max students is invalid")
        void shouldThrowExceptionWhenMaxStudentsIsInvalid() {
            // Given
            Course course = new Course();
            course.setCourseCode("CS101");
            course.setCourseName("Introduction to Computer Science");
            course.setInstructor("Dr. Smith");
            course.setCredits(3);
            course.setMaxStudents(0); // Invalid max students

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.createCourse(course)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Course Retrieval Tests")
    class CourseRetrievalTests {

        @Test
        @DisplayName("Should throw exception when getting course by null ID")
        void shouldThrowExceptionWhenGettingCourseByNullId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.getCourseById(null)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should return empty optional for non-existent course")
        void shouldReturnEmptyOptionalForNonExistentCourse() {
            // When
            Optional<Course> result = courseService.getCourseById(999L);

            // Then
            assertFalse(result.isPresent());
        }

        @Test
        @DisplayName("Should find course by valid ID")
        void shouldFindCourseByValidId() {
            // Given
            Course course = createValidCourse();
            Course createdCourse = courseService.createCourse(course);

            // When
            Optional<Course> result = courseService.getCourseById(createdCourse.getId());

            // Then
            assertTrue(result.isPresent());
            assertEquals(createdCourse.getId(), result.get().getId());
        }
    }

    @Nested
    @DisplayName("Course Update Tests")
    class CourseUpdateTests {

        @Test
        @DisplayName("Should throw exception when updating course with null ID")
        void shouldThrowExceptionWhenUpdatingCourseWithNullId() {
            // Given
            Course course = createValidCourse();

            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.updateCourse(null, course)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when updating course with null data")
        void shouldThrowExceptionWhenUpdatingCourseWithNullData() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.updateCourse(1L, null)
            );
            
            assertEquals("Course data cannot be null or invalid", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when updating non-existent course")
        void shouldThrowExceptionWhenUpdatingNonExistentCourse() {
            // Given
            Course course = createValidCourse();

            // When & Then
            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> courseService.updateCourse(999L, course)
            );
            
            assertEquals("Course with ID 999 not found", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Course Search Tests")
    class CourseSearchTests {

        @Test
        @DisplayName("Should throw exception when searching by null course code")
        void shouldThrowExceptionWhenSearchingByNullCourseCode() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.findCoursesByCode(null)
            );
            
            assertEquals("Course code cannot be null or empty", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when searching by empty course code")
        void shouldThrowExceptionWhenSearchingByEmptyCourseCode() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.findCoursesByCode("")
            );
            
            assertEquals("Course code cannot be null or empty", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when searching by null instructor")
        void shouldThrowExceptionWhenSearchingByNullInstructor() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.findCoursesByInstructor(null)
            );
            
            assertEquals("Instructor name cannot be null or empty", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when searching by null department")
        void shouldThrowExceptionWhenSearchingByNullDepartment() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.findCoursesByDepartment(null)
            );
            
            assertEquals("Department cannot be null or empty", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Student Enrollment Tests")
    class StudentEnrollmentTests {

        @Test
        @DisplayName("Should throw exception when enrolling student with null course ID")
        void shouldThrowExceptionWhenEnrollingStudentWithNullCourseId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.enrollStudent(null, 1L)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when enrolling student with null student ID")
        void shouldThrowExceptionWhenEnrollingStudentWithNullStudentId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.enrollStudent(1L, null)
            );
            
            assertEquals("Student ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when enrolling student in non-existent course")
        void shouldThrowExceptionWhenEnrollingStudentInNonExistentCourse() {
            // When & Then
            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> courseService.enrollStudent(999L, 1L)
            );
            
            assertEquals("Course with ID 999 not found", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when unenrolling student with null course ID")
        void shouldThrowExceptionWhenUnenrollingStudentWithNullCourseId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.unenrollStudent(null, 1L)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when unenrolling student with null student ID")
        void shouldThrowExceptionWhenUnenrollingStudentWithNullStudentId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.unenrollStudent(1L, null)
            );
            
            assertEquals("Student ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when getting enrollment count with null course ID")
        void shouldThrowExceptionWhenGettingEnrollmentCountWithNullCourseId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.getEnrollmentCount(null)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Course Deletion Tests")
    class CourseDeletionTests {

        @Test
        @DisplayName("Should throw exception when deleting course with null ID")
        void shouldThrowExceptionWhenDeletingCourseWithNullId() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.deleteCourse(null)
            );
            
            assertEquals("Course ID cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should return false when deleting non-existent course")
        void shouldReturnFalseWhenDeletingNonExistentCourse() {
            // When
            boolean result = courseService.deleteCourse(999L);

            // Then
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Credit Range Tests")
    class CreditRangeTests {

        @Test
        @DisplayName("Should throw exception when credit range is invalid")
        void shouldThrowExceptionWhenCreditRangeIsInvalid() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.getCoursesByCreditRange(5, 3) // min > max
            );
            
            assertEquals("Invalid credit range", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when min credits is negative")
        void shouldThrowExceptionWhenMinCreditsIsNegative() {
            // When & Then
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> courseService.getCoursesByCreditRange(-1, 3)
            );
            
            assertEquals("Invalid credit range", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should perform complete course lifecycle")
        void shouldPerformCompleteCourseLifecycle() {
            // Create course
            Course course = createValidCourse();
            Course createdCourse = courseService.createCourse(course);
            assertNotNull(createdCourse.getId());

            // Retrieve course
            Optional<Course> retrievedCourse = courseService.getCourseById(createdCourse.getId());
            assertTrue(retrievedCourse.isPresent());

            // Update course
            createdCourse.setDescription("Updated description");
            Course updatedCourse = courseService.updateCourse(createdCourse.getId(), createdCourse);
            assertEquals("Updated description", updatedCourse.getDescription());

            // Enroll student
            boolean enrolled = courseService.enrollStudent(createdCourse.getId(), 1L);
            assertTrue(enrolled);

            // Check enrollment count
            int count = courseService.getEnrollmentCount(createdCourse.getId());
            assertEquals(1, count);

            // Unenroll student
            boolean unenrolled = courseService.unenrollStudent(createdCourse.getId(), 1L);
            assertTrue(unenrolled);

            // Delete course
            boolean deleted = courseService.deleteCourse(createdCourse.getId());
            assertTrue(deleted);

            // Verify course is deleted
            Optional<Course> deletedCourse = courseService.getCourseById(createdCourse.getId());
            assertFalse(deletedCourse.isPresent());
        }

        @Test
        @DisplayName("Should handle multiple courses and search operations")
        void shouldHandleMultipleCoursesAndSearchOperations() {
            // Create multiple courses
            Course course1 = createValidCourse();
            course1.setCourseCode("CS101");
            course1.setInstructor("Dr. Smith");
            course1.setDepartment("Computer Science");
            courseService.createCourse(course1);

            Course course2 = createValidCourse();
            course2.setCourseCode("MATH201");
            course2.setInstructor("Dr. Johnson");
            course2.setDepartment("Mathematics");
            courseService.createCourse(course2);

            Course course3 = createValidCourse();
            course3.setCourseCode("CS102");
            course3.setInstructor("Dr. Smith");
            course3.setDepartment("Computer Science");
            courseService.createCourse(course3);

            // Test search by instructor
            List<Course> smithCourses = courseService.findCoursesByInstructor("Smith");
            assertEquals(2, smithCourses.size());

            // Test search by department
            List<Course> csCourses = courseService.findCoursesByDepartment("Computer Science");
            assertEquals(2, csCourses.size());

            // Test search by course code
            List<Course> csCodeCourses = courseService.findCoursesByCode("CS");
            assertEquals(2, csCodeCourses.size());

            // Test get all courses
            List<Course> allCourses = courseService.getAllCourses();
            assertEquals(3, allCourses.size());
        }
    }

    // Helper method to create a valid course
    private Course createValidCourse() {
        Course course = new Course();
        course.setCourseCode("TEST101");
        course.setCourseName("Test Course");
        course.setInstructor("Test Instructor");
        course.setCredits(3);
        course.setMaxStudents(30);
        course.setDepartment("Test Department");
        course.setStartDate(LocalDate.now());
        course.setEndDate(LocalDate.now().plusMonths(3));
        return course;
    }
}