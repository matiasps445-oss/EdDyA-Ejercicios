# Explicación de `PeorCasoQuickSort`

El programa aplica Quicksort al arreglo ascendente `[1, 2, 3, 4, 5, 6, 7]` y devuelve otro arreglo ordenado. Como la entrada ya está ordenada, el resultado se ve igual que la entrada; el propósito principal es observar cómo la elección del pivote afecta el trabajo recursivo.

## Flujo de las llamadas en este ejemplo

El método auxiliar `quickSort` comprueba primero si la partición tiene longitud cero o uno. Si es así, devuelve ese arreglo directamente. En cualquier partición más grande, toma `numeros[0]` como pivote, distribuye los demás valores y llama a Quicksort para la izquierda y después para la derecha.

En la raíz, el pivote es `1`. No hay valores menores, de modo que se obtiene:

```text
Pivote 1: izquierda [] | derecha [2, 3, 4, 5, 6, 7]
```

La llamada izquierda vacía llega inmediatamente al caso base. Luego, la llamada derecha elige `2` y repite el mismo patrón:

```text
quickSort([1, 2, 3, 4, 5, 6, 7])  pivote 1; espera primero la izquierda []
└─ quickSort([])                  caso base; devuelve []
└─ quickSort([2, 3, 4, 5, 6, 7])  pivote 2; espera primero la izquierda []
   └─ quickSort([])               caso base; devuelve []
   └─ quickSort([3, 4, 5, 6, 7])  pivote 3
      └─ quickSort([])            caso base
      └─ quickSort([4, 5, 6, 7])  pivote 4
         └─ ... continúa hasta [7]
```

Cada llamada de la rama derecha queda pendiente mientras se completa la siguiente llamada más pequeña. Cuando se llega a una partición de un elemento, la recursión alcanza el caso base y empieza a retornar. En cada nivel, se combinan la izquierda vacía, el pivote y la derecha ordenada. El resultado final es `[1, 2, 3, 4, 5, 6, 7]`.

## Por qué es el peor caso

Quicksort funciona mejor cuando el pivote separa la partición en dos partes de tamaños parecidos. Si hay siete elementos y el pivote los divide aproximadamente por la mitad, las llamadas trabajan con problemas bastante menores en cada nivel.

Aquí, el primer elemento es siempre el menor de su partición. Por eso cada división deja una izquierda vacía y una derecha con todos los demás elementos. En vez de reducir el tamaño de la rama principal aproximadamente a la mitad, lo reduce de uno en uno:

```text
7 elementos -> 6 -> 5 -> 4 -> 3 -> 2 -> 1
```

La suma del trabajo de partición es proporcional a `6 + 5 + 4 + 3 + 2 + 1 = 21`, que crece como `O(n^2)`. La cadena también mantiene llamadas pendientes hasta profundidad `O(n)`. El mismo problema ocurre con el arreglo en orden inverso cuando siempre se elige el primer elemento, porque ese pivote queda en el extremo opuesto de la partición.

## Qué significa el contador mostrado

`ContadorLlamadas` es un objeto mutable compartido por toda la ejecución. Antes de cada llamada recursiva a una partición hija, `contador.cantidad` aumenta en uno. Se cuentan las llamadas a particiones vacías o unitarias porque son llamadas reales que entran al caso base. La primera llamada del método auxiliar desde `ordenarQuickSort` no se cuenta.

Para este arreglo hay seis particiones no triviales, de tamaños `7`, `6`, `5`, `4`, `3` y `2`. Cada una hace dos llamadas hijas, así que la salida indica:

```text
Llamadas recursivas (sin la inicial): 12
```

Si también se cuenta la primera invocación a `quickSort`, se obtienen 13 invocaciones en total. La cifra 12 es alta para una entrada de solo siete elementos y muestra que la recursión se está encadenando en lugar de dividirse en ramas equilibradas. Sin embargo, el número de llamadas por sí solo no mide todo el tiempo: cada partición también recorre sus elementos para construir las listas.

## Alternativas para escoger el pivote

- **Elemento central por índice:** en una entrada ordenada, el elemento central suele dividir los valores aproximadamente a la mitad y reduce la profundidad recursiva. Para datos arbitrarios, el elemento central por posición no necesariamente es el valor mediano, así que no garantiza siempre una división equilibrada.
- **Pivote aleatorio:** reduce la probabilidad de elegir repetidamente un extremo y produce un tiempo esperado de `O(n log n)`. Aun así, existe un peor caso de `O(n^2)`.
- **Mediana de tres:** elegir la mediana entre el primero, el central y el último suele evitar algunos casos ordenados o casi ordenados, aunque tampoco elimina todos los casos desfavorables.

En este ejercicio se conserva deliberadamente el primer elemento como pivote para poder observar el peor caso. Las alternativas son mejoras posibles para otras implementaciones, no cambios que deban hacerse a esta demostración.

## Errores comunes

- **Contar solo las particiones no triviales:** el código cuenta también las llamadas hijas vacías o unitarias. Omitirlas produciría una cifra diferente a la convención definida.
- **Incluir o excluir la llamada inicial sin decirlo:** el valor mostrado es 12 llamadas hijas; contando también la raíz son 13 invocaciones a `quickSort`.
- **No incrementar antes de llamar:** el contador debe aumentar una vez por cada llamada recursiva hija.
- **Elegir el pivote del arreglo original en cada nivel:** debe ser `numeros[0]` de la partición actual.
- **No tener caso base para longitud cero o uno:** sin esta detención, la recursión puede no terminar y acabar en `StackOverflowError`.
- **Suponer que Quicksort siempre es `O(n log n)`:** esa complejidad describe el caso promedio o particiones equilibradas; con este pivote y esta entrada, el tiempo es `O(n^2)`.

La cantidad de llamadas no es eficiente para esta entrada pequeña, pero es justamente lo que la demostración busca hacer visible: elegir siempre el primer elemento como pivote en datos ya ordenados transforma una división equilibrada en una secuencia de problemas que decrecen de uno en uno.