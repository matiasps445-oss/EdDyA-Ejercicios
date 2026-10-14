# Plan técnico: cola enlazada de enteros

## Objetivo

Implementar en Java una cola FIFO de números enteros construida desde cero con nodos enlazados. No utilizar `Queue`, `LinkedList` ni otras colecciones de Java para almacenar los elementos.

## Diseño de clases y estado

- **`Nodo`:** guarda un entero `dato` y una referencia `siguiente` al nodo que viene después.
- **`ColaEnlazada`:** administra los nodos con dos referencias privadas: `head`, que señala el frente, y `tail`, que señala el último nodo. Mantener además `size` como contador.
- Una cola vacía cumple `head == null`, `tail == null` y `size == 0`.
- Los nodos se enlazan desde el frente hacia el final; al imprimir, se muestran en orden FIFO, desde `head` hasta `tail`.

## Operaciones

- **`encolar(int dato)`:** crear un nodo nuevo con `siguiente == null`. Si `tail == null`, la cola está vacía y tanto `head` como `tail` deben apuntar al nodo nuevo. En otro caso, enlazar `tail.siguiente` con el nuevo nodo y actualizar `tail`. Incrementar `size` una sola vez. Complejidad: $O(1)$.
- **`desencolar()`:** si la cola está vacía, lanzar una excepción, preferentemente `NoSuchElementException`. En otro caso, guardar el dato de `head`, mover `head` a `head.getSiguiente()` y reducir `size`. Si después del avance `head == null`, también actualizar `tail = null`. Devolver el dato retirado. Complejidad: $O(1)$.
- **`consultarFrente()`:** si la cola está vacía, lanzar `NoSuchElementException`; si no, devolver el dato de `head` sin modificar enlaces ni tamaño. Complejidad: $O(1)$.
- **`estaVacia()`:** devolver si `head == null`. Complejidad: $O(1)$.
- **`contiene(int dato)`:** recorrer desde `head` con una referencia auxiliar hasta encontrar el valor o llegar a `null`. Devolver `true` o `false` sin modificar la cola. Complejidad: $O(n)$ en el peor caso.
- **`imprimir()`:** recorrer desde `head` hasta `null` e imprimir los elementos desde el frente al final; mostrar un mensaje claro si está vacía. Complejidad: $O(n)$.
- **`getSize()`:** devolver el contador `size` en tiempo $O(1)$.

`NoSuchElementException` pertenece a la biblioteca estándar y comunica que se solicitó un elemento cuando no existe. No es una colección ni reemplaza la implementación enlazada.

## Por qué se conservan `head` y `tail`

Con solo `head`, encolar al final obligaría a recorrer todos los nodos para hallar el último, con costo $O(n)$. `tail` conserva directamente la referencia al último nodo, por lo que se puede conectar el nuevo elemento sin recorrido y actualizar `tail` en $O(1)$. Desencolar también es $O(1)$ porque siempre retira el nodo de `head`.

## Invariantes y casos límite

- `head` siempre señala el próximo elemento que se desencolará; `tail` siempre señala el último que se encoló y aún permanece en la cola.
- Si la cola está vacía, ambas referencias son `null`; si tiene un único nodo, `head == tail`.
- Al desencolar el único nodo, actualizar `head` y `tail` a `null` para no dejar una cola inconsistente.
- `size` aumenta una vez por `encolar` y disminuye una vez por `desencolar` exitoso.
- `consultarFrente`, `contiene` e `imprimir` no modifican la estructura.
- `desencolar` y `consultarFrente` sobre una cola vacía lanzan una excepción; `contiene` sobre una cola vacía devuelve `false`.

## Eficiencia

`encolar` y `desencolar` cuestan $O(1)$ gracias a `tail` y `head`, respectivamente. Consultar el frente, comprobar si está vacía y consultar el tamaño también cuestan $O(1)$. Buscar un valor e imprimir la cola requieren recorrer los nodos, por lo que cuestan $O(n)$.

## Prueba de demostración en `main`

1. Crear una cola y encolar tres enteros, por ejemplo `10`, `20` y `30`, en ese orden.
2. Mostrar la cola como `10 -> 20 -> 30`.
3. Consultar el frente y comprobar que es `10`.
4. Desencolar y mostrar que el elemento retirado es `10`; la cola debe quedar `20 -> 30`.
5. Buscar un valor específico existente, por ejemplo `20`, y mostrar `true`.

La prueba demuestra FIFO: sale primero el elemento que entró primero.

## Criterios de aceptación

- Existe una clase de nodo y una `ColaEnlazada` construida con referencias, sin colecciones como almacenamiento.
- `head` representa el frente y `tail` el último elemento.
- Están implementadas `encolar`, `desencolar`, `consultarFrente`, `estaVacia`, `contiene`, `imprimir` y `getSize`.
- `encolar` y `desencolar` son $O(1)$; la búsqueda recorre secuencialmente y es $O(n)$ en el peor caso.
- Desencolar o consultar el frente de una cola vacía lanza una excepción.
- Al retirar el último elemento, tanto `head` como `tail` quedan en `null` y `size` queda en cero.
- `main` encola tres valores, consulta el frente, desencola uno y busca un valor, mostrando resultados claros.
