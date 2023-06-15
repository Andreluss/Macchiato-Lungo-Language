package runtime;

import expressions.*;
import instructions.*;

import java.util.List;

// Zadanie zaliczeniowe 2 z Programowania Obiektowego
// Autor rozwiązania: Andrzej Jabłoński
public class Main {
    public static void main(String[] args) {
        // Tworzymy program z treści zadania.
        Program program = new Program(new Block(
                new Declaration[] {
                        new VariableDeclaration('n', new Constant(30))
                },
                new Instruction[] {
                        new For('k', new Subtraction(new Variable('n'), new Constant(1)),
                                new Instruction[] { new Block(
                                        new Declaration[] {
                                                new VariableDeclaration('p', new Constant(1))},
                                        new Instruction[] {
                                                new Assignment('k', new Sum(new Variable('k'), new Constant(2))),
                                                new For('i', new Subtraction(new Variable('k'), new Constant(2)),
                                                        new Instruction[] {
                                                                new Assignment('i', new Sum(new Variable('i'), new Constant(2))),
                                                                new If(new Modulo(new Variable('k'), new Variable('i')), If.ComparisonType.Equal, new Constant(0),
                                                                        new Instruction[] {
                                                                                new Assignment('p', new Constant(0))
                                                                        })
                                                        }),
                                                new If(new Variable('p'), If.ComparisonType.Equal, new Constant(1),
                                                        new Instruction[] {
                                                                new Print(new Variable('k'))
                                                        })
                                        }
                                )}
                        )
                }
        ));

        program.run();

        program = new Program(new Block(
                new Declaration[] {
                        new ProcedureDeclaration("prt", List.of(new Character[]{'x'}),
                                new Block(new Declaration[]{}, new Instruction[]{
                                        new Print(new Multiplication(new Constant(11), new Variable('x')))
                                })),
                        new VariableDeclaration('a', new Constant(69))
                },
                new Instruction[] {
                        new ProcedureCall("prt", List.of(new Expression[]{new Constant(10)})),
                        new ProcedureCall("prt", List.of(new Expression[]{new Variable('a')})),
                }
        ));

        // uruchamiamy bez debugowania
//        program.run();

        // uruchamiamy z debugowaniem
         program.debug();


    }
}