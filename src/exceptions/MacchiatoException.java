package exceptions;

/**
 * Klasa bazowa dla wyjątków w Macchiato.
 * Udostępnia konstruktor ustawiający exception message.
 */
public class MacchiatoException extends Exception {
    public MacchiatoException(String message) {
        super(message);
    }
}
