package pxvalles;

//import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import java.util.Scanner;

public class DerivadaSymja {
    public static void main(String[] args) {
        
        ExprEvaluator evaluator = new ExprEvaluator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calcular Derivadas");
        System.out.print("Ingresa una función (por ejemplo, x^2 + 3*x + 1): ");
        String funcion = scanner.nextLine();

        try {
            // Calcular la derivada simbólica
            IExpr derivada = evaluator.eval("D(" + funcion + ", x)");
            // Mostrar el resultado
            System.out.println("La derivada de '" + funcion + "' es: " + derivada);
        } catch (Exception e) {
            System.out.println("Error al calcular la derivada. Asegúrate de ingresar una función válida.");
        } finally {
            scanner.close();
        }
    }
}

