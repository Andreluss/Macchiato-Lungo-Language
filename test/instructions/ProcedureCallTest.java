package instructions;

import builders.BlockBuilder;
import expressions.Addition;
import expressions.Constant;
import expressions.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedureCallTest extends InstructionBaseTest {
    @Test
    public void testIfProcedureIsActuallyCalledAndThrowsNoErrors() {
        // given
        environment.getVariables().create('a', 4);
        environment.getVariables().createProcedure("add",
                new ProcedureDeclaration("add", List.of('x'), new BlockBuilder()
                .assign('a', Addition.of(Variable.named('a'), Variable.named('x')))
                .build())
        );
        var call = new ProcedureCall("add", List.of(Constant.of(6)));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> call.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().value('a'), "Zmienna 'a' powinna istnieć."),
                () -> assertEquals(10,
                        environment.getVariables().value('a'),
                        "Zmienna 'a' powinna mieć wartość 10 = 4 + 6.")
        );
    }
}