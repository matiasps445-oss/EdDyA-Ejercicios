# Plan técnico: cola circular

## Prompt inicial

Quiero implementar en Java una clase de Cola Circular utilizando un arreglo de tamaño fijo. El objetivo principal de esta estructura es resolver el problema de desperdicio de espacio de la cola simple. Para reutilizar los espacios liberados al inicio del arreglo, los índices deben volver a la posición 0 una vez que alcanzan el límite final, simulando un anillo. El programa debe permitir las operaciones `enqueue`, `dequeue`, `front`, `isEmpty` e `isFull`. Para manejar el arreglo interno y lograr el comportamiento circular, es imperativo usar el operador módulo `%`. Cada vez que se incrementan los índices, la fórmula matemática debe ser: `rear = (rear + 1) % tamaño` y `front = (front + 1) % tamaño`. Para detectar los casos límite: la cola se considera vacía si la cantidad actual de elementos (`size`) es 0. Se considera llena si la cantidad de elementos es igual al tamaño total del arreglo (`size == tamaño`). Para verificar que el resultado sea correcto, quiero ejecutar una prueba que consista en instanciar una cola de tamaño 3, llenarla con tres elementos, hacer un `dequeue` del primero, y luego hacer un `enqueue` de un elemento nuevo para demostrar visualmente que se reutiliza el índice 0 exitosamente.

## Objetivo

Implementar una cola circular FIFO de enteros mediante un arreglo de capacidad fija. A diferencia de la cola lineal, la cola circular puede reutilizar las posiciones liberadas al principio del arreglo cuando `rear` llega al final.

## Diseño de la cola

- **Clase sugerida:** `ColaCircularEnteros`.
- **Arreglo interno:** `int[] elementos`; su capacidad se recibe al crear la cola.
- **Índices privados:** `front` señala el próximo elemento a retirar y `rear` señala la última posición ocupada.
- **Contador privado:** `size` guarda la cantidad lógica de elementos y permite distinguir sin ambigüedad una cola vacía de una llena.
- **Estado inicial recomendado:** `front = 0`, `rear = -1`, `size = 0`.
- **Vacía:** `size == 0`.
- **Llena:** `size == elementos.length`.
- Rechazar capacidades menores que 1 con `IllegalArgumentException`.

## Operaciones

- **`enqueue(int valor)`:** si `isFull()` es verdadero, lanzar `IllegalStateException` sin modificar el estado. En otro caso, avanzar `rear` usando exactamente `rear = (rear + 1) % elementos.length`, guardar el valor en esa posición e incrementar `size`.
- **`dequeue()`:** si `isEmpty()` es verdadero, lanzar `IllegalStateException`. De lo contrario, guardar y devolver `elementos[front]`, limpiar la celda retirada con `0` para facilitar la demostración, avanzar `front` usando exactamente `front = (front + 1) % elementos.length` y decrementar `size`.
- **`front()`:** si la cola está vacía, lanzar `IllegalStateException`; si no, devolver `elementos[front]` sin modificar el estado.
- **`isEmpty()`:** devolver `size == 0`.
- **`isFull()`:** devolver `size == elementos.length`.
- **`size()`:** devolver el contador `size` para poder mostrar el estado de la cola.
- **Mostrar estado:** incluir el arreglo físico completo, `front`, `rear` y `size` después de cada operación de la prueba.

El operador módulo mantiene ambos índices dentro del rango válido `0` a `elementos.length - 1`. Cuando un índice está en el último índice, sumarle uno y tomar el resto de la división por la capacidad hace que vuelva a 0. El contador `size` es necesario porque, en una estructura circular, los valores de `front` y `rear` por sí solos no bastan para distinguir de forma general entre vacía y llena.

## Secuencia de demostración

1. Crear una cola de capacidad 3 y mostrar su estado inicial.
2. Encolar tres valores, por ejemplo `10`, `20` y `30`, mostrando el estado después de cada inserción.
3. Desencolar el primer valor (`10`) y mostrar el arreglo y los índices.
4. Encolar un valor nuevo, por ejemplo `40`, y mostrar el estado. La inserción debe reutilizar el índice 0 aunque `rear` estuviera en el último índice antes de la operación.
5. Consultar `front()` para confirmar que el primer elemento pendiente sigue siendo `20`; mostrar también que la cola está llena después de la nueva inserción.

## Estados clave esperados

| Momento | Arreglo | `front` | `rear` | `size` |
|---|---|---:|---:|---:|
| Inicial | `[0, 0, 0]` | 0 | -1 | 0 |
| Encolar `10` | `[10, 0, 0]` | 0 | 0 | 1 |
| Encolar `20` | `[10, 20, 0]` | 0 | 1 | 2 |
| Encolar `30` | `[10, 20, 30]` | 0 | 2 | 3 |
| Desencolar `10` | `[0, 20, 30]` | 1 | 2 | 2 |
| Encolar `40` | `[40, 20, 30]` | 1 | 0 | 3 |

La última inserción calcula `rear = (2 + 1) % 3 = 0`: el valor `40` ocupa la posición que quedó libre al retirar `10`. El frente continúa en el índice 1, de modo que `front()` devuelve `20`; la cola mantiene el orden FIFO aunque los elementos ocupen posiciones que rodean el final del arreglo.

## Comentarios y criterios de aceptación

- Explicar que `% elementos.length` hace que los índices vuelvan al inicio sin salirse de los límites del arreglo.
- Mantener privados el arreglo, `front`, `rear` y `size`.
- Verificar el estado lleno y vacío por medio de `size`, no comparando únicamente `front` y `rear`.
- Lanzar un error al intentar retirar o consultar el frente de una cola vacía, y al intentar agregar a una cola llena.
- La prueba debe tener capacidad 3, encolar tres elementos, retirar el primero y encolar uno nuevo.
- La salida debe mostrar que el elemento nuevo se escribió en el índice 0, que `rear` volvió a 0 y que `front()` conserva el siguiente elemento FIFO.
- No desplazar elementos para liberar espacio: la reutilización debe ocurrir mediante el avance circular con módulo.