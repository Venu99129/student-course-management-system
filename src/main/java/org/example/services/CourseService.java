package org.example.services;

import org.example.entites.Course;
import org.example.entites.Enums.Status;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

// Service managing Course entities in memory
public class CourseService {

    // In-memory list storing courses
    private List<Course> courseList;

    // Initialize the service and internal storage
    public CourseService(){
        courseList = new ArrayList<>();
    }

    // Add a new course, assign id and mark as active
    public Course addNewCourse(Course course){
        course.setCourseId(IdGenerator.getCourseId());
        course.setActive(true);
        courseList.add(course);
        return course;
    }


    // Return all courses or throw if none available
    public List<Course> viewAllCourses()throws EmptyContentException{
        if(courseList.isEmpty()) throw new EmptyContentException("there is no Courses are Available !!");
        return courseList;
    }

    // Find a course by id or throw if not found
    public Course findByCourseId(int courseId) throws EntityNotFoundException{
        for(Course course: courseList){
            if(course.getCourseId() == courseId){
                return course;
            }
        }
        throw new EntityNotFoundException("Course not Fount with Id:"+courseId);
    }

    // Update the active status of a course and return it
    public Course modifyStatus(int courseId,boolean status) throws EntityNotFoundException {
        Course course = findByCourseId(courseId);
        course.setActive(status);

        return course;
    }
}
