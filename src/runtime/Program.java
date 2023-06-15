package runtime;

import exceptions.MacchiatoDebugStopException;
import exceptions.MacchiatoEnvironment;
import exceptions.MacchiatoException;
import instructions.Instruction;
import instructions.InstructionBase;

import java.util.Scanner;

/**
 * Klasa reprezentująca cały program (taki wrapper na najbardziej zewnętrzną instrukcję i środowisko)
 * Umożliwia tworzenie i uruchamianie programów.
 */
public class Program {
    private final Instruction instruction;
    private final MacchiatoEnvironment environment;

    /**
     * Tworzy program wykonujący wszystkie instrukcje z instruction oraz tworzy środowisko uruchomieniowe.
     *
     * @param instruction instrukcja-blok zawierający cały program
     */
    public Program(Instruction instruction) {
        this.instruction = instruction;
        environment = new MacchiatoEnvironment();
    }

    /**
     * Uruchamia program bez debugowania
     */
    public void run() {
        System.out.println("================================");
        System.out.println("Uruchamiamy program w Macchiato!");
        System.out.println("================================");

        try {
            instruction.run(environment);
            System.out.println("Program zakończył się. ");
            System.out.println("Końcowe wartości zmiennych z głównego bloku programu:");
            environment.getLastVariables().print();
        } catch (MacchiatoException e) {
            System.out.println(e.getMessage());
            System.out.println("Wartości zmiennych: ");
            environment.getVariables().print();
            System.out.println("Wadliwa instrukcja: " + environment.getCurrentInstruction());
        }

        System.out.println("================================");
    }

    /**
     * Pomocnicza funkcja, wywoływana po udanym wykonaniu programu w trybie debugowania.
     * Służy do zamknięcia debuggera poleceniem exit wydanym przez użytkownika (jak w treści zadania).
     * Podobnie, jak {@link MacchiatoEnvironment#pauseExecution(InstructionBase)}, czeka na polecenia użytkownika,
     * ale na każde z nich odpowiada, że program się już zakończył.
     */
    private void waitForExitCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("[Debugger]: "); // oczekujemy na wpisanie polecenia
            char c = scanner.next().charAt(0);
            switch (c) {
                case 'c', 's', 'd' -> {
                    System.out.println("Program już się zakończył. ");
                    scanner.nextLine();
                }
                case 'e' -> {
                    System.out.println("Debugger został poprawnie zamknięty.");
                    return;
                }
                default -> {
                    System.out.println("Podano niewłaściwe polecenie. Możliwe opcje to 'c', 's <num>', 'd <num>', 'e')");
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * Uruchamia program z debugowaniem
     */
    public void debug() {
        System.out.println("================================");
        System.out.println("Macchiato Debugger został uruchomiony!");
        System.out.println("================================");

        environment.setMode(MacchiatoEnvironment.Mode.Debug);
        environment.setSteps(0); // dzięki temu pauseExecution() wykona się od razu przy pierwszej instrukcji

        try {
            instruction.run(environment);

            System.out.println("Program zakończył się. ");
            System.out.println("Końcowe wartości zmiennych z głównego bloku programu:");
            environment.getLastVariables().print();

            waitForExitCommand();
        } catch (MacchiatoDebugStopException e) {
            // wiemy, że program został przerwany poleceniem exit
            System.out.println("Program przerwany przez użytkownika. Debuger został zamknięty. ");
        } catch (MacchiatoException e) {
            System.out.println(e.getMessage());
            System.out.println("Wartości zmiennych: ");
            environment.getVariables().print();
            System.out.println("Wadliwa instrukcja: " + environment.getCurrentInstruction());
        }

        System.out.println("================================");
    }
}
