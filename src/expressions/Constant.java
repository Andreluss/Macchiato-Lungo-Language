package expressions;

import runtime.Variables;

/**
 * Wyrażenie: literał całkowity, np. 100 lub -42
 */
public class Constant extends Expression {
    int value;

    /**
     * Tworzy wyrażenie o stałej wartości value
     * @param value wartość
     */
    public Constant(int value) {
        this.value = value;
    }

    public static Constant of(int value) { return new Constant(value); }

    @Override
    public int evaluate(Variables variables) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
