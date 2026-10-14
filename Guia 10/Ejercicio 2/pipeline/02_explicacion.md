# Explicación: cola enlazada de enteros

## Cómo trabajan juntos `head` y `tail`

`head` y `tail` son referencias a nodos, no copias de sus datos. `head` señala el próximo nodo que saldrá de la cola (frente); `tail` señala el nodo más reciente que entró (final). Los enlaces `siguiente` conectan los elementos en orden FIFO.

### Insertar el primer elemento

Al comienzo, `head == null` y `tail == null`. Al encolar `10`, se crea un nodo cuyo `siguiente` vale `null`. Como la cola estaba vacía, las dos referencias se asignan al mismo nodo:

```text
head ─┐
      v
     [10 | null]
      ^
tail ─┘
```

Ese nodo es a la vez el frente y el final. La condición `tail == null` detecta este caso especial.

### Insertar cuando ya hay elementos

Al encolar `20`, se crea otro nodo y se enlaza desde el nodo al que apunta `tail`. Luego `tail` avanza al nodo nuevo. `head` se mantiene en `10`:

```text
head                         tail
  |                            |
  v                            v
[10 | siguiente] ----------> [20 | null]
```

Cada encolado posterior repite esos dos cambios: conectar el antiguo final con el nuevo y mover `tail`. Como no se busca el último nodo recorriendo desde `head`, `encolar` cuesta $O(1)$. Desencolar retira directamente desde `head`, también en $O(1)$.

## Por qué hay que limpiar `tail` al quitar el último elemento

Al desencolar, el código avanza `head` al siguiente nodo. Si el nodo retirado era el único, su sucesor es `null`, por lo que `head` queda en `null`. En ese momento la cola está vacía y también se debe asignar `tail = null`.

Si se dejara `tail` apuntando al nodo antiguo, las referencias representarían estados contradictorios: `head` diría “vacía”, pero `tail` todavía apuntaría a un nodo. Esto rompe una suposición que usa `encolar`: si `tail != null`, conecta el nodo nuevo desde `tail`. La nueva cadena empezaría en el nodo viejo, pero `head` seguiría en `null`; el nuevo elemento quedaría inaccesible al recorrer desde el frente y la cola parecería vacía aunque `size` hubiera aumentado.

Por eso, después de mover `head`, el método comprueba si quedó en `null`; si es así, también pone `tail` en `null`. La condición de cola vacía queda consistente:

```text
head == null
 tail == null
 size == 0
```

## Comparación con una cola basada en arreglo estático

Una cola basada en un arreglo reserva un bloque de capacidad fija. En una cola lineal simple, al desencolar elementos del principio quedan celdas libres al comienzo; si el índice final llega al límite, puede parecer que no hay espacio aunque haya huecos reutilizables. Una cola circular resuelve ese problema haciendo que los índices vuelvan al inicio con módulo, por ejemplo `rear = (rear + 1) % capacidad`. Así se reciclan las posiciones liberadas.

La cola enlazada no reserva un arreglo de capacidad fija ni necesita índices circulares: cada `encolar` crea un nodo y lo conecta a `tail`. Por eso no deja huecos internos del arreglo ni necesita la fórmula `%` para envolver índices. La contrapartida es que cada nodo consume memoria adicional para su referencia `siguiente`, y la capacidad sigue limitada por la memoria disponible del heap; no es ilimitada.

## Seguimiento de la prueba

Al encolar `10`, `20` y `30`, la estructura es:

```text
head -> 10 -> 20 -> 30 <- tail
```

`consultarFrente()` devuelve `10` sin retirarlo. `contiene(20)` recorre desde `head` y devuelve `true`. Luego `desencolar()` devuelve `10` y mueve `head` a `20`; la cola queda `20 -> 30`. Si se siguen retirando elementos hasta vaciarla, al sacar `30` ambos extremos se ponen en `null`.
