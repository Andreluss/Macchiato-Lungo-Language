package expressions;

import exceptions.MacchiatoRuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuloTest extends BinaryExpressionTest {
    @Test
    public void testEvaluate() {
        // given
        Modulo modulo = new Modulo(new Constant(5), new Constant(2));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> modulo.evaluate(variables)),
                () -> assertEquals(1, modulo.evaluate(variables))
        );
    }

    @Test
    public void testModuloZero() {
        // given
        Modulo modulo = new Modulo(new Constant(5), new Constant(0));

        // then
        assertThrows(MacchiatoRuntimeException.class, () -> modulo.evaluate(variables));
    }
}