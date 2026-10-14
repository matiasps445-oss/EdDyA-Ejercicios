/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'int contarOcurrencias(int dato)' para nuestra lista enlazada simple.
El objetivo de este método es recorrer la lista completa y contar cuántas veces aparece un valor específico.
A diferencia del método de búsqueda tradicional (que puede usar un 'return' o 'break' para cortar el recorrido apenas encuentra la primera coincidencia), en este caso NO se debe interrumpir el ciclo. El algoritmo debe iterar obligatoriamente hasta el final de la estructura (es decir, hasta que el puntero 'actual' sea null). Si cortáramos el recorrido en la primera coincidencia, ignoraríamos los nodos posteriores que también podrían contener el mismo valor.
Durante el recorrido secuencial, se debe utilizar una variable contadora iniciada en 0, que se incrementará en 1 cada vez que 'actual.getDato() == dato'.
Para la prueba de ejecución, quiero construir una lista exactamente con estos valores: 10 -> 20 -> 10 -> 30 -> 10. Luego, llamar al método para contar las ocurrencias del valor 10, y el programa debe imprimir en consola que el resultado es 3.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó de manera perfecta por qué no se debe cortar el ciclo while al encontrar la primera coincidencia, lo que permitió que la lógica de conteo abarcara toda la lista de forma exitosa.
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

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
            return;
        }

        Nodo actual = head;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public int contarOcurrencias(int dato) {
        int contador = 0;
        Nodo actual = head;

        while (actual != null) {
            if (actual.getDato() == dato) {
                contador++;
            }
            actual = actual.getSiguiente();
        }

        return contador;
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(10);

        int ocurrencias = lista.contarOcurrencias(10);
        System.out.println("Ocurrencias de 10: " + ocurrencias);
    }
}