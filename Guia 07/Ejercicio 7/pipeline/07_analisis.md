# Plan técnico: peor caso de Quicksort con primer pivote

## Objetivo

Implementar en Java Quicksort sobre el arreglo ordenado `{1, 2, 3, 4, 5, 6, 7}`, eligiendo siempre el primer elemento de cada partición como pivote. El programa debe mostrar el arreglo final y contar las llamadas recursivas.

## Diseño

- **Método sugerido:** un método público para ordenar el arreglo y un método auxiliar recursivo que recibe la partición y el contador.
- **Pivote:** siempre el primer elemento de la partición actual.
- **Particiones:** menores que el pivote a la izquierda; mayores o iguales a la derecha. Incluir iguales en la derecha conserva todos los elementos.
- **Resultado:** mostrar el arreglo ordenado y el total de llamadas recursivas.
- **Definición del contador:** contar cada llamada a la función recursiva generada para una partición hija, incluso si esa partición es vacía o tiene un elemento. Excluir la llamada inicial del método público. Con esta convención, informar también en la explicación que las invocaciones totales, incluida la inicial, son una más.

## Casos base

Una partición vacía o de un solo elemento ya está ordenada y no genera nuevas llamadas. Comprobar `longitud <= 1` antes de elegir el pivote permite que cada rama termine correctamente.

## Estrategia recursiva

1. Recibir la partición actual.
2. Si tiene cero o un elemento, devolverla como resultado ordenado.
3. Elegir el primer elemento (`numeros[0]`) como pivote.
4. Recorrer el resto y distribuirlo en dos subarreglos: los valores `< pivote` en la izquierda y los valores `>= pivote` en la derecha.
5. Antes de cada llamada recursiva a una partición hija, incrementar el contador una vez. Llamar al método para ordenar la izquierda y la derecha; las llamadas a casos base también cuentan porque son llamadas recursivas reales.
6. Combinar la izquierda ordenada, el pivote y la derecha ordenada y devolver el resultado.

## Por qué el arreglo ordenado es el peor caso

Al comenzar con `{1, 2, 3, 4, 5, 6, 7}`, el primer pivote es `1`. Ningún elemento restante es menor, de modo que la partición izquierda queda vacía y la derecha contiene `[2, 3, 4, 5, 6, 7]`. La siguiente llamada vuelve a elegir su primer elemento (`2`), dejando otra vez una partición izquierda vacía y la derecha con todos los demás elementos. El patrón continúa hasta llegar a una partición de un elemento.

Cada partición no trivial recorre todos sus elementos restantes, pero solo elimina el pivote y deja una rama de tamaño `n - 1`. Para esta entrada, el trabajo de partición es proporcional a `6 + 5 + 4 + 3 + 2 + 1`, es decir, crece cuadráticamente. En general, este caso tiene tiempo `O(n^2)` y profundidad recursiva `O(n)`.

## Conteo esperado

Con la convención definida —contar las llamadas a las particiones hijas y no la llamada inicial— el conteo se obtiene así:

| Tamaño de la partición que se divide | Partición izquierda | Partición derecha | Llamadas recursivas generadas |
|---:|---|---|---:|
| 7 | `[]` | `[2, 3, 4, 5, 6, 7]` | 2 |
| 6 | `[]` | `[3, 4, 5, 6, 7]` | 2 |
| 5 | `[]` | `[4, 5, 6, 7]` | 2 |
| 4 | `[]` | `[5, 6, 7]` | 2 |
| 3 | `[]` | `[6, 7]` | 2 |
| 2 | `[]` | `[7]` | 2 |
| 1 | caso base | caso base | 0 |
| **Total** |  |  | **12** |

Las 12 llamadas incluyen las llamadas a particiones vacías y unitarias, que inmediatamente alcanzan el caso base. Si también se cuenta la primera entrada al método recursivo desde el método público, hay 13 invocaciones en total. El arreglo final debe ser `[1, 2, 3, 4, 5, 6, 7]`.

## Comentarios requeridos en el código

- Explicar qué llamada incrementa el contador y si incluye o excluye la llamada inicial.
- Indicar que el caso base devuelve las particiones de longitud cero o uno sin seguir recursando.
- Explicar que el primer elemento de cada partición es el pivote.
- Justificar que, en este arreglo ordenado, cada pivote es el menor de su partición y deja vacía la izquierda.
- Describir por qué la sucesión de particiones de tamaño `n - 1` degrada el tiempo a `O(n^2)` y hace crecer la pila recursiva.

## Criterios de aceptación

- Se procesa `{1, 2, 3, 4, 5, 6, 7}` y se usa el primer elemento de cada partición como pivote.
- El arreglo final se muestra ordenado como `[1, 2, 3, 4, 5, 6, 7]`.
- El contador usa la convención explícita del plan y muestra 12 llamadas recursivas, excluyendo la entrada inicial (13 invocaciones en total).
- La explicación del código relaciona las particiones desbalanceadas con `O(n^2)` y el aumento de la profundidad recursiva.