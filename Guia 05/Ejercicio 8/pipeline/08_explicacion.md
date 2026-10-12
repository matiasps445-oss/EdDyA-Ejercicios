# Explicacion de `SumaMatriz`

El programa suma todas las celdas de una matriz rectangular de enteros y devuelve dos datos: la suma total y cuantas acumulaciones se realizaron. Para la demostracion, procesa `{{1, -2, 3}, {4, 5, -6}}` y una matriz sin filas.

El metodo reutilizable es `sumarMatriz(int[][] matriz)`. Su tipo de retorno, `ResultadoSuma`, agrupa el total y el conteo, en vez de imprimirlos dentro del algoritmo.

## Funcionamiento paso a paso

1. `sumarMatriz` comprueba si la referencia de matriz es `null`. Si lo es, lanza `IllegalArgumentException`.
2. Si hay al menos una fila, llama a `validarForma` antes de sumar. Asi una entrada irregular no puede producir un resultado parcial.
3. `validarForma` comprueba primero que la fila inicial no sea `null` y toma su longitud como cantidad esperada de columnas.
4. Luego examina las demas filas. Si alguna es `null` o tiene otra longitud, lanza `IllegalArgumentException`. Si todas tienen la misma longitud, la matriz es rectangular.
5. El metodo inicializa `suma` y `operaciones` en cero.
6. El primer `for-each` visita cada fila; el segundo visita cada valor de esa fila. Por cada celda hace `suma += valor` e incrementa `operaciones` en uno.
7. Una vez recorridas todas las celdas, crea un `ResultadoSuma` con ambos valores y lo devuelve.
8. `main` llama a `mostrarResultado`, que usa los getters para imprimir la suma y las acumulaciones.

Para la matriz `{{1, -2, 3}, {4, 5, -6}}`, el recorrido suma `1 - 2 + 3 + 4 + 5 - 6 = 5`. Hay seis celdas, por lo que se contabilizan seis acumulaciones. En la matriz vacia `new int[0][]`, no se ejecutan los bucles y el resultado es suma `0` y operaciones `0`.

## Que significa el conteo

En este programa, una "operacion" significa una acumulacion de una celda en la suma. Por cada celda ejecutada, `operaciones++` suma uno al contador. Para `r` filas y `c` columnas, el valor final es `r * c`.

No se cuentan como operaciones las comparaciones de los bucles, los incrementos que controlan sus indices ni las comprobaciones para validar la forma. Es una metrica definida para el ejercicio y no un conteo de todas las instrucciones ejecutadas por Java o por el procesador.

## Por que se implemento asi

Para conocer el total hay que inspeccionar cada valor; un numero positivo, negativo o cero puede cambiar la suma. Los bucles anidados reflejan directamente las dos dimensiones: el bucle externo recorre filas y el interno columnas.

La matriz se valida completa antes de iniciar el calculo. Java permite matrices irregulares porque `int[][]` es, en realidad, un arreglo de referencias a arreglos. Comprobar longitudes y filas nulas establece la precondicion rectangular que espera el metodo. La suma posterior puede entonces recorrer cada fila con seguridad.

`long` se usa tanto para la suma como para el contador. Esto permite sumar valores de `int` con un margen mayor que un acumulador `int`; no proporciona una garantia absoluta contra desbordamiento para cualquier carga imaginable, pero es suficiente para los casos habituales del ejercicio.

No se modifica ni copia la entrada: las variables `fila` y `valor` solo permiten leerla. `ResultadoSuma` agrupa las dos salidas relacionadas y evita devolver un arreglo de longitud dos con posiciones que tendrian que documentarse aparte.

## Complejidad

Sean `r` la cantidad de filas y `c` la cantidad de columnas de una matriz rectangular:

- **Validacion: `O(r)`** para revisar que cada fila no sea nula y mida lo mismo que la primera.
- **Suma: `O(r * c)`**; cada celda se visita una vez. El contador final es exactamente `r * c`.
- **Tiempo total: `O(r + r * c)`** incluyendo la validacion. Si hay al menos una columna, se puede simplificar a `O(r * c)`. Si hay filas pero cero columnas, no hay celdas que sumar, aunque se revisan las `r` filas; el tiempo es `O(r)`. Con cero filas, el trabajo es constante.
- **Espacio auxiliar: `O(1)`**. Se mantienen la suma, el contador y variables de recorrido; no se crea una matriz auxiliar. El objeto `ResultadoSuma` ocupa espacio constante.

