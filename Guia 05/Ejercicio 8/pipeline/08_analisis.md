# Analisis tecnico: sumar los elementos de una matriz

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara codigo Java que calcule la suma de todos los valores de una matriz, explique el recorrido y la complejidad, y contabilice las operaciones realizadas bajo una definicion explicita.

## Supuestos adoptados

- La matriz sera un arreglo bidimensional Java `int[][]` rectangular.
- El metodo reutilizable devolvera un resultado que contenga tanto la suma como el conteo de operaciones; se recomienda un tipo `ResultadoSuma` con campos `long suma` y `long operaciones`.
- La suma se acumulara como `long` para reducir el riesgo de desbordamiento que tendria un acumulador `int` al sumar muchos valores.
- Una referencia `null`, una fila `null` o filas de longitudes distintas se rechazaran con `IllegalArgumentException`.
- Una matriz con cero filas o con filas de cero columnas es valida: su suma y su conteo de operaciones son ambos cero.
- Para hacer verificable la metrica, una operacion contabilizada sera una acumulacion de una celda (`suma += valor`). El contador aumenta exactamente una vez por celda procesada. No se cuentan comparaciones de control de los bucles ni validaciones estructurales.
- `main` usara una matriz rectangular con valores positivos y negativos, y mostrara la suma junto con el conteo. Tambien puede incluir una matriz vacia para demostrar el resultado base.
- La solucion no modificara la matriz y no pedira datos por consola.

## Estrategia tecnica

Implementar un metodo reutilizable, por ejemplo `sumarMatriz(int[][] matriz)`, con este flujo:

1. Rechazar la referencia de matriz `null`.
2. Si hay filas, obtener la cantidad de columnas de la primera y validar que todas las filas sean no nulas y tengan la misma longitud.
3. Inicializar `suma` y `operaciones` en cero.
4. Recorrer cada fila y cada columna con dos bucles anidados.
5. En cada celda, acumular su valor en `suma` e incrementar `operaciones` en uno.
6. Devolver ambos valores en un objeto resultado.

La validacion de forma debe ocurrir antes de recorrer los datos para sumar, de modo que una matriz irregular no produzca una suma parcial ni un error inesperado de acceso. La matriz de cero filas se trata antes de intentar acceder a `matriz[0]`.

La estructura de recorrido es necesaria porque no hay informacion sobre los valores que permita omitir celdas: para conocer la suma total se debe visitar cada una. No se crea una copia ni se cambia el orden de los datos.

## Definicion del conteo

Sea `N` el numero total de celdas de la matriz valida. Bajo la definicion adoptada, el metodo realiza exactamente `N` operaciones contabilizadas: una suma acumulada por celda. Por ello:

- Una matriz de `r` filas y `c` columnas contabiliza `r * c` operaciones.
- Una matriz vacia, o con cero columnas, contabiliza cero.
- Las comprobaciones de limites de los bucles y la validacion de que la matriz sea rectangular no forman parte de este contador; se aclarara esta convencion en la explicacion.

## Complejidad esperada

Sean `r` el numero de filas y `c` el numero de columnas de una matriz rectangular:

- Recorrer y validar que las filas existan y tengan la longitud esperada cuesta `O(r)`.
- Sumar todas las celdas cuesta `O(r * c)` y ejecuta exactamente `r * c` acumulaciones.
- El tiempo total, incluida la validacion de forma, es `O(r + r * c)`. Para matrices con al menos una columna, esto se simplifica a `O(r * c)`; con cero columnas queda `O(r)` por la validacion de filas.
- Espacio auxiliar: `O(1)`, sin contar el objeto de resultado de tamano constante. No se crea otra matriz.

La suma se acumula en `long`, pero cualquier tipo entero de capacidad finita podria desbordarse en un caso extremo; para los datos habituales del ejercicio, `long` ofrece un margen mucho mayor que `int`. Si el dominio exigiera garantia matematica para cualquier cantidad de datos, podria considerarse `BigInteger`, con mayor costo y complejidad de diseno.

## Flujo esperado de la demostracion

1. Definir una matriz rectangular pequena, por ejemplo `{{1, -2, 3}, {4, 5, -6}}`.
2. Mostrar o describir sus filas para que se vea que se procesan todos los elementos.
3. Ejecutar el metodo y presentar la suma `5` y las seis operaciones contabilizadas.
4. Opcionalmente ejecutar la matriz vacia para mostrar suma `0` y operaciones `0`.
5. Aclarar que el numero impreso es el conteo definido de acumulaciones, no un conteo de cada instruccion o comparacion del procesador.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique el recorrido de una matriz con bucles anidados y el motivo por el que se visitan todas las celdas.
2. Presente codigo Java comentado con un metodo reutilizable y un tipo resultado que devuelva suma y operaciones.
3. Valide matriz nula, filas nulas y forma rectangular; trate matrices vacias o de cero columnas con resultado cero.
4. Use `long` para suma y contador, no modifique la entrada y use datos fijos en `main`.
5. Defina el conteo como una acumulacion por celda, con exactamente `r * c` operaciones contabilizadas para una matriz rectangular de dimensiones positivas.
6. Analice tiempo `O(r*c)` para el procesamiento de celdas, espacio auxiliar `O(1)` y el costo de validacion estructural.

No se solicita lectura por consola, modificar la matriz, calcular promedios o productos, ni agregar una suite de pruebas automatizadas.

## Criterios de aceptacion

- La suma incluye cada celda exactamente una vez, sin omitir valores negativos ni ceros.
- El contador coincide con la cantidad de acumulaciones: una por celda procesada.
- El metodo devuelve suma y conteo, y `main` presenta un ejemplo cuyo resultado sea comprobable manualmente.
- Las referencias nulas y matrices no rectangulares se rechazan antes de comenzar la suma.
- Una matriz vacia o con cero columnas devuelve suma cero y cero operaciones.
- La implementacion no modifica ni copia la matriz y acumula el resultado en `long`.
- La explicacion distingue las operaciones contabilizadas de las comparaciones de bucle y valida el analisis de complejidad temporal y espacial.
