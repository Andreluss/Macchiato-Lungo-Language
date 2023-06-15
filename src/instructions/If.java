package instructions;

import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;
import expressions.Expression;

/**
 * Instrukcja: if {...} (else {...})
 */
public class If extends Instruction {
    private interface Predicate {
        boolean compare(int a, int b);
    }

    /**
     * Rodzaj porównania w instrukcji warunkowej. Dostępne są standardowe relacje porządkujące.
     */
    public enum ComparisonType {
        Less, LessOrEqual, Equal, Greater, GreaterOrEqual, NotEqual
    }
    private final Expression exLeft;
    private final Expression exRight;
    private final Instruction[] instructionsIf;
    private final Instruction[] instructionsElse;
    private String comparisonSign;
    private Predicate predicate;

    private void initializeComparisonInfo(ComparisonType comparisonType) {
        switch (comparisonType) {
            case Less -> {
                predicate = (a, b) -> a < b;
                comparisonSign = "<";
            }
            case LessOrEqual -> {
                predicate = (a, b) -> a <= b;
                comparisonSign = "<=";
            }
            case Equal -> {
                predicate = (a, b) -> a == b;
                comparisonSign = "=";
            }
            case Greater -> {
                predicate = (a, b) -> a > b;
                comparisonSign = ">";
            }
            case GreaterOrEqual -> {
                predicate = (a, b) -> a >= b;
                comparisonSign = ">=";
            }
            case NotEqual -> {
                predicate = (a, b) -> a != b;
                comparisonSign = "!=";
            }
        }
    }

    /**
     * Tworzy instrukcję warunkową, w której warunkiem jest wynik porównania 2 wyrażeń.
     * Parametr instructionsElse[] jest opcjonalny.
     * @param exLeft wyrażenie z lewej strony porównania
     * @param comparisonType typ porównania
     * @param exRight wyrażenie z prawej strony porównania
     * @param instructionsIf instrukcje do wykonania, jeśli warunek jest spełniony (nie null!)
     * @param instructionsElse instrukcje do wykonania w.p.p. (mogą być null)
     */
    public If(Expression exLeft,
              ComparisonType comparisonType,
              Expression exRight,
              Instruction[] instructionsIf,
              Instruction[] instructionsElse) {
        this.exLeft = exLeft;
        this.exRight = exRight;
        this.instructionsIf = instructionsIf;
        this.instructionsElse = instructionsElse;
        initializeComparisonInfo(comparisonType);
    }

    /**
     * Tworzy instrukcję if bez bloku else, używając ogólnego konstruktora
     * {@link If#If(Expression, ComparisonType, Expression, Instruction[], Instruction[])}.
     */
    public If(Expression exLeft,
              ComparisonType comparisonType,
              Expression exRight,
              Instruction[] instructionsIf) {
        this(exLeft, comparisonType, exRight, instructionsIf, null);
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this); // wyliczenie wyrażenia i przejście do odpowiedniego bloku

        int valueLeft = exLeft.evaluate(environment.getVariables());
        int valueRight = exRight.evaluate(environment.getVariables());
        boolean condition = predicate.compare(valueLeft, valueRight);

        if(condition) {
            for (Instruction instruction : instructionsIf) {
                instruction.run(environment);
            }
        }
        else if(instructionsElse != null) {
            for (Instruction instruction : instructionsElse) {
                instruction.run(environment);
            }
        }
    }

    @Override
    public String toString() {
        String result = "if(" + exLeft + " " + comparisonSign + " " + exRight + ") ...";
        if(instructionsElse != null) {
            result += " else ...";
        }
        return result;
    }
}
