package task1.cargoDepartment.exceptions;

public class WrongSellingPriceException extends Exception {
    public WrongSellingPriceException() {
        super("selling price of good is wrong");
    }
}
