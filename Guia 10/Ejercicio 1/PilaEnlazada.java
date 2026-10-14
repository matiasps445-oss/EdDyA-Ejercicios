/*
Prompt inicial utilizado:
Quiero implementar en Java una estructura de Pila (Stack) de números enteros utilizando internamente una lista enlazada simple.
Corresponde usar una pila porque necesito un comportamiento estricto LIFO (Last In, First Out). Internamente, la estructura funcionará utilizando una clase Nodo y una referencia 'head' (cabeza).
Es fundamental comprender que 'head' representará siempre el tope (top) de la pila. El motivo estructural de esta decisión es la eficiencia algorítmica: insertar un nodo al inicio de una lista enlazada simple (apilar/push) y eliminar el nodo del inicio (desapilar/pop) son operaciones directas que no requieren recorrer la estructura. Por lo tanto, esto garantiza que push y pop se resuelvan con una complejidad de O(1) (tiempo constante).
Las operaciones que debe implementar son: apilar, desapilar, consultar el tope, verificar si está vacía (head == null), buscar si contiene un valor (lo cual requerirá un recorrido secuencial de O(n)) e imprimir su contenido.
Como casos especiales a contemplar, si la pila está vacía y se intenta desapilar o consultar el tope, el sistema debe arrojar una excepción (EmptyStackException) para proteger la ejecución.
Quiero que la prueba ejecute un apilamiento de 3 números, muestre el tope, busque un valor existente y desapile un elemento.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt justificó con total claridad por qué 'head' debe ser el tope para lograr una complejidad de O(1) en las operaciones principales, lo que permitió que el código generado estuviera perfectamente optimizado desde el principio.
*/
import java.util.EmptyStackException;

class Nodo {
    private final int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

public class PilaEnlazada {
    private Nodo head;
    private int size;

    public void apilar(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public int desapilar() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }

        int datoRetirado = head.getDato();
        head = head.getSiguiente();
        size--;
        return datoRetirado;
    }

    public int consultarTope() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }

        return head.getDato();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(int dato) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.getDato() == dato) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Pila vacía.");
            return;
        }

        Nodo actual = head;
        System.out.print("Tope -> ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? " -> Fondo" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PilaEnlazada pila = new PilaEnlazada();
        System.out.println("¿Está vacía al comenzar? " + pila.estaVacia());

        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);
        System.out.println("Pila después de apilar 10, 20 y 30:");
        pila.imprimir();

        System.out.println("Tope: " + pila.consultarTope());
        System.out.println("¿Contiene 20? " + pila.contiene(20));

        System.out.println("Elemento desapilado: " + pila.desapilar());
        System.out.println("Pila después de desapilar:");
        pila.imprimir();
        System.out.println("Nuevo tope: " + pila.consultarTope());
        System.out.println("Tamaño: " + pila.getSize());

        PilaEnlazada pilaVacia = new PilaEnlazada();
        try {
            pilaVacia.desapilar();
        } catch (EmptyStackException excepcion) {
            System.out.println("Desapilar en vacío: EmptyStackException controlada.");
        }

        try {
            pilaVacia.consultarTope();
        } catch (EmptyStackException excepcion) {
            System.out.println("Consultar el tope en vacío: EmptyStackException controlada.");
        }
    }
}
