# Explicación: cola circular

## Qué problema resuelve

Una cola lineal puede dejar posiciones libres al principio del arreglo después de retirar elementos, pero no volver a utilizarlas si su índice `rear` ya alcanzó el final. La cola circular conserva el arreglo fijo y trata su último índice y el índice 0 como posiciones consecutivas. Así reutiliza los huecos sin desplazar los elementos que siguen en la cola.

## Cómo funciona el módulo `%`

En una cola de capacidad 3, los índices válidos son `0`, `1` y `2`. Al avanzar `rear` o `front`, el programa suma uno y calcula el resto respecto a la capacidad:

- `rear = (rear + 1) % elementos.length`
- `front = (front + 1) % elementos.length`

Cuando el índice vale 0 o 1, el siguiente queda dentro del arreglo. Cuando vale 2, la suma produce 3 y `3 % 3` da 0. Por eso el índice vuelve al comienzo automáticamente, sin una condición especial para detectar el final. El operador módulo realiza este cálculo en tiempo constante, $O(1)$; no mueve ni copia los elementos del arreglo.

En `enqueue`, `rear` avanza y señala dónde guardar el nuevo valor. En `dequeue`, se lee y libera la posición señalada por `front`, y luego `front` avanza con la misma fórmula circular. La posición liberada se pone en `0` para que el arreglo físico muestre el hueco; el cero es solo una marca visual. El orden de la cola lo determinan los índices y `size`, no el valor de las celdas vacías.

## Límites: vacía y llena

El campo `size` representa cuántos elementos están activos en la cola:

- `isEmpty()` devuelve `size == 0`.
- `isFull()` devuelve `size == elementos.length`.

En una estructura circular, `front` y `rear` pueden coincidir tanto cuando queda un único elemento como en situaciones límite tras recorrer el arreglo. Por eso compararlos no basta para distinguir de forma general una cola vacía de una llena. El contador `size` resuelve la diferencia directamente y hace que las condiciones sean simples y constantes: no es necesario recorrer el arreglo.

Antes de insertar, `enqueue` comprueba `isFull()`. Si es cierto, lanza `IllegalStateException` y no cambia la cola; si no, avanza `rear`, escribe el valor e incrementa `size`. Antes de retirar o consultar el frente, `dequeue` y `front()` comprueban `isEmpty()` y también lanzan `IllegalStateException` cuando no hay elementos. Una inserción válida incrementa `size` en uno, y una extracción válida lo decrementa en uno.

## Recorrido de la prueba de `main`

La capacidad es 3, por lo que el arreglo inicia como `[0, 0, 0]`, con `front = 0`, `rear = -1` y `size = 0`.

| Paso | Operación | Arreglo | `front` | `rear` | `size` |
|---|---|---|---:|---:|---:|
| Inicio | - | `[0, 0, 0]` | 0 | -1 | 0 |
| 1 | Encolar `10` | `[10, 0, 0]` | 0 | 0 | 1 |
| 2 | Encolar `20` | `[10, 20, 0]` | 0 | 1 | 2 |
| 3 | Encolar `30` | `[10, 20, 30]` | 0 | 2 | 3 |
| 4 | Desencolar `10` | `[0, 20, 30]` | 1 | 2 | 2 |
| 5 | Encolar `40` | `[40, 20, 30]` | 1 | 0 | 3 |

Tras insertar `30`, `rear` está en el último índice, `2`, y la cola está llena. Al retirar `10`, se libera el índice 0; `front` avanza de 0 a 1 y `size` baja a 2. Al insertar `40`, el cálculo es `rear = (2 + 1) % 3 = 0`. El valor nuevo ocupa el hueco del índice 0 y `rear` vuelve allí, mientras que `front` permanece en 1.

Aunque el arreglo final se imprime como `[40, 20, 30]`, los elementos no se leen de izquierda a derecha desde el índice 0. El recorrido FIFO comienza en `front`: índice 1 contiene `20`, índice 2 contiene `30` y, al dar la vuelta, índice 0 contiene `40`. Por eso `front()` devuelve `20` y se conserva el orden de llegada. Con la nueva inserción, `size` vuelve a 3 y `isFull()` devuelve `true`.

## Reflexión sobre el prompt

El prompt inicial detalló explícitamente la fórmula matemática con el operador módulo `%` para actualizar ambos índices, además de establecer las condiciones de vacío y lleno mediante `size`. También describió la prueba de retirar un elemento y volver a insertar otro en el índice liberado. Gracias a esas indicaciones precisas, no fue necesario hacer ajustes posteriores al prompt para lograr el comportamiento circular esperado.