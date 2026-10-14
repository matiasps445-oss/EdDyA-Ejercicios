/*
Quiero implementar en Java una función recursiva que determine si una palabra es palíndromo.
Este problema puede resolverse recursivamente porque para saber si una palabra se lee igual de izquierda a derecha que de derecha a izquierda, hay que comparar el primer y último carácter. 
Si son distintos, la función debe devolver false inmediatamente porque ya no es un palíndromo, rompiendo la recursión.
Si son iguales, el problema se reduce eliminando esos extremos (el primer y último carácter) y volviendo a llamar a la función con la palabra resultante (la subcadena del medio), acercándose al caso base.
El caso base es cuando la palabra se reduce a tener 0 o 1 carácter, porque una cadena vacía o de una sola letra es palíndromo por definición, devolviendo true y deteniendo la recursión.
La función debe devolver un valor boolean (true o false).
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos:
- caso 1: palabra = "neuquen"
- caso 2: palabra = "a"
- caso 3: palabra = "hola"
*/

/**
 * Ejercicio 9: comprobación de palíndromos mediante recursividad.
 */
public class Palindromo {

    /**
     * Comprueba recursivamente si una palabra es un palíndromo.
     *
     * Caso base:
     * Si la palabra tiene cero o un carácter, devuelve true porque se lee
     * igual en ambos sentidos y no necesita más comparaciones.
     *
     * Caso recursivo:
     * Si los extremos son distintos, devuelve false sin continuar. Si coinciden,
     * comprueba la subcadena interior, quitando el primer y el último carácter.
     *
     * Pila de llamadas:
     * Para "neuquen", las llamadas pendientes son esPalindromo("neuquen"),
     * esPalindromo("euque") y esPalindromo("uq"). La última llama a
     * esPalindromo("") y recibe true del caso base. Al retornar, cada llamada
     * propaga ese true porque sus extremos coincidían.
     *
     * @param palabra cadena que se comprobará
     * @return true si palabra es un palíndromo; de lo contrario, false
     */
    public static boolean esPalindromo(String palabra) {
        // Caso base: una cadena vacía o de un carácter es palíndromo.
        if (palabra.length() <= 1) {
            return true;
        }

        // Si los extremos difieren, no es un palíndromo y no se recurre más.
        if (palabra.charAt(0) != palabra.charAt(palabra.length() - 1)) {
            return false;
        }

        // Caso recursivo: comprueba la subcadena sin sus extremos coincidentes.
        return esPalindromo(palabra.substring(1, palabra.length() - 1));
    }

    /**
     * Ejecuta los casos de prueba solicitados.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        String primeraPalabra = "neuquen";
        String segundaPalabra = "a";
        String terceraPalabra = "hola";

        System.out.println("esPalindromo(\"" + primeraPalabra + "\") = "
                + esPalindromo(primeraPalabra));
        System.out.println("esPalindromo(\"" + segundaPalabra + "\") = "
                + esPalindromo(segundaPalabra));
        System.out.println("esPalindromo(\"" + terceraPalabra + "\") = "
                + esPalindromo(terceraPalabra));
    }
}