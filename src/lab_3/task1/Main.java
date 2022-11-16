package task1;

import task1.cargoDepartment.CargoDepartment;
import task1.cargoDepartment.exceptions.*;
import task1.client.Client;
import task1.service.Service;
import task1.store.Good;
import task1.store.History;
import task1.store.Receipt;
import task1.store.Store;
import task1.warehouse.Warehouse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws WrongSellingPriceException, WrongBuyingPriceException, NoManufacturerException, WrongAmountException, NoNameException {
        final Good snickers = new Good("nestle", "snickers", 0.3, 1);
        Map<Good, Integer> goods = new HashMap<Good, Integer>();
        goods.put(snickers, 20);
        LinkedList<Receipt> history = new LinkedList<Receipt>();

        Store silpo = new Store(new Warehouse(goods), new History(history));
        CargoDepartment silpoCargo = new CargoDepartment(silpo);

        final Good nesquick = new Good("nestle", "nesquick", 0.7, 1.5);
        final Good vermishel = new Good("rollton", "vermishel", 0.3, 0.75);
        final Good twix = new Good("nestle", "twix", 0.5, 1.1);
        final Good apple = new Good("locals", "apple", 0.1, 0.3);

        silpoCargo.receiveCargo(nesquick, 100);
        silpoCargo.receiveCargo(vermishel, 300);
        silpoCargo.receiveCargo(twix, 40);
        silpoCargo.receiveCargo(apple, 1000);

        try {
            if (silpoCargo.validate(silpoCargo.getReport())) {
                System.out.printf("validation finished successfully");
            }

        } catch (final NoManufacturerException | NoNameException | WrongBuyingPriceException |
                       WrongSellingPriceException | WrongAmountException err) {
            throw new RuntimeException(err);
        }

        final Client ishtvan = new Client("ishtvan");
        final Client anon = new Client("anon");

        Map<Good, Integer> purchase1 = new HashMap<Good, Integer>();
        purchase1.put(nesquick, 5);
        purchase1.put(vermishel, 1);

        Map<Good, Integer> purchase2 = new HashMap<Good, Integer>();
        purchase2.put(vermishel, 59);

        Map<Good, Integer> purchase3 = new HashMap<Good, Integer>();
        purchase3.put(apple, 3);
        purchase3.put(twix, 1);
        purchase3.put(vermishel, 2);

        silpo.sellGood(purchase1, ishtvan, LocalDate.of(2022, 10, 1));
        silpo.sellGood(purchase2, anon, LocalDate.of(2022, 10, 2));
        silpo.sellGood(purchase3, ishtvan, LocalDate.of(2022, 10, 25));

        //System.out.printf(silpo.toString());

        Service service = new Service(silpo);
        //service.sortGoodsByPrice();
        service.sortGoodsByAlphabet();

        //service.filterGoodsByMinAndMax(3, 5);

        //System.out.printf(silpo.toString());

        //System.out.printf("\n" + Math.round(service.getAveragePrice() * 100.0) / 100.0 + "\n");

        //System.out.printf("\n" + service.getTotalSpendByClient(ishtvan, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 24)) + "\n");

        System.out.printf("\nall goods by ishtvan: \n");
        service.getAllGoodsByClient(ishtvan);

        System.out.printf("\nmost popular godd:");
        service.getMostPopularGood();
        System.out.printf("\n");

        System.out.printf("biggest income:");
        service.getBiggestIncome();
        System.out.printf("\n");

    }
}
