/*
Quiero implementar en Java una función recursiva que calcule el factorial de un número.
Este problema puede resolverse recursivamente porque el factorial de un número n es n multiplicado por el factorial de n-1, por lo que cada llamada reduce el tamaño del problema hasta llegar a un caso trivial.
El caso base es cuando n vale 0 o 1, porque por definición matemática, el factorial de 0 y 1 es 1, y en ese punto la recursión debe detenerse.
El caso recursivo es n * factorial(n-1), porque en cada llamada el problema se reduce restando 1 a n y acercándose al caso base.
La función debe devolver un número entero con el resultado del factorial calculado.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos: caso 1: n = 0; caso 2: n = 1; caso 3: n = 5.
*/

/**
 * Ejercicio 1: cálculo del factorial mediante recursividad.
 *
 * La función recursiva sigue la definición matemática del factorial:
 * factorial(n) = n * factorial(n - 1)
 * con caso base en n == 0 o n == 1.
 */
public class FactorialRecursivo {

    /**
     * Calcula el factorial de un número entero no negativo de forma recursiva.
     *
     * Caso base:
     * Si n == 0 o n == 1, la función devuelve 1 porque por definición
     * 0! = 1 y 1! = 1. Aquí la recursión termina y no se siguen haciendo llamadas.
     *
     * Caso recursivo:
     * Si n > 1, se ejecuta la llamada factorial(n - 1) y luego se multiplica por n.
     * Esto reduce el problema cada vez en 1, acercándose al caso base.
     *
     * Pila de llamadas:
     * Cuando se invoca factorial(5), Java apila las llamadas:
     * factorial(5) -> factorial(4) -> factorial(3) -> factorial(2) -> factorial(1)
     * Cada llamada espera a que la llamada más pequeña termine para multiplicar el
     * resultado por el valor actual de n.
     *
     * @param n número entero no negativo
     * @return factorial de n
     * @throws IllegalArgumentException si n es negativo
     */
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial solo está definido para números no negativos.");
        }

        // Caso base: cuando n vale 0 o 1, el factorial es 1.
        if (n == 0 || n == 1) {
            return 1;
        }

        // Caso recursivo: n * factorial(n - 1)
        // En cada llamada se reduce el problema restando 1 a n.
        return n * factorial(n - 1);
    }

    /**
     * Método principal con los casos de prueba solicitados.
     */
    public static void main(String[] args) {
        int caso1 = 0;
        int caso2 = 1;
        int caso3 = 5;

        System.out.println("factorial(" + caso1 + ") = " + factorial(caso1));
        System.out.println("factorial(" + caso2 + ") = " + factorial(caso2));
        System.out.println("factorial(" + caso3 + ") = " + factorial(caso3));
    }
}
