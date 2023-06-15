package expressions;

import exceptions.MacchiatoRuntimeException;
import instructions.ProcedureDeclaration;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa trzymająca informacje o zmiennych i procedurach w aktualnym poziomie zagłębienia w programie.
 */
public class Variables {
    private static class Var {
        private int value;
        public Var(int value) {
            this.value = value;
        }
    }
    private final Var[] vars;
    private final Map<String, ProcedureDeclaration> procs;

    /**
     * Tworzy nowy zestaw informacji o zmiennych — domyślnie wszystkie nie istnieją
     */
    public Variables() {
        this.vars = new Var[26]; // wszystkie są null'ami
        this.procs = new HashMap<>(); // nie ma żadnych zadeklarowanych procedur
    }

    /**
     * Tworzy nową informację o zmiennych, używając już zdefiniowanych zmiennych w previousVariables
     * @param previousVariables zmienne z poprzedniego zagłębienia
     */
    public Variables(Variables previousVariables) {
        this.vars = new Var[26];
        // kopiujemy referencje do zmiennych:
        System.arraycopy(previousVariables.vars, 0, vars, 0, 26);
        // kopiujemy referencje do (deklaracji) procedur:
        this.procs = new HashMap<>(previousVariables.procs);
    }
    private int idx(char name) {
        return name - 'a';
    }
    private Var get(char name) throws MacchiatoRuntimeException {
        if(vars[idx(name)] == null)
            throw new MacchiatoRuntimeException("Zmienna " + name + " nie istnieje w bieżącym kontekście!");
        return vars[idx(name)];
    }


    /**
     * Tworzy nową procedurę (być może przysłaniając poprzednią).
     * @param name nazwa procedury
     * @param procedureDeclaration referencja do deklaracji procedury
     */
    public void createProcedure(String name, ProcedureDeclaration procedureDeclaration) {
        procs.put(name, procedureDeclaration);
    }

    /**
     * Zwraca deklarację procedury o podanej nazwie w bieżącym kontekście.
     * @param name nazwa procedury
     * @return deklaracja procedury o podanej nazwie
     * @throws MacchiatoRuntimeException jeśli procedura o tej nazwie nie instnieje
     */
    public ProcedureDeclaration getProcedure(String name) throws MacchiatoRuntimeException {
        ProcedureDeclaration procedure = procs.get(name);
        if(procedure == null)
            throw new MacchiatoRuntimeException("Procedura " + name + " nie istnieje w bieżącym kontekście!");
        return procedure;
    }

    /**
     * Tworzy nową zmienną (być może przysłaniając).
     * @param name nazwa zmiennej
     * @param value wartość
     */
    public void create(char name, int value) {
        vars[idx(name)] = new Var(value); // przysłaniamy lub tworzymy nową zmienną
    }

    /**
     * Funkcja przypisuje wartość value do zmiennej name,
     * zakładając, że taka zmienna już istnieje
     * @param name nazwa zmiennej
     * @param value przypisywana wartość
     * @throws MacchiatoRuntimeException jeśli zmienna o takiej nazwie nie istnieje
     */
    public void assign(char name, int value) throws MacchiatoRuntimeException {
        get(name).value = value;
    }

    /**
     * Zwraca aktualną wartość zmiennej name, chyba że taka nie istnieje.
     * @param name nazwa zmiennej
     * @return wartość zmiennej name
     * @throws MacchiatoRuntimeException jeśli taka zmienna nie istnieje
     */
    public int value(char name) throws MacchiatoRuntimeException {
        return get(name).value;
    }

    /**
     * Wyświetla wartości wszystkich istniejących zmiennych.
     */
    public void print() {
        for (int i = 0; i < 26; i++) {
            if (vars[i] != null) {
                char name = (char) (i + 'a');
                System.out.println("-> " + name + " = " + vars[i].value);
            }
        }
    }
}
