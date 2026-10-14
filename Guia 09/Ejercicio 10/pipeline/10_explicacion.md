# Explicación: lista enlazada genérica

## Separación de responsabilidades: estructura y contenido

Una lista enlazada tiene dos aspectos distintos:

- **Estructura:** los enlaces `siguiente` conectan un nodo con el próximo; `head` señala dónde empieza la cadena y `size` cuenta sus nodos.
- **Contenido:** cada nodo guarda un dato. En esta versión, ese dato es de tipo `T`.

`Nodo<T>` representa una caja: contiene un valor `dato` y un enlace `siguiente`. `ListaEnlazada<T>` administra las cajas y sus conexiones. El recorrido y la inversión solo siguen o cambian enlaces; no necesitan saber si el contenido es un número o una palabra. Por eso la misma lógica estructural sirve para ambos tipos.

En el código, `private final T dato` hace que el valor del nodo se establezca al crearlo, mientras que `private Nodo<T> siguiente` puede cambiar para enlazar o invertir la lista. La forma parametrizada del enlace garantiza que un nodo de una lista de tipo `T` apunte a otro nodo del mismo tipo.

## Por qué se usa `Integer` y no `int`

Los parámetros genéricos de Java representan tipos de referencia, no tipos primitivos. Por eso no se puede declarar `ListaEnlazada<int>`. Para guardar enteros se usa la clase envoltorio `Integer`:

```java
ListaEnlazada<Integer> numeros = new ListaEnlazada<>();
numeros.insertarAlFinal(10);
```

El literal `10` es un `int`, pero Java lo convierte automáticamente a `Integer` mediante *autoboxing*. Al recuperar un `Integer` y asignarlo a un `int`, Java puede convertirlo de nuevo mediante *unboxing*. Para palabras se utiliza directamente el tipo de referencia `String`:

```java
ListaEnlazada<String> palabras = new ListaEnlazada<>();
palabras.insertarAlFinal("Java");
```

## Seguridad de tipos en compilación

El parámetro `T` fija qué tipo puede almacenar cada instancia. En `ListaEnlazada<String>`, los métodos que reciben datos aceptan cadenas; en `ListaEnlazada<Integer>`, aceptan enteros envueltos. El compilador rechaza una mezcla accidental, por ejemplo intentar insertar un objeto `Alumno` en una `ListaEnlazada<String>`.

Esta comprobación ocurre antes de ejecutar el programa. Al leer un valor, el tipo ya es conocido y no hace falta convertirlo manualmente ni hacer un *cast*. Eso evita errores tardíos como `ClassCastException` causados por una conversión incompatible.

## Por qué no usar una lista de `Object`

`Object` es el supertipo común de las clases Java, así que una estructura que guarde `Object` permite mezclar cadenas, alumnos y otros objetos. Esa flexibilidad también elimina la garantía de que todos los elementos sean del tipo esperado:

```java
// ListaEnlazada<Object> puede aceptar distintos tipos de objeto.
```

Al obtener un elemento de una lista `Object`, el resultado tiene tipo `Object` y normalmente habría que convertirlo al tipo concreto. Si el objeto no es del tipo supuesto, el error aparecería durante la ejecución. Con `ListaEnlazada<String>`, en cambio, la mezcla se rechaza al compilar y los valores recuperados ya son `String`.

Los genéricos conservan la reutilización de una sola implementación sin renunciar a las comprobaciones estáticas de tipos. Las instancias `ListaEnlazada<Integer>` y `ListaEnlazada<String>` comparten el mismo diseño y los mismos algoritmos, pero cada una limita sus elementos a su tipo declarado.

## Qué demuestra el `main`

El programa crea una lista `Integer` con `10`, `20` y `30`, y otra `String` con `Java`, `Listas` y `Genéricas`. Ambas usan el mismo método `insertarAlFinal` y el mismo `imprimir`, pero almacenan tipos distintos de forma segura:

```text
Lista de Integer:
Lista: 10 -> 20 -> 30
Lista de String:
Lista: Java -> Listas -> Genéricas
```

La estructura cambia de tipo de dato mediante `T`; la lógica de enlaces permanece igual.
