# Explicacion de `DuplicadosVector`

La clase ofrece dos metodos para responder la misma pregunta: ¿el arreglo contiene al menos un valor repetido? `contieneDuplicadosConCiclos` compara parejas de elementos; `contieneDuplicadosConHashSet` recuerda los valores vistos en un conjunto. Ambos devuelven `true` al encontrar una repeticion y `false` si terminan sin encontrar ninguna. No devuelven cuales valores estan duplicados ni cuantas veces aparecen.

## Flujo del programa

`main` crea dos arreglos fijos:

- `conDuplicados = {8, -3, 5, 8, 0}` contiene el valor `8` dos veces.
- `sinDuplicados = {8, -3, 5, 0}` no contiene valores repetidos.

Para cada arreglo, `mostrarResultados` llama a ambos metodos y muestra sus resultados. En el primero, ambos deben imprimir `true`; en el segundo, ambos deben imprimir `false`. La demostracion no lee datos por consola ni mide el rendimiento: ilustra el resultado logico de las dos tecnicas.

## Metodo con ciclos anidados

`contieneDuplicadosConCiclos` primero llama a `validarArreglo`. Luego usa dos bucles `for`:

1. `indice` selecciona un elemento.
2. `siguiente` comienza en `indice + 1`, por lo que solo compara ese elemento con posiciones posteriores.
3. Si `numeros[indice] == numeros[siguiente]`, se ha encontrado una pareja igual y el metodo devuelve `true` inmediatamente.
4. Si se revisan todas las parejas sin igualdad, devuelve `false`.

Con `{8, -3, 5, 8, 0}`, el primer valor se compara sucesivamente con `-3`, `5` y el `8` de la posicion 3. Al encontrar la igualdad, retorna `true` y ya no revisa las comparaciones restantes.

Empezar el segundo bucle en `indice + 1` evita dos trabajos innecesarios: comparar un valor consigo mismo y volver a comparar una pareja en el sentido contrario. Por ejemplo, se compara la pareja `(0, 3)` pero no se vuelve a comparar `(3, 0)`.

Si todos los elementos son distintos, se comparan exactamente `n(n - 1) / 2` parejas. Por eso el tiempo en el peor caso es `O(n^2)`. Si la primera pareja ya coincide, el retorno temprano produce el mejor caso `O(1)`. El metodo usa solo indices y no crea estructuras auxiliares, asi que su espacio auxiliar es `O(1)`.

## Metodo con `HashSet`

`contieneDuplicadosConHashSet` tambien valida primero el arreglo. Despues crea un conjunto vacio `valoresVistos` y recorre el arreglo una sola vez con un bucle `for-each`:

1. Intenta agregar el numero actual con `valoresVistos.add(numero)`.
2. Si `add` devuelve `true`, el valor no estaba y queda registrado para comparaciones futuras.
3. Si devuelve `false`, el valor ya se encontraba en el conjunto; por lo tanto, existe un duplicado y el metodo retorna `true`.
4. Si el recorrido termina sin que `add` devuelva `false`, devuelve `false`.

Para `{8, -3, 5, 8, 0}`, el conjunto recibe primero `8`, luego `-3` y `5`. Al intentar agregar el segundo `8`, `add` devuelve `false` y la busqueda termina. En el arreglo sin duplicados, cada insercion es nueva y el metodo devuelve `false` despues de recorrerlo completo.

No se llama primero a `contains` y luego a `add`: el resultado de `add` ya indica si el valor era nuevo. El conjunto no conserva el orden del arreglo y no hace falta que lo haga; aqui solo importa saber si el valor ya aparecio.

En promedio, las operaciones de `HashSet` son de costo constante, por lo que recorrer `n` elementos toma `O(n)` esperado. El conjunto puede guardar hasta `n` valores, asi que usa `O(n)` espacio auxiliar. En un escenario teorico con muchas colisiones, el costo puede degradarse; las implementaciones modernas de Java pueden mitigar algunos casos con buckets arbolados, pero el analisis habitual expresa `O(n)` como costo esperado, no como garantia para cualquier patron de colision.

## Validacion y contrato

`validarArreglo` lanza `IllegalArgumentException` cuando recibe `null`. Se comprueba en los dos metodos publicos antes de acceder al arreglo o recorrerlo. Esto evita una `NullPointerException` accidental y comunica claramente que el argumento no cumple el contrato.

Un arreglo vacio y uno de un elemento son validos: ninguno puede contener dos posiciones iguales. El doble bucle no encuentra pares y devuelve `false`; el metodo con `HashSet` no detecta inserciones repetidas y tambien devuelve `false`.

Ninguno de los metodos cambia los elementos del arreglo. La primera estrategia solo lee valores y la segunda modifica su conjunto local, no la entrada.

## Conceptos de Java utilizados

- **Arreglo primitivo `int[]`**: contiene enteros, se indexa desde cero y expone el numero de elementos mediante `length`.
- **Metodos `static`**: los metodos pertenecen a `DuplicadosVector` y pueden llamarse sin crear una instancia de la clase.
- **Tipo de retorno `boolean`**: representa la respuesta binaria sobre existencia: `true` o `false`.
- **Bucles anidados**: un `for` dentro de otro permite recorrer parejas de posiciones. El segundo bucle empieza despues del primero para evitar parejas repetidas.
- **Bucle `for-each`**: `for (int numero : numeros)` asigna cada elemento del arreglo a `numero`, sin que el codigo tenga que mantener un indice.
- **Interfaz `Set` y clase `HashSet`**: `Set<Integer>` declara la abstraccion de conjunto, mientras `new HashSet<>()` proporciona una implementacion basada en hashing que no acepta elementos duplicados.
- **Autoboxing**: el arreglo almacena el primitivo `int`, pero `Set<Integer>` trabaja con objetos `Integer`. Java convierte automaticamente `numero` a `Integer` al llamar `add`. El conjunto compara los valores usando las reglas de igualdad de `Integer`.
- **Genericos y operador diamante**: `Set<Integer>` limita los elementos del conjunto a enteros. `new HashSet<>()` infiere `Integer` a partir del tipo de la variable, evitando repetirlo.
- **Retorno temprano**: `return true` detiene el metodo al hallar una coincidencia, en lugar de completar trabajo innecesario.
- **Excepcion definida por la aplicacion**: `throw new IllegalArgumentException(...)` representa una entrada no permitida (`null`).
- **Metodos auxiliares privados**: `validarArreglo` y `mostrarResultados` encapsulan tareas internas que no forman parte de la API publica de la clase.
- **Concatenacion e impresion**: `+` construye las lineas descriptivas y `System.out.println` las escribe en la consola.
- **Javadoc**: los bloques `/** ... */` describen el proposito, parametros, resultado, excepcion y complejidad de los metodos.

## Comparacion de las estrategias

| Estrategia | Tiempo esperado | Peor caso | Espacio auxiliar | Ventaja principal |
| --- | --- | --- | --- | --- |
| Ciclos anidados | `O(n^2)` | `O(n^2)` | `O(1)` | No necesita estructuras auxiliares. |
| `HashSet` | `O(n)` esperado | Puede degradarse teoricamente con colisiones | `O(n)` | Recorre el arreglo una vez y detecta repetidos al insertar. |

Los dos enfoques intercambian tiempo por memoria: los ciclos anidados usan muy poca memoria, pero pueden comparar todas las parejas; `HashSet` suele ser mas rapido para arreglos grandes, a costa de guardar valores vistos. Esta tabla describe complejidad, no un resultado de benchmark. Para arreglos pequenos, el costo constante y las asignaciones tambien influyen en el tiempo real.

## Alternativas de diseno

- **Ordenar y comparar vecinos**: ordenar una copia del arreglo y revisar si hay valores iguales consecutivos puede tomar `O(n log n)` tiempo. Debe ordenarse una copia para preservar la entrada, lo que requiere espacio adicional, y hace mas trabajo que `HashSet` en promedio.
- **Usar `HashSet<Integer>` como valor de retorno**: permitiria informar los elementos repetidos, pero cambia el contrato de `boolean` y agrega trabajo para determinar todas las repeticiones; no se necesita para esta consigna.
- **Contar frecuencias con `Map<Integer, Integer>`**: es apropiado si se necesita saber cuantas veces aparece cada valor, pero almacena conteos que esta solucion no requiere.
- **Agregar pruebas automatizadas**: se pueden probar arreglos vacios, unitarios, negativos, duplicado al inicio o al final, y excepciones para `null`. Los ejemplos de `main` son una demostracion manual, no reemplazan pruebas unitarias.

## Errores comunes

- **Empezar el segundo ciclo en cero**: causa comparaciones de un elemento consigo mismo y revisa las mismas parejas dos veces. Debe empezar en `indice + 1`.
- **No detenerse al encontrar una igualdad**: continuar es trabajo innecesario y puede complicar el resultado. Como solo se pregunta si existe algun duplicado, se puede retornar inmediatamente.
- **Usar `==` para comparar objetos `Integer`**: `==` entre referencias compara identidad, no necesariamente el valor numerico. En el doble ciclo actual ambos operandos son `int`, asi que `==` compara correctamente los valores. `HashSet<Integer>` se encarga de la igualdad de los objetos internamente.
- **Consultar `contains` antes de `add`**: hace dos consultas al conjunto por cada valor nuevo. El booleano de `add` ya informa si el valor fue insertado.
- **Creer que `HashSet` tiene siempre tiempo garantizado `O(1)`**: es un costo promedio/esperado; colisiones pueden aumentar el trabajo.
- **Olvidar el caso `null`**: recorrer una referencia nula produciria `NullPointerException`. La validacion explicita lanza la excepcion definida por el contrato.
- **Confundir ausencia con error**: un arreglo vacio es valido y el resultado correcto es `false`; no se debe lanzar excepcion por su longitud cero.
- **Modificar o reordenar el vector innecesariamente**: ordenar puede cambiar el orden original; ninguna de las dos estrategias necesita hacerlo.
- **Interpretar `true` como una lista de duplicados**: el resultado solo confirma que existe al menos uno; no identifica el valor ni sus posiciones.
