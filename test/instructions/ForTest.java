package instructions;

import builders.BlockBuilder;
import expressions.Addition;
import expressions.Constant;
import expressions.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForTest extends InstructionBaseTest {
    @Test
    public void testExecutionForExpressionEqualZero() {
        // given
        environment.getVariables().create('a', 0);
        var block = new BlockBuilder()
                .forLoop('i', Constant.of(0),
                        new BlockBuilder()
                        .assign('a', new Addition(new Variable('a'), Constant.of(1)))
                        .build())
                .build();

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> block.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().value('a')),
                () -> assertEquals(0, environment.getVariables().value('a'))
        );
    }
}