# Plan técnico: Quicksort con el primer elemento como pivote

## Objetivo

Implementar en Java Quicksort recursivo, usando siempre el primer elemento de cada partición como pivote. En cada llamada no trivial, mostrar el pivote y las dos particiones resultantes; al final, mostrar el arreglo completamente ordenado.

## Diseño

- **Método público sugerido:** `ordenarQuickSort(int[] numeros)`, que devuelve un arreglo ordenado.
- **Método recursivo auxiliar:** recibe una partición y ordena ese subarreglo de manera independiente.
- **Arreglo de demostración:** `{5, 3, 8, 4, 2, 7, 1, 10}`. La consigna y la imagen no fijan valores; se adopta este arreglo para que la salida muestre varios niveles de recursión.
- **Particiones:** convertir los elementos restantes en dos subarreglos: menores que el pivote a la izquierda y mayores o iguales a la derecha. El segundo grupo incluye valores iguales para que no se pierdan duplicados. En el arreglo de demostración no hay valores repetidos, por lo que todos los elementos de la derecha son estrictamente mayores.
- **Salida:** mostrar el arreglo de entrada antes de ordenar; en cada llamada no trivial, imprimir el pivote elegido y el contenido de ambas particiones después de distribuir los elementos; al final, imprimir el arreglo ordenado.

## Casos base

Si la partición tiene cero o un elemento, ya está ordenada. Devolverla directamente sin elegir un pivote ni generar subllamadas evita trabajo innecesario y detiene la recursión.

## Estrategia recursiva

1. Recibir una partición.
2. Si cumple el caso base, devolverla.
3. Elegir `numeros[0]` como pivote, siempre el primer elemento de esta partición.
4. Recorrer los elementos desde el índice `1` y distribuir cada uno en una de dos colecciones:
   - izquierda si `numero < pivote`;
   - derecha si `numero >= pivote`, para conservar duplicados.
5. Imprimir el pivote y las dos particiones ya formadas.
6. Llamar recursivamente al método para ordenar la partición izquierda y la derecha.
7. Combinar el resultado ordenado izquierdo, el pivote y el resultado ordenado derecho, en ese orden, y devolver el nuevo arreglo.

Esta versión usa subarreglos explícitos en lugar de particionar in-place. Esa decisión facilita que las particiones se puedan imprimir y seguir al aprender divide y vencerás.

## Seguimiento esperado

Las particiones del arreglo de demostración, antes de las llamadas recursivas, serán:

```text
Pivote: 5 | Izquierda: [3, 4, 2, 1] | Derecha: [8, 7, 10]
Pivote: 3 | Izquierda: [2, 1] | Derecha: [4]
Pivote: 2 | Izquierda: [1] | Derecha: []
Pivote: 8 | Izquierda: [7] | Derecha: [10]
```

Las particiones de longitud cero o uno alcanzan el caso base y no eligen otro pivote. La combinación de los resultados debe producir:

```text
[1, 2, 3, 4, 5, 7, 8, 10]
```

El orden de las líneas de partición dependerá de si se procesa la recursión izquierda antes que la derecha, pero cada llamada debe mostrar las particiones correctas para su propio pivote.

## Comentarios requeridos en el código

- Explicar que el primer elemento de la partición se selecciona como pivote.
- Describir cómo se divide cada elemento restante entre la partición izquierda y la derecha.
- Indicar que se imprimen las particiones después de formarlas y antes de ordenar recursivamente.
- Explicar las dos llamadas recursivas y la combinación de ambas partes con el pivote.
- Aclarar los casos base y por qué detienen la recursión.

## Complejidad y consideración del pivote

Con particiones razonablemente equilibradas, Quicksort suele tener tiempo `O(n log n)`. Si siempre se elige el primer elemento y la entrada ya está ordenada o muy desbalanceada, cada llamada puede dejar un subarreglo vacío y otro de tamaño `n - 1`, lo que lleva al peor caso `O(n^2)` y una pila recursiva de profundidad `O(n)`. La implementación sigue la regla didáctica de pivote fijada por el ejercicio; no debe cambiarlo por un pivote aleatorio o mediano.

## Criterios de aceptación

- El primer elemento de cada partición es el pivote.
- Cada elemento restante aparece exactamente en una de las dos particiones; los iguales al pivote se conservan en la derecha.
- En cada llamada no trivial se imprimen el pivote, la partición izquierda y la derecha.
- La recursión ordena ambas particiones y las combina en el orden izquierda, pivote, derecha.
- Las particiones de cero o un elemento detienen la recursión.
- El arreglo de demostración termina como `[1, 2, 3, 4, 5, 7, 8, 10]`.