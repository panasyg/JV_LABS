package task1.store;

public class Good {
    private String manufacturer;
    private String name;
    private double buyingPrice;
    private double sellingPrice;

    public Good(final String manufacturer, final String name, final double buyingPrice) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.buyingPrice = buyingPrice;
    }

    public Good(final String manufacturer, final String name, final double buyingPrice, final double sellingPrice) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}
