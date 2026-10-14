# Explicación: eliminar un nodo por posición

## Por qué el recorrido llega hasta `posicion - 1`

Para eliminar un nodo de una lista simplemente enlazada, hay que cambiar el enlace del nodo que está antes del objetivo. El ciclo del código mantiene `anterior` y lo deja exactamente en esa posición:

```java
Nodo anterior = head;
for (int indice = 0; indice < posicion - 1; indice++) {
    anterior = anterior.getSiguiente();
}
```

Las posiciones empiezan en cero. Si se quiere borrar la posición `2` de `10 -> 20 -> 30 -> 40`, el objetivo es `30` y el nodo anterior es `20`, ubicado en la posición `1`. El ciclo avanza desde `head` solo hasta ese predecesor. La expresión `indice < posicion - 1` hace que termine con `anterior` en `20`.

Para `posicion == 1`, el ciclo no repite: `anterior` ya apunta al nodo de posición `0`, que precede al objetivo. Para posiciones mayores, cada repetición mueve `anterior` un enlace.

Si el recorrido se detuviera en `posicion`, `anterior` apuntaría al nodo que queremos eliminar. Entonces ya no tendríamos a mano el nodo previo cuyo enlace debe cambiarse. Si aplicáramos la expresión de puenteo usando ese nodo como `anterior`, se modificaría el enlace siguiente al objetivo y se eliminaría el nodo posterior, no el solicitado. En una lista simple no se puede retroceder desde el objetivo porque cada nodo solo conoce a su sucesor.

## Qué hace el puenteo de referencias

La instrucción es:

```java
anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
```

Se puede leer de adentro hacia afuera:

1. `anterior.getSiguiente()` obtiene el nodo objetivo.
2. El segundo `.getSiguiente()` obtiene el nodo que viene después del objetivo.
3. `anterior.setSiguiente(...)` reemplaza el enlace del predecesor para que apunte directamente a ese sucesor.

Con `10 -> 20 -> 30 -> 40`, para borrar `30`, `anterior` apunta a `20`. Antes del puente:

```text
head -> 10 -> 20 -> 30 -> 40 -> null
                 ^     ^
             anterior  objetivo
```

El sucesor del objetivo es `40`, así que se asigna `20.siguiente = 40`:

```text
head -> 10 -> 20 ------> 40 -> null
                       30 queda fuera del recorrido desde head
```

Si el objetivo es el último nodo, su sucesor es `null`; el enlace del anterior pasa a `null`, terminando la lista en el nodo correcto.

## Por qué la posición `0` se maneja aparte

No hay ningún nodo anterior a `head`. Por eso el caso `posicion == 0` no puede usar el puenteo general: debe cambiar el propio punto de entrada de la lista.

```java
if (posicion == 0) {
    head = head.getSiguiente();
} else {
    // localizar anterior y puentear el objetivo
}
```

Si la lista es `10 -> 20 -> 30`, al quitar la posición `0`, la asignación mueve `head` a `20` y la lista queda `20 -> 30`. Si solo había un nodo, su sucesor es `null`, por lo que `head` pasa a `null`.

## Por qué `size` debe mantenerse sincronizado

`size` representa la cantidad de nodos alcanzables desde `head`. Después de una eliminación válida, el método ejecuta `size--` exactamente una vez; la validación ocurre antes y la excepción impide modificar el estado si la posición es inválida.

Ese contador también permite validar posiciones sin recorrer toda la lista: la condición del método rechaza `posicion < 0` o `posicion >= size`. Si `size` quedara más grande que la cantidad real de nodos, podría aceptar una posición que no existe y el recorrido llegaría a `null` antes de puentear. Si quedara más pequeño, podría rechazar una posición válida. Mantener el contador preciso protege las operaciones basadas en límites, como obtener o eliminar por posición, y hace que `getSize()` reporte el estado real.

## Seguimiento de la prueba en `main`

La prueba inicia con cuatro elementos:

```text
head -> 10 -> 20 -> 30 -> 40 -> null
size = 4
```

1. Al eliminar la posición `0`, `head` avanza a `20`. Queda `20 -> 30 -> 40` y `size` vale `3`.
2. Al eliminar la posición `1`, el ciclo deja `anterior` en `20` y puentea `30`. Queda `20 -> 40` y `size` vale `2`.
3. La última posición actual se calcula como `getSize() - 1`, que vale `1`. Al eliminarla, `20.siguiente` queda en `null`. La lista final es `20` y `size` vale `1`.

Así, la demostración ejecuta correctamente la eliminación de la cabeza, un nodo intermedio y la última posición recalculada luego de las eliminaciones anteriores.
