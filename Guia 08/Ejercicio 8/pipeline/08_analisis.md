# Plan técnico: cola de impresión

## Objetivo

Definir un sistema de impresión en Java que mantenga documentos en una cola FIFO respaldada por un arreglo. Cada trabajo contiene un nombre y una cantidad de páginas; los trabajos se devuelven para impresión en el mismo orden en que llegaron.

## Diseño de los datos

- **Clase `Documento`:** almacenar `nombre` (`String`) y `cantidadPaginas` (`int`) en campos privados, inicializados por un constructor; proporcionar getters y una representación legible mediante `toString()`.
- **Clase de la cola:** `ColaImpresion`, con un arreglo interno `Documento[] trabajos` de capacidad fija recibida en el constructor.
- **Índices y cantidad:** `front` identifica el próximo trabajo a imprimir, `rear` el último trabajo agregado y `size` la cantidad de trabajos pendientes.
- **Estado inicial:** `front = 0`, `rear = -1`, `size = 0`.
- **Capacidad inválida:** rechazar capacidades menores que uno con `IllegalArgumentException`.
- **Suposición:** la prueba recibe documentos no nulos con datos válidos; no se agregan reglas de validación de dominio no pedidas.

## Elección de FIFO

Una cola FIFO (**First In, First Out**) representa el orden cronológico de un spooler: el primer trabajo enviado debe ser el primero que la impresora procesa. Una estructura LIFO imprimiría antes el trabajo más reciente, postergando los anteriores y rompiendo el orden justo de llegada.

## Operaciones

- **Encolar (`encolarDocumento(Documento documento)`):** si `size == trabajos.length`, lanzar `IllegalStateException` sin modificar el estado. Si hay espacio, avanzar `rear` de forma circular (`(rear + 1) % trabajos.length`), guardar el documento y aumentar `size`.
- **Imprimir/desencolar (`imprimirSiguiente()`):** si `size == 0`, lanzar `IllegalStateException` indicando que no hay trabajos pendientes. En caso contrario, guardar `trabajos[front]`, limpiar esa celda, avanzar `front` de forma circular, reducir `size` y devolver el documento retirado.
- **Consultar el siguiente (`consultarSiguiente()`):** opcional para inspección; comprobar que `size > 0` y devolver `trabajos[front]` sin quitarlo.
- **Estado:** `estaVacia()` puede comprobar `size == 0`; `estaLlena()` puede comprobar `size == trabajos.length`.

El arreglo circular permite reutilizar las posiciones liberadas sin desplazar los documentos pendientes. `front` y `rear` deben actualizarse con módulo de la capacidad; `size` diferencia el estado vacío del lleno aun cuando los índices coincidan.

## Prueba de demostración

1. Crear una cola con capacidad suficiente.
2. Encolar `Documento("Documento A", 10)` y después `Documento("Documento B", 5)`.
3. Ejecutar una operación de impresión (`imprimirSiguiente()`) y mostrar el documento devuelto.

La salida esperada de esa operación es:

```text
Imprimiendo: Documento A (10 páginas)
```

Esto verifica FIFO: Documento A se agregó primero, por lo que se retira para imprimir antes que Documento B.

## Casos límite

- Si se intenta imprimir con `size == 0`, lanzar `IllegalStateException` antes de acceder al arreglo.
- Si se intenta encolar con `size == trabajos.length`, lanzar `IllegalStateException` antes de sobrescribir un trabajo.
- Si la capacidad solicitada es menor que uno, lanzar `IllegalArgumentException` al construir la cola.

## Comentarios requeridos en el código

- Explicar qué datos agrupa `Documento` y cómo se representa al imprimirlo.
- Justificar que la cola FIFO respeta el orden de llegada de los trabajos.
- Describir el rol de `front`, `rear` y `size`.
- Aclarar cómo el módulo permite reutilizar las posiciones del arreglo circular.
- Indicar que lleno y vacío se comprueban antes de acceder al arreglo.

## Criterios de aceptación

- Cada trabajo tiene nombre y cantidad de páginas.
- Los documentos se guardan en un arreglo de capacidad fija administrado como cola FIFO.
- Encolar y retirar actualizan índices y cantidad de forma consistente.
- La impresión de la prueba devuelve primero `Documento A` con 10 páginas.
- La cola rechaza trabajos cuando está llena e informa si se intenta imprimir cuando está vacía.