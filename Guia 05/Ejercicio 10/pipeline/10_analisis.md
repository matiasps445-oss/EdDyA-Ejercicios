# Analisis tecnico: ordenamiento burbuja (Bubble Sort)

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara una implementacion Java del algoritmo Bubble Sort, explicando por que es adecuado principalmente para fines didacticos y conjuntos pequeños de datos, y pidiendo que el programa cuente comparaciones e intercambios.

## Supuestos adoptados

- El problema se resolvera sobre un arreglo unidimensional `int[]`.
- El orden deseado sera ascendente.
- El metodo reutilizable devolvera el arreglo ordenado y, ademas, entregara una estructura con el conteo de comparaciones e intercambios. Por ejemplo: `ResultadoOrdenacion` o un arreglo de dos enteros.
- Si la entrada es `null`, se rechazara con `IllegalArgumentException`, porque no es posible ordenar una referencia inexistente.
- Se permitira un arreglo vacio y un arreglo con un solo elemento; en ambos casos no hay intercambios ni comparaciones relevantes.
- No se pedira entrada por consola; la demostracion usara arreglos fijos en `main` para mostrar el comportamiento.
- El algoritmo no requiere una optimizacion especial para conjuntos grandes; la prioridad es la claridad didactica.
- La implementacion no debe modificar la estructura del problema ni agregar complejidad innecesaria: es un ejercicio de algoritmos basicos.

## Estrategia tecnica

Implementar un metodo reutilizable, por ejemplo `ordenarBurbuja(int[] numeros)`:

1. Validar que el arreglo no sea `null`.
2. Recorriendo el arreglo desde el inicio hasta `n - 1`, comparar elementos adyacentes.
3. Si el elemento actual es mayor que el siguiente, intercambiar ambos valores.
4. Cada comparacion incrementa el contador de comparaciones.
5. Cada intercambio incrementa el contador de intercambios.
6. Repetir el proceso hasta completar las pasadas necesarias para dejar el arreglo ordenado.
7. Devolver el arreglo ordenado junto con el conteo total de comparaciones e intercambios.

## Justificacion del enfoque

Bubble Sort es intuitivo para aprender porque compara dos elementos vecinos y mueve el mayor hacia el final de cada pasada. Esa idea es sencilla de visualizar y facilita la comprension del concepto de intercambio y del hecho de que el algoritmo debe repetir operaciones hasta que el arreglo quede completamente ordenado.

El conteo de comparaciones e intercambios es importante porque permite observar el costo real del algoritmo y poner en evidencia que, aunque es facil de implementar, no es eficiente para colecciones grandes. En un arreglo desordenado, cada elemento puede moverse varias posiciones, y cada pasada requiere volver a comparar elementos que ya se sabe que quedaron ordenados parcialmente.

## Casos limite importantes

- `int[] numeros = null` -> deberia rechazarse.
- Arreglo vacio `new int[0]` -> no hay comparaciones ni cambios.
- Arreglo de un elemento -> no requiere ordenamiento adicional.
- Arreglo ya ordenado -> se realizan comparaciones, pero no hay intercambios.
- Arreglo con elementos repetidos -> el algoritmo debe mantener los valores en su posicion relativa si el criterio es solo comparacion numerica; no se requieren reglas adicionales.
- Arreglo con numeros negativos y positivos -> la comparacion funciona igual, siempre que se compare por valor numerico.

## Complejidad esperada

Sean `n` la cantidad de elementos del arreglo:

- En el peor caso, el algoritmo hace `O(n^2)` comparaciones.
- En el peor caso, tambien puede realizar `O(n^2)` intercambios.
- El tiempo total es `O(n^2)`.
- El espacio auxiliar es `O(1)`, porque solo requiere variables temporales para el intercambio y contadores, sin crear arreglos adicionales.

## Motivo para limitarlo a conjuntos pequenos

Bubble Sort es pedagogicamente valioso, pero no suele ser el metodo elegido para grandes volumenes de datos. En aplicaciones reales, se prefieren algoritmos mas eficientes como insercion, seleccion, mergesort o quicksort, dependiendo del caso de uso.

La razon es que Bubble Sort revisa repetidamente elementos que ya no necesitan ser comparados, lo que hace crecer el costo cuadraticamente en funcion de la cantidad de datos. Para fines academicos, sin embargo, su simplicidad y la forma en que hace visible el proceso de intercambio lo convierten en una excelente introduccion a la ordenacion.

## Flujo esperado de la demostracion

1. Definir un arreglo desordenado, por ejemplo `{5, 1, 4, 2, 8}`.
2. Ejecutar el metodo de ordenamiento.
3. Mostrar el arreglo resultado ordenado.
4. Mostrar la cantidad de comparaciones e intercambios realizadas.
5. Explicar que la cantidad de comparaciones suele ser mayor en arreglos desordenados y que el algoritmo no es ideal para grandes volumenes de datos.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique el concepto de Bubble Sort y por que recorre el arreglo varias veces.
2. Presente codigo Java comentado con un metodo reutilizable.
3. Valide referencia `null` y considere arreglos vacios y de un elemento.
4. Cuente comparaciones e intercambios de forma clara.
5. Muestre un ejemplo fijo en `main` con salida por consola.
6. Indique complejidad temporal `O(n^2)` y espacio auxiliar `O(1)`.
7. Aclare que es adecuado principalmente para fines didacticos y listas pequenas.

No se solicita entrada por consola dinamica, ordenamiento descendente ni optimizaciones avanzadas. El foco debe estar en la claridad del algoritmo y en la medicion de su trabajo.

## Criterios de aceptacion

- El metodo ordena correctamente un arreglo de enteros en forma ascendente.
- Se cuentan comparaciones e intercambios de manera consistente.
- Se valida una referencia `null`.
- El algoritmo funciona para arreglos vacios y de un elemento.
- La solucion explica que Bubble Sort es adecuado para aprendizaje y conjuntos pequenos, no para grandes volumenes de datos.
- El desglose de complejidad indica `O(n^2)` en tiempo y `O(1)` en espacio auxiliar.
