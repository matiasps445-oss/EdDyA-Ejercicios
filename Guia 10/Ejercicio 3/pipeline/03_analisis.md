# Plan técnico: pila enlazada genérica

## Objetivo

Generalizar la pila enlazada de enteros para almacenar distintos tipos de referencia mediante `<T>`, conservando el comportamiento LIFO y la lógica estructural de la implementación anterior. El compilador debe comprobar que cada pila reciba y devuelva el tipo que declara.

## Parametrización de las clases

- Declarar el nodo como `Nodo<T>` y cambiar su campo de dato a `private final T dato`; el enlace debe ser `private Nodo<T> siguiente`.
- Declarar la pila como `PilaEnlazada<T>` y su referencia `head` como `Nodo<T>`.
- El tope sigue siendo `head`. Los métodos que reciben o devuelven elementos pasan a usar `T`: `apilar(T dato)`, `desapilar(): T`, `consultarTope(): T` y, si se conserva, `contiene(T dato)`.
- `size` permanece como `int`; no depende del tipo almacenado.
- Los genéricos de Java requieren tipos de referencia. Para enteros se usa `Integer`, no `int`; Java aplica autoboxing al pasar valores enteros.

## Operaciones y lógica estructural

Conservar sin cambios el algoritmo de pila:

- **`apilar(T dato)`:** crear un `Nodo<T>`, enlazarlo antes del `head` actual y actualizar `head`. Incrementar `size`. Complejidad $O(1)$.
- **`desapilar()`:** lanzar `EmptyStackException` si está vacía; de lo contrario, guardar y devolver el dato del tope, mover `head` a su sucesor y decrementar `size`. Complejidad $O(1)$.
- **`consultarTope()`:** devolver el dato de `head` sin modificar la pila; lanzar `EmptyStackException` si está vacía. Complejidad $O(1)$.
- **`estaVacia()`:** comprobar `head == null`.
- **`contiene(T dato)`:** recorrer los nodos con `Nodo<T> actual`; para comparar valores genéricos, usar `Objects.equals(actual.getDato(), dato)` en lugar de `==`. Complejidad $O(n)$ en el peor caso.
- **`imprimir()`:** recorrer desde `head` hasta `null` e imprimir valores de tope a fondo.

Los cambios de tipo no deben alterar los enlaces ni el orden LIFO. Si la implementación previa incluye otras operaciones sobre el dato, adaptar sus firmas a `T` sin cambiar sus recorridos estructurales.

## Modelo `Torneo`

Definir una clase sencilla `Torneo` para la demostración, con al menos `nombre` y `cantidadParticipantes`, constructor y una representación legible, por ejemplo mediante `toString()`. Esta clase se almacenará como dato dentro de `Nodo<Torneo>`; la pila solo la conecta y no necesita conocer su estructura interna.

## Seguridad de tipos e igualdad

Usar el parámetro `T` permite declarar instancias independientes como `PilaEnlazada<Integer>`, `PilaEnlazada<String>` y `PilaEnlazada<Torneo>`. Cada una acepta únicamente elementos compatibles con su tipo declarado. No usar tipos crudos ni conversiones forzadas.

Para `contiene`, comparar con `Objects.equals` para admitir correctamente objetos y valores nulos. Si se desea que dos objetos `Torneo` distintos se consideren iguales por sus atributos, `Torneo` debe definir `equals`/`hashCode`; para una prueba que consulta la misma instancia, no hace falta esa extensión.

## Invariantes y eficiencia

- `head` siempre apunta al elemento más recientemente apilado que aún está en la pila.
- La cadena `Nodo<T>` enlazada desde `head` contiene los elementos de tope a fondo.
- `size` incrementa al apilar y decrementa solo al desapilar exitosamente.
- Las operaciones `apilar` y `desapilar` continúan siendo $O(1)$; consultar el tope y comprobar vacío también son $O(1)$.
- Buscar o imprimir requiere recorrer nodos: $O(n)$ en el peor caso.
- Una pila vacía cumple `head == null` y `size == 0`; desapilar o consultar el tope en ese estado lanza `EmptyStackException`.

## Prueba de demostración en `main`

Instanciar y usar las tres pilas:

1. `PilaEnlazada<Integer>` con varios números; apilar, mostrar el tope y el contenido.
2. `PilaEnlazada<String>` con varios textos; apilar, mostrar el tope y el contenido.
3. `PilaEnlazada<Torneo>` con al menos dos objetos `Torneo` con nombres y cantidades de participantes; apilar y mostrar el tope y el contenido de forma legible.

Las pruebas deben demostrar que se reutiliza la misma implementación, que el orden LIFO se conserva y que cada lista mantiene elementos de su tipo.

## Criterios de aceptación

- Existen `Nodo<T>` y `PilaEnlazada<T>`, con `head` y enlaces también parametrizados.
- Las firmas que reciben o devuelven elementos usan `T`; el contador continúa siendo `int`.
- El algoritmo de apilar/desapilar conserva LIFO y sus costos $O(1)$; las excepciones de pila vacía se mantienen.
- Las comparaciones por valor genérico usan `Objects.equals`, no `==`.
- `Torneo` representa al menos nombre y cantidad de participantes y puede mostrarse claramente.
- `main` prueba pilas `Integer`, `String` y `Torneo` sin tipos crudos.
