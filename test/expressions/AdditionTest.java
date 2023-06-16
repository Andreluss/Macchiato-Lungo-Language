package expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest extends BinaryExpressionTest {
    @Test
    public void testDoesNotThrowAndEquals() {
        // given
        var addition = new Addition(new Constant(1), new Constant(2));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> addition.evaluate(variables)),
                () -> assertEquals(3, addition.evaluate(variables))
        );
    }
}
