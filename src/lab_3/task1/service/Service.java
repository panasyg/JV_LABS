package task1.service;

import task1.client.Client;
import task1.store.Good;
import task1.store.Receipt;
import task1.store.Store;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Service {
    private Store store;

    public Service(Store store) {
        this.store = store;
    }

    public void sortGoodsByAlphabet() {
        Map<Good, Integer> sortedGoods = store.getWarehouse().getGoods().entrySet().stream().sorted((e1, e2) -> {
            String firstCompareValue = e1.getKey().getName();
            String secondCompareValue = e2.getKey().getName();
            int compared = firstCompareValue.compareToIgnoreCase(secondCompareValue);
            return compared < 0 ? -1 : compared == 0 ? 0 : 1;
        }).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (v1, v2) -> v1, LinkedHashMap::new));

        store.getWarehouse().setGoods(sortedGoods);
    }

    public void sortGoodsByPrice() {
        Map<Good, Integer> sortedGoods = store.getWarehouse().getGoods().entrySet().stream().sorted((e1, e2) -> {
            double firstCompareValue = e1.getKey().getSellingPrice();
            double secondCompareValue = e2.getKey().getSellingPrice();
            return firstCompareValue > secondCompareValue ? -1 : firstCompareValue == secondCompareValue ? 0 : 1;
        }).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (v1, v2) -> v1, LinkedHashMap::new));

        store.getWarehouse().setGoods(sortedGoods);
    }

    public void filterGoodsByMinAndMax(final double min, final double max) {
        Map<Good, Integer> sortedGoods = store.getWarehouse().getGoods().entrySet().stream().filter(e -> e.getKey().getSellingPrice() >= min && e.getKey().getSellingPrice() <= max).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (v1, v2) -> v1, LinkedHashMap::new));

        store.getWarehouse().setGoods(sortedGoods);
    }

    public double getAveragePrice() {
        return store.getWarehouse().getGoods().entrySet().stream().mapToDouble(p -> p.getKey().getSellingPrice()).average().orElse(Double.NaN);
    }

    public double getTotalSpendByClient(final Client client, final LocalDate from, final LocalDate to) {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        store.getHistory().getReceipts().stream().forEach((receipt) -> {
            if (receipt.getClient().equals(client) && (receipt.getDate().isEqual(from) || receipt.getDate().isAfter(from)) && (receipt.getDate().isEqual(to) || receipt.getDate().isBefore(to))) {
                receipt.getGoods().entrySet().stream().forEach((good) -> {
                    result.updateAndGet(v -> (double) (v + good.getKey().getSellingPrice() * good.getValue()));
                });
            }
            ;
        });

        return result.get();
    }

    public void getAllGoodsByClient(final Client client) {
        LinkedList<Receipt> receipts = store.getHistory().getReceipts();
        LinkedList<Receipt> filteredReceipts = receipts.stream().filter(e -> e.getClient().equals(client)).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Good> allGoods = new LinkedList<Good>();

        for (Receipt r : filteredReceipts) {
            for (Map.Entry<Good, Integer> g : r.getGoods().entrySet()) {
                for (int i = 0; i < g.getValue(); i++) {
                    allGoods.push(g.getKey());
                }
            }
        }

        Map<String, Long> resultCount = allGoods.stream().collect(Collectors.groupingBy(Good -> "Name: " + Good.getName() + "; Manufacturer: " + Good.getManufacturer(), Collectors.counting()));

        for (Map.Entry<String, Long> entry : resultCount.entrySet()) {
            System.out.println(entry.getKey() + "; Count: " + entry.getValue().toString() + ";");
        }
    }

    public void getMostPopularGood() {
        LinkedList<Receipt> receipts = store.getHistory().getReceipts();
        LinkedList<Good> allGoods = new LinkedList<Good>();

        for (Receipt r : receipts) {
            for (Map.Entry<Good, Integer> g : r.getGoods().entrySet()) {
                for (int i = 0; i < g.getValue(); i++) {
                    allGoods.push(g.getKey());
                }
            }
        }

        Map<String, Long> resultCount = allGoods.stream().collect(groupingBy(Good -> "Name: " + Good.getName() + "; Manufacturer: " + Good.getManufacturer(), Collectors.counting()));

        Map<String, Long> sortedGoods = resultCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (v1, v2) -> v1, LinkedHashMap::new));

        System.out.printf("\n" + sortedGoods.entrySet().stream().reduce((one, two) -> two).get().getKey() + "; Count: " + sortedGoods.entrySet().stream().reduce((one, two) -> two).get().getValue() + "\n");
    }

    public void getBiggestIncome() {
        LinkedList<Receipt> receipts = store.getHistory().getReceipts();

        Map<LocalDate, Double> entryCalculateReceipt = receipts.stream().collect(Collectors.toMap(p -> p.getDate(), p -> {
            AtomicReference<Double> res = new AtomicReference<>((double) 0);
            p.getGoods().entrySet().stream().forEach(g -> res.set((g.getKey().getSellingPrice() - g.getKey().getBuyingPrice()) * g.getValue()));
            return res.get();
        }, (v1, v2) -> v1, LinkedHashMap::new));


        Map<LocalDate, Double> entryCalculatedByDate = entryCalculateReceipt.entrySet().stream()
                .collect(groupingBy(p -> p.getKey(), summingDouble(p -> p.getValue())));

        Map<LocalDate, Double> sortedDates = entryCalculatedByDate.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (v1, v2) -> v1, LinkedHashMap::new));

        System.out.printf("\n" + sortedDates.entrySet().stream().reduce((one, two) -> two).get().getKey() + "; Total income: " + Math.round(sortedDates.entrySet().stream().reduce((one, two) -> two).get().getValue() * 100.0) / 100.0 + "\n");

    }


}
