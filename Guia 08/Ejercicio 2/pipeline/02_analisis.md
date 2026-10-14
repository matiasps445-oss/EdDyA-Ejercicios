# Plan técnico: simulador de torre de platos

## Objetivo

Implementar en Java una torre de platos usando una pila LIFO respaldada por un arreglo de tamaño fijo. Cada plato se representa con un `String`. El último plato agregado queda arriba y debe ser el primero en retirarse.

## Justificación de la estructura

Una torre física solo permite acceder al plato superior. Por eso, al retirar un plato se obtiene el último que se agregó: este comportamiento es LIFO (Last In, First Out), propio de una pila. Una cola es FIFO (First In, First Out) y retiraría primero el plato que quedó en la base, lo que no representa la operación de la torre.

## Diseño de la clase

- **Clase sugerida:** `TorrePlatos`.
- **Arreglo interno:** `String[] platos`, de capacidad fija recibida en el constructor.
- **Índice del tope:** `int top`, inicializado en `-1`; ese valor indica que no hay platos.
- **Encapsulamiento:** mantener el arreglo y el índice como campos `private`.
- **Capacidad inválida:** rechazar capacidades menores que uno con `IllegalArgumentException`.

## Operaciones requeridas

- **Agregar (`agregarPlato(String plato)`):** comprobar primero si la pila está llena (`top == platos.length - 1`). Si está llena, lanzar `IllegalStateException` antes de modificar `top` o acceder al arreglo. Si hay espacio, incrementar `top` y guardar el nombre en `platos[top]`.
- **Retirar (`retirarPlato()`):** comprobar primero si está vacía (`top == -1`). Si lo está, lanzar `IllegalStateException`. De lo contrario, guardar `platos[top]`, decrementar `top` y devolver el plato retirado.
- **Consultar (`consultarPlatoSuperior()`):** comprobar que no esté vacía y devolver `platos[top]` sin cambiar el índice ni retirar el plato.

Las comprobaciones pueden expresarse mediante métodos auxiliares `estaVacia()` y `estaLlena()` para que las operaciones sean claras. No se debe incrementar el tope antes de validar la capacidad, ni consultar el arreglo antes de validar que haya un elemento.

## Prueba de demostración

Crear una torre de capacidad `3` y ejecutar:

1. Agregar `"Plato 1"`, `"Plato 2"` y `"Plato 3"`, en ese orden.
2. Consultar e imprimir el plato superior; debe ser `Plato 3` y la consulta no debe modificar la pila.
3. Retirar e imprimir el plato superior; debe retirarse `Plato 3`.
4. Consultar e imprimir nuevamente el superior; debe ser `Plato 2`.

Salida conceptual esperada:

```text
Plato superior: Plato 3
Plato retirado: Plato 3
Nuevo plato superior: Plato 2
```

Esta secuencia demuestra que la última inserción es la primera retirada y que `peek` no elimina el plato consultado.

## Casos límite

- Intentar retirar o consultar cuando `top == -1` debe producir un error claro y no cambiar el estado.
- Intentar agregar cuando `top == platos.length - 1` debe producir un error de pila llena y no cambiar el estado.
- Para estos errores de estado se recomienda `IllegalStateException`. `StackOverflowError` en Java se refiere al agotamiento de la pila de llamadas de la JVM, no al límite de capacidad del arreglo.

## Comentarios requeridos en el código

- Explicar por qué la torre se modela como pila LIFO y no como cola FIFO.
- Indicar que `top` comienza en `-1` y representa el último índice ocupado.
- Explicar por qué se valida la capacidad antes de agregar y por qué se valida el estado vacío antes de retirar o consultar.
- Aclarar que retirar guarda el valor antes de decrementar `top`, mientras consultar no modifica el estado.

## Criterios de aceptación

- Los platos se almacenan en un arreglo `String[]` de capacidad fija.
- `top` comienza en `-1` y se actualiza correctamente en las operaciones de inserción y retiro.
- Agregar, retirar y consultar respetan el comportamiento LIFO.
- Los casos de pila llena y vacía se detectan antes de acceder fuera del arreglo.
- La prueba agrega los tres nombres solicitados, consulta el superior, lo retira y vuelve a consultar.
- La salida muestra `Plato 3` como el plato retirado y `Plato 2` como el nuevo superior.