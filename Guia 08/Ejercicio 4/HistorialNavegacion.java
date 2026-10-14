/*
Quiero implementar en Java un simulador simple de historial de navegación web utilizando una Pila (Stack) basada en un arreglo de Strings para guardar las URLs.
Este problema funciona fundamentalmente bajo el principio LIFO (Last In, First Out) porque en cualquier navegador web, la última página que visitaste es obligatoriamente la primera a la que regresas cuando presionas el botón 'Atrás'.
Las operaciones que necesito son: visitar una página (equivalente a 'push', apilando la nueva URL), volver a la página anterior (equivalente a 'pop', que retira la página actual para regresar a la anterior) y consultar la página actual (equivalente a 'peek').
El arreglo interno se manejará con una variable de índice para el tope iniciada en -1. Los casos límite que se deben contemplar son: intentar volver atrás cuando el historial está vacío (no hay páginas previas) y visitar una página cuando el arreglo alcanzó su tamaño máximo.
Para la prueba de ejecución, quiero visitar 'google.com', luego 'github.com' y finalmente 'stackoverflow.com'. Después, simular presionar el botón de 'Atrás' (haciendo un pop) y consultar la página actual para comprobar que efectivamente regresamos a 'github.com'.
*/

/**
 * Historial de navegación LIFO almacenado en un arreglo de capacidad fija.
 */
public class HistorialNavegacion {
    private final String[] urls;
    // top indica el índice de la URL actual; -1 representa un historial vacío.
    private int top = -1;

    /**
     * Crea un historial con la capacidad indicada.
     *
     * @param capacidad cantidad máxima de URLs que puede guardar
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public HistorialNavegacion(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        urls = new String[capacidad];
    }

    /**
     * Registra una visita y la convierte en la página actual.
     *
     * @param url dirección de la página visitada
     * @throws IllegalStateException si el arreglo del historial está lleno
     */
    public void visitarPagina(String url) {
        if (estaLleno()) {
            throw new IllegalStateException("No se puede visitar la página: el historial está lleno.");
        }

        // Se valida la capacidad antes de avanzar top y guardar la URL.
        top++;
        urls[top] = url;
    }

    /**
     * Retira y devuelve la página actual para volver a la anterior.
     *
     * @return URL que se retiró del tope
     * @throws IllegalStateException si el historial está vacío
     */
    public String volverAtras() {
        if (estaVacio()) {
            throw new IllegalStateException("No hay páginas anteriores en el historial.");
        }

        String urlRetirada = urls[top];
        top--;
        return urlRetirada;
    }

    /**
     * Consulta la página actual sin modificar el historial.
     *
     * @return URL ubicada en el tope
     * @throws IllegalStateException si el historial está vacío
     */
    public String consultarPaginaActual() {
        if (estaVacio()) {
            throw new IllegalStateException("El historial está vacío.");
        }
        return urls[top];
    }

    private boolean estaVacio() {
        return top == -1;
    }

    private boolean estaLleno() {
        return top == urls.length - 1;
    }

    /**
     * Demuestra la visita de páginas y el funcionamiento del botón Atrás.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        HistorialNavegacion historial = new HistorialNavegacion(3);

        historial.visitarPagina("google.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("stackoverflow.com");

        System.out.println("Página actual: " + historial.consultarPaginaActual());
        System.out.println("Página retirada: " + historial.volverAtras());
        System.out.println("Página actual después de Atrás: " + historial.consultarPaginaActual());
    }
}