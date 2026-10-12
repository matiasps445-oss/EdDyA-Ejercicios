# Explicacion de `MayorMatriz`

El programa busca el valor mayor dentro de una matriz rectangular de enteros y devuelve ese valor como resultado. Para la demostracion, usa dos ejemplos: una matriz con numeros negativos y otra con valores cercanos a los limites de `int`.

El metodo reutilizable es `encontrarMayor(int[][] matriz)`. Su objetivo es inspeccionar todas las celdas de la matriz, sin modificarla, y devolver el elemento mas grande que encuentre.

## Funcionamiento paso a paso

1. `encontrarMayor` comienza llamando a `validarMatriz(matriz)`.
2. `validarMatriz` comprueba primero si la matriz es `null` o si tiene cero filas. Si ocurre alguno de esos casos, lanza `IllegalArgumentException`.
3. Luego verifica que la primera fila no sea `null` y que tenga al menos una columna. Si no, tambien lanza la excepcion.
4. A continuacion, toma la longitud de la primera fila como referencia y recorre el resto de filas. Si alguna fila es `null` o tiene una longitud distinta, la matriz es irregular y se rechaza.
5. Si la validacion pasa, se inicializa `mayor` con el primer elemento real de la matriz: `matriz[0][0]`.
6. El primer `for-each` recorre cada fila; el segundo recorre cada valor dentro de esa fila.
7. En cada iteracion, se compara `valor > mayor`. Si la condicion es verdadera, se actualiza `mayor` con el nuevo valor mas grande.
8. Cuando termina el recorrido completo, el metodo devuelve `mayor`.

En la matriz `{{-8, -2, -15}, {-4, -1, -6}}`, el mayor valor es `-1`, porque aunque todos los numeros son negativos, uno de ellos es el menos negativo. En `{{Integer.MIN_VALUE, -1}, {0, Integer.MAX_VALUE}}`, el mayor es `Integer.MAX_VALUE`.

## Por que se inicializa desde un elemento real

El punto mas importante del algoritmo es esta linea:

```java
int mayor = matriz[0][0];
```

No se usa `0` ni otro valor artificial como centinela. Esa decisión es intencional: si la matriz contuviera solo valores negativos, comenzar en `0` produciria un resultado incorrecto, porque `0` no forma parte de la matriz y no representa el maximo real.

Por ejemplo, con `{{-8, -2, -15}, {-4, -1, -6}}`, el mayor real es `-1`, y `0` no es un valor valido para evaluarlo. Al inicializar con un elemento real de la matriz, el algoritmo evita esa trampa y funciona tanto con numeros negativos como con extremos del tipo `int`.

## Por que se valida la forma de la matriz

En Java, `int[][]` no garantiza que todas las filas tengan la misma longitud. Una matriz puede ser irregular, por ejemplo:

```java
int[][] matriz = {
    {1, 2, 3},
    {4, 5}
};
```

Esto no es una matriz rectangular, y en ese caso el algoritmo ya no tiene una forma de recorrerla con la misma regla para todas las filas. Es por eso que `validarMatriz` exige:

- que la matriz no sea `null`
- que tenga al menos una fila
- que la primera fila no sea `null`
- que tenga al menos una columna
- que todas las filas tengan la misma longitud

Esto convierte la entrada en un contrato claro: solo se aceptan matrices rectangulares validas.

## Por que se implemento asi

La forma natural de buscar el mayor valor en una matriz es recorrer cada elemento y comparar si es mayor que el valor actual guardado. Esa idea coincide perfectamente con la estructura de una matriz bidimensional: una fila es un conjunto de columnas y cada una se necesita inspeccionar.

Los bucles anidados expresan esa idea con claridad:

```java
for (int[] fila : matriz) {
    for (int valor : fila) {
        if (valor > mayor) {
            mayor = valor;
        }
    }
}
```

El bucle externo recorre filas y el interno recorre cada valor de la fila. Como no hay ninguna propiedad especial de orden en la matriz, cada celda debe revisarse. Si se omitiera una sola celda, se podria perder el maximo real.

La validacion se hace antes de comparar porque una mayor no tiene sentido si la estructura es invalida. Es mejor fallar rápidamente con una excepcion explicita que devolver un valor basado en una entrada que no cumple el contrato.

