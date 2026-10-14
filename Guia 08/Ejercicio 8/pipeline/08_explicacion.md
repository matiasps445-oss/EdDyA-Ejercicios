# Explicación de `ColaImpresion`

`ColaImpresion` representa los trabajos pendientes de una impresora como una cola FIFO dentro de un arreglo circular. Cada elemento del arreglo es un objeto `Documento`, que agrupa dos datos: `nombre` y `cantidadPaginas`. El método `toString()` convierte esos datos a una forma fácil de mostrar, como `Documento A (10 páginas)`.

## Por qué el spooler usa FIFO

Un spooler recibe trabajos de impresión y los mantiene esperando a que la impresora pueda procesarlos. FIFO (**First In, First Out**) conserva el orden cronológico: el primer documento que llegó es el próximo que se procesa. En la prueba, Documento A se encola antes que Documento B, por lo que A debe imprimirse primero.

Una pila LIFO invertiría ese orden y podría imprimir primero el trabajo más reciente. Para la política de atención por llegada de este ejercicio, la cola es la estructura que representa el comportamiento esperado.

## Qué hay en el arreglo y qué significan los índices

La cola usa `Documento[] trabajos` y mantiene tres valores:

- `front`: índice del próximo trabajo que se retirará para imprimir.
- `rear`: índice del último trabajo agregado.
- `size`: cantidad de trabajos que siguen pendientes.

Para una capacidad de tres, el estado avanza así:

| Acción | Arreglo `[0, 1, 2]` | `front` | `rear` | `size` |
|---|---|---:|---:|---:|
| Inicio | `[null, null, null]` | 0 | -1 | 0 |
| Encolar Documento A (10 páginas) | `[A, null, null]` | 0 | 0 | 1 |
| Encolar Documento B (5 páginas) | `[A, B, null]` | 0 | 1 | 2 |
| Imprimir siguiente | `[null, B, null]` | 1 | 1 | 1 |

En `encolarDocumento`, el índice `rear` avanza de forma circular con `(rear + 1) % trabajos.length`; el objeto se guarda en esa posición y `size` aumenta. En `imprimirSiguiente`, primero se guarda la referencia ubicada en `trabajos[front]`, luego se limpia esa celda, se avanza `front` con el mismo módulo, se reduce `size` y se devuelve el documento. Para A y B, se devuelve el objeto de índice `0`, Documento A, y Documento B queda en el nuevo `front`.

El operador `%` permite volver al inicio del arreglo y reutilizar posiciones liberadas sin desplazar los trabajos que todavía esperan. `size` permite distinguir correctamente una cola vacía de una llena, incluso cuando los índices coinciden después de envolver.

## Cómo se muestra el documento impreso

`main` crea `Documento("Documento A", 10)` y `Documento("Documento B", 5)` y los encola en ese orden. La llamada a `imprimirSiguiente()` devuelve el primer objeto. Al concatenarlo con el texto de salida, Java llama a su `toString()`, por lo que aparece el nombre y la cantidad de páginas:

```text
Imprimiendo: Documento A (10 páginas)
```

Documento B no se pierde: permanece en el arreglo y su índice pasa a ser `front`, listo para la siguiente impresión.

## Qué ocurre si se intenta imprimir con la cola vacía

`imprimirSiguiente()` primero evalúa `estaVacia()`, que comprueba `size == 0`. Si no hay trabajos, lanza `IllegalStateException` con el mensaje de que no hay documentos pendientes y termina antes de acceder a `trabajos[front]`. Así evita retirar una posición inválida o alterar los índices. La misma idea se aplica al agregar cuando la cola está llena: se valida `size == trabajos.length` antes de escribir, para no sobrescribir un trabajo ni exceder la capacidad.

## Reflexión sobre el prompt

El prompt inicial relacionó claramente el funcionamiento de una impresora con el principio FIFO: el primer trabajo que llega debe ser el primero que se imprime. También especificó los datos del documento y la prueba deseada, por lo que no fue necesario realizar ajustes al prompt para que OpenCode generara la lógica adecuada.