# Plan técnico: eliminar un nodo por posición

## Objetivo

Añadir a `ListaEnlazada` el método `void eliminarEnPosicion(int posicion)`. Debe desconectar el nodo en el índice indicado, conservar la cadena de los demás nodos y reducir `size` una sola vez cuando la eliminación es válida.

## Contrato y validación

- Interpretar `posicion` con índice base cero: el primer nodo está en `0` y el último en `size - 1`.
- Antes de recorrer o modificar la lista, validar `posicion < 0 || posicion >= size`.
- Si la posición no es válida, lanzar una excepción de índice, por ejemplo `IndexOutOfBoundsException`, con un mensaje claro. En una lista vacía (`size == 0`), toda posición es inválida.
- Las posiciones válidas para eliminar son `[0, size - 1]`; a diferencia de la inserción, `posicion == size` no es válido porque no hay nodo que eliminar allí.

## Algoritmo de `eliminarEnPosicion(int posicion)`

1. Validar la posición antes de acceder a los nodos.
2. Si `posicion == 0`, actualizar directamente `head = head.getSiguiente()`. No hay nodo anterior a la cabeza.
3. Para `posicion > 0`, iniciar una referencia auxiliar `anterior` en `head` y avanzar con un contador hasta el nodo de índice `posicion - 1`.
4. Puentear el nodo objetivo usando `anterior.setSiguiente(anterior.getSiguiente().getSiguiente())`. El enlace del nodo anterior pasa a apuntar al sucesor del nodo eliminado; si el objetivo era el último, ese sucesor es `null`.
5. Decrementar `size` exactamente una vez después de actualizar los enlaces.

La posición debe validarse antes del recorrido para que el algoritmo no intente obtener el sucesor de un nodo inexistente ni genere un `NullPointerException` accidental. Usar una referencia auxiliar preserva `head` en las eliminaciones que no afectan al primer nodo.

## Casos e invariantes

- **Eliminar cabeza:** la nueva cabeza es el antiguo segundo nodo. Si solo había un nodo, `head` queda en `null`.
- **Eliminar el último nodo:** el anterior pasa a tener `siguiente == null`.
- **Eliminar un nodo intermedio:** el anterior queda conectado al nodo posterior al eliminado.
- Después de cada eliminación válida, `size` coincide con la cantidad de nodos alcanzables desde `head`.
- Una posición inválida debe producir la excepción antes de modificar `head`, enlaces o `size`.

## Eficiencia

- **Tiempo:** $O(1)$ para eliminar la cabeza; para las demás posiciones se recorren hasta el nodo anterior, por lo que el costo es $O(posicion)$ y $O(n)$ en el peor caso.
- **Espacio adicional:** $O(1)$, mediante una referencia auxiliar y un contador; no se usan colecciones.

## Prueba de demostración

En `main`:

1. Crear una lista con cuatro elementos, por ejemplo `10 -> 20 -> 30 -> 40`.
2. Eliminar la posición `0`; imprimir la lista, que debe quedar `20 -> 30 -> 40`.
3. Calcular la última posición actual como `getSize() - 1`, eliminarla e imprimir; la lista debe quedar `20 -> 30`.
4. Eliminar la posición `1` (ahora intermedia) e imprimir; la lista debe quedar `20`.

La última posición debe calcularse con el tamaño actualizado, no reutilizar el índice que era final antes de la primera eliminación.

## Criterios de aceptación

- Existe `public void eliminarEnPosicion(int posicion)`.
- La validación rechaza posiciones menores que `0` o mayores/iguales que `size` antes de recorrer.
- La posición `0` actualiza `head`; las demás posiciones recorren hasta `posicion - 1` y puentean el nodo objetivo con `anterior.getSiguiente().getSiguiente()`.
- `size` disminuye una sola vez después de una eliminación exitosa.
- `main` agrega cuatro elementos y demuestra la eliminación de la cabeza, la última posición actual y una posición intermedia, imprimiendo la lista en cada paso.
- La lista conserva correctamente sus nodos restantes y no se utilizan colecciones de Java.
