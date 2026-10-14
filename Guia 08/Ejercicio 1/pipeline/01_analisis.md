# Plan técnico: implementar una pila de enteros

## Objetivo

Definir una clase Java `PilaEnteros` que implemente una pila LIFO mediante un arreglo de enteros de tamaño fijo. Debe permitir insertar, retirar, consultar el tope, comprobar si está vacía o llena y conocer su tamaño actual.

## Diseño de la clase

- **Capacidad:** recibirla en el constructor y crear un arreglo `int[] elementos` de ese tamaño. Rechazar una capacidad menor que uno con `IllegalArgumentException`.
- **Tope:** mantener un entero `top`, inicializado en `-1`. `top` señala el índice del último elemento; `-1` significa que no hay ningún elemento.
- **Encapsulamiento:** mantener el arreglo y `top` como campos `private` para que solo las operaciones de la pila puedan modificar su estado.

## Operaciones

- **`push(int valor)`:** comprobar primero `isFull()`. Si la pila está llena, lanzar `IllegalStateException` con un mensaje claro. Si hay espacio, incrementar `top` y guardar el valor en `elementos[top]`.
- **`pop()`:** comprobar primero `isEmpty()`. Si está vacía, lanzar `IllegalStateException`. En caso contrario, guardar `elementos[top]`, decrementar `top` y devolver el valor guardado.
- **`peek()`:** comprobar `isEmpty()` y, si hay elementos, devolver `elementos[top]` sin modificar `top`.
- **`isEmpty()`:** devolver `top == -1`.
- **`isFull()`:** devolver `top == elementos.length - 1`.
- **`size()`:** devolver `top + 1`.

## Invariantes del estado

- Una pila vacía cumple `top == -1` y `size() == 0`.
- Si contiene elementos, estos ocupan consecutivamente los índices de `0` a `top`.
- Una pila llena cumple `top == elementos.length - 1` y `size() == elementos.length`.
- `push` no modifica el estado cuando la pila está llena; `pop` y `peek` no modifican el estado cuando la pila está vacía porque informan el error antes de acceder al arreglo.

La expresión de `push` debe comprobar capacidad antes de incrementar `top`; de lo contrario, se intentaría escribir fuera del arreglo. En `pop`, primero se conserva el elemento y luego se reduce `top`, para devolver el valor que se retiró.

## Excepciones para estados límite

Se recomienda usar `IllegalStateException` para `push` sobre una pila llena y `pop` o `peek` sobre una pila vacía. Aunque a veces se hable informalmente de “desbordamiento de pila”, `StackOverflowError` en Java describe el agotamiento de la pila de llamadas de la JVM, no el límite de capacidad de este arreglo fijo.

## Prueba de demostración

Crear una pila con capacidad `3` y ejecutar una secuencia como:

1. Comprobar que empieza vacía, con tamaño `0` y no llena.
2. Insertar `10`, `20` y `30` en ese orden.
3. Comprobar que está llena, que su tamaño es `3` y que `peek()` devuelve `30` sin retirarlo.
4. Ejecutar `pop()` y comprobar que devuelve `30`, pues fue el último insertado.
5. Comprobar que el nuevo tope es `20` y que el tamaño bajó a `2`.

La demostración confirma LIFO: el orden de salida es el inverso del orden de entrada.

## Comentarios requeridos en el código

- Explicar que `top` es un índice, comienza en `-1` y cambia al insertar o retirar.
- Indicar que `push` comprueba `isFull()` antes de incrementar el tope y escribir en el arreglo.
- Explicar que `pop` guarda el elemento antes de decrementar `top`.
- Aclarar que `peek` consulta el tope sin alterar la pila.
- Describir por qué las operaciones que requieren elementos verifican primero `isEmpty()`.

## Criterios de aceptación

- La pila usa un arreglo interno de capacidad fija y `top` comienza en `-1`.
- Están implementadas las seis operaciones requeridas con sus tipos de retorno adecuados.
- Insertar y retirar actualizan `top` en el orden correcto.
- `pop` y `peek` informan un error ante una pila vacía; `push` informa un error ante una pila llena.
- La prueba inserta tres números, consulta el tope, retira uno y verifica el nuevo tamaño.
- La prueba demuestra que el elemento retirado es el último que se insertó.