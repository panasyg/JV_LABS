package task1.store;

import java.time.LocalDate;
import java.util.Map;

import task1.client.Client;

public class Receipt {
    private Map<Good, Integer> goods;
    private Client client;

    private LocalDate date;

    public Receipt(final Map<Good, Integer> goods, final Client client, final LocalDate date) {
        this.goods = goods;
        this.client = client;
        this.date = date;
    }

    public Receipt(final Client client, final LocalDate date) {
        this.client = client;
        this.date = date;
    }

    public Receipt(final Map<Good, Integer> goods, final Client client) {
        this.goods = goods;
        this.client = client;
    }

    public Receipt(final Client client) {
        this.client = client;
    }


    public Receipt() {
    }

    public LocalDate getDate() {
        return date;
    }


    public Map<Good, Integer> getGoods() {
        return goods;
    }

    public Client getClient() {
        return client;
    }

    public void addGood(final Good good, final int amount) {
        final Good tmp = new Good(good.getManufacturer(), good.getName(), good.getBuyingPrice(), good.getSellingPrice());
        this.goods.put(tmp, amount);
    }

    public void addGood(final Map<Good, Integer> goods) {
        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            final Good tmp = new Good(entry.getKey().getManufacturer(), entry.getKey().getName(), entry.getKey().getBuyingPrice(), entry.getKey().getSellingPrice());
            this.goods.put(tmp, entry.getValue());
        }
    }

    @Override
    public String toString() {
        double total = 0;
        String result = "";
        result += "  client: " + client.getName() + "\n";

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {
            result += "     " + "product name: " + entry.getKey().getName() + ";     ";
            result += "manufacturer: " + entry.getKey().getManufacturer() + ";     ";
            result += "buying price: " + entry.getKey().getBuyingPrice() + ";     ";
            result += "selling price: " + entry.getKey().getSellingPrice() + ";     ";
            result += "amount: " + entry.getValue() + ";     " + "\n";
            total += entry.getKey().getSellingPrice() * entry.getValue();
        }
        result += "     " +  "date: " + date + "\n";
        result += "     " +  "Total: " + total  + "\n";

        return result;
    }
}
