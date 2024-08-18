package com.example.dcit308group8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Drug {
    private String name, code, description, manDate, expDate;
    private double price;
    private Integer initQuantity, inStockQuantity;
    private SupplierLinkedList supplierLinkedList;
    private ArrayList<PurchaseRecord> purchaseHistory;

    // Constructor with SupplierLinkedList parameter
    public Drug(String name, String code, String description, double price, String manDate, String expDate, Integer initQuantity, Integer inStockQuantity, SupplierLinkedList supplierLinkedList) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.manDate = manDate;
        this.expDate = expDate;
        this.initQuantity = initQuantity;
        this.inStockQuantity = inStockQuantity;
        this.supplierLinkedList = supplierLinkedList;
        this.purchaseHistory = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Integer getInitQuantity() {
        return initQuantity;
    }

    public Integer getInStockQuantity() {
        return inStockQuantity;
    }

    public String getManDate() {
        return manDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public SupplierLinkedList getSupplierLinkedList() {
        return supplierLinkedList;
    }

    public ArrayList<PurchaseRecord> getPurchaseHistory() {
        return purchaseHistory;
    }

    // Add a purchase record and sort the history by date and time
    public void addPurchaseRecord(PurchaseRecord record) {
        purchaseHistory.add(record);
        Collections.sort(purchaseHistory, Comparator.comparing(PurchaseRecord::getDate).thenComparing(PurchaseRecord::getTime));
    }
}
