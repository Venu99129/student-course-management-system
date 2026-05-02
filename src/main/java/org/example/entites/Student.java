package org.example.entites;

public class Student extends Person{

    private int batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, int batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(String firstName, String lastName,String email) {
        super(firstName, lastName,email);
    }



    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return STR."Student{studentId=\{getId()}, firstName=\{getFirstName()}, lastName=\{getLastName()}, email=\{getEmail()}, batch=\{batch}, active=\{active}}";
    }
}
