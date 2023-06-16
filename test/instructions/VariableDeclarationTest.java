package instructions;

import expressions.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableDeclarationTest extends InstructionBaseTest {
    @Test
    public void testIfVariableIsDeclaredCorrectly() {
        // given
        var variableDeclaration = new VariableDeclaration('a', Constant.of(1));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> variableDeclaration.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().value('a'), "Zmienna 'a' powinna istnieć"),
                () -> assertEquals(1, environment.getVariables().value('a'), "Zmienna 'a' powinna być równa 1")
        );
    }
}