package expressions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest extends BinaryExpressionTest {
    public static Stream<TestTriple> testDataProvider() {
        return Stream.of(
                new TestTriple(1, 1, 0),
                new TestTriple(1, 2, -1),
                new TestTriple(2, 1, 1),
                new TestTriple(0, 1, -1),
                new TestTriple(-2, -2, 0),
                new TestTriple(-2, 2, -4),
                new TestTriple(2, -2, 4)
        );
    }
    @ParameterizedTest
    @MethodSource("testDataProvider")
    public void testEquals(TestTriple triple) {
        // given
        Subtraction subtraction = new Subtraction(new Constant(triple.left), new Constant(triple.right));

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> subtraction.evaluate(variables)),
                () -> assertEquals(triple.expected, subtraction.evaluate(variables))
        );
    }
}