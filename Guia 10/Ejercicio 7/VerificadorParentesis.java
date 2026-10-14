/*
Prompt inicial utilizado:
Quiero implementar en Java un programa que reciba una expresión matemática en formato String y determine si sus paréntesis están balanceados, utilizando internamente nuestra Pila enlazada.
La estructura ideal para resolver este problema es una Pila debido a su comportamiento LIFO (Last In, First Out). El algoritmo debe procesar la cadena de texto carácter por carácter. La lógica funciona así: cada vez que encontramos un paréntesis de apertura '(', lo guardamos en la pila (apilar/push) para registrar que se abrió un bloque. Cada vez que encontramos un paréntesis de cierre ')', debemos retirar un elemento de la pila (desapilar/pop) para emparejarlo.
Los casos especiales que invalidan la expresión son dos:
1. Si encontramos un cierre ')' pero la pila está vacía, significa que hay un cierre sin su correspondiente apertura (ejemplo: '())(').
2. Si al terminar de leer toda la expresión la pila NO está vacía, significa que quedaron aperturas sin cerrar (ejemplo: '(2+3').
La expresión solo es válida si, al finalizar el recorrido, la pila queda perfectamente vacía.
Para las pruebas de ejecución, quiero evaluar los ejemplos válidos: '(2+3)*(5-1)' y '((a+b)*c)', y los ejemplos inválidos: '(2+3' y '())(', imprimiendo true o false en cada caso.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt detalló perfectamente la lógica de apilar aperturas y desapilar con cierres, así como las dos condiciones de falla (pila vacía al cerrar y pila con sobrantes al final), logrando un algoritmo de verificación exacto.
*/
public class VerificadorParentesis {
    private static final class NodoCaracter {
        private final char dato;
        private final NodoCaracter siguiente;

        private NodoCaracter(char dato, NodoCaracter siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private static final class PilaCaracteres {
        private NodoCaracter head;

        private void apilar(char caracter) {
            head = new NodoCaracter(caracter, head);
        }

        private char desapilar() {
            char caracter = head.dato;
            head = head.siguiente;
            return caracter;
        }

        private boolean estaVacia() {
            return head == null;
        }
    }

    public static boolean estaBalanceada(String expresion) {
        PilaCaracteres aperturas = new PilaCaracteres();

        for (int indice = 0; indice < expresion.length(); indice++) {
            char caracter = expresion.charAt(indice);

            if (caracter == '(') {
                aperturas.apilar(caracter);
            } else if (caracter == ')') {
                if (aperturas.estaVacia()) {
                    return false;
                }
                aperturas.desapilar();
            }
        }

        return aperturas.estaVacia();
    }

    public static void main(String[] args) {
        String[] expresiones = {
            "(2+3)*(5-1)",
            "((a+b)*c)",
            "(2+3",
            "())("
        };

        for (String expresion : expresiones) {
            System.out.println(expresion + " -> " + estaBalanceada(expresion));
        }
    }
}