package org.example.consoleUI;

import org.example.entites.Course;
import org.example.entites.Enrollment;
import org.example.entites.Enums.Status;
import org.example.entites.Student;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.exceptions.UnActiveEntityException;
import org.example.services.CourseService;
import org.example.services.EnrollmentService;
import org.example.services.StudentService;
import java.util.List;
import java.util.Scanner;

// Console-based UI entry point and menu handling
public class Main {
    // Shared scanner for console input
    static Scanner sc = new Scanner(System.in);

    // Application main method and top-level menu loop
    public static void main(String[] args) {
        // Initialize service layer instances
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);


        // Top-level interactive menu
        while (true) {
            System.out.println("\nchoose your option .." +
                    "\n1.student services" +
                    "\n2.course services" +
                    "\n3.enrollment service" +
                    "\n4.exit menu");

            int option = sc.nextInt();
            if (option == 4) break; // Exit the application

            // Dispatch to the selected feature menu
            switch (option) {
                case 1 -> handleStudentServices(studentService);
                case 2 -> handleCourseService(courseService);
                case 3 -> handleEnrollmentService(enrollmentService);
                default -> System.out.println("choose right option ......");
            }
        }
    }

    // Student services menu loop
    public static void handleStudentServices(StudentService studentService) {

        while (true) {
            System.out.println(
                    "\n1.add new Student" +
                            "\n2.findByStudentId" +
                            "\n3.view all students" +
                            "\n4.back to main menu");

            int stuOption = sc.nextInt();
            if (stuOption == 4) return; // Return to main menu

            // Dispatch student menu actions
            switch (stuOption) {
                case 1 -> createStudentWithUI(studentService);
                case 2 -> findStudentById(studentService);
                case 3 -> viewAllStudents(studentService);
                default -> System.out.println("choose right option ......");
            }
        }
    }

    // Gather input and create a student via the service
    public static void createStudentWithUI(StudentService studentService) {
        System.out.println("\nEnter the Student details...");
        System.out.print("First Name :");
        String firstName = sc.next(); // Read first name

        System.out.print("\nSecond Name :");
        String lastName = sc.next(); // Read last name

        System.out.print("\nEmail :");
        String email = sc.next(); // Read email

        // Build student and save via service
        Student newStudent = new Student(firstName, lastName, email);
        Student savedStudent = studentService.addNewStudent(newStudent);

        // Show saved student
        System.out.println(savedStudent);
    }

    // Prompt for id and lookup a student
    public static void findStudentById(StudentService studentService) {

        System.out.print("\nEnter student Id :");
        int studentId = sc.nextInt(); // Read student id
        try {

            Student student = studentService.findByStudentId(studentId); // Lookup
            System.out.println(student); // Print found student

        } catch (EntityNotFoundException exception) {
            // Print error message when not found
            System.out.println(exception.getLocalizedMessage());
        }
    }

    // Display all students or an error if none exist
    public static void viewAllStudents(StudentService studentService) {
        System.out.println();
        try {
            List<Student> students = studentService.viewAllStudents(); // Get list

           for(Student stu: students){
               System.out.println(stu); // Print each student
           }
        } catch (EmptyContentException exception) {
            // Print message when no students are available
            System.out.println(exception.getLocalizedMessage());
        }

    }

    // Course services menu loop
    public static void handleCourseService(CourseService courseService) {
        while (true) {
            System.out.println(
                    "\n1.add new Course" +
                            "\n2.view all Courses" +
                            "\n3.update the Course Status" +
                            "\n4.back to main menu");

            int courseOption = sc.nextInt();
            if (courseOption == 4) return; // Back to main menu

            // Dispatch course menu actions
            switch (courseOption) {
                case 1 -> createCourseWithUI(courseService);
                case 2 -> viewAllCourses(courseService);
                case 3 -> updateCourseStatus(courseService);
                default -> System.out.println("choose right option ......");
            }
        }
    }


    // Gather course input and add via service
    public static void createCourseWithUI(CourseService courseService) {

        System.out.print("\nEnter the Course Details....." +
                "\nCourse Name :");
        String courseName = sc.nextLine();
        courseName = sc.nextLine(); // Read course name (consume newline)

        System.out.print("\nCourse Description :");
        String description = sc.nextLine(); // Read description

        System.out.print("\nDuration in weeks :");
        int duration = sc.nextInt(); // Read duration

        // Build and save course
        Course course = new Course(courseName,description,duration);
        Course savedCourse = courseService.addNewCourse(course);

        // Show saved course
        System.out.println(savedCourse);
    }

    // Display all courses or print message when none
    public static void viewAllCourses(CourseService courseServices) {
        try {
            List<Course> courses = courseServices.viewAllCourses(); // Fetch courses

            for(Course course: courses){
                System.out.println(course); // Print each course
            }

        } catch (EmptyContentException e) {
            // Print no-courses message
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Prompt to update course active status
    private static void updateCourseStatus(CourseService courseService) {
        try {
            System.out.print("\nCourse Id :");
            int courseId = sc.nextInt(); // Read course id

            courseService.findByCourseId(courseId); // Validate exists

            System.out.print("\nCourse Active status (true/false) :");
            boolean status = sc.nextBoolean(); // Read status

            Course modified = courseService.modifyStatus(courseId,status); // Update

            System.out.println("\n modified course");
            System.out.println(modified); // Show modified course

        }catch (EntityNotFoundException e){
            // Print not-found message
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Enrollment services menu loop
    public static void handleEnrollmentService(EnrollmentService enrollmentService) {
        while (true) {
            System.out.println(
                    "\n1.Student Enroll to Course" +
                            "\n2.view all Enrolments student" +
                            "\n3.modify the Enrollment status" +
                            "\n4.back to main menu");

            int enrollOption = sc.nextInt();
            if (enrollOption == 4) return; // Back to main menu

            // Dispatch enrollment actions
            switch (enrollOption) {
                case 1 -> studentEnrollCourse(enrollmentService);
                case 2 -> findEnrollmentsByStudentId(enrollmentService);
                case 3 -> modifyEnrollmentStatus(enrollmentService);
                default -> System.out.println("choose right option ......");
            }
        }
    }


    // Read enrollment details and create enrollment
    private static void studentEnrollCourse(EnrollmentService enrollmentService) {

        try {
            System.out.print("\nprovide the Enrolment Details " +
                    "\nstudent Id :");
            int studentId = sc.nextInt(); // Read student id

            System.out.print("\ncourse Id :");
            int courseId = sc.nextInt(); // Read course id

            System.out.print("\nBatch Id :");
            int batchId = sc.nextInt(); // Read batch id

            // Create enrollment via service
            Enrollment savedEnrollment = enrollmentService.enrollStudentWithCourse(studentId,courseId,batchId);

            // Print saved enrollment
            System.out.println(savedEnrollment);

        }catch (EntityNotFoundException | UnActiveEntityException e){
            // Print validation errors
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Lookup and print all enrollments for a student
    private static void findEnrollmentsByStudentId(EnrollmentService enrollmentService) {
        try {
            System.out.print("\nStudent Id:");
            int studentId = sc.nextInt(); // Read student id

            List<Enrollment> enrollments = enrollmentService.viewStudentEnrolments(studentId); // Fetch enrollments

            for(Enrollment enr: enrollments){
                System.out.println(enr); // Print each enrollment
            }

        }catch (EmptyContentException e){
            // Print message when no enrollments
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Prompt for enrollment id and modify its status
    private static void modifyEnrollmentStatus(EnrollmentService enrollmentService) {
        try {
            System.out.print("\nStudent Id:");
            int studentId = sc.nextInt(); // Read student id

            System.out.print("\nEnrollment Id :");
            int enrollmentId = sc.nextInt(); // Read enrollment id

            System.out.print("\nStatus (COMPLETED/CANCELLED) :");
            String status = sc.next();
            status = status.toUpperCase(); // Normalize input

            Status modify = Status.valueOf(status); // Parse enum

            Enrollment modifiedEnrollment = enrollmentService.modifyStatus(studentId,enrollmentId,modify); // Update

            System.out.println(modifiedEnrollment); // Print updated enrollment

        }catch (EmptyContentException e){
            // Print error when update fails
            System.out.println(e.getLocalizedMessage());
        }
    }

}
