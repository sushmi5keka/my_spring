package com.israt.carrentalproject.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerMobile;

    private String customerEmail;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookingdate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    private int noOfDays;

    private double farePerDay;

    private double totalFareAmount;

    private double advanceFareAmount;

    private double dueFareAmount;


    @ManyToMany
    @JoinTable(
            name = "booking_car",
            joinColumns = @JoinColumn(name = "booking_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "car_id",nullable = false))
    //,nullable = false................(if there was a problem,then delete this)
    private Set<Car> car;


    public Booking() {
    }

    public Booking(String customerName, String customerMobile, String customerEmail, Date bookingdate, Date returnDate, int noOfDays, double farePerDay, double totalFareAmount, double advanceFareAmount, double dueFareAmount, Set<Car> car) {
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.bookingdate = bookingdate;
        this.returnDate = returnDate;
        this.noOfDays = noOfDays;
        this.farePerDay = farePerDay;
        this.totalFareAmount = totalFareAmount;
        this.advanceFareAmount = advanceFareAmount;
        this.dueFareAmount = dueFareAmount;
        this.car = car;
    }

    public Booking(Booking booking) {
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.bookingdate = bookingdate;
        this.returnDate = returnDate;
        this.noOfDays = noOfDays;
        this.farePerDay = farePerDay;
        this.totalFareAmount = totalFareAmount;
        this.advanceFareAmount = advanceFareAmount;
        this.dueFareAmount = dueFareAmount;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public double getFarePerDay() {
        return farePerDay;
    }

    public void setFarePerDay(double farePerDay) {
        this.farePerDay = farePerDay;
    }

    public double getTotalFareAmount() {
        return totalFareAmount;
    }

    public void setTotalFareAmount(double totalFareAmount) {
        this.totalFareAmount = totalFareAmount;
    }

    public double getAdvanceFareAmount() {
        return advanceFareAmount;
    }

    public void setAdvanceFareAmount(double advanceFareAmount) {
        this.advanceFareAmount = advanceFareAmount;
    }

    public double getDueFareAmount() {
        return dueFareAmount;
    }

    public void setDueFareAmount(double dueFareAmount) {
        this.dueFareAmount = dueFareAmount;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return noOfDays == booking.noOfDays &&
                Double.compare(booking.farePerDay, farePerDay) == 0 &&
                Double.compare(booking.totalFareAmount, totalFareAmount) == 0 &&
                Double.compare(booking.advanceFareAmount, advanceFareAmount) == 0 &&
                Double.compare(booking.dueFareAmount, dueFareAmount) == 0 &&
                Objects.equals(id, booking.id) &&
                Objects.equals(customerName, booking.customerName) &&
                Objects.equals(customerMobile, booking.customerMobile) &&
                Objects.equals(customerEmail, booking.customerEmail) &&
                Objects.equals(bookingdate, booking.bookingdate) &&
                Objects.equals(returnDate, booking.returnDate) &&
                Objects.equals(car, booking.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerMobile, customerEmail, bookingdate, returnDate, noOfDays, farePerDay, totalFareAmount, advanceFareAmount, dueFareAmount, car);
    }
}