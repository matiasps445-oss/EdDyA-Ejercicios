# Plan técnico: comprobar palíndromos recursivamente

## Objetivo

Implementar en Java una función recursiva que determine si una palabra se lee igual de izquierda a derecha que de derecha a izquierda y devuelva `true` o `false`.

## Diseño de la función

- **Nombre sugerido:** `esPalindromo`.
- **Parámetro:** una cadena `palabra`.
- **Tipo de retorno:** `boolean`.
- **Suposición:** la función recibe una cadena no nula. Se evalúa el contenido tal como se recibe, sin normalizar mayúsculas, tildes ni espacios; las pruebas usan palabras simples.

## Lógica recursiva

1. **Caso base:** si `palabra.length() <= 1`, devolver `true`. Una cadena vacía o de un carácter es palíndromo por definición, así que esta condición detiene la recursión.
2. **Comparación de extremos:** comparar el primer carácter (`palabra.charAt(0)`) con el último (`palabra.charAt(palabra.length() - 1)`). Si son distintos, devolver `false` inmediatamente: ya existe una diferencia que impide que la palabra sea palíndromo.
3. **Caso recursivo:** si los extremos coinciden, llamar al método con `palabra.substring(1, palabra.length() - 1)`, que elimina ambos extremos. Devolver el resultado de esa llamada.
4. Cada llamada reduce la cadena en dos caracteres. La secuencia alcanza el caso base cuando queda una cadena vacía o de un carácter.

## Terminación y pila de llamadas

Cada llamada que encuentra extremos iguales espera el resultado de comprobar la subcadena interior. Para `palabra = "neuquen"`, las comparaciones y llamadas pendientes son:

```text
esPalindromo("neuquen") compara 'n' con 'n'; espera esPalindromo("euque")
esPalindromo("euque")  compara 'e' con 'e'; espera esPalindromo("uq")
esPalindromo("uq")     compara 'u' con 'u'; espera esPalindromo("")
esPalindromo("")       caso base: devuelve true
```

Al retornar, cada llamada recibe `true` y lo devuelve porque sus extremos coincidían. Por lo tanto, `esPalindromo("neuquen")` devuelve `true`.

Si en cualquier nivel los extremos son distintos, la función devuelve `false` en ese momento y no crea más llamadas. Para `"hola"`, compara `'h'` con `'a'` y retorna `false` inmediatamente.

## Comentarios requeridos en el código

- Explicar que una cadena de longitud cero o uno es el caso base y devuelve `true`.
- Explicar que si los extremos son distintos se devuelve `false` sin continuar la recursión.
- Explicar que, si coinciden, se comprueba recursivamente la subcadena interior.
- Describir cómo las llamadas que encontraron extremos iguales esperan el resultado y lo propagan al retornar desde el caso base.

## Pruebas manuales

| Caso | Entrada | Resultado esperado |
|---|---|---|
| 1 | `palabra = "neuquen"` | `true` |
| 2 | `palabra = "a"` | `true` |
| 3 | `palabra = "hola"` | `false` |

## Criterios de aceptación

- La función recibe una palabra y devuelve un valor `boolean`.
- El caso base devuelve `true` cuando `length() <= 1`.
- Si el primer y último carácter son distintos, devuelve `false` sin realizar otra llamada recursiva.
- Si coinciden, la llamada recursiva procesa la subcadena sin el primer ni el último carácter.
- La recursión termina al reducir la cadena hasta longitud cero o uno.
- Las tres pruebas producen los resultados esperados y el código comenta el caso base, el caso recursivo y la pila de llamadas.
