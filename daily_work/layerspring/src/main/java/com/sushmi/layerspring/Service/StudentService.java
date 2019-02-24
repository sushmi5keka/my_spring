package com.sushmi.layerspring.Service;

import com.sushmi.layerspring.Dto.StudentDto;
import com.sushmi.layerspring.Entity.Student;

import java.util.List;

public interface StudentService {

    void saveOrUpdate(StudentDto studentDto);

    void deleteById(Long id);

    Student findById(Long id);

    Student findByEmailAdd(String email);

    List<StudentDto> getAll();

    long countNoOfStudents(String email);

}
