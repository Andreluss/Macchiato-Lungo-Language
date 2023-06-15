package instructions;

import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;
import exceptions.MacchiatoRuntimeException;
import expressions.Expression;

import java.util.Collection;
import java.util.Iterator;

/**
 * Instrukcja: wywołanie procedury
 */
public class ProcedureCall extends Instruction {
    private final String name;
    private final Collection<Expression> arguments;

    /**
     * Tworzy wywołanie procedury
     * @param name nazwa procedury
     * @param arguments lista argumentów — wartości przekazywanych do funkcji
     */
    public ProcedureCall(String name, Collection<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        environment.pauseExecutionIfNeeded(this);
        environment.pushStackFrame();

        // bierzemy referencję do aktualnej procedury o podanej nazwie
        ProcedureDeclaration procedure = environment.getVariables().getProcedure(name);

        // sprawdzamy, czy liczba argumentów się zgadza
        int count = procedure.getParameters().size();
        if (procedure.getParameters().size() != arguments.size()) {
            throw new MacchiatoRuntimeException(
                    "Procedurze " + name + " podano złą ilość argumentów. " +
                    "Podano " + count + ", oczekiwano " + arguments.size());
        }

        // inicjalizujemy parametry procedury podanymi argumentami
        Iterator<Character> parameter = procedure.getParameters().iterator();
        Iterator<Expression> argument = arguments.iterator();
        while(parameter.hasNext() && argument.hasNext()) {
            new VariableDeclaration(parameter.next(), argument.next()).run(environment);
        }

        // i finalnie wykonujemy kod naszej procedury.
        procedure.getBody().run(environment);

        environment.popStackFrame();
    }

    @Override
    public String toString() {
        String args = arguments.toString(); // konwertujemy do postaci [a, b, c]
        args = args.substring(1, args.length() - 1); // obcinamy nawiasy
        return name + "(" + args +  ")";
    }
}
