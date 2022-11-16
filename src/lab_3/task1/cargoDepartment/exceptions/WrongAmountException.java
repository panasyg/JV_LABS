package task1.cargoDepartment.exceptions;

public class WrongAmountException extends Exception {
    public WrongAmountException() {
        super("amount of goods is wrong");
    }
}
