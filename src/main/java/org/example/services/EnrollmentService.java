package org.example.services;

import org.example.entites.Course;
import org.example.entites.Enrollment;
import org.example.entites.Enums.Status;
import org.example.entites.Student;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.exceptions.UnActiveEntityException;
import org.example.utility.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Service that manages student enrollments in courses
public class EnrollmentService {

    // Map from studentId to list of enrollments
    private Map<Integer,List<Enrollment>> enrollmentsMap;

    // Dependency to manage students
    private StudentService studentService;

    // Dependency to manage courses
    private CourseService courseService;

    // Constructor injecting required services and initializing storage
    public EnrollmentService(StudentService studentService,CourseService courseService) {
        enrollmentsMap = new HashMap<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Enroll a student into a course after validations
    public Enrollment enrollStudentWithCourse(int studentId,int courseId,int batchNo) throws EntityNotFoundException, UnActiveEntityException {
        // Lookup student and ensure active
        Student student = studentService.findByStudentId(studentId);
        if(!student.isActive())
            throw new UnActiveEntityException("the student is Inactive we cant assign to any course.....");

        // Update student's batch
        student.setBatch(batchNo);

        // Lookup course and ensure active
        Course course = courseService.findByCourseId(courseId);
        if(!course.isActive()){
            throw new UnActiveEntityException("the course is Inactive we cannot assign the course to student....");
        }

        // Create a new enrollment and add it to the student's list
        Enrollment enrollment = new Enrollment(IdGenerator.getEnrollmentId(),studentId,courseId, Status.ACTIVE);
        if(enrollmentsMap.containsKey(studentId)){
            List<Enrollment> enrollmentList = enrollmentsMap.get(studentId);
            enrollmentList.add(enrollment);
            enrollmentsMap.put(studentId,enrollmentList);
        }
        else {
            List<Enrollment> enrollmentList = new ArrayList<>();
            enrollmentList.add(enrollment);
            enrollmentsMap.put(studentId, enrollmentList);
        }
        return enrollment;
    }

    // Return all enrollments for a given student or throw if none
    public List<Enrollment> viewStudentEnrolments(int studentId) throws EmptyContentException {
        if(enrollmentsMap.containsKey(studentId)){
            return enrollmentsMap.get(studentId);
        }

        throw new EmptyContentException("Student not Enrolled any Courses !!!!");
    }

    // Modify the status of a specific enrollment for a student
    public Enrollment modifyStatus(int studentId, int enrollmentId, Status status) throws EmptyContentException {
        if(enrollmentsMap.containsKey(studentId)){
            List<Enrollment> enrollmentList = enrollmentsMap.get(studentId);
            for(Enrollment enroll: enrollmentList){
                if(enroll.getEnrollmentId() == enrollmentId){
                    enroll.setStatus(status);
                    enrollmentsMap.put(studentId,enrollmentList);
                    return enroll;
                }
            }

            // Enrollment id not found for this student
            throw new EmptyContentException("Student not enrolled this course :"+enrollmentId);
        }

        // Student has no enrollments
        throw new EmptyContentException("Student not Enrolled any Courses !!!!");
    }


}
