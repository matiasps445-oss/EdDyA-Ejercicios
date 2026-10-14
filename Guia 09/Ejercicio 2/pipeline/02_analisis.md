# Plan técnico: buscar un elemento en una lista enlazada

## Objetivo

Añadir a la lista enlazada simple de enteros el método `boolean buscar(int dato)`. La búsqueda debe recorrer secuencialmente los nodos desde `head`, sin usar índices ni colecciones de Java, y devolver si el valor está presente.

## Diseño y compatibilidad

- Mantener el modelo existente: cada `Nodo` guarda un entero `dato` y una referencia `siguiente`; `ListaEnlazada` conserva `head` y `size`.
- El recorrido solicitado debe avanzar mediante `getSiguiente()`. Si `Nodo` todavía no ofrece ese método, agregar un accesor que devuelva la referencia `siguiente` sin modificarla.
- El método `buscar` debe ser público y devolver un valor booleano. No debe cambiar `head`, los enlaces ni `size`.

## Algoritmo de `buscar(int dato)`

1. Crear una referencia auxiliar local, por ejemplo `actual`, iniciada con `head`.
2. Mientras `actual != null`, comparar el dato del nodo actual con el valor buscado.
3. Si coinciden, devolver `true` inmediatamente.
4. Si no coinciden, avanzar el auxiliar con `actual = actual.getSiguiente()`.
5. Si el ciclo termina porque `actual` llegó a `null`, devolver `false`.

El auxiliar permite visitar la cadena sin mover `head`. Cada avance sigue el enlace del nodo actual; la búsqueda no necesita ni debe acceder mediante un índice numérico.

## Casos límite e invariantes

- Lista vacía (`head == null`): el ciclo no se ejecuta y se devuelve `false`.
- Coincidencia en el primer nodo: devolver `true` en la primera comparación.
- Coincidencia en un nodo intermedio o en el último: avanzar hasta él y devolver `true`.
- Valor ausente: recorrer todos los nodos, alcanzar `null` y devolver `false`.
- La búsqueda es de solo lectura: al terminar, `head`, todos los enlaces y `size` conservan sus valores.

## Eficiencia

- **Tiempo:** $O(n)$ en el peor caso, donde $n$ es el número de nodos, porque puede ser necesario inspeccionarlos todos. Si el primer nodo coincide, el caso favorable es $O(1)$.
- **Espacio adicional:** $O(1)$, porque solo se usa una referencia auxiliar, independientemente del tamaño de la lista.

## Prueba de demostración

En `main`, crear una lista con varios enteros y comprobar mediante impresiones claras:

- un valor presente en el primer nodo devuelve `true`;
- un valor presente en un nodo posterior devuelve `true`;
- un valor ausente devuelve `false`;
- la búsqueda en una lista vacía devuelve `false`.

Después de las búsquedas, mostrar o comprobar que el tamaño y la impresión de la lista no cambiaron.

## Criterios de aceptación

- `ListaEnlazada` implementa `public boolean buscar(int dato)` con el recorrido secuencial descrito.
- La búsqueda empieza en `head`, avanza con `getSiguiente()` y compara el dato de cada nodo.
- Devuelve `true` en la primera coincidencia y `false` al llegar a `null` sin encontrarla.
- Si falta, `Nodo` incorpora `getSiguiente()` para exponer el enlace sin alterar la estructura.
- No se usa indexación ni colecciones de Java.
- La búsqueda no modifica `head`, enlaces ni `size`.
- La demostración cubre lista vacía, coincidencia y ausencia de valor.
