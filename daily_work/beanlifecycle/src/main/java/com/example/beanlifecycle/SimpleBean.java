package com.example.beanlifecycle;

public class SimpleBean {

    private final String DEFULT_NAME="moli";

    private String name = null;

    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "DEFULT_NAME='" + DEFULT_NAME + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public  void  inti(){
        System.out.println("Initializing bean");
        if(name== null){
            System.out.println("Using default name");
            name = DEFULT_NAME;
        }
        if (age == Integer.MIN_VALUE){
            throw new IllegalArgumentException("you must set the age property of habijabi...");
        }
    }

}
