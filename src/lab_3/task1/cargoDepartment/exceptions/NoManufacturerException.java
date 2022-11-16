package task1.cargoDepartment.exceptions;

public class NoManufacturerException extends Exception {
    public NoManufacturerException() {
        super("no manufacturer in good");
    }
}
