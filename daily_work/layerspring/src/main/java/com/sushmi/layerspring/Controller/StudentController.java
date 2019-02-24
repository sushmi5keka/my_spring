package com.sushmi.layerspring.Controller;

import com.sushmi.layerspring.Dto.StudentDto;
import com.sushmi.layerspring.Dto.StudentDtoReport;
import com.sushmi.layerspring.Entity.Student;
import com.sushmi.layerspring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/test/{id}")
    public Student getStudent(@PathVariable("id") Long id){
        return studentService.findById(id);
    }

    @GetMapping(value = "/list")
    public List<StudentDto> getStudentDtoList(){
        return studentService.getAll();
    }

    @GetMapping(value = "/report1")
    public List<StudentDtoReport> getReport(){
        Map<String,Long> maps = new HashMap<>();
        for (StudentDto studentDto : studentService.getAll()){
            maps.put(studentDto.getEmail(),studentService.countNoOfStudents(studentDto.getEmail()));

        }

        List<StudentDtoReport> list=new ArrayList<>();
        for(Map.Entry<String,Long> s : maps.entrySet()){
            list.add(new StudentDtoReport(s.getValue(),s.getKey()));

        }
        return  list;

    }

    @GetMapping(value = "/report2")
    public Map<String,Long> getReport2(){
        Map<String,Long> maps = new HashMap<>();
        for (StudentDto studentDto : studentService.getAll()){
            maps.put(studentDto.getEmail(),studentService.countNoOfStudents(studentDto.getEmail()));
        }
        return maps;
    }

}
