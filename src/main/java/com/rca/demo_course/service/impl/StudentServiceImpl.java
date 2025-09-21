package com.rca.demo_course.service.impl;

import com.rca.demo_course.domain.Student;
import com.rca.demo_course.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of StudentService interface.
 * Provides business logic for student management operations.
 */
@Service
public class StudentServiceImpl implements StudentService {

    // In-memory storage for demo purposes (in real application, this would be a database)
    private final Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Student createStudent(Student student) {
        if (!validateStudent(student)) {
            throw new IllegalArgumentException("Invalid student data");
        }

        student.setId(nextId++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if (!validateStudent(student)) {
            throw new IllegalArgumentException("Invalid student data");
        }

        Student existingStudent = students.get(id);
        if (existingStudent == null) {
            throw new NoSuchElementException("Student with ID " + id + " not found");
        }

        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return students.remove(id) != null;
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return students.values().stream()
                .filter(student -> student.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                        student.getLastName().toLowerCase().contains(name.toLowerCase()) ||
                        student.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsByEmail(String email) {
        return students.values().stream()
                .filter(student -> student.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public double calculateAverageGrade(Long studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new NoSuchElementException("Student with ID " + studentId + " not found");
        }

        List<Double> grades = student.getGrades();
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public boolean validateStudent(Student student) {
        if (student == null) {
            return false;
        }

        // Validate required fields
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            return false;
        }

        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            return false;
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return false;
        }

        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return false;
        }

        // Validate email format (basic validation)
        if (!student.getEmail().contains("@")) {
            return false;
        }

        // Validate GPA range
        if (student.getGpa() < 0.0 || student.getGpa() > 4.0) {
            return false;
        }

        return true;
    }

    // Additional business logic methods
    public List<Student> getActiveStudents() {
        return students.values().stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByDepartment(String department) {
        return students.values().stream()
                .filter(student -> department.equalsIgnoreCase(student.getDepartment()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWithGPAAbove(double minGPA) {
        return students.values().stream()
                .filter(student -> student.getGpa() >= minGPA)
                .collect(Collectors.toList());
    }

    public int getTotalStudentCount() {
        return students.size();
    }

    public void deactivateStudent(Long id) {
        Student student = students.get(id);
        if (student != null) {
            student.setActive(false);
        }
    }

    public void activateStudent(Long id) {
        Student student = students.get(id);
        if (student != null) {
            student.setActive(true);
        }
    }
}
