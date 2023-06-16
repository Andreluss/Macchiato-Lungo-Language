package instructions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest extends InstructionBaseTest {
    @Test
    public void testStackAndNoExceptions() {
        // given
        environment.getVariables().create('a', 1);
        var block = new Block(new Declaration[]{}, new Instruction[]{});

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> block.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().value('a')),
                () -> assertEquals(1, environment.getVariables().value('a'))
        );
    }
}