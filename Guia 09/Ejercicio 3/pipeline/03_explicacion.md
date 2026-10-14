# Explicación: obtener un elemento por posición

## La falsa sensación de que hay un índice

El método recibe un número llamado `posicion`, pero eso no convierte a la lista enlazada en un arreglo ni le da acceso directo al nodo. En un `ArrayList`, los elementos se guardan en un arreglo interno y `get(posicion)` puede calcular directamente dónde está la referencia correspondiente. Por eso el acceso por índice de un `ArrayList` es $O(1)$: la cantidad de pasos no crece con el tamaño de la colección.

En esta lista enlazada cada `Nodo` contiene su dato y una referencia al nodo siguiente. No existe un bloque contiguo de posiciones ni una fórmula que lleve directamente al nodo número `posicion`. Para encontrarlo, `obtener` empieza en `head` y sigue los enlaces uno por uno. Si pide el nodo más alejado, puede visitar todos los nodos: el tiempo es $O(n)$ en el peor caso.

La posición funciona como el número de pasos que hay que avanzar desde la cabeza, no como una dirección directa de memoria. La operación es de solo lectura y utiliza espacio adicional $O(1)$ para guardar el nodo actual y el contador.

## Cómo la variable `contador` encuentra el nodo

El método inicializa dos variables locales:

```java
Nodo actual = head;
int contador = 0;
```

`actual` empieza apuntando al primer nodo. `contador` indica la posición de ese nodo: como la lista usa posiciones base cero, el primer nodo corresponde a `0`.

El ciclo es `while (contador < posicion)`. Cada repetición avanza `actual` un enlace con `actual = actual.getSiguiente()` y luego incrementa `contador`. De este modo, las dos variables avanzan juntas: `actual` cambia de nodo y `contador` registra cuántos enlaces se han recorrido.

Con la lista `10 -> 20 -> 30` y `posicion == 1`:

| Momento | Nodo señalado por `actual` | `contador` | ¿`contador < posicion`? |
|---|---:|---:|---|
| Inicio | `10` | `0` | Sí: avanza al siguiente |
| Después de avanzar | `20` | `1` | No: el ciclo termina |

Al salir, `actual` señala el nodo de la posición solicitada. `return actual.getDato()` devuelve `20`. Para la posición `0`, el ciclo no avanza y se devuelve el dato de `head`; para la posición `2`, avanza dos enlaces.

El recorrido modifica solo la referencia local `actual`. No mueve `head`, no cambia los enlaces y no altera `size`.

## Validación fail-fast: fallar antes de recorrer

Al principio del método se comprueba:

```java
if (posicion < 0 || posicion >= size) {
    throw new IndexOutOfBoundsException(...);
}
```

El rango válido es desde `0` hasta `size - 1`. Si la posición es negativa, no representa un nodo de la lista. Si es igual o mayor que `size`, queda después del último. Ambas condiciones se detectan antes de acceder a `head` o seguir enlaces.

Este comportamiento es *fail-fast*: el método identifica el error en el punto de entrada y comunica exactamente que la posición no es válida mediante `IndexOutOfBoundsException`. Sin esa comprobación, una posición fuera del rango podría hacer que el recorrido siguiera hasta `null` y luego intentara leer un método de una referencia nula, produciendo un `NullPointerException` menos claro. La validación también maneja correctamente una lista vacía: como `size` vale `0`, cualquier posición es inválida.

En el `main`, la lista contiene tres elementos. `obtener(1)` imprime el dato del medio (`20`). Las llamadas con `3` y `-1` lanzan `IndexOutOfBoundsException`, que se captura para mostrar el mensaje sin detener abruptamente la demostración.
