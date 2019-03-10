package com.israt.carrentalproject.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile;

    private String email;

    private String ownername;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Agency() {
    }

    public Agency(String name, String mobile, String email, String ownername, Address address) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.ownername = ownername;
        this.address = address;
    }

    public Agency(Agency agency) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.ownername = ownername;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(id, agency.id) &&
                Objects.equals(name, agency.name) &&
                Objects.equals(mobile, agency.mobile) &&
                Objects.equals(email, agency.email) &&
                Objects.equals(ownername, agency.ownername) &&
                Objects.equals(address, agency.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mobile, email, ownername, address);
    }
}
