/*
Quiero implementar en Java un programa que reciba una expresión matemática en formato String y determine si los paréntesis están correctamente balanceados, devolviendo 'válido' o 'inválido'.
Este problema se resuelve de forma óptima utilizando una Pila. La estrategia consiste en recorrer la expresión carácter por carácter: cada vez que encontramos un paréntesis de apertura '(', lo guardamos haciendo un 'push' en la pila. Cuando encontramos un paréntesis de cierre ')', debemos realizar un 'pop' para emparejarlo con el último paréntesis abierto.
Los casos de invalidez (casos límite) ocurren si:
1. Encontramos un ')' y al intentar hacer 'pop' la pila está vacía (hay un cierre sin apertura).
2. Al terminar de leer toda la expresión, la pila no queda vacía (hay un '(' que nunca se cerró).
Para verificar el comportamiento, quiero ejecutar pruebas de ejecución con estos dos casos exactos: la expresión '(5+3)*(2+1)' que debe devolver 'válido', y la expresión '(5+3))*(2+1' que debe devolver 'inválido'.
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Valida si los paréntesis de una expresión están balanceados.
 */
public class ValidarParentesis {

    /**
     * Devuelve si la expresión tiene sus paréntesis balanceados.
     * Los caracteres distintos de '(' y ')' no afectan la validación.
     *
     * @param expresion expresión matemática que se analizará
     * @return "válido" si los paréntesis están balanceados; de lo contrario, "inválido"
     */
    public static String validarParentesis(String expresion) {
        Deque<Character> pila = new ArrayDeque<>();

        for (int indice = 0; indice < expresion.length(); indice++) {
            char caracter = expresion.charAt(indice);

            if (caracter == '(') {
                // Guarda cada apertura pendiente de encontrar su cierre.
                pila.push(caracter);
            } else if (caracter == ')') {
                // Un cierre sin apertura previa es inválido; se evita hacer pop en una pila vacía.
                if (pila.isEmpty()) {
                    return "inválido";
                }
                pila.pop();
            }
        }

        // Solo es válido si no quedó ninguna apertura sin su paréntesis de cierre.
        return pila.isEmpty() ? "válido" : "inválido";
    }

    /**
     * Ejecuta los dos casos de prueba requeridos.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        String[] expresiones = {"(5+3)*(2+1)", "(5+3))*(2+1"};

        for (String expresion : expresiones) {
            System.out.println(expresion + " -> " + validarParentesis(expresion));
        }
    }
}