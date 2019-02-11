package com.ex.form.Entity;


import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Date;
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

    @NotEmpty(message = "Enter an email Address")
    @Email
    @Column(name = "email",unique = true)
    private String email;

    @Size(min = 11,message = "use 11 charecter for mobile number")
    private String mobile;

    @NotNull
    @Min(value = 18,message = "minimum age 18")
    private String age;

    @NotNull(message = "enter your gender")
    private String gender;

    @NotEmpty(message =" enter your courses")
    private String[] courses;

    @NotNull(message = "enter your round")
    private String round;

    @NotNull(message =" enter your birthdate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regiDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate= new Date();



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

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegiDate(Date regiDate) {
        this.regiDate = regiDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getRegiDate() {
        return regiDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
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
                Objects.equals(round, student.round) &&
                Objects.equals(birthDate, student.birthDate) &&
                Objects.equals(regiDate, student.regiDate) &&
                Objects.equals(lastModifiedDate, student.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, mobile, age, gender, round, birthDate, regiDate, lastModifiedDate);
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
                ", birthDate=" + birthDate +
                ", regiDate=" + regiDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
