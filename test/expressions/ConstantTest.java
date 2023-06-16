package expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantTest extends ExpressionTest {
    @Test
    void testEquals() {
        // given
        Constant constant = new Constant(0);
        // then
        assertEquals(constant.evaluate(variables), 0);
    }
}