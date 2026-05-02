package org.example.entites;

import org.example.entites.Enums.Status;

// Represents an enrollment linking a student to a course with a status
public class Enrollment {

    // Unique enrollment identifier
    private int enrollmentId;

    // Associated student identifier
    private int studentId;

    // Associated course identifier
    private int courseId;

    // Current enrollment status
    private Status status;

    // Full constructor to create an Enrollment instance
    public Enrollment(int enrollmentId, int studentId, int courseId, Status status) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    // Returns the enrollment id
    public int getEnrollmentId() {
        return enrollmentId;
    }

    // Sets the enrollment id
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    // Returns the student id
    public int getStudentId() {
        return studentId;
    }

    // Sets the student id
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Returns the course id
    public int getCourseId() {
        return courseId;
    }

    // Sets the course id
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    // Returns the enrollment status
    public Status getStatus() {
        return status;
    }

    // Sets the enrollment status
    public void setStatus(Status status) {
        this.status = status;
    }

    // Returns a string representation of the enrollment
    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", status=" + status +
                '}';
    }
}
