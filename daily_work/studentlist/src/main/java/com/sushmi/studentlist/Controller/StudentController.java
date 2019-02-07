package com.sushmi.studentlist.Controller;


import com.sushmi.studentlist.Entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    static List<Student> list;

    static {
        list = new ArrayList<>();
        list.add(new Student(1,"Ria","MBBS"));
        list.add(new Student(2,"pia","BBA"));
        list.add(new Student(3,"jia","MBA"));
        list.add(new Student(4,"mia","CA"));
        list.add(new Student(5,"tia","BCS"));
    }


    @GetMapping(value = "/studentslist")
    public List<Student> students(){

        return list;
    }

    public Student getStudentById(@PathVariable("id") int id){
        Student student = null;
        for (Student s : this.list){

            if(id == s.getId()){
                student = new Student();
                student = new Student(s.getId(),s.getName(),s.getCourse());
                break;
            }
        }
        return student;
    }
}
