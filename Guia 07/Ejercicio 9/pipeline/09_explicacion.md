# Explicación de `ComparacionAlgoritmosOrdenamiento`

El programa ejecuta Bubble Sort, Selection Sort e Insertion Sort sobre el mismo arreglo inicial, `{64, 34, 25, 12, 22, 11, 90}`, y muestra el arreglo ordenado junto con contadores de comparaciones y movimientos.

## Cómo se hace justa la prueba

En `main`, `arregloOriginal` conserva los datos de entrada. Antes de ordenar, el programa crea tres arreglos distintos con `clone()`:

```java
int[] copiaBubble = arregloOriginal.clone();
int[] copiaSelection = arregloOriginal.clone();
int[] copiaInsertion = arregloOriginal.clone();
```

Cada método modifica el arreglo que recibe, así que darle a los tres la misma referencia sería injusto: el segundo o tercer método podría recibir datos ya ordenados por el anterior. Las copias aseguran que cada algoritmo empieza con exactamente la misma secuencia y que el original permanece disponible para mostrar.

Cada método devuelve un `ResultadoOrdenamiento`, que agrupa el arreglo y los contadores. La impresión común muestra los datos, pero usa la etiqueta adecuada: Bubble y Selection informan **intercambios**, mientras que Insertion informa **desplazamientos**.

## Estrategia y contadores

### Bubble Sort

Bubble compara vecinos. Si el elemento izquierdo es mayor, los intercambia; así los valores grandes avanzan hacia la derecha. El límite interno se reduce una posición después de cada pasada porque el mayor de la zona activa ya quedó en su lugar.

Para siete valores, el algoritmo realiza seis pasadas con `6 + 5 + 4 + 3 + 2 + 1 = 21` comparaciones. En el arreglo de prueba, 14 pares estaban invertidos al momento de compararlos, por lo que se realizan 14 intercambios. La versión implementada no tiene una salida anticipada cuando una pasada no intercambia nada.

### Selection Sort

Selection busca el índice del menor elemento de la zona no ordenada y, una vez terminada la búsqueda, lo intercambia con el primero de esa zona. También realiza `6 + 5 + 4 + 3 + 2 + 1 = 21` comparaciones.

Hace solo 4 intercambios reales: en las primeras cuatro pasadas el menor estaba en otra posición; en las dos últimas ya estaba donde correspondía. No cuenta intercambiar un elemento consigo mismo.

### Insertion Sort

Insertion guarda una `clave` y recorre hacia atrás la parte ya ordenada. Mientras los valores anteriores sean mayores que la clave, los desplaza una posición a la derecha; al encontrar un valor menor o igual, o alcanzar el inicio, coloca la clave en el hueco.

Para esta entrada realiza 16 comparaciones entre valores y 14 desplazamientos. Las comparaciones por clave son `1 + 2 + 3 + 4 + 5 + 1 = 16`: la última comparación válida de cada búsqueda también cuenta, aunque resulte falsa y detenga el `while`. Los desplazamientos por clave son `1 + 2 + 3 + 3 + 5 + 0 = 14`. Por ejemplo, al insertar `22`, se desplazan `64`, `34` y `25`, y el `12` detiene la búsqueda.

## Resultados obtenidos

| Algoritmo | Comparaciones entre valores | Movimientos contados | Arreglo final |
|---|---:|---:|---|
| Bubble Sort | 21 | 14 intercambios | `[11, 12, 22, 25, 34, 64, 90]` |
| Selection Sort | 21 | 4 intercambios | `[11, 12, 22, 25, 34, 64, 90]` |
| Insertion Sort | 16 | 14 desplazamientos | `[11, 12, 22, 25, 34, 64, 90]` |

Bubble y Selection empatan en la mayor cantidad de comparaciones: 21. Insertion hace menos, 16, porque termina cada búsqueda hacia atrás cuando encuentra un valor que ya debe quedar antes de la clave. Selection realiza menos movimientos: 4 intercambios, frente a 14 intercambios de Bubble y 14 desplazamientos de Insertion.

## Intercambio y desplazamiento no son lo mismo

- **Intercambio:** dos elementos cambian de posición entre sí usando una variable temporal. Bubble suele hacer muchos intercambios vecinos; Selection puede colocar el mínimo con un único intercambio al final de cada pasada.
- **Desplazamiento:** un elemento se copia una posición a la derecha para abrir espacio. Insertion conserva la clave aparte mientras desplaza varios valores y luego la escribe en el hueco. Esa escritura final no se cuenta como desplazamiento en esta implementación.

Aunque Bubble e Insertion muestran ambos el número 14, no significa que hayan ejecutado el mismo tipo de movimiento ni el mismo número de asignaciones de memoria. Por eso conviene analizar las columnas separadas. La suma didáctica de contadores es Bubble `35`, Selection `25` e Insertion `30`, pero no equivale a una medición de tiempo real porque un intercambio, un desplazamiento y una comparación pueden tener costos distintos.

## Qué dicen los resultados sobre eficiencia

Los tres resultados son correctos y los tres algoritmos tienen complejidad cuadrática en el caso promedio/peor: `O(n^2)`. Los contadores explican cómo trabajaron sobre **esta entrada específica**, pero no prueban que uno sea siempre más rápido. El tiempo real también depende del tamaño del arreglo, el hardware, el costo de mover datos y otras características de la entrada.

- **Insertion Sort:** suele convenir para arreglos pequeños o casi ordenados. Se adapta a la entrada: si está ordenada, el `while` se detiene pronto y no hay desplazamientos. También es útil cuando los datos llegan uno a uno y se quiere insertar cada nuevo elemento en una secuencia que ya está ordenada.
- **Selection Sort:** puede convenir cuando las escrituras son costosas y se quiere minimizar la cantidad de intercambios. Mantiene `O(n^2)` comparaciones incluso si el arreglo está ordenado, por lo que no suele ser una buena elección para colecciones grandes.
- **Bubble Sort:** es claro para enseñar comparaciones adyacentes e intercambios, pero normalmente no se elige para producción con volúmenes grandes. Una variante con detección de una pasada sin intercambios puede terminar antes en datos ordenados o casi ordenados, pero esa optimización no está presente en el código revisado.

## Errores comunes con los contadores

- Contar las condiciones de los `for` como comparaciones de datos. El programa cuenta comparaciones entre valores, no comprobaciones de índices contra límites.
- Contar un intercambio en cada vuelta, aunque el `if` no cambie elementos.
- Contar en Selection cada vez que se actualiza el índice del menor como intercambio; el intercambio solo sucede al final de la búsqueda y si el índice cambió.
- En Insertion, contar comparaciones exitosas pero olvidar la comparación final que detiene el desplazamiento.
- Contar la inserción final de la clave como desplazamiento, aunque el contador definido registra solo los elementos movidos hacia la derecha.
- Comparar los movimientos de los tres métodos como si fueran idénticos: Bubble/Selection cuentan intercambios, Insertion cuenta desplazamientos.

La idea central de la prueba es que los tres métodos resuelven el mismo problema y producen el mismo arreglo, pero su forma de llegar al resultado genera cantidades y tipos de operaciones diferentes. Las copias independientes hacen válida la comparación y los contadores ayudan a visualizar esas estrategias.