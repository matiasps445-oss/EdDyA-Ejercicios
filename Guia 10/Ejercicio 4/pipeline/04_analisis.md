# Plan técnico: cola enlazada genérica

## Objetivo

Generalizar la cola enlazada de enteros para que almacene valores de distintos tipos de referencia usando `<T>`, conservando el comportamiento FIFO y la eficiencia de las operaciones estructurales.

## Parametrización de las clases

- Declarar el nodo como `Nodo<T>`; su campo de dato pasa a `T` y su enlace a `Nodo<T>`.
- Declarar la cola como `ColaEnlazada<T>`; las referencias `head` y `tail` deben ser de tipo `Nodo<T>`.
- Las operaciones que reciben o devuelven elementos usan `T`: `encolar(T dato)`, `desencolar(): T`, `consultarFrente(): T` y `contiene(T dato)`.
- `size` sigue siendo `int`, ya que cuenta nodos y no depende de `T`.
- La cola genérica admite tipos de referencia. No se usan tipos primitivos como argumentos genéricos; para enteros se requeriría `Integer`.

## Operaciones y lógica FIFO

Conservar sin cambios los algoritmos de enlaces de la cola enlazada:

- **`encolar(T dato)`:** crear un nodo con `siguiente == null`. Si `tail == null`, asignar el nodo a `head` y `tail`. Si ya hay elementos, enlazar el antiguo `tail` al nodo nuevo y después actualizar `tail`. Incrementar `size` una vez. Complejidad $O(1)$.
- **`desencolar()`:** lanzar `NoSuchElementException` si la cola está vacía. En otro caso, guardar el dato de `head`, avanzar `head` a su sucesor y decrementar `size`. Si `head` queda en `null`, actualizar también `tail = null`. Devolver el dato retirado. Complejidad $O(1)$.
- **`consultarFrente()`:** lanzar `NoSuchElementException` si está vacía; en otro caso, devolver el dato de `head` sin modificar la cola. Complejidad $O(1)$.
- **`estaVacia()`:** comprobar si `head == null`.
- **`contiene(T dato)`:** recorrer desde `head` con un auxiliar `Nodo<T>`. Comparar valores con `Objects.equals(actual.getDato(), dato)` y devolver `true` o `false` sin cambiar la estructura. Complejidad $O(n)$ en el peor caso.
- **`imprimir()`:** recorrer desde `head` a `null` e imprimir los datos de frente a final. Complejidad $O(n)$.
- **`getSize()`:** devolver `size` en tiempo $O(1)$.

La parametrización del contenido no modifica el orden de los nodos: siempre se encola al final (`tail`) y se desencola desde el frente (`head`).

## Clase `Cliente`

Crear una clase propia `Cliente` con atributos básicos `nombre` y `numeroCliente`, un constructor y una representación legible mediante `toString()`. Los nodos almacenan referencias a objetos `Cliente`; `ColaEnlazada<T>` no necesita conocer ni manipular sus campos para mantener el orden FIFO.

Si se comparan clientes diferentes por su valor en `contiene`, implementar `equals` y `hashCode` según los atributos elegidos. Para demostrar la cola basta con encolar y desencolar instancias de `Cliente` y mostrarlas.

## Invariantes y seguridad de tipos

- `head` siempre señala el próximo elemento que saldrá; `tail` señala el último elemento que entró y aún está en la cola.
- Una cola vacía cumple `head == null`, `tail == null` y `size == 0`; si contiene un único nodo, `head == tail`.
- Al desencolar el último nodo, ambas referencias deben quedar en `null`.
- `size` aumenta una vez al encolar y disminuye una vez al desencolar exitosamente.
- `ColaEnlazada<String>` solo acepta cadenas y `ColaEnlazada<Cliente>` solo acepta clientes; no usar tipos crudos ni conversiones forzadas.
- `NoSuchElementException` para desencolar o consultar el frente cuando la cola está vacía se mantiene sin importar el tipo `T`.

## Eficiencia

`encolar` y `desencolar` siguen siendo $O(1)$ gracias a las referencias `tail` y `head`. Consultar frente, comprobar vacío y obtener tamaño también son $O(1)$. Buscar o imprimir recorre los nodos y cuesta $O(n)$ en el peor caso.

## Prueba de demostración en `main`

1. Crear una `ColaEnlazada<String>` y encolar varios nombres de personas. Imprimirla y desencolar al menos un nombre, mostrando que sale el primero que ingresó.
2. Crear una `ColaEnlazada<Cliente>` y encolar varios clientes con nombre y número. Imprimirla y desencolar al menos un cliente, mostrando que se conserva el orden de llegada.
3. Mostrar los elementos retirados y el estado restante de ambas colas para comprobar que comparten la misma implementación genérica y mantienen FIFO.

## Criterios de aceptación

- Existen `Nodo<T>`, `ColaEnlazada<T>` y una clase `Cliente` con nombre y número de cliente.
- `head` y `tail` se tipan como `Nodo<T>` y mantienen sus invariantes en vacío, uno y varios elementos.
- Las firmas que manipulan datos utilizan `T`; `size` permanece como entero.
- La lógica FIFO y los costos de encolar/desencolar permanecen $O(1)$.
- La búsqueda genérica compara con `Objects.equals`, no con `==`.
- `main` encola y desencola valores en colas de `String` y `Cliente`, mostrando resultados claros.
