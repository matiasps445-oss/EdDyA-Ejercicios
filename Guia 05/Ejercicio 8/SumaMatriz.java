/**
 * Suma los elementos de una matriz rectangular y cuenta las acumulaciones.
 */
public class SumaMatriz {

    /** Resultado de sumar una matriz y contar las celdas procesadas. */
    public static final class ResultadoSuma {
        private final long suma;
        private final long operaciones;

        private ResultadoSuma(long suma, long operaciones) {
            this.suma = suma;
            this.operaciones = operaciones;
        }

        public long getSuma() {
            return suma;
        }

        public long getOperaciones() {
            return operaciones;
        }
    }

    /**
     * Suma todas las celdas de una matriz rectangular.
     * Cada acumulacion de una celda cuenta como una operacion.
     *
     * @param matriz matriz rectangular de enteros
     * @return suma total y cantidad de acumulaciones realizadas
     * @throws IllegalArgumentException si la matriz o alguna fila es null,
     *         o si las filas tienen longitudes distintas
     * Complejidad: O(r + r*c) en tiempo y O(1) en espacio auxiliar.
     */
    public static ResultadoSuma sumarMatriz(int[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("La matriz no puede ser null.");
        }

        if (matriz.length > 0) {
            validarForma(matriz);
        }
        long suma = 0;
        long operaciones = 0;

        for (int[] fila : matriz) {
            for (int valor : fila) {
                suma += valor;
                operaciones++;
            }
        }

        return new ResultadoSuma(suma, operaciones);
    }

    private static void validarForma(int[][] matriz) {
        if (matriz[0] == null) {
            throw new IllegalArgumentException("Las filas de la matriz no pueden ser null.");
        }

        int columnas = matriz[0].length;
        for (int indice = 1; indice < matriz.length; indice++) {
            if (matriz[indice] == null || matriz[indice].length != columnas) {
                throw new IllegalArgumentException("La matriz debe ser rectangular y sus filas no pueden ser null.");
            }
        }

    }

    /** Demuestra la suma y el conteo en una matriz con datos y una vacia. */
    public static void main(String[] args) {
        int[][] matriz = {
                {1, -2, 3},
                {4, 5, -6}
        };
        int[][] matrizVacia = new int[0][];

        mostrarResultado("Matriz de ejemplo", sumarMatriz(matriz));
        mostrarResultado("Matriz vacia", sumarMatriz(matrizVacia));
    }

    private static void mostrarResultado(String descripcion, ResultadoSuma resultado) {
        System.out.println(descripcion + ":");
        System.out.println("  Suma: " + resultado.getSuma());
        System.out.println("  Acumulaciones: " + resultado.getOperaciones());
    }
}
