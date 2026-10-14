# Plan técnico: insertar un nodo en una posición específica

## Objetivo

Añadir a `ListaEnlazada` el método `void insertarEnPosicion(int dato, int posicion)` para insertar un entero en una lista enlazada simple. Debe admitir inserción al inicio, en el medio y al final, preservando todos los enlaces y actualizando `size`.

## Contrato y validación

- Interpretar `posicion` con índice base cero: `0` significa insertar antes del primer nodo y `size` significa insertar después del último.
- Las posiciones válidas son las del intervalo `[0, size]`, inclusive en el extremo final.
- Antes de crear o enlazar nodos, validar `posicion < 0 || posicion > size`. Si se cumple, lanzar `IndexOutOfBoundsException` con un mensaje claro.
- No rechazar `posicion == size`: en una lista no vacía equivale a insertar al final; en una lista vacía equivale a insertar en la posición `0`.

## Algoritmo de inserción

1. Validar la posición antes de modificar la estructura.
2. Crear el nodo nuevo.
3. Si `posicion == 0`, enlazar primero `nuevo.siguiente` con el `head` actual y luego actualizar `head` para que apunte al nodo nuevo. Esto cubre tanto insertar al inicio como insertar en la lista vacía.
4. Para `posicion > 0`, iniciar una referencia auxiliar `anterior` en `head` y recorrer hasta el nodo de índice `posicion - 1`. El auxiliar permite recorrer sin perder el `head`.
5. Reconectar en este orden obligatorio:
   - `nuevo.setSiguiente(anterior.getSiguiente());` conserva en el nodo nuevo la referencia que antes seguía a `anterior`.
   - `anterior.setSiguiente(nuevo);` conecta el nodo anterior con el nuevo.
6. Incrementar `size` exactamente una vez, después de completar la inserción.

No invertir los pasos de reconexión. Si `anterior` apuntara primero a `nuevo`, se sobrescribiría su referencia al resto de la lista antes de guardarla y los nodos posteriores quedarían desconectados.

## Casos y recorrido

- **Inicio (`posicion == 0`):** el nodo nuevo apunta al antiguo `head`; luego el nuevo nodo pasa a ser `head`.
- **Medio (`0 < posicion < size`):** recorrer hasta el nodo anterior a la posición e insertar entre ese nodo y su sucesor actual.
- **Final (`posicion == size`):** recorrer hasta el último nodo. Su `siguiente` es `null`, por lo que el nodo nuevo queda como último. Si la lista estaba vacía, se usa el caso de inicio.
- **Inválida (`posicion < 0` o `posicion > size`):** lanzar `IndexOutOfBoundsException` sin cambiar `head`, los enlaces ni `size`.

Después de cualquier inserción válida, `size` debe coincidir con la cantidad de nodos alcanzables desde `head`, y el último nodo debe conservar `siguiente == null`.

## Eficiencia

- **Tiempo:** $O(posicion)$ porque puede ser necesario recorrer desde `head` hasta el nodo anterior. En el peor caso, insertar al final cuesta $O(n)$.
- **Espacio adicional:** $O(1)$, mediante el nodo nuevo y una referencia auxiliar; no se usan arreglos ni colecciones para realizar la inserción.

## Prueba de demostración

En `main`:

1. Crear una lista e insertar varios elementos al inicio.
2. Insertar un elemento en la posición `size` para demostrar la inserción al final.
3. Insertar un elemento en la posición `1` para comprobar el caso intermedio.
4. Imprimir la lista y el tamaño para verificar el orden y la actualización del contador.
5. Intentar insertar en una posición inválida, como `size + 1` o `-1`, y capturar `IndexOutOfBoundsException` para mostrar el error sin interrumpir abruptamente la demostración.

## Criterios de aceptación

- `ListaEnlazada` implementa `insertarEnPosicion(int dato, int posicion)`.
- La validación rechaza posiciones menores que `0` o mayores que `size`, pero acepta `posicion == size`.
- La inserción inicial actualiza correctamente `head`; la inserción no inicial recorre hasta el nodo anterior.
- En inserciones intermedias y al final, el nodo nuevo conserva primero el enlace anterior (`nuevo.setSiguiente(anterior.getSiguiente())`) y luego el anterior apunta al nuevo (`anterior.setSiguiente(nuevo)`).
- `size` aumenta una sola vez por inserción válida y no cambia ante una posición inválida.
- La demostración cubre inicio, final, posición `1` e intento inválido.
- No se pierde ningún nodo ni se utilizan colecciones de Java.
