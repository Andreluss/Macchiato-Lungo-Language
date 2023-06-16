package builders;

import expressions.Expression;
import instructions.*;
import runtime.Program;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Klasa budująca blok w programie.
 */
public class BlockBuilder {
    protected final ArrayList<Declaration> declarations;
    protected final ArrayList<Instruction> instructions;

    /**
     * Tworzy builder bloku kodu w Macchiato.
     */
    public BlockBuilder() {
        declarations = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    /**
     * @return Buduje blok kodu w Macchiato. <br>
     * Uwaga (ważna w ProgramBuilder): do zbudowania <b>programu</b> należy użyć funkcji buildProgram().
     */
    public Block build() {
        return new Block(declarations.toArray(new Declaration[0]), instructions.toArray(new Instruction[0]));
    }

    /**
     * Buduje program z obecnego bloku kodu w Macchiato.
     * @return stworzony program
     */
    public Program buildProgram() {
        return new Program(build());
    }

    public BlockBuilder assign(char variable, Expression expression) {
        instructions.add(new Assignment(variable, expression));
        return this;
    }

    public BlockBuilder block(Block block) {
        instructions.add(block);
        return this;
    }

    public BlockBuilder forLoop(char variable, Expression iterationsCount, Block block) {
        instructions.add(new For(variable, iterationsCount, block));
        return this;
    }

    public BlockBuilder checkCondition(If ifInstruction) {
        instructions.add(ifInstruction);
        return this;
    }

    public BlockBuilder print(Expression expression) {
        instructions.add(new Print(expression));
        return this;
    }

    public BlockBuilder invoke(String procedure, Collection<Expression> arguments) {
        instructions.add(new ProcedureCall(procedure, arguments));
        return this;
    }

    public BlockBuilder declareProcedure(String name, Collection<Character> parameters, Block body) {
        assert instructions.size() == 0;
        declarations.add(new ProcedureDeclaration(name, parameters, body));
        return this;
    }

    public BlockBuilder declareVariable(char name, Expression expression) {
        assert instructions.size() == 0; // Zakładamy, że wszystkie deklaracje sa przed instrukcjami
        declarations.add(new VariableDeclaration(name, expression));
        return this;
    }
}
