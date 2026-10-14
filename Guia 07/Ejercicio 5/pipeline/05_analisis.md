# Plan técnico: ShellSort explicando los gaps

## Objetivo

Implementar en Java ShellSort para ordenar un arreglo de enteros de menor a mayor y hacer visible su progreso: imprimir el valor de `gap` y el estado completo del arreglo al terminar cada pasada correspondiente a ese gap.

## Diseño

- **Método sugerido:** `ordenarShell(int[] numeros)`.
- **Arreglo de demostración:** `{23, 12, 1, 8, 34, 54, 2, 3}`. La consigna y la imagen no fijan valores, por eso se adopta este ejemplo de ocho elementos, que permite mostrar varios gaps.
- **Secuencia de gaps:** empezar con `numeros.length / 2` y dividir el gap entre dos después de cada etapa, hasta llegar a `1`. Para ocho elementos, la secuencia es `4`, `2`, `1`.
- **Salida:** imprimir el arreglo original y, después de completar cada pasada gap, imprimir ese gap y el arreglo completo con `Arrays.toString(...)`.
- **Mutación:** ordenar el arreglo recibido en el mismo lugar; no se requieren arreglos auxiliares.

## Estrategia de ordenamiento

1. Calcular el gap inicial con la mitad de la longitud del arreglo.
2. Para cada gap, recorrer los elementos desde el índice `gap` hasta el final.
3. Guardar cada elemento actual como `clave` y comparar hacia atrás con los elementos separados por `gap` posiciones.
4. Mientras el índice anterior sea válido (`posicion >= gap`) y el elemento anterior sea mayor que la clave, desplazar ese elemento `gap` posiciones a la derecha y restar `gap` al índice.
5. Insertar la clave en el espacio encontrado.
6. Cuando todos los elementos fueron procesados para ese gap, imprimir una sola vez el valor del gap y el arreglo completo.
7. Reducir el gap a la mitad y repetir. La etapa con `gap == 1` es un Insertion Sort normal, aplicado a un arreglo que las etapas anteriores ya acercaron a su orden final.

El ciclo interno debe verificar `posicion >= gap` antes de acceder a `numeros[posicion - gap]`. Así nunca consulta un índice negativo. La condición de valor detiene los desplazamientos al encontrar un elemento menor o igual que la clave.

## Traza esperada

Con el arreglo propuesto, la secuencia de etapas debe ser:

| Gap | Estado al terminar la pasada completa del gap |
|---:|---|
| 4 | `[23, 12, 1, 3, 34, 54, 2, 8]` |
| 2 | `[1, 3, 2, 8, 23, 12, 34, 54]` |
| 1 | `[1, 2, 3, 8, 12, 23, 34, 54]` |

La impresión se realiza después de procesar todos los elementos para el gap actual, no después de cada comparación o desplazamiento individual. El último estado debe estar ordenado de menor a mayor.

## Comentarios requeridos en el código

- Explicar cómo se calcula el gap inicial y cómo se reduce en cada etapa.
- Describir que el gap separa los elementos comparados y permite mover valores a mayor distancia que un solo índice.
- Explicar por qué el límite del ciclo interno protege el acceso al elemento ubicado en `posicion - gap`.
- Indicar que el estado se imprime una vez, al completar la pasada correspondiente al gap.
- Aclarar que el gap final igual a `1` realiza la pasada de inserción definitiva sobre un arreglo casi ordenado.

## Complejidad y alcance

ShellSort ordena in-place y usa espacio auxiliar `O(1)`. Su complejidad depende de la secuencia de gaps; con la división por dos especificada, el peor caso es `O(n^2)`, aunque suele requerir menos desplazamientos que Insertion Sort directo cuando los elementos están lejos de su posición correcta. El objetivo de esta implementación es mostrar el efecto de los saltos, no añadir secuencias de gaps avanzadas ni optimizaciones adicionales.

## Criterios de aceptación

- El programa ordena el arreglo elegido ascendentemente mediante ShellSort.
- Los gaps se calculan con `longitud / 2` y se reducen dividiéndolos entre dos hasta terminar después de procesar `gap == 1`.
- Se imprime el valor del gap y el arreglo completo al finalizar cada pasada de ese gap.
- El acceso a `numeros[posicion - gap]` está protegido por la condición del límite.
- La traza para el ejemplo coincide con los estados de gaps `4`, `2` y `1` indicados.
- El arreglo queda ordenado al terminar la etapa final.