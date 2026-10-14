# Plan técnico: contar ocurrencias de un valor

## Objetivo

Añadir a `ListaEnlazada` el método `int contarOcurrencias(int dato)`. Debe recorrer todos los nodos y devolver cuántas veces aparece el valor indicado, sin modificar la estructura ni interrumpir el recorrido en la primera coincidencia.

## Algoritmo de `contarOcurrencias(int dato)`

1. Crear una variable entera `contador` inicializada en `0`.
2. Crear una referencia auxiliar `actual` iniciada en `head`.
3. Recorrer con un ciclo cuya condición sea `actual != null`.
4. En cada nodo, comparar `actual.getDato()` con `dato`. Si son iguales, incrementar `contador` en `1`.
5. Avanzar siempre al siguiente nodo con `actual = actual.getSiguiente()`, tanto si hubo coincidencia como si no.
6. Solo cuando `actual` llegue a `null`, terminar el ciclo y devolver `contador`.

No usar `return` ni `break` dentro del ciclo al encontrar una coincidencia: pueden existir más nodos con el mismo dato y todos deben contarse. El auxiliar permite recorrer desde `head` sin modificar la referencia de entrada.

## Casos e invariantes

- Lista vacía (`head == null`): el ciclo no se ejecuta y el resultado es `0`.
- Sin coincidencias: se visitan todos los nodos y se devuelve `0`.
- Una o varias coincidencias: se incrementa el contador una vez por nodo coincidente y se sigue recorriendo hasta el final.
- El método es de solo lectura: no modifica `head`, los enlaces, los datos ni `size`.

## Eficiencia

- **Tiempo:** $O(n)$ para una lista de $n$ nodos, ya que es obligatorio inspeccionar todos, aunque se hayan encontrado coincidencias.
- **Espacio adicional:** $O(1)$, mediante una referencia auxiliar y un contador.

## Prueba de demostración

En `main`, construir exactamente la lista:

```text
10 -> 20 -> 10 -> 30 -> 10
```

Llamar a `contarOcurrencias(10)` y mostrar un mensaje claro con el resultado esperado, `3`. La prueba debe demostrar que se cuentan las tres apariciones, incluidas las que están después de la primera coincidencia.

## Criterios de aceptación

- Existe `public int contarOcurrencias(int dato)`.
- El método inicializa el contador en cero y recorre desde `head` mientras `actual != null`.
- Incrementa el contador solo cuando `actual.getDato() == dato`.
- Avanza al siguiente nodo en cada iteración y devuelve el resultado después de recorrer la lista completa.
- No corta el ciclo al encontrar la primera coincidencia y no modifica la lista.
- `main` crea `10 -> 20 -> 10 -> 30 -> 10`, cuenta el valor `10` e imprime `3`.
