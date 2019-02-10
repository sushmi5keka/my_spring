package com.ex.form.Entity;


import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "stu")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3,max = 30,message = "must use letter size between 3-30")
    private String name;

    @NotNull(message = "Enter an email Address")
    @Column(name = "email",unique = true)
    private String email;

    @Size(min = 11,message = "use 11 charecter for mobile number")
    private String mobile;

    @NotNull
    @Min(value = 18,message = "minimum age 18")
    private String age;

    @NotNull(message = "enter your gender")
    private String gender;

    @NotNull(message =" enter your courses")
    private String[] courses;

    @NotNull(message = "enter your round")
    private String round;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String[] getCourses() {
        return courses;
    }

    public String getRound() {
        return round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(email, student.email) &&
                Objects.equals(mobile, student.mobile) &&
                Objects.equals(age, student.age) &&
                Objects.equals(gender, student.gender) &&
                Arrays.equals(courses, student.courses) &&
                Objects.equals(round, student.round);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, mobile, age, gender, round);
        result = 31 * result + Arrays.hashCode(courses);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", courses=" + Arrays.toString(courses) +
                ", round='" + round + '\'' +
                '}';
    }
}
