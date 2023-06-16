package expressions;

import exceptions.MacchiatoRuntimeException;
import runtime.Variables;

/**
 * Klasa reprezentująca wyrażenie w programie
 */
public abstract class Expression {
    /**
     * Funkcja obliczająca wartość wyrażenia w bieżącym kontekście.
     * @param variables aktualne zmienne i ich wartości w danym momencie programie
     * @return wartość wyrażenia po podstawieniu odpowiednich wartości pod zmienne
     * @throws MacchiatoRuntimeException jeśli jakaś zmienna nie istnieje
     * lub pewna operacja jest niedozwolona (np. dzielenie przez zero)
     */
    public abstract int evaluate(Variables variables) throws MacchiatoRuntimeException;

    /**
     * @return wyrażenie w formie tekstowej
     */
    @Override // wymuszamy implementację toString() w podklasach
    public abstract String toString();
}
