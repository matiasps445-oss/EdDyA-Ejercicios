# Plan técnico: comparación entre Bubble Sort y Selection Sort

## Objetivo

Implementar en Java dos métodos de ordenamiento ascendente, `bubbleSort(int[] array)` y `selectionSort(int[] array)`, y comparar su trabajo mediante contadores de comparaciones e intercambios. Ambos algoritmos recibirán copias independientes del mismo arreglo original y deberán producir el mismo resultado ordenado.

## Diseño

- **Arreglo original de demostración:** `{64, 34, 25, 12, 22, 11, 90}`.
- **Métodos:** `bubbleSort(int[] array)` y `selectionSort(int[] array)`.
- **Datos para mostrar:** arreglo ordenado, cantidad de comparaciones y cantidad de intercambios de cada algoritmo.
- **Resultado sugerido:** una clase sencilla `ResultadoOrdenamiento` que agrupe el arreglo ordenado y sus dos contadores. Cada método puede devolver esta estructura mientras ordena el arreglo que recibe.
- **Copias independientes:** conservar el arreglo original para mostrarlo y crear una copia con `clone()` para cada algoritmo. Así, ambos parten exactamente de los mismos valores y ninguno recibe el arreglo ya ordenado por el otro.
- **Suposición:** el arreglo de demostración no es `null`; la validación de entradas nulas no forma parte del objetivo principal del ejercicio.

## Estrategia de Bubble Sort

1. Realizar hasta `array.length - 1` pasadas.
2. En cada pasada, recorrer los pares adyacentes de la zona todavía no ordenada. Reducir el límite interno en una posición por pasada, porque el mayor valor de la zona activa queda al final.
3. Incrementar el contador de comparaciones cada vez que se evalúa un par adyacente.
4. Si `array[indice] > array[indice + 1]`, intercambiar los valores y aumentar el contador de intercambios.
5. Devolver el arreglo ordenado y ambos contadores.

Bubble Sort hace intercambios locales: un elemento grande puede avanzar varias posiciones durante una pasada, pero solo cruzando a su vecino inmediato cada vez.

## Estrategia de Selection Sort

1. Recorrer las posiciones desde el inicio hasta `array.length - 2`. La posición actual marca el inicio de la zona no ordenada.
2. Suponer que el menor elemento está en la posición actual y explorar el resto de la zona no ordenada para localizar el índice del menor real.
3. Incrementar el contador de comparaciones por cada candidato comparado con el menor actual.
4. Al terminar la búsqueda, intercambiar el menor encontrado con el elemento de la posición actual si sus índices son distintos. Aumentar el contador solo cuando ocurre ese intercambio real.
5. Devolver el arreglo ordenado y ambos contadores.

Selection Sort hace una búsqueda global del mínimo en la zona pendiente y, como máximo, un intercambio real por pasada. No debe incrementar el contador de intercambios cuando el mínimo ya está en la posición correcta.

## Definición consistente de las operaciones

- **Comparación:** evaluación de los valores de los elementos para decidir su orden. En Bubble Sort se cuenta una comparación por par adyacente evaluado. En Selection Sort se cuenta una comparación por candidato que se evalúa contra el menor encontrado hasta ese momento.
- **Intercambio:** cambio efectivo de posición entre dos elementos distintos. El contador aumenta dentro del bloque que efectivamente cambia los valores. En Selection Sort, no se cuenta un intercambio consigo mismo cuando el menor ya ocupa la posición actual.
- Los contadores no incluyen operaciones de control de los ciclos, asignaciones de índices ni las comparaciones del contador contra límites del `for`; de esta manera ambos reportan las operaciones principales del algoritmo.

## Resultados esperados para el arreglo indicado

Ambos métodos deben terminar con:

```text
[11, 12, 22, 25, 34, 64, 90]
```

Con las definiciones de conteo anteriores y los ciclos estándar con límite decreciente:

| Algoritmo | Comparaciones de elementos | Intercambios reales | Total de operaciones contadas |
|---|---:|---:|---:|
| Bubble Sort | 21 | 14 | 35 |
| Selection Sort | 21 | 4 | 25 |

Ambos realizan 21 comparaciones para un arreglo de siete elementos porque cada uno evalúa una vez cada posición candidata dentro de sus pasadas. Los contadores de comparaciones, por lo tanto, coinciden en este caso; la diferencia está en los intercambios y en el total de operaciones contadas. No se debe alterar artificialmente la definición de comparación para forzar contadores distintos.

## Salida esperada

El programa debe mostrar el mismo arreglo de partida y luego una sección por algoritmo, con su resultado y sus contadores. La redacción exacta puede variar, pero debe permitir verificar, por ejemplo:

```text
Bubble Sort: [11, 12, 22, 25, 34, 64, 90]
Comparaciones: 21
Intercambios: 14

Selection Sort: [11, 12, 22, 25, 34, 64, 90]
Comparaciones: 21
Intercambios: 4
```

## Comentarios requeridos en el código

- Señalar el punto exacto donde Bubble Sort cuenta y realiza cada comparación de vecinos.
- Explicar el intercambio local de Bubble Sort y la reducción del límite de su zona activa.
- Señalar el punto exacto donde Selection Sort compara candidatos y actualiza el índice del menor.
- Explicar que Selection Sort intercambia una sola vez al final de cada búsqueda, y solo si el menor está en otra posición.
- Indicar dónde se incrementa cada contador y qué operación representa.

## Criterios de aceptación

- Los dos métodos se llaman `bubbleSort(int[] array)` y `selectionSort(int[] array)`.
- Ambos reciben copias exactas del mismo arreglo original.
- Los dos producen `[11, 12, 22, 25, 34, 64, 90]`.
- Cada método informa comparaciones de elementos e intercambios reales conforme a las definiciones comunes.
- Para el arreglo especificado, Bubble Sort informa 21 comparaciones y 14 intercambios; Selection Sort informa 21 comparaciones y 4 intercambios.
- La salida demuestra que los arreglos finales coinciden y que los totales de operaciones son diferentes.
- Los comentarios identifican con precisión las comparaciones, los intercambios y el incremento de los contadores.