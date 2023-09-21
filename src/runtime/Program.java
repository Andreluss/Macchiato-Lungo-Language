package runtime;

import exceptions.MacchiatoDebugStopException;
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
        System.out.println("Your Macchiato program is starting!");
        System.out.println("================================");

        try {
            instruction.run(environment);
            System.out.println("The program has ended. ");
            System.out.println("Final values of variables from the main block of the program:");
            environment.getLastVariables().print();
        } catch (MacchiatoException e) {
            System.out.println(e.getMessage());
            System.out.println("Variables: ");
            environment.getVariables().print();
            System.out.println("Faulty instruction: " + environment.getCurrentInstruction());
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
                case 'c', 's', 'd', 'm' -> {
                    System.out.println("Program has already ended. ");
                    scanner.nextLine();
                }
                case 'e' -> {
                    System.out.println("Debugger has been closed correctly.");
                    return;
                }
                default -> {
                    System.out.println("An incorrect command was given. " +
                                       "The possible options are 'c', 's <num>', 'd <num>', 'm <text>', 'e')");
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
        System.out.println("Macchiato Debugger is running!");
        System.out.println("================================");

        environment.setMode(MacchiatoEnvironment.Mode.Debug);
        environment.setSteps(0); // dzięki temu pauseExecution() wykona się od razu przy pierwszej instrukcji

        try {
            instruction.run(environment);

            System.out.println("Program has ended. ");
            System.out.println("Final values of variables from the main block of the program:");
            environment.getLastVariables().print();

            waitForExitCommand();
        } catch (MacchiatoDebugStopException e) {
            // wiemy, że program został przerwany poleceniem exit
            System.out.println("The program was interrupted by the user. The debugger was closed. ");
        } catch (MacchiatoException e) {
            System.out.println(e.getMessage());
            System.out.println("Variables: ");
            environment.getVariables().print();
            System.out.println("Faulty instruction: " + environment.getCurrentInstruction());
        }

        System.out.println("================================");
    }
}
