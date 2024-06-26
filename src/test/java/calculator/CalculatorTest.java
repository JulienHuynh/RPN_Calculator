package calculator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private final CalculatorRPN calculator = new CalculatorRPN();

    @Test
    public void testAddition() throws Exception {
        assertEquals(5.0, calculator.evaluateRPN("2 3 +"), 0.001);
    }

    @Test
    public void testSubtraction() throws Exception {
        assertEquals(1.0, calculator.evaluateRPN("4 3 -"), 0.001);
    }

    @Test
    public void testMultiplication() throws Exception {
        assertEquals(12.0, calculator.evaluateRPN("3 4 *"), 0.001);
    }

    @Test
    public void testCombinedOperations() throws Exception {
        assertEquals(14.0, calculator.evaluateRPN("5 1 2 + 4 * + 3 -"), 0.001);
    }

    @Test
    public void testSingleNumber() throws Exception {
        assertEquals(7.0, calculator.evaluateRPN("7"), 0.001);
    }

    @Test
    public void testInvalidExpressionNotEnoughOperandsForAddition() {
        try {
            calculator.evaluateRPN("3 +");
            Assert.fail("Une exception aurait dû être levée.");
        } catch (Exception e) {
            assertEquals("Erreur: pas assez d'opérandes.", e.getMessage());
        }
    }

    @Test
    public void testInvalidExpressionNotEnoughOperandsForSoustraction() {
        try {
            calculator.evaluateRPN("3 -");
            Assert.fail("Une exception aurait dû être levée.");
        } catch (Exception e) {
            assertEquals("Erreur: pas assez d'opérandes.", e.getMessage());
        }
    }

    @Test
    public void testInvalidExpressionNotEnoughOperandsForMultiplication() {
        try {
            calculator.evaluateRPN("3 *");
            Assert.fail("Une exception aurait dû être levée.");
        } catch (Exception e) {
            assertEquals("Erreur: pas assez d'opérandes.", e.getMessage());
        }
    }


    @Test
    public void testInvalidExpressionTooManyOperands() {
        try {
            calculator.evaluateRPN("3 4 5 +");
            Assert.fail("Une exception aurait dû être levée.");
        } catch (Exception e) {
            assertEquals("Expression RPN invalide", e.getMessage());
        }
    }

    @Test
    public void testInvalidOperand() {
        try {
            calculator.evaluateRPN("2 a +");
            Assert.fail("Une exception aurait dû être levée.");
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("Erreur: opérateur invalide."));
        }
    }
}
