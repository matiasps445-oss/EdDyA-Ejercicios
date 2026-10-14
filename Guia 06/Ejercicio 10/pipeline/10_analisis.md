# Plan técnico: búsqueda recursiva en un arreglo

## Objetivo

Implementar en Java una función recursiva que busque un número en un arreglo de enteros y devuelva `true` si lo encuentra o `false` si no aparece. La búsqueda comienza en el índice `0` y no utiliza ciclos.

## Diseño de la función

- **Nombre sugerido:** `buscar`.
- **Parámetros:** el arreglo `int[] arreglo`, el valor `int buscado` y el índice actual `int indice`.
- **Tipo de retorno:** `boolean`.
- **Inicio:** invocar la función con `indice = 0`.
- **Suposición:** el arreglo no es `null`; puede estar vacío.

## Lógica recursiva

1. **Caso base de fin:** comprobar primero si `indice == arreglo.length`. Si se cumple, devolver `false`: se revisaron todas las posiciones y el valor no está. Esta comprobación debe ocurrir antes de acceder a `arreglo[indice]`, para que también funcione con un arreglo vacío.
2. **Caso base de éxito:** si `arreglo[indice] == buscado`, devolver `true` inmediatamente; ya se encontró el valor y no hace falta seguir buscando.
3. **Caso recursivo:** si el valor actual no coincide, llamar a `buscar(arreglo, buscado, indice + 1)` y devolver el resultado. Cada llamada avanza una posición sin usar `for` ni `while`.

## Terminación y pila de llamadas

Cada llamada que no encuentra el valor en su índice actual queda pendiente mientras busca desde el índice siguiente. Para `arreglo = [1, 2, 3, 4, 5]` y `buscado = 3`, la búsqueda avanza así:

```text
buscar(arreglo, 3, 0): 1 no coincide; espera buscar(arreglo, 3, 1)
buscar(arreglo, 3, 1): 2 no coincide; espera buscar(arreglo, 3, 2)
buscar(arreglo, 3, 2): 3 coincide; devuelve true
```

Al retornar, las llamadas pendientes propagan `true` hasta la llamada inicial. Si el valor no aparece, las llamadas avanzan hasta `indice == arreglo.length`; ese caso base devuelve `false`, que se propaga de regreso por la pila. Con un arreglo vacío, la primera llamada tiene `indice = 0` y `arreglo.length = 0`, por lo que devuelve `false` sin intentar acceder a una posición.

## Comentarios requeridos en el código

- Explicar el caso base que detecta el final del arreglo y devuelve `false`.
- Explicar el caso base que devuelve `true` cuando el valor coincide con el elemento actual.
- Explicar que el caso recursivo avanza con `indice + 1` y delega la búsqueda al resto del arreglo.
- Describir cómo las llamadas pendientes esperan el resultado y lo propagan al desarmarse la pila.

## Pruebas manuales

| Caso | Entrada | Resultado esperado |
|---|---|---|
| 1 | `arreglo = [1, 2, 3, 4, 5]`, `buscado = 3` | `true` |
| 2 | `arreglo = [1, 2, 3, 4, 5]`, `buscado = 6` | `false` |
| 3 | `arreglo = []`, `buscado = 1` | `false` |

## Criterios de aceptación

- La función devuelve un `boolean` e inicia la búsqueda desde el índice `0`.
- No se utilizan ciclos; cada avance se realiza mediante una llamada recursiva con `indice + 1`.
- El fin del arreglo se comprueba antes de acceder al elemento actual.
- La función devuelve `true` al encontrar el valor y `false` al alcanzar el final sin encontrarlo.
- Los tres casos de prueba producen los resultados esperados, incluido el arreglo vacío.
- El código incluye comentarios sobre los casos base, el caso recursivo y la propagación de resultados en la pila de llamadas.
