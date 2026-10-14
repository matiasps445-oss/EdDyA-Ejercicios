# Plan técnico: historial de navegación web

## Objetivo

Implementar en Java un simulador de historial de navegación que guarde URLs en una pila LIFO basada en un arreglo de `String`. Debe permitir visitar una página, volver atrás retirando la página actual y consultar la página actual.

## Justificación de la estructura

El historial se modela con una pila porque el sitio visitado más recientemente es el primero al que se regresa al presionar “Atrás”. En una pila, `push` coloca una URL en el tope y `pop` retira esa última URL; la página anterior pasa a ser el nuevo tope. Una cola FIFO retiraría primero la URL más antigua, que no corresponde al comportamiento solicitado.

## Diseño de la clase

- **Clase sugerida:** `HistorialNavegacion`.
- **Arreglo interno:** `String[] urls` con capacidad fija recibida en el constructor.
- **Tope:** `int top`, inicializado en `-1` para representar un historial vacío.
- **Encapsulamiento:** mantener el arreglo y el índice como campos `private`.
- **Capacidad inválida:** rechazar capacidades menores que uno con `IllegalArgumentException`.

## Operaciones

- **Visitar (`visitarPagina(String url)`):** comprobar primero si el arreglo está lleno (`top == urls.length - 1`). Si está lleno, lanzar `IllegalStateException` antes de modificar el índice. En otro caso, incrementar `top` y guardar la URL en `urls[top]`.
- **Atrás (`volverAtras()`):** comprobar primero que el historial no esté vacío (`top != -1`). Guardar y devolver la URL actual en `urls[top]`, y luego decrementar `top`. La URL que queda en el nuevo tope es la página actual a la que se regresó.
- **Consultar (`consultarPaginaActual()`):** comprobar que el historial no esté vacío y devolver `urls[top]` sin modificar el índice.

Se recomienda lanzar `IllegalStateException` si se intenta volver atrás o consultar cuando no hay URLs, o visitar cuando se alcanzó la capacidad. No se debe acceder a una posición del arreglo antes de validar el estado.

## Prueba de demostración

Crear un historial con capacidad suficiente y realizar estas acciones:

1. Visitar `google.com`.
2. Visitar `github.com`.
3. Visitar `stackoverflow.com`.
4. Consultar la página actual: debe ser `stackoverflow.com`.
5. Volver atrás, retirando `stackoverflow.com`.
6. Consultar nuevamente la página actual: debe ser `github.com`.

Salida conceptual esperada:

```text
Página actual: stackoverflow.com
Página retirada: stackoverflow.com
Página actual después de Atrás: github.com
```

## Comentarios requeridos en el código

- Explicar que el historial es LIFO: la última URL visitada es la primera que se retira.
- Indicar que `top` empieza en `-1` y señala la URL actual cuando el historial no está vacío.
- Explicar por qué se valida si está lleno antes de visitar y si está vacío antes de volver atrás o consultar.
- Aclarar que `volverAtras` decrementa el tope y que `consultarPaginaActual` solo lee la nueva cima.

## Criterios de aceptación

- Las URLs se guardan en un arreglo `String[]` de capacidad fija.
- `top` comienza en `-1` y cambia correctamente al visitar y volver atrás.
- Las visitas apilan URLs; Atrás retira la más reciente y deja como actual la anterior.
- Consultar no modifica el historial.
- Se previenen accesos fuera del arreglo y operaciones sobre un historial vacío.
- La prueba muestra primero `stackoverflow.com` y, tras volver atrás, `github.com`.