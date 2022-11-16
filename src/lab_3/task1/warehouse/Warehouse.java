package task1.warehouse;

import task1.store.Good;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<Good, Integer> goods;

    public Warehouse(final Map<Good, Integer> goods) {
        this.goods = goods;
    }

    public Map<Good, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Good, Integer> goods) {
        this.goods = goods;
    }

    public void addGood(final Good good, final int amount) {
        if (goods == null) {
            goods =  new  HashMap<Good, Integer>();
        }

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            if (entry.getKey().equals(good.getName()) && entry.getKey().getManufacturer().equals(good.getManufacturer()) && entry.getKey().getBuyingPrice() == good.getBuyingPrice()) {
                final  int result = entry.getValue() + amount;
                entry.setValue(result);
                return;
            }
        }

        final Good tmp = new Good(good.getManufacturer(), good.getName(), good.getBuyingPrice(), good.getSellingPrice());
        this.goods.put(tmp, amount);
    }

    public void addGood(final Map<Good, Integer> deliveredGoods) {
        if (goods == null) {
            goods = new HashMap<Good, Integer>();
        }

        for (Map.Entry<Good, Integer> deliveredEntry : deliveredGoods.entrySet()) {
            boolean available = false;

            for (Map.Entry<Good, Integer> availableEntry : goods.entrySet()) {
                if (availableEntry.getKey().getName().equals(deliveredEntry.getKey().getName()) && availableEntry.getKey().getManufacturer().equals(deliveredEntry.getKey().getManufacturer()) && availableEntry.getKey().getBuyingPrice() == deliveredEntry.getKey().getBuyingPrice()) {
                    available = true;
                    final  int result = availableEntry.getValue() + deliveredEntry.getValue();
                    availableEntry.setValue(result);
                }
            }

            if (!available) {
                final  Good tmp = new Good(deliveredEntry.getKey().getManufacturer(), deliveredEntry.getKey().getName(), deliveredEntry.getKey().getBuyingPrice(), deliveredEntry.getKey().getSellingPrice());
                this.goods.put(tmp, deliveredEntry.getValue());
            }
        }
    }

    public void sellGood(final Good good, int amount) {
        if (amount <= 0) {
            //throw exception
        }

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            if (entry.getKey().getName().equals(good.getName()) && entry.getKey().getManufacturer().equals(good.getManufacturer())) {
                if (entry.getValue() == amount) {
                    goods.remove(entry);
                } else if (entry.getValue() > amount) {
                    final  int result = entry.getValue() - amount;
                    entry.setValue(result);
                    return;
                } else {
                    //throw exception
                }
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += " GOODS IN WAREHOUSE" + "\n";

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            result += "     ";
            result += "product name: " + entry.getKey().getName() + ";     ";
            result += "manufacturer: " + entry.getKey().getManufacturer() + ";     ";
            result += "buying price: " + entry.getKey().getBuyingPrice() + ";     ";
            result += "selling price: " + entry.getKey().getSellingPrice() + ";     ";
            result += "amount: " + entry.getValue() + ";     " + "\n";
        }

        return result;
    }
}
