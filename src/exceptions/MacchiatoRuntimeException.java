package exceptions;

/**
 * Wyjątek związany z błędem wykonania — nieprawidłową operacją, odwołaniem do nieistniejącej zmiennej itp.
 */
public class MacchiatoRuntimeException extends MacchiatoException {
    public MacchiatoRuntimeException(String message) {
        super("Błąd wykonania: " + message);
    }
}
