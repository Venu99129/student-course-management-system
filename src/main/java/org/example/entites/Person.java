package org.example.entites;

// Simple data holder for a person record.
public class Person {

    // Unique identifier for the person (e.g. database primary key).
    private int id;

    // Person's first name.
    private String firstName;

    // Person's last name.
    private String lastName;

    // Contact email address (may be null).
    private String email;

    // Full constructor including id.
    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Constructor without id and email.
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor without id but with email.
    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Returns the person's id.
    public int getId() {
        return id;
    }

    // Sets the person's id.
    public void setId(int id) {
        this.id = id;
    }

    // Returns the first name.
    public String getFirstName() {
        return firstName;
    }

    // Sets the first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the last name.
    public String getLastName() {
        return lastName;
    }

    // Sets the last name.
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Returns the email address.
    public String getEmail() {
        return email;
    }

    // Sets the email address.
    public void setEmail(String email) {
        this.email = email;
    }
}