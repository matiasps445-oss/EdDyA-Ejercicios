# Explicación de `PilaEnteros`

`PilaEnteros` implementa una pila LIFO (Last In, First Out) con un arreglo `int[]` de capacidad fija. Los enteros se almacenan en posiciones consecutivas desde el índice `0`; no se mueven cuando se retira el tope. El campo `top` marca cuál es el último índice que pertenece a la pila en ese momento.

## Por qué `top` comienza en `-1`

Los índices válidos del arreglo empiezan en `0`. Por eso `top = -1` significa que no hay ningún índice ocupado: la pila está vacía. El mismo campo permite responder consultas sin recorrer el arreglo:

- `isEmpty()` devuelve `true` si `top == -1`.
- `isFull()` devuelve `true` si `top == elementos.length - 1`.
- `size()` devuelve `top + 1`: con `top = -1`, el tamaño es `0`; con `top = 2`, hay tres elementos.

El arreglo físico puede contener valores en posiciones que ya no forman parte de la pila. La frontera lógica es `top`: solo se consideran válidas las posiciones entre `0` y `top`, inclusive.

## Qué ocurre al ejecutar `push` y `pop`

Supongamos capacidad `3`. Un arreglo `int[]` recién creado tiene inicialmente sus celdas en cero; `top = -1` hace que ninguna cuente como elemento.

| Operación | Índices físicos `[0, 1, 2]` | `top` | Estado lógico |
|---|---|---:|---|
| Inicio | `[0, 0, 0]` | -1 | vacía; tamaño 0 |
| `push(10)` | `[10, 0, 0]` | 0 | `[10]` |
| `push(20)` | `[10, 20, 0]` | 1 | `[10, 20]` |
| `push(30)` | `[10, 20, 30]` | 2 | `[10, 20, 30]`, llena |
| `pop()` devuelve `30` | `[10, 20, 30]` | 1 | `[10, 20]`, tamaño 2 |

En `push`, primero se verifica que haya espacio; luego se incrementa `top` y se escribe el valor en `elementos[top]`. Por ejemplo, al insertar `20`, el tope pasa de `0` a `1` y el valor se guarda en el índice `1`.

En `pop`, primero se lee y conserva `elementos[top]`; después se decrementa `top` y se devuelve el valor guardado. Al retirar `30`, la celda física `elementos[2]` aún puede contener `30`, pero `top` baja a `1`, por lo que esa celda ya no pertenece a la pila. El próximo `push` la sobrescribiría.

## LIFO, `peek` y la prueba de `main`

La demostración inserta `10`, `20` y `30`. `peek()` devuelve `30` sin modificar `top`, porque consulta `elementos[top]` pero no decrementa el índice. Luego `pop()` también devuelve `30`, ya que fue el último en entrar. Tras esa retirada, `peek()` devuelve `20` y `size()` vale `2`.

Al retirar después `20` y `10`, `top` vuelve a `-1`; `isEmpty()` devuelve `true` y `size()` vuelve a `0`. Esto muestra la propiedad LIFO: los elementos salen en orden inverso al que entraron.

## Protección de los casos límite

- **Pila llena:** cuando `top == elementos.length - 1`, `push` lanza `IllegalStateException` antes de incrementar `top` o escribir. Así no se intenta acceder a un índice fuera del arreglo y el estado de la pila permanece intacto.
- **Pila vacía:** cuando `top == -1`, `pop` y `peek` lanzan `IllegalStateException` antes de acceder a `elementos[top]`. No se lee el índice `-1` ni se cambia el estado.
- **Capacidad inválida:** el constructor rechaza capacidades menores que uno con `IllegalArgumentException`.

En el enunciado se usa informalmente “Stack Overflow” para una pila llena. El código representa ese error de estado con `IllegalStateException`; `StackOverflowError` es un error distinto de Java, asociado al agotamiento de la pila de llamadas de la JVM.

## Reflexión sobre el prompt

El prompt inicial entregado al Agente Planificador fue lo suficientemente claro y explícito en sus reglas como para orientar el diseño de la clase, la inicialización de `top`, las operaciones requeridas, el tratamiento de los casos límite y la prueba LIFO. Por ello, no fue necesario ajustar los requisitos posteriores para obtener la solución correcta.