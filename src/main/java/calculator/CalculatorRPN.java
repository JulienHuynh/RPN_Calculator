package calculator;

import java.util.Scanner;
import java.util.Stack;

public class CalculatorRPN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une expression RPN:");
        String input = scanner.nextLine();
        scanner.close();

        try {
            double result = evaluateRPN(input);
            System.out.println("Résultat: " + result);
        } catch (Exception e) {
            System.out.println("Erreur dans l'expression RPN.");
        }
    }

    public static double evaluateRPN(String expression) throws Exception {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            switch (token) {
                case "+":
                    if (stack.size() < 2) throw new Exception("Erreur: pas assez d'opérandes.");
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    if (stack.size() < 2) throw new Exception("Erreur: pas assez d'opérandes.");
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    if (stack.size() < 2) throw new Exception("Erreur: pas assez d'opérandes.");
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    try {
                        stack.push(Double.parseDouble(token));
                    } catch (NumberFormatException e) {
                        throw new Exception("Erreur: opérateur invalide.");
                    }
                    break;
            }
        }

        if (stack.size() != 1) throw new Exception("Expression RPN invalide");
        return stack.pop();
    }
}
