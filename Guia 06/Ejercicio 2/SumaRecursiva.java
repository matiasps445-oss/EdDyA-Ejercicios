/*
Quiero implementar en Java una función recursiva que sume los números desde n hasta 1.
Este problema puede resolverse recursivamente porque la suma de n números, es decir suma(n), puede pensarse como n + suma(n-1), por lo que cada llamada reduce el problema a una versión más pequeña hasta llegar a un caso trivial.
El caso base es cuando n es 0, porque la suma de 0 números es 0 y detiene la ejecución.
El caso recursivo es n + suma(n-1), porque en cada llamada el problema se reduce restando 1 a n, acercándose al caso base de 0.
La función debe devolver un número entero con el resultado de la suma.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos para validar que funciona correctamente: caso 1: n = 0; caso 2: n = 1; caso 3: n = 5.
*/

/**
 * Ejercicio 2: suma de los primeros n números mediante recursividad.
 *
 * La función recursiva sigue la definición matemática:
 * suma(n) = n + suma(n - 1)
 * con caso base en n == 0.
 */
public class SumaRecursiva {

    /**
     * Calcula la suma de los números desde n hasta 1 de forma recursiva.
     *
     * Caso base:
     * Si n == 0, la función devuelve 0 porque la suma de 0 números es 0.
     * Aquí la recursión termina y deja de hacer más llamadas.
     *
     * Caso recursivo:
     * Si n > 0, la función hace la llamada suma(n - 1) y luego agrega n.
     * Esto reduce el problema en 1 cada vez, acercándolo al caso base.
     *
     * Pila de llamadas:
     * Cuando se invoca suma(5), Java apila estas llamadas:
     * suma(5) -> suma(4) -> suma(3) -> suma(2) -> suma(1) -> suma(0)
     * Cada llamada espera a que la siguiente termine para sumar su valor.
     *
     * @param n número entero no negativo
     * @return suma de todos los valores desde n hasta 1
     * @throws IllegalArgumentException si n es negativo
     */
    public static int suma(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("La suma solo está definida para números no negativos.");
        }

        // Caso base: cuando n vale 0, la suma es 0.
        if (n == 0) {
            return 0;
        }

        // Caso recursivo: n + suma(n - 1)
        // En cada llamada se reduce el problema restando 1 a n.
        return n + suma(n - 1);
    }

    /**
     * Método principal con los casos de prueba requeridos.
     */
    public static void main(String[] args) {
        int caso1 = 0;
        int caso2 = 1;
        int caso3 = 5;

        System.out.println("suma(" + caso1 + ") = " + suma(caso1));
        System.out.println("suma(" + caso2 + ") = " + suma(caso2));
        System.out.println("suma(" + caso3 + ") = " + suma(caso3));
    }
}
