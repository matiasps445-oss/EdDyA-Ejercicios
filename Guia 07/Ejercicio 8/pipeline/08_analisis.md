# Plan técnico: MergeSort paso a paso

## Objetivo

Definir una implementación Java de MergeSort que ordene un arreglo de enteros y haga visible todo el proceso: divisiones recursivas, casos base y fusiones de subarreglos ordenados.

## Diseño

- **Método recursivo:** `mergeSort(int[] numeros)`, que devuelve un arreglo ordenado.
- **Método de fusión:** `fusionar(int[] izquierda, int[] derecha)`, que recibe dos arreglos ya ordenados y devuelve uno combinado y ordenado.
- **Arreglo de demostración:** `{38, 27, 43, 3, 9, 82, 10, 5}`. El planteo y la imagen no especifican valores; se elige una entrada de ocho elementos para mostrar el árbol completo y varias fusiones.
- **Presentación:** imprimir el arreglo original y el resultado final con `Arrays.toString(...)`.
- **Trazabilidad:** imprimir una línea de división antes de realizar las llamadas recursivas, una línea al alcanzar cada caso base y una línea al completar cada fusión.
- **Estrategia de subarreglos:** crear las mitades con `Arrays.copyOfRange(...)`. Esto hace explícita la división y simplifica la explicación del recorrido.

## Responsabilidades de los métodos

### `mergeSort`

1. **Caso base:** si la longitud es cero o uno, imprimir que se alcanzó el caso base y devolver el arreglo tal como está; ya está ordenado.
2. Calcular el punto medio con `numeros.length / 2`.
3. Crear las mitades izquierda y derecha, sin perder ni duplicar elementos.
4. Imprimir el arreglo actual y las mitades resultantes antes de recursar.
5. Llamar a `mergeSort` para ordenar cada mitad de forma independiente.
6. Pasar ambas mitades ordenadas a `fusionar` y devolver su resultado.

Este método divide el problema y organiza las llamadas recursivas; no combina por sí mismo los elementos.

### `fusionar`

1. Recibir dos arreglos que ya están ordenados.
2. Usar un índice para cada arreglo y comparar sus elementos actuales.
3. Copiar al arreglo resultado el menor de los dos y avanzar el índice correspondiente.
4. Cuando una mitad se agote, copiar al resultado los elementos restantes de la otra.
5. Imprimir las dos mitades fusionadas y el arreglo combinado, y devolverlo.

Este método no divide ni hace llamadas recursivas; su responsabilidad es combinar dos resultados ordenados en uno solo también ordenado.

## Árbol de división esperado

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

Cada división corta el arreglo aproximadamente por la mitad hasta llegar a particiones de un elemento. Para una longitud impar, la mitad izquierda puede tener un elemento más; el punto medio debe mantener ambas partes y preservar todos los valores.

## Fusiones esperadas

Las fusiones ocurren al retornar de la recursión, desde las hojas hacia la raíz:

```text
Fusionar [38] + [27] -> [27, 38]
Fusionar [43] + [3] -> [3, 43]
Fusionar [27, 38] + [3, 43] -> [3, 27, 38, 43]
Fusionar [9] + [82] -> [9, 82]
Fusionar [10] + [5] -> [5, 10]
Fusionar [9, 82] + [5, 10] -> [5, 9, 10, 82]
Fusionar [3, 27, 38, 43] + [5, 9, 10, 82] -> [3, 5, 9, 10, 27, 38, 43, 82]
```

El arreglo final esperado es `[3, 5, 9, 10, 27, 38, 43, 82]`.

## Comentarios requeridos en el código

- Explicar que `mergeSort` divide recursivamente el problema y que los casos base detienen la división.
- Aclarar que las llamadas recursivas ordenan cada mitad por separado.
- Explicar que `fusionar` recibe mitades ya ordenadas y usa índices para elegir el menor elemento disponible.
- Indicar por qué se copian los elementos restantes después de que uno de los lados se agota.
- Hacer explícito que la división y la fusión son etapas distintas con responsabilidades diferentes.

## Complejidad esperada

- **Tiempo:** `O(n log n)`, porque el arreglo se divide en aproximadamente `log n` niveles y cada nivel procesa en conjunto `n` elementos durante las fusiones.
- **Espacio auxiliar:** `O(n)` por los subarreglos y arreglos de resultado creados durante la división y la fusión; la profundidad de las llamadas recursivas es `O(log n)` con divisiones equilibradas.

## Criterios de aceptación

- Se muestra el arreglo original y el arreglo final ordenado.
- La salida permite reconstruir el árbol de divisiones hasta los casos base.
- Cada llamada no base imprime sus mitades antes de recursar.
- Cada fusión se imprime después de combinar las dos mitades ordenadas.
- `mergeSort` divide y coordina la recursión; `fusionar` combina, sin mezclar las responsabilidades.
- El resultado del arreglo de demostración es `[3, 5, 9, 10, 27, 38, 43, 82]`.