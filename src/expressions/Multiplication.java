package expressions;

/**
 * Wyrażenie: exp1 * exp2
 */
public class Multiplication extends BinaryExpression {
    public Multiplication(Expression exp1, Expression exp2) {
        super(exp1, exp2, '*');
    }

    @Override
    protected int operation(int a, int b) {
        return a * b;
    }
}
