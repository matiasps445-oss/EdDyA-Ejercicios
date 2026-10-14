# Explicación: pila enlazada de enteros

## Por qué `head` debe ser el tope

Una pila LIFO retira primero el último elemento agregado. En esta implementación, `head` apunta al tope y cada nodo enlaza con el elemento que está debajo:

```text
head (tope) -> 30 -> 20 -> 10 -> null (fondo)
```

Al apilar, el nuevo nodo se conecta delante de `head` y luego `head` se actualiza. Al desapilar, se lee el dato de `head` y se mueve `head` al siguiente nodo. Ambas operaciones hacen una cantidad fija de cambios, sin importar cuántos elementos haya: cuestan $O(1)$.

Si el tope estuviera en el final de una lista simplemente enlazada, el costo sería distinto. Para apilar al final sin una referencia a la cola, habría que recorrer desde el comienzo para encontrar el último nodo, lo que cuesta $O(n)$. Desapilar el final también requiere llegar al penúltimo nodo para hacer que su enlace pase a `null`; como cada nodo solo apunta hacia adelante, ese recorrido cuesta $O(n)$. Incluso conservando una referencia a la cola para acelerar el apilamiento, la lista simple no permite encontrar directamente el predecesor al desapilar. Por eso poner el tope en `head` mantiene tanto `apilar` como `desapilar` en tiempo constante.

En `main`, apilar `10`, `20` y `30` deja `30` en `head`. La siguiente operación `desapilar()` devuelve `30`, que fue el último valor agregado: así se observa LIFO.

## Capacidad: arreglo fijo y nodos enlazados

Un arreglo estático creado con una capacidad determinada tiene un límite explícito. Si se llena, no hay una posición disponible para otro dato, y la implementación debe rechazar la inserción o crear otro arreglo más grande.

La pila enlazada no reserva un bloque de tamaño fijo. `apilar` crea un nodo nuevo y lo enlaza; por tanto, no existe una capacidad máxima preestablecida por la estructura. Pero eso no significa capacidad infinita: cada nodo ocupa memoria del heap y la pila sigue limitada por la memoria disponible. Si no se puede reservar más memoria, Java puede lanzar `OutOfMemoryError`.

También conviene distinguir ese límite de `StackOverflowError`: en Java este error normalmente se refiere al agotamiento de la pila de llamadas de un hilo, por ejemplo por recursión demasiado profunda. No es el mismo fenómeno que llenar una pila de datos almacenados en nodos.

## Cómo funciona `contiene` y por qué no es $O(1)$

`contiene(int dato)` empieza en `head` usando una referencia auxiliar `actual`. En cada nodo compara `actual.getDato()` con el valor buscado. Si coinciden, devuelve `true`; si no, avanza con `actual = actual.getSiguiente()`. Si llega a `null` sin coincidencias, devuelve `false`.

La lista no guarda una tabla de posiciones ni un índice que indique dónde está cada valor. Solo se puede llegar a los nodos siguiendo los enlaces. Para un valor que no está presente, o que está cerca del fondo, el método puede revisar los $n$ nodos. Por eso su peor caso es $O(n)$ y no puede garantizarse un tiempo constante para cualquier búsqueda. Si el dato está justo en `head`, termina en el primer paso ($O(1)$), pero ese caso favorable no cambia el costo del peor caso.

Como los datos de esta pila son `int`, la comparación `==` compara los valores enteros. La búsqueda es de solo lectura: no modifica `head`, los enlaces ni `size`.

## Pila vacía y excepciones

`estaVacia()` comprueba si `head == null`. Si se llama a `desapilar()` o `consultarTope()` en ese estado, no existe un nodo cuyo dato se pueda leer; por eso ambos métodos lanzan `EmptyStackException` antes de acceder al nodo. `contiene` en una pila vacía recorre cero nodos y devuelve `false`.
