package pl.piotrschodzinski.homebudget.exception.customException;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity not found.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
