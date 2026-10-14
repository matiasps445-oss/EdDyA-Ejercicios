# Explicación: insertar un nodo en una posición específica

## El orden de enlaces evita perder la lista

Supongamos que la lista contiene `10 -> 20 -> 30 -> null` y queremos insertar `15` en la posición `1`. El nodo anterior es el que contiene `10`; el nodo que actualmente le sigue es `20`. Al crear `nuevo`, su referencia `siguiente` empieza en `null`.

El código conecta los nodos en este orden:

```java
nuevo.setSiguiente(anterior.getSiguiente());
anterior.setSiguiente(nuevo);
```

Primero, `nuevo.setSiguiente(anterior.getSiguiente())` hace que `nuevo` apunte al `20`. Así, el camino desde `nuevo` hacia los nodos que ya estaban después queda preservado:

```text
anterior (10) -----> 20 -----> 30 -----> null
nuevo (15) --------> 20 -----> 30 -----> null
```

Luego, `anterior.setSiguiente(nuevo)` cambia el enlace de `10` para que apunte a `15`:

```text
head -> 10 -> 15 -> 20 -> 30 -> null
```

### Qué pasa si se invierten los pasos

Si primero ejecutáramos `anterior.setSiguiente(nuevo)`, el enlace que llevaba de `10` a `20` se sobrescribiría con una referencia a `15`. Como `nuevo.siguiente` todavía es `null`, la estructura accesible desde `head` quedaría así:

```text
head -> 10 -> 15 -> null

20 -> 30 -> null   (desconectados del comienzo de la lista)
```

En ese momento ya no queda, desde `head` ni desde `anterior`, una referencia que permita llegar a `20` y `30`. En Java, esas asignaciones cambian referencias a objetos; no mueven físicamente los nodos. Los objetos `20` y `30` podrían seguir existiendo mientras alguna otra referencia los conserve, pero la lista ya no podría alcanzarlos y eventualmente el recolector de basura podría liberarlos. Guardar primero el enlace antiguo en `nuevo.siguiente` evita perder el acceso al resto de la cadena.

## Por qué el inicio se trata aparte

Cuando `posicion == 0`, no existe un nodo anterior dentro de la lista. El nuevo nodo debe convertirse en el punto de entrada, por lo que el método realiza:

```java
nuevo.setSiguiente(head);
head = nuevo;
```

Primero enlaza el nuevo nodo con la antigua cabeza; después actualiza `head`. Para la lista `10 -> 20`, el resultado de insertar `5` en la posición `0` es `head -> 5 -> 10 -> 20`.

Para cualquier posición mayor que cero, la cabeza debe seguir siendo el primer nodo existente. El método crea `anterior = head` y avanza con el ciclo hasta el nodo de índice `posicion - 1`. Desde allí enlaza el nodo nuevo con el sucesor y después conecta `anterior` con el nuevo nodo. En la posición `1`, el ciclo no necesita avanzar: `head` ya es el nodo anterior. En posiciones más lejanas, avanza por los enlaces hasta llegar al anterior correcto.

La inserción al final también usa este segundo camino cuando la lista no está vacía: se recorre hasta el último nodo, cuyo `siguiente` es `null`, y el nuevo nodo queda al final. Si la lista está vacía, `posicion` solo puede ser `0`, así que se utiliza el caso de inicio.

## Por qué `posicion == size` es válido

`obtener(posicion)` busca un nodo que ya existe. Si hay `size` nodos, sus posiciones son `0` a `size - 1`; por eso `posicion == size` está fuera del rango para obtener un elemento.

Insertar representa una operación distinta: se inserta en un espacio entre nodos, antes de uno de ellos o después del último. Una lista de `size` nodos tiene `size + 1` posiciones válidas de inserción:

```text
posiciones:  0       1       2       3
lista:      | 10 |   | 20 |   | 30 |
```

En esa lista de tres elementos, la posición `3` es el espacio que queda después de `30`, por lo que insertar en `posicion == size` equivale a anexar al final. Por eso la validación rechaza `posicion < 0` o `posicion > size`, pero acepta la igualdad.

## Validación y tamaño

La comprobación de límites ocurre antes de crear o enlazar el nodo. Una posición negativa o mayor que `size` produce `IndexOutOfBoundsException` y deja intactos `head`, los enlaces y `size`. Tras una inserción válida, `size++` se ejecuta una sola vez para reflejar el nuevo nodo.

La demostración de `main` inserta nodos al inicio, en la posición final (`size`) y en la posición `1`, imprimiendo la lista tras cada operación. Luego intenta insertar en `size + 1` y captura la excepción para mostrar el error sin terminar abruptamente.
