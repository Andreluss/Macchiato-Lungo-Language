package expressions;

/**
 * Wyrażenie: exp1 - exp2
 */
public class Subtraction extends BinaryExpression {
    public Subtraction(Expression exp1, Expression exp2) {
        super(exp1, exp2, '-');
    }
    public static Subtraction of(Expression exp1, Expression exp2) {
        return new Subtraction(exp1, exp2);
    }
    @Override
    protected int operation(int a, int b) {
        return a - b;
    }
}
