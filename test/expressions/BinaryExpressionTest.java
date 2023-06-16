package expressions;

public class BinaryExpressionTest extends ExpressionTest {
    public static class TestTriple {
        public final int left;
        public final int right;
        public final int expected;

        public TestTriple(int left, int right, int expected) {
            this.left = left;
            this.right = right;
            this.expected = expected;
        }
    }
}
