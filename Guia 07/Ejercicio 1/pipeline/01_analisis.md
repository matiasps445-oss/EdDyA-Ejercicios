# Plan técnico: ordenamiento burbuja paso a paso

## Objetivo

Definir una implementación Java que ordene un arreglo de números enteros de menor a mayor mediante Bubble Sort y muestre por consola el arreglo original y el estado completo del arreglo después de cada pasada externa.

## Diseño

- **Entrada de demostración:** `int[] numeros = {64, 34, 25, 12, 22, 11, 90}`.
- **Orden:** ascendente.
- **Método sugerido:** `ordenarBurbuja(int[] numeros)`, con el ordenamiento realizado sobre el arreglo recibido.
- **Presentación:** usar `Arrays.toString(...)` para imprimir el arreglo original antes de modificarlo y el estado después de cada pasada.
- **Entrada por consola:** no es necesaria para este ejercicio; `main` puede demostrar el algoritmo con el arreglo especificado.

## Estrategia del algoritmo

1. Mostrar el arreglo antes de comenzar el ordenamiento.
2. Usar un ciclo externo para controlar las pasadas. Para un arreglo de longitud `n`, realizar hasta `n - 1` pasadas.
3. En cada pasada, recorrer con el ciclo interno los pares adyacentes del segmento todavía no ordenado.
4. Comparar `numeros[indice]` con `numeros[indice + 1]`. Si el elemento izquierdo es mayor, intercambiarlos mediante una variable auxiliar.
5. Al completar el ciclo interno, imprimir el arreglo con el número de pasada. La impresión debe ocurrir una vez por pasada, después de todos sus intercambios.

## Límites de los ciclos

- El ciclo externo puede expresarse como `pasada < numeros.length - 1`.
- El ciclo interno debe usar la condición `indice < numeros.length - 1 - pasada`.
- El término `- pasada` reduce el tramo examinado en cada iteración externa: al terminar una pasada, el mayor elemento del tramo activo quedó al final y ya está en su posición definitiva. Por eso no hace falta volver a compararlo en las pasadas siguientes.
- La resta de `1` permite acceder con seguridad a `numeros[indice + 1]` sin superar el último índice.
- No se agregará una salida anticipada por falta de intercambios; así se conservan las pasadas previstas y se puede observar cada iteración externa.

## Traza esperada

Para el arreglo de demostración, las impresiones después de cada pasada deben ser:

| Momento | Estado esperado |
|---|---|
| Original | `[64, 34, 25, 12, 22, 11, 90]` |
| Pasada 1 | `[34, 25, 12, 22, 11, 64, 90]` |
| Pasada 2 | `[25, 12, 22, 11, 34, 64, 90]` |
| Pasada 3 | `[12, 22, 11, 25, 34, 64, 90]` |
| Pasada 4 | `[12, 11, 22, 25, 34, 64, 90]` |
| Pasada 5 | `[11, 12, 22, 25, 34, 64, 90]` |
| Pasada 6 | `[11, 12, 22, 25, 34, 64, 90]` |

La última pasada no cambia el arreglo; se incluye porque el algoritmo realiza `n - 1` pasadas sin optimización de salida anticipada.

## Comentarios requeridos en el código

- En la comparación, indicar que se revisa un par de elementos adyacentes.
- En el intercambio, explicar que se ejecuta solo cuando el par está en orden incorrecto para orden ascendente.
- Junto al límite del ciclo interno, explicar que el mayor elemento de cada pasada queda fijo al final del tramo activo y que por eso el rango se reduce.
- Antes de imprimir el estado, indicar que se imprime una vez al terminar cada pasada externa.

## Complejidad esperada

- **Tiempo:** `O(n^2)` en el caso general, por las comparaciones repetidas entre pares adyacentes en múltiples pasadas.
- **Espacio auxiliar:** `O(1)` si se ordena el arreglo recibido y solo se usa una variable temporal para intercambiar valores.

## Criterios de aceptación

- El programa muestra primero el arreglo original.
- Compara elementos adyacentes e intercambia solo cuando el izquierdo es mayor que el derecho.
- El ciclo interno se acorta según la cantidad de pasadas ya completadas y no accede fuera del arreglo.
- Se imprime el arreglo después de cada pasada externa, no después de cada comparación o intercambio.
- La salida coincide con la traza indicada y termina con los valores ordenados de menor a mayor.
- Los comentarios explican la comparación, el intercambio y la reducción del límite interno.