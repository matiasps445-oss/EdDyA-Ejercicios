# Explicación: desperdicio de espacio en una cola lineal

## Idea central

Una cola lineal implementada sobre un arreglo usa dos índices para conservar el orden FIFO (*First In, First Out*): `front` indica dónde está el siguiente elemento que debe salir, y `rear` indica la última posición ocupada. Ambos índices se desplazan en una sola dirección: `front` aumenta cuando se desencola y `rear` aumenta cuando se encola. En esta implementación no retroceden ni dan la vuelta al llegar al extremo del arreglo.

## Por qué se desperdician posiciones

El arreglo tiene capacidad $N = 5$ y sus índices válidos son $0, 1, 2, 3, 4$. Al principio, `front = 0` y `rear = -1`. Cada inserción incrementa `rear` y escribe allí; tras encolar los cinco valores, `rear = 4`, que es el último índice.

Al retirar un valor, `dequeue()` lo lee en la posición `front`, escribe `0` para hacer visible el hueco y luego incrementa `front`. No desplaza los elementos restantes y tampoco reduce `rear`. Después de retirar dos elementos, se cumple:

- `front = 2`: el primer elemento activo está en el índice 2.
- `rear = 4`: el último elemento activo sigue en el índice 4.
- Tamaño lógico: $rear - front + 1 = 4 - 2 + 1 = 3$.
- Posiciones físicamente libres: los índices 0 y 1.

El espacio libre no se puede usar porque `enqueue()` solo escribe después de `rear`. Para decidir si puede insertar, `isFull()` comprueba `rear == elementos.length - 1`. Como `rear` vale 4 y el último índice también es 4, rechaza la operación antes de modificar el arreglo. No es que queden cinco elementos activos: quedan tres. El problema es que los huecos están detrás de `front`, y la regla lineal de inserción no vuelve a ellos.

En general, si una cola de capacidad $N$ ya alcanzó el final (`rear = N - 1`) y se retiraron $k$ elementos del frente sin desplazar los demás, entonces `front = k`, quedan $N-k$ elementos activos y hay $k$ celdas libres al principio. Sin embargo, el índice `rear` todavía señala el final. Por eso la condición de lleno de esta implementación puede cumplirse aunque el tamaño lógico sea menor que $N$.

## Lectura de la salida

| Operación | Arreglo físico | `front` | `rear` | Tamaño lógico |
|---|---|---:|---:|---:|
| Inicio | `[0, 0, 0, 0, 0]` | 0 | -1 | 0 |
| Encolar `10` | `[10, 0, 0, 0, 0]` | 0 | 0 | 1 |
| Encolar `20` | `[10, 20, 0, 0, 0]` | 0 | 1 | 2 |
| Encolar `30` | `[10, 20, 30, 0, 0]` | 0 | 2 | 3 |
| Encolar `40` | `[10, 20, 30, 40, 0]` | 0 | 3 | 4 |
| Encolar `50` | `[10, 20, 30, 40, 50]` | 0 | 4 | 5 |
| Desencolar `10` | `[0, 20, 30, 40, 50]` | 1 | 4 | 4 |
| Desencolar `20` | `[0, 0, 30, 40, 50]` | 2 | 4 | 3 |
| Intentar encolar `60` | `[0, 0, 30, 40, 50]` | 2 | 4 | 3 |

Los ceros en las posiciones 0 y 1 son marcadores visuales puestos por `dequeue()`; no son elementos activos. Cuando se intenta agregar `60`, `enqueue()` lanza `IllegalStateException` porque `rear` ya llegó al índice 4. El `catch` imprime el error y permite que el programa continúe hasta `mostrarArreglo()`, que demuestra que ni el arreglo ni los índices cambiaron con el intento fallido.

## Solución estructural: cola circular

La solución conceptual es una **Cola Circular**: al alcanzar el último índice, los índices pueden continuar desde el comienzo del arreglo si esas posiciones ya fueron liberadas. Así se reutiliza el espacio físico sin desplazar todos los elementos. Para que esa lógica funcione correctamente, la estructura también debe distinguir de manera fiable entre los estados vacío y lleno. Este ejercicio no la implementa; la cola circular es el siguiente diseño que aborda esta limitación.

## Reflexión sobre el prompt

El prompt inicial pidió explícitamente demostrar el desperdicio de espacio y mostrar cómo quedan huecos al principio que una cola simple no puede reutilizar. Esa precisión permitió generar directamente un código de demostración visual con los estados del arreglo, los índices y el intento de inserción fallido, sin necesidad de hacer ajustes posteriores al prompt.