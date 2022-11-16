package task1.cargoDepartment;

import task1.cargoDepartment.exceptions.*;
import task1.store.Good;
import task1.store.Store;

import java.util.Map;

public class CargoDepartment {

    private Store store;

    public CargoDepartment(final Store store) {
        this.store = store;
    }

    public Map<Good, Integer> getReport() {
        return store.getWarehouse().getGoods();
    }

    public boolean validate(final Map<Good, Integer> report) throws NoManufacturerException, NoNameException, WrongBuyingPriceException, WrongSellingPriceException, WrongAmountException {
        for (Map.Entry<Good, Integer> entry : report.entrySet()) {
            if (entry.getKey().getManufacturer().isEmpty()) {
                throw new NoManufacturerException();
            }
            if (entry.getKey().getName().isEmpty()) {
                throw new NoNameException();
            }
            if (entry.getKey().getBuyingPrice() <= 0) {
                throw new WrongBuyingPriceException();
            }
            if (entry.getKey().getSellingPrice() <= 0) {
                throw new WrongSellingPriceException();
            }
            if (entry.getValue() < 0) {
                throw new WrongAmountException();
            }
        }

        return true;
    }

    public void receiveCargo(final Good good, final int amount) {
        store.receiveCargo(good, amount);
    }

    public void receiveCargo(final Map<Good, Integer> deliveredGoods) {
        store.receiveCargo(deliveredGoods);
    }
}
