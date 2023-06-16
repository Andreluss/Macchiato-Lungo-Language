package instructions;

import expressions.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest extends InstructionBaseTest {
    @Test
    public void testDoesNotThrowAndEquals() {
        // given
        environment.getVariables().create('x', 44);

        // when
        var assignment = new Assignment('x', new Constant(1));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> assignment.run(environment)),
                () -> assertEquals(1, environment.getVariables().value('x'))
        );
    }


}