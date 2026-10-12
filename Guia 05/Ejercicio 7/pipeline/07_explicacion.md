# Explicacion de `InvertirVector`

La clase demuestra dos maneras de invertir un arreglo `int[]`:

- `invertirConAuxiliar` devuelve un arreglo nuevo con los valores en orden inverso y conserva intacta la entrada.
- `invertirInSitu` cambia el arreglo recibido, intercambiando sus extremos hacia el centro.

Invertir no significa ordenar: se conserva el mismo conjunto de valores, pero en orden contrario.

## Funcionamiento paso a paso

### Version con arreglo auxiliar

1. `invertirConAuxiliar` llama a `validarArreglo`. Si la referencia es `null`, se lanza `IllegalArgumentException` antes de consultar su longitud.
2. Crea `invertido` con la misma longitud que `numeros`.
3. Recorre cada indice de destino desde cero.
4. Copia a `invertido[indice]` el valor de `numeros[numeros.length - 1 - indice]`. El primer lugar del resultado recibe el ultimo valor del original; el segundo recibe el penultimo, y asi sucesivamente.
5. Devuelve el nuevo arreglo. Como nunca escribe en `numeros`, la entrada original queda intacta.

Con `{1, 2, 3, 4, 5}`, las asignaciones son:

| Indice destino | Indice origen | Valor copiado |
| ---: | ---: | ---: |
| 0 | 4 | 5 |
| 1 | 3 | 4 |
| 2 | 2 | 3 |
| 3 | 1 | 2 |
| 4 | 0 | 1 |

El resultado es `{5, 4, 3, 2, 1}`.

### Version in situ

1. `invertirInSitu` aplica la misma validacion de `null`.
2. Inicializa `inicio` en `0` y `fin` en el indice final, `numeros.length - 1`.
3. Mientras `inicio < fin`, guarda el elemento del extremo izquierdo en `temporal`, copia el elemento derecho al lado izquierdo y luego coloca el valor guardado en el lado derecho. Esto completa un intercambio sin perder ninguno de los valores.
4. La expresion de incremento del `for` aumenta `inicio` y disminuye `fin`, acercando ambos indices al centro.
5. Cuando los indices se encuentran o se cruzan, el bucle termina y el arreglo ya esta invertido.

Con `{1, 2, 3, 4, 5}`, los intercambios son:

- Posiciones `0` y `4`: `{5, 2, 3, 4, 1}`.
- Posiciones `1` y `3`: `{5, 4, 3, 2, 1}`.
- El valor central `3` no necesita moverse.

Para una longitud par, como `{1, 2, 3, 4}`, se hacen dos intercambios y los indices se cruzan. Para un arreglo vacio o unitario, `inicio < fin` es falso desde el principio, asi que no se realizan intercambios.

### Demostracion de `main`

`main` crea `original` con `{1, 2, 3, 4, 5}` y obtiene `copiaParaInvertirInSitu` mediante `original.clone()`. La version auxiliar recibe `original`; la version in situ recibe la copia. Asi, la segunda estrategia no recibe un arreglo ya procesado por la primera y ambas parten del mismo contenido.

La salida muestra el arreglo original antes y despues del metodo auxiliar, el nuevo resultado auxiliar y el resultado de la inversion in situ. `Arrays.toString` convierte cada arreglo en una representacion legible para la consola.

## Por que se implementaron las dos versiones

Las dos estrategias producen el mismo orden inverso, pero tienen contratos diferentes. La version auxiliar es apropiada si quien llama necesita conservar los datos originales; paga por ello creando otro arreglo. La version in situ evita esa asignacion proporcional al tamano de entrada, pero modifica el arreglo recibido. Los nombres de los metodos indican claramente ese efecto.

La version in situ solo necesita procesar la mitad de las posiciones, porque cada intercambio coloca dos elementos en su lugar final. La version auxiliar escribe una vez por cada posicion del nuevo arreglo. En ambas, el numero de operaciones crece proporcionalmente a la longitud.

## Complejidad

Sea `n` la longitud del arreglo:

| Metodo | Tiempo | Espacio auxiliar propio | Efecto sobre la entrada |
| --- | --- | --- | --- |
| `invertirConAuxiliar` | `O(n)` | `O(n)` por el nuevo arreglo | No la modifica. |
| `invertirInSitu` | `O(n)` | `O(1)` | La modifica directamente. |

La version in situ realiza hasta `floor(n / 2)` intercambios; sigue siendo tiempo `O(n)`. La version auxiliar tambien escribe `n` elementos. Para longitud cero, el recorrido auxiliar no tiene iteraciones y el resultado es otro arreglo vacio; para longitud uno, crea un resultado de un elemento. Ambos metodos manejan esos casos sin condiciones especiales.

