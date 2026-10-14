# Explicación de `ValidarParentesis`

El método `validarParentesis` recorre una expresión y devuelve `"válido"` si cada paréntesis de apertura tiene su cierre correspondiente, o `"inválido"` si encuentra un desbalance. La pila se implementa con `Deque<Character>` usando `ArrayDeque`; se apilan únicamente los caracteres `(` que todavía están pendientes.

## Por qué una pila LIFO es adecuada

LIFO significa **Last In, First Out**. Si se abren varios paréntesis antes de cerrarlos, el último que se abrió es el primero que debe cerrarse. Por ejemplo, en `((...))`, el primer `)` debe emparejarse con el segundo `(`, no con el primero.

`push('(')` agrega una apertura a la cima. Cuando aparece `)`, `pop()` retira esa apertura más reciente y forma el par. Los paréntesis se procesan en el orden en que aparecen, pero la pila conserva cuáles siguen pendientes y cuál debe cerrarse primero.

## Simulación de `(5+3)*(2+1)`

La cima de la pila se muestra a la derecha. Los números y operadores se ignoran; solo modifican la pila los paréntesis:

| Carácter leído | Acción | Pila después de la acción |
|---|---|---|
| Inicio | Crear la pila vacía | `[]` |
| `(` | `push('(')` | `[(]` |
| `5` | Ignorar | `[(]` |
| `+` | Ignorar | `[(]` |
| `3` | Ignorar | `[(]` |
| `)` | La pila no está vacía; `pop()` empareja el cierre | `[]` |
| `*` | Ignorar | `[]` |
| `(` | `push('(')` | `[(]` |
| `2` | Ignorar | `[(]` |
| `+` | Ignorar | `[(]` |
| `1` | Ignorar | `[(]` |
| `)` | `pop()` empareja el cierre | `[]` |
| Fin | La pila está vacía | devuelve `"válido"` |

La pila termina vacía porque cada apertura encontró un cierre. El símbolo `*` no cambia el resultado: este programa valida el balance de paréntesis, no la sintaxis completa ni el valor matemático de la expresión.

## Cómo se detecta el desbalance

Hay dos formas de que la expresión sea inválida:

1. **Cierre sobrante:** cuando aparece `)`, el método consulta primero `pila.isEmpty()`. Si está vacía, devuelve `"inválido"` inmediatamente. Así evita hacer `pop()` sobre una pila vacía. En `(5+3))*(2+1`, el primer cierre vacía la pila y el segundo cierre la encuentra vacía, por lo que el método termina en ese punto.
2. **Apertura sin cierre:** si se termina de recorrer la expresión y la pila todavía contiene `(`, devuelve `"inválido"`. Por ejemplo, `(5+3` deja una apertura pendiente al final.

El orden de las comprobaciones importa: verificar que haya una apertura antes de llamar a `pop()` evita una excepción y permite devolver el resultado solicitado de manera controlada.

## Qué hace `main`

`main` define las dos expresiones indicadas y llama a `validarParentesis` para cada una. El resultado esperado es:

```text
(5+3)*(2+1) -> válido
(5+3))*(2+1 -> inválido
```

Cada carácter se analiza una sola vez, por lo que el tiempo es `O(n)`. En el peor caso, una expresión con muchas aperturas consecutivas guarda hasta `n` caracteres en la pila, así que el espacio auxiliar es `O(n)`.

## Reflexión sobre el prompt

El prompt inicial detalló con claridad cuándo usar `push` para guardar una apertura y cuándo usar `pop` para emparejarla con un cierre. También especificó los dos casos de invalidez y las pruebas esperadas, por lo que no fue necesario ajustar el prompt para obtener un análisis y desarrollo exitosos.