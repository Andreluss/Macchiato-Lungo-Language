package instructions;

import builders.BlockBuilder;
import builders.IfBuilder;
import expressions.Constant;
import expressions.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfTest extends InstructionBaseTest {
    @Test
    public void testControlFlow() {
        // given
        environment.getVariables().create('a', 77);
        var ifInstruction = new IfBuilder()
                .ifHolds(Variable.named('a'), If.ComparisonType.Less, Constant.of(0))
                .then(new BlockBuilder()
                        .assign('a', Constant.of(-1))
                        .build())
                .elseThen(new BlockBuilder()
                        .assign('a', Constant.of(1))
                        .build())
                .build();

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> ifInstruction.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().value('a'), "Zmienna 'a' powinna istnieć"),
                () -> assertEquals(1, environment.getVariables().value('a'), "Zmienna 'a' powinna być równa 1")
        );
    }
}