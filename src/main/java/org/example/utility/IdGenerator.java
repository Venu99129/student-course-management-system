package org.example.utility;

// Utility for generating simple incremental ids for entities
public class IdGenerator {

    // Last issued student id
    private static int studentId = 0;

    // Last issued course id
    private static int courseId = 0;

    // Last issued enrollment id
    private static int enrollmentId = 0;

    // Return next student id (increment and return)
    public static int getStudentId(){
        return ++studentId;
    }

    // Return next course id (increment and return)
    public static int getCourseId(){
        return ++courseId;
    }

    // Return next enrollment id (increment and return)
    public static int getEnrollmentId(){
        return ++enrollmentId;
    }
}
