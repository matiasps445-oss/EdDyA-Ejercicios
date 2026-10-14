/*
Quiero implementar en Java una función recursiva que cuente los dígitos de un número entero positivo.
Este problema puede resolverse recursivamente porque contar los dígitos de un número se puede pensar como contar 1 (por el último dígito) más la cantidad de dígitos del resto del número.
El caso base es cuando el número es menor a 10, porque cualquier número entero positivo menor que 10 está compuesto por un solo dígito, lo que detiene la recursión devolviendo 1.
El caso recursivo es 1 + contarDigitos(numero / 10), porque en cada llamada el problema se reduce quitando el último dígito usando división entera por 10, acercándose al caso base.
La función debe devolver en cada llamada un número entero sumando 1 al resultado de la llamada recursiva.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos:
- caso 1: numero = 7
- caso 2: numero = 42
- caso 3: numero = 1048
*/

/**
 * Ejercicio 6: conteo de dígitos mediante recursividad.
 */
public class ContarDigitos {

    /**
     * Cuenta los dígitos de un número entero positivo.
     *
     * Caso base:
     * Si numero es menor que 10, devuelve 1 porque queda un único dígito.
     *
     * Caso recursivo:
     * Divide numero entre 10 para quitar su último dígito y suma 1 al conteo
     * que devuelve la llamada para el resto del número.
     *
     * Pila de llamadas:
     * Para 1048, las llamadas son contarDigitos(1048), contarDigitos(104),
     * contarDigitos(10) y contarDigitos(1). La última devuelve 1; al retornar,
     * cada llamada suma 1, produciendo 2, 3 y finalmente 4.
     *
     * @param numero número entero positivo cuyo total de dígitos se cuenta
     * @return cantidad de dígitos de numero
     */
    public static int contarDigitos(int numero) {
        if (numero < 10) {
            return 1;
        }

        return 1 + contarDigitos(numero / 10);
    }

    /**
     * Ejecuta los casos de prueba indicados en el análisis.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int primerNumero = 7;
        int segundoNumero = 42;
        int tercerNumero = 1048;

        System.out.println("contarDigitos(" + primerNumero + ") = " + contarDigitos(primerNumero));
        System.out.println("contarDigitos(" + segundoNumero + ") = " + contarDigitos(segundoNumero));
        System.out.println("contarDigitos(" + tercerNumero + ") = " + contarDigitos(tercerNumero));
    }
}