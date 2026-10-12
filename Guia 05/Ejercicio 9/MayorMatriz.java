/**
 * Encuentra el mayor valor de una matriz rectangular de enteros.
 */
public class MayorMatriz {

    /**
     * Devuelve el mayor elemento de una matriz rectangular no vacia.
     *
     * @param matriz matriz de enteros que se va a revisar
     * @return el mayor valor de la matriz
     * @throws IllegalArgumentException si la matriz es null, vacia, irregular,
     *         contiene filas null o no tiene columnas
     * Complejidad: O(r*c) en tiempo y O(1) en espacio auxiliar.
     */
    public static int encontrarMayor(int[][] matriz) {
        validarMatriz(matriz);

        int mayor = matriz[0][0];
        for (int[] fila : matriz) {
            for (int valor : fila) {
                if (valor > mayor) {
                    mayor = valor;
                }
            }
        }

        return mayor;
    }

    private static void validarMatriz(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("La matriz debe tener al menos una fila.");
        }
        if (matriz[0] == null || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz debe tener al menos una columna.");
        }

        int columnas = matriz[0].length;
        for (int indice = 1; indice < matriz.length; indice++) {
            if (matriz[indice] == null || matriz[indice].length != columnas) {
                throw new IllegalArgumentException("La matriz debe ser rectangular y sus filas no pueden ser null.");
            }
        }
    }

    /** Demuestra el maximo con valores negativos y con los limites de int. */
    public static void main(String[] args) {
        int[][] negativos = {
                {-8, -2, -15},
                {-4, -1, -6}
        };
        int[][] limitesInt = {
                {Integer.MIN_VALUE, -1},
                {0, Integer.MAX_VALUE}
        };

        System.out.println("Mayor en la matriz de negativos: " + encontrarMayor(negativos));
        System.out.println("Mayor entre los limites de int: " + encontrarMayor(limitesInt));
    }
}
