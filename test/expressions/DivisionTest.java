package expressions;

import exceptions.MacchiatoRuntimeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest extends BinaryExpressionTest {
    /*@ParameterizedTest
    @CsvSource({
            "1, 1, 1",
            "1, 2, 0",
            "2, 1, 2",
            "0, 1, 0",
    })
    public void testEquals(int left, int right, int expected) {
        // given
        Division division = new Division(new Constant(left), new Constant(right));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> division.evaluate(variables)),
                () -> assertEquals(expected, division.evaluate(variables))
        );
    }*/


    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "0, 0",
    })
    public void testEdgeCases(int left, int right) {
        // given
        Division division = new Division(new Constant(left), new Constant(right));

        // then
        assertThrows(MacchiatoRuntimeException.class, () -> division.evaluate(variables), "Dzielenie przez zero");
    }
}