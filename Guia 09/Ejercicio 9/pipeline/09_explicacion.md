# Explicación: invertir una lista enlazada simple

## Por qué el algoritmo usa tres referencias

En cada iteración, el método necesita conservar tres roles distintos:

- `actual`: nodo cuyo enlace se está cambiando.
- `anterior`: comienzo del tramo que ya fue invertido; será el nuevo destino de `actual.siguiente`.
- `siguiente`: comienzo del tramo que todavía no se ha procesado.

Antes de invertir un enlace, el algoritmo debe recordar tanto el tramo anterior como el resto aún por recorrer. Por eso, en la versión iterativa in-place, primero guarda `siguiente`, después cambia el enlace de `actual` y finalmente avanza las referencias.

Conviene precisar la afirmación de que es “matemáticamente imposible” usar menos punteros: no es una imposibilidad absoluta para cualquier algoritmo. Se puede invertir una lista usando recursión, aprovechando la pila de llamadas, o guardando nodos en una estructura auxiliar. Pero esas alternativas usan almacenamiento adicional. En este algoritmo iterativo, que modifica los propios enlaces sin otra estructura, se necesitan simultáneamente los tres roles. Con un solo cursor, al reemplazar su enlace se pierde el camino hacia el resto; con solo `anterior` y `actual`, al cambiar `actual.siguiente` falta una referencia para conservar el tramo no procesado, salvo que se use otro almacenamiento o se recicle otra referencia de la estructura.

También es más preciso hablar de “perder acceso a nodos” que de una fuga de memoria. En Java, si un tramo queda desconectado y no existen otras referencias, sus objetos quedan inalcanzables y el Garbage Collector podrá recuperarlos más adelante. El daño inmediato es que el algoritmo ya no puede completar la inversión ni conservar correctamente los datos.

## Primera iteración: el nodo `10` pasa a apuntar a `null`

Al inicio, la lista contiene:

```text
head
  |
  v
 10 -> 20 -> 30 -> 40 -> null
```

Las referencias comienzan así:

```text
anterior = null
actual   = 10
```

Primero se guarda el sucesor de `10`:

```java
siguiente = actual.getSiguiente();
```

Ahora `siguiente` apunta a `20`. Esto conserva el acceso a todo el tramo que todavía no se ha invertido:

```text
actual                         siguiente
  |                                |
  v                                v
 10 -> 20 -> 30 -> 40 -> null     20 -> 30 -> 40 -> null
```

Luego se cambia el enlace de `10`:

```java
actual.setSiguiente(anterior);
```

Como `anterior` es `null`, `10.siguiente` pasa a ser `null`. En este instante `head` todavía apunta a `10`, pero el resto no se perdió: la referencia local `siguiente` conserva el acceso a `20 -> 30 -> 40`.

Por último se avanza:

```java
anterior = actual;
actual = siguiente;
```

Ahora `anterior` apunta a `10`, que es el tramo invertido hasta el momento, y `actual` apunta a `20`, el próximo nodo por procesar. El mismo procedimiento se repite para cada nodo.

## Por qué `anterior` termina siendo el nuevo `head`

Al inicio de cada iteración, `anterior` apunta a la cabeza del segmento ya invertido y `actual` apunta al comienzo del segmento que falta invertir. `siguiente` guarda temporalmente el resto antes de modificar el enlace actual.

Cada vuelta mueve un nodo de `actual` al segmento invertido. Cuando el ciclo termina, `actual == null`: no quedan nodos por procesar. Por lo tanto, `anterior` apunta al último nodo que se procesó, que era precisamente el último nodo de la lista original.

Para la lista de ejemplo, al finalizar los enlaces representan:

```text
anterior -> 40 -> 30 -> 20 -> 10 -> null
actual   -> null
```

La asignación final:

```java
head = anterior;
```

hace que la lista vuelva a tener un punto de entrada persistente, ahora en `40`. El resultado es `40 -> 30 -> 20 -> 10 -> null`. El primer nodo original (`10`) quedó como último y apunta a `null`. La cantidad de nodos no cambia, así que `size` permanece igual.
