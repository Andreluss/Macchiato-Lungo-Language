package instructions;

import builders.BlockBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedureDeclarationTest extends InstructionBaseTest {
    @Test
    public void testIsProcedureIsDeclaredProperly() {
        // given
        var procedureDeclaration = new ProcedureDeclaration("test", List.of('x', 'y'), new BlockBuilder().build());

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> procedureDeclaration.run(environment)),
                () -> assertDoesNotThrow(() -> environment.getVariables().getProcedure("test")),
                () -> assertEquals(procedureDeclaration, environment.getVariables().getProcedure("test")),
                () -> assertEquals(2, environment.getVariables().getProcedure("test").getParameters().size(),
                        "Procedura powinna mieć 2 parametry")
        );

    }
}