package instructions;

import expressions.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest extends InstructionBaseTest {
    @Test
    public void testIfNoErrors() {
        // given
        var print = new Print(new Constant(1));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> print.run(environment))
        );
    }
}