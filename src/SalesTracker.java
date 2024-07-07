import java.util.Stack;

public class SalesTracker {
    private Stack<String> salesStack;

    public SalesTracker() {
        salesStack = new Stack<>();
    }

    public void recordSale(String drugCode) {
        salesStack.push(drugCode);
    }

    public Stack<String> getSales() {
        return salesStack;
    }
}
