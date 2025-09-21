package com.rca.demo_course.domain;

import java.util.List;

/**
 * Domain model representing a Student entity.
 * Contains student information and academic data.
 */
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String studentId;
    private String department;
    private List<Double> grades;
    private double gpa;
    private boolean active;

    // Default constructor
    public Student() {
    }

    // Constructor with basic fields
    public Student(String firstName, String lastName, String email, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.active = true;
    }

    // Constructor with all fields
    public Student(Long id, String firstName, String lastName, String email, 
                   String phoneNumber, String studentId, String department, 
                   List<Double> grades, double gpa, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentId = studentId;
        this.department = department;
        this.grades = grades;
        this.gpa = gpa;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Utility methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addGrade(double grade) {
        if (grades != null) {
            grades.add(grade);
            calculateGPA();
        }
    }

    public void calculateGPA() {
        if (grades != null && !grades.isEmpty()) {
            double sum = grades.stream().mapToDouble(Double::doubleValue).sum();
            this.gpa = sum / grades.size();
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                ", gpa=" + gpa +
                ", active=" + active +
                '}';
    }
}
