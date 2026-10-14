# Plan técnico: Insertion Sort con arreglo casi ordenado

## Objetivo

Implementar en Java el ordenamiento por inserción sobre el arreglo casi ordenado `{1, 2, 3, 5, 4, 6, 7}`. El programa debe ordenar el arreglo de menor a mayor y mostrar el resultado junto con el número exacto de desplazamientos.

## Diseño

- **Método sugerido:** `ordenarPorInsercion(int[] numeros)`.
- **Comportamiento:** ordenar el arreglo recibido en el mismo lugar y devolver la cantidad de desplazamientos.
- **Demostración:** `main` declara el arreglo del ejercicio, llama al método y muestra el arreglo ordenado con `Arrays.toString(...)` y el contador de desplazamientos.
- **Definición del contador:** contar cada movimiento de un elemento existente una posición hacia la derecha para abrir espacio a la clave. La asignación final de la clave en su lugar no se cuenta como desplazamiento.

## Estrategia del algoritmo

1. Considerar inicialmente que el primer elemento forma una porción ordenada.
2. Recorrer el arreglo desde el índice `1`. En cada iteración, guardar el elemento actual en una variable `clave` y comenzar a revisar desde el elemento anterior (`indice - 1`).
3. Mientras el índice anterior sea válido y el elemento allí sea mayor que `clave`, copiar ese elemento una posición hacia la derecha y aumentar el contador de desplazamientos.
4. Cuando el `while` termina, insertar `clave` en la posición `indice + 1`. La parte recorrida queda ordenada y se puede continuar con el siguiente elemento.

La condición del `while` debe evaluar primero `posicion >= 0` y después `numeros[posicion] > clave`, usando `&&`. Java evalúa `&&` de izquierda a derecha y con cortocircuito; si la posición ya es negativa, no intenta acceder a un índice inválido. El ciclo también se detiene cuando encuentra un elemento menor o igual que la clave, pues esta ya va después de ese elemento.

## Seguimiento del arreglo indicado

| Clave procesada | Trabajo realizado | Arreglo después de insertarla | Desplazamientos acumulados |
|---|---|---|---:|
| `2` | No desplaza: `1 <= 2`. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| `3` | No desplaza: `2 <= 3`. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| `5` | No desplaza: `3 <= 5`. | `[1, 2, 3, 5, 4, 6, 7]` | 0 |
| `4` | Desplaza el `5` una posición a la derecha y coloca `4` en el hueco. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |
| `6` | No desplaza: `5 <= 6`. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |
| `7` | No desplaza: `6 <= 7`. | `[1, 2, 3, 4, 5, 6, 7]` | 1 |

El resultado esperado es `[1, 2, 3, 4, 5, 6, 7]` con **1 desplazamiento**. La clave `4` se guarda temporalmente, el `5` se copia hacia la derecha y la clave ocupa el espacio que quedó libre. No se cuentan las comparaciones ni la escritura final de la clave.

## Comentarios requeridos en el código

- Explicar que el contador aumenta cada vez que un elemento mayor que `clave` se mueve una posición a la derecha.
- Explicar que el `while` se detiene al llegar a una posición negativa o al encontrar un valor menor o igual que la clave.
- Aclarar por qué la comprobación del índice aparece antes del acceso al arreglo.
- Indicar que, al terminar el `while`, la clave se coloca en el hueco correcto de la porción ordenada.

## Eficiencia esperada

Insertion Sort es adecuado para esta entrada casi ordenada porque la mayoría de las claves encuentran su posición tras una sola comparación con el elemento anterior y no provocan desplazamientos. En este ejemplo solo el `5` debe moverse una posición para insertar el `4`. Para una entrada ya ordenada, el algoritmo realiza cero desplazamientos; en el peor caso, cuando los datos están en orden inverso, puede realizar un número cuadrático de desplazamientos.

## Criterios de aceptación

- El arreglo se ordena ascendentemente mediante Insertion Sort.
- El algoritmo construye la porción ordenada insertando cada clave en su posición.
- El `while` verifica el índice antes de acceder a `numeros[posicion]` y se detiene al encontrar un elemento menor o igual que la clave.
- El contador aumenta solo al desplazar un elemento existente hacia la derecha.
- Para `{1, 2, 3, 5, 4, 6, 7}`, se muestra `[1, 2, 3, 4, 5, 6, 7]` y se informa exactamente `1` desplazamiento.
- Los comentarios explican el contador y la condición de terminación anticipada del `while`.