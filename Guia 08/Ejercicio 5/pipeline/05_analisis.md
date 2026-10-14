# Plan técnico: implementar una pila genérica

## Objetivo

Definir una clase Java `Pila<T>` que use un arreglo interno de capacidad fija para almacenar elementos de cualquier tipo de referencia. La pila debe ofrecer `push`, `pop`, `peek`, `isEmpty`, `isFull` y `size`, conservando el comportamiento LIFO y la seguridad de tipos de los genéricos.

## Diseño de la clase

- **Clase:** `public class Pila<T>`.
- **Almacenamiento interno:** usar `Object[] elementos`. Java no permite crear directamente un arreglo de tipo genérico con `new T[capacidad]` por el borrado de tipos (type erasure).
- **Tope:** usar `int top`, inicializado en `-1` para representar la pila vacía.
- **Capacidad:** recibirla en el constructor; rechazar valores menores que uno con `IllegalArgumentException`.
- **Encapsulamiento:** mantener el arreglo y el tope como campos `private`.
- **Casts:** `pop` y `peek` convierten el objeto leído de `Object[]` a `T`. Mantener el cast y su `@SuppressWarnings("unchecked")` en el menor ámbito posible; el tipo genérico de la API restringe los valores insertados cuando se usa correctamente.

## Operaciones

- **`push(T elemento)`:** comprobar `isFull()` antes de cambiar el tope. Si está llena, lanzar `IllegalStateException`; si hay espacio, incrementar `top` y guardar el elemento en `elementos[top]`.
- **`pop()`:** comprobar `isEmpty()`; si está vacía, lanzar `IllegalStateException`. Si contiene elementos, obtener y convertir `elementos[top]`, limpiar esa celda asignándole `null`, decrementar `top` y devolver el elemento.
- **`peek()`:** comprobar `isEmpty()` y devolver el elemento en `elementos[top]` convertido a `T`, sin modificar el tope.
- **`isEmpty()`:** devolver `top == -1`.
- **`isFull()`:** devolver `top == elementos.length - 1`.
- **`size()`:** devolver `top + 1`.

Limpiar la celda en `pop` evita que el arreglo conserve una referencia a un objeto que ya no forma parte de la pila. No se debe acceder al arreglo ni cambiar `top` antes de validar las condiciones de lleno o vacío.

## Seguridad de tipos y reutilización

La misma clase se puede instanciar como `Pila<Integer>` o `Pila<String>`. El parámetro `T` expresa en compilación qué tipo acepta cada pila y permite que `pop` y `peek` devuelvan ese tipo sin que el código cliente tenga que hacer casts. Una `Pila<Integer>` no debería aceptar una cadena cuando se usa con tipos genéricos correctamente; los raw types pueden evitar esa protección y no son parte del uso esperado.

El arreglo interno es `Object[]` por una limitación del sistema de tipos de Java, pero los casts quedan confinados a los métodos de extracción. Como la clase solo inserta elementos recibidos como `T` y los recupera desde su propio arreglo, conserva la correspondencia de tipos mientras se use la API genérica sin raw types.

## Prueba de demostración en `main`

1. Crear `Pila<Integer>` y `Pila<String>`, ambas con capacidad suficiente.
2. Agregar dos números a la primera y dos textos a la segunda.
3. Ejecutar un `pop` en cada pila y mostrar los elementos devueltos; deben ser los últimos que se agregaron.
4. Mostrar `peek` y `size` para verificar que cada pila mantiene el elemento anterior en el tope.

Por ejemplo:

```text
Pop de Pila<Integer>: 20
Tope de Pila<Integer>: 10
Pop de Pila<String>: mundo
Tope de Pila<String>: hola
```

La prueba debe dejar claro que la misma implementación almacena ambos tipos sin mezclar elementos entre pilas.

## Casos límite

- `pop` o `peek` sobre una pila vacía deben lanzar `IllegalStateException` antes de acceder a `elementos[-1]`.
- `push` sobre una pila llena debe lanzar `IllegalStateException` antes de incrementar `top` o escribir después del último índice.
- El constructor debe rechazar capacidad cero o negativa con `IllegalArgumentException`.

## Comentarios requeridos en el código

- Explicar cómo `T` permite reutilizar la clase con distintos tipos y proporciona comprobación de tipos en compilación.
- Aclarar por qué el almacenamiento se declara como `Object[]` y dónde se realizan los casts.
- Indicar que `top` comienza en `-1` y cómo cambian `push` y `pop`.
- Explicar la validación de los estados lleno y vacío antes de acceder al arreglo.
- Describir en `main` que las dos instancias son pilas independientes de tipos diferentes.

## Criterios de aceptación

- La clase se declara como `Pila<T>` y usa un arreglo interno de tamaño fijo.
- Se implementan las seis operaciones requeridas con el tipo genérico adecuado.
- Las operaciones protegen los límites de la pila y lanzan excepciones claras.
- La implementación puede instanciarse como `Pila<Integer>` y `Pila<String>` sin duplicar la lógica.
- `main` ejecuta un `pop` en cada pila y muestra los valores retirados junto con sus tipos y, de forma útil, los topes restantes.
- La demostración evidencia LIFO y que no se mezclan los tipos entre instancias.