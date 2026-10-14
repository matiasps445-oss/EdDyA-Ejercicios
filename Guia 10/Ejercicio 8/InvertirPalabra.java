/*
Prompt inicial utilizado:
Quiero implementar en Java un programa que reciba una palabra (String) y devuelva la palabra invertida utilizando nuestra estructura de Pila enlazada.
El algoritmo debe consistir en dos fases. Primero, iterar sobre la palabra original carácter por carácter de izquierda a derecha y apilar (push) cada letra en la pila. Segundo, mientras la pila no esté vacía, desapilar (pop) las letras y concatenarlas para formar la nueva palabra.
La estructura adecuada para esto es una Pila debido a su política LIFO (Last In, First Out). Esta política permite invertir el orden de los caracteres de forma natural: la última letra de la palabra original que ingresa al tope de la pila será obligatoriamente la primera en salir cuando comencemos a desapilar, invirtiendo así la secuencia sin necesidad de usar índices ni cálculos matemáticos complejos.
Para probarlo, quiero enviar la palabra 'algoritmo' al método. El programa debe imprimir la palabra original y, a continuación, mostrar el resultado invertido, que debería ser 'omtirogla'.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó con claridad por qué la política LIFO es el mecanismo perfecto para invertir secuencias, indicando las dos fases (apilar todo y luego desapilar todo), lo que resultó en un algoritmo eficiente a la primera.
*/
public class InvertirPalabra {
    private static final class Nodo<T> {
        private final T dato;
        private final Nodo<T> siguiente;

        private Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private static final class PilaEnlazada<T> {
        private Nodo<T> head;

        private void apilar(T dato) {
            head = new Nodo<>(dato, head);
        }

        private T desapilar() {
            T dato = head.dato;
            head = head.siguiente;
            return dato;
        }

        private boolean estaVacia() {
            return head == null;
        }
    }

    public static String invertirPalabra(String palabra) {
        PilaEnlazada<Character> pila = new PilaEnlazada<>();

        for (int indice = 0; indice < palabra.length(); indice++) {
            pila.apilar(palabra.charAt(indice));
        }

        StringBuilder resultado = new StringBuilder(palabra.length());
        while (!pila.estaVacia()) {
            resultado.append(pila.desapilar());
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        String palabraOriginal = "algoritmo";
        String palabraInvertida = invertirPalabra(palabraOriginal);

        System.out.println("Palabra original: " + palabraOriginal);
        System.out.println("Palabra invertida: " + palabraInvertida);
    }
}