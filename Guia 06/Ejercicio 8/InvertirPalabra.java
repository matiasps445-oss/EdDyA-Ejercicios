/*
Quiero implementar en Java una función recursiva que invierta un String.
Este problema puede resolverse recursivamente porque invertir una palabra entera es equivalente a tomar el primer carácter, invertir el resto de la palabra y luego colocar ese primer carácter al final.
El caso base es cuando la cadena está vacía o tiene un solo carácter (length <= 1), porque una cadena así ya está invertida por naturaleza y detiene la recursión.
El caso recursivo se forma llamando a la función con el resto de la cadena (separando el primer carácter) y concatenando ese primer carácter al final. En cada llamada la cadena se reduce eliminando su primer letra.
Al volver de la recursión, el resultado se reconstruye concatenando los caracteres en orden inverso. La función debe devolver un String.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos:
- caso 1: palabra = "a"
- caso 2: palabra = "hola"
- caso 3: palabra = "recursividad"
*/

/**
 * Ejercicio 8: inversión de una palabra mediante recursividad.
 */
public class InvertirPalabra {

    /**
     * Invierte los caracteres de una palabra de forma recursiva.
     *
     * Caso base:
     * Si la palabra tiene cero o un carácter, se devuelve sin cambios,
     * porque ya está invertida y no necesita más llamadas.
     *
     * Caso recursivo:
     * Se invierte la subcadena que empieza en el segundo carácter y se agrega
     * el primer carácter al final del resultado.
     *
     * Pila de llamadas:
     * Para "hola", las llamadas pendientes son invertirPalabra("hola"),
     * invertirPalabra("ola"), invertirPalabra("la") e invertirPalabra("a").
     * La última devuelve "a"; al retornar, se agregan 'l', 'o' y 'h',
     * reconstruyendo "aloh".
     *
     * @param palabra cadena cuyos caracteres se invertirán
     * @return palabra con los caracteres en orden inverso
     */
    public static String invertirPalabra(String palabra) {
        // Caso base: una cadena vacía o de un carácter ya está invertida.
        if (palabra.length() <= 1) {
            return palabra;
        }

        // Caso recursivo: invierte el resto y coloca el primer carácter al final.
        return invertirPalabra(palabra.substring(1)) + palabra.charAt(0);
    }

    /**
     * Ejecuta los casos de prueba solicitados.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        String primeraPalabra = "a";
        String segundaPalabra = "hola";
        String terceraPalabra = "recursividad";

        System.out.println("invertirPalabra(\"" + primeraPalabra + "\") = "
                + invertirPalabra(primeraPalabra));
        System.out.println("invertirPalabra(\"" + segundaPalabra + "\") = "
                + invertirPalabra(segundaPalabra));
        System.out.println("invertirPalabra(\"" + terceraPalabra + "\") = "
                + invertirPalabra(terceraPalabra));
    }
}