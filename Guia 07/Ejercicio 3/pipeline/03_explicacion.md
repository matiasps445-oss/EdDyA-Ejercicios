# Explicación de `OrdenamientoInsercion`

El programa ordena el arreglo `{1, 2, 3, 5, 4, 6, 7}` de menor a mayor con Insertion Sort. El método `ordenarPorInsercion` modifica el arreglo recibido y devuelve el número de desplazamientos. `main` muestra el arreglo final y ese contador.

## Idea del algoritmo

Insertion Sort mantiene una porción ordenada a la izquierda. En cada iteración del `for`, toma el siguiente valor como `clave` y lo inserta en el lugar correcto dentro de esa porción. Para hacer espacio, mueve una posición a la derecha los valores que son mayores que la clave.

La variable `indice` recorre el arreglo desde `1` porque un único elemento en la posición `0` ya está ordenado por sí mismo. La variable `posicion` empieza en el índice anterior a la clave y retrocede mientras encuentre valores que deban quedar después de ella.

## Seguimiento paso a paso

| `indice` | `clave` | Qué sucede | Arreglo tras insertar la clave | Desplazamientos acumulados |
|---:|---:|---|---|---:|
| 1 | 2 | Compara con `1`. Como `1 <= 2`, el `while` termina enseguida; no desplaza. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| 2 | 3 | Compara con `2`. Como `2 <= 3`, termina sin desplazar. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| 3 | 5 | Compara con `3`. Como `3 <= 5`, termina sin desplazar. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| 4 | 4 | Compara con `5`. Como `5 > 4`, mueve `5` una posición a la derecha y suma un desplazamiento. Después compara con `3`; como `3 <= 4`, se detiene e inserta `4` tras el `3`. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |
| 5 | 6 | Compara con `5`. Como `5 <= 6`, termina sin desplazar. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |
| 6 | 7 | Compara con `6`. Como `6 <= 7`, termina sin desplazar. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |

Cuando `indice` vale `4`, el segmento relevante empieza así: `[1, 2, 3, 5, 4]`. El método guarda `4` en `clave`, copia el `5` desde el índice `3` al índice `4`, baja `posicion` al índice `2` y encuentra el `3`. Finalmente escribe la clave en `posicion + 1`, que vuelve a ser el índice `3`. La clave no se pierde porque quedó guardada temporalmente aunque su posición original se haya sobrescrito.

La salida es:

```text
Arreglo ordenado: [1, 2, 3, 4, 5, 6, 7]
Desplazamientos: 1
```

## Por qué el `while` se detiene rápidamente

La condición es:

```java
posicion >= 0 && numeros[posicion] > clave
```

El cuerpo solo se ejecuta si existe una posición anterior y su valor es mayor que la clave. En cuanto encuentra un valor menor o igual, la clave ya debe insertarse inmediatamente después y el `while` se detiene. Para las claves `2`, `3`, `5`, `6` y `7` del ejemplo, basta una comprobación para detenerse; solo la clave `4` necesita desplazar un elemento.

Esta parada temprana es una ventaja natural de Insertion Sort con datos casi ordenados: cada elemento ya está cerca de su posición, así que el bucle interno suele hacer poco trabajo. En una entrada completamente ordenada, el método realiza cero desplazamientos y el `while` falla en su primera condición de valor para cada clave.

## Desplazamiento frente a intercambio

- **Intercambiar**, como hace Bubble Sort, toma dos elementos y cambia sus posiciones entre sí. Bubble Sort suele hacer varios intercambios locales para que un valor avance por el arreglo.
- **Desplazar**, como hace este Insertion Sort, copia un elemento mayor una posición a la derecha para abrir un hueco. La clave se guarda aparte mientras se mueven los demás valores, y al final se escribe una vez en el hueco correcto.

Por eso el contador `desplazamientos` aumenta dentro del `while`, justo después de copiar `numeros[posicion]` a `numeros[posicion + 1]`. La asignación final de `clave` no se cuenta: según la definición del ejercicio, se cuentan solo los movimientos de elementos existentes hacia la derecha. En este caso el `5` se desplaza una posición y ese es el único desplazamiento.

## Errores comunes

- **Acceder al arreglo antes de revisar el límite:** si se evalúa `numeros[posicion]` cuando `posicion` vale `-1`, Java lanza `ArrayIndexOutOfBoundsException` (una subclase de `IndexOutOfBoundsException`). La condición comprueba primero `posicion >= 0`.
- **Invertir el orden de la condición con `&&`:** escribir primero `numeros[posicion] > clave` intentaría acceder al arreglo antes de saber si el índice es válido. Java evalúa de izquierda a derecha y usa cortocircuito, así que la protección solo funciona cuando el límite está a la izquierda.
- **No guardar la clave antes de desplazar:** la posición original de la clave puede sobrescribirse mientras se mueve la porción ordenada; `clave` conserva el valor que se insertará después.
- **Incrementar el contador por la inserción de la clave:** el plan cuenta movimientos de elementos existentes a la derecha, no la escritura final de `clave`.
- **Incrementar el contador por cada comparación:** las comparaciones no son desplazamientos; el contador aumenta solo dentro del bloque que copia un elemento hacia la derecha.
- **Usar `>=` en vez de `>` para desplazar:** con valores repetidos movería también los iguales, haría trabajo innecesario y podría alterar el orden relativo de elementos iguales.
- **Colocar la clave en `posicion` en vez de `posicion + 1`:** después del bucle, `posicion` puede señalar al último elemento menor que la clave; la posición libre correcta es la siguiente.

En resumen, el algoritmo amplía una zona ordenada insertando cada nueva clave. En el arreglo casi ordenado, el `while` se detiene pronto la mayoría de las veces y solo desplaza el `5` para colocar el `4`, de modo que el contador termina en `1`.