import java.util.HashMap;
import java.util.ArrayList;

public class DrugCollection {
    private HashMap<String, Drug> drugs;

    public DrugCollection() {
        drugs = new HashMap<>();
    }

    public void addDrug(Drug drug) {
        drugs.put(drug.getCode(), drug);
    }

    public void removeDrug(String code) {
        drugs.remove(code);
    }

    public Drug getDrug(String code) {
        return drugs.get(code);
    }

    public ArrayList<Drug> getAllDrugs() {
        return new ArrayList<>(drugs.values());
    }
}
