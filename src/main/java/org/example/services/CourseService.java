package org.example.services;

import org.example.entites.Course;
import org.example.entites.Enums.Status;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private List<Course> courseList;

    public CourseService(){
        courseList = new ArrayList<>();
    }

    public Course addNewCourse(Course course){
        course.setCourseId(IdGenerator.getCourseId());
        course.setActive(true);
        courseList.add(course);
        return course;
    }


    public List<Course> viewAllCourses()throws EmptyContentException{
        if(courseList.isEmpty()) throw new EmptyContentException("there is no Courses are Available !!");
        return courseList;
    }

    public Course findByCourseId(int courseId) throws EntityNotFoundException{
        for(Course course: courseList){
            if(course.getCourseId() == courseId){
                return course;
            }
        }
        throw new EntityNotFoundException(STR."Course not Fount with Id:\{courseId}");
    }

    public Course modifyStatus(int courseId,boolean status) throws EntityNotFoundException {
        Course course = findByCourseId(courseId);
        course.setActive(status);

        return course;
    }
}
