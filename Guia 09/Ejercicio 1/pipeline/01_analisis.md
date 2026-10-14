# Plan técnico: implementar una lista enlazada simple de enteros

## Objetivo

Implementar en Java una lista enlazada simple de números enteros, construida desde cero mediante referencias entre nodos. No utilizar `ArrayList`, `LinkedList` ni otras colecciones de la API de Java.

## Diseño de clases

- **`Nodo`:** representa un elemento de la lista. Contiene un campo entero `dato` y una referencia `siguiente` al próximo nodo; `siguiente` es `null` cuando el nodo es el último.
- **`ListaEnlazada`:** administra los nodos con una referencia `head` al primero y un entero `size` con la cantidad actual de elementos. Ambos atributos deben mantenerse encapsulados.
- Una lista vacía tiene `head == null` y `size == 0`. No se requiere una referencia a cola: para insertar al final se recorrerá la lista desde `head`.

## Operaciones

- **`insertarAlInicio(int dato)`:** crear un nodo cuyo `siguiente` apunte al `head` actual; después asignar ese nodo a `head` e incrementar `size`.
- **`insertarAlFinal(int dato)`:** crear un nodo con `siguiente == null`. Si `head == null`, asignarlo directamente a `head`. En otro caso, recorrer desde `head` hasta el nodo cuyo `siguiente == null` y enlazar allí el nodo nuevo. Incrementar `size` exactamente una vez.
- **`imprimir()`:** recorrer desde `head` siguiendo `siguiente` hasta `null` e imprimir los datos en orden, del primero al último. Definir una salida clara también para la lista vacía.
- **`estaVacia()`:** devolver si `head == null`.
- **`getSize()`:** devolver el valor de `size`.

## Invariantes y casos límite

- `size` siempre coincide con la cantidad de nodos alcanzables desde `head`.
- `head == null` si y solo si la lista está vacía y `size == 0`.
- El último nodo alcanzable tiene `siguiente == null`.
- Insertar al final en una lista vacía debe producir una lista de un nodo sin intentar recorrer desde `null`.
- Insertar al inicio conserva como segundo elemento el antiguo primer nodo.
- Cada inserción incrementa el tamaño una sola vez; imprimir y consultar no modifican la lista ni el tamaño.

## Prueba de demostración en `main`

Incluir una ejecución legible que:

1. Cree una lista y muestre que comienza vacía y con tamaño `0`.
2. Inserte al final en la lista vacía para probar ese caso límite.
3. Inserte otros enteros al inicio y al final, mostrando el tamaño después de las inserciones.
4. Llame a `imprimir()` y compruebe visualmente que el orden mostrado coincide con los enlaces y con el orden esperado.
5. Compruebe que `estaVacia()` devuelve `false` para la lista con elementos.

La secuencia debe permitir verificar la inserción en ambos extremos, el recorrido desde `head` y el mantenimiento de `size`.

## Reglas de entrega

- Al principio del archivo Java debe incluirse el bloque de comentarios requerido por el prompt del ejercicio. Sustituir el texto entre corchetes por el prompt inicial completo proporcionado y conservar el ajuste indicado:

```text
/*
Prompt inicial utilizado:
[Inserta aquí el texto del prompt inicial que comienza con "Quiero implementar en Java..."]

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue lo suficientemente claro y detalló correctamente el uso de la clase Nodo, la referencia head y la actualización del tamaño, por lo que el código generado funcionó a la primera.
*/
```

- Implementar las clases `Nodo` y `ListaEnlazada` con los nombres y métodos especificados.
- No utilizar colecciones de Java; la estructura se construye únicamente con nodos y referencias.
- Incluir en `main` la prueba indicada, con inserciones al inicio y al final, impresión de los elementos y visualización del tamaño.

## Criterios de aceptación

- Existen las clases `Nodo` y `ListaEnlazada`; cada nodo contiene `dato` y `siguiente`.
- `ListaEnlazada` mantiene `head` y `size` con valores coherentes, incluyendo al estar vacía.
- Están implementados `insertarAlInicio(int)`, `insertarAlFinal(int)`, `imprimir()`, `estaVacia()` y `getSize()` con el comportamiento descrito.
- Insertar al final funciona tanto para una lista vacía como para una lista con nodos.
- Las inserciones incrementan `size` una vez y las consultas o la impresión no lo alteran.
- No se usan colecciones de Java.
- `main` muestra una prueba clara con inserciones al inicio y al final, tamaño y salida de la lista.
- El archivo Java comienza con el bloque de comentarios obligatorio y el prompt inicial queda completo en lugar del texto entre corchetes.
