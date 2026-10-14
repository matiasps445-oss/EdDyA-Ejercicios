# Explicación: cola enlazada genérica

## Cómo `<T>` cambia las firmas

La clase declara el parámetro de tipo en su encabezado:

```java
public class ColaEnlazada<T>
```

Ese mismo `T` se usa en los nodos y en los métodos que reciben o devuelven el dato:

```java
public void encolar(T dato)
public T desencolar()
```

Al crear `ColaEnlazada<String>`, el compilador trata esas firmas como si recibieran y devolvieran `String`. Para `ColaEnlazada<Cliente>`, las mismas operaciones reciben y devuelven `Cliente`. El dato de `Nodo<T>` también es `T`, mientras que el enlace sigue siendo `Nodo<T>`.

Por ejemplo, `nombres.encolar("Ana")` guarda una cadena; `nombres.desencolar()` devuelve una cadena. En la otra instancia, `clientes.encolar(clienteUno)` guarda el objeto `Cliente` completo y `clientes.desencolar()` devuelve un `Cliente`, sin conversiones manuales.

## Por qué FIFO no depende del tipo

La cola conserva dos referencias estructurales:

- `head` apunta al nodo que saldrá primero.
- `tail` apunta al último nodo que llegó.

`encolar` conecta el nodo nuevo después de `tail` y mueve `tail`. `desencolar` devuelve el dato de `head` y mueve `head` a su sucesor. Esos pasos solo manipulan referencias a nodos; no necesitan examinar si el dato es un nombre o un cliente.

Con los nombres del `main`, primero se encolan `Ana` y `Luis`:

```text
head -> Ana -> Luis <- tail
```

El primer `desencolar()` devuelve `Ana`, que llegó primero. Con los clientes, `Marta` se encola antes que `Diego`, por lo que también sale primero `Marta`. El orden de llegada se mantiene en ambas instancias: la genericidad cambia el tipo de carga útil, no la regla FIFO.

## Seguridad de tipos en compilación

Cada instancia fija su tipo:

```java
ColaEnlazada<String> nombres = new ColaEnlazada<>();
ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();
```

Si se intenta hacer esto:

```java
nombres.encolar(new Cliente("Marta", 101));
```

el compilador rechaza la llamada con un error de tipos, típicamente “incompatible types: Cliente cannot be converted to String”. El programa no compila, así que no se trata de una excepción que aparezca durante la ejecución. Para ingresar un `Cliente`, se debe usar la cola `ColaEnlazada<Cliente>`.

Esta comprobación evita mezclar elementos incompatibles y permite que `desencolar()` entregue el tipo esperado sin que el usuario tenga que hacer un *cast*. Usar la cola sin `<T>` (tipo crudo) debilitaría esa protección y podría trasladar el problema a la ejecución.

## La clase `Cliente` y su carga útil

`Cliente` contiene `nombre` y `numeroCliente`; `toString()` produce una forma legible para que `imprimir()` muestre esos datos. La cola solo almacena y devuelve la referencia al objeto. No necesita conocer los campos ni cómo se representa el cliente para conectar su nodo.

La búsqueda `contiene` compara mediante `Objects.equals`. Para cadenas, esto compara el contenido. En `Cliente`, la clase de este ejercicio no redefine `equals`, así que una búsqueda por valor funciona cuando se pasa la misma instancia; para considerar equivalentes dos instancias distintas con los mismos campos, habría que definir `equals` y `hashCode` en `Cliente`.
