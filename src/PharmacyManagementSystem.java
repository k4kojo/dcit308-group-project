import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PharmacyManagementSystem {
    private ArrayList<Drug> drugs;

    public PharmacyManagementSystem() {
        this.drugs = new ArrayList<>();
    }

    public void addDrug(String name, String code, LocalDate manDate, LocalDate expDate, ArrayList<SupplierNode> suppliers) {
        SupplierLinkedList supplierLinkedList = new SupplierLinkedList();
        for (SupplierNode supplier : suppliers) {
            supplierLinkedList.addSupplier(supplier.getSupplierName(), supplier.getLocation());
        }
        Drug drug = new Drug(name, code, manDate, expDate, supplierLinkedList);
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

    public ArrayList<Drug> viewAllDrugs() {
        return drugs;
    }

    public ArrayList<PurchaseRecord> viewPurchaseHistory(String code) {
        Drug drug = searchDrug(code);
        if (drug != null) {
            return drug.getPurchaseHistory();
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
