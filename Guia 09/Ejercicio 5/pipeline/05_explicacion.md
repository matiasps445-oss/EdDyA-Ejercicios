# Explicación: eliminar un nodo por valor

## Desconectar el nodo y el papel del Garbage Collector

En Java no se libera manualmente la memoria de un nodo con una instrucción equivalente a `free`. El programa modifica las referencias para que la lista deje de llegar al nodo eliminado. El Garbage Collector (GC) identifica periódicamente los objetos que ya no son alcanzables desde las raíces de la aplicación (por ejemplo, variables activas de métodos y campos de objetos vivos) y puede recuperar su memoria.

Al eliminar un nodo del medio o del final, el código ejecuta:

```java
anterior.setSiguiente(actual.getSiguiente());
```

Antes, la cadena se ve así:

```text
head -> 10 -> 20 -> 30 -> 40 -> null
                         ^
                       actual
```

Al eliminar `30`, `anterior` señala `20` y `actual` señala `30`. La asignación hace que `20.siguiente` apunte directamente a `40`:

```text
head -> 10 -> 20 -------> 40 -> null
                         
                    30 (ya no está enlazado desde head)
```

La lista ya no pasa por `30`, y los nodos posteriores siguen conectados. Durante la ejecución del método, la variable local `actual` todavía puede referenciar el nodo `30`; al retornar, esa variable deja de existir. Si no quedan otras referencias al nodo, pasa a ser elegible para el GC. La recolección no necesariamente ocurre de inmediato. Si otra parte del programa conserva una referencia al nodo, ese objeto todavía puede ser alcanzable desde allí.

## Por qué hacen falta `anterior` y `actual`

Una lista simplemente enlazada solo tiene enlaces hacia adelante: cada nodo conoce a su sucesor, pero no conoce a su predecesor. Para eliminar un nodo distinto de la cabeza, no basta con saber cuál es `actual`: también hay que cambiar el enlace del nodo que conduce a `actual`.

El método mantiene dos referencias:

- `actual` examina el nodo que podría contener el valor buscado.
- `anterior` conserva el nodo que apunta a `actual`.

Si el dato de `actual` no coincide, ambos avanzan juntos: primero `anterior = actual`, luego `actual = actual.getSiguiente()`. Así, en cada iteración, `anterior` queda justo detrás de `actual`.

Cuando se encuentra el valor, la asignación `anterior.setSiguiente(actual.getSiguiente())` puentea `actual`. Por ejemplo, para eliminar el último nodo `40` de `20 -> 40 -> null`, `anterior` señala `20` y `actual` señala `40`; como `actual.siguiente` es `null`, `20.siguiente` pasa a ser `null`.

En una lista doblemente enlazada, cada nodo también guarda una referencia al anterior. Desde el nodo que se elimina se podría llegar directamente a su predecesor y reconectar ambos lados. En esta lista simple ese enlace hacia atrás no existe, así que hay que conservar el rastro del predecesor durante el recorrido.

## Por qué eliminar `head` es un caso especial

Para el primer nodo no existe un `anterior` dentro de la lista. Por eso el método no puede usar el mismo puente que utiliza para los demás nodos. Primero comprueba si el valor está en `head`; si coincide, ejecuta:

```java
head = head.getSiguiente();
size--;
return true;
```

Si la lista es `10 -> 20 -> 30`, eliminar `10` mueve el punto de entrada de `head` al nodo `20`:

```text
Antes:   head -> 10 -> 20 -> 30 -> null
Después: head ------> 20 -> 30 -> null
```

Esto también funciona cuando hay un solo nodo: su sucesor es `null`, de modo que la nueva cabeza queda en `null` y el tamaño baja a cero. Separar el caso evita buscar un predecesor inexistente y evita dejar `head` apuntando al nodo retirado.

## Cómo se cubren los casos y se mantiene el tamaño

- Si `head == null`, el método devuelve `false` antes de recorrer.
- Si coincide el dato de `head`, se mueve la cabeza, se decrementa `size` una vez y se devuelve `true`.
- Para el resto, el ciclo comprueba `actual != null` antes de consultar sus datos. Al encontrar el valor, puentea el nodo, decrementa `size` una sola vez y retorna `true`.
- Si `actual` llega a `null`, el valor no estaba en la lista. Se devuelve `false` sin cambiar enlaces ni tamaño.

El retorno inmediato después de eliminar significa que, si el valor aparece varias veces, se elimina únicamente la primera aparición.

## Qué muestra `main`

La demostración comienza comprobando que eliminar de una lista vacía devuelve `false`. Luego crea `10 -> 20 -> 30 -> 40` y prueba cada ubicación: al eliminar `10`, la cabeza pasa a `20`; al eliminar `30`, `20` queda enlazado con `40`; al eliminar `40`, el enlace de `20` pasa a `null`. Por último, eliminar `99` devuelve `false` y deja la lista en `20` con tamaño `1`.
