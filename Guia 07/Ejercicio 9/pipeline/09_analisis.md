# Plan técnico: comparación de algoritmos simples

## Objetivo

Implementar en Java Bubble Sort, Selection Sort e Insertion Sort, aplicar cada uno a una copia independiente del mismo arreglo desordenado y mostrar para cada método el arreglo ordenado, las comparaciones entre elementos y los intercambios o desplazamientos reales.

## Diseño

- **Arreglo original de demostración:** `{64, 34, 25, 12, 22, 11, 90}`. La consigna y la imagen no fijan valores; se elige esta entrada para producir distintos contadores en los tres métodos.
- **Métodos sugeridos:** `bubbleSort(int[] array)`, `selectionSort(int[] array)` e `insertionSort(int[] array)`.
- **Resultados:** usar una estructura sencilla `ResultadoOrdenamiento` con el arreglo ordenado, comparaciones e intercambios/desplazamientos.
- **Copias independientes:** conservar `arregloOriginal` y crear una copia con `clone()` para cada método dentro de `main`. No pasar a un algoritmo el arreglo ya modificado por otro.
- **Salida:** imprimir el arreglo original y después el nombre del algoritmo, su arreglo resultante, comparaciones y movimientos.

## Definición común de operaciones

Para que los contadores sean comparables, contar solo comparaciones entre valores del arreglo, no las evaluaciones de control de los ciclos ni comparaciones de índices con límites:

- **Bubble Sort:** sumar una comparación por cada par adyacente evaluado por la condición que decide si se intercambia. Sumar un intercambio solo cuando se cambian realmente dos elementos.
- **Selection Sort:** sumar una comparación por cada candidato comparado con el menor actual. Sumar un intercambio solo al final de la pasada y únicamente si el índice del menor es distinto de la posición actual.
- **Insertion Sort:** sumar una comparación por cada evaluación de `array[posicion] > clave` mientras `posicion >= 0`, incluyendo la evaluación que resulta falsa y detiene el desplazamiento. No contar la comprobación del límite ni la escritura final de `clave`. Para contar exactamente las evaluaciones de valores, conviene que el `while` compruebe primero el límite y que el cuerpo incremente el contador antes de evaluar los valores, deteniéndose cuando el valor no sea mayor. Sumar un desplazamiento solo al copiar un elemento una posición a la derecha.

El contador de movimientos tiene significados relacionados, pero no idénticos: Bubble y Selection cuentan intercambios; Insertion cuenta desplazamientos. La salida y los comentarios deben nombrarlo correctamente para cada método.

## Estrategias por algoritmo

### Bubble Sort

1. Ejecutar hasta `n - 1` pasadas, acortando el rango interno una posición por pasada.
2. Comparar elementos adyacentes; si el de la izquierda es mayor, intercambiarlos.
3. Contar cada comparación entre valores y cada intercambio efectuado.

### Selection Sort

1. Para cada posición de la zona no ordenada, iniciar el índice del menor con la posición actual.
2. Recorrer el resto de la zona y comparar cada candidato con el menor encontrado.
3. Al finalizar la búsqueda, intercambiar el menor con la posición actual si hace falta.
4. Contar cada comparación entre valores y solo los intercambios reales.

### Insertion Sort

1. Considerar ordenado inicialmente el primer elemento y recorrer desde el índice `1`.
2. Guardar el elemento actual como `clave`; comparar hacia atrás los valores previos con ella.
3. Mientras haya un elemento previo y sea mayor que la clave, desplazarlo una posición a la derecha y contar ese movimiento.
4. Cuando el límite se alcance o aparezca un elemento menor o igual, insertar la clave en el hueco.

## Contadores esperados para el arreglo de demostración

Con las definiciones anteriores y los bucles estándar con límite decreciente:

| Algoritmo | Comparaciones entre elementos | Intercambios o desplazamientos | Suma de ambos contadores |
|---|---:|---:|---:|
| Bubble Sort | 21 | 14 intercambios | 35 |
| Selection Sort | 21 | 4 intercambios | 25 |
| Insertion Sort | 16 | 14 desplazamientos | 30 |

Los tres métodos deben terminar con `[11, 12, 22, 25, 34, 64, 90]`. Bubble y Selection comparten 21 comparaciones para esta entrada; eso no invalida la comparación, porque la suma de operaciones y la cantidad de movimientos sí difieren. Insertion hace 16 comparaciones de elementos con la convención indicada: algunas claves requieren varias comparaciones exitosas y una comparación final que detiene el desplazamiento.

## Comentarios requeridos en el código

- Indicar dónde se crean las tres copias independientes y por qué se parte del mismo arreglo original.
- En cada algoritmo, señalar dónde se incrementa el contador de comparaciones entre elementos.
- Señalar el bloque donde ocurre el intercambio real en Bubble y Selection.
- Señalar el desplazamiento a la derecha y la inserción final de la clave en Insertion Sort, aclarando cuál de esas acciones cuenta.
- Explicar que los contadores miden operaciones distintas y no deben confundirse con las condiciones de control de los ciclos.

## Criterios de aceptación

- Se implementan y ejecutan los tres métodos sobre copias exactas e independientes del arreglo original.
- Los tres producen `[11, 12, 22, 25, 34, 64, 90]`.
- Cada resultado muestra comparaciones y el tipo correcto de movimiento.
- Para la entrada indicada, Bubble informa `21` comparaciones y `14` intercambios; Selection, `21` comparaciones y `4` intercambios; Insertion, `16` comparaciones y `14` desplazamientos.
- Los comentarios distinguen comparaciones de elementos, control de ciclos, intercambios y desplazamientos.
- La salida permite comparar las estrategias y las operaciones totales sin afirmar erróneamente que cada contador individual debe diferir entre los tres métodos.