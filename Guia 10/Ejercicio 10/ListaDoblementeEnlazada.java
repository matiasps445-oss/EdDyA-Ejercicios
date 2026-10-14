/*
Prompt inicial utilizado:
Quiero implementar en Java una Lista Doblemente Enlazada genérica (<T>).
Para esta estructura, cada nodo (NodoDoble) no solo debe tener una referencia al 'siguiente' nodo, sino también una referencia al nodo 'anterior'. Se necesitan ambas referencias porque esto permite recorrer la estructura bidireccionalmente, abriendo la posibilidad de realizar operaciones de forma más eficiente y flexible, como navegar hacia atrás.
La lista debe administrar dos punteros principales: 'head' (inicio) y 'tail' (final).
Las operaciones que debe permitir son:
1. Insertar al inicio: El nuevo nodo apunta al head actual, y si la lista no estaba vacía, el head actual debe apuntar hacia atrás al nuevo nodo.
2. Insertar al final: Usando 'tail', se enlaza el nuevo nodo al final en O(1).
3. Imprimir hacia adelante: Recorriendo desde 'head' usando la referencia 'siguiente'.
4. Imprimir hacia atrás: Recorriendo desde 'tail' usando la referencia 'anterior'.
5. Eliminar un elemento: Esta operación tiene casos especiales críticos que deben contemplarse al reconectar los enlaces:
   - Si la lista está vacía, no se hace nada.
   - Si el elemento a eliminar es el único en la lista (head == tail), ambos punteros quedan en null.
   - Si es el primer elemento (head), el nuevo head será el 'siguiente', y su 'anterior' debe quedar en null.
   - Si es el último elemento (tail), el nuevo tail será el 'anterior', y su 'siguiente' debe quedar en null.
   - Si está en el medio, se deben puentear ambos nodos vecinos: actual.anterior.siguiente = actual.siguiente y actual.siguiente.anterior = actual.anterior.
Para la prueba, quiero insertar 3 elementos al inicio y 2 al final, imprimir hacia adelante, imprimir hacia atrás, eliminar el primer elemento, eliminar el último, eliminar uno del medio, y volver a imprimir para verificar.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt definió detalladamente la necesidad de las referencias 'anterior' y 'siguiente', y desglosó a la perfección los 5 casos especiales al eliminar nodos (vacía, único nodo, head, tail, medio), logrando que los enlaces dobles se mantuvieran consistentes.
*/
import java.util.Objects;

class NodoDoble<T> {
    private final T dato;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    NodoDoble(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}

public class ListaDoblementeEnlazada<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int size;

    public void insertarAlInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        nuevo.setSiguiente(head);

        if (head == null) {
            tail = nuevo;
        } else {
            head.setAnterior(nuevo);
        }

        head = nuevo;
        size++;
    }

    public void insertarAlFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        nuevo.setAnterior(tail);

        if (tail == null) {
            head = nuevo;
        } else {
            tail.setSiguiente(nuevo);
        }

        tail = nuevo;
        size++;
    }

    public boolean eliminar(T dato) {
        NodoDoble<T> actual = head;
        while (actual != null && !Objects.equals(actual.getDato(), dato)) {
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            return false;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else if (actual == head) {
            head = actual.getSiguiente();
            head.setAnterior(null);
        } else if (actual == tail) {
            tail = actual.getAnterior();
            tail.setSiguiente(null);
        } else {
            actual.getAnterior().setSiguiente(actual.getSiguiente());
            actual.getSiguiente().setAnterior(actual.getAnterior());
        }

        size--;
        return true;
    }

    public void imprimirAdelante() {
        if (head == null) {
            System.out.println("Lista vacía.");
            return;
        }

        NodoDoble<T> actual = head;
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? "" : " <-> ");
        }
        System.out.println();
    }

    public void imprimirAtras() {
        if (tail == null) {
            System.out.println("Lista vacía.");
            return;
        }

        NodoDoble<T> actual = tail;
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getAnterior();
            System.out.print(actual == null ? "" : " <-> ");
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        lista.insertarAlInicio(10);
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        System.out.println("Lista inicial hacia adelante:");
        lista.imprimirAdelante();
        System.out.println("Lista inicial hacia atrás:");
        lista.imprimirAtras();

        System.out.println("\nEliminar primero (30): " + lista.eliminar(30));
        lista.imprimirAdelante();
        lista.imprimirAtras();

        System.out.println("\nEliminar último (50): " + lista.eliminar(50));
        lista.imprimirAdelante();
        lista.imprimirAtras();

        System.out.println("\nEliminar del medio (10): " + lista.eliminar(10));
        System.out.println("Resultado final hacia adelante:");
        lista.imprimirAdelante();
        System.out.println("Resultado final hacia atrás:");
        lista.imprimirAtras();
        System.out.println("Tamaño final: " + lista.getSize());
    }
}