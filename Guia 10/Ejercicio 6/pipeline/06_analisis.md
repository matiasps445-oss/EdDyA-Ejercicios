# Plan técnico: cola de atención de clientes

## Objetivo

Implementar en Java una fila de atención bancaria con una cola enlazada simple de objetos `Cliente`. El orden de atención debe seguir FIFO: el primero que llega es el primero que se atiende.

## Diseño de clases y estado

- **`Cliente`:** contiene tres atributos: `nombre`, `numeroTurno` y `motivoConsulta`. Incluir un constructor y una representación legible, por ejemplo mediante `toString()`, para mostrar los datos durante la prueba.
- **`Nodo`:** contiene un `Cliente dato` y una referencia `siguiente` al próximo cliente de la fila.
- **`ColaAtencion`:** administra los nodos mediante `head` (frente, próximo cliente a atender) y `tail` (final, último cliente agregado). Mantener opcionalmente un contador `size`.
- Una fila vacía cumple `head == null` y `tail == null`; si solo contiene un cliente, `head == tail`.

## Operaciones

- **`encolar(Cliente cliente)`:** crear un nodo. Si `tail == null`, asignarlo tanto a `head` como a `tail`. Si ya hay clientes, enlazar el nodo nuevo después del `tail` actual y actualizar `tail`. Incrementar `size` una sola vez si se mantiene. Complejidad: $O(1)$.
- **`desencolar()`:** si la fila está vacía, lanzar una excepción clara, preferentemente `NoSuchElementException`. En otro caso, guardar el `Cliente` de `head`, mover `head` a `head.getSiguiente()` y disminuir `size` si existe. Si `head` queda en `null`, actualizar también `tail = null`. Devolver el cliente atendido. Complejidad: $O(1)$.
- **`consultarFrente()`:** si la fila está vacía, lanzar `NoSuchElementException`; de lo contrario, devolver el cliente de `head` sin retirarlo. Complejidad: $O(1)$.
- **`estaVacia()`:** indicar si `head == null`. Complejidad: $O(1)$.
- **`imprimirFila()`:** recorrer desde `head` hasta `null` e imprimir cada cliente desde el próximo a atender hasta el último que llegó. Mostrar un mensaje claro si la fila está vacía. Complejidad: $O(n)$.

## Comportamiento FIFO e invariantes

- Los clientes nuevos se agregan al final mediante `tail`; el frente `head` no cambia al encolar mientras la fila ya tiene elementos.
- Se atiende siempre el cliente señalado por `head`; después de atenderlo, el frente avanza al siguiente nodo.
- Al atender al único cliente, tanto `head` como `tail` deben quedar en `null`.
- Si `desencolar` o `consultarFrente` se invocan con la fila vacía, la excepción debe ocurrir antes de acceder a un nodo.
- `size` aumenta al encolar y disminuye solo al atender exitosamente si la clase mantiene ese contador.

## Eficiencia

Encolar usa `tail` para agregar sin recorrer la fila y desencolar opera directamente sobre `head`; ambas operaciones cuestan $O(1)$. Consultar el siguiente cliente y comprobar si la fila está vacía también cuestan $O(1)$. Imprimir la fila requiere visitar cada nodo y cuesta $O(n)$.

## Prueba de demostración en `main`

1. Crear una cola vacía.
2. Encolar tres clientes distintos, cada uno con nombre, número de turno y motivo de consulta.
3. Imprimir la fila para mostrar el orden de llegada.
4. Consultar quién sigue y mostrar los datos del primer cliente.
5. Desencolar y mostrar al cliente atendido.
6. Imprimir nuevamente la fila para comprobar que el segundo cliente ahora está al frente y que el resto conserva su orden.

## Criterios de aceptación

- Existe una clase `Cliente` con los atributos `nombre`, `numeroTurno` y `motivoConsulta`.
- El almacenamiento se construye con nodos y referencias, sin una colección como estructura subyacente.
- `head` identifica al próximo cliente a atender y `tail` al último cliente agregado.
- Están implementadas las operaciones para encolar, desencolar, consultar el frente, comprobar vacío e imprimir la fila.
- Las operaciones que requieren un cliente lanzan una excepción clara si la fila está vacía.
- Al retirar el último cliente, `head` y `tail` quedan en `null`.
- `main` encola tres clientes, consulta el frente, atiende al primero e imprime la fila antes y después.
