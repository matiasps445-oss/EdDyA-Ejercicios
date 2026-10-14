# Explicación de `QuickSortPrimerPivote`

El programa ordena `{5, 3, 8, 4, 2, 7, 1, 10}` con Quicksort recursivo y muestra cada partición antes de ordenar recursivamente sus lados. La versión implementada crea subarreglos explícitos; no reorganiza un único arreglo in-place. `ordenarQuickSort` delega el trabajo al método privado `quickSort`, y `main` imprime la entrada y el resultado devuelto.

## Divide y vencerás

Quicksort representa bien el paradigma **divide y vencerás**:

1. **Divide:** elige un pivote y distribuye los demás elementos entre una partición menor y otra mayor o igual.
2. **Vence:** ordena cada partición por separado llamando al mismo método.
3. **Combina:** concatena la parte izquierda ya ordenada, el pivote y la parte derecha ya ordenada.

La combinación no necesita volver a comparar los elementos. Los elementos de la izquierda son menores que el pivote y los de la derecha son mayores o iguales, así que el orden concatenado es correcto una vez ordenados ambos lados.

## Cómo se forma cada partición

En cada llamada no trivial, el pivote se toma de `numeros[0]`: es el primer elemento de la partición actual, no necesariamente el primer valor del arreglo original. El ciclo comienza en el índice `1` para examinar cada uno de los elementos restantes exactamente una vez:

- Si `numeros[indice] < pivote`, se agrega a `izquierda`.
- En caso contrario, se agrega a `derecha`. Esto incluye valores iguales al pivote, para conservar duplicados.

Una vez distribuidos todos los valores, el programa imprime el pivote y las dos listas. Después convierte cada lista en un `int[]`, llama recursivamente a `quickSort` para la izquierda y luego para la derecha, y usa `System.arraycopy` para formar el resultado con las dos partes ordenadas y el pivote en el centro.

## Recorrido del ejemplo

La primera partición usa el `5`:

```text
Pivote: 5 | Izquierda: [3, 4, 2, 1] | Derecha: [8, 7, 10]
```

El método procesa primero la izquierda. El `3` divide `[3, 4, 2, 1]` en `[2, 1]` y `[4]`. Luego, el `2` divide `[2, 1]` en `[1]` y `[]`. Los subarreglos `[1]`, `[]` y `[4]` ya están ordenados por ser de longitud cero o uno, así que se devuelven directamente.

Al volver de esas llamadas, las combinaciones forman `[1, 2]` y luego `[1, 2, 3, 4]`. Después se procesa la partición derecha original: el pivote `8` divide `[8, 7, 10]` en `[7]` y `[10]`; ambos lados son casos base y se combinan como `[7, 8, 10]`. Por último, el resultado de la raíz es `[1, 2, 3, 4, 5, 7, 8, 10]`.

## Simulación de la pila de llamadas

El método ordena la partición izquierda antes de llamar a la derecha. Mientras una llamada espera el resultado de sus subllamadas, su pivote y la otra mitad quedan pendientes en la pila:

```text
Entrada, descendiendo primero por la izquierda

quickSort([5, 3, 8, 4, 2, 7, 1, 10])  espera ordenar izquierda [3, 4, 2, 1]
└─ quickSort([3, 4, 2, 1])            espera ordenar izquierda [2, 1]
   └─ quickSort([2, 1])               espera ordenar izquierda [1]
      └─ quickSort([1])               caso base: devuelve [1]
      └─ quickSort([])                caso base: devuelve []
```

Cuando se devuelve `[1, 2]`, se completa la rama del pivote `3`; luego la llamada raíz puede continuar con su partición derecha. Al final, cada método pendiente combina los valores que recibió y devuelve una partición más grande ya ordenada.

Los casos base están en `numeros.length <= 1`. Un arreglo vacío no tiene pivote ni necesita ordenarse; uno de un elemento ya está ordenado. Ambos se devuelven sin más llamadas, lo que garantiza que la recursión avance hacia su terminación siempre que cada partición sea más pequeña que la recibida.

## Salida esperada

```text
Arreglo original: [5, 3, 8, 4, 2, 7, 1, 10]
Pivote: 5 | Izquierda: [3, 4, 2, 1] | Derecha: [8, 7, 10]
Pivote: 3 | Izquierda: [2, 1] | Derecha: [4]
Pivote: 2 | Izquierda: [1] | Derecha: []
Pivote: 8 | Izquierda: [7] | Derecha: [10]
Arreglo ordenado: [1, 2, 3, 4, 5, 7, 8, 10]
```

Los arreglos de longitud cero o uno no imprimen una línea de pivote: el caso base retorna antes de elegirlo. Por eso se muestran cuatro particiones no triviales para esta entrada.

## Errores comunes

- **No definir o no alcanzar el caso base:** si la recursión continúa con particiones que no se hacen más pequeñas, la ejecución puede entrar en recursión indefinida y terminar con `StackOverflowError`.
- **Incluir el pivote en una de las subllamadas:** el pivote debe separarse de las particiones antes de recursar. Si una llamada vuelve a recibir el mismo arreglo sin reducirlo, no progresa hacia el caso base.
- **Elegir un pivote distinto al primero de la partición actual:** el requisito es usar `numeros[0]` en cada llamada, también para los subarreglos recursivos.
- **Perder o duplicar elementos durante la distribución:** cada elemento restante debe agregarse exactamente a una partición. Los iguales al pivote van a la derecha según la regla de esta implementación.
- **Combinar en el orden incorrecto:** se debe devolver primero la izquierda ordenada, luego el pivote y luego la derecha ordenada.
- **Confundir subarreglos con índices del arreglo original:** esta implementación crea nuevos arreglos y listas; los índices de una llamada se refieren a la partición recibida.
- **Ignorar el peor caso del pivote inicial:** con entrada ya ordenada, invertida o muchos valores iguales asignados a la derecha, las particiones pueden quedar muy desbalanceadas. El tiempo puede llegar a `O(n^2)` y la profundidad de recursión a `O(n)`, con riesgo de `StackOverflowError` para entradas grandes.

Elegir siempre el primer elemento hace visible el mecanismo de Quicksort y cumple el ejercicio, pero no garantiza divisiones equilibradas. La recursividad termina gracias a los casos base y a que, en cada división, el pivote se retira y los subarreglos recursivos contienen menos elementos.