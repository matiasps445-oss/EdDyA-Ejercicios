# Explicacion de `BurbujaOrdenacion`

El programa implementa Bubble Sort, un algoritmo de ordenamiento sencillo y muy didactico para aprender como se comparan y se intercambian elementos dentro de un arreglo. La idea central es recorrer el arreglo varias veces y empujar el valor mas grande hacia el final en cada pasada.

El metodo reutilizable es `ordenarBurbuja(int[] numeros)`. Este metodo no solo ordena el arreglo, sino que tambien devuelve un resultado que incluye la cantidad de comparaciones e intercambios realizados.

## Funcionamiento paso a paso

1. El metodo `ordenarBurbuja` valida si el arreglo recibido es `null`.
2. Si es `null`, lanza `IllegalArgumentException` porque no se puede ordenar una referencia inexistente.
3. Para no alterar el arreglo original, crea una copia con `numeros.clone()`.
4. Inicializa dos contadores: `comparaciones` e `intercambios` en cero.
5. El bucle externo `for (int i = 0; i < arregloOrdenado.length - 1; i++)` representa una pasada del algoritmo.
6. El bucle interno `for (int j = 0; j < arregloOrdenado.length - 1 - i; j++)` recorre el segmento no ordenado del arreglo.
7. En cada iteracion, incrementa `comparaciones` antes de comparar dos elementos adyacentes.
8. Si `arregloOrdenado[j] > arregloOrdenado[j + 1]`, significa que estan desordenados, entonces se intercambian con una variable auxiliar.
9. Cada intercambio aumenta `intercambios` en uno.
10. Cuando termina la ejecucion de ambos bucles, se devuelve un objeto `ResultadoOrdenacion` con:
    - el arreglo ordenado
    - el total de comparaciones
    - el total de intercambios

En el ejemplo `int[] ejemplo = {5, 1, 4, 2, 8, -3, 7};`, el algoritmo va moviendo valores grandes hacia la derecha hasta dejar el arreglo en orden ascendente: `[-3, 1, 2, 4, 5, 7, 8]`.

## Por que se implemento asi

Bubble Sort se usa aqui por su claridad pedagogica. Cada pasada compara elementos vecinos y, si estan en el orden incorrecto, los intercambia. Esto muestra de forma muy visual el proceso de ordenacion: el elemento mayor se "burbujea" hacia el final del arreglo.

La estructura del algoritmo encaja perfectamente con el problema porque la ordenacion no necesita una estructura auxiliar compleja. Solo se necesitan:

- dos indices para recorrer el arreglo
- una variable temporal para intercambiar dos elementos
- contadores para medir trabajo

La decision de clonar el arreglo original es importante. Si se ordenara el arreglo directo, se perderia la informacion inicial y la demostracion quedaria menos limpia. Al devolver un arreglo nuevo ordenado, se mantiene la referencia original intacta y el metodo se vuelve mas seguro y reutilizable.

Ademas, devolver un objeto `ResultadoOrdenacion` es una buena practica porque agrupa varias piezas de informacion relacionadas: el arreglo ordenado y dos contadores. Asi el metodo tiene una salida estructurada, en vez de depender de varias variables globales o de una implicacion menos clara.

## Como funciona el conteo de comparaciones e intercambios

El contador `comparaciones` se incrementa cada vez que se compara un par de elementos adyacentes. Eso se hace en la linea:

```java
comparaciones++;
```

El contador `intercambios` se incrementa solo cuando realmente cambia el orden de dos elementos. Eso representa una accion costosa del algoritmo, porque implica copiar un valor a una variable auxiliar y reubicar ambos elementos.

En un arreglo de longitud `n`, el numero de comparaciones puede aproximarse a `n * (n - 1) / 2` en el peor caso, y el tiempo total suele expresarse como `O(n^2)`. Eso hace que Bubble Sort sea correcto para aprender, pero no optimal para grandes colecciones.

## Complejidad

Sea `n` la cantidad de elementos del arreglo:

