package instructions;

import exceptions.MacchiatoException;
import runtime.MacchiatoEnvironment;

/**
 * Klasa bazowa, reprezentująca instrukcję lub deklarację.
 * Zawiera nagłówki podstawowych funkcji, jakie musi udostępniać, aby można było ją wykonać i wyświetlać.
 */
public abstract class InstructionBase {
    /**
     * <p>Funkcja wykonuje tę instrukcję (lub deklarację) w danym środowisku {@link MacchiatoEnvironment} environment. </p>
     * <p>Na początku każdego wywołania <b>powinna</b>:<br>
     * 1. Wywoływać funkcję {@link MacchiatoEnvironment#pauseExecutionIfNeeded(InstructionBase)},
     * aby sprawdzić, czy wykonanie programu nie powinno się zatrzymać właśnie przed tą instrukcją. <br>
     * 2. Wywoływać funkcję {@link MacchiatoEnvironment#countNextStep(InstructionBase)},
     * aby zarejestrować w środowisku wykonanie tej instrukcji (policzyć ją jako wykonanie kolejnego kroku).
     * </p>
     * @param environment środowisko uruchomieniowe
     * @throws MacchiatoException jeśli podczas wykonania wystąpił błąd lub program został zapauzowany
     */
    public abstract void run(MacchiatoEnvironment environment) throws MacchiatoException;

    /**
     * @return Instrukcja w skróconej formie tekstowej.
     */
    @Override // wymuszamy implementację toString() w pochodnych klasach
    public abstract String toString();
}
