# Plan técnico: validar paréntesis balanceados

## Objetivo

Implementar en Java una validación de paréntesis para una expresión matemática recibida como `String`. El método debe devolver exactamente `"válido"` o `"inválido"` según los paréntesis estén balanceados.

## Diseño

- **Método sugerido:** `validarParentesis(String expresion)`, con retorno `String`.
- **Estructura auxiliar:** una pila de caracteres, por ejemplo `Deque<Character>` usada con sus operaciones `push`, `pop` e `isEmpty`.
- **Suposición:** la expresión recibida no es `null`.
- **Alcance:** se valida el balance de `(` y `)`; los números, operadores, espacios y demás caracteres se ignoran. No se evalúa la sintaxis completa de la expresión ni se validan otros tipos de delimitadores.

## Estrategia

1. Crear una pila vacía para registrar los paréntesis de apertura que todavía no tienen pareja.
2. Recorrer la expresión carácter por carácter.
3. Si el carácter es `(`, agregarlo a la pila con `push`.
4. Si es `)`, comprobar primero si la pila está vacía. Si lo está, devolver `"inválido"` inmediatamente porque apareció un cierre sin apertura previa. Si contiene un `(`, retirarlo con `pop` para emparejarlo con este cierre.
5. Ignorar todos los demás caracteres.
6. Al terminar el recorrido, devolver `"válido"` solo si la pila quedó vacía; si quedan aperturas pendientes, devolver `"inválido"`.

La pila es adecuada porque el paréntesis abierto más reciente debe cerrarse primero. Al apilar aperturas y retirar una con cada cierre, el algoritmo respeta el orden LIFO y nunca intenta hacer `pop` sobre una pila vacía.

## Casos de invalidez

- **Cierre sin apertura:** encontrar `)` cuando la pila está vacía. Se detecta durante el recorrido y la validación termina de inmediato.
- **Apertura sin cierre:** terminar el recorrido con uno o más `(` en la pila. Se detecta con la comprobación final.

## Pruebas requeridas

| Expresión | Recorrido relevante | Resultado esperado |
|---|---|---|
| `(5+3)*(2+1)` | Cada `(` se apila y cada `)` retira una apertura; al final la pila está vacía. | `válido` |
| `(5+3))*(2+1` | El segundo `)` encuentra la pila vacía después de emparejar el primer par. | `inválido` |

El segundo caso también contiene un `(` final sin cierre, pero el método puede devolver `"inválido"` antes, al detectar el cierre excedente.

## Comentarios requeridos en el código

- Explicar que la pila guarda únicamente aperturas pendientes.
- Indicar que cada `(` se agrega con `push` y cada `)` emparejado retira la apertura más reciente con `pop`.
- Explicar por qué se comprueba `isEmpty()` antes del `pop`.
- Aclarar que la pila debe estar vacía al terminar para que la expresión sea válida.
- Indicar que los demás caracteres no participan en la validación solicitada.

## Complejidad

- **Tiempo:** `O(n)`, donde `n` es la longitud de la expresión, porque cada carácter se examina una vez.
- **Espacio auxiliar:** `O(n)` en el peor caso, si la expresión contiene solo paréntesis de apertura.

## Criterios de aceptación

- El método recorre la expresión una sola vez y usa una pila LIFO para las aperturas pendientes.
- Nunca ejecuta `pop` si la pila está vacía.
- Detecta cierres sin apertura durante el recorrido y aperturas sin cierre al final.
- Devuelve `"válido"` para `(5+3)*(2+1)` y `"inválido"` para `(5+3))*(2+1`.
- La implementación no pretende validar operadores, operandos ni otros delimitadores.