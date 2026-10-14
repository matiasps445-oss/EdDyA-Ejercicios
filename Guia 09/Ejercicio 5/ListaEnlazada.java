/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'boolean eliminar(int dato)' para nuestra lista enlazada simple. Este método debe buscar y eliminar la primera aparición del dato especificado, devolviendo true si lo logró o false si no lo encontró.
Es fundamental entender que en Java no se borra ni se destruye manualmente el nodo de la memoria (como se haría en C o C++ con 'free'). En su lugar, simplemente 'desconectamos' el nodo actualizando las referencias para dejarlo inaccesible; luego, el recolector de basura de Java (Garbage Collector) se encargará automáticamente de liberar esa memoria.
El algoritmo debe contemplar obligatoriamente 5 casos:
1. Lista vacía: Si head es null, devolver false inmediatamente.
2. Eliminar el primer nodo: Si el dato está en el head, simplemente actualizamos head para que apunte a head.getSiguiente() y reducimos el size.
3 y 4. Eliminar un nodo del medio o el último: Requiere recorrer la lista llevando dos punteros ('actual' y 'anterior'). Cuando 'actual' tiene el dato, hacemos que 'anterior' apunte al 'siguiente' de 'actual', puenteando así al nodo a eliminar. Reducimos el size.
5. Dato inexistente: Si el recorrido termina y no se encontró el dato, devolver false.
Para verificar, quiero crear una lista con los valores 10, 20, 30 y 40, y probar eliminar el 10 (head), el 30 (medio), el 40 (último) y un 99 (inexistente), imprimiendo la lista tras cada paso.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue excelente al detallar explícitamente que en Java los nodos no se borran manualmente sino que se dejan inaccesibles para el Garbage Collector, y al listar los 5 casos de prueba necesarios, logrando un código robusto a la primera.
*/
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

public class ListaEnlazada {
    private Nodo head;
    private int size;

    public ListaEnlazada() {
        head = null;
        size = 0;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public boolean eliminar(int dato) {
        if (head == null) {
            return false;
        }

        if (head.getDato() == dato) {
            head = head.getSiguiente();
            size--;
            return true;
        }

        Nodo anterior = head;
        Nodo actual = head.getSiguiente();

        while (actual != null) {
            if (actual.getDato() == dato) {
                anterior.setSiguiente(actual.getSiguiente());
                size--;
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        if (head == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo actual = head;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? "" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        System.out.println("Eliminar en lista vacía (10): " + lista.eliminar(10));
        lista.imprimir();

        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        System.out.println("Lista inicial:");
        lista.imprimir();

        System.out.println("Eliminar 10 (head): " + lista.eliminar(10));
        lista.imprimir();

        System.out.println("Eliminar 30 (medio): " + lista.eliminar(30));
        lista.imprimir();

        System.out.println("Eliminar 40 (último): " + lista.eliminar(40));
        lista.imprimir();

        System.out.println("Eliminar 99 (inexistente): " + lista.eliminar(99));
        lista.imprimir();
        System.out.println("Tamaño final: " + lista.getSize());
    }
}