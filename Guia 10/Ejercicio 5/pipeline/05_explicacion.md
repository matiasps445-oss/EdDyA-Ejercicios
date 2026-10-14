# Explicación: historial de navegación con pila

## Por qué el botón “Atrás” sigue LIFO

El historial se comporta como una pila de páginas: cada visita nueva se coloca en el tope y es la primera que se retira al retroceder. Con la secuencia `google.com`, `github.com`, `stackoverflow.com`, el estado de la pila queda así:

```text
head (página actual)
  |
  v
stackoverflow.com -> github.com -> google.com -> null
```

Volver atrás quita `stackoverflow.com`, la página visitada más recientemente, y deja `github.com` como actual. Eso es LIFO: último en entrar, primero en salir.

Con una cola FIFO ocurriría lo contrario: el primer elemento en entrar, `google.com`, saldría primero. Al pedir “Atrás” desde `stackoverflow.com`, se perdería o se mostraría la página más antigua en vez de la inmediatamente anterior (`github.com`). El recorrido sería confuso y no representaría la secuencia de navegación del usuario.

## `head` como página activa

`head` no almacena una copia de la URL: es una referencia al nodo que está en el tope de la pila y representa la página actual.

- Al visitar una página, `visitarPagina` crea un nodo, hace que su `siguiente` apunte al antiguo `head` y luego cambia `head` al nuevo nodo. La página recién visitada pasa a ser la activa.
- Al volver atrás, `volverAtras` toma la URL actual y mueve `head` a `head.getSiguiente()`. La página anterior queda activa.
- `consultarPaginaActual` solo lee el dato del nodo señalado por `head`; no lo retira ni cambia el historial.

Como visitar y volver atrás modifican una cantidad fija de referencias, ambas operaciones cuestan $O(1)$, independientemente de cuántas páginas haya. Imprimir el historial sí recorre todos los nodos y cuesta $O(n)$.

## Qué evita la comprobación del historial vacío

Cuando no quedan páginas, `head == null`. Antes de leer `head.getUrl()` o mover `head`, los métodos `consultarPaginaActual` y `volverAtras` comprueban `estaVacio()`. Si no hay una página disponible, lanzan `EmptyStackException` en lugar de intentar desreferenciar `null` y producir un `NullPointerException` accidental.

La excepción informa al código que llama que la acción no es válida. Para que una interfaz siga funcionando tras un clic adicional en “Atrás”, el llamador debe capturarla y mostrar un aviso o deshabilitar el botón. En el `main` de este ejercicio se captura el caso de consulta sobre historial vacío; el propio método `volverAtras` también contiene la misma protección.

Al retirar la única página, `head` pasa a `null` y el historial queda vacío. No hay otra referencia de tope que actualizar en esta implementación.

## Seguimiento de la prueba

El programa visita las tres URLs y las imprime desde la actual hacia las anteriores. `consultarPaginaActual()` devuelve `stackoverflow.com`. Después `volverAtras()` retira esa URL y el nuevo `head` apunta a `github.com`; la siguiente consulta devuelve `github.com`, y el historial restante es:

```text
head -> github.com -> google.com -> null
```
