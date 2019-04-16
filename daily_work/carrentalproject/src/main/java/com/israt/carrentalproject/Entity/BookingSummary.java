package com.israt.carrentalproject.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalFareAmount;

    private double advanceFareAmount;

    private double dueFareAmount;

    private double collectedAmount;


    @OneToOne
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking booking;

    public BookingSummary() {
    }

    public BookingSummary(double totalFareAmount, double advanceFareAmount, double dueFareAmount, Booking booking) {
        this.totalFareAmount = totalFareAmount;
        this.advanceFareAmount = advanceFareAmount;
        this.dueFareAmount = dueFareAmount;
        this.booking = booking;
    }

    public BookingSummary(double totalFareAmount, double advanceFareAmount, double dueFareAmount, double collectedAmount, Booking booking) {
        this.totalFareAmount = totalFareAmount;
        this.advanceFareAmount = advanceFareAmount;
        this.dueFareAmount = dueFareAmount;
        this.collectedAmount = collectedAmount;
        this.booking = booking;
    }

    public BookingSummary(BookingSummary bookingSummary) {
        this.totalFareAmount = bookingSummary.totalFareAmount;
        this.advanceFareAmount = bookingSummary.advanceFareAmount;
        this.dueFareAmount = bookingSummary.dueFareAmount;
        this.collectedAmount = bookingSummary.collectedAmount;
        this.booking = bookingSummary.booking;
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

    public double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingSummary that = (BookingSummary) o;
        return Double.compare(that.totalFareAmount, totalFareAmount) == 0 &&
                Double.compare(that.advanceFareAmount, advanceFareAmount) == 0 &&
                Double.compare(that.dueFareAmount, dueFareAmount) == 0 &&
                Double.compare(that.collectedAmount, collectedAmount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(booking, that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalFareAmount, advanceFareAmount, dueFareAmount, collectedAmount, booking);
    }
}
