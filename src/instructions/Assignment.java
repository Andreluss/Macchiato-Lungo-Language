package instructions;

import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;
import expressions.Expression;

/**
 * Instrukcja: przypisanie var variable = expression
 */
public class Assignment extends Instruction {
    private final char variable;
    private final Expression expression;

    /**
     * Tworzy instrukcję przypisania zmiennej variable wartości wyrażenia expression
     * @param variable nazwa zmiennej
     * @param expression wyrażenie przypisywanej zmiennej
     */
    public Assignment(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    /**
     * @throws MacchiatoException jeśli zmienna nie istnieje,
     * przy obliczaniu wyrażenia wystąpił błąd
     * lub program został zapauzowany
     */
    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this);

        int value = expression.evaluate(environment.getVariables());
        environment.getVariables().assign(variable, value);
    }

    @Override
    public String toString() {
        return variable + " = " + expression;
    }
}
