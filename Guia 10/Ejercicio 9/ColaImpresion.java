/*
Prompt inicial utilizado:
Quiero implementar en Java un sistema que simule una cola de impresión.
Primero, necesito crear una clase 'TrabajoImpresion' que contenga tres atributos: nombre del archivo, cantidad de páginas y usuario que lo envió.
El sistema debe permitir las siguientes operaciones utilizando nuestra estructura de Cola enlazada: agregar trabajos a la cola (encolar/enqueue), imprimir el próximo trabajo (desencolar/dequeue), consultar el próximo archivo a imprimir (frente/front) y mostrar todos los trabajos pendientes.
La estructura de datos obligatoria para este problema es una Cola. Esto se debe a que una impresora física debe procesar los documentos usando estrictamente la política FIFO (First In, First Out). En un entorno de oficina o red donde múltiples usuarios envían documentos, es imperativo respetar el orden cronológico de llegada para garantizar la justicia y evitar que un trabajo enviado después interrumpa o postergue a uno que ya estaba esperando. El primer documento que ingresa a la cola debe ser el primero en salir impreso.
Como casos especiales, el sistema debe contemplar y manejar adecuadamente el intento de 'atender al próximo cliente' o 'consultar quién sigue' cuando la fila está completamente vacía.
Para probarlo, quiero instanciar la cola, agregar 3 trabajos de impresión de distintos usuarios, consultar cuál es el próximo archivo, simular la impresión del primer trabajo (retirándolo de la cola) y finalmente mostrar todos los trabajos pendientes que quedan.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt justificó con precisión por qué una impresora real requiere una estructura FIFO para administrar los recursos compartidos de forma justa, lo que resultó en un sistema de impresión simulado perfectamente funcional.
*/
import java.util.NoSuchElementException;
import java.util.Objects;

class TrabajoImpresion {
    private final String nombreArchivo;
    private final int cantidadPaginas;
    private final String usuario;

    TrabajoImpresion(String nombreArchivo, int cantidadPaginas, String usuario) {
        this.nombreArchivo = nombreArchivo;
        this.cantidadPaginas = cantidadPaginas;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nombreArchivo + " (" + cantidadPaginas + " páginas, usuario: " + usuario + ")";
    }
}

class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}

class ColaEnlazada<T> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }
        size++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola de impresión está vacía.");
        }

        T datoRetirado = head.getDato();
        head = head.getSiguiente();
        size--;
        if (head == null) {
            tail = null;
        }
        return datoRetirado;
    }

    public T consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay trabajos pendientes.");
        }
        return head.getDato();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
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
            System.out.println("No hay trabajos pendientes.");
            return;
        }

        Nodo<T> actual = head;
        while (actual != null) {
            System.out.println("- " + actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}

public class ColaImpresion {
    public static void main(String[] args) {
        ColaEnlazada<TrabajoImpresion> trabajos = new ColaEnlazada<>();

        TrabajoImpresion trabajoUno = new TrabajoImpresion("informe.pdf", 8, "Ana");
        TrabajoImpresion trabajoDos = new TrabajoImpresion("factura.pdf", 2, "Luis");
        TrabajoImpresion trabajoTres = new TrabajoImpresion("presentacion.pdf", 15, "Marta");

        System.out.println("Encolando trabajo: " + trabajoUno);
        trabajos.encolar(trabajoUno);
        System.out.println("Encolando trabajo: " + trabajoDos);
        trabajos.encolar(trabajoDos);
        System.out.println("Encolando trabajo: " + trabajoTres);
        trabajos.encolar(trabajoTres);

        System.out.println("\nTrabajos pendientes:");
        trabajos.imprimir();

        System.out.println("\nPróximo archivo a imprimir: " + trabajos.consultarFrente());

        TrabajoImpresion trabajoImpreso = trabajos.desencolar();
        System.out.println("\nImprimiendo: " + trabajoImpreso);

        System.out.println("\nTrabajos pendientes después de imprimir:");
        trabajos.imprimir();
    }
}