package instructions;

import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;

import java.util.Collection;

/**
 * Deklaracja procedury w Macchiato.
 */
public class ProcedureDeclaration extends Declaration {
    private final String name;
    private final Collection<Character> parameters;
    private final Block body;

    /**
     * Tworzy deklarację procedury o nazwie name,
     * o parametrach z listy parameters.
     * Procedura po wywołaniu inicjalizuje parametry
     * i wykonuje blok kodu zawarty w zmiennej body.
     * @param name nazwa procedury
     * @param parameters nazwy kolejnych parametrów
     * @param body kod procedury
     */
    public ProcedureDeclaration(String name, Collection<Character> parameters, Block body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public void run(MacchiatoEnvironment environment) throws MacchiatoException {
        // na początku wykonujemy funkcje związane ze środowiskiem
        environment.pauseExecutionIfNeeded(this);
        environment.countNextStep(this);

        // ... todo
    }

    @Override
    public String toString() {
        return null; // todo
    }
}
