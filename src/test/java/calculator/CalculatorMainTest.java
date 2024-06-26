package calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class CalculatorMainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream originalIn = new ByteArrayInputStream(new byte[0]);
    private final CalculatorRPN calculator = new CalculatorRPN();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
    }

    @Test
    public void testMainAddition() {
        provideInput("2 3 +\n");
        calculator.main(new String[]{});
        assertEquals("Entrez une expression RPN:\nRésultat: 5.0\n", outContent.toString());
    }

    @Test
    public void testMainSubtraction() {
        provideInput("5 3 -\n");
        calculator.main(new String[]{});
        assertEquals("Entrez une expression RPN:\nRésultat: 2.0\n", outContent.toString());
    }

    @Test
    public void testMainMultiplication() {
        provideInput("4 2 *\n");
        calculator.main(new String[]{});
        assertEquals("Entrez une expression RPN:\nRésultat: 8.0\n", outContent.toString());
    }

    @Test
    public void testMainInvalidExpression() {
        provideInput("4 +\n");
        calculator.main(new String[]{});
        assertEquals("Entrez une expression RPN:\nErreur dans l'expression RPN.\n", outContent.toString());
    }

    @Test
    public void testMainNoOperands() {
        provideInput("4 3");
        calculator.main(new String[]{});
        assertEquals("Entrez une expression RPN:\nErreur dans l'expression RPN.\n", outContent.toString());
    }
}
