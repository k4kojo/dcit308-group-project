package com.example.dcit308group8;

import java.time.LocalDate;
import java.time.LocalTime;

public class PurchaseRecord {
    private LocalDate date;
    private LocalTime time;
    private String buyerName;
    private double amount;

    public PurchaseRecord(LocalDate date, LocalTime time, String buyerName, double amount) {
        this.date = date;
        this.time = time;
        this.buyerName = buyerName;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public double getAmount() {
        return amount;
    }
}

