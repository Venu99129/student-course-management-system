package org.example.services;

import org.example.entites.Student;
import org.example.exceptions.EmptyContentException;
import org.example.exceptions.EntityNotFoundException;
import org.example.utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> studentList;

    public StudentService(){
        studentList = new ArrayList<>();
    }

    public Student addNewStudent(Student student){
        student.setId(IdGenerator.getStudentId());
        student.setActive(true);

        studentList.add(student);

        return student;
    }

    public List<Student> viewAllStudents()throws EmptyContentException{
        if(studentList.isEmpty()) throw new EmptyContentException("there is no Students added Yet !!");
        return studentList;
    }

    public Student findByStudentId(int id) throws EntityNotFoundException{
        for(Student student: studentList){
            if(student.getId() == id){
                return student;
            }
        }
        throw new EntityNotFoundException(STR."Student not Fount with Id:\{id}");
    }
}