- **Tiempo: `O(n^2)`**. En cada pasada se revisan menos elementos y el proceso se repite varias veces hasta ordenar todo.
- **Espacio auxiliar: `O(1)`**. Solo usa variables temporales y contadores; no crea un arreglo adicional aparte de la copia del argumento.

Esto significa que si el arreglo crece, el tiempo crece de forma cuadratica. Para colecciones grandes se prefieren algoritmos como Insercion, Seleccion, Merge Sort o Quick Sort.

## Conceptos de Java utilizados

- **Arreglos `int[]`**: estructura basica para almacenar una secuencia de enteros.
- **`clone()`**: permite copiar un arreglo sin modificar el original.
- **Bucle `for` anidados**: el externo representa cada pasada y el interno recorre el segmento a ordenar.
- **Variables temporales**: `auxiliar` se usa para intercambiar dos elementos.
- **Clases internas estaticas**: `ResultadoOrdenacion` es una clase anidada `static` dentro de `BurbujaOrdenacion`.
- **Campos `private final`**: hacen inmutables los atributos del resultado despues de construir el objeto.
- **Getters**: permiten acceder a los datos sin permitir modificaciones externas.
- **`IllegalArgumentException`**: se usa para validar entrada invalida, en este caso `null`.
- **`Arrays.toString()`**: convierte el arreglo en texto para mostrarlo por consola.
- **`main`**: prepara ejemplos y muestra los resultados.
- **Javadoc**: documenta proposito, parametros, retorno y excepciones.

## Mejores o alternativas posibles

- **Usar un record**: desde Java 16, `ResultadoOrdenacion` podria ser un `record`, reduciendo codigo repetitivo y dejando la estructura mas compacta.
- **No clonar el arreglo**: si la estrategia fuera ordenar el original directamente, se reduce memoria, pero se pierde la posibilidad de conservar los datos de entrada. Para aprendizaje y depuracion, la copia es preferible.
- **Optimizar Bubble Sort**: agregar una bandera para detectar si ya no hubo intercambios en una pasada, lo que permite terminar antes cuando el arreglo ya esta ordenado. Esa mejora es valida y ensena otra optimizacion simple, pero no se implementa aqui porque el ejercicio busca claridad.
- **Usar otro algoritmo**: para colecciones grandes, Insercion o Merge Sort suelen ser mejores. Bubble Sort es mas sencillo de explicar, pero no el mas eficiente en rendimiento.
- **Retornar un `int[]` y dos contadores por separado**: tambien es posible, pero encapsularlos en un objeto es una mejor decision de diseño porque mantiene la coherencia de la salida.

## Errores comunes

- **Olvidar validar `null`**: intentar ejecutar `numeros.clone()` sobre `null` provoca `NullPointerException`.
- **No copiar el arreglo original**: esto altera la entrada y puede producir efectos secundarios no deseados.
- **Usar `j <= arregloOrdenado.length - i` sin ajustar el rango**: eso puede provocar accesos fuera de rango, porque se intenta leer `j + 1` cuando `j` ya llega al final.
- **No incrementar el contador de comparaciones**: entonces el programa no mide realmente el trabajo del algoritmo.
- **Incrementar intercambios aunque no haya intercambio**: este contador debe reflejar cambios reales entre elementos.
- **Suponer que Bubble Sort es eficiente para todo tipo de datos**: es correcto, pero en volumenes grandes es mucho peor que otras opciones.
- **No considerar arreglos vacios o de un elemento**: aunque funcionan, conviene probarlos para asegurar que el algoritmo no haga comparaciones invalidas.

## Resumen corto

El programa ordena un arreglo con Bubble Sort usando dos bucles anidados. El externo controla las pasadas y el interno compara elementos adyacentes. Si un elemento es mayor que el siguiente, se intercambian y se cuenta ese intercambio. El metodo devuelve no solo el arreglo ordenado, sino tambien cuantas comparaciones e intercambios hizo. Ese enfoque hace el algoritmo facil de seguir, muy apropiado para aprender estructuras y ordenacion, aunque no sea la mejor opcion para conjuntos grandes.