En `main`, `original.clone()` crea una copia de tamano `n`, que usa `O(n)` memoria en el programa de demostracion. Esa copia no es parte del espacio auxiliar de `invertirInSitu`: existe para poder comparar las dos estrategias sobre datos independientes.

## Conceptos de Java utilizados

- **Arreglo primitivo `int[]`**: almacena enteros en posiciones indexadas desde cero; `length` devuelve su longitud.
- **Metodos estaticos (`static`)**: ambos metodos de inversion pueden usarse sin instanciar `InvertirVector`.
- **Tipos de retorno diferentes**: el metodo auxiliar devuelve `int[]`; el metodo in situ devuelve `void` porque su resultado es el arreglo que ya modifico.
- **Validacion compartida**: `validarArreglo` es `private static`, por lo que solo se usa dentro de la clase y evita duplicar la misma comprobacion.
- **Excepcion `IllegalArgumentException`**: expresa que `null` incumple el contrato. Un arreglo vacio si es valido.
- **Bucle `for` con dos indices**: la version in situ declara `inicio` y `fin` en la inicializacion, usa `inicio < fin` como condicion y actualiza ambos indices en cada iteracion.
- **Variable temporal**: `temporal` conserva un valor durante el intercambio; sin ella se sobrescribiria uno de los extremos antes de copiarlo.
- **`clone()` en arreglos**: produce otro arreglo con los mismos valores. En este caso son enteros primitivos, asi que copiar el arreglo es suficiente para tener datos independientes.
- **Clase `Arrays`**: `Arrays.toString` genera una cadena con los elementos para mostrar los datos. La importacion `java.util.Arrays` habilita su uso.
- **Javadoc**: los comentarios `/** ... */` documentan el proposito, parametros, retorno, excepciones y complejidad de los metodos.

## Mejoras o alternativas posibles

- **Usar un ciclo `while` con los dos punteros**: podria hacer mas visible la condicion de avance. El `for` actual expresa en una sola estructura la inicializacion, condicion e incremento/decremento.
- **Invertir el arreglo auxiliar recorriendo el origen al reves**: otra forma equivalente es empezar en el ultimo indice del original y copiar secuencialmente al resultado. La version actual recorre el destino desde el principio y calcula la posicion inversa del origen.
- **Proveer una version que devuelva una copia para ambos casos**: seria otra API, pero no reemplaza la version in situ requerida porque siempre reservaria memoria adicional.
- **Usar una funcion de biblioteca**: para listas, `Collections.reverse` puede invertir en sitio, pero no se aplica directamente a `int[]`; ademas, la version manual muestra el mecanismo solicitado.
- **Pruebas automatizadas**: se pueden probar longitudes pares e impares, arreglo vacio, un elemento, `null`, preservacion de la entrada auxiliar y mutacion in situ. La demostracion de `main` no sustituye una suite de pruebas.

## Errores comunes

- **Confundir invertir con ordenar**: el resultado debe tener los mismos valores en orden contrario, no ordenados de menor a mayor.
- **Usar una formula de indice incorrecta**: para un indice `indice`, el espejo correcto es `length - 1 - indice`. Olvidar el `-1` puede producir un indice fuera de rango.
- **Modificar el arreglo durante la inversion auxiliar**: escribir en el arreglo original puede destruir valores que todavia hacen falta para llenar el resultado. La version auxiliar debe escribir solo en el nuevo arreglo.
- **Intercambiar sin variable temporal**: asignar directamente el extremo izquierdo sobre el derecho pierde uno de los valores. `temporal` permite guardar y restaurar el valor.
- **Usar `inicio <= fin` para intercambiar**: si los indices coinciden en el centro de un arreglo impar, se intercambiaria el elemento consigo mismo. No cambia el resultado, pero es innecesario; `inicio < fin` expresa exactamente cuando hay dos posiciones distintas por intercambiar.
- **Avanzar solo uno de los indices**: el bucle podria no terminar o dejar posiciones sin procesar. En cada paso `inicio` debe aumentar y `fin` disminuir.
- **Ejecutar primero la version in situ sobre el original compartido**: el segundo metodo recibiria datos ya invertidos. La copia hecha con `clone()` mantiene ambas demostraciones independientes.
- **Olvidar validar `null` antes de consultar `length`**: eso causaria `NullPointerException` en vez de la excepcion definida por el metodo.
- **Atribuir la memoria del `clone()` al metodo in situ**: la copia pertenece a la preparacion de `main`; el metodo `invertirInSitu` no crea un arreglo adicional.
