package com.example.springthridproject;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);

        Student student = (Student) context.getBean("student");
        System.out.println(student);


        Student student1 = (Student) context.getBean("student1");
        System.out.println(student1);

        }



}


