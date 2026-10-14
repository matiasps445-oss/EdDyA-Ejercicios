# Explicación de `MergeSortPasoAPaso`

El programa ordena `{38, 27, 43, 3, 9, 82, 10, 5}` con MergeSort. Para entenderlo conviene separar dos trabajos distintos: `mergeSort` divide el problema y coordina las llamadas recursivas; `fusionar` combina mitades que ya están ordenadas. La primera fase desciende hacia subarreglos pequeños y la segunda reconstruye el resultado al regresar.

## Fase de división: `mergeSort`

El método primero evalúa `numeros.length <= 1`. Si la condición se cumple, imprime que alcanzó el caso base y devuelve ese arreglo sin modificarlo. Un arreglo vacío o de un solo elemento ya está ordenado, así que no necesita más divisiones.

Si el arreglo tiene más de un elemento, calcula `puntoMedio = numeros.length / 2` y crea dos copias con `Arrays.copyOfRange`:

- izquierda: desde el índice `0` hasta `puntoMedio`, sin incluirlo;
- derecha: desde `puntoMedio` hasta `numeros.length`, sin incluir el extremo final.

Después imprime el arreglo actual y las dos mitades, y llama a `mergeSort` primero para la izquierda y luego para la derecha. El método todavía no ordena comparando elementos; solo divide y espera los resultados ordenados de las llamadas hijas.

Para la entrada del programa, el árbol de divisiones es:

```text
[38, 27, 43, 3, 9, 82, 10, 5]
├── [38, 27, 43, 3]
│   ├── [38, 27]
│   │   ├── [38]  caso base
│   │   └── [27]  caso base
│   └── [43, 3]
│       ├── [43]  caso base
│       └── [3]   caso base
└── [9, 82, 10, 5]
    ├── [9, 82]
    │   ├── [9]   caso base
    │   └── [82]  caso base
    └── [10, 5]
        ├── [10]  caso base
        └── [5]   caso base
```

Las llamadas se resuelven en profundidad: termina de dividir y ordenar la rama izquierda antes de procesar la derecha de su padre. Cuando llega a `[38]`, por ejemplo, la llamada retorna enseguida. La llamada que esperaba ese resultado puede entonces fusionar `[38]` con `[27]`.

## Fase de fusión: `fusionar`

`fusionar` recibe dos arreglos ordenados y reserva `resultado` con espacio para todos sus elementos. Mantiene tres índices: `indiceIzquierda`, `indiceDerecha` e `indiceResultado`.

Mientras haya elementos sin consumir en ambos lados, compara los elementos señalados por los dos primeros índices. Copia el menor a `resultado` y avanza el índice del lado del que lo tomó, además de avanzar el índice de salida. Por ejemplo, al fusionar `[27, 38]` y `[3, 43]`:

1. Compara `27` y `3`; copia `3`.
2. Compara `27` y `43`; copia `27`.
3. Compara `38` y `43`; copia `38`.
4. La mitad izquierda se agotó; copia el `43` restante.
5. El resultado es `[3, 27, 38, 43]`.

Los dos bucles finales copian cualquier elemento sobrante. No hace falta compararlo con el otro lado porque ese lado ya se agotó y los elementos restantes allí están ordenados entre sí. Al terminar, el método imprime ambas entradas y el arreglo combinado.

El uso de `<=` cuando se comparan los valores hace que, si ambos lados tienen el mismo valor, se tome primero el de la izquierda. Esto conserva el orden relativo de valores iguales y mantiene estable la fusión.

## Reconstrucción completa

Las fusiones ocurren al retornar de las llamadas, de las partes más pequeñas hacia el arreglo raíz:

```text
Fusionando [38] + [27] -> [27, 38]
Fusionando [43] + [3] -> [3, 43]
Fusionando [27, 38] + [3, 43] -> [3, 27, 38, 43]
Fusionando [9] + [82] -> [9, 82]
Fusionando [10] + [5] -> [5, 10]
Fusionando [9, 82] + [5, 10] -> [5, 9, 10, 82]
Fusionando [3, 27, 38, 43] + [5, 9, 10, 82] -> [3, 5, 9, 10, 27, 38, 43, 82]
```

La primera fusión es la última pareja de elementos de la rama izquierda; la última fusión une las dos mitades grandes ya ordenadas. El método principal imprime finalmente `[3, 5, 9, 10, 27, 38, 43, 82]`.

## Recursividad y condición de corte

Cada llamada no base reduce el problema a mitades más pequeñas. La división continúa hasta que la longitud es `0` o `1`, que es la condición de corte exacta `numeros.length <= 1`. Una vez que todas las ramas alcanzan ese caso base, las llamadas pendientes se completan mediante fusiones.

Como el punto medio se calcula con división entera, una partición de longitud impar queda dividida en una mitad de `n / 2` elementos y otra de `n - n / 2`. Por ejemplo, `[a, b, c]` se divide en `[a]` y `[b, c]`; ambas mitades son menores que la original, así que la recursión progresa.

## Errores comunes

- **Calcular mal el punto medio o los rangos de copia:** las mitades deben cubrir todos los elementos exactamente una vez. Con `copyOfRange`, el índice final es exclusivo; usar `puntoMedio + 1` en el inicio de la derecha podría perder el elemento del medio.
- **No alcanzar un caso base:** si una división vuelve a crear un subarreglo del mismo tamaño que el actual, la recursión no progresa y puede terminar en `StackOverflowError`.
- **Escribir sobre los datos que aún se necesitan:** la implementación evita sobrescribir las mitades al fusionar porque recibe arreglos ordenados y crea un `resultado` separado. Una versión in-place necesitaría otra estrategia para no perder elementos pendientes.
- **Olvidar copiar los elementos restantes:** cuando un lado se agota, el otro todavía puede contener valores que deben agregarse al resultado.
- **Incrementar el índice equivocado o no incrementarlo:** cada elemento copiado debe avanzar su índice de origen y el índice de salida; si alguno queda inmóvil, el `while` puede no terminar o puede sobrescribir posiciones.
- **Leer un índice después del final:** las condiciones `indiceIzquierda < izquierda.length` y `indiceDerecha < derecha.length` deben comprobarse antes de acceder a esos arreglos.
- **Confundir las responsabilidades:** `mergeSort` divide y recursa; `fusionar` compara y combina. Mezclar ambas etapas dificulta seguir el árbol y diagnosticar errores.

En resumen, MergeSort baja por el árbol dividiendo el arreglo hasta piezas que ya están ordenadas por definición. Luego `fusionar` usa recorridos lineales de dos entradas ordenadas para reconstruir arreglos mayores, hasta producir la salida final.