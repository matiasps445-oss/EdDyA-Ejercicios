/*
Quiero implementar en Java una función recursiva que sume los dígitos de un número entero positivo.
Este problema puede resolverse recursivamente porque la suma de los dígitos de un número es igual a su último dígito más la suma de los dígitos del resto del número.
Para obtener el último dígito de forma matemática se utiliza la operación módulo 10 (numero % 10).
Para reducir el número y quitarle ese último dígito en cada paso, se utiliza la división entera por 10 (numero / 10).
El caso base es cuando el número es menor a 10, porque está compuesto por un solo dígito y su suma es simplemente el mismo número, deteniendo así la recursión.
El caso recursivo se forma combinando los resultados: (numero % 10) + sumarDigitos(numero / 10).
La función debe devolver un número entero con la suma acumulada.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos para validar:
- caso 1: numero = 8
- caso 2: numero = 45
- caso 3: numero = 1234
*/

/**
 * Ejercicio 7: suma de dígitos mediante recursividad.
 */
public class SumarDigitos {

    /**
     * Calcula la suma de los dígitos de un número entero positivo.
     *
     * Caso base:
     * Si numero es menor que 10, devuelve el mismo numero porque contiene
     * un solo dígito. Esta condición detiene la recursión.
     *
     * Caso recursivo:
     * Suma el último dígito, obtenido con numero % 10, al resultado de llamar
     * al método con numero / 10, que elimina ese último dígito.
     *
     * Pila de llamadas:
     * Para 1234, las llamadas pendientes son sumarDigitos(1234),
     * sumarDigitos(123), sumarDigitos(12) y sumarDigitos(1). La última
     * devuelve 1; al retornar, cada llamada suma su último dígito pendiente,
     * produciendo 3, 6 y finalmente 10.
     *
     * @param numero número entero positivo cuyos dígitos se sumarán
     * @return suma de los dígitos de numero
     */
    public static int sumarDigitos(int numero) {
        // Caso base: un número positivo menor que 10 tiene un único dígito.
        if (numero < 10) {
            return numero;
        }

        // Caso recursivo: suma el último dígito al resultado del resto.
        return (numero % 10) + sumarDigitos(numero / 10);
    }

    /**
     * Ejecuta los casos de prueba solicitados.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int primerNumero = 8;
        int segundoNumero = 45;
        int tercerNumero = 1234;

        System.out.println("sumarDigitos(" + primerNumero + ") = " + sumarDigitos(primerNumero));
        System.out.println("sumarDigitos(" + segundoNumero + ") = " + sumarDigitos(segundoNumero));
        System.out.println("sumarDigitos(" + tercerNumero + ") = " + sumarDigitos(tercerNumero));
    }
}