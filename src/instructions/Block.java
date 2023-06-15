package instructions;

import exceptions.MacchiatoException;
import runtime.MacchiatoEnvironment;

/**
 * Instrukcja: blok kodu w Macchiato
 */
public class Block extends Instruction {
    private final Declaration[] declarations;
    private final Instruction[] instructions;

    /**
     * Tworzy blok kodu zawierający na początku
     * (być może pustą, ale != null) listę deklaracji declarations
     * oraz listę instrukcji instructions do wykonania
     * @param declarations lista deklaracji (nie null!)
     * @param instructions lista instrukcji (nie null!)
     */
    public Block(Declaration[] declarations, Instruction[] instructions) {
        this.declarations = declarations;
        this.instructions = instructions;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.pushStackFrame();
        for(Declaration declaration : declarations) {
            declaration.run(environment);
        }
        for(Instruction instruction : instructions) {
            instruction.run(environment);
        }
        environment.popStackFrame();
    }

    @Override
    public String toString() {
        String result = "{ ";
        if(declarations != null && declarations.length > 0) {
            result += declarations[0] + "; ... ";
        }
        else if(instructions != null && instructions.length > 0) {
            result += instructions[0] + "; ... ";
        }
        result += "}";
        return result;
    }
}
