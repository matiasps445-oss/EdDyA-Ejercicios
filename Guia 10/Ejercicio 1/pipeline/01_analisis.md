# Plan técnico: pila enlazada de enteros

## Objetivo

Implementar en Java una pila LIFO de números enteros construida desde cero con nodos enlazados. No usar `Stack`, `LinkedList` ni otras colecciones de Java para almacenar los elementos.

## Diseño de clases y estado

- **`Nodo`:** contiene un entero `dato` y una referencia `siguiente` al nodo debajo de él; el último nodo de la cadena apunta a `null`.
- **`PilaEnteros`:** administra los nodos mediante una referencia privada `head` y un contador `size`.
- `head` representa siempre el tope (`top`) de la pila. La cadena enlazada desde `head` hacia `null` representa los elementos desde el tope hasta el fondo.
- Una pila vacía cumple `head == null` y `size == 0`.

## Operaciones

- **`apilar(int dato)`:** crear un nodo nuevo, enlazar su `siguiente` con el `head` actual y luego actualizar `head` para que apunte al nodo nuevo. Incrementar `size` una vez. Complejidad: $O(1)$.
- **`desapilar()`:** si la pila está vacía, lanzar `EmptyStackException`. En otro caso, guardar el dato de `head`, avanzar `head` a `head.getSiguiente()`, decrementar `size` una vez y devolver el dato retirado. Complejidad: $O(1)$.
- **`consultarTope()`:** si la pila está vacía, lanzar `EmptyStackException`; de lo contrario, devolver el dato de `head` sin cambiar enlaces ni tamaño. Complejidad: $O(1)$.
- **`estaVacia()`:** devolver si `head == null`. Complejidad: $O(1)$.
- **`contiene(int dato)`:** recorrer desde `head` con una referencia auxiliar y comparar los datos hasta encontrar una coincidencia o llegar a `null`. Devolver `true` si aparece y `false` si no. No modificar la pila. Complejidad: $O(n)$ en el peor caso.
- **`imprimir()`:** recorrer desde `head` hasta `null` e imprimir los valores en orden de tope a fondo. Para una pila vacía, mostrar un mensaje claro. Complejidad: $O(n)$.

Para `EmptyStackException`, utilizar `java.util.EmptyStackException`; es una excepción de la biblioteca estándar, no una colección.

## Invariantes y casos límite

- El nodo apuntado por `head` es siempre el elemento más recientemente apilado que todavía no fue desapilado.
- Apilar conserva todos los nodos previos enlazándolos detrás del nuevo tope.
- Desapilar solo elimina el nodo del tope; el nuevo `head` es el siguiente nodo, o `null` si se retiró el único elemento.
- `size` aumenta una vez por cada `apilar` y disminuye una vez por cada `desapilar` exitoso.
- `consultarTope()` no retira el elemento ni modifica `size`.
- `contiene` e `imprimir` son operaciones de lectura y no modifican el estado.
- `desapilar` y `consultarTope` lanzan `EmptyStackException` cuando `head == null`; `contiene` sobre una pila vacía devuelve `false`.

## Eficiencia

Insertar y retirar desde `head` evita recorrer la cadena, por lo que `apilar` y `desapilar` cuestan $O(1)$. Consultar el tope y verificar si está vacía también cuestan $O(1)$. Buscar un valor o imprimir todos los elementos requiere recorrer hasta $n$ nodos y cuesta $O(n)$.

## Prueba de demostración en `main`

1. Crear una pila vacía y, opcionalmente, mostrar que está vacía.
2. Apilar tres enteros, por ejemplo `10`, `20` y `30`, en ese orden.
3. Mostrar el contenido para comprobar que se imprime desde el tope: `30`, `20`, `10`.
4. Consultar el tope y comprobar que es `30`.
5. Buscar un valor existente, por ejemplo `20`, y mostrar `true`.
6. Desapilar y mostrar el valor retirado, que debe ser `30`; después, el nuevo tope debe ser `20`.

## Criterios de aceptación

- La estructura se construye con `Nodo` y referencias, sin delegar el almacenamiento a una colección.
- `head` representa el tope de la pila y el comportamiento es LIFO.
- Están implementadas `apilar`, `desapilar`, `consultarTope`, `estaVacia`, `contiene` e `imprimir`.
- `apilar` y `desapilar` son operaciones $O(1)$ y actualizan `size` correctamente.
- `desapilar` y `consultarTope` lanzan `EmptyStackException` cuando la pila está vacía.
- `main` apila tres valores, consulta el tope, busca un valor existente y desapila un elemento, mostrando resultados claros.
