/*
Quiero implementar en Java un programa que simule una torre de platos utilizando una estructura de datos basada en arreglos. Cada plato estará representado por su nombre (String).
Este problema se resuelve obligatoriamente con una Pila (Stack) y no con una Cola (Queue) porque, al apilar objetos físicos, el último plato que se coloca en la cima de la torre es el único accesible y, por lo tanto, debe ser el primero en retirarse. Esto define un comportamiento LIFO (Last In, First Out). Si usáramos una Cola (FIFO), estaríamos intentando sacar el primer plato que pusimos en la base, lo cual derrumbaría la torre.
Las operaciones necesarias que debe tener la estructura son: agregar un plato (equivalente a push), retirar el plato superior (equivalente a pop) y consultar cuál es el plato que está arriba (equivalente a peek).
Para manejar el arreglo interno, se usará un índice que apunte al plato superior. Se deben contemplar los casos límite: no poder retirar ni consultar si la torre está vacía, y no poder agregar si el arreglo interno llegó a su capacidad máxima.
Para verificar el comportamiento esperado, quiero ejecutar una prueba agregando tres platos ("Plato 1", "Plato 2", "Plato 3"), consultar cuál es el superior, retirar uno y volver a consultar.
*/

/**
 * Simula una torre de platos LIFO usando un arreglo de capacidad fija.
 */
public class TorreDePlatos {
    private final String[] platos;
    // top es el índice del plato superior; -1 representa una torre vacía.
    private int top = -1;

    /**
     * Crea una torre con la capacidad indicada.
     *
     * @param capacidad cantidad máxima de platos
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public TorreDePlatos(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        platos = new String[capacidad];
    }

    /**
     * Agrega un plato nuevo en la cima de la torre.
     *
     * @param plato nombre del plato que se agregará
     * @throws IllegalStateException si la torre está llena
     */
    public void agregarPlato(String plato) {
        if (estaLlena()) {
            throw new IllegalStateException("No se puede agregar: la torre está llena.");
        }

        // Se valida la capacidad antes de avanzar top y escribir en el arreglo.
        top++;
        platos[top] = plato;
    }

    /**
     * Retira y devuelve el plato que está en la cima.
     *
     * @return nombre del plato retirado
     * @throws IllegalStateException si la torre está vacía
     */
    public String retirarPlato() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede retirar: la torre está vacía.");
        }

        String platoRetirado = platos[top];
        top--;
        return platoRetirado;
    }

    /**
     * Consulta el plato superior sin retirarlo.
     *
     * @return nombre del plato superior
     * @throws IllegalStateException si la torre está vacía
     */
    public String consultarPlatoSuperior() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede consultar: la torre está vacía.");
        }
        return platos[top];
    }

    private boolean estaVacia() {
        return top == -1;
    }

    private boolean estaLlena() {
        return top == platos.length - 1;
    }

    /**
     * Demuestra el orden LIFO con tres platos.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        TorreDePlatos torre = new TorreDePlatos(3);

        torre.agregarPlato("Plato 1");
        torre.agregarPlato("Plato 2");
        torre.agregarPlato("Plato 3");

        System.out.println("Plato superior: " + torre.consultarPlatoSuperior());
        System.out.println("Plato retirado: " + torre.retirarPlato());
        System.out.println("Nuevo plato superior: " + torre.consultarPlatoSuperior());
    }
}