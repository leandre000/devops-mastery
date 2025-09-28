package com.rca.demo_course.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Domain model representing a Course entity.
 * Contains course information and academic data.
 */
public class Course {

    private Long id;
    private String courseCode;
    private String courseName;
    private String description;
    private String instructor;
    private int credits;
    private String department;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxStudents;
    private List<Long> enrolledStudents;
    private boolean active;

    // Default constructor
    public Course() {
    }

    // Constructor with basic fields
    public Course(String courseCode, String courseName, String instructor, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.active = true;
    }

    // Constructor with all fields
    public Course(Long id, String courseCode, String courseName, String description,
                  String instructor, int credits, String department, LocalDate startDate,
                  LocalDate endDate, int maxStudents, List<Long> enrolledStudents, boolean active) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.credits = credits;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudents = maxStudents;
        this.enrolledStudents = enrolledStudents;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Long> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Long> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Utility methods
    public String getFullCourseInfo() {
        return courseCode + " - " + courseName + " (" + credits + " credits)";
    }

    public void enrollStudent(Long studentId) {
        if (enrolledStudents != null && !enrolledStudents.contains(studentId)) {
            if (enrolledStudents.size() < maxStudents) {
                enrolledStudents.add(studentId);
            } else {
                throw new IllegalStateException("Course is full. Cannot enroll more students.");
            }
        }
    }

    public void unenrollStudent(Long studentId) {
        if (enrolledStudents != null) {
            enrolledStudents.remove(studentId);
        }
    }

    public int getCurrentEnrollment() {
        return enrolledStudents != null ? enrolledStudents.size() : 0;
    }

    public boolean isFull() {
        return getCurrentEnrollment() >= maxStudents;
    }

    public boolean isEnrolled(Long studentId) {
        return enrolledStudents != null && enrolledStudents.contains(studentId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", instructor='" + instructor + '\'' +
                ", credits=" + credits +
                ", department='" + department + '\'' +
                ", maxStudents=" + maxStudents +
                ", active=" + active +
                '}';
    }
}
