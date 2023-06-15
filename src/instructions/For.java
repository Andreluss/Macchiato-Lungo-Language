package instructions;

import exceptions.MacchiatoException;
import expressions.Constant;
import expressions.Expression;
import runtime.MacchiatoEnvironment;

/**
 * Instrukcja: pętla for
 */
public class For extends Instruction {
    private final char variable;
    private final Expression expression;
    private final Instruction[] instructions;

    /**
     * Tworzy pętlę for ze zmienną sterującą variable.
     * Funkcja wykona się tyle razy, ile wyniesie wartość wyrażenia expression.
     * Pętla za każdym obrotem przypisuje zmiennej sterującej kolejną wartość
     * z przedziału [0, expression) (niezależnie od jej poprzedniej wartości),
     * a następnie wykonuje instrukcje z listy instructions.
     * @param variable nazwa zmiennej sterującej
     * @param expression wyrażenie opisujące wartość zmiennej sterującej
     * @param instructions lista instrukcji do wykonania w pętli
     */
    public For(char variable, Expression expression, Instruction[] instructions) {
        this.variable = variable;
        this.expression = expression;
        this.instructions = instructions;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this); // obliczenie expression i rozpoczęcie pętli

        int iterations = expression.evaluate(environment.getVariables());

        for (int value = 0; value < iterations; value++) {
            // wchodzimy poziom niżej
            environment.pushStackFrame();

            // przypisujemy zmiennej sterującej kolejną wartość (jest to 1 krok):
            new VariableDeclaration(variable, new Constant(value)).run(environment);

            // wykonujemy wszystkie wewnętrzne instrukcje
            for(Instruction instruction : instructions) {
                instruction.run(environment);
            }

            environment.popStackFrame();
        }
    }

    @Override
    public String toString() {
        return "for (var " + variable + " in range(" + expression + "):";
    }
}
