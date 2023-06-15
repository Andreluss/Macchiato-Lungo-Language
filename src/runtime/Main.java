package runtime;

import expressions.Constant;
import expressions.Modulo;
import expressions.Subtraction;
import expressions.Sum;
import instructions.*;

// Zadanie zaliczeniowe 2 z Programowania Obiektowego
// Autor rozwiązania: Andrzej Jabłoński
public class Main {
    public static void main(String[] args) {
        // Tworzymy program z treści zadania.
        Program program = new Program(new Block(
                new Declaration[] {
                        new Declaration('n', new Constant(30))
                },
                new Instruction[] {
                        new For('k', new Subtraction(new Variable('n'), new Constant(1)),
                                new Instruction[] { new Block(
                                        new Declaration[] {
                                                new Declaration('p', new Constant(1))},
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

        // uruchamiamy bez debugowania
//        program.run();

        // uruchamiamy z debugowaniem
         program.debug();
    }
}