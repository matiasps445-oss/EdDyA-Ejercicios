/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'void modificar(int posicion, int nuevoDato)' para nuestra lista enlazada simple.
La función de este método es localizar un nodo existente en una posición específica y actualizar únicamente el valor que almacena.
Primero, es estrictamente necesario validar la posición antes de recorrer (posicion < 0 o posicion >= size) arrojando una excepción si es inválida, para evitar errores de ejecución.
Si la posición es válida, el algoritmo debe recorrer la lista con un puntero 'actual' desde el 'head' hasta llegar al índice indicado. Una vez allí, se debe sobreescribir el valor usando 'actual.setDato(nuevoDato)'.
Es vital entender la diferencia arquitectónica de esta operación: modificar el dato de un nodo cambia solamente la "carga" útil (el valor) que transporta el eslabón, manteniendo intacta la estructura de la lista. En cambio, modificar la referencia al siguiente nodo (con setSiguiente) altera la topología de la lista, conectando o desconectando eslabones. Aquí solo queremos cambiar el dato, por lo que las referencias no se tocan.
Para probarlo, quiero crear una lista con los valores 10, 20 y 30. Luego modificar el elemento en la posición 1 (el 20) por un 99, e imprimir la lista para comprobar que ahora es 10, 99, 30.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt diferenció de manera excelente entre modificar la carga de un nodo (el dato) y modificar los enlaces de la estructura (las referencias), por lo que el algoritmo generado cumplió su propósito de forma segura y sin alterar el tamaño de la lista.
*/
class Nodo {
    private int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int nuevoDato) {
        this.dato = nuevoDato;
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

    public void modificar(int posicion, int nuevoDato) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición " + posicion + " inválida para una lista de tamaño " + size + ".");
        }

        Nodo actual = head;
        int contador = 0;
        while (contador < posicion) {
            actual = actual.getSiguiente();
            contador++;
        }

        actual.setDato(nuevoDato);
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

        System.out.println("Lista antes de modificar:");
        lista.imprimir();
        System.out.println("Tamaño antes de modificar: " + lista.getSize());

        lista.modificar(1, 99);

        System.out.println("Lista después de modificar la posición 1:");
        lista.imprimir();
        System.out.println("Tamaño después de modificar: " + lista.getSize());
    }
}