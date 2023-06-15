package expressions;

import exceptions.MacchiatoRuntimeException;

/**
 * Abstrakcyjna klasa reprezentująca wyrażenie-operację dwuargumentową w programie. <br>
 * Taka struktura ułatwia tworzenie klas pochodnych typu +, -, *, itp.
 */
public abstract class BinaryExpression extends Expression {
    /**
     * Wyrażenie z lewej strony
     */
    protected final Expression exp1;
    /**
     * Wyrażenie z prawej strony
     */
    protected final Expression exp2;

    /**
     * Znak operacji, np. '+' lub '*'
     */
    private final char operationSign;

    /**
     * Tworzy wyrażenie (o znaku sign) dla argumentów exp1 i exp2
     * @param exp1 lewa strona wyrażenia
     * @param exp2 prawa strona wyrażenia
     * @param sign znak operacji (potrzebny w toString())
     */
    public BinaryExpression (Expression exp1, Expression exp2, char sign) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        operationSign = sign;
    }

    /**
     * Funkcja wykonuje zdefiniowaną w podklasie binarną operację na wartościach a i b
     * @param a wartość z lewej strony
     * @param b wartość z prawej strony
     * @return wynik operacji dla (a, b)
     * @throws MacchiatoRuntimeException jeśli operacja dla danych argumentów
     * nie da się wykonać operacji (np. dzielenie przez zero)
     */
    protected abstract int operation(int a, int b) throws MacchiatoRuntimeException;

    @Override
    public int evaluate(Variables variables) throws MacchiatoRuntimeException {
        return operation(exp1.evaluate(variables), exp2.evaluate(variables));
    }

    @Override
    public String toString() {
        return "(" + exp1.toString() + " " + operationSign + " " + exp2.toString() + ")";
    }
}
