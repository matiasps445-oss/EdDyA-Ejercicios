# Plan técnico: obtener un elemento por posición en una lista enlazada

## Objetivo

Añadir a `ListaEnlazada` el método `int obtener(int posicion)`, que devuelva el dato del nodo en la posición indicada. Aunque se use una posición numérica, la lista no permite acceso directo como `arreglo[posicion]`; para localizar el nodo hay que recorrer los enlaces desde `head`.

## Diseño y compatibilidad

- Mantener la lista enlazada simple con una referencia `head`, un contador `size` y nodos que contienen `dato` y `siguiente`.
- Interpretar `posicion` con índice base cero: la cabeza ocupa la posición `0`, y la posición central de tres elementos es `1`.
- El método debe devolver un `int` y no modificar `head`, los enlaces ni `size`.
- Si la estructura de `Nodo` no ofrece acceso al dato o al siguiente nodo, incorporar los accesores necesarios, por ejemplo `getDato()` y `getSiguiente()`, respetando el encapsulamiento.

## Validación de límites

Antes de iniciar el recorrido, comprobar estrictamente si `posicion < 0 || posicion >= size`. Si cualquiera de las condiciones se cumple, lanzar `IndexOutOfBoundsException` con un mensaje claro que identifique la posición inválida y, de ser útil, el tamaño actual.

La validación debe ocurrir antes de dereferenciar `head` o cualquier nodo. Así se rechazan las posiciones inválidas de forma deliberada, en vez de permitir que el recorrido termine en `null` y produzca un `NullPointerException`.

## Algoritmo de `obtener(int posicion)`

1. Validar la posición contra el intervalo válido `[0, size - 1]` y lanzar `IndexOutOfBoundsException` si queda fuera.
2. Inicializar una referencia auxiliar local `actual` con `head` y un contador local en `0`.
3. Avanzar de nodo en nodo mediante `actual = actual.getSiguiente()` mientras el contador sea menor que `posicion`; incrementar el contador en cada avance.
4. Al terminar, devolver `actual.getDato()`.

El recorrido usa una referencia auxiliar para preservar `head`. Como el índice ya fue validado y la lista mantiene correctamente `size`, al llegar a la posición solicitada `actual` apunta a un nodo válido.

## Casos límite e invariantes

- Lista vacía: `size == 0`, por lo que cualquier posición, incluida `0`, es inválida y debe provocar `IndexOutOfBoundsException`.
- Posición `0` en una lista no vacía: devolver el dato de `head` sin avanzar.
- Posición `size - 1`: recorrer hasta el último nodo y devolver su dato.
- Posición negativa o igual/mayor que `size`: lanzar la excepción antes del recorrido.
- La operación es de solo lectura: `head`, los enlaces y `size` permanecen sin cambios.

## Eficiencia

- **Tiempo:** $O(posicion)$ para una posición válida, y $O(n)$ en el peor caso, cuando se solicita el último nodo de una lista de $n$ elementos. No existe acceso directo por índice.
- **Espacio adicional:** $O(1)$, pues solo se requieren una referencia auxiliar y un contador.

## Prueba de demostración

En `main`:

1. Crear una lista e insertar tres elementos.
2. Llamar a `obtener(1)` y mostrar el dato del medio, verificando que las posiciones comienzan en `0`.
3. Intentar obtener una posición fuera de los límites, por ejemplo `3` cuando hay tres elementos. Capturar `IndexOutOfBoundsException` para mostrar que la validación funciona sin terminar el programa abruptamente.
4. Si se desea, mostrar la lista y su tamaño antes y después para confirmar que `obtener` no alteró la estructura.

## Criterios de aceptación

- Existe `public int obtener(int posicion)` dentro de `ListaEnlazada`.
- La posición se valida antes de recorrer, usando `posicion < 0 || posicion >= size`.
- Una posición válida se resuelve mediante recorrido secuencial desde `head`, con auxiliar y contador; no se usa indexación ni colecciones.
- Se devuelve el dato del nodo correspondiente a la posición base cero.
- Una posición inválida produce `IndexOutOfBoundsException`, no un `NullPointerException` accidental.
- La prueba agrega tres elementos, obtiene la posición `1` y fuerza un error de límites.
- La operación no modifica `head`, los enlaces ni `size`.
