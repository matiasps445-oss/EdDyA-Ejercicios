# Explicación de `Pila<T>`

`Pila<T>` implementa una pila LIFO de capacidad fija. El parámetro genérico `T` representa el tipo de elemento que elegirá quien cree cada instancia. Por eso una misma clase puede servir para números, textos u otros objetos sin duplicar la lógica de `push`, `pop`, `peek` y las operaciones de estado.

## Qué significa `<T>` en Java

`T` es un parámetro de tipo: una variable que representa un tipo elegido al instanciar la clase. Por ejemplo:

```java
Pila<Integer> pilaNumeros = new Pila<>(3);
Pila<String> pilaTextos = new Pila<>(3);
```

La lógica de la pila se escribe una sola vez. `pilaNumeros` acepta y devuelve `Integer`; `pilaTextos` acepta y devuelve `String`. Sin genéricos se necesitarían clases separadas como `PilaEnteros` y `PilaStrings`, o se almacenarían valores como `Object` y el código cliente tendría que convertirlos manualmente, con más riesgo de errores.

Los tipos genéricos de Java son tipos de referencia. Para números enteros se escribe `Integer`, no el primitivo `int`; Java convierte automáticamente entre ambos en operaciones como `push(10)` (autoboxing) y al recuperar un valor (unboxing si se asigna a `int`).

## Por qué el arreglo interno es `Object[]`

Aunque la clase se declara como `Pila<T>`, Java no permite crear directamente un arreglo genérico como `new T[capacidad]`. Los arreglos conocen su tipo de componente en ejecución, pero los genéricos usan **borrado de tipos** (*type erasure*): la información del parámetro `T` no está disponible como un tipo concreto para crear ese arreglo en tiempo de ejecución.

Por eso el campo se declara como `Object[] elementos`, que puede guardar referencias a distintos objetos. El límite de tipo se mantiene en la interfaz pública de la pila: `push` recibe `T`, y `pop` y `peek` declaran que devuelven `T`.

Al extraer un valor, el método lo convierte de `Object` a `T`:

```java
T elemento = (T) elementos[top];
```

El compilador no puede verificar completamente ese cast, porque el tipo concreto de `T` fue borrado; por eso `pop()` y `peek()` tienen `@SuppressWarnings("unchecked")` aplicado localmente. El cast se mantiene encapsulado dentro de la pila, en vez de pedir a cada usuario que convierta el resultado.

Después de `pop()`, el código asigna `null` a la celda retirada. Esto no cambia el comportamiento LIFO, pero evita que el arreglo conserve innecesariamente una referencia al objeto que ya salió de la pila.

## Cómo evita el compilador mezclar tipos

En `main`, la declaración `Pila<Integer> pilaNumeros` hace que las llamadas normales se comprueben con `Integer` durante la compilación. Por ejemplo:

```java
pilaNumeros.push(10);       // correcto: el int se convierte a Integer
pilaNumeros.push("hola");  // error de compilación
```

La segunda llamada no compila porque una cadena no es un `Integer`. En cambio, `Pila<String>` acepta cadenas. Las dos instancias también tienen arreglos y topes independientes; usar el mismo código de clase no mezcla sus contenidos.

Esta seguridad depende de usar el tipo parametrizado. Si se elimina el parámetro y se usa un **raw type** (`Pila pila`), se pueden introducir valores incompatibles y perder la comprobación de compilación; al recuperarlos podría producirse `ClassCastException`. El arreglo interno `Object[]` no guarda por sí solo una etiqueta que identifique si una instancia era `Pila<Integer>` o `Pila<String>`.

## Flujo de la prueba

1. `pilaNumeros` recibe `10` y luego `20`; `pop()` devuelve `20` y queda `10` en su tope.
2. `pilaTextos` recibe `"hola"` y luego `"mundo"`; `pop()` devuelve `"mundo"` y queda `"hola"` en su tope.
3. `peek()` permite mostrar esos elementos restantes sin retirarlos.

La salida esperada es:

```text
Pop de Pila<Integer>: 20
Tope de Pila<Integer>: 10
Pop de Pila<String>: mundo
Tope de Pila<String>: hola
```

La prueba muestra simultáneamente reutilización, aislamiento entre instancias y LIFO.

## Reflexión sobre el prompt

El prompt inicial justificó con claridad la necesidad y los beneficios de usar genéricos: reutilizar la misma pila para varios tipos y mantener seguridad de tipos. También especificó las operaciones, los límites y la prueba con `Integer` y `String`, por lo que no fue necesario ajustar el prompt para obtener un código versátil y seguro.