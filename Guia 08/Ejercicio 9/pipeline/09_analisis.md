# Plan técnico: desperdicio de espacio en una cola simple

## Objetivo

Implementar una cola **lineal** de enteros basada en un arreglo de tamaño fijo y demostrar que, después de desencolar algunos elementos, los espacios libres del principio no se pueden reutilizar aunque el arreglo todavía contenga lugares vacíos.

## Diseño de la cola

- **Clase sugerida:** `ColaLinealEnteros`.
- **Arreglo interno:** `int[] elementos`, de capacidad fija `5`.
- **Índices:** `front` señala el siguiente elemento por retirar; `rear` señala la última posición ocupada.
- **Estado inicial:** `front = 0`, `rear = -1`.
- **Vacía:** `front > rear`.
- **Llena para esta cola lineal:** `rear == elementos.length - 1`. Esta condición significa que no quedan posiciones al final para encolar; no necesariamente que todas las celdas contengan elementos activos.
- Mantener los índices como campos privados.

## Operaciones

- **`enqueue(int valor)`:** antes de escribir, comprobar si `rear == elementos.length - 1`. Si se cumple, rechazar la operación con `IllegalStateException` y no modificar índices ni arreglo. Si hay espacio al final, incrementar `rear` y guardar el valor en `elementos[rear]`.
- **`dequeue()`:** comprobar que la cola no esté vacía. Guardar y devolver `elementos[front]`, asignar `0` a esa celda para que el hueco sea visible en la demostración y luego avanzar `front`. No mover los elementos que quedan.
- **Mostrar estado:** después de cada operación, imprimir el arreglo, `front`, `rear` y el tamaño lógico. Usar `rear - front + 1` mientras haya elementos; si está vacía, mostrar tamaño `0`.

No se debe desplazar el contenido hacia el inicio ni usar índices circulares: el objetivo es observar precisamente la limitación de la cola lineal. El cero en una posición libre funciona como marcador visual en esta prueba; el significado lógico depende de `front` y `rear`, no del contenido aislado de una celda.

## Secuencia de demostración

1. Mostrar el arreglo inicial de cinco posiciones.
2. Encolar `10`, `20`, `30`, `40` y `50`, imprimiendo el estado después de cada inserción.
3. Desencolar dos veces, imprimiendo el estado después de cada retiro.
4. Intentar encolar `60`. Capturar la excepción para que la demostración pueda continuar, informar que la cola lineal rechaza la inserción y volver a imprimir el estado sin cambios.

## Estados clave esperados

| Momento | Arreglo | `front` | `rear` | Tamaño lógico |
|---|---|---:|---:|---:|
| Después de encolar `10..50` | `[10, 20, 30, 40, 50]` | 0 | 4 | 5 |
| Después de retirar `10` | `[0, 20, 30, 40, 50]` | 1 | 4 | 4 |
| Después de retirar `20` | `[0, 0, 30, 40, 50]` | 2 | 4 | 3 |
| Después de intentar encolar `60` | `[0, 0, 30, 40, 50]` | 2 | 4 | 3 |

En el último estado hay dos celdas vacías en los índices `0` y `1`, pero `rear` ya está en el último índice (`4`). El `enqueue` falla según la condición de la cola lineal, dejando visible el desperdicio de espacio. La cantidad lógica de elementos es solo `3`; la cola no puede reutilizar los huecos iniciales sin desplazar elementos o rediseñarse como circular.

## Comentarios requeridos en el código

- Explicar que `front` avanza al retirar y `rear` solo avanza al agregar.
- Indicar que `dequeue` marca la celda liberada sin mover los elementos restantes.
- Señalar que la condición de lleno lineal depende de que `rear` llegue al último índice.
- Aclarar que los espacios anteriores a `front` son vacíos físicamente, pero no son reutilizables en esta implementación.
- Explicar por qué se captura el error de `enqueue` en la demostración y se imprime el estado después del intento fallido.

## Criterios de aceptación

- Se usan un arreglo de cinco posiciones y una cola lineal, no circular.
- Se muestra el arreglo y sus índices después de cada `enqueue`, cada `dequeue` y el intento final.
- La secuencia encola `10`, `20`, `30`, `40`, `50`, retira `10` y `20`, e intenta agregar `60`.
- El último intento es rechazado aunque las dos primeras celdas estén libres.
- La salida hace evidente que el problema proviene de `rear` alcanzando el final y no de que haya cinco elementos activos.