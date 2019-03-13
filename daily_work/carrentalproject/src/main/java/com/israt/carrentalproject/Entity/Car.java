package com.israt.carrentalproject.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int carNumber;

    private String carModel;

    private String color;

    private String noOfSeats;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfManufacture;

    private double farePerDay;

    @ManyToOne
    @JoinColumn(name = "agency_id",nullable = false)
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    public Car() {
    }

    public Car(int carNumber, String carModel, String color, String noOfSeats, Date dateOfManufacture, double farePerDay, Agency agency) {
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.color = color;
        this.noOfSeats = noOfSeats;
        this.dateOfManufacture = dateOfManufacture;
        this.farePerDay = farePerDay;
        this.agency = agency;
        this.category = category;
    }

    public Car(Car car) {
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.color = color;
        this.noOfSeats = noOfSeats;
        this.dateOfManufacture = dateOfManufacture;
        this.farePerDay = farePerDay;
        this.agency = agency;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public double getFarePerDay() {
        return farePerDay;
    }

    public void setFarePerDay(double farePerDay) {
        this.farePerDay = farePerDay;
    }

    public Agency getAgency() {
        return agency;
    }

  public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carNumber == car.carNumber &&
                Double.compare(car.farePerDay, farePerDay) == 0 &&
                Objects.equals(id, car.id) &&
                Objects.equals(carModel, car.carModel) &&
                Objects.equals(color, car.color) &&
                Objects.equals(noOfSeats, car.noOfSeats) &&
                Objects.equals(dateOfManufacture, car.dateOfManufacture) &&
                Objects.equals(agency, car.agency) &&
                Objects.equals(category, car.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carNumber, carModel, color, noOfSeats, dateOfManufacture, farePerDay, agency, category);
    }
}

