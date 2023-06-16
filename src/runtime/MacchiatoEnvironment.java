package runtime;

import exceptions.MacchiatoDebugStopException;
import instructions.InstructionBase;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * <p> Klasa reprezentująca środowisko, w którym "uruchamiamy" program. </p>
 * <p> Środowisko przechowuje i udostępnia informacje: <br>
 *     - o aktualnym wartościowaniu zmiennych, <br>
 *     - trybie uruchomienia programu (run/debug) <br>
 *     - oraz limicie instrukcji do następnego zapauzowania (ważne w trybie debugowania) <br>
 * </p>
 */
public class MacchiatoEnvironment {
    /**
     * Tryb uruchomienia programu — bez debugowania (Run) i z debugowaniem (Debug)
     */
    public enum Mode {
        Run, Debug
    }
    private Mode mode;
    private int steps;
    private final Stack<Variables> stack;
    private InstructionBase currentInstruction;

    /**
     * @return tryb uruchomienia programu
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * @param mode nowy tryb uruchomienia programu
     */
    public void setMode(Mode mode) {
        this.mode = mode;
        if(mode == Mode.Run) {
            steps = Integer.MAX_VALUE;
        }
    }

    /**
     * @return liczba kroków do następnego zapauzowania
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Ustawia liczbę kroków do następnego zapauzowania.
     * @param steps nowa liczba kroków
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Funkcja rejestruje w środowisku wykonanie nowej instrukcji.
     * Zapisuje ją oraz aktualizuje licznik kroków do następnego zapauzowania.
     * @param currentInstruction aktualnie wykonywana instrukcja
     */
    public void countNextStep(InstructionBase currentInstruction) {
        this.currentInstruction = currentInstruction;
        this.steps--;
    }

    /**
     * Pauzuje wykonanie programu, a następnie pyta o kolejne kroki i obsługuje polecenia użytkownika.
     * Kończy wykonanie, dopiero gdy użytkownik wyda polecenie step lub exit.
     * @param nextInstruction instrukcja, przed którą zatrzymał się debugger
     * @return true, jeśli program ma kontynuować wykonanie
     * lub false, jeśli wywołano exit, czyli program ma się skończyć natychmiast
     */
    public boolean pauseExecution(InstructionBase nextInstruction) {
        System.out.println("Program zapauzowany. Następny krok to " + nextInstruction);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("[Debugger]: "); // oczekujemy na wpisanie polecenia
                char c = scanner.next().charAt(0);
                switch (c) {
                    case 'c' -> {
                        setMode(Mode.Run);
                        return true; // kontynuuj wykonanie programu
                    }
                    case 's' -> {
                        setSteps(scanner.nextInt());
                        return true; // kontynuuj wykonanie programu
                    }
                    case 'd' -> {
                        int levels = scanner.nextInt();
                        if(levels < stack.size()) {
                            stack.get(stack.size() - levels - 1).print(); // który od góry element stosu
                        }
                        else {
                            System.out.println("Za duża wartość zagłębienia (aktualnie powinna być < " + stack.size() + ").");
                        }
                    }
                    case 'm' -> {
                        String path = scanner.next(); // ścieżka do pliku
                        if(stack.isEmpty()) {
                            System.out.println("Stos wykonania programu jest pusty - zrzut pamięci nie został wykonany. ");
                        }
                        else {
                            Variables variables = stack.peek();
                            variables.dumpToFile(path);
                        }
                    }
                    case 'e' -> {
                        return false; // przerwij wykonanie programu
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Podano niewłaściwe polecenie. Możliwe opcje to 'c', 's <num>', 'd <num>', 'm <text>', 'e')");
                scanner.nextLine();
            }
        }
    }

    /**
     * @return ostatnie zapisane wartości zmiennych w środowisku programu
     */
    public Variables getLastVariables() {
        return lastVariables;
    }

    /**
     * @return aktualnie wykonywana instrukcja
     */
    public InstructionBase getCurrentInstruction() {
        return currentInstruction;
    }

    /**
     * <p>Sprawdza, czy program wykonywany w środowisku powinien się zapauzować
     * przed wykonaniem aktualnej instrukcji nextInstruction.</p>
     * <p>Jeśli powinien, to zostaje wywołana funkcja {@link #pauseExecution(InstructionBase)},
     * która zatrzymuje program i pyta użytkownika o następne kroki. <br>
     * Jeśli tam użytkownik wpisał polecenie e(xit), to wtedy pauseExecution zwraca wartość false,
     * a wtedy nasza funkcja rzuca wyjątek {@link MacchiatoDebugStopException},
     * który w wygodny sposób przerywa wykonywanie programu. <br>
     * <small>(Oczywiście mogłaby też zwracać tę informację jako boolean,
     * ale wtedy wszystkie funkcje run(environment) również musiałyby tę informację przekazywać
     * i na każdym poziomie rekurencji wykonywać dodatkowe sprawdzenie, czy właśnie wykonane run
     * nie chciało przerwać programu)</small></p>
     *
     * @param nextInstruction następna instrukcja do wykonania
     * @throws MacchiatoDebugStopException jeśli program powinien się zatrzymać
     */
    public void pauseExecutionIfNeeded(InstructionBase nextInstruction) throws MacchiatoDebugStopException {
        if(getMode() == Mode.Debug && getSteps() == 0) {
            // musimy zatrzymać się w tym miejscu, tuż przed wykonaniem nextInstruction
            if(!pauseExecution(nextInstruction)) {
                // jeśli mamy zatrzymać program (polecenie exit)
                throw new MacchiatoDebugStopException();
            }
        }
    }


    /**
     * Tworzy nowe środowisko do uruchamiania programów w Macchiato.
     * Domyślnie ustawia tryb na uruchamianie bez debugowania.
     */
    public MacchiatoEnvironment() {
        this.stack = new Stack<>();
        setMode(Mode.Run);
    }

    /**
     * @return informacje o zmiennych z aktualnego zagłębienia
     */
    public Variables getVariables() {
        return stack.peek();
    }

    /**
     * Wchodzi na kolejny poziom zagłębienia i przysłaniania nazw zmiennych.
     */
    public void pushStackFrame() {
        if(stack.isEmpty()) {
            stack.push(new Variables());
        }
        else {
            assert(stack.peek() != null);
            stack.push(new Variables(stack.peek()));
        }
    }
    private Variables lastVariables;

    /**
     * Wychodzi z aktualnego poziomu zagłębienia nazw zmiennych.
     */
    public void popStackFrame() {
        lastVariables = stack.peek();
        stack.pop();
    }
}
