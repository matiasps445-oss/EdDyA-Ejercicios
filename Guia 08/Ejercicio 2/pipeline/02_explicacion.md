# Explicación de `TorreDePlatos`

`TorreDePlatos` representa una torre física mediante una pila LIFO guardada en un arreglo `String[]`. Cada celda contiene el nombre de un plato y `top` indica el índice del plato que está arriba y puede retirarse directamente.

## Por qué una torre es LIFO

LIFO significa **Last In, First Out**: el último plato que se coloca arriba es el primero que se puede tomar. Si se apilan, en orden, `Plato 1`, `Plato 2` y `Plato 3`, `Plato 3` queda en la cima. Para llegar a `Plato 2`, primero hay que retirar `Plato 3`.

Una cola usa FIFO (**First In, First Out**) y retiraría primero el plato que entró primero, `Plato 1`. En una torre física ese plato está en la base, debajo de los demás: intentar sacarlo sin retirar antes los platos superiores no es posible y desarmaría la torre. Por eso la pila describe correctamente el acceso real; la cola no.

## Cómo representan la pila el arreglo y `top`

El arreglo tiene capacidad fija. La clase empieza con `top = -1`, que significa que ninguna posición está ocupada lógicamente. Al insertar, `top` aumenta y apunta a la nueva celda superior. Al retirar, el código lee esa celda y disminuye `top`; no necesita desplazar los demás elementos.

Con capacidad `3`, el estado evoluciona así:

| Acción | Índices `[0, 1, 2]` | `top` | Contenido lógico de la torre |
|---|---|---:|---|
| Inicio | `[null, null, null]` | -1 | vacía |
| `agregarPlato("Plato 1")` | `[Plato 1, null, null]` | 0 | `[Plato 1]` |
| `agregarPlato("Plato 2")` | `[Plato 1, Plato 2, null]` | 1 | `[Plato 1, Plato 2]` |
| `agregarPlato("Plato 3")` | `[Plato 1, Plato 2, Plato 3]` | 2 | `[Plato 1, Plato 2, Plato 3]` |
| `retirarPlato()` devuelve `Plato 3` | la celda 2 aún puede conservar `Plato 3` | 1 | `[Plato 1, Plato 2]` |

Después de retirar, la celda del índice `2` aún puede guardar físicamente la referencia a `Plato 3`, pero ya no pertenece a la torre: el límite lógico es `top = 1`. Si se agrega otro plato, esa posición se sobrescribe.

## Qué hacen las operaciones

- **`agregarPlato`** comprueba `estaLlena()` y, si hay espacio, incrementa `top` y escribe el nombre en `platos[top]`.
- **`retirarPlato`** guarda `platos[top]`, reduce `top` y devuelve el nombre guardado. Así no pierde el valor al actualizar el índice.
- **`consultarPlatoSuperior`** devuelve `platos[top]` sin modificar el índice; consultar no retira el plato.
- **`estaVacia`** comprueba si `top == -1`.
- **`estaLlena`** comprueba si `top == platos.length - 1`.

En `main`, después de agregar los tres platos, la consulta devuelve `Plato 3`. El `pop` retira ese mismo plato y la consulta siguiente devuelve `Plato 2`. Esa secuencia demuestra LIFO y también que consultar no modifica la torre.

## Cómo se evitan accesos inválidos

Antes de agregar, `agregarPlato` verifica si `top` ya está en el último índice válido. Si está llena, lanza `IllegalStateException` antes de incrementar `top` o escribir. Sin esa comprobación, el siguiente índice quedaría fuera del arreglo y Java podría lanzar `ArrayIndexOutOfBoundsException`.

Antes de retirar o consultar, los métodos comprueban `estaVacia()`. Si `top` es `-1`, lanzan `IllegalStateException` antes de evaluar `platos[top]`; así evitan acceder al índice `-1` y dejan el estado sin cambios. El constructor también rechaza capacidades menores que uno con `IllegalArgumentException`.

El código usa `IllegalStateException` para expresar que la operación no es válida con el estado actual de la torre. Aunque suele llamarse informalmente “desbordamiento” a agregar en una pila llena, `StackOverflowError` en Java se refiere específicamente a agotar la pila de llamadas de la JVM.

## Reflexión sobre el prompt

El prompt inicial entregado al Agente Planificador justificó con total claridad por qué una torre de platos requiere una Pila y no una Cola: el único plato accesible es el último que se colocó. También especificó las operaciones y los límites esperados, por lo que no fue necesario realizar ajustes posteriores para obtener una implementación lógica correcta.