# Plan técnico: invertir una palabra recursivamente

## Objetivo

Implementar en Java una función recursiva que reciba un `String` y devuelva sus caracteres en orden inverso.

## Diseño de la función

- **Nombre sugerido:** `invertirPalabra`.
- **Parámetro:** una cadena `palabra`.
- **Tipo de retorno:** `String`.
- **Suposición:** la función recibe una cadena no nula; el manejo de `null` queda fuera del alcance del ejercicio.

## Lógica recursiva

1. **Caso base:** si `palabra.length() <= 1`, devolver `palabra`. La cadena vacía y la de un solo carácter ya están invertidas, por lo que este caso detiene la recursión.
2. **Caso recursivo:** separar el primer carácter y devolver `invertirPalabra(palabra.substring(1)) + palabra.charAt(0)`. Se invierte el resto y luego se coloca el primer carácter al final.
3. En cada llamada se elimina el primer carácter, por lo que la cadena se acorta hasta alcanzar el caso base.

## Terminación y pila de llamadas

Cada llamada recursiva procesa una cadena un carácter más corta. La pila conserva los primeros caracteres pendientes de agregar al final mientras se invierte el resto.

Para `palabra = "hola"`, las llamadas pendientes se forman así:

```text
invertirPalabra("hola") espera invertirPalabra("ola") + 'h'
invertirPalabra("ola")  espera invertirPalabra("la") + 'o'
invertirPalabra("la")   espera invertirPalabra("a") + 'l'
invertirPalabra("a")    caso base: devuelve "a"
```

Al retornar, se reconstruye el resultado: `"a" + 'l' = "al"`, `"al" + 'o' = "alo"` y `"alo" + 'h' = "aloh"`.

## Comentarios requeridos en el código

- Explicar por qué una cadena vacía o de un carácter es el caso base y detiene la recursión.
- Explicar que la llamada recursiva invierte la subcadena que comienza en el segundo carácter y agrega el primero al final.
- Describir cómo la pila conserva los caracteres y reconstruye la palabra al retornar las llamadas.

## Pruebas manuales

| Caso | Entrada | Resultado esperado |
|---|---|---|
| 1 | `palabra = "a"` | `"a"` |
| 2 | `palabra = "hola"` | `"aloh"` |
| 3 | `palabra = "recursividad"` | `"dadivisrucer"` |

## Criterios de aceptación

- La función recibe un `String` y devuelve otro `String` con los caracteres en orden inverso.
- El caso base devuelve la cadena cuando `length() <= 1`.
- El caso recursivo invierte el resto de la cadena y concatena al final el primer carácter.
- Cada llamada reduce la longitud de la cadena y alcanza el caso base.
- Las tres pruebas producen los resultados esperados y el código comenta el caso base, el caso recursivo y la pila de llamadas.
