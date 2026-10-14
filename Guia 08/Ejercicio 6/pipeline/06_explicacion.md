# Explicación de `ColaEnteros`

`ColaEnteros` implementa una cola FIFO (**First In, First Out**) con un arreglo de capacidad fija. El primer valor que se encola será el primero en salir. Por eso la clase usa dos índices: `rear` indica dónde se agrega el siguiente valor al final, mientras que `front` señala el valor más antiguo pendiente de retirar.

## Por qué se necesitan dos índices

Una pila solo agrega y retira desde el mismo extremo, por lo que un índice de tope puede bastar. Una cola agrega por un extremo y retira por el otro: necesita recordar ambos extremos de la parte activa del arreglo.

- `rear` se mueve cuando se agrega con `enqueue`.
- `front` se mueve cuando se retira con `dequeue`.
- `front()` consulta el valor indicado por `front` sin mover ninguno de los dos índices.

El estado inicial es `front = 0` y `rear = -1`. Como `front > rear`, `isEmpty()` devuelve `true`. Al agregar el primer valor, `rear` pasa a `0` y ambos índices señalan la única posición ocupada.

## Simulación en el arreglo

La prueba crea una cola de capacidad `3`. En un arreglo `int[]`, las celdas comienzan físicamente en cero, pero el rango lógico vacío lo determinan los índices:

| Acción | Arreglo `[0, 1, 2]` | `front` | `rear` | Cola lógica |
|---|---|---:|---:|---|
| Inicio | `[0, 0, 0]` | 0 | -1 | `[]` |
| `enqueue(10)` | `[10, 0, 0]` | 0 | 0 | `[10]` |
| `enqueue(20)` | `[10, 20, 0]` | 0 | 1 | `[10, 20]` |
| `enqueue(30)` | `[10, 20, 30]` | 0 | 2 | `[10, 20, 30]` |
| `front()` | sin cambios | 0 | 2 | devuelve `10` |
| `dequeue()` | la celda 0 puede seguir mostrando `10` | 1 | 2 | devuelve `10`; quedan `[20, 30]` |

El valor retirado puede permanecer físicamente en el índice `0`, pero deja de pertenecer a la cola lógica porque `front` ahora vale `1`. El tamaño se obtiene con `rear - front + 1`: `2 - 1 + 1 = 2`. Por eso la salida muestra `20` como nuevo frente y tamaño `2`.

## Cómo se protegen los límites

- **Desencolar desde una cola vacía:** `dequeue()` llama primero a `isEmpty()`. Si `front > rear`, lanza `IllegalStateException` antes de evaluar `elementos[front]`; evita leer fuera del arreglo.
- **Consultar el frente vacío:** `front()` realiza la misma comprobación antes de devolver `elementos[front]`.
- **Encolar en una cola llena:** `enqueue()` comprueba `isFull()` antes de incrementar `rear` o escribir. Si `rear == elementos.length - 1`, lanza `IllegalStateException`; así evita escribir en el índice siguiente, que sería inválido.
- **Retirar el último elemento:** después de aumentar `front`, si `front > rear`, la cola quedó vacía. El código restablece `front = 0` y `rear = -1`, de modo que puede volver a usarse desde el inicio del arreglo.

## Una limitación de la cola lineal

Esta implementación no es circular. Si se retiran elementos mientras todavía quedan otros y `rear` llega al final del arreglo, `isFull()` será verdadero aunque queden huecos antes de `front`. El plan sigue intencionalmente la cola lineal sencilla; una cola circular podría reutilizar esos huecos, pero requiere otra lógica de índices.

## Reflexión sobre el prompt

El prompt inicial explicó perfectamente el rol de los índices `front` y `rear` y sus desplazamientos: `rear` avanza al agregar y `front` avanza al retirar. Por eso no fue necesario realizar ajustes al prompt para obtener un análisis correcto del comportamiento FIFO esperado.