/*
Prompt inicial utilizado:
Quiero implementar en Java un sistema simple de historial de navegación web utilizando una pila enlazada.
El programa debe permitir las siguientes operaciones: visitar una página (apilar/push), volver a la página anterior (desapilar/pop), consultar la página actual (tope/top) e imprimir el historial completo.
Es fundamental utilizar una estructura de Pila para este problema porque representa a la perfección el comportamiento del botón 'Atrás' de cualquier navegador mediante la política LIFO (Last In, First Out). En la navegación web, la última página a la que ingresaste es obligatoriamente la primera página a la que vas a regresar si decides retroceder. Cada nueva página visitada se coloca en el tope de la pila, ocultando a las anteriores, y al retroceder, se retira ese tope para revelar la página inmediatamente anterior.
Como casos especiales, el sistema debe protegerse si el usuario intenta 'volver atrás' o 'consultar la página actual' cuando el historial está vacío (pila vacía).
Para la prueba, quiero visitar 'google.com', luego 'github.com' y finalmente 'stackoverflow.com'. Consultar la página actual, volver a la página anterior y volver a consultar la página actual (debería ser github.com).

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt justificó con absoluta claridad por qué la política LIFO de la pila representa matemáticamente el comportamiento de volver a la página anterior, lo que derivó en un código exacto y funcional al primer intento.
*/
import java.util.EmptyStackException;

class Nodo {
    private final String url;
    private Nodo siguiente;

    Nodo(String url) {
        this.url = url;
        this.siguiente = null;
    }

    public String getUrl() {
        return url;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

public class HistorialNavegacion {
    private Nodo head;
    private int size;

    public void visitarPagina(String url) {
        Nodo nuevaPagina = new Nodo(url);
        nuevaPagina.setSiguiente(head);
        head = nuevaPagina;
        size++;
    }

    public String volverAtras() {
        if (estaVacio()) {
            throw new EmptyStackException();
        }

        String paginaActual = head.getUrl();
        head = head.getSiguiente();
        size--;
        return paginaActual;
    }

    public String consultarPaginaActual() {
        if (estaVacio()) {
            throw new EmptyStackException();
        }

        return head.getUrl();
    }

    public boolean estaVacio() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void imprimirHistorial() {
        if (estaVacio()) {
            System.out.println("Historial vacío.");
            return;
        }

        Nodo actual = head;
        System.out.println("Historial (actual a anterior):");
        while (actual != null) {
            System.out.println("- " + actual.getUrl());
            actual = actual.getSiguiente();
        }
    }

    public static void main(String[] args) {
        HistorialNavegacion historialVacio = new HistorialNavegacion();
        try {
            historialVacio.consultarPaginaActual();
        } catch (EmptyStackException excepcion) {
            System.out.println("No se puede consultar: el historial está vacío.");
        }

        HistorialNavegacion historial = new HistorialNavegacion();
        historial.visitarPagina("google.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("stackoverflow.com");

        historial.imprimirHistorial();
        System.out.println("Página actual: " + historial.consultarPaginaActual());

        System.out.println("Volver atrás desde: " + historial.volverAtras());
        System.out.println("Página actual después de volver: " + historial.consultarPaginaActual());
        historial.imprimirHistorial();
        System.out.println("Páginas en el historial: " + historial.getSize());
    }
}