package task1.store;

import task1.client.Client;
import task1.warehouse.Warehouse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private Warehouse warehouse;
    protected History history;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public History getHistory() {
        return history;
    }

    public void setWarehouse(final Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Store(final Warehouse warehouse, final History history) {
        this.warehouse = warehouse;
        this.history = history;
    }

    public void sellGood(final Map<Good, Integer> goods, final Client client, final LocalDate date) {
        final Map<Good, Integer> tmp = new HashMap<Good, Integer>();
        Receipt r = new Receipt(tmp, client, date);

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            warehouse.sellGood(entry.getKey(), entry.getValue());
            r.addGood(entry.getKey(), entry.getValue());
        }

        history.addReceipt(r);
    }

    public void receiveCargo(final Good good, final int amount) {
        warehouse.addGood(good, amount);
    }

    public void receiveCargo(final Map<Good, Integer> deliveredGoods) {
        warehouse.addGood(deliveredGoods);
    }

    @Override
    public String toString() {
        return "Store info" + "\n" + "\n" + this.warehouse.toString() + "\n" + this.history.toString();
    }
}
