# Explicación: pila enlazada genérica

## Cómo `<T>` previene errores de tipo

`T` es un parámetro de tipo: al crear una instancia se reemplaza por el tipo elegido. Por ejemplo:

```java
PilaEnlazada<Integer> numeros = new PilaEnlazada<>();
PilaEnlazada<Torneo> torneos = new PilaEnlazada<>();
```

En la primera pila, `apilar` acepta un `Integer`; en la segunda, acepta un `Torneo`. Si se intenta escribir `numeros.apilar(new Torneo("Local", 16))`, el compilador informa que `Torneo` no es compatible con `Integer`. El programa no llega a ejecutarse con una pila contaminada por un dato de tipo equivocado.

Esta comprobación ocurre en tiempo de compilación. Al recuperar un elemento, el tipo también está definido (`Integer`, `String` o `Torneo`) y no se necesita convertirlo manualmente. Esto evita errores de ejecución como `ClassCastException` que podrían aparecer al recuperar y convertir un objeto almacenado sin control de tipos.

Los tipos crudos, como `PilaEnlazada pila = new PilaEnlazada();`, omiten la verificación genérica y generan advertencias; permiten mezclar valores y pueden volver a introducir esos errores en tiempo de ejecución. La parametrización mantiene la reutilización del código sin perder la seguridad de tipos.

## Qué cambió y qué permaneció igual

`Nodo<T>` contiene la carga útil en `dato` y el enlace en `siguiente`. `PilaEnlazada<T>` mantiene `head` como referencia al tope. Los métodos que reciben o devuelven el contenido usan `T`: `apilar(T dato)`, `desapilar(): T`, `consultarTope(): T` y `contiene(T dato)`. El enlace se tipa como `Nodo<T>` y `size` continúa siendo `int`, porque cuenta nodos, no valores.

La lógica de los punteros no necesita conocer qué clase concreta tiene `T`. Al apilar, el nodo nuevo apunta al antiguo `head` y luego `head` pasa a apuntar al nuevo nodo. Al desapilar, se guarda el dato del tope y se mueve `head` al siguiente nodo. Esos mismos pasos sirven para números, textos u objetos `Torneo`; solo cambia el tipo del contenido que entra o sale.

Por eso `apilar` y `desapilar` siguen siendo $O(1)$: cada uno hace una cantidad fija de cambios en referencias. La generalización no afecta el orden LIFO ni la topología de la cadena.

## Por qué se usa `Integer` y no `int`

Los argumentos de tipo genérico de Java deben ser tipos de referencia. `int` es primitivo y no se puede escribir `PilaEnlazada<int>`. Se utiliza su clase envoltorio `Integer`:

```java
PilaEnlazada<Integer> numeros = new PilaEnlazada<>();
numeros.apilar(10);
```

Java convierte automáticamente el `int` literal `10` en `Integer` mediante *autoboxing*. Al usar el valor como `int`, también puede realizar *unboxing*. Para textos se usa `String`, y para la clase propia se usa `Torneo`.

## Comparar datos genéricos

En `contiene`, la pila utiliza `Objects.equals(actual.getDato(), dato)`. Esto compara por igualdad definida por los objetos y evita depender de `==`, que para referencias comprueba si son exactamente el mismo objeto. Para `Integer` y `String`, `equals` compara sus valores. En el `main`, la búsqueda del torneo pasa la misma instancia insertada; si se quisieran considerar iguales dos instancias `Torneo` distintas según nombre y participantes, habría que definir `equals` (y su correspondiente `hashCode`) en esa clase.

## Qué demuestran las tres pruebas

- **`PilaEnlazada<Integer>`:** apila `10` y `20`; el tope es `20`, buscar `10` da `true` y al desapilar sale `20`.
- **`PilaEnlazada<String>`:** apila `"Primero"` y `"Segundo"`; el tope y el primer desapilado son `"Segundo"`.
- **`PilaEnlazada<Torneo>`:** apila dos torneos con nombre y cantidad de participantes; se imprimen con `toString()`, el torneo más reciente queda arriba y sale primero al desapilar.

Las tres pilas comparten la misma estructura y los mismos algoritmos, mientras el compilador mantiene separado el tipo permitido por cada instancia.
