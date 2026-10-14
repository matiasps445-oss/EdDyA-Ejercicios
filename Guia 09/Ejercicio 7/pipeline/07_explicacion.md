# Explicación: modificar el dato de un nodo

## Cambiar la carga no cambia los enganches

Imaginemos que la lista es un tren: cada nodo es un vagón, el campo `dato` es la carga que lleva y `siguiente` es el enganche que lo conecta con el vagón de adelante.

En la lista `10 -> 20 -> 30`, modificar la posición `1` por `99` es como abrir el segundo vagón y cambiar su carga de `20` a `99`. Los vagones siguen en el mismo orden y unidos con los mismos enganches:

```text
Antes:  head -> [10] -> [20] -> [30] -> null
Después: head -> [10] -> [99] -> [30] -> null
```

El método encuentra ese segundo nodo y ejecuta `actual.setDato(nuevoDato)`. No llama a `setSiguiente()`, porque eso cambiaría el enganche: podría conectar o desconectar vagones y modificar la topología de la lista. La operación solo reemplaza el valor almacenado.

## Por qué `size` no cambia

`size` cuenta cuántos nodos hay, no la suma ni el contenido de sus datos. Antes y después de cambiar `20` por `99`, siguen existiendo los mismos tres nodos. Por eso `modificar` no debe incrementar ni decrementar `size`.

Esto contrasta con insertar o eliminar: esas operaciones agregan o quitan un nodo y, por tanto, sí cambian el tamaño. En la demostración se imprime `3` antes y después de modificar, lo que confirma que se conservó la cantidad de elementos.

## Recorrido y eficiencia

Aunque el número de posición sea conocido, la lista no tiene acceso directo al nodo de esa posición. El método inicia `actual` en `head` y avanza por cada enlace hasta llegar al índice:

```java
Nodo actual = head;
int contador = 0;
while (contador < posicion) {
    actual = actual.getSiguiente();
    contador++;
}
actual.setDato(nuevoDato);
```

El contador comienza en `0`, igual que el índice del primer nodo. Con `posicion == 1`, el ciclo avanza una vez: `actual` pasa del nodo `10` al nodo `20`, el contador llega a `1` y el ciclo termina. Entonces se reemplaza ese dato por `99`.

La validación `posicion < 0 || posicion >= size` se realiza antes de empezar el recorrido. Si el índice no existe, se lanza `IndexOutOfBoundsException` sin tocar la lista.

El costo para llegar a una posición es proporcional a la cantidad de enlaces que hay que recorrer: $O(posicion)$ y, en el peor caso, $O(n)$ para un nodo cercano al final de una lista de $n$ nodos. La actualización del dato en sí es constante, pero primero hay que localizar el nodo.

En un arreglo estático, la posición se usa para calcular directamente dónde está el elemento, por lo que actualizar `arreglo[posicion]` cuesta $O(1)$. La lista enlazada no tiene esa dirección calculable: solo se puede empezar en `head` y seguir las referencias sucesivas.

## Resultado de la prueba en `main`

El programa crea la lista `10 -> 20 -> 30`, registra el tamaño `3`, ejecuta `modificar(1, 99)` y la imprime como `10 -> 99 -> 30`. El orden de los nodos y el tamaño permanecen iguales; solo cambió el dato del nodo central.
