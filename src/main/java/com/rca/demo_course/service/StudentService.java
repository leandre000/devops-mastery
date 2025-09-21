package com.rca.demo_course.service;

import com.rca.demo_course.domain.Student;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for student operations.
 * Provides CRUD operations and business logic for student management.
 */
public interface StudentService {

    /**
     * Creates a new student.
     *
     * @param student the student to create
     * @return the created student
     */
    Student createStudent(Student student);

    /**
     * Retrieves a student by ID.
     *
     * @param id the student ID
     * @return an Optional containing the student if found
     */
    Optional<Student> getStudentById(Long id);

    /**
     * Retrieves all students.
     *
     * @return a list of all students
     */
    List<Student> getAllStudents();

    /**
     * Updates an existing student.
     *
     * @param id the student ID
     * @param student the updated student data
     * @return the updated student
     */
    Student updateStudent(Long id, Student student);

    /**
     * Deletes a student by ID.
     *
     * @param id the student ID
     * @return true if the student was deleted, false otherwise
     */
    boolean deleteStudent(Long id);

    /**
     * Finds students by name.
     *
     * @param name the name to search for
     * @return a list of students matching the name
     */
    List<Student> findStudentsByName(String name);

    /**
     * Finds students by email.
     *
     * @param email the email to search for
     * @return a list of students matching the email
     */
    List<Student> findStudentsByEmail(String email);

    /**
     * Calculates the average grade for a student.
     *
     * @param studentId the student ID
     * @return the average grade
     */
    double calculateAverageGrade(Long studentId);

    /**
     * Validates student data.
     *
     * @param student the student to validate
     * @return true if the student data is valid
     */
    boolean validateStudent(Student student);
}
