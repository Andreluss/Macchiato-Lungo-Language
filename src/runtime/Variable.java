package runtime;

import exceptions.MacchiatoRuntimeException;
import expressions.Expression;
import expressions.Variables;

/**
 * Wyrażenie: zmienna
 */
public class Variable extends Expression {
    private final char name;

    /**
     * Tworzy wyrażenie zawierające zmienną name
     * @param name jednoliterowa nazwa zmiennej
     */
    public Variable(char name) {
        this.name = name;
    }
    public static Variable named(char name) {
        return new Variable(name);
    }
    @Override
    public int evaluate(Variables variables) throws MacchiatoRuntimeException {
        return variables.value(name);
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
