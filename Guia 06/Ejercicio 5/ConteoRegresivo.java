/*
Quiero implementar en Java una función recursiva que imprima un conteo regresivo desde n hasta 0.
Este problema puede resolverse recursivamente porque hacer un conteo desde n implica imprimir el valor actual de n y luego delegar el resto del conteo al mismo proceso pero con n-1.
El caso base es cuando n es menor a 0, porque el conteo termina en 0, deteniendo la recursión en ese punto.
El caso recursivo es imprimir el número actual y hacer la llamada recursiva con n-1, porque así el problema se reduce y se acerca al caso base. Si en lugar de n-1 se usara n+1, la función se alejaría del caso base generando un bucle infinito que terminaría en un error de desbordamiento de memoria.
La función no necesita devolver un valor (su tipo de retorno será void), lo cual significa que su único propósito es ejecutar una acción (imprimir por consola) y no calcular ni retornar un resultado al método que la llamó.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probar la ejecución con estos casos:
- caso 1: n = 5
- caso 2: n = 1
- caso 3: n = 0
*/

/**
 * Ejercicio 5: conteo regresivo mediante recursividad.
 */
public class ConteoRegresivo {

    /**
     * Imprime los enteros desde n hasta 0, inclusive.
     *
     * Caso base:
     * Si n es menor que 0, la función termina sin imprimir. La llamada con -1
     * se alcanza después de imprimir 0 y detiene la cadena de llamadas.
     *
     * Caso recursivo:
     * Se imprime el valor actual y se llama de nuevo con n - 1, acercándose
     * al caso base en cada paso.
     *
     * Pila de llamadas:
     * Para n = 5, las llamadas avanzan desde conteoRegresivo(5) hasta
     * conteoRegresivo(-1). Al retornar la llamada del caso base, las llamadas
     * pendientes terminan una tras otra. Como la impresión ocurre antes de
     * cada llamada recursiva, los valores aparecen en orden descendente.
     *
     * @param n valor inicial del conteo
     */
    public static void conteoRegresivo(int n) {
        if (n < 0) {
            return;
        }

        System.out.println(n);
        conteoRegresivo(n - 1);
    }

    /**
     * Ejecuta los casos de prueba solicitados.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("Caso 1: n = 5");
        conteoRegresivo(5);

        System.out.println("Caso 2: n = 1");
        conteoRegresivo(1);

        System.out.println("Caso 3: n = 0");
        conteoRegresivo(0);
    }
}