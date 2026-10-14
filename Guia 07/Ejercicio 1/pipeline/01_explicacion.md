# Explicación de `OrdenamientoBurbuja`

El programa ordena el arreglo `{64, 34, 25, 12, 22, 11, 90}` de menor a mayor con Bubble Sort. Primero imprime el arreglo original; luego, el método `ordenarBurbuja` modifica ese mismo arreglo y muestra su estado completo al terminar cada pasada.

## Los dos ciclos `for`

El ciclo externo controla las pasadas:

```java
for (int pasada = 0; pasada < numeros.length - 1; pasada++)
```

El arreglo de ejemplo tiene siete elementos, así que el ciclo realiza seis pasadas, con `pasada` desde `0` hasta `5`. Cada pasada completa mueve al menos el mayor valor que aún estaba en la zona activa hasta el extremo derecho de esa zona.

El ciclo interno compara los pares adyacentes:

```java
for (int indice = 0; indice < numeros.length - 1 - pasada; indice++)
```

En cada paso compara `numeros[indice]` con `numeros[indice + 1]`. Si el de la izquierda es mayor, el bloque `if` los intercambia usando `temporal` para guardar uno de los valores mientras se reasignan las posiciones. Si están en orden ascendente, no hace falta cambiarlos.

Para el arreglo de siete elementos, el último índice que alcanza `indice` es cada vez menor:

| Pasada | Comparaciones adyacentes | Último índice `indice` |
|---|---:|---:|
| 1 | 6 | 5 |
| 2 | 5 | 4 |
| 3 | 4 | 3 |
| 4 | 3 | 2 |
| 5 | 2 | 1 |
| 6 | 1 | 0 |

La expresión `numeros.length - 1 - pasada` evita dos problemas: no deja que `indice + 1` salga del arreglo, y evita volver a comparar la parte final que ya quedó ordenada.

## Por qué el mayor avanza hacia el final

Al comparar dos vecinos, el mayor de los dos queda a la derecha después de un intercambio. En la comparación siguiente, ese valor vuelve a compararse con el vecino de su derecha. Si sigue siendo mayor, vuelve a avanzar una posición. Por eso un valor grande puede recorrer varias posiciones en una sola pasada, como si fuera desplazándose hacia el final.

Al completar una pasada, el mayor valor de la zona no ordenada queda en su posición definitiva. En la siguiente pasada ya no es necesario incluirlo; por eso se resta `pasada` al límite del ciclo interno. El ciclo externo avanza y la zona pendiente se hace más corta desde la derecha.

## Seguimiento del ejemplo

El resultado que imprime el programa es:

```text
Arreglo original: [64, 34, 25, 12, 22, 11, 90]
Pasada 1: [34, 25, 12, 22, 11, 64, 90]
Pasada 2: [25, 12, 22, 11, 34, 64, 90]
Pasada 3: [12, 22, 11, 25, 34, 64, 90]
Pasada 4: [12, 11, 22, 25, 34, 64, 90]
Pasada 5: [11, 12, 22, 25, 34, 64, 90]
Pasada 6: [11, 12, 22, 25, 34, 64, 90]
```

En la primera pasada, `64` supera a cada vecino sucesivo y avanza hasta el índice `5`; el `90` ya era mayor, así que queda en el último índice. La segunda pasada solo recorre hasta el índice `4`, y deja `34` al final de la zona pendiente. El mismo patrón continúa hasta que el arreglo queda ordenado en la quinta pasada. La sexta pasada compara el único par de la zona que queda y no necesita intercambiarlo.

## Por qué se imprime después de cada pasada

La impresión está fuera del ciclo interno, pero dentro del externo. Así se muestra una instantánea del arreglo después de que todos los pares de una pasada fueron comparados y, cuando hacía falta, intercambiados. Si se imprimiera dentro del ciclo interno, se vería una línea por comparación y sería más difícil distinguir el resultado de cada pasada. Si se imprimiera solo al final, se perdería la oportunidad de observar cómo los valores grandes se van fijando al final.

La salida paso a paso permite comprobar visualmente tanto la corrección como el progreso: el segmento ordenado crece desde la derecha y, al terminar, todos los valores aparecen en orden ascendente.

## Ineficiencias y complejidad

- **Arreglos grandes:** Bubble Sort vuelve a recorrer repetidamente pares de elementos. En esta implementación, el número de comparaciones es `n(n - 1) / 2`, por lo que el tiempo crece como `O(n^2)`. Para muchos datos suele ser mucho más lento que algoritmos como Merge Sort o Quick Sort.
- **Arreglo ya ordenado:** la condición de intercambio nunca se cumple, así que se hacen cero intercambios. Sin embargo, el código no detecta que ya está ordenado: sigue realizando las `n - 1` pasadas y las `n(n - 1) / 2` comparaciones. Una bandera que detectara si hubo algún intercambio permitiría terminar antes, pero no forma parte de esta implementación y reduciría el número de pasadas impresas.
- **Espacio:** el ordenamiento se hace directamente en el arreglo recibido y solo usa una variable temporal para intercambiar dos valores, por lo que el espacio auxiliar es `O(1)`.

En resumen, los ciclos anidados implementan la comparación repetida de vecinos: el externo cuenta pasadas y el interno recorre una zona que se reduce desde la derecha. La impresión al final de cada pasada hace visible ese avance, aunque el algoritmo siga siendo costoso para entradas grandes y no aproveche que los datos ya estén ordenados.