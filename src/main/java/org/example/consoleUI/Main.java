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

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);


        while (true) {
            System.out.println("\nchoose your option .." +
                    "\n1.student services" +
                    "\n2.course services" +
                    "\n3.enrollment service" +
                    "\n4.exit menu");

            int option = sc.nextInt();
            if (option == 4) break;

            switch (option) {
                case 1 -> handleStudentServices(studentService);
                case 2 -> handleCourseService(courseService);
                case 3 -> handleEnrollmentService(enrollmentService);
                default -> System.out.println("choose right option .....");
            }
        }
    }

    public static void handleStudentServices(StudentService studentService) {

        while (true) {
            System.out.println(
                    "\n1.add new Student" +
                            "\n2.findByStudentId" +
                            "\n3.view all students" +
                            "\n4.back to main menu");

            int stuOption = sc.nextInt();
            if (stuOption == 4) return;

            switch (stuOption) {
                case 1 -> createStudentWithUI(studentService);
                case 2 -> findStudentById(studentService);
                case 3 -> viewAllStudents(studentService);
                default -> System.out.println("choose right option .....");
            }
        }
    }

    public static void createStudentWithUI(StudentService studentService) {
        System.out.println("\nEnter the Student details...");
        System.out.print("First Name :");
        String firstName = sc.next();

        System.out.print("\nSecond Name :");
        String lastName = sc.next();

        System.out.print("\nEmail :");
        String email = sc.next();

        Student newStudent = new Student(firstName, lastName, email);
        Student savedStudent = studentService.addNewStudent(newStudent);

        System.out.println(savedStudent);
    }

    public static void findStudentById(StudentService studentService) {

        System.out.print("\nEnter student Id :");
        int studentId = sc.nextInt();
        try {

            Student student = studentService.findByStudentId(studentId);
            System.out.println(student);

        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }

    public static void viewAllStudents(StudentService studentService) {
        System.out.println();
        try {
            List<Student> students = studentService.viewAllStudents();

           for(Student stu: students){
               System.out.println(stu);
           }
        } catch (EmptyContentException exception) {
            System.out.println(exception.getLocalizedMessage());
        }

    }

    public static void handleCourseService(CourseService courseService) {
        while (true) {
            System.out.println(
                    "\n1.add new Course" +
                            "\n2.view all Courses" +
                            "\n3.update the Course Status" +
                            "\n4.back to main menu");

            int courseOption = sc.nextInt();
            if (courseOption == 4) return;

            switch (courseOption) {
                case 1 -> createCourseWithUI(courseService);
                case 2 -> viewAllCourses(courseService);
                case 3 -> updateCourseStatus(courseService);
                default -> System.out.println("choose right option .....");
            }
        }
    }



    public static void createCourseWithUI(CourseService courseService) {

        System.out.print("\nEnter the Course Details....." +
                "\nCourse Name :");
        String courseName = sc.nextLine();
        courseName = sc.nextLine();

        System.out.print("\nCourse Description :");
        String description = sc.nextLine();

        System.out.print("\nDuration in weeks :");
        int duration = sc.nextInt();

        Course course = new Course(courseName,description,duration);
        Course savedCourse = courseService.addNewCourse(course);

        System.out.println(savedCourse);
    }

    public static void viewAllCourses(CourseService courseServices) {
        try {
            List<Course> courses = courseServices.viewAllCourses();

            for(Course course: courses){
                System.out.println(course);
            }

        } catch (EmptyContentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void updateCourseStatus(CourseService courseService) {
        try {
            System.out.print("\nCourse Id :");
            int courseId = sc.nextInt();

            courseService.findByCourseId(courseId);

            System.out.print("\nCourse Active status (true/false) :");
            boolean status = sc.nextBoolean();

            Course modified = courseService.modifyStatus(courseId,status);

            System.out.println("\n modified course");
            System.out.println(modified);

        }catch (EntityNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void handleEnrollmentService(EnrollmentService enrollmentService) {
        while (true) {
            System.out.println(
                    "\n1.Student Enroll to Course" +
                            "\n2.view all Enrolments student" +
                            "\n3.modify the Enrollment status" +
                            "\n4.back to main menu");

            int enrollOption = sc.nextInt();
            if (enrollOption == 4) return;

            switch (enrollOption) {
                case 1 -> studentEnrollCourse(enrollmentService);
                case 2 -> findEnrollmentsByStudentId(enrollmentService);
                case 3 -> modifyEnrollmentStatus(enrollmentService);
                default -> System.out.println("choose right option .....");
            }
        }
    }


    private static void studentEnrollCourse(EnrollmentService enrollmentService) {

        try {
            System.out.print("\nprovide the Enrolment Details " +
                    "\nstudent Id :");
            int studentId = sc.nextInt();

            System.out.print("\ncourse Id :");
            int courseId = sc.nextInt();

            System.out.print("\nBatch Id :");
            int batchId = sc.nextInt();

            Enrollment savedEnrollment = enrollmentService.enrollStudentWithCourse(studentId,courseId,batchId);

            System.out.println(savedEnrollment);

        }catch (EntityNotFoundException | UnActiveEntityException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void findEnrollmentsByStudentId(EnrollmentService enrollmentService) {
        try {
            System.out.print("\nStudent Id:");
            int studentId = sc.nextInt();

            List<Enrollment> enrollments = enrollmentService.viewStudentEnrolments(studentId);

            for(Enrollment enr: enrollments){
                System.out.println(enr);
            }

        }catch (EmptyContentException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void modifyEnrollmentStatus(EnrollmentService enrollmentService) {
        try {
            System.out.print("\nStudent Id:");
            int studentId = sc.nextInt();

            System.out.print("\nEnrollment Id :");
            int enrollmentId = sc.nextInt();

            System.out.print("\nStatus (COMPLETED/CANCELLED) :");
            String status = sc.next();
            status = status.toUpperCase();

            Status modify = Status.valueOf(status);

            Enrollment modifiedEnrollment = enrollmentService.modifyStatus(studentId,enrollmentId,modify);

            System.out.println(modifiedEnrollment);

        }catch (EmptyContentException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

}
