# Explicación: lista enlazada simple

## Las dos clases y su relación

La lista puede imaginarse como una cadena de eslabones. Cada objeto `Nodo` es un eslabón individual: guarda un entero en `dato` y una referencia en `siguiente` que permite llegar al siguiente nodo. El último nodo tiene `siguiente == null`, lo que marca el final de la cadena.

`ListaEnlazada` no almacena los valores en un arreglo ni en una colección. Administra la cadena manteniendo `head`, que apunta a su primer nodo, y `size`, que registra cuántos nodos hay. En otras palabras, `Nodo` describe cada pieza; `ListaEnlazada` conserva el punto de entrada y ofrece las operaciones para enlazar y recorrer esas piezas.

Por ejemplo, después de insertar `5`, `10` y `20`, los enlaces representan:

```text
head -> [5 | siguiente] -> [10 | siguiente] -> [20 | null]
```

Las variables que hacen referencia a un nodo no contienen una copia de todo el nodo: permiten acceder a ese objeto. El enlace de cada nodo es lo que hace posible continuar por la cadena.

## Por qué `head` es crítico

`head` es la única referencia persistente de la lista hacia el primer nodo. A partir de él se pueden seguir todos los enlaces y alcanzar los demás nodos. También permite distinguir el estado vacío: si `head == null`, no hay primer nodo y la lista está vacía.

Si se perdiera o sobrescribiera accidentalmente el valor de `head`, la lista dejaría de tener su punto de entrada. Aunque los nodos pudieran seguir existiendo temporalmente en memoria, ya no habría desde la lista una referencia para encontrarlos; sus datos y enlaces serían inaccesibles para las operaciones. El recolector de basura de Java podría liberar esos objetos cuando ya no existieran otras referencias a ellos. Además, si solo se cambiara `head` sin actualizar correctamente la estructura, `size` podría dejar de coincidir con los nodos alcanzables.

Por eso las operaciones deben preservar la referencia de entrada. En particular, recorrer la lista no debe avanzar directamente `head`.

## Cómo funciona `insertarAlFinal`

El método crea primero `nuevoNodo`; su constructor deja `siguiente` en `null`, apropiado para el futuro último nodo.

- **Lista vacía:** si `head == null`, el nodo nuevo se asigna directamente a `head`. No hay nodos que recorrer.
- **Lista con elementos:** se crea la referencia local `actual` con el valor de `head`. `actual` apunta inicialmente al primer nodo, pero moverla no mueve ni cambia `head`.
- El ciclo `while (actual.siguiente != null)` pregunta si el nodo actual tiene otro nodo después. Si lo tiene, la asignación `actual = actual.siguiente` avanza el auxiliar un eslabón.
- Cuando el ciclo termina, `actual` apunta al último nodo: su referencia `siguiente` es `null`.
- La asignación `actual.siguiente = nuevoNodo` enlaza el nodo nuevo después del último. Como el nuevo nodo conserva `siguiente == null`, pasa a ser el último.
- Finalmente, `size++` registra que se agregó un nodo.

Ejemplo con `head -> 5 -> 10 -> null`: `actual` empieza en `5`, avanza a `10` y se detiene porque `10.siguiente` es `null`. Después se enlaza el nodo nuevo:

```text
head -> 5 -> 10 -> 20 -> null
```

El punto importante es que `actual` es un puntero auxiliar (una variable local de referencia), no una sustitución de `head`. Al terminar, `head` sigue apuntando al nodo `5`, de modo que la lista completa continúa accesible desde su comienzo.

## Comparación de eficiencia

La notación $O(1)$ describe un tiempo constante: el número de acciones no aumenta con la cantidad de nodos. La notación $O(n)$ describe un tiempo que crece proporcionalmente al número de nodos, donde $n$ es el tamaño de la lista.

- **`insertarAlInicio(int dato)`: $O(1)$.** Crea un nodo, enlaza su `siguiente` con el `head` anterior, actualiza `head` y aumenta `size`. No importa si la lista contiene uno o un millón de nodos: no necesita recorrerlos.
- **`insertarAlFinal(int dato)`: $O(n)$.** Como la clase solo guarda `head` y no conserva una referencia a la cola, necesita avanzar desde el primer nodo hasta el último para encontrar dónde enlazar el nuevo. En el caso de una lista no vacía, el recorrido puede visitar todos los nodos. Para la lista vacía el caso particular es $O(1)$, pero el costo general se expresa como $O(n)$.

Esta diferencia surge de la información que la estructura mantiene: conocer solo el primer nodo hace rápida la inserción al inicio, pero obliga a buscar el último para insertar al final. Mantener también una referencia a la cola podría hacer constante esa inserción, pero no forma parte del diseño solicitado.

## Lectura de las otras operaciones

- **`imprimir()`:** usa otra variable local (`actual`) para visitar cada nodo desde `head` hasta `null`, mostrando los datos en orden. Si la lista está vacía, informa ese estado y termina. Su tiempo es $O(n)$ porque debe visitar todos los nodos.
- **`estaVacia()`:** retorna el resultado de comparar `head` con `null`. Su tiempo es $O(1)$.
- **`getSize()`:** devuelve el contador `size`, sin recorrer nodos. Su tiempo es $O(1)$; esto es posible porque las inserciones actualizan el contador.

## Qué verifica `main`

La demostración empieza con una lista vacía de tamaño `0`. Inserta `10` al final para ejercitar el caso de `head == null`, luego agrega `5` al inicio y `20` al final. Los tamaños mostrados deben avanzar de `0` a `1`, `2` y `3`. Finalmente, la impresión `5 -> 10 -> 20` confirma que los enlaces conservan el orden esperado y que la lista ya no está vacía.
