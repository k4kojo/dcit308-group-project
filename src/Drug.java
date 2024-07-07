import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Drug {
    private String name;
    private String code;
    private LocalDate manDate;
    private LocalDate expDate;
    private SupplierLinkedList suppliers;
    private ArrayList<PurchaseRecord> purchaseHistory;

    public Drug(String name, String code, LocalDate manDate, LocalDate expDate, SupplierLinkedList suppliers) {
        this.name = name;
        this.code = code;
        this.manDate = manDate;
        this.expDate = expDate;
        this.suppliers = suppliers;
        this.purchaseHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getManDate() {
        return manDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public SupplierLinkedList getSuppliers() {
        return suppliers;
    }

    public ArrayList<PurchaseRecord> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addPurchaseRecord(PurchaseRecord record) {
        purchaseHistory.add(record);
        Collections.sort(purchaseHistory, Comparator.comparing(PurchaseRecord::getDate).thenComparing(PurchaseRecord::getTime));
    }
}
