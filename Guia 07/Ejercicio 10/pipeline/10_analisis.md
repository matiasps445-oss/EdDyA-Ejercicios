# Plan técnico: sistema de ranking de puntajes

## Objetivo

Implementar en Java un programa que ordene un arreglo de jugadores por puntaje descendente usando MergeSort y muestre el ranking final, del puntaje más alto al más bajo.

## Diseño de los datos

- Definir una clase `Jugador` con `nombre` y `puntaje`.
- Mantener los campos `private` y `final`, inicializados mediante un constructor; exponer los valores mediante getters.
- Proporcionar una representación legible, por ejemplo con `toString()`, para imprimir el nombre y el puntaje juntos.
- Usar el arreglo de demostración: `new Jugador("Ana", 1200)`, `new Jugador("Pedro", 900)` y `new Jugador("Lucia", 1500)`.

## Elección del algoritmo

Utilizar MergeSort porque mantiene tiempo `O(n log n)` en los casos promedio y peor, y puede ser estable. Esto ofrece un costo predecible cuando crece la cantidad de jugadores y evita el peor caso cuadrático que puede sufrir Quicksort con una selección de pivote inadecuada.

MergeSort requiere espacio auxiliar `O(n)` para combinar los elementos. Para que el ordenamiento sea estable, la fase de fusión debe conservar el orden original de los jugadores con el mismo puntaje.

## Estrategia de ordenamiento

1. Implementar un método que ordene el arreglo de jugadores por rango: si el rango tiene cero o un elemento, devolverlo como base; de lo contrario, dividirlo por el índice medio.
2. Ordenar recursivamente las mitades izquierda y derecha.
3. Fusionar ambas mitades comparando sus puntajes. Para orden descendente, elegir primero el jugador con mayor puntaje.
4. Si los puntajes son iguales, elegir primero el elemento de la mitad izquierda. Así se conserva el orden relativo original y MergeSort sigue siendo estable.
5. Copiar los elementos restantes de la mitad que no se haya agotado.
6. Mostrar el arreglo resultante en `main` como ranking final.

La condición esencial para seleccionar el siguiente jugador en la fusión es equivalente a comparar si `izquierda.getPuntaje() >= derecha.getPuntaje()`. El operador `>=` invierte el criterio habitual ascendente y coloca los puntajes altos primero; además, prioriza la mitad izquierda en caso de empate.

## Flujo principal

1. Crear el arreglo inicial de jugadores.
2. Invocar el método de ordenamiento por MergeSort.
3. Imprimir cada jugador del arreglo ordenado, con nombre y puntaje.

## Resultado esperado

```text
Lucia (1500)
Ana (1200)
Pedro (900)
```

## Comentarios requeridos en el código

- Explicar que `Jugador` agrupa el nombre y el puntaje, y que los campos encapsulados se consultan mediante getters.
- Señalar dónde se obtiene el puntaje para compararlo durante la fusión.
- Explicar que el criterio descendente elige el puntaje mayor primero.
- Aclarar cómo elegir la mitad izquierda en un empate conserva la estabilidad.
- Diferenciar la división recursiva de MergeSort de la fusión ordenada de jugadores.

## Criterios de aceptación

- El arreglo contiene los tres jugadores especificados.
- El algoritmo usado es MergeSort y ordena por puntaje, no por nombre.
- La comparación durante la fusión deja el puntaje mayor antes que el menor.
- La salida final presenta `Lucia (1500)`, `Ana (1200)` y `Pedro (900)` en ese orden.
- Los comentarios explican la clase `Jugador`, la lectura y comparación del puntaje, y el criterio descendente.
- La estrategia mantiene MergeSort estable al preservar el orden previo para puntajes iguales.