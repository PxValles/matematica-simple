package pxvalles;

import java.util.Scanner;
import com.ezylang.evalex.*;
//import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;


public class Main {


    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        System.out.println("Metodo busca");
        System.out.println("Ingrese la función f(x)");

        String inpuString = escaner.nextLine();
        
        System.out.println("Ingrese un intervalo de analisis [a,b] ");
        System.out.println("Ingrese \'a\'");
        double a = escaner.nextDouble();
        System.out.println("Ingrese \'b\'");
        double b = escaner.nextDouble();

        Expression expr = new Expression(inpuString);

        if (a>=b) {
            System.out.println("Error. \'a\' debe ser menor que \'b\' ");
        } else {
            double fa=0,fb=0;
            try {
                fa = expr.with("x", a).evaluate().getNumberValue().doubleValue();
                fb = expr.with("x", b).evaluate().getNumberValue().doubleValue();
            }  catch (EvaluationException e) {
                // Maneja errores de evaluación (ej: división por cero)
                System.err.println("Error al evaluar: " + e.getMessage());
            } catch (ParseException e) {
                // Maneja errores de sintaxis en la expresión
                System.err.println("Error de sintaxis: " + e.getMessage());
            }
            if (fa*fb==0) {
                if (fa==0 && fb==0) {
                    System.out.println("La solución es:");
                    System.out.println(a);
                    System.out.println(b);
                } else {
                    if (fa==0) {
                        System.out.println("La solución es:");
                        System.out.println(a);
                    } else {
                       System.out.println("La solución es:");
                       System.out.println(b); 
                    }
                }
                
            } else {
                System.out.println("Ingrese un incremento H");
                double H= escaner.nextDouble();
                
                while (H<=0) {
                    System.out.println("El incremento H debe ser positivo");
                    H=escaner.nextDouble();
                }
    
                double M = a;
                double N = M+H;
                int iter = 1;
                int iterMax = 10000;
    
                try {
                    fa = expr.with("x", M).evaluate().getNumberValue().doubleValue();
                    fb = expr.with("x", N).evaluate().getNumberValue().doubleValue();
                    while (fa*fb>0 && N<b && iter<iterMax) {
                        M=N;
                        N=M+H;
                        fa = expr.with("x", M).evaluate().getNumberValue().doubleValue();
                        fb = expr.with("x", N).evaluate().getNumberValue().doubleValue();
                        iter++; 
                    }
                    if (iter>iterMax) {
                        System.out.println("Máximo de iteraciones alcanzado sin encontrar una raíz.");
                    } else {
                        if (fa*fb<0) {
                            System.out.println("Existe una solución en el rango: ");
                            System.out.printf("%.6f \n",M);
                            System.out.printf("%.6f",N);
                        } else {
                            System.out.println("No se encontro una solución en el intervalo:");
                            System.out.printf("["+a+","+b+"]");
                        }
                    }
    
                } catch (EvaluationException e) {
                    System.err.println("Error al evaluar: " + e.getMessage());
                } catch (ParseException e) {
                    System.err.println("Error de sintaxis: " + e.getMessage());
                }
                escaner.close();
            }
            
        }
    }
}