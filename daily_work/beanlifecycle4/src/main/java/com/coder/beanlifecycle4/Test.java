package com.coder.beanlifecycle4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beanConfig.xml");

        ctx.getBean("awareService", AwareService.class);

        ctx.close();
    }
}
