package com.israt.carrentalproject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactUs() {
    }

    public ContactUs(String message) {
        this.message = message;
    }

    public ContactUs(ContactUs contactUs) {
        this.message = message;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactUs contactUs = (ContactUs) o;
        return Objects.equals(id, contactUs.id) &&
                Objects.equals(message, contactUs.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}
