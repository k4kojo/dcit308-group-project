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
