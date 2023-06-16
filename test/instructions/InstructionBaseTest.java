package instructions;

import org.junit.jupiter.api.*;
import runtime.MacchiatoEnvironment;

public class InstructionBaseTest {
    protected MacchiatoEnvironment environment;
    @BeforeEach
    public void setup() {
        environment = new MacchiatoEnvironment();
        environment.setMode(MacchiatoEnvironment.Mode.Run);
        environment.pushStackFrame();
    }
}
