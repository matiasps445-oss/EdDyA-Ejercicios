# Plan técnico: eliminar la primera aparición de un valor

## Objetivo

Añadir a `ListaEnlazada` el método `boolean eliminar(int dato)`. Debe buscar y desconectar la primera aparición del valor indicado, devolver `true` si la elimina y `false` si no existe. La estructura debe mantener correctamente `head`, los enlaces y `size`.

## Diseño y compatibilidad

- Mantener la lista simplemente enlazada: cada `Nodo` contiene `dato` y una referencia `siguiente`; `ListaEnlazada` conserva `head` y `size`.
- Para el recorrido, utilizar referencias auxiliares locales. Si un nodo coincide, ajustar enlaces para que la cadena desde `head` ya no pase por él.
- No utilizar colecciones ni operaciones de liberación manual de memoria.

## Algoritmo de `eliminar(int dato)`

1. **Lista vacía:** si `head == null`, devolver `false` inmediatamente; no hay nodo que buscar y `size` permanece en cero.
2. **Eliminar el primer nodo:** si `head.getDato() == dato`, guardar o utilizar el enlace al siguiente nodo para asignar `head = head.getSiguiente()`, reducir `size` una vez y devolver `true`. Este caso es especial porque no hay un nodo anterior a `head`.
3. **Buscar en el resto:** inicializar `anterior` en `head` y `actual` en `head.getSiguiente()`. Mientras `actual != null`, comparar su dato con el buscado.
4. Si coinciden, guardar el enlace de `actual` en `anterior` mediante `anterior.setSiguiente(actual.getSiguiente())`. Así se puentea el nodo que se elimina y se conserva el resto de la lista. Reducir `size` una vez y devolver `true`.
5. Si no coinciden, avanzar ambas referencias en orden: `anterior = actual` y `actual = actual.getSiguiente()`.
6. **Dato inexistente:** si `actual` llega a `null`, devolver `false` sin cambiar enlaces ni tamaño.

Al devolver inmediatamente después de eliminar, el método elimina solo la primera aparición si hay valores duplicados.

## Memoria y recolector de basura

En Java no se llama a `free` para destruir manualmente el nodo. Eliminar consiste en actualizar el enlace de la lista para que el nodo ya no sea alcanzable desde `head`. Una vez desconectado, si no queda ninguna otra referencia a ese nodo, el objeto queda elegible para que el Garbage Collector recupere su memoria cuando corresponda. No se garantiza que la recuperación ocurra inmediatamente; si existen referencias externas, el nodo puede seguir siendo alcanzable desde ellas.

## Invariantes y casos límite

- `size` disminuye exactamente una vez solo cuando se elimina un nodo.
- Si se elimina el `head`, la nueva cabeza es el antiguo segundo nodo; eliminar el único nodo deja `head == null` y `size == 0`.
- Si se elimina un nodo intermedio o el último, `anterior` pasa a apuntar al sucesor de `actual`; si era el último, ese sucesor es `null`.
- Si el valor no existe, `head`, todos los enlaces y `size` permanecen intactos.
- El recorrido debe comprobar `actual != null` antes de leer el dato o su enlace.

## Eficiencia

- **Tiempo:** $O(n)$ en el peor caso, porque puede ser necesario recorrer todos los nodos; eliminar el `head` es $O(1)$.
- **Espacio adicional:** $O(1)$, utilizando solo las referencias auxiliares `anterior` y `actual`.

## Prueba de demostración

En `main`:

1. Crear una lista y agregar `10`, `20`, `30` y `40`, en ese orden.
2. Intentar eliminar `10` (cabeza), imprimir el resultado y la lista.
3. Intentar eliminar `30` (nodo intermedio), imprimir el resultado y la lista.
4. Intentar eliminar `40` (último nodo), imprimir el resultado y la lista.
5. Intentar eliminar `99` (inexistente), imprimir `false` y volver a imprimir la lista, que debe permanecer sin cambios.

La secuencia esperada de contenidos después de cada eliminación exitosa es `20 -> 30 -> 40`, luego `20 -> 40`, y luego `20`. La última búsqueda no debe cambiar ese contenido.

## Criterios de aceptación

- Existe `public boolean eliminar(int dato)` y devuelve `true` al eliminar la primera coincidencia y `false` si no existe.
- Se cubren lista vacía, eliminación de `head`, eliminación intermedia, eliminación del último nodo y valor inexistente.
- Los nodos distintos del eliminado siguen conectados correctamente.
- `size` solo se reduce una vez cuando la eliminación tiene éxito.
- La demostración usa los valores `10`, `20`, `30`, `40`, y prueba eliminar `10`, `30`, `40` y `99`, imprimiendo la lista tras cada paso.
- No se intenta liberar memoria manualmente; el nodo desconectado queda sujeto a las reglas del recolector de basura de Java.
