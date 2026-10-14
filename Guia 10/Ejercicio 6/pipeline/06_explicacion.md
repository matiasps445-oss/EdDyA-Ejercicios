# Explicación: cola de atención de clientes

## Por qué una fila bancaria es FIFO

FIFO significa “primero en entrar, primero en salir”. Es la regla natural de una fila de atención: el turno de llegada determina quién será atendido antes. Si Ana llega antes que Luis y Marta, la cola conserva ese orden y atiende primero a Ana.

Una pila LIFO haría lo contrario: el último que llegó sería el primero atendido. Con Ana, Luis y Marta en ese orden, Marta saltaría al frente y Ana quedaría esperando a pesar de haber llegado primero. En una fila común, eso perjudicaría a quienes llevan más tiempo esperando y rompería el orden justo de los turnos.

## Cómo `tail` permite encolar en $O(1)$

La cola guarda dos referencias a los extremos:

- `head` señala al primer cliente que debe atenderse.
- `tail` señala al cliente que llegó último.

Al insertar el primer cliente, `tail == null`, así que el nuevo nodo es a la vez el frente y el final: `head` y `tail` apuntan al mismo objeto `Nodo`.

Cuando la fila ya tiene clientes, `encolar` hace dos cambios directos: enlaza el `siguiente` del nodo `tail` actual con el nodo nuevo y mueve `tail` al nodo nuevo. Por ejemplo:

```text
Antes:  head -> Ana -> Luis <- tail
Entra Marta
Después: head -> Ana -> Luis -> Marta <- tail
```

No se recorre desde Ana para encontrar el final porque `tail` ya conserva esa referencia. Por eso agregar al final cuesta $O(1)$, independientemente de cuántas personas estén esperando. `tail` no adelanta a `head`: los dos siguen representando extremos diferentes.

## Cómo avanza la fila al atender a `head`

`desencolar()` lee primero el `Cliente` de `head` para devolver quién fue atendido. Después ejecuta `head = head.getSiguiente()`. Esa reasignación no copia ni mueve a los clientes: hace que la referencia de frente apunte al siguiente nodo de la cadena.

Antes de atender a Ana:

```text
head -> Ana -> Luis -> Marta <- tail
```

Después:

```text
head ------> Luis -> Marta <- tail
```

Ana deja de ser el frente y Luis pasa a ser el próximo. El método reduce `size` una vez. Si se atendiera al único cliente, el nuevo `head` sería `null`; el código también pondría `tail` en `null` para dejar la cola vacía en un estado coherente.

Tanto encolar como desencolar actúan directamente sobre sus extremos y cuestan $O(1)$. En cambio, imprimir la fila recorre todos los nodos y cuesta $O(n)$.

## Resultado de la prueba en `main`

El programa encola a Ana García (turno 1), Luis Pérez (turno 2) y Marta López (turno 3), e imprime la fila en ese orden. `consultarFrente()` devuelve a Ana sin retirarla. Luego `desencolar()` atiende a Ana y la fila restante comienza con Luis, seguido por Marta. Esto confirma que los turnos avanzan en orden FIFO.
