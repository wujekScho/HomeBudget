package pl.piotrschodzinski.homebudget.exception.customException;

public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException(String message) {
        super(message);
    }
}
