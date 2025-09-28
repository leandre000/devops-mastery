package com.rca.demo_course.service.impl;

import com.rca.demo_course.domain.Course;
import com.rca.demo_course.service.CourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of CourseService interface.
 * Provides business logic for course management operations.
 */
@Service
public class CourseServiceImpl implements CourseService {

    // In-memory storage for demo purposes (in real application, this would be a database)
    private final Map<Long, Course> courses = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Course createCourse(Course course) {
        if (!validateCourse(course)) {
            throw new IllegalArgumentException("Course data cannot be null or invalid");
        }

        course.setId(nextId++);
        if (course.getEnrolledStudents() == null) {
            course.setEnrolledStudents(new ArrayList<>());
        }
        // Set course as active by default
        course.setActive(true);
        courses.put(course.getId(), course);
        return course;
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        if (!validateCourse(course)) {
            throw new IllegalArgumentException("Course data cannot be null or invalid");
        }

        Course existingCourse = courses.get(id);
        if (existingCourse == null) {
            throw new NoSuchElementException("Course with ID " + id + " not found");
        }

        course.setId(id);
        // Preserve existing enrollment data if not provided
        if (course.getEnrolledStudents() == null) {
            course.setEnrolledStudents(existingCourse.getEnrolledStudents());
        }
        courses.put(id, course);
        return course;
    }

    @Override
    public boolean deleteCourse(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        return courses.remove(id) != null;
    }

    @Override
    public List<Course> findCoursesByCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        
        return courses.values().stream()
                .filter(course -> course.getCourseCode() != null && 
                        course.getCourseCode().toLowerCase().contains(courseCode.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesByInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty");
        }
        
        return courses.values().stream()
                .filter(course -> course.getInstructor() != null && 
                        course.getInstructor().toLowerCase().contains(instructor.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesByDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }
        
        return courses.values().stream()
                .filter(course -> course.getDepartment() != null && 
                        course.getDepartment().toLowerCase().contains(department.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean enrollStudent(Long courseId, Long studentId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        Course course = courses.get(courseId);
        if (course == null) {
            throw new NoSuchElementException("Course with ID " + courseId + " not found");
        }

        if (course.isEnrolled(studentId)) {
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        if (course.isFull()) {
            throw new IllegalStateException("Course is full. Cannot enroll more students");
        }

        course.enrollStudent(studentId);
        return true;
    }

    @Override
    public boolean unenrollStudent(Long courseId, Long studentId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        Course course = courses.get(courseId);
        if (course == null) {
            throw new NoSuchElementException("Course with ID " + courseId + " not found");
        }

        if (!course.isEnrolled(studentId)) {
            throw new IllegalStateException("Student is not enrolled in this course");
        }

        course.unenrollStudent(studentId);
        return true;
    }

    @Override
    public int getEnrollmentCount(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        Course course = courses.get(courseId);
        if (course == null) {
            throw new NoSuchElementException("Course with ID " + courseId + " not found");
        }

        return course.getCurrentEnrollment();
    }

    @Override
    public boolean validateCourse(Course course) {
        if (course == null) {
            return false;
        }

        // Validate required fields
        if (course.getCourseCode() == null || course.getCourseCode().trim().isEmpty()) {
            return false;
        }

        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            return false;
        }

        if (course.getInstructor() == null || course.getInstructor().trim().isEmpty()) {
            return false;
        }

        // Validate credits
        if (course.getCredits() <= 0 || course.getCredits() > 6) {
            return false;
        }

        // Validate max students
        if (course.getMaxStudents() <= 0) {
            return false;
        }

        // Validate dates if provided
        if (course.getStartDate() != null && course.getEndDate() != null) {
            if (course.getStartDate().isAfter(course.getEndDate())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Course> getActiveCourses() {
        return courses.values().stream()
                .filter(Course::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesWithAvailableSpots() {
        return courses.values().stream()
                .filter(course -> !course.isFull() && course.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesByCreditRange(int minCredits, int maxCredits) {
        if (minCredits < 0 || maxCredits < 0 || minCredits > maxCredits) {
            throw new IllegalArgumentException("Invalid credit range");
        }

        return courses.values().stream()
                .filter(course -> course.getCredits() >= minCredits && course.getCredits() <= maxCredits)
                .collect(Collectors.toList());
    }

    // Additional business logic methods
    public List<Course> getCoursesStartingAfter(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return courses.values().stream()
                .filter(course -> course.getStartDate() != null && course.getStartDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesEndingBefore(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return courses.values().stream()
                .filter(course -> course.getEndDate() != null && course.getEndDate().isBefore(date))
                .collect(Collectors.toList());
    }

    public int getTotalCourseCount() {
        return courses.size();
    }

    public void deactivateCourse(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        
        Course course = courses.get(id);
        if (course != null) {
            course.setActive(false);
        }
    }

    public void activateCourse(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        
        Course course = courses.get(id);
        if (course != null) {
            course.setActive(true);
        }
    }
}
