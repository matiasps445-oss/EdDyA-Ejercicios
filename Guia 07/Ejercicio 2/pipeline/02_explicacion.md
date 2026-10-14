# Explicación de `ComparacionOrdenamientos`

El programa aplica dos métodos distintos al mismo arreglo inicial, `{64, 34, 25, 12, 22, 11, 90}`, para ordenarlo de menor a mayor. En `main`, conserva el arreglo original y llama a cada algoritmo con su propia copia (`arregloOriginal.clone()`). Así, el segundo método no recibe por accidente el arreglo que el primero ya ordenó.

Cada método devuelve un `ResultadoOrdenamiento`, que agrupa el arreglo resultante y sus contadores. `imprimirResultado` presenta esos datos sin repetir la lógica de impresión en `main`.

## Bubble Sort: comparar vecinos

`bubbleSort` realiza pasadas sobre la zona que todavía no está ordenada:

1. El `for` externo controla las pasadas.
2. El `for` interno visita pares contiguos: `array[indice]` y `array[indice + 1]`.
3. Antes de evaluar si están en el orden incorrecto, suma uno a `comparaciones`.
4. Si el elemento izquierdo es mayor, guarda su valor en `temporal`, intercambia ambos elementos y suma uno a `intercambios`.
5. Al terminar una pasada, el mayor elemento de la zona activa llegó a su extremo derecho. La siguiente pasada reduce el límite interno con `array.length - 1 - pasada` y deja fuera esa posición definitiva.

En el ejemplo, las pasadas dejan estos estados:

| Pasada | Arreglo al terminar | Comparaciones de la pasada | Intercambios de la pasada |
|---|---|---:|---:|
| 1 | `[34, 25, 12, 22, 11, 64, 90]` | 6 | 5 |
| 2 | `[25, 12, 22, 11, 34, 64, 90]` | 5 | 4 |
| 3 | `[12, 22, 11, 25, 34, 64, 90]` | 4 | 3 |
| 4 | `[12, 11, 22, 25, 34, 64, 90]` | 3 | 1 |
| 5 | `[11, 12, 22, 25, 34, 64, 90]` | 2 | 1 |
| 6 | `[11, 12, 22, 25, 34, 64, 90]` | 1 | 0 |

Por ejemplo, en la primera pasada, `64` se compara sucesivamente con sus vecinos y se mueve a la derecha cada vez que encuentra uno menor. `90` ya es mayor que `64`, por lo que no se intercambian.

## Selection Sort: buscar el menor

`selectionSort` divide conceptualmente el arreglo en una parte ordenada a la izquierda y una zona pendiente a la derecha:

1. `indiceActual` señala la primera posición de la zona pendiente.
2. `indiceMenor` comienza apuntando a esa posición, como candidato al menor.
3. El `for` interno examina todos los elementos siguientes. Antes de comparar cada candidato con `array[indiceMenor]`, incrementa `comparaciones`. Si encuentra uno menor, actualiza `indiceMenor`.
4. Cuando termina la búsqueda, si `indiceMenor` no es `indiceActual`, intercambia el menor encontrado con el elemento al comienzo de la zona pendiente y cuenta ese intercambio real.
5. La posición elegida queda ordenada, y la siguiente iteración inicia la búsqueda una posición más a la derecha.

La traza de Selection Sort es:

| Posición fijada | Menor encontrado | Arreglo al terminar | Comparaciones de la búsqueda | Intercambios |
|---|---:|---|---:|---:|
| 0 | 11 | `[11, 34, 25, 12, 22, 64, 90]` | 6 | 1 |
| 1 | 12 | `[11, 12, 25, 34, 22, 64, 90]` | 5 | 1 |
| 2 | 22 | `[11, 12, 22, 34, 25, 64, 90]` | 4 | 1 |
| 3 | 25 | `[11, 12, 22, 25, 34, 64, 90]` | 3 | 1 |
| 4 | 34 | `[11, 12, 22, 25, 34, 64, 90]` | 2 | 0 |
| 5 | 64 | `[11, 12, 22, 25, 34, 64, 90]` | 1 | 0 |

En las dos últimas búsquedas, el menor ya está en la posición actual. La condición `indiceMenor != indiceActual` evita hacer y contar un intercambio de un elemento consigo mismo.

## Diferencia fundamental

- **Bubble Sort** compara elementos vecinos. Si están invertidos, los intercambia en el acto. Por ello un elemento puede moverse varias posiciones en una pasada mediante una serie de intercambios pequeños.
- **Selection Sort** examina toda la zona no ordenada buscando su menor elemento. No lo mueve durante la búsqueda; al final lo coloca al principio de esa zona con, como máximo, un intercambio real por pasada.

Los dos algoritmos llegan al mismo arreglo final, pero lo construyen de forma distinta: Bubble desplaza valores grandes hacia la derecha, mientras Selection fija valores pequeños desde la izquierda.

## Cómo interpretar los contadores

La salida para este arreglo es:

| Algoritmo | Comparaciones | Intercambios | Suma de ambos contadores |
|---|---:|---:|---:|
| Bubble Sort | 21 | 14 | 35 |
| Selection Sort | 21 | 4 | 25 |

Ambos hacen 21 comparaciones: en cada algoritmo, las pasadas examinan respectivamente 6, 5, 4, 3, 2 y 1 candidatos. La diferencia en este ejemplo está en los intercambios: Bubble necesita 14 cambios locales; Selection solo 4 cambios efectivos, uno por cada mínimo que estaba en otra posición.

Esto muestra que Selection Sort puede reducir los intercambios, pero no que siempre tenga menos comparaciones ni que sea más rápido en todo contexto. Ambos métodos tienen tiempo `O(n^2)` con estos ciclos. Los intercambios adicionales de Bubble pueden importar cuando escribir o mover elementos es costoso; para arreglos pequeños, la diferencia puede ser insignificante.

## Errores comunes al contar

- **Contar la condición de los `for` como comparación de datos:** expresiones como `indice < array.length - 1 - pasada` controlan el recorrido, pero el contador del ejercicio mide comparaciones entre elementos. Por eso `comparaciones++` está junto a la comparación de valores, no en la cabecera del ciclo.
- **Contar cada vuelta como intercambio:** una vuelta del ciclo interno no necesariamente modifica el arreglo. En Bubble Sort, `intercambios++` solo se ejecuta si el `if` encuentra un par invertido.
- **Contar cada candidato de Selection como intercambio:** examinar o actualizar `indiceMenor` no cambia las posiciones del arreglo. Selection incrementa el contador de intercambios una sola vez, después de buscar, y solo si los índices son distintos.
- **Incrementar comparaciones solo cuando hay intercambio:** toda comparación de valores cuenta, tanto si conduce a cambiar posiciones como si no.
- **Usar el mismo arreglo para ambas llamadas:** el segundo algoritmo comenzaría con datos ya ordenados y la comparación dejaría de ser justa. Los dos `clone()` de `main` evitan ese problema.

En conclusión, para entender los resultados hay que mirar los dos contadores por separado: las comparaciones indican cuánto se inspecciona y los intercambios cuántos cambios reales se hacen. En la entrada de ejemplo ambos inspeccionan la misma cantidad de pares/candidatos, pero Selection mueve los elementos con menos intercambios.