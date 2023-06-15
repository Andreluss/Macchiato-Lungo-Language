package exceptions;

/**
 * Wyjątek przeznaczony do wygodnego przerywania wykonania całego programu w Macchiato.
 */
public class MacchiatoDebugStopException extends MacchiatoException {
    public MacchiatoDebugStopException(String message) {
        super(message);
    }

    public MacchiatoDebugStopException() {
        this("Program przerwany.");
    }
}
