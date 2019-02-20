package com.example.springthridproject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean(name = "student")
    public Student getStudent() {

        Student student = new Student(100, "moly");
        return student;

    }

    @Bean(name = "student1")
    public Student getStudentBySetter() {
        Student student = new Student();
        student.setId(1001);
        student.setName("Mr. Bean");
        return student;
    }

}
