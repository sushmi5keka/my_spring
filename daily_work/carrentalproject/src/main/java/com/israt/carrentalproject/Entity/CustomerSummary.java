package com.israt.carrentalproject.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class CustomerSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalFareAmount;

    private double advanceFareAmount;

    private double dueFareAmount;

    private int noOfBooking;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date firstBookingDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastBookingDate;

    @OneToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private User customer;

    public CustomerSummary() {
    }

    public CustomerSummary(double totalFareAmount, double advanceFareAmount, double dueFareAmount, int noOfBooking, Date firstBookingDate, Date lastBookingDate, User customer) {
        this.totalFareAmount = totalFareAmount;
        this.advanceFareAmount = advanceFareAmount;
        this.dueFareAmount = dueFareAmount;
        this.noOfBooking = noOfBooking;
        this.firstBookingDate = firstBookingDate;
        this.lastBookingDate = lastBookingDate;
        this.customer = customer;
    }

    public CustomerSummary(CustomerSummary customerSummary) {
        this.totalFareAmount = customerSummary.totalFareAmount;
        this.advanceFareAmount = customerSummary.advanceFareAmount;
        this.dueFareAmount = customerSummary.dueFareAmount;
        this.noOfBooking = customerSummary.noOfBooking;
        this.firstBookingDate = customerSummary.firstBookingDate;
        this.lastBookingDate = customerSummary.lastBookingDate;
        this.customer = customerSummary.customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNoOfBooking() {
        return noOfBooking;
    }

    public void setNoOfBooking(int noOfBooking) {
        this.noOfBooking = noOfBooking;
    }

    public Date getFirstBookingDate() {
        return firstBookingDate;
    }

    public void setFirstBookingDate(Date firstBookingDate) {
        this.firstBookingDate = firstBookingDate;
    }

    public Date getLastBookingDate() {
        return lastBookingDate;
    }

    public void setLastBookingDate(Date lastBookingDate) {
        this.lastBookingDate = lastBookingDate;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSummary that = (CustomerSummary) o;
        return Double.compare(that.totalFareAmount, totalFareAmount) == 0 &&
                Double.compare(that.advanceFareAmount, advanceFareAmount) == 0 &&
                Double.compare(that.dueFareAmount, dueFareAmount) == 0 &&
                noOfBooking == that.noOfBooking &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstBookingDate, that.firstBookingDate) &&
                Objects.equals(lastBookingDate, that.lastBookingDate) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalFareAmount, advanceFareAmount, dueFareAmount, noOfBooking, firstBookingDate, lastBookingDate, customer);
    }
}
