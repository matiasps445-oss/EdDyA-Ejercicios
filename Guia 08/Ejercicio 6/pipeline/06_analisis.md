# Plan técnico: cola simple de enteros

## Objetivo

Implementar en Java una clase `ColaEnteros` que use un arreglo de tamaño fijo y respete FIFO (First In, First Out): el primer entero agregado será el primero en retirarse.

## Diseño de la clase

- **Arreglo interno:** `int[] elementos`, con capacidad recibida en el constructor.
- **Índices:** `front` apunta al elemento más antiguo pendiente de salir; `rear` apunta al último elemento agregado.
- **Estado inicial:** `front = 0` y `rear = -1`. La cola está vacía cuando `front > rear`.
- **Capacidad inválida:** rechazar una capacidad menor que uno con `IllegalArgumentException`.
- Mantener el arreglo y ambos índices como campos `private`.

## Operaciones requeridas

- **`enqueue(int valor)`:** comprobar `isFull()` antes de modificar el estado. Si `rear == elementos.length - 1`, lanzar `IllegalStateException`; de lo contrario, incrementar `rear` y guardar el valor en `elementos[rear]`.
- **`dequeue()`:** comprobar `isEmpty()` antes de acceder al arreglo. Si está vacía, lanzar `IllegalStateException`. En caso contrario, guardar y devolver `elementos[front]`, y avanzar `front` en uno. Si ese avance deja `front > rear`, la cola quedó completamente vacía y se deben restablecer `front = 0` y `rear = -1` para que pueda reutilizarse desde el principio.
- **`front()`:** comprobar `isEmpty()` y devolver `elementos[front]` sin retirar el valor ni modificar los índices.
- **`isEmpty()`:** devolver `front > rear`.
- **`isFull()`:** devolver `rear == elementos.length - 1`.
- **`size()`:** devolver `0` cuando está vacía; si no, devolver `rear - front + 1`.

Validar antes de leer o escribir evita accesos fuera del arreglo. Los errores de operación deben expresarse con `IllegalStateException`, mientras que una capacidad inválida se rechaza con `IllegalArgumentException`.

## Alcance y limitación de la cola lineal

El diseño sigue el enunciado: `rear` solo avanza y no vuelve al inicio mientras queden elementos en la cola. Por eso es una **cola lineal**, no circular. Después de retirar algunos elementos, pueden quedar celdas libres antes de `front`, pero no se reutilizan; cuando `rear` llega al final, `isFull()` devuelve `true` aunque la cantidad lógica de elementos sea menor que la capacidad física. Reiniciar los índices cuando la cola se vacía por completo permite reutilizar todo el arreglo después de retirar todos los elementos.

Una cola circular permitiría reutilizar huecos durante el uso, pero cambiaría la regla simple de incrementar `rear` y queda fuera del alcance de este ejercicio.

## Prueba de demostración

Crear una cola con capacidad `3` y:

1. Encolar `10`, `20` y `30`, en ese orden.
2. Consultar `front()`; debe devolver `10` sin retirarlo.
3. Desencolar; debe devolver `10`, el primer valor que entró.
4. Consultar de nuevo `front()`; debe devolver `20`.
5. Mostrar `size()`; debe ser `2`.

Salida conceptual esperada:

```text
Front inicial: 10
Dequeue: 10
Nuevo front: 20
Size: 2
```

## Comentarios requeridos en el código

- Explicar la diferencia de funciones entre `front` y `rear`.
- Indicar por qué `enqueue` avanza `rear` y `dequeue` avanza `front`.
- Explicar las comprobaciones previas de cola vacía y llena.
- Aclarar por qué los índices se reinician al retirar el último elemento.
- Señalar la limitación de una cola lineal frente a una circular, sin implementar la alternativa.

## Criterios de aceptación

- `ColaEnteros` usa un arreglo `int[]` de capacidad fija y los índices `front` y `rear`.
- La cola inicia vacía con `front = 0` y `rear = -1`.
- `enqueue`, `dequeue`, `front`, `isEmpty`, `isFull` y `size` implementan el comportamiento FIFO esperado.
- Las operaciones que leen o escriben validan el estado antes de acceder al arreglo.
- La prueba encola `10`, `20` y `30`; consulta `10`, retira `10` y luego comprueba que el nuevo `front` sea `20` y el tamaño `2`.