package runtime;

import builders.BlockBuilder;
import builders.IfBuilder;
import builders.ProgramBuilder;
import expressions.*;
import instructions.*;

import java.util.List;

// Zadanie zaliczeniowe 2 z Programowania Obiektowego
// Autor rozwiązania: Andrzej Jabłoński
public class Main {

    public static void main(String[] args) {
        // Tworzymy i uruchamiamy programy z treści zadania.
        initPrograms();

        programNew.debug();

        if (false) {
            programOG.run();
            program_v2.debug();
            program_v3.debug();
        }

    }

    private static void initPrograms() {
        programNew = new ProgramBuilder()
                .declareVariable('x', Constant.of(101))
                .declareVariable('y', Constant.of(1))
                .declareProcedure("out", List.of('a'), new BlockBuilder()
                        .print(Addition.of(Variable.named('a'), Variable.named('x')))
                        .build()
                )
                .assign('x', Subtraction.of(Variable.named('x'), Variable.named('y')))
                .invoke("out", List.of(Variable.named('x')))
                .invoke("out", List.of(Constant.of(100)))
                .block(new BlockBuilder()
                        .declareVariable('x', Constant.of(10))
                        .invoke("out", List.of(Constant.of(100)))
                        .build()
                )
                .buildProgram();

        programOG = new Program(new Block(
                new Declaration[]{
                        new VariableDeclaration('n', new Constant(30))
                },
                new Instruction[]{
                        new For('k', new Subtraction(new Variable('n'), new Constant(1)),
                                new Instruction[]{new Block(
                                        new Declaration[]{
                                                new VariableDeclaration('p', new Constant(1))},
                                        new Instruction[]{
                                                new Assignment('k', new Addition(new Variable('k'), new Constant(2))),
                                                new For('i', new Subtraction(new Variable('k'), new Constant(2)),
                                                        new Instruction[]{
                                                                new Assignment('i', new Addition(new Variable('i'), new Constant(2))),
                                                                new If(new Modulo(new Variable('k'), new Variable('i')), If.ComparisonType.Equal, new Constant(0),
                                                                        new Instruction[]{
                                                                                new Assignment('p', new Constant(0))
                                                                        })
                                                        }),
                                                new If(new Variable('p'), If.ComparisonType.Equal, new Constant(1),
                                                        new Instruction[]{
                                                                new Print(new Variable('k'))
                                                        })
                                        }
                                )}
                        )
                }
        ));

        program_v2 = new Program(new Block(
                new Declaration[]{
                        new ProcedureDeclaration("prt", List.of(new Character[]{'x'}),
                                new Block(new Declaration[]{}, new Instruction[]{
                                        new Print(new Multiplication(new Constant(11), new Variable('x')))
                                })),
                        new VariableDeclaration('a', new Constant(69))
                },
                new Instruction[]{
                        new ProcedureCall("prt", List.of(new Expression[]{new Constant(10)})),
                        new ProcedureCall("prt", List.of(new Expression[]{new Variable('a')})),
                }
        ));

        program_v3 = new ProgramBuilder()
                .declareVariable('c', Constant.of(42))
                .checkCondition(new IfBuilder()
                        .ifHolds(Variable.named('c'), If.ComparisonType.Greater, Constant.of(68))
                        .then(new BlockBuilder().build())
                        .elseThen(new BlockBuilder()
                                .print(new Constant(-1)).build())
                        .build()
                )
                .buildProgram();
    }

    private static Program programNew;
    private static Program programOG;
    private static Program program_v2;
    private static Program program_v3;
}