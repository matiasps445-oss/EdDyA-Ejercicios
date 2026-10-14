# Plan técnico: modificar el dato de un nodo por posición

## Objetivo

Añadir a `ListaEnlazada` el método `void modificar(int posicion, int nuevoDato)`. Debe localizar el nodo existente en la posición indicada y reemplazar únicamente el entero que contiene, sin alterar los enlaces ni el tamaño de la lista.

## Diseño y compatibilidad

- Interpretar `posicion` con índice base cero: `head` está en la posición `0`.
- Mantener la estructura enlazada existente, con `head`, `size` y nodos que contienen `dato` y `siguiente`.
- Para actualizar el valor, el nodo debe ofrecer `setDato(int nuevoDato)`. Si el atributo `dato` es inmutable (`final`) o todavía no hay un setter, ajustarlo para permitir únicamente la actualización del dato. Mantener `siguiente` sin cambios.

## Validación de límites

Antes de recorrer la lista, comprobar `posicion < 0 || posicion >= size`. Si la condición se cumple, lanzar `IndexOutOfBoundsException` con un mensaje claro. En una lista vacía, cualquier posición es inválida.

La validación debe preceder a cualquier acceso a nodos para evitar un recorrido fuera de rango o un `NullPointerException`. Una posición inválida no debe modificar el dato ni ninguna parte de la estructura.

## Algoritmo de `modificar(int posicion, int nuevoDato)`

1. Validar la posición contra el intervalo válido `[0, size - 1]`.
2. Inicializar una referencia auxiliar `actual` con `head` y un contador en `0`.
3. Avanzar con `actual = actual.getSiguiente()` e incrementar el contador hasta que este coincida con `posicion`.
4. Actualizar el valor del nodo encontrado mediante `actual.setDato(nuevoDato)`.
5. No llamar a `setSiguiente`, no reasignar `head` y no cambiar `size`.

El auxiliar permite localizar el nodo sin perder la referencia de entrada `head`. Solo cambia la carga útil del nodo; la topología (la cadena de enlaces entre nodos) permanece intacta.

## Diferencia entre dato y enlace

- **Dato (`dato`):** es el valor entero que transporta el nodo. `setDato` reemplaza ese valor.
- **Enlace (`siguiente`):** determina qué nodo viene después. Cambiarlo puede conectar, desconectar o reordenar partes de la lista.

Esta operación modifica únicamente el primer elemento. Cambiar `siguiente` sería una operación estructural distinta y no corresponde a `modificar`.

## Eficiencia

- **Tiempo:** $O(posicion)$ para llegar al índice solicitado y $O(n)$ en el peor caso.
- **Espacio adicional:** $O(1)$, pues solo se necesita una referencia auxiliar y un contador.

## Prueba de demostración

En `main`:

1. Crear una lista con los valores `10`, `20` y `30`, en ese orden.
2. Llamar a `modificar(1, 99)` para reemplazar el valor central.
3. Imprimir la lista y comprobar que el resultado sea `10 -> 99 -> 30`.
4. Verificar que el tamaño siga siendo `3`; la lista conserva la misma cantidad de nodos y el mismo orden de enlaces.

## Criterios de aceptación

- Existe `public void modificar(int posicion, int nuevoDato)`.
- La validación `posicion < 0 || posicion >= size` ocurre antes del recorrido y lanza `IndexOutOfBoundsException` cuando corresponde.
- La búsqueda del nodo se realiza secuencialmente desde `head` con un auxiliar y un contador.
- El método modifica el entero mediante `setDato(nuevoDato)` y no cambia referencias, `head` ni `size`.
- La prueba transforma `10 -> 20 -> 30` en `10 -> 99 -> 30` y confirma que el tamaño permanece igual.
