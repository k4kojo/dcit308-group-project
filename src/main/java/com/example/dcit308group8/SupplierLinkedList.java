package com.example.dcit308group8;

import java.util.Iterator;

public class SupplierLinkedList implements Iterable<SupplierNode> {
    private SupplierNode head;

    public SupplierLinkedList() {
        this.head = null;
    }

    public void addSupplier(String supplierName, String location) {
        SupplierNode newNode = new SupplierNode(supplierName, location);
        newNode.setNext(head);
        head = newNode;
    }

    public String searchSupplier(String location) {
        SupplierNode current = head;
        while (current != null) {
            if (current.getLocation().equals(location)) {
                return current.getSupplierName();
            }
            current = current.getNext();
        }
        return null;
    }

    public SupplierNode getHead() {
        return head;
    }

    @Override
    public Iterator<SupplierNode> iterator() {
        return new SupplierIterator();
    }

    // Returns a string of all supplier names separated by commas
    public String getSupplierNames() {
        StringBuilder sb = new StringBuilder();
        SupplierNode current = head;
        while (current != null) {
            sb.append(current.getSupplierName()).append(", ");
            current = current.getNext();
        }
        // Remove the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    // Returns a string of all supplier locations separated by commas
    public String getSupplierLocations() {
        StringBuilder sb = new StringBuilder();
        SupplierNode current = head;
        while (current != null) {
            sb.append(current.getLocation()).append(", ");
            current = current.getNext();
        }
        // Remove the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    private class SupplierIterator implements Iterator<SupplierNode> {
        private SupplierNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public SupplierNode next() {
            SupplierNode temp = current;
            current = current.getNext();
            return temp;
        }
    }
}
