package com.example.beanlifecycle2;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Employee implements InitializingBean, DisposableBean {

    private int id;
    private String posion;

    public void setId(int id) {
        this.id = id;
    }

    public void setPosion(String posion) {
        this.posion = posion;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", posion='" + posion + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("i am in distroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("i am in afterPropertiesSet...");
    }
}
