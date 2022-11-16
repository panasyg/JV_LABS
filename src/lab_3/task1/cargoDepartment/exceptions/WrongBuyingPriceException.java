package task1.cargoDepartment.exceptions;

public class WrongBuyingPriceException extends Exception {
    public WrongBuyingPriceException() {
        super("buying price of good is wrong");
    }
}
