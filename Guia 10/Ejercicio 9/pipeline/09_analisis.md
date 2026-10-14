# Plan técnico: cola de impresión

## Objetivo

Implementar en Java una cola de impresión que mantenga trabajos pendientes en orden FIFO. La cola debe enlazar objetos `TrabajoImpresion`, de modo que el documento que llega primero sea el primero que se procese.

## Modelo `TrabajoImpresion`

Crear una clase `TrabajoImpresion` con tres atributos:

- `nombreArchivo` (`String`)
- `cantidadPaginas` (`int`)
- `usuario` (`String`)

Incluir un constructor y una representación legible mediante `toString()` para mostrar los datos del trabajo al imprimir la fila o atenderlo.

## Cola y dependencia

- Reutilizar la cola enlazada genérica existente como `ColaEnlazada<TrabajoImpresion>`; no usar `Queue`, `LinkedList` ni otra colección de Java para el almacenamiento.
- La cola mantiene `head` como el próximo trabajo que se imprimirá y `tail` como el último trabajo agregado. Cada nodo contiene un `TrabajoImpresion` y un enlace `siguiente`.
- Una cola vacía cumple `head == null` y `tail == null`; cuando hay un único trabajo, ambos extremos apuntan al mismo nodo.
- Si los ejercicios se compilan de forma aislada, incluir la fuente de `ColaEnlazada<T>` en la compilación o agregarla al classpath/source path.

## Operaciones

- **Agregar/encolar:** llamar a `encolar(trabajo)`. Un nuevo trabajo se enlaza al final mediante `tail`; no cambia el frente si la cola ya contiene elementos. Complejidad: $O(1)$.
- **Atender/imprimir el próximo:** llamar a `desencolar()`, que devuelve y retira el `TrabajoImpresion` de `head`. Complejidad: $O(1)$.
- **Consultar el próximo:** llamar a `consultarFrente()` para obtener el trabajo de `head` sin retirarlo. Complejidad: $O(1)$.
- **Mostrar la fila:** llamar a `imprimir()` para recorrer desde `head` hasta `null` e imprimir todos los trabajos en orden de llegada. Complejidad: $O(n)$.
- La cola existente debe conservar su manejo de fila vacía, lanzando `NoSuchElementException` al desencolar o consultar el frente cuando no hay trabajos. Al retirar el único nodo, debe dejar `head` y `tail` en `null`.

## Invariantes FIFO

- Los trabajos nuevos se agregan siempre después del `tail` actual.
- Solo se atiende el trabajo señalado por `head`; los trabajos posteriores no pueden adelantarse.
- El orden de los nodos desde `head` hasta `tail` coincide con el orden de llegada.
- La cola no debe modificar los atributos de un trabajo para mantener el orden; únicamente enlaza nodos que contienen esos objetos.

## Prueba de demostración en `main`

1. Crear una `ColaEnlazada<TrabajoImpresion>` vacía.
2. Encolar tres trabajos de usuarios distintos, cada uno con nombre de archivo, páginas y usuario.
3. Mostrar la fila completa y consultar el próximo trabajo; debe ser el primero encolado.
4. Desencolar ese trabajo para simular su impresión y mostrar cuál se procesó.
5. Imprimir nuevamente la cola para comprobar que quedan los otros dos trabajos en el orden original.

La demostración debe mostrar datos legibles, por ejemplo con archivos `informe.pdf`, `factura.pdf` y `presentacion.pdf` enviados por usuarios distintos.

## Criterios de aceptación

- Existe `TrabajoImpresion` con nombre de archivo, cantidad de páginas y usuario.
- Los trabajos se almacenan en `ColaEnlazada<TrabajoImpresion>` sin colecciones alternativas.
- `head` conserva el próximo trabajo a imprimir y `tail` el último recibido.
- La consulta no retira el trabajo; la atención retira siempre el primero en llegar.
- `main` agrega tres trabajos distintos, consulta el frente, atiende uno e imprime la fila antes y después.
- La cola vacía se maneja mediante las excepciones ya definidas por la cola genérica.
