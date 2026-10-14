/*
Prompt inicial utilizado:
Quiero implementar en Java un sistema para manejar la fila de atención de clientes en un banco utilizando una cola enlazada simple.
Primero, necesito crear una clase Cliente que contenga tres atributos: nombre, número de turno y motivo de consulta.
El sistema debe permitir las siguientes operaciones: agregar un cliente a la fila (encolar/enqueue), atender al próximo cliente (desencolar/dequeue), consultar quién sigue (frente/front) e imprimir la fila actual.
La estructura adecuada para este problema es, indiscutiblemente, una Cola. Esto se debe a que una fila de banco funciona bajo el principio FIFO (First In, First Out). El orden de llegada de los clientes dicta estrictamente el orden de atención para mantener la justicia: el primer cliente que se suma a la fila (apuntado por 'head') debe ser el primero en ser atendido y retirado, mientras que los clientes nuevos que van llegando se colocan siempre al final de la fila (apuntados por 'tail').
Como casos especiales, el sistema debe contemplar y manejar adecuadamente el intento de 'atender al próximo cliente' o 'consultar quién sigue' cuando la fila está completamente vacía.
Para probarlo, quiero agregar a la fila a 3 clientes distintos, consultar quién sigue (el primero), atender a ese cliente y volver a mostrar la fila para ver cómo avanzó el orden.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó con total claridad por qué la estructura de Cola (FIFO) es la única lógica válida para representar el orden de llegada en un banco, logrando que el sistema de turnos funcione de manera justa desde el primer intento.
*/
import java.util.NoSuchElementException;

class Nodo {
    private final ColaAtencion.Cliente dato;
    private Nodo siguiente;

    Nodo(ColaAtencion.Cliente dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public ColaAtencion.Cliente getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

public class ColaAtencion {
    public static class Cliente {
        private final String nombre;
        private final int numeroTurno;
        private final String motivoConsulta;

        public Cliente(String nombre, int numeroTurno, String motivoConsulta) {
            this.nombre = nombre;
            this.numeroTurno = numeroTurno;
            this.motivoConsulta = motivoConsulta;
        }

        public String getNombre() {
            return nombre;
        }

        public int getNumeroTurno() {
            return numeroTurno;
        }

        public String getMotivoConsulta() {
            return motivoConsulta;
        }

        @Override
        public String toString() {
            return "Turno " + numeroTurno + ": " + nombre + " - " + motivoConsulta;
        }
    }

    private Nodo head;
    private Nodo tail;
    private int size;

    public void encolar(Cliente cliente) {
        Nodo nuevo = new Nodo(cliente);

        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public Cliente desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay clientes para atender.");
        }

        Cliente clienteAtendido = head.getDato();
        head = head.getSiguiente();
        size--;

        if (head == null) {
            tail = null;
        }

        return clienteAtendido;
    }

    public Cliente consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay clientes en espera.");
        }

        return head.getDato();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void imprimirFila() {
        if (estaVacia()) {
            System.out.println("La fila está vacía.");
            return;
        }

        Nodo actual = head;
        System.out.println("Fila de atención:");
        while (actual != null) {
            System.out.println("- " + actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public static void main(String[] args) {
        ColaAtencion fila = new ColaAtencion();
        fila.encolar(new Cliente("Ana García", 1, "Apertura de cuenta"));
        fila.encolar(new Cliente("Luis Pérez", 2, "Depósito"));
        fila.encolar(new Cliente("Marta López", 3, "Consulta de saldo"));

        fila.imprimirFila();
        System.out.println("Siguiente cliente: " + fila.consultarFrente());

        Cliente atendido = fila.desencolar();
        System.out.println("Cliente atendido: " + atendido);
        fila.imprimirFila();
        System.out.println("Clientes en espera: " + fila.getSize());
    }
}
