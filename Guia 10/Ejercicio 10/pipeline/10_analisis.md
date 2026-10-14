# Plan técnico: lista doblemente enlazada genérica

## Objetivo

Implementar una lista doblemente enlazada genérica en Java. Cada nodo debe enlazar al siguiente y al anterior para permitir recorridos en ambas direcciones y eliminar nodos reconectando los dos lados.

## Diseño de clases y estado

- **`NodoDoble<T>`:** contiene un dato `T`, una referencia `siguiente` y una referencia `anterior`.
- **`ListaDoblementeEnlazada<T>`:** administra los nodos mediante dos referencias privadas: `head` al primer nodo y `tail` al último. Mantener un `size` entero para reflejar la cantidad de nodos.
- Lista vacía: `head == null`, `tail == null` y `size == 0`.
- Lista con un nodo: `head == tail`; el nodo tiene ambos enlaces en `null`.
- Para comparar valores genéricos durante la eliminación, utilizar `Objects.equals` en lugar de `==`.

## Operaciones

- **`insertarAlInicio(T dato)`:** crear un nodo con `anterior == null` y `siguiente == head`. Si la lista estaba vacía, asignarlo también a `tail`; si no, asignar `head.anterior` al nodo nuevo. Finalmente, actualizar `head` e incrementar `size`. Complejidad: $O(1)$.
- **`insertarAlFinal(T dato)`:** crear un nodo con `siguiente == null` y `anterior == tail`. Si la lista estaba vacía, asignarlo también a `head`; si no, enlazar `tail.siguiente` con el nodo nuevo. Finalmente, actualizar `tail` e incrementar `size`. Complejidad: $O(1)$.
- **`imprimirAdelante()`:** recorrer desde `head` usando `siguiente` hasta `null` e imprimir los datos en orden. Complejidad: $O(n)$.
- **`imprimirAtras()`:** recorrer desde `tail` usando `anterior` hasta `null` e imprimir los datos en orden inverso. Complejidad: $O(n)$.
- **`eliminar(T dato)`:** buscar desde `head` el primer nodo cuyo dato sea igual mediante `Objects.equals`. Si no se encuentra, devolver `false` sin cambiar la lista. Si se encuentra, tratar según su posición:
  - Lista con un solo nodo (`head == tail`): asignar ambos extremos a `null`.
  - Primer nodo: mover `head` al sucesor y dejar `head.anterior == null`.
  - Último nodo: mover `tail` al predecesor y dejar `tail.siguiente == null`.
  - Nodo intermedio: enlazar el anterior con el siguiente en ambas direcciones: `actual.anterior.siguiente = actual.siguiente` y `actual.siguiente.anterior = actual.anterior`.
  - Decrementar `size` una sola vez y devolver `true`.

La búsqueda de `eliminar` cuesta $O(n)$ en el peor caso; una vez localizado el nodo, reconectar los enlaces cuesta $O(1)$.

## Invariantes de enlaces

- `head.anterior == null` cuando la lista no está vacía.
- `tail.siguiente == null` cuando la lista no está vacía.
- Para dos nodos vecinos `A` y `B`, si `A.siguiente == B`, entonces `B.anterior == A`.
- Tras cualquier operación, `head`, `tail`, los enlaces y `size` deben describir la misma secuencia de nodos.
- Insertar o eliminar un nodo debe actualizar los dos enlaces afectados para no dejar referencias asimétricas.

## Prueba de demostración en `main`

1. Crear una lista de enteros.
2. Insertar tres valores al inicio, por ejemplo `10`, `20` y `30`, en ese orden.
3. Insertar dos valores al final, por ejemplo `40` y `50`.
4. Imprimir hacia adelante y hacia atrás para comprobar que los órdenes son inversos.
5. Eliminar el primer elemento, el último y luego uno del medio; comprobar el resultado de cada eliminación.
6. Volver a imprimir en ambas direcciones. Con esos valores, la lista inicial queda `30 <-> 20 <-> 10 <-> 40 <-> 50`; después de eliminar `30`, `50` y `10`, queda `20 <-> 40`.

## Criterios de aceptación

- Existen `NodoDoble<T>` y `ListaDoblementeEnlazada<T>` con referencias `siguiente` y `anterior` tipadas.
- `head` y `tail` se actualizan correctamente en inserción inicial, inserción final y los casos de eliminación.
- Se implementan los recorridos hacia adelante y hacia atrás usando el enlace correspondiente.
- Eliminar cubre lista vacía, único nodo, cabeza, cola, nodo intermedio y dato ausente.
- `size` cambia una sola vez por cada inserción o eliminación exitosa.
- La prueba inserta tres elementos al inicio y dos al final, imprime en ambas direcciones, elimina primero/último/medio y vuelve a imprimir.
