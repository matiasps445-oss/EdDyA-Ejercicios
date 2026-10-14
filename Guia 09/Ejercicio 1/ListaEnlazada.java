/*
Prompt inicial utilizado:
Quiero implementar en Java una lista enlazada simple de números enteros desde cero, sin utilizar estructuras de la API de Java como ArrayList o LinkedList.
Para la estructura de clases, considero necesario crear una clase 'Nodo' que represente cada eslabón, conteniendo un entero 'dato' y una referencia 'siguiente' que apunte al próximo nodo. Luego, una clase 'ListaEnlazada' que administre la estructura.
La función principal de la lista recae sobre el atributo 'head' (cabeza), que sirve como punto de entrada único apuntando al primer nodo; si la lista está vacía, 'head' es null. También usaré un atributo 'size' para mantener el tamaño actualizado.
Debo implementar las siguientes operaciones:
1. insertarAlInicio(int dato): crea un nodo, hace que su 'siguiente' apunte al 'head' actual, y luego actualiza 'head' para que sea este nuevo nodo. Aumenta 'size'.
2. insertarAlFinal(int dato): crea un nodo. Si la lista está vacía, va directo al 'head'. Si no, requiere un recorrido secuencial desde 'head' saltando de nodo en nodo hasta encontrar aquel cuyo 'siguiente' sea null, y lo enlaza allí. Aumenta 'size'.
3. imprimir(): recorre la lista secuencialmente mostrando los datos.
4. estaVacia(): retorna true si 'head' es null.
5. getSize(): devuelve el valor de 'size'.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue lo suficientemente claro y detalló correctamente el uso de la clase Nodo, la referencia head y la actualización del tamaño, por lo que el código generado funcionó a la primera.
*/
class Nodo {
    int dato;
    Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

public class ListaEnlazada {
    private Nodo head;
    private int size;

    public ListaEnlazada() {
        head = null;
        size = 0;
    }

    public void insertarAlInicio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = head;
        head = nuevoNodo;
        size++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }

        size++;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo actual = head;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato);
            actual = actual.siguiente;
            System.out.print(actual == null ? "" : " -> ");
        }
        System.out.println();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        System.out.println("¿Está vacía al comenzar? " + lista.estaVacia());
        System.out.println("Tamaño inicial: " + lista.getSize());

        lista.insertarAlFinal(10);
        System.out.println("Tamaño después de insertar 10 al final: " + lista.getSize());

        lista.insertarAlInicio(5);
        System.out.println("Tamaño después de insertar 5 al inicio: " + lista.getSize());

        lista.insertarAlFinal(20);
        System.out.println("Tamaño después de insertar 20 al final: " + lista.getSize());

        System.out.println("Orden esperado: 5 -> 10 -> 20");
        lista.imprimir();
        System.out.println("¿Está vacía al terminar? " + lista.estaVacia());
    }
}