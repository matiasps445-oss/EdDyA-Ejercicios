# Analisis tecnico: encontrar el mayor elemento de una matriz

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara una implementacion Java que encuentre el mayor valor de una matriz y justifique por que debe considerar todos sus elementos, junto con el analisis de complejidad temporal y espacial.

## Supuestos adoptados

- La matriz se representara como un arreglo bidimensional Java `int[][]` rectangular.
- El metodo reutilizable devolvera el mayor valor como `int`; no se solicita su posicion ni todas las posiciones donde aparece.
- Una referencia de matriz `null`, una fila `null`, filas con distintas longitudes o una matriz sin celdas se rechazaran con `IllegalArgumentException`, porque en esos casos no se puede determinar un maximo.
- La matriz debe tener al menos una fila y una columna.
- Se permiten valores negativos, repetidos y los extremos del tipo `int`.
- `main` usara una matriz fija con valores positivos y negativos y mostrara el maximo. No solicitara datos por consola.
- El algoritmo no modificara ni ordenara la matriz.

## Estrategia tecnica

Implementar un metodo reutilizable, por ejemplo `encontrarMayor(int[][] matriz)`:

1. Rechazar una referencia de matriz `null`.
2. Validar que exista al menos una fila, que la primera fila no sea `null` y que tenga al menos una columna.
3. Comprobar que todas las filas sean no nulas y tengan la misma longitud.
4. Inicializar `mayor` con el primer elemento real, `matriz[0][0]`.
5. Recorrer las celdas de la matriz con dos bucles anidados. Si una celda es mayor que `mayor`, actualizar el valor conservado.
6. Devolver `mayor` al finalizar el recorrido.

La inicializacion desde un elemento real evita escoger un centinela como `0`, que produciria un resultado incorrecto si todos los valores fueran negativos. Tambien funciona con `Integer.MIN_VALUE` y `Integer.MAX_VALUE`, porque el algoritmo compara valores sin sumar ni restar.

Es necesario considerar todas las celdas: sin una garantia de orden o informacion adicional, cualquier posicion no inspeccionada podria contener un valor mayor que el maximo actual. Se puede comparar la primera celda consigo misma en el recorrido por simplicidad; esa comparacion redundante no cambia el resultado ni la complejidad.

## Complejidad esperada

Sean `r` el numero de filas y `c` la cantidad de columnas de la matriz rectangular:

- Validar que todas las filas existan y tengan la misma longitud cuesta `O(r)`.
- Recorrer las celdas para hallar el maximo cuesta `O(r * c)`.
- Tiempo total, incluida la validacion: `O(r + r * c)`. Como se requiere `c >= 1`, se simplifica a `O(r * c)`.
- Espacio auxiliar: `O(1)`, ya que solo se conserva el mayor actual y variables de recorrido; no se crea otra matriz.

No existe caso de terminacion anticipada basado en una coincidencia o valor particular: aun al hallar un numero grande, otra celda podria ser mayor. Para dimensiones validas, deben examinarse todas las celdas.

## Flujo esperado de la demostracion

1. Definir una matriz rectangular con valores negativos y positivos, por ejemplo `{{-8, -2, -15}, {-4, -1, -6}}`.
2. Ejecutar el metodo y mostrar que el maximo es `-1`, ilustrando que puede ser negativo.
3. Opcionalmente agregar un segundo ejemplo con `Integer.MIN_VALUE` o con valores repetidos para mostrar que la inicializacion con un dato real y las comparaciones lo manejan correctamente.
4. No presentar la ejecucion de `main` como benchmark; es una demostracion funcional.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique el recorrido de una matriz mediante bucles anidados y por que todas las celdas deben ser consideradas.
2. Presente codigo Java comentado con un metodo reutilizable y un `main` con una matriz fija.
3. Valide referencias nulas, filas nulas, forma rectangular y ausencia de celdas.
4. Inicialice el maximo desde el primer elemento real, para manejar matrices totalmente negativas y `Integer.MIN_VALUE`.
5. Indique complejidad temporal `O(r*c)` y espacio auxiliar `O(1)`, incorporando o explicando el costo de validacion `O(r)`.
6. Aclare que el metodo devuelve el valor mayor, no su indice, y no modifica ni ordena la matriz.

No se solicita entrada por consola, devolver coordenadas, contar cuantas veces aparece el maximo, ordenar los datos ni crear una suite de pruebas automatizadas.

## Criterios de aceptacion

- El metodo devuelve el mayor valor de una matriz rectangular no vacia.
- La referencia principal, cada fila y la forma rectangular se validan antes de buscar; una matriz sin celdas no produce un maximo y se rechaza.
- La inicializacion usa un elemento de la matriz, no `0` ni otro valor que falle ante datos negativos.
- Todos los valores se consideran y la entrada no se modifica.
- `main` muestra un caso con valores negativos cuyo maximo sea tambien negativo.
- Se explica el tiempo `O(r*c)` y espacio auxiliar `O(1)`, indicando el costo de validacion de filas.
