package expressions;

import exceptions.MacchiatoRuntimeException;

/**
 * Wyrażenie: exp1 modulo exp2
 */
public class Modulo extends BinaryExpression {
    public Modulo(Expression exp1, Expression exp2) {
        super(exp1, exp2, '%');
    }
    public static Modulo of(Expression exp1, Expression exp2) {
        return new Modulo(exp1, exp2);
    }
    @Override
    protected int operation(int a, int b) throws MacchiatoRuntimeException {
        if(b == 0) {
            throw new MacchiatoRuntimeException("expressions.Modulo zero - wyrażenie " + this);
        }
        return a % b;
    }
}
