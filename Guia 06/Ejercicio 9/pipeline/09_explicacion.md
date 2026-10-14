# Ejercicio 9: comprobar palíndromos con recursividad

## ¿Qué hace el programa?

La clase `Palindromo` contiene el método `esPalindromo`, que recibe una cadena y devuelve un `boolean`: `true` cuando la cadena es un palíndromo y `false` cuando no lo es. Un palíndromo se lee igual de izquierda a derecha que de derecha a izquierda.

El método `main` prueba las tres entradas solicitadas:

| Entrada | Resultado |
|---|---|
| `"neuquen"` | `true` |
| `"a"` | `true` |
| `"hola"` | `false` |

La función verifica pares de caracteres simétricos: compara el primero con el último, luego el segundo con el penúltimo y así sucesivamente. Cada vez que un par coincide, basta con comprobar la parte que queda entre ellos.

## Cómo se compara y reduce la cadena

La primera comparación del método es equivalente a:

```java
palabra.charAt(0) != palabra.charAt(palabra.length() - 1)
```

- `length()` devuelve la cantidad de caracteres de la cadena.
- Los índices de `String` empiezan en `0`; por eso el primer carácter está en el índice `0`.
- El último índice válido es `length() - 1`.
- `charAt(indice)` devuelve el carácter que ocupa ese índice.

Si los dos caracteres son diferentes, el método retorna `false` de inmediato. Ya hay una pareja simétrica que no coincide, así que ninguna comprobación del centro podría convertir la palabra en palíndromo. No se hace otra llamada recursiva.

Si son iguales, el método ejecuta:

```java
return esPalindromo(palabra.substring(1, palabra.length() - 1));
```

`substring(inicio, fin)` incluye el índice `inicio` y excluye el índice `fin`. Por tanto, `substring(1, palabra.length() - 1)` descarta el carácter inicial y el final y conserva el centro. Por ejemplo:

```text
"neuquen" -> substring(1, 6) -> "euque"
"euque"   -> substring(1, 4) -> "uqu"
"uqu"     -> substring(1, 2) -> "q"
```

Cada llamada recursiva acorta la cadena en dos caracteres. Si la longitud es par, eventualmente queda una cadena vacía; si es impar, queda un solo carácter.

## Caso base

El método comprueba primero:

```java
if (palabra.length() <= 1) {
    return true;
}
```

Una cadena vacía (`""`) y una cadena de un carácter (por ejemplo, `"a"`) son palíndromos por definición: no hay dos extremos distintos que puedan contradecirlo. Devolver `true` detiene la recursión. Esta condición también evita intentar leer extremos cuando ya no hay una pareja que comparar.

## Pila de llamadas para `"neuquen"`

Al encontrar extremos iguales, cada llamada queda pausada y espera el resultado de comprobar la parte interior. La pila crece así; la llamada más reciente aparece al final:

```text
┌──────────────────────────────────────────────────────┐
│ esPalindromo("neuquen") compara 'n' con 'n'          │
│ espera el resultado de esPalindromo("euque")         │
├──────────────────────────────────────────────────────┤
│ esPalindromo("euque") compara 'e' con 'e'            │
│ espera el resultado de esPalindromo("uqu")           │
├──────────────────────────────────────────────────────┤
│ esPalindromo("uqu") compara 'u' con 'u'              │
│ espera el resultado de esPalindromo("q")             │
├──────────────────────────────────────────────────────┤
│ esPalindromo("q") alcanza el caso base y devuelve true│
└──────────────────────────────────────────────────────┘
```

La pila se resuelve en orden inverso al que se formó:

```text
esPalindromo("q")       devuelve true   <- caso base
esPalindromo("uqu")     recibe true; sus extremos coinciden, devuelve true
esPalindromo("euque")   recibe true; sus extremos coinciden, devuelve true
esPalindromo("neuquen") recibe true; sus extremos coinciden, devuelve true
```

Por eso `esPalindromo("neuquen")` termina devolviendo `true`: todas las parejas comparadas coincidieron y la llamada más interna llegó al caso base.

**Nota sobre la traza del análisis:** el documento `09_analisis.md` menciona la secuencia `"neuquen"` → `"euque"` → `"uq"` → `""`. Esa secuencia no corresponde exactamente a los índices que usa el código: `substring(1, length - 1)` produce `"uqu"` desde `"euque"`, y luego `"q"` desde `"uqu"`. La conclusión (`true`) no cambia, pero la pila descrita arriba refleja el comportamiento real de Java.

## Qué ocurre cuando los extremos difieren

Por ejemplo, para `"hola"`, la primera llamada compara `'h'` con `'a'`. Como no coinciden, ejecuta `return false` inmediatamente. No llega a `substring` ni crea una llamada recursiva.

Si la diferencia aparece en una llamada interior, ocurre lo mismo allí: esa llamada devuelve `false`. Las llamadas anteriores que estaban esperando reciben ese `false` y también lo devuelven, porque su expresión de retorno es directamente el resultado de la llamada recursiva. Así, un solo par diferente basta para que el resultado final sea `false`.

## Errores comunes

- **Índices incorrectos en `charAt`:** los índices válidos van de `0` a `length() - 1`. Usar `charAt(length())` excede el final de la cadena y puede lanzar `StringIndexOutOfBoundsException`.
- **Límites incorrectos en `substring`:** el límite final no se incluye. En esta solución, usar `substring(1, length())` quitaría solo el primer carácter y no ambos extremos. Un inicio mayor que el fin, o índices fuera del rango permitido, pueden causar `StringIndexOutOfBoundsException`.
- **Olvidar reducir dos caracteres:** la llamada recursiva debe procesar la subcadena interior. Si vuelve a llamar con la misma cadena, nunca alcanza el caso base y acaba acumulando llamadas hasta un `StackOverflowError`.
- **Omitir el caso base o revisarlo tarde:** intentar comparar caracteres cuando la cadena está vacía puede causar una excepción de índice. El caso base debe ejecutarse antes de `charAt` y `substring`.
- **Suponer que Java ignora mayúsculas, tildes o espacios:** esta implementación compara literalmente los caracteres tal como se reciben. Por ejemplo, `'N'` y `'n'` son distintos, y los espacios cuentan como caracteres. Una frase que solo sea palíndroma al quitar espacios o signos podría devolver `false`.
- **Pasar `null`:** el plan supone que el argumento no es nulo. Si se llama `esPalindromo(null)`, `palabra.length()` lanza `NullPointerException`; no es un error de límites de `substring`.

## Resumen

El método tiene tres resultados posibles en cada nivel: devuelve `true` al llegar a una cadena de longitud cero o uno; devuelve `false` apenas encuentra extremos diferentes; o, si los extremos coinciden, delega la comprobación al centro mediante una nueva llamada con `substring`. La pila conserva las comprobaciones pendientes y propaga el resultado al regresar desde el caso base.