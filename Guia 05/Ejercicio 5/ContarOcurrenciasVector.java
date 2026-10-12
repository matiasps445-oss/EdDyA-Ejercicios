/**
 * Cuenta cuantas veces aparece un valor en un arreglo de enteros.
 */
public class ContarOcurrenciasVector {

    /**
     * Devuelve el numero de apariciones del objetivo en el arreglo.
     *
     * @param numeros arreglo de enteros que se va a revisar
     * @param objetivo valor cuyas apariciones se cuentan
     * @return cantidad de elementos iguales al objetivo
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(n) en tiempo y O(1) en espacio auxiliar.
     */
    public static int contarOcurrencias(int[] numeros, int objetivo) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }

        int cantidad = 0;
        for (int numero : numeros) {
            if (numero == objetivo) {
                cantidad++;
            }
        }

        return cantidad;
    }

    /** Demuestra el conteo con un objetivo repetido y otro ausente. */
    public static void main(String[] args) {
        int[] numeros = {4, -2, 4, 7, 4, 0};
        int objetivoRepetido = 4;
        int objetivoAusente = 9;

        System.out.println("El valor " + objetivoRepetido + " aparece "
                + contarOcurrencias(numeros, objetivoRepetido) + " veces.");
        System.out.println("El valor " + objetivoAusente + " aparece "
                + contarOcurrencias(numeros, objetivoAusente) + " veces.");
    }
}
