package com.ex.beanlifecycle3;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beanConfig.xml");

        Employee emp = (Employee) context.getBean("em");
        System.out.println(emp);



        Employee emp1 = (Employee) context.getBean("em1");
        System.out.println(emp1);
        context.close();
    }
}
