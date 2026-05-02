package org.example.entites;

import org.example.entites.Enums.Status;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Status status;

    public Enrollment(int enrollmentId, int studentId, int courseId, Status status) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return STR."Enrollment{enrollmentId=\{enrollmentId}, studentId=\{studentId}, courseId=\{courseId}, status=\{status}}";
    }
}
