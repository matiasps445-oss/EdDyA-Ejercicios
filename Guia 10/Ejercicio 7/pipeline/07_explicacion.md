# Explicación: verificar paréntesis balanceados

## Por qué una pila encaja con la sintaxis anidada

Los paréntesis pueden anidarse: si se abre un grupo dentro de otro, ese grupo interior debe cerrarse antes que el exterior. La pila modela exactamente esa regla LIFO. Cada `(` se apila como una apertura pendiente; cuando aparece `)`, se retira la apertura más reciente que todavía no tenía pareja.

La misma idea aparece en compiladores e intérpretes al procesar expresiones anidadas, delimitadores y ámbitos: el último bloque que se abrió es el primero que debe cerrarse. Por ejemplo, en `((a+b)*c)`, el segundo `(` debe emparejarse antes que el primero. Una estructura FIFO no serviría, porque intentaría emparejar el cierre con la apertura más antigua y perdería la correspondencia correcta del anidamiento.

En este ejercicio, `PilaCaracteres` implementa la pila con nodos enlazados. Cada nodo guarda un `char` y un enlace al nodo anterior. `head` es el tope: `apilar` lo inserta al inicio y `desapilar` lo retira del inicio. Así, cada operación de pila cuesta $O(1)$.

## Seguimiento de `())(` carácter por carácter

El método `estaBalanceada` recorre el texto de izquierda a derecha. En `())(` ocurre lo siguiente:

| Índice | Carácter | Acción | Estado de la pila |
|---:|:---:|---|---|
| 0 | `(` | Se apila una apertura | `(` |
| 1 | `)` | La pila no está vacía; se desapila la apertura | vacía |
| 2 | `)` | La pila ya está vacía; se devuelve `false` inmediatamente | vacía |
| 3 | `(` | No se procesa: el método ya terminó | vacía |

El tercer carácter intenta cerrar un bloque sin que exista una apertura pendiente. El `if (aperturas.estaVacia())` detecta esa situación antes de llamar a `desapilar()`, por lo que el verificador devuelve `false` en vez de provocar una excepción por desapilar una pila vacía.

La otra condición de fallo se detecta al final: si el ciclo terminó y la pila aún tiene elementos, quedaron aperturas sin pareja, como en `(2+3`. Por eso la respuesta final es `aperturas.estaVacia()`.

## Por qué se ignoran los demás caracteres

La tarea comprueba el balance de `(` y `)`, no la validez completa de la expresión matemática. Los números, letras, espacios y operadores como `+` o `*` no abren ni cierran grupos, así que no cambian el estado de la pila y se ignoran.

Esto implica que el método responde una pregunta concreta: “¿Los paréntesis están balanceados?”. No comprueba si la expresión está bien formada en otros aspectos; por ejemplo, una cadena con operadores mal colocados podría devolver `true` si sus paréntesis sí están balanceados.

## Resultados de los ejemplos

- `(2+3)*(5-1)` devuelve `true`: ambos pares se cierran y la pila termina vacía.
- `((a+b)*c)` devuelve `true`: primero se cierra el grupo interior y luego el exterior, respetando LIFO.
- `(2+3` devuelve `false`: queda una apertura pendiente al terminar.
- `())(` devuelve `false`: el tercer carácter es un cierre sobrante y el método termina en ese punto.
