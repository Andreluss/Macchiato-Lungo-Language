package expressions;

import exceptions.MacchiatoRuntimeException;

/**
 * Wyrażenie: exp1 / exp2
 */
public class Division extends BinaryExpression {
    public Division(Expression exp1, Expression exp2) {
        super(exp1, exp2, ':');
    }
    public static Division of(Expression exp1, Expression exp2) {
        return new Division(exp1, exp2);
    }
    @Override
    protected int operation(int a, int b) throws MacchiatoRuntimeException {
        if(b == 0) {
            throw new MacchiatoRuntimeException("Dzielenie przez zero - wyrażenie \"" + exp2 + "\"");
        }
        return a / b;
    }
}
