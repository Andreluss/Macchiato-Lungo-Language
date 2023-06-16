package runtime;

import instructions.Block;
import instructions.Instruction;

import java.util.Collection;

/**
 * Klasa budująca program.
 * Uwaga: ponieważ program to praktycznie to samo, co blok kodu,
 * w celu niepowtarzania kodu, klasa ProgramBuilder dziedziczy po BlockBuilder
 * i pod spodem tak naprawdę buduje blok.
 * Aby zbudować program, należy na końcu użyć funkcji buildProgram() zamiast build().
 */
public class ProgramBuilder extends BlockBuilder {
    public Program buildProgram() {
        return new Program(build());
    }
}