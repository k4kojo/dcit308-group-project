package com.example.dcit308group8;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PharmacyManagementSystem {
    private final ArrayList<Drug> drugs;

    public PharmacyManagementSystem() {
        this.drugs = new ArrayList<>();
    }

    public void addDrug(String name, String code, String description, double price, String manDate, String expDate, Integer initQuantity, Integer inStockQuantity, ArrayList<SupplierNode> suppliers) {
        SupplierLinkedList supplierLinkedList = new SupplierLinkedList();
        for (SupplierNode supplier : suppliers) {
            supplierLinkedList.addSupplier(supplier.getSupplierName(), supplier.getLocation());
        }
        Drug drug = new Drug(name, code, description, price, manDate, expDate, initQuantity, inStockQuantity, supplierLinkedList);
        drugs.add(drug);
    }

    public Drug searchDrug(String code) {
        for (Drug drug : drugs) {
            if (drug.getCode().equals(code)) {
                return drug;
            }
        }
        return null;
    }

    public ArrayList<Drug> loadAllDrugs() {
        return new ArrayList<>(drugs); // Return a copy to avoid modifying the original list
    }

    public ArrayList<PurchaseRecord> viewPurchaseHistory(String code) {
        Drug drug = searchDrug(code);
        if (drug != null) {
            return new ArrayList<>(drug.getPurchaseHistory()); // Return a copy to avoid modifying the original list
        }
        return new ArrayList<>();
    }

    public void recordSale(String code, LocalDate date, LocalTime time, String buyerName, double amount) {
        Drug drug = searchDrug(code);
        if (drug != null) {
            PurchaseRecord record = new PurchaseRecord(date, time, buyerName, amount);
            drug.addPurchaseRecord(record);
        } else {
            throw new IllegalArgumentException("Drug not found");
        }
    }
}
