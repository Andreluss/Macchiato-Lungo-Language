package instructions;

import exceptions.MacchiatoException;
import expressions.Expression;
import runtime.MacchiatoEnvironment;

/**
 * Instrukcja wyświetlania wartości wyrażenia w konsoli
 */
public class Print extends Instruction {
    private final Expression expression;

    /**
     * Tworzy nową instrukcję print,
     * która po uruchomieniu wyświetli wartość wyrażenia expression w danym kontekście
     * @param expression wyrażenie do obliczenia i wyświetlenia
     */
    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this);
        System.out.println("[Macchiato]: " + expression.evaluate(environment.getVariables()));
    }

    @Override
    public String toString() {
        return "print(" + expression + ")";
    }
}
