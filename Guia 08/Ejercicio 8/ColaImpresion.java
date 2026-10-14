/*
Quiero implementar en Java un sistema de cola de impresión utilizando una estructura de Cola (Queue) basada en arreglos. Cada documento a imprimir será un objeto que tiene un nombre (String) y una cantidad de páginas (int).
Una cola representa correctamente el orden de impresión porque utiliza el principio FIFO (First In, First Out). En el mundo real, cuando varios usuarios envían documentos a una impresora, el primer documento que llega al sistema (spooler) debe ser el primero en imprimirse físicamente para mantener un orden justo y cronológico.
El sistema debe permitir: agregar un documento a la cola (enqueue) avanzando el índice 'rear', e imprimir el documento (dequeue) retirándolo del índice 'front' y devolviéndolo.
Se deben contemplar los casos límite: si la cola está llena, el sistema debe rechazar nuevos trabajos de impresión; si la cola está vacía, el intento de imprimir debe indicar que no hay documentos pendientes.
Para verificar el comportamiento, quiero agregar a la cola el 'Documento A' (10 páginas) y luego el 'Documento B' (5 páginas). Al ejecutar la operación de imprimir una vez, el sistema debe mostrar que se imprimió el 'Documento A', respetando el orden de llegada.
*/

/**
 * Cola circular FIFO para administrar trabajos de impresión.
 */
public class ColaImpresion {

    /**
     * Documento que se agrega a la cola de impresión.
     */
    public static class Documento {
        private final String nombre;
        private final int cantidadPaginas;

        public Documento(String nombre, int cantidadPaginas) {
            this.nombre = nombre;
            this.cantidadPaginas = cantidadPaginas;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidadPaginas() {
            return cantidadPaginas;
        }

        @Override
        public String toString() {
            return nombre + " (" + cantidadPaginas + " páginas)";
        }
    }

    private final Documento[] trabajos;
    // front señala el siguiente trabajo; rear señala el último trabajo agregado.
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    /**
     * Crea una cola de impresión de capacidad fija.
     *
     * @param capacidad cantidad máxima de trabajos pendientes
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public ColaImpresion(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        trabajos = new Documento[capacidad];
    }

    /**
     * Agrega un documento al final de la cola.
     *
     * @param documento trabajo de impresión que se agregará
     * @throws IllegalStateException si la cola está llena
     */
    public void encolarDocumento(Documento documento) {
        if (estaLlena()) {
            throw new IllegalStateException("No se puede agregar el trabajo: la cola está llena.");
        }

        rear = (rear + 1) % trabajos.length;
        trabajos[rear] = documento;
        size++;
    }

    /**
     * Retira y devuelve el siguiente documento que debe imprimirse.
     *
     * @return documento más antiguo en la cola
     * @throws IllegalStateException si no hay trabajos pendientes
     */
    public Documento imprimirSiguiente() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay documentos pendientes de impresión.");
        }

        Documento documento = trabajos[front];
        trabajos[front] = null;
        front = (front + 1) % trabajos.length;
        size--;
        return documento;
    }

    private boolean estaVacia() {
        return size == 0;
    }

    private boolean estaLlena() {
        return size == trabajos.length;
    }

    /**
     * Ejecuta la prueba de impresión en orden de llegada.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion(3);
        cola.encolarDocumento(new Documento("Documento A", 10));
        cola.encolarDocumento(new Documento("Documento B", 5));

        Documento documentoImpreso = cola.imprimirSiguiente();
        System.out.println("Imprimiendo: " + documentoImpreso);
    }
}