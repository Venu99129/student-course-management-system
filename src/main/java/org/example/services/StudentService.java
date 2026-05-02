package org.example.services;

import org.example.entites.Student;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

// Service managing Student entities in memory
public class StudentService {

    // In-memory list storing student records
    private List<Student> studentList;

    // Initialize the service and internal storage
    public StudentService(){
        studentList = new ArrayList<>();
    }

    // Add a new student, assign id and mark as active
    public Student addNewStudent(Student student){
        student.setId(IdGenerator.getStudentId());
        student.setActive(true);

        studentList.add(student);

        return student;
    }

    // Return all students or throw if none available
    public List<Student> viewAllStudents()throws EmptyContentException{
        if(studentList.isEmpty()) throw new EmptyContentException("there is no Students added Yet !!");
        return studentList;
    }

    // Find a student by id or throw if not found
    public Student findByStudentId(int id) throws EntityNotFoundException{
        for(Student student: studentList){
            if(student.getId() == id){
                return student;
            }
        }
        throw new EntityNotFoundException("Student not Fount with Id:"+id);
    }
}
