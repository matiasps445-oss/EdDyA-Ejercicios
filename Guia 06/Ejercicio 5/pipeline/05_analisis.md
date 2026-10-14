# Plan técnico: conteo regresivo

## Objetivo

Implementar en Java una función recursiva que imprima los enteros desde `n` hasta `0`, en orden descendente e incluyendo ambos extremos cuando `n` es no negativo.

## Diseño de la función

- **Nombre sugerido:** `conteoRegresivo`.
- **Parámetro:** un entero `n` que representa el valor inicial.
- **Tipo de retorno:** `void`, porque la función realiza la acción de imprimir y no calcula un resultado para devolver.
- **Efecto observable:** escribe cada valor del conteo en la consola, uno por línea.

## Lógica recursiva

1. **Caso base:** si `n < 0`, terminar la función sin imprimir. Al llegar a `-1`, ya se imprimió `0` en la llamada anterior y el conteo queda completo.
2. **Caso recursivo:** imprimir `n` y llamar a `conteoRegresivo(n - 1)`. Cada llamada reduce el argumento y lo acerca al caso base.
3. La comprobación del caso base debe ocurrir antes de imprimir para que no aparezca `-1` en la salida.

## Terminación y pila de llamadas

Para un valor inicial no negativo, cada llamada disminuye `n` en uno. La secuencia alcanza `-1`, satisface el caso base y la recursión termina. La pila conserva las llamadas pendientes; después de que la llamada con `-1` retorna, las llamadas anteriores también retornan sin trabajo adicional.

Usar `n + 1` en vez de `n - 1` alejaría el argumento del caso base. La recursión no terminaría normalmente y acabaría provocando un desbordamiento de pila (`StackOverflowError`).

## Comentarios requeridos en el código

- Explicar que el caso base detiene la función cuando `n < 0`.
- Explicar que el caso recursivo imprime el valor actual y continúa con `n - 1`.
- Describir cómo se apilan las llamadas y cómo retornan al alcanzarse el caso base.

## Pruebas manuales

| Caso | Entrada | Salida esperada |
|---|---:|---|
| 1 | `n = 5` | `5`, `4`, `3`, `2`, `1`, `0` (uno por línea) |
| 2 | `n = 1` | `1`, `0` (uno por línea) |
| 3 | `n = 0` | `0` |

## Criterios de aceptación

- La salida comienza en el valor recibido y desciende de uno en uno hasta `0`, inclusive.
- No se imprime ningún valor negativo.
- La ejecución termina porque las llamadas avanzan hacia `n < 0`.
- La función no devuelve un valor y los comentarios explican los tres aspectos solicitados.