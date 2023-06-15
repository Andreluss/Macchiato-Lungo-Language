package instructions;

import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;
import expressions.Expression;


/**
 * Deklaracja zmiennej w Macchiato.
 */
public class VariableDeclaration extends Declaration {
    private final char variable;
    private final Expression expression;

    /**
     * Tworzy deklarację zmiennej o nazwie variable,
     * do której przypisywana będzie wartość wyrażenia expression
     * @param variable nazwa deklarowanej zmiennej
     * @param expression wyrażenie przypisywane do zmiennej w deklaracji
     */
    public VariableDeclaration(char variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        // na początku wykonujemy funkcje związane ze środowiskiem
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this);

        // wyliczamy wartość wyrażenia (tu może wystąpić błąd)
        int value = expression.evaluate(environment.getVariables());

        // i przypisujemy zmiennej variable (przy okazji ją tworząc) wartość variable
        environment.getVariables().create(variable, value);

        // (zgodnie z treścią zadania, zakładamy, że w danym zakresie
        // nie będzie dwóch deklaracji tej samej zmiennej)
    }

    @Override
    public String toString() {
        return "var " + variable + " = " + expression;
    }
}
