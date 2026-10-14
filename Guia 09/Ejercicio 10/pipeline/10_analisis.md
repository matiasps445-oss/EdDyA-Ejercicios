# Plan técnico: convertir la lista de enteros en una lista genérica

## Objetivo

Generalizar la lista enlazada simple para que almacene valores de cualquier tipo de referencia mediante el parámetro de tipo `T`, en lugar de estar limitada a `int`. La misma implementación debe permitir, por ejemplo, listas de `Integer` y de `String`.

## Parametrización de las clases

- Declarar el nodo como `Nodo<T>` y cambiar el dato de `int` a `T`:
  - `private T dato;`
  - `private Nodo<T> siguiente;`
- Declarar la lista como `ListaEnlazada<T>` y tipar su cabeza como `Nodo<T>`.
- Actualizar constructores, parámetros y retornos relacionados con datos para usar `T`. Por ejemplo, insertar recibe `T`, obtener devuelve `T`, y los accesores del nodo usan `T`.
- Mantener `size` como `int`: cuenta nodos y no depende del tipo almacenado.
- En `main`, utilizar `ListaEnlazada<Integer>` para números y `ListaEnlazada<String>` para palabras. Al usar enteros, Java convierte automáticamente entre `int` y `Integer` cuando corresponde; los genéricos no aceptan tipos primitivos directamente.

## Operaciones estructurales

Conservar la lógica de los enlaces: `head`, `getSiguiente()` y `setSiguiente()` continúan conectando nodos sin depender del contenido de `T`. Los ciclos de recorrido, inserción, eliminación e inversión mantienen los mismos pasos; solo sus referencias locales y tipos de nodos se parametrizan, por ejemplo `Nodo<T> actual`.

Actualizar las firmas que manipulan el dato si esas operaciones forman parte de la clase implementada: `insertarAlFinal(T dato)`, `buscar(T dato)`, `obtener(int posicion): T`, `modificar(int posicion, T nuevoDato)`, `eliminar(T dato)` y `contarOcurrencias(T dato)`. Los métodos puramente estructurales, como `invertir()` y `getSize()`, conservan sus firmas de retorno y su algoritmo.

## Comparación de valores genéricos

Al generalizar operaciones que comparan datos, no usar `==` para decidir si dos objetos `T` tienen el mismo valor. `==` puede comparar identidad de referencias. Usar `Objects.equals(actual.getDato(), dato)` (con `java.util.Objects`) para que la comparación funcione por valor y sea segura también si alguno es `null`. Esto no altera los algoritmos de recorrido ni de manipulación de enlaces.

## Invariantes y compatibilidad

- Todos los nodos de una instancia contienen valores compatibles con el mismo `T`; el compilador impide insertar, por ejemplo, una cadena en una `ListaEnlazada<Integer>`.
- Los enlaces siempre conectan `Nodo<T>` con otros `Nodo<T>` del mismo tipo.
- El número de nodos y `size` se mantienen de la misma manera que en la versión de enteros.
- Invertir o recorrer la lista no requiere conocer ni convertir el tipo almacenado.
- No se usan tipos crudos (`Nodo` o `ListaEnlazada` sin `<T>`) ni conversiones forzadas innecesarias.

## Prueba de demostración en `main`

1. Crear una `ListaEnlazada<Integer>`, insertar varios números y mostrarla.
2. Crear una `ListaEnlazada<String>`, insertar varias palabras y mostrarla.
3. Verificar que ambas instancias usan la misma clase genérica, pero almacenan tipos distintos de forma segura en compilación. Se puede ejecutar una operación estructural como invertir sobre ambas listas para demostrar que los enlaces funcionan con cualquier tipo.

## Criterios de aceptación

- Las clases están declaradas como `Nodo<T>` y `ListaEnlazada<T>`.
- El dato del nodo es de tipo `T`; los enlaces y la cabeza son `Nodo<T>`.
- Los parámetros y retornos que representan valores almacenados usan `T`; `size` continúa siendo `int`.
- Se preserva la lógica existente de las operaciones estructurales y se usan tipos genéricos en todas las referencias a nodos.
- Las comparaciones por valor entre datos genéricos usan igualdad de objetos, no `==`.
- `main` crea y usa tanto `ListaEnlazada<Integer>` como `ListaEnlazada<String>` sin tipos crudos.
