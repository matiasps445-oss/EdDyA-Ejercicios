# Explicación de `ColaTurnos`

`ColaTurnos` modela una fila de atención con una cola circular FIFO almacenada en `String[] nombres`. FIFO significa **First In, First Out**: entre las personas que esperan, se atiende primero a quien llegó primero. Para una política de atención por orden de llegada, este es el modelo justo y directo.

Una pila LIFO atendería primero a la persona que acaba de llegar. Si siguieran llegando personas nuevas, quienes llegaron antes podrían esperar indefinidamente. Otros sistemas reales pueden tener prioridades especiales, pero eso no es lo que representa este ejercicio: aquí todos deben conservar su orden de llegada.

## Qué representa cada índice

- `front` señala la posición de la próxima persona que se atenderá.
- `rear` señala la posición de la persona que se agregó más recientemente.
- `size` cuenta cuántas personas están esperando; permite distinguir con claridad una cola vacía de una llena.

Al comenzar, `front = 0`, `rear = -1` y `size = 0`. La cola está vacía. La capacidad del ejemplo es tres:

| Acción | Arreglo `[0, 1, 2]` | `front` | `rear` | `size` |
|---|---|---:|---:|---:|
| Inicio | `[null, null, null]` | 0 | -1 | 0 |
| Agregar Ana | `[Ana, null, null]` | 0 | 0 | 1 |
| Agregar Pedro | `[Ana, Pedro, null]` | 0 | 1 | 2 |
| Agregar Lucía | `[Ana, Pedro, Lucía]` | 0 | 2 | 3 |
| Consultar primero | sin cambios; devuelve Ana | 0 | 2 | 3 |
| Atender siguiente | `[null, Pedro, Lucía]` | 1 | 2 | 2 |

## Ingreso de personas

`agregarPersona` comprueba primero `estaLlena()`, que equivale a `size == nombres.length`. Si hay espacio, calcula la nueva posición de `rear` con `(rear + 1) % nombres.length`, guarda allí el nombre y aumenta `size`.

El módulo hace que los índices puedan volver a cero después del último espacio. Por ejemplo, si `rear` está en la última celda, el siguiente avance, cuando haya lugar, reutiliza la primera posición disponible del arreglo circular. Esto permite aprovechar espacios liberados sin desplazar a las demás personas.

## Atención y consulta

`consultarPrimero()` devuelve `nombres[front]` sin cambiar ningún índice ni reducir el tamaño. Por eso consultar a Ana no la quita de la fila.

`atenderSiguiente()` valida que la cola no esté vacía y guarda `nombres[front]` como la persona atendida. Luego limpia esa celda, avanza `front` con `(front + 1) % nombres.length`, reduce `size` y devuelve el nombre guardado. Después de atender a Ana, `front` apunta a Pedro: el siguiente en llegar pasa a ser el próximo en ser atendido.

La consola muestra:

```text
Primera persona: Ana
Atendida: Ana
Nueva primera persona: Pedro
```

## Qué pasa al atender una cola vacía

Si alguien llama a `atenderSiguiente()` cuando no hay personas, `estaVacia()` detecta que `size == 0`. El método lanza `IllegalStateException` antes de leer `nombres[front]`, avanzar el índice o cambiar `size`. Así se evita acceder a una celda sin una persona válida y el estado de la cola queda intacto. `consultarPrimero()` aplica la misma protección.

De forma equivalente, al intentar agregar cuando `size == nombres.length`, `estaLlena()` hace que `agregarPersona()` lance `IllegalStateException` antes de sobrescribir una persona o cambiar los índices.

## Reflexión sobre el prompt

El prompt inicial justificó adecuadamente la elección de FIFO sobre LIFO para este problema del mundo real: en una fila justa, quien llega primero debe ser atendido primero. También especificó las operaciones y la prueba necesaria, por lo que no hubo necesidad de modificar el prompt para que el algoritmo funcionara como se esperaba.