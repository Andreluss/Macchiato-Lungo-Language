package expressions;

import exceptions.MacchiatoRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest extends ExpressionTest {

    @Override @BeforeEach
    public void setup() {
        super.setup();
        variables.create('x', 42);
        variables.create('y', -17);
    }

    @Test
    public void testEquals() {
        // given
        Variable x = new Variable('x');
        Variable y = new Variable('y');
        Variable z = new Variable('z');

        // test if evaluate returns the correct value
        assertAll(
                () -> assertEquals(42, x.evaluate(variables)),
                () -> assertEquals(-17, y.evaluate(variables)),
                () -> assertNotEquals(x.evaluate(variables), y.evaluate(variables)),
                () -> assertThrows(MacchiatoRuntimeException.class,
                        () -> z.evaluate(variables),
                        "Zmienna z nie jest zdefiniowana")

        );
    }
}