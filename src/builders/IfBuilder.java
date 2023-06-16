package builders;

import expressions.Expression;
import instructions.Block;
import instructions.If;
import instructions.Instruction;

public class IfBuilder {
    private Expression left;
    private If.ComparisonType comp;
    private Expression right;
    private Block codeIf;
    private Block codeElse;

    public IfBuilder ifHolds(Expression left, If.ComparisonType comp, Expression right) {
        this.left = left;
        this.comp = comp;
        this.right = right;
        this.codeIf = null;
        this.codeElse = null;
        return this;
    }
    public IfBuilder then(Block codeIf) {
        this.codeIf = codeIf;
        return this;
    }
    public IfBuilder elseThen(Block codeElse) {
        this.codeElse = codeElse;
        return this;
    }
    public If build() {
        assert codeIf != null; // Zakładamy, że codeIf nie będzie null
        Instruction[] instructionsIf = new Instruction[]{codeIf};
        Instruction[] instructionsElse = null;
        if(codeElse != null)
            instructionsElse = new Instruction[] {codeElse};
        return new If(left, comp, right, instructionsIf, instructionsElse);
    }

}
