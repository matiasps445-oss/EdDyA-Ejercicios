# Plan técnico: sistema de turnos para atención

## Objetivo

Implementar en Java un sistema de turnos para una oficina que guarde nombres en una cola FIFO respaldada por un arreglo. Debe permitir agregar una persona, atender a la siguiente y consultar quién está primero.

## Justificación de la estructura

Una fila de atención requiere FIFO (**First In, First Out**): la primera persona que llega es la primera atendida. Una pila LIFO invertiría la prioridad y permitiría que la persona que llegó última pasara antes que quienes llevan más tiempo esperando.

## Diseño de la cola

- **Clase sugerida:** `ColaTurnos`.
- **Almacenamiento:** `String[] nombres` de capacidad fija recibida en el constructor.
- **Índices:** `front` indica la próxima posición que se atenderá; `rear` indica la última posición ocupada.
- **Cantidad:** mantener `size` para diferenciar con claridad la cola vacía y llena y poder reutilizar las posiciones liberadas.
- **Estado inicial:** `front = 0`, `rear = -1` y `size = 0`.
- **Capacidad inválida:** rechazar capacidades menores que uno con `IllegalArgumentException`.

Se recomienda una **cola circular**: al agregar, calcular `rear = (rear + 1) % nombres.length`; al atender, avanzar `front = (front + 1) % nombres.length`. Esto permite reutilizar los espacios liberados al inicio sin mover los nombres restantes. La estructura sigue usando un arreglo y mantiene el orden FIFO.

## Operaciones

- **Agregar (`agregarPersona(String nombre)`):** comprobar primero si `size == nombres.length`. Si está llena, lanzar `IllegalStateException`; si hay espacio, avanzar `rear` de forma circular, guardar el nombre en esa posición y aumentar `size`.
- **Atender (`atenderSiguiente()`):** comprobar primero si `size == 0`. Si está vacía, lanzar `IllegalStateException`; en caso contrario, guardar y devolver `nombres[front]`, limpiar esa celda, avanzar `front` de forma circular y reducir `size`.
- **Consultar (`consultarPrimero()`):** comprobar que `size > 0` y devolver `nombres[front]` sin cambiar el estado.
- **`estaVacia()` y `estaLlena()`:** pueden exponerse como métodos auxiliares para expresar los controles de capacidad.

La validación debe preceder a cualquier cambio de índices o acceso al arreglo, para evitar lecturas o escrituras fuera de rango y mantener la cola intacta cuando la operación no es válida.

## Prueba de demostración

Crear una cola con capacidad suficiente y realizar:

1. Agregar `"Ana"`, `"Pedro"` y `"Lucia"`, en ese orden.
2. Consultar e imprimir a la primera persona; debe ser `Ana` y la consulta no debe retirarla.
3. Atender a la siguiente persona; debe devolver y retirar `Ana`.
4. Consultar e imprimir nuevamente a la primera persona; debe ser `Pedro`.

Salida conceptual esperada:

```text
Primera persona: Ana
Atendida: Ana
Nueva primera persona: Pedro
```

## Casos límite

- Atender o consultar cuando `size == 0` debe lanzar `IllegalStateException` sin acceder a una posición inválida.
- Agregar cuando `size == nombres.length` debe lanzar `IllegalStateException` sin sobrescribir una persona pendiente.
- La cola circular debe calcular los índices con módulo de la capacidad y actualizar `size` en cada inserción y retiro.

## Comentarios requeridos en el código

- Explicar por qué una fila de atención requiere FIFO y no LIFO.
- Describir el rol de `front`, `rear` y `size`.
- Explicar cómo la aritmética modular permite reutilizar posiciones del arreglo.
- Indicar por qué se validan las condiciones de vacío y lleno antes de acceder al arreglo.
- Aclarar que `consultarPrimero()` observa el turno sin atenderlo.

## Criterios de aceptación

- Los nombres se almacenan en un arreglo de capacidad fija y se atienden en el mismo orden en que se agregaron.
- La implementación utiliza `front`, `rear` y una cantidad de elementos para administrar la cola circular.
- `agregarPersona`, `atenderSiguiente` y `consultarPrimero` respetan el contrato FIFO y las condiciones límite.
- La prueba agrega a Ana, Pedro y Lucía; consulta a Ana, la atiende y confirma que Pedro queda primero.
- El uso de FIFO se justifica como la política justa para una fila de atención.