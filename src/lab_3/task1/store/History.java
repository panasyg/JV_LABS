package task1.store;


import java.util.Iterator;
import java.util.LinkedList;

public class History {
    private LinkedList<Receipt> receipts;

    public History(final LinkedList<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void addReceipt(final Receipt receipt) {
        this.receipts.add(receipt);
    }

    public LinkedList<Receipt> getReceipts() {
        return receipts;
    }

    @Override
    public String toString() {
        String result = "";
        result += " HISTORY OF PURCHASES" + "\n";

        Iterator iterator = receipts.iterator();
        while (iterator.hasNext()) {
            result += iterator.next().toString();
        }

        return result;
    }
}
