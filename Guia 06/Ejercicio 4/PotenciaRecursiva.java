/*
Quiero implementar en Java una función recursiva que calcule una potencia sin usar la función Math.pow.
Este problema puede resolverse recursivamente porque una potencia (una base elevada a un exponente) puede verse como multiplicaciones sucesivas de la base por sí misma.
El caso base es cuando el exponente es 0, porque matemáticamente cualquier número elevado a la 0 es 1, lo que detiene la recursión y debe devolver 1.
El caso recursivo es base * potencia(base, exponente - 1), porque en cada llamada el problema se reduce restándole 1 al exponente, acercándose al caso base.
La función debe devolver un número entero con el resultado del cálculo en cada caso.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos: caso 1: base = 2, exponente = 0; caso 2: base = 2, exponente = 1; caso 3: base = 2, exponente = 3.
*/

/**
 * Ejercicio 4: potencia mediante recursividad.
 *
 * La función recursiva sigue la idea de que:
 * potencia(base, exponente) = base * potencia(base, exponente - 1)
 * con caso base en exponente == 0.
 */
public class PotenciaRecursiva {

    /**
     * Calcula la potencia de una base elevada a un exponente usando recursividad.
     *
     * Caso base:
     * Si exponente == 0, la función devuelve 1 porque cualquier número elevado a 0 es 1.
     * Esto detiene la recursión y finaliza la cadena de llamadas.
     *
     * Caso recursivo:
     * Si exponente > 0, la función realiza la llamada potencia(base, exponente - 1)
     * y luego multiplica el resultado por la base.
     * En cada llamada se reduce el exponente en 1 para acercarse al caso base.
     *
     * Pila de llamadas:
     * Cuando se invoca potencia(2, 3), Java apila estas llamadas:
     * potencia(2, 3) -> potencia(2, 2) -> potencia(2, 1) -> potencia(2, 0)
     * Cada llamada espera a que la siguiente resuelva el cálculo más pequeño,
     * y después multiplica el resultado por la base.
     *
     * @param base base de la potencia
     * @param exponente exponente al que se elevará la base
     * @return resultado de base^exponente
     */
    public static int potencia(int base, int exponente) {
        if (exponente < 0) {
            throw new IllegalArgumentException("El exponente debe ser mayor o igual que 0.");
        }

        // Caso base: cualquier número elevado a 0 es 1.
        if (exponente == 0) {
            return 1;
        }

        // Caso recursivo: base * potencia(base, exponente - 1)
        // En cada llamada, el exponente se reduce en 1 hasta alcanzar el caso base.
        return base * potencia(base, exponente - 1);
    }

    /**
     * Método principal con los casos de prueba indicados.
     */
    public static void main(String[] args) {
        int caso1Base = 2;
        int caso1Exponente = 0;

        int caso2Base = 2;
        int caso2Exponente = 1;

        int caso3Base = 2;
        int caso3Exponente = 3;

        System.out.println("potencia(" + caso1Base + ", " + caso1Exponente + ") = " + potencia(caso1Base, caso1Exponente));
        System.out.println("potencia(" + caso2Base + ", " + caso2Exponente + ") = " + potencia(caso2Base, caso2Exponente));
        System.out.println("potencia(" + caso3Base + ", " + caso3Exponente + ") = " + potencia(caso3Base, caso3Exponente));
    }
}
