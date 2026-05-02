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

public class EnrollmentService {
    private Map<Integer,List<Enrollment>> enrollmentsMap;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService,CourseService courseService) {
        enrollmentsMap = new HashMap<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudentWithCourse(int studentId,int courseId,int batchNo) throws EntityNotFoundException, UnActiveEntityException {
        Student student = studentService.findByStudentId(studentId);
        if(!student.isActive())
            throw new UnActiveEntityException("the student is Inactive we cant assign to any course.....");

        student.setBatch(batchNo);

        Course course = courseService.findByCourseId(courseId);
        if(!course.isActive()){
            throw new UnActiveEntityException("the course is Inactive we cannot assign the course to student....");
        }

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

    public List<Enrollment> viewStudentEnrolments(int studentId) throws EmptyContentException {
        if(enrollmentsMap.containsKey(studentId)){
            return enrollmentsMap.get(studentId);
        }

        throw new EmptyContentException("Student not Enrolled any Courses !!!!");
    }

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

            throw new EmptyContentException(STR."Student not enrolled this course :\{enrollmentId}");
        }

        throw new EmptyContentException("Student not Enrolled any Courses !!!!");
    }


}
