/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'void eliminarEnPosicion(int posicion)' para nuestra lista enlazada simple.
Este método debe eliminar el nodo que se encuentra en un índice específico, reduciendo el atributo 'size' al finalizar.
Primero, es obligatorio validar que la posición no sea inválida (posicion < 0 o posicion >= size). Si es inválida, debe arrojar una excepción.
Al igual que en la inserción, eliminar el primer elemento (posición 0) es un caso especial: simplemente se debe actualizar 'head' para que apunte a 'head.getSiguiente()'.
Para eliminar cualquier otra posición, el algoritmo debe recorrer la lista hasta ubicarse exactamente en el nodo 'anterior' al que se quiere eliminar (es decir, iterar hasta 'posicion - 1'). Una vez posicionado en el nodo anterior, la lógica para eliminar es 'puentear' el nodo objetivo. Esto se logra haciendo que el nodo anterior apunte directamente al nodo que le sigue al eliminado: anterior.setSiguiente(anterior.getSiguiente().getSiguiente()).
Para verificar el funcionamiento, se debe crear una lista con 4 elementos e intentar eliminar la posición 0, luego la última posición actual, y finalmente una posición en el medio, mostrando la lista en cada paso.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó con total claridad la fórmula matemática para puentear las referencias (anterior.getSiguiente().getSiguiente()) y la necesidad de detenerse en el nodo previo, permitiendo que el método funcione correctamente sin ajustes.
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

    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición " + posicion + " inválida para una lista de tamaño " + size + ".");
        }

        if (posicion == 0) {
            head = head.getSiguiente();
        } else {
            Nodo anterior = head;
            for (int indice = 0; indice < posicion - 1; indice++) {
                anterior = anterior.getSiguiente();
            }

            anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        }

        size--;
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
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.println("Lista inicial:");
        lista.imprimir();

        lista.eliminarEnPosicion(0);
        System.out.println("Después de eliminar la cabeza (posición 0):");
        lista.imprimir();

        lista.eliminarEnPosicion(1);
        System.out.println("Después de eliminar el medio (posición 1):");
        lista.imprimir();

        int ultimaPosicion = lista.getSize() - 1;
        lista.eliminarEnPosicion(ultimaPosicion);
        System.out.println("Después de eliminar la última posición actual (" + ultimaPosicion + "):");
        lista.imprimir();
        System.out.println("Tamaño final: " + lista.getSize());
    }
}