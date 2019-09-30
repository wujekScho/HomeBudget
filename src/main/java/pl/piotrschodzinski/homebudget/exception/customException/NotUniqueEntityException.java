package pl.piotrschodzinski.homebudget.exception.customException;

public class NotUniqueEntityException extends RuntimeException {
    public NotUniqueEntityException() {
        super("Same entity already exists.");
    }

    public NotUniqueEntityException(String message) {
        super(message);
    }
}
