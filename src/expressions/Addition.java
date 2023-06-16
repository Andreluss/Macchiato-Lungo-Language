package expressions;

/**
 * Wyrażenie: exp1 + exp2
 */
public class Addition extends BinaryExpression {
    public Addition(Expression exp1, Expression exp2) {
        super(exp1, exp2, '+');
    }
    public static Addition of(Expression exp1, Expression exp2) {
        return new Addition(exp1, exp2);
    }
    @Override
    protected int operation(int a, int b) {
        return a + b;
    }
}
