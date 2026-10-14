# Plan técnico: historial de navegación con pila enlazada

## Objetivo

Implementar en Java un historial simple de navegación web basado en una pila enlazada de URLs (`String`). La última página visitada debe ser la primera que se retire al volver atrás, siguiendo LIFO.

## Diseño de clases y estado

- **`Nodo`:** almacena una URL en `dato` y una referencia `siguiente` al nodo que está debajo en la pila.
- **`HistorialNavegacion`:** administra la pila con una referencia `head` al tope y, opcionalmente, un contador `size`.
- `head` representa la página actual; los nodos posteriores representan páginas anteriores. Un historial vacío cumple `head == null`.
- No se necesita una pila adicional de “Adelante”, ya que no forma parte de los requisitos.

## Operaciones

- **`visitarPagina(String url)`:** crear un nodo para la URL, enlazarlo antes del `head` actual y actualizar `head`. La nueva página pasa a ser la actual. Complejidad: $O(1)$.
- **`volverAtras()`:** si `head == null`, lanzar `EmptyStackException`. En otro caso, retirar la página actual moviendo `head` a `head.getSiguiente()` y devolver la página retirada. Complejidad: $O(1)$.
- **`consultarPaginaActual()`:** si el historial está vacío, lanzar `EmptyStackException`; de lo contrario, devolver la URL de `head` sin retirarla. Complejidad: $O(1)$.
- **`estaVacio()`:** devolver si `head == null`. Complejidad: $O(1)$.
- **`imprimirHistorial()`:** recorrer desde `head` hasta `null` e imprimir desde la página actual hacia las páginas anteriores. No debe modificar enlaces ni quitar páginas. Complejidad: $O(n)$.

Para `EmptyStackException`, usar `java.util.EmptyStackException`. Las consultas de una pila vacía deben detectarse antes de acceder al nodo.

## Comportamiento LIFO e invariantes

- Cada visita nueva se coloca en el tope; por ello la página visitada más recientemente es la actual.
- Volver atrás retira el tope, dejando como actual la página que se visitó inmediatamente antes.
- Consultar la página actual no cambia el historial.
- Si solo queda una página, al volver atrás `head` pasa a `null`.

## Eficiencia

Visitar una página, volver atrás y consultar la actual operan directamente sobre `head` y cuestan $O(1)$. Imprimir todo el historial requiere recorrer los $n$ nodos y cuesta $O(n)$.

## Prueba de demostración en `main`

1. Crear un historial vacío.
2. Visitar `google.com`, luego `github.com` y finalmente `stackoverflow.com`.
3. Imprimir el historial y consultar la página actual; debe ser `stackoverflow.com`.
4. Ejecutar `volverAtras()` para retirar `stackoverflow.com`.
5. Consultar nuevamente la página actual; debe ser `github.com`.
6. Imprimir el historial restante para comprobar que `github.com` está en el tope y `google.com` debajo.

La prueba demuestra que la última URL visitada se retira primero y que la página anterior reaparece como actual.

## Criterios de aceptación

- El historial se implementa con nodos enlazados, sin usar una colección como almacenamiento.
- `head` identifica el tope y la página actual.
- Están implementadas las operaciones de visitar, volver atrás, consultar la actual, comprobar vacío e imprimir el historial.
- Volver atrás o consultar la página actual con historial vacío lanza `EmptyStackException`.
- `main` visita las tres URLs solicitadas, consulta el tope, retrocede una página y confirma que `github.com` es la página actual.
