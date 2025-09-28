package com.rca.demo_course.service;

import com.rca.demo_course.domain.Course;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for course operations.
 * Provides CRUD operations and business logic for course management.
 */
public interface CourseService {

    /**
     * Creates a new course.
     *
     * @param course the course to create
     * @return the created course
     * @throws IllegalArgumentException if course data is invalid
     */
    Course createCourse(Course course);

    /**
     * Retrieves a course by ID.
     *
     * @param id the course ID
     * @return an Optional containing the course if found
     */
    Optional<Course> getCourseById(Long id);

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    List<Course> getAllCourses();

    /**
     * Updates an existing course.
     *
     * @param id the course ID
     * @param course the updated course data
     * @return the updated course
     * @throws IllegalArgumentException if course data is invalid
     * @throws NoSuchElementException if course is not found
     */
    Course updateCourse(Long id, Course course);

    /**
     * Deletes a course by ID.
     *
     * @param id the course ID
     * @return true if the course was deleted, false otherwise
     */
    boolean deleteCourse(Long id);

    /**
     * Finds courses by course code.
     *
     * @param courseCode the course code to search for
     * @return a list of courses matching the course code
     */
    List<Course> findCoursesByCode(String courseCode);

    /**
     * Finds courses by instructor.
     *
     * @param instructor the instructor name to search for
     * @return a list of courses taught by the instructor
     */
    List<Course> findCoursesByInstructor(String instructor);

    /**
     * Finds courses by department.
     *
     * @param department the department to search for
     * @return a list of courses in the department
     */
    List<Course> findCoursesByDepartment(String department);

    /**
     * Enrolls a student in a course.
     *
     * @param courseId the course ID
     * @param studentId the student ID
     * @return true if enrollment was successful
     * @throws IllegalStateException if course is full or student already enrolled
     */
    boolean enrollStudent(Long courseId, Long studentId);

    /**
     * Unenrolls a student from a course.
     *
     * @param courseId the course ID
     * @param studentId the student ID
     * @return true if unenrollment was successful
     */
    boolean unenrollStudent(Long courseId, Long studentId);

    /**
     * Gets the enrollment count for a course.
     *
     * @param courseId the course ID
     * @return the number of enrolled students
     */
    int getEnrollmentCount(Long courseId);

    /**
     * Validates course data.
     *
     * @param course the course to validate
     * @return true if the course data is valid
     */
    boolean validateCourse(Course course);

    /**
     * Gets all active courses.
     *
     * @return a list of active courses
     */
    List<Course> getActiveCourses();

    /**
     * Gets courses with available spots.
     *
     * @return a list of courses that are not full
     */
    List<Course> getCoursesWithAvailableSpots();

    /**
     * Gets courses by credit range.
     *
     * @param minCredits minimum credits
     * @param maxCredits maximum credits
     * @return a list of courses within the credit range
     */
    List<Course> getCoursesByCreditRange(int minCredits, int maxCredits);
}
