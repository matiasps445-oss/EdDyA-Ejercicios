/*
Quiero implementar en Java una función recursiva que calcule una multiplicación sin usar el operador *.
Este problema puede resolverse recursivamente porque la multiplicación de dos números (a y b) se puede representar como sumas repetidas, sumando 'a' un total de 'b' veces.
El caso base es cuando uno de los factores (por ejemplo, el parámetro b) vale 0, porque cualquier número multiplicado por 0 es 0, y esto detiene la recursión.
El caso recursivo es a + multiplicación(a, b-1), porque en cada llamada el parámetro 'b' se reduce restándole 1 para acercarse al caso base de 0.
La función debe devolver un número entero con el resultado del producto.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos: caso 1: a = 5, b = 0; caso 2: a = 5, b = 1; caso 3: a = 4, b = 3.
*/

/**
 * Ejercicio 3: multiplicación mediante sumas usando recursividad.
 *
 * La función recursiva sigue la idea de que:
 * multiplicacion(a, b) = a + multiplicacion(a, b - 1)
 * con caso base en b == 0.
 */
public class MultiplicacionRecursiva {

    /**
     * Calcula el producto de dos enteros positivos usando solo sumas y recursividad.
     *
     * Caso base:
     * Si b == 0, la función devuelve 0 porque cualquier número multiplicado por 0 es 0.
     * Esto detiene la recursión y termina la cadena de llamadas.
     *
     * Caso recursivo:
     * Si b > 0, la función hace la llamada multiplicacion(a, b - 1) y luego suma a.
     * En cada llamada se reduce b en 1, acercándose al caso base.
     *
     * Pila de llamadas:
     * Cuando se invoca multiplicacion(4, 3), Java apila estas llamadas:
     * multiplicacion(4, 3) -> multiplicacion(4, 2) -> multiplicacion(4, 1) -> multiplicacion(4, 0)
     * Cada llamada espera a la siguiente para sumar a su valor actual.
     *
     * @param a primer factor
     * @param b segundo factor
     * @return producto de a y b sin usar el operador *
     */
    public static int multiplicacion(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("Los factores deben ser no negativos para esta versión recursiva.");
        }

        // Caso base: cualquier valor multiplicado por 0 es 0.
        if (b == 0) {
            return 0;
        }

        // Caso recursivo: a + multiplicacion(a, b - 1)
        // En cada llamada se reduce el segundo factor en 1.
        return a + multiplicacion(a, b - 1);
    }

    /**
     * Método principal con los casos de prueba indicados.
     */
    public static void main(String[] args) {
        int caso1A = 5;
        int caso1B = 0;

        int caso2A = 5;
        int caso2B = 1;

        int caso3A = 4;
        int caso3B = 3;

        System.out.println("multiplicacion(" + caso1A + ", " + caso1B + ") = " + multiplicacion(caso1A, caso1B));
        System.out.println("multiplicacion(" + caso2A + ", " + caso2B + ") = " + multiplicacion(caso2A, caso2B));
        System.out.println("multiplicacion(" + caso3A + ", " + caso3B + ") = " + multiplicacion(caso3A, caso3B));
    }
}
