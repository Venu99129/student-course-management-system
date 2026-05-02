package org.example.utility;

public class IdGenerator {

    private static int studentId = 0;
    private static int courseId = 0;
    private static int enrollmentId = 0;

    public static int getStudentId(){
        return ++studentId;
    }

    public static int getCourseId(){
        return ++courseId;
    }

    public static int getEnrollmentId(){
        return ++enrollmentId;
    }
}
