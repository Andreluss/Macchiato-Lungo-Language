package runtime;

import expressions.Expression;
import instructions.*;

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

    public BlockBuilder declareVariable(char name, Expression expression) {
        // todo check instructions.size()
        declarations.add(new VariableDeclaration(name, expression));
        return this;
    }

    public BlockBuilder declareProcedure(String name, Collection<Character> parameters, Block body) {
        declarations.add(new ProcedureDeclaration(name, parameters, body));
        return this;
    }

    public BlockBuilder print(Expression expression) {
        instructions.add(new Print(expression));
        return this;
    }

    public BlockBuilder assign(char variable, Expression expression) {
        instructions.add(new Assignment(variable, expression));
        return this;
    }

    public BlockBuilder invoke(String procedure, Collection<Expression> arguments) {
        instructions.add(new ProcedureCall(procedure, arguments));
        return this;
    }
}