Ademas, el metodo no modifica la matriz. Solo lee los valores; la variable `mayor` es una copia del mayor encontrado, no una referencia a la matriz ni a un elemento concreto.

## Complejidad

Sean `r` el numero de filas y `c` el numero de columnas de la matriz rectangular:

- La validacion revisa cada fila una vez: `O(r)`.
- El recorrido para comparar todos los valores cuesta `O(r * c)`.
- El costo total es `O(r + r * c)`, que se simplifica a `O(r * c)` para matrices validas con al menos una columna.
- El espacio auxiliar es `O(1)`: solo se guarda el valor actual y las variables de recorrido. No se crea otra matriz ni ninguna estructura adicional.

La clave de esta complejidad es que cada celda se visita exactamente una vez. No existe una manera mas corta de garantizar el maximo si la matriz no tiene un orden previo.

## Conceptos de Java utilizados

- **Arreglo bidimensional `int[][]`**: representacion de una matriz como arreglo de filas, donde cada fila es un `int[]`.
- **Bucles `for-each` anidados**: permiten recorrer cada fila y luego cada valor sin manejar indices manualmente.
- **`IllegalArgumentException`**: se usa para indicar que la entrada no cumple el contrato del metodo.
- **Validacion defensiva**: verificar antes de ejecutar el algoritmo que la entrada es segura y tiene la forma esperada.
- **Inicializacion con un valor real**: `matriz[0][0]` evita errores con matrices formadas exclusivamente por negativos.
- **`main`**: se usa para demostrar el caso de uso con ejemplos concretos.
- **Javadoc**: documenta el proposito, parametros, excepciones y complejidad del metodo.

## Mejora o alternativas posibles

- **Devolver coordenadas ademas del valor**: si en algun momento se necesitara saber no solo el mayor sino donde aparece, se podria devolver un objeto con el valor y sus indices. En este ejercicio no se pide esa informacion.
- **Usar un `record` o una clase auxiliar**: si se quisiera devolver mas informacion, un record haria la API mas limpia y expresiva.
- **Separar validacion de la busqueda**: en programas mayores, podriamos tener un metodo `validarMatriz` en otra clase de utilidades. Aqui se mantiene junto al algoritmo para mantener el ejemplo simple y claro.
- **Ordenar la matriz para encontrar el maximo**: seria una alternativa mucho menos eficiente, porque ordenarla implica `O(r * c log(r * c))` o mas en general, mientras que una pasada lineal es mejor.
- **Usar `Integer.MIN_VALUE` como valor inicial**: es una alternativa valida para algunos casos, pero solo si se asegura que la matriz no es vacia y que se compara el primer valor real de todas formas. La solucion actual es mas directa y evita suposiciones adicionales.

## Errores comunes

- **Iniciar `mayor` en `0`**: falla si la matriz contiene solo valores negativos.
- **No validar filas nulas**: una fila `null` provocaria un `NullPointerException` al intentar recorrerla.
- **No comprobar la rectangularidad**: una matriz con filas de distinta longitud puede engañar al algoritmo y producir resultados inconsistentes.
- **Omitir la validacion de la matriz vacia**: acceder a `matriz[0][0]` cuando no hay filas provoca un error.
- **Modificar la matriz durante la busqueda**: no es necesario y rompe el contrato del metodo, que debe limitarse a leer la entrada.
- **Suponer que `int[][]` siempre tiene filas y columnas validas**: Java permite estructuras incompletas y ese tipo de datos debe controlarse explicitamente.
- **Confundir el valor mayor con su indice o posicion**: este metodo devuelve el valor, no la coordenada en la matriz.
- **Pensar que un solo recorrido basta cuando la matriz tiene filas irregulares**: la forma irregular invalida la operacion, por lo que debe rechazarse antes de calcular.

## Resumen corto

El metodo `encontrarMayor` hace exactamente lo que su nombre promete: valida la matriz, recorre toda la estructura con dos bucles anidados y compara cada valor con el mayor encontrado hasta el momento. La solucion se implementa asi porque una matriz no tiene orden interno garantizado, por lo que hay que inspeccionar todos los elementos. La clave para manejar negativos y extremos de `int` es inicializar `mayor` con un valor real de la matriz y no con un centinela artificial.
