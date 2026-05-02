package org.example.entites;

// Represents a course with id, name, description, duration and active flag
public class Course {

    // Unique course identifier
    private int courseId;

    // Human-readable course name
    private String courseName;

    // Brief description of the course
    private String description;

    // Duration of the course in weeks
    private int durationInWeeks;

    // Whether the course is currently available
    private boolean active;

    // Constructor to create a new course (id assigned later)
    public Course(String courseName, String description, int durationInWeeks) {
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
    }

    // Returns the course id
    public int getCourseId() {
        return courseId;
    }

    // Sets the course id
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    // Returns the course name
    public String getCourseName() {
        return courseName;
    }

    // Sets the course name
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Returns the course description
    public String getDescription() {
        return description;
    }

    // Sets the course description
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns the course duration in weeks
    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    // Sets the course duration in weeks
    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    // Returns whether the course is active
    public boolean isActive() {
        return active;
    }

    // Sets the course active flag
    public void setActive(boolean active) {
        this.active = active;
    }

    // String representation of the course
    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", durationInWeeks=" + durationInWeeks +
                ", active=" + active +
                '}';
    }
}
