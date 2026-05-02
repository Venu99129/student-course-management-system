package org.example.entites;

// Student entity that extends Person with batch and active flag
public class Student extends Person{

    // Student cohort or batch number
    private int batch;

    // Whether the student is currently active
    private boolean active;

    // Constructor with all properties including id and active flag
    public Student(int id, String firstName, String lastName, String email, int batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    // Constructor for new student without id (id assigned later)
    public Student(String firstName, String lastName,String email) {
        super(firstName, lastName,email);
    }

    // Returns the student's batch
    public int getBatch() {
        return batch;
    }

    // Sets the student's batch
    public void setBatch(int batch) {
        this.batch = batch;
    }

    // Returns whether the student is active
    public boolean isActive() {
        return active;
    }

    // Sets the student's active status
    public void setActive(boolean active) {
        this.active = active;
    }

    // Returns a string representation of the student
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
