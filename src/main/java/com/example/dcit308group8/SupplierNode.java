package com.example.dcit308group8;

public class SupplierNode {
    private String supplierName;
    private String location;
    private SupplierNode next;

    public SupplierNode(String supplierName, String location) {
        this.supplierName = supplierName;
        this.location = location;
        this.next = null;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getLocation() {
        return location;
    }

    public SupplierNode getNext() {
        return next;
    }

    public void setNext(SupplierNode next) {
        this.next = next;
    }
}

