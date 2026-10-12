# Explicacion de `MinimoVector`

El programa encuentra y muestra el menor valor de un arreglo de enteros. El calculo esta en un metodo reutilizable, `encontrarMinimo`, y `main` lo demuestra con datos definidos en el propio codigo. El metodo devuelve el valor, no la posicion donde aparece, y no modifica el arreglo recibido.

## Recorrido paso a paso

1. `main` crea el arreglo `{8, -3, 5, 0, -3}`.
2. Llama a `encontrarMinimo(numeros)` y guarda el valor devuelto en `minimo`.
3. `encontrarMinimo` valida la entrada. Si la referencia es `null` o el arreglo no contiene elementos, lanza `IllegalArgumentException`.
4. Si la entrada es valida, inicializa `minimo` con el primer elemento, `numeros[0]`.
5. El bucle comienza en el indice `1`, pues el elemento de indice `0` ya se uso como valor inicial. Recorre los elementos restantes y compara cada uno con `minimo`.
6. Cuando un elemento es menor que el minimo actual, el metodo actualiza `minimo`.
7. Al terminar el bucle, devuelve el menor valor. `main` lo imprime.

Para el arreglo de demostracion, el seguimiento es:

| Elemento revisado | Minimo despues de revisarlo |
| --- | --- |
| `8` (valor inicial) | `8` |
| `-3` | `-3` |
| `5` | `-3` |
| `0` | `-3` |
| `-3` | `-3` |

La salida es `El valor minimo es: -3`.

## Por que funciona asi

En cada paso, `minimo` representa el valor mas pequeno entre los elementos que ya se han revisado. Si el siguiente elemento es menor, pasa a ser el nuevo minimo; si no, el minimo anterior sigue siendo correcto. Al terminar de inspeccionar todos los elementos, esa variable contiene el menor valor del arreglo completo.

La inicializacion con `numeros[0]` evita inventar un valor inicial o centinela. Inicializar, por ejemplo, con `0` daria un resultado incorrecto para un arreglo cuyos valores fueran todos positivos. Empezar con un elemento real funciona tambien con numeros negativos, repetidos y con los extremos de `int`, incluido `Integer.MIN_VALUE`.

No se ordena el arreglo porque no es necesario conocer el orden de todos sus elementos: una comparacion de cada valor basta para conservar el minimo. Ordenar agregaria trabajo y podria modificar los datos si se ordenara el arreglo original.

## Complejidad

- **Tiempo: `O(n)`**, donde `n` es la longitud del arreglo. Cada elemento se visita una sola vez. La inicializacion y la validacion cuestan tiempo constante.
- **Espacio adicional: `O(1)`**. Solo se necesitan unas pocas variables, en particular `minimo` y `indice`; no se crea otro arreglo ni una estructura que crezca con la entrada.

## Conceptos de Java utilizados

- **Arreglo de primitivos (`int[]`)**: almacena enteros y permite acceder a cada elemento por su indice. Los indices empiezan en `0`; `length` indica la cantidad de elementos.
- **Metodo estatico (`static`)**: `encontrarMinimo` pertenece a la clase y puede invocarse desde `main` sin crear un objeto `MinimoVector`.
- **Parametro y valor de retorno**: el metodo recibe `int[] numeros` y devuelve un `int`. Por eso quien lo llama obtiene el minimo y puede guardarlo o usarlo directamente.
- **Validacion y excepciones**: la condicion `numeros == null || numeros.length == 0` detecta entradas no permitidas. `||` usa cortocircuito: si `numeros == null` es verdadero, Java no evalua `numeros.length`, evitando una `NullPointerException` en esa comprobacion. `throw new IllegalArgumentException(...)` comunica que el argumento no cumple el contrato del metodo.
- **Bucle `for`**: controla el indice desde `1` mientras sea menor que `numeros.length`, incrementandolo en cada iteracion.
- **Condicional `if`**: decide si el valor recien visitado reemplaza el minimo actual.
- **Comentarios Javadoc**: los bloques `/** ... */` documentan la clase y el metodo, incluyendo parametros, resultado, excepciones y complejidad.
- **Metodo `main`**: `public static void main(String[] args)` es el punto de entrada habitual de una aplicacion Java. En esta demostracion no se usan los argumentos `args` porque los datos estan escritos en el codigo.

## Alternativas y mejoras posibles

- **Pruebas automatizadas**: se pueden agregar pruebas para arreglos de un elemento, todos positivos, todos negativos, valores repetidos y `Integer.MIN_VALUE`, ademas de verificar las excepciones. La demostracion de `main` no sustituye una suite de pruebas.
- **Ejemplo mas visible**: para mostrar tambien el arreglo completo, se podria usar `Arrays.toString(numeros)` de `java.util.Arrays`. No es necesario para calcular el minimo.
- **Contrato de entrada**: se podria usar `Objects.requireNonNull` para el caso `null`, pero lanzaria `NullPointerException`, no `IllegalArgumentException`. Solo conviene si se cambia deliberadamente el contrato requerido.
- **Separacion de responsabilidades**: mantener el calculo en un metodo y la impresion en `main` ya separa la logica del algoritmo de la demostracion. En una aplicacion mayor, la salida podria quedar a cargo de otra capa.

## Errores comunes

- Inicializar el minimo en `0`: falla cuando todos los valores son mayores que cero.
- Acceder a `numeros[0]` antes de comprobar que el arreglo no sea `null` ni vacio: puede causar `NullPointerException` o `ArrayIndexOutOfBoundsException`.
- Revisar `numeros.length` antes de comprobar `numeros == null`: la lectura de `length` sobre una referencia nula causa `NullPointerException`. El orden de la condicion actual evita ese problema gracias al cortocircuito de `||`.
- Empezar el bucle en `0`: no suele cambiar el resultado si se compara el primer elemento consigo mismo, pero repite una comparacion innecesaria. Empezar en `1` refleja que el primer elemento ya inicializo el minimo.
- Usar `>` en lugar de `<` al comparar: mantendria el maximo en vez del minimo.
- Devolver el indice en vez del elemento: `indice` indica una posicion, mientras que el objetivo del metodo es devolver `numeros[indice]` como valor minimo.
- Ordenar el arreglo para encontrar el minimo: hace trabajo adicional y puede cambiar el orden original sin aportar nada que el recorrido lineal no resuelva.
- Asumir que hay desbordamiento en la comparacion: aqui solo se comparan enteros; no se suman ni restan, asi que la busqueda no introduce desbordamiento aritmetico.
