# Plan técnico: invertir una lista enlazada simple

## Objetivo

Añadir a `ListaEnlazada` el método `void invertir()` para invertir la dirección de todos los enlaces de la lista en sitio. El último nodo original debe convertirse en `head` y el primero debe quedar como último, con `siguiente == null`.

## Algoritmo con tres referencias

Usar las referencias auxiliares `anterior`, `actual` y `siguiente`:

1. Inicializar `anterior` en `null` y `actual` en `head`.
2. Iterar mientras `actual != null`.
3. Dentro de cada iteración, ejecutar las operaciones en este orden estricto:
   - Guardar el enlace que todavía apunta al resto de la lista: `siguiente = actual.getSiguiente()`.
   - Invertir el enlace del nodo actual: `actual.setSiguiente(anterior)`.
   - Avanzar el segmento invertido y el segmento por procesar: `anterior = actual` y `actual = siguiente`.
4. Al terminar el ciclo, asignar `head = anterior`. En ese punto `actual` es `null` y `anterior` señala el antiguo último nodo, ahora primero.

No invertir el orden de las dos primeras operaciones: si se reemplaza `actual.siguiente` antes de guardar su enlace original, se pierde desde el recorrido el acceso al resto aún no procesado.

## Invariantes y casos límite

- Al inicio de cada iteración, `anterior` es la cabeza del tramo ya invertido y `actual` es el primer nodo del tramo todavía por procesar.
- `siguiente` conserva temporalmente la entrada al resto de la lista antes de modificar el enlace de `actual`.
- El primer nodo original termina apuntando a `null`, porque `anterior` empieza en `null` cuando se invierte ese nodo.
- Lista vacía: `actual` empieza en `null`, no se ejecuta el ciclo y `head = anterior` conserva `head == null`.
- Un solo nodo: queda apuntando a `null` y continúa siendo la cabeza.
- Invertir cambia enlaces y `head`, pero no agrega ni elimina nodos; `size` debe permanecer igual.

## Eficiencia

- **Tiempo:** $O(n)$, porque cada nodo se procesa una sola vez.
- **Espacio adicional:** $O(1)$, porque se reutilizan los mismos nodos y solo se ocupan tres referencias auxiliares.

## Prueba de demostración

En `main`:

1. Crear una lista con `10`, `20`, `30` y `40`, en ese orden.
2. Imprimirla antes de invertir: `10 -> 20 -> 30 -> 40`.
3. Ejecutar `invertir()`.
4. Imprimirla nuevamente y comprobar el resultado: `40 -> 30 -> 20 -> 10`.
5. Si se muestra `size`, verificar que conserva el valor `4`.

## Criterios de aceptación

- Existe `public void invertir()` y utiliza las referencias `anterior`, `actual` y `siguiente`.
- `anterior` empieza en `null`, `actual` empieza en `head` y el ciclo continúa mientras `actual != null`.
- Cada iteración guarda primero `actual.getSiguiente()`, invierte después el enlace y finalmente avanza las referencias.
- Al finalizar, `head` apunta a `anterior`.
- No se pierde ningún nodo y `size` no cambia.
- `main` imprime la lista `10 -> 20 -> 30 -> 40` antes y `40 -> 30 -> 20 -> 10` después.
