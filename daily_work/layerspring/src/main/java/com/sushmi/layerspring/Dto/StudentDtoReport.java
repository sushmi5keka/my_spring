package com.sushmi.layerspring.Dto;

import com.sushmi.layerspring.Entity.Student;

public class StudentDtoReport {

    private  long noOfStudents;
    private String email;

    public StudentDtoReport() {
    }

    public StudentDtoReport(long noOfStudents, String email) {
        this.noOfStudents = noOfStudents;
        this.email = email;
    }

    public long getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(long noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
