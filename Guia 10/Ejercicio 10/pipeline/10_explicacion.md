# Explicación: lista doblemente enlazada

## El intercambio entre memoria y flexibilidad

Cada `NodoDoble<T>` guarda el dato y dos referencias: `siguiente` y `anterior`. El enlace hacia adelante permite recorrer desde `head`; el enlace hacia atrás permite recorrer desde `tail` en sentido inverso. Así, `imprimirAdelante()` y `imprimirAtras()` pueden comenzar en extremos distintos sin reconstruir el recorrido.

La ventaja tiene un costo: cada nodo almacena una referencia adicional respecto de una lista simplemente enlazada. Para $n$ nodos, se usan $n$ enlaces `anterior` extra, además del dato y de los enlaces `siguiente`. También hay que mantener cada pareja de enlaces coherente al insertar o eliminar. A cambio, recorrer hacia atrás es directo y, cuando ya se conoce el nodo a quitar, se puede modificar su predecesor sin volver desde `head` para localizarlo.

Una precisión importante: `eliminar(T dato)` primero busca el dato recorriendo desde `head`, por lo que esa búsqueda todavía puede costar $O(n)$. La ventaja es que una vez encontrado `actual`, reconectarlo cuesta $O(1)$ y no hace falta llevar otra variable `nodoAnterior` durante el ciclo.

## Cómo se reconecta una eliminación intermedia

Tomemos la cadena:

```text
head -> 10 <-> 20 <-> 30 <- tail
                 ^
               actual
```

Para eliminar `20`, el propio nodo `actual` guarda las referencias a sus dos vecinos: `actual.getAnterior()` apunta a `10` y `actual.getSiguiente()` apunta a `30`. El código ejecuta estas dos asignaciones:

```java
actual.getAnterior().setSiguiente(actual.getSiguiente());
actual.getSiguiente().setAnterior(actual.getAnterior());
```

Primero, el enlace hacia adelante de `10` se actualiza para saltar directamente a `30`. Después, el enlace hacia atrás de `30` se actualiza para volver directamente a `10`:

```text
Antes:   10 <-> 20 <-> 30
Después: 10 <----------> 30
                 20 queda fuera de la cadena
```

Las dos asignaciones son necesarias. Si solo se cambiara el enlace de `10` a `30`, el recorrido hacia atrás desde `30` todavía encontraría el nodo `20`. Si solo se cambiara el enlace de `30` a `10`, el recorrido hacia adelante desde `10` todavía pasaría por `20`. Actualizar ambos lados conserva la simetría de la lista.

Al terminar, `actual` deja de estar enlazado desde la lista. Si no hay otras referencias que apunten a ese nodo, será elegible para el Garbage Collector cuando el método deje de conservar la referencia local; Java no lo libera manualmente ni garantiza que la recolección sea inmediata.

## Por qué no hace falta `nodoAnterior`

En una lista simplemente enlazada, cada nodo solo sabe cuál es el siguiente. Si el recorrido encuentra el nodo que se quiere quitar, no tiene una forma de regresar al anterior, así que se suele conservar una variable auxiliar `anterior` que avance detrás de `actual`.

Aquí cada nodo mantiene también su enlace al predecesor. Cuando `actual` encuentra el dato, `actual.getAnterior()` ya proporciona el nodo previo, y `actual.getSiguiente()` proporciona el posterior. Por ello el algoritmo puede reconectar los dos vecinos directamente desde `actual`, sin una variable retrasada separada.

Esta ventaja se aplica después de encontrar el nodo. La implementación aún recorre desde `head` buscando el primer dato igual con `Objects.equals`; lo que elimina es la necesidad de recordar el nodo anterior durante ese recorrido.

## Casos especiales de la implementación

El primer y último nodo no tienen dos vecinos, así que el código los maneja aparte:

- Si `head == tail`, era el único nodo; se ponen ambos extremos en `null`.
- Si `actual == head`, `head` avanza y el nuevo primer nodo recibe `anterior == null`.
- Si `actual == tail`, `tail` retrocede y el nuevo último nodo recibe `siguiente == null`.
- Solo para un nodo intermedio se aplican las dos reconexiones anteriores.

El tamaño se decrementa una sola vez cuando se encuentra y elimina un nodo. Si el dato no existe, `eliminar` devuelve `false` y la estructura permanece igual.

## Lectura de la prueba

Las inserciones producen primero `30 <-> 20 <-> 10` al insertar tres valores al inicio y luego `30 <-> 20 <-> 10 <-> 40 <-> 50` al agregar dos al final. La impresión hacia atrás comienza en `tail` y muestra exactamente el orden inverso.

La prueba elimina `30` (cabeza), `50` (cola) y `10` (nodo intermedio). El resultado es `20 <-> 40`; impreso desde `tail`, aparece como `40 <-> 20`. Esto comprueba que ambos recorridos y los enlaces restantes siguen consistentes.
