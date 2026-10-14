# Explicación: búsqueda en una lista enlazada

## Arreglos y listas enlazadas: dos formas de llegar a un dato

En un arreglo, los elementos están organizados en posiciones consecutivas y se puede pedir directamente una posición mediante un índice, por ejemplo `arreglo[i]`. La dirección de esa posición se calcula a partir de la dirección inicial y del desplazamiento del índice. Por eso el acceso a una posición concreta es $O(1)$: su costo no depende de cuántos elementos haya.

En la lista enlazada de este ejercicio no hay un índice que conduzca directamente al nodo número `i`. Cada objeto `Nodo` guarda su dato y una referencia al nodo siguiente. Los nodos pueden estar en distintas posiciones de memoria; para llegar a uno posterior hay que seguir los enlaces desde el nodo conocido. Por eso `buscar` realiza un recorrido secuencial y, en el peor caso, compara los $n$ nodos: su tiempo es $O(n)$.

La diferencia no es que la lista no pueda encontrar sus datos, sino que la ruta de acceso se construye enlace por enlace en vez de calcularse directamente con un índice.

## Cómo el ciclo evita acceder a `null`

El método implementado sigue esta estructura:

```java
Nodo actual = head;

while (actual != null) {
    if (actual.getDato() == dato) {
        return true;
    }
    actual = actual.getSiguiente();
}

return false;
```

La condición `actual != null` se verifica antes de ejecutar el cuerpo del ciclo. Mientras sea verdadera, `actual` referencia un nodo válido y se puede llamar a `getDato()` y `getSiguiente()` sobre él.

Al visitar el último nodo, `getSiguiente()` devuelve `null`. La asignación deja `actual` en `null`; antes de comenzar otra iteración, el `while` vuelve a comprobar la condición, la encuentra falsa y sale. Así, el algoritmo no intenta ejecutar `actual.getDato()` cuando `actual` es `null`, que sería lo que provocaría un `NullPointerException`.

Si el dato buscado coincide con alguno de los nodos, el método devuelve `true` inmediatamente. Si se llega al final sin coincidencias, el ciclo termina y se ejecuta `return false`. En una lista vacía, `head` ya es `null`, así que el ciclo no se ejecuta y el resultado es `false`.

## Por qué se recorre con `actual` y se conserva `head`

`head` es la referencia persistente que mantiene a la lista conectada con su primer nodo. Desde allí se puede alcanzar el resto de la cadena. En `buscar`, `Nodo actual = head` copia esa referencia en una variable local: al principio ambas apuntan al mismo primer nodo, pero después solo se modifica `actual`.

Cada instrucción `actual = actual.getSiguiente()` hace avanzar el auxiliar al próximo nodo. No cambia el enlace del nodo anterior ni modifica el valor de `head`. Cuando termina la búsqueda, `head` sigue apuntando al inicio y la lista conserva todos sus nodos.

Si se usara `head` como cursor y se lo avanzara repetidamente, al final quedaría en `null`. La lista habría perdido su punto de entrada: el objeto `ListaEnlazada` ya no podría recorrer desde el primer nodo, aunque los nodos todavía pudieran existir en memoria. Por eso las operaciones de recorrido usan una referencia auxiliar cuando deben inspeccionar la lista sin consumirla ni cambiar su comienzo.

## Qué demuestra el `main`

La prueba primero busca un valor en una lista vacía y obtiene `false`. Luego construye la secuencia `5 -> 10 -> 20`: las búsquedas de `5` y `20` devuelven `true`, mientras que la de `99` devuelve `false`. Finalmente vuelve a imprimir la lista y a mostrar el tamaño. Que el contenido siga siendo `5 -> 10 -> 20` y el tamaño siga en `3` evidencia que buscar solo lee los nodos y no altera la estructura.
