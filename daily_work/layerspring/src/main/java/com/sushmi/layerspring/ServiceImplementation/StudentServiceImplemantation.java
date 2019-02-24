package com.sushmi.layerspring.ServiceImplementation;

import com.sushmi.layerspring.Dto.StudentDto;
import com.sushmi.layerspring.Entity.Student;
import com.sushmi.layerspring.Repository.StudentRepository;
import com.sushmi.layerspring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImplemantation implements StudentService {

    @Autowired
    private StudentRepository repository;


    public Student convertDtoToEntity(StudentDto dto){
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        return student;
    }

    public StudentDto convertEntityToDto(Student student){
        StudentDto dtoo = new StudentDto();
        dtoo.setId(student.getId());
        dtoo.setName(student.getName());
        dtoo.setEmail(student.getEmail());
        return dtoo;
    }


    @Override
    public void saveOrUpdate(StudentDto studentDto) {
        repository.save(convertDtoToEntity(studentDto));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Student findByEmailAdd(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<StudentDto> getAll() {
        List<StudentDto> dtos = new ArrayList<>();
        List<Student> list = repository.findAll();

        for (Student s : list){
            dtos.add(convertEntityToDto(s));
        }
        return dtos;
    }

    @Override
    public long countNoOfStudents(String email) {
        return repository.countAllByEmail(email);
    }
}