## Conceptos de Java utilizados

- **Arreglo bidimensional `int[][]`**: es un arreglo de filas, donde cada fila es otro `int[]`. No garantiza por si solo que todas las filas tengan igual longitud; por eso el programa valida la forma.
- **Clase anidada estatica (`static class`)**: `ResultadoSuma` esta declarada dentro de `SumaMatriz`, pero no necesita una instancia externa para existir.
- **Campos `private final`**: `suma` y `operaciones` no pueden modificarse despues de inicializarse. Los getters ofrecen acceso de solo lectura.
- **Constructor privado**: restringe la creacion directa de resultados a la clase exterior, que construye el resultado al finalizar el metodo.
- **Metodo estatico**: `sumarMatriz` puede invocarse sin crear un objeto `SumaMatriz`.
- **Tipos `long`**: almacenan enteros de 64 bits y tienen un rango mayor que `int`.
- **Bucles `for-each` anidados**: `for (int[] fila : matriz)` recorre filas y `for (int valor : fila)` recorre sus valores sin gestionar indices manualmente.
- **Excepciones**: `IllegalArgumentException` comunica argumentos que no cumplen el contrato: matriz nula, fila nula o forma irregular.
- **`main` y metodos auxiliares**: `main` prepara ejemplos; `mostrarResultado` centraliza la impresion para no repetir formato.
- **Javadoc**: los comentarios `/** ... */` documentan el proposito, los parametros, el resultado, las excepciones y la complejidad.

## Mejoras o alternativas posibles

- **Usar un `record`**: con Java 16 o posterior, `ResultadoSuma` podria ser un `record ResultadoSuma(long suma, long operaciones)`, reduciendo constructores y getters. La clase actual funciona con versiones anteriores y restringe la creacion mediante constructor privado.
- **Usar `BigInteger`**: si los datos pudieran superar el rango de `long`, `java.math.BigInteger` evitaria el desbordamiento. Sus operaciones consumen mas recursos y no son necesarias para una matriz pequena de ejercicio.
- **Mostrar la matriz**: se podria usar `Arrays.deepToString(matriz)` para presentar el contenido en `main`. No es necesario para calcular ni validar la suma.
- **Separar validacion y calculo**: en una aplicacion mayor, la validacion y la salida podrian pertenecer a otras clases. En este ejercicio se mantienen cerca del algoritmo para hacer el flujo facil de seguir.
- **Pruebas automatizadas**: convendria probar valores negativos, ceros, dimensiones de una fila/columna, matriz de cero columnas, filas nulas, filas irregulares y matriz nula. La demostracion de `main` no sustituye esas pruebas.

## Errores comunes

- **Acceder a `matriz[0]` sin revisar la cantidad de filas**: una matriz de cero filas no tiene primera fila. El codigo solo valida la forma cuando `matriz.length > 0`.
- **Consultar `.length` en una fila nula**: causaria `NullPointerException`. `validarForma` comprueba `matriz[indice] == null` antes de leer la longitud; el `||` corta la evaluacion si la fila es nula.
- **Empezar a sumar antes de validar todas las filas**: una fila irregular podria provocar una excepcion despues de haber calculado una suma parcial. La validacion se completa primero.
- **Contar controles del bucle como acumulaciones**: el numero mostrado define solo las ejecuciones de `suma += valor`; no representa cada comparacion o instruccion del programa.
- **Contar una celda dos veces o no contar negativos**: ambos bucles deben visitar cada par fila-columna una vez, y la suma debe incluir el valor tal como aparece, sea positivo o negativo.
- **Usar `int` para la suma sin considerar overflow**: un acumulador `int` puede desbordarse antes que los elementos individuales. `long` amplia el rango disponible.
- **Suponer que `int[][]` siempre es rectangular**: Java permite filas de diferente longitud. Si el contrato exige una matriz rectangular, esa propiedad debe comprobarse.
- **Cambiar accidentalmente la entrada**: la suma es de solo lectura. Asignar valores en `matriz[fila][columna]` alteraria el argumento y no es necesario.
- **Confundir una matriz vacia con una matriz nula**: `new int[0][]` es un arreglo valido sin filas y produce resultado cero; `null` es una referencia ausente y se rechaza.
