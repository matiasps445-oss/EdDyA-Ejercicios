# Ejercicio 8: invertir una palabra con recursividad

## ¿Qué hace el programa?

La clase `InvertirPalabra` contiene un método que recibe una cadena y devuelve sus caracteres en orden inverso. El método `main` prueba tres entradas:

| Entrada | Resultado |
|---|---|
| `"a"` | `"a"` |
| `"hola"` | `"aloh"` |
| `"recursividad"` | `"dadivisrucer"` |

La idea recursiva es tomar el primer carácter, invertir todo lo que queda y agregar ese primer carácter al final:

```text
invertirPalabra(palabra) = invertirPalabra(resto) + primer carácter
```

## Cómo se separa la cadena

La expresión central del método es:

```java
return invertirPalabra(palabra.substring(1)) + palabra.charAt(0);
```

- `palabra.substring(1)` devuelve la parte de la cadena que comienza en el índice `1`. Como los índices de un `String` comienzan en `0`, esto excluye el carácter inicial y conserva el resto. Por ejemplo, para `"hola"`, devuelve `"ola"`.
- `palabra.charAt(0)` obtiene el carácter que está en el índice `0`, es decir, el primero. Para `"hola"`, devuelve `'h'`.
- Primero se evalúa la llamada recursiva de la izquierda. Cuando esta devuelve una cadena, se concatena con el primer carácter de esta llamada.

En cada paso, `substring(1)` hace que la entrada tenga un carácter menos: `"hola"`, `"ola"`, `"la"`, `"a"`. Así, el problema se reduce hasta que puede resolverse directamente.

## Caso base

```java
if (palabra.length() <= 1) {
    return palabra;
}
```

`length()` devuelve la cantidad de caracteres de la cadena. Si su longitud es `0` o `1`, la cadena ya está invertida: `""` sigue siendo `""` y `"a"` sigue siendo `"a"`.

Devolver en este punto detiene la recursión. Es esencial revisar esta condición antes de tomar subcadenas o caracteres: sin ella, el método seguiría solicitando llamadas para cadenas cada vez más cortas y podría intentar acceder a un índice que no existe.

## Pila de llamadas para `"hola"`

Cada llamada conserva su propia variable `palabra` y queda esperando el resultado de la llamada recursiva. En el descenso, las llamadas se apilan así; la más reciente está al final:

```text
┌──────────────────────────────────────────────┐
│ invertirPalabra("hola") espera el resultado  │
│   de invertirPalabra("ola") y luego agrega h │
├──────────────────────────────────────────────┤
│ invertirPalabra("ola") espera el resultado   │
│   de invertirPalabra("la") y luego agrega o   │
├──────────────────────────────────────────────┤
│ invertirPalabra("la") espera el resultado    │
│   de invertirPalabra("a") y luego agrega l   │
├──────────────────────────────────────────────┤
│ invertirPalabra("a") alcanza el caso base    │
└──────────────────────────────────────────────┘
```

La llamada con `"a"` devuelve `"a"`. A partir de ese momento, la pila se resuelve en orden inverso al que se formó:

```text
invertirPalabra("a")    devuelve "a"       <- caso base
invertirPalabra("la")   recibe "a"; agrega 'l' -> "al"
invertirPalabra("ola")  recibe "al"; agrega 'o' -> "alo"
invertirPalabra("hola") recibe "alo"; agrega 'h' -> "aloh"
```

Por eso el resultado no se forma al bajar por las llamadas. Se reconstruye al volver de ellas: cada llamada agrega al final el carácter que había guardado mientras esperaba.

## Relación con `main`

El método `main` asigna las tres palabras a variables y llama a `invertirPalabra` para cada una. `System.out.println` muestra la entrada y el resultado devuelto. Por ejemplo, la llamada con `segundaPalabra`, cuyo valor es `"hola"`, imprime:

```text
invertirPalabra("hola") = aloh
```

## Errores comunes al manipular `String`

- **Acceder a un índice inválido:** `charAt(0)` falla con `StringIndexOutOfBoundsException` si se aplica a una cadena vacía. También se obtiene esa excepción si se pide un índice fuera del rango válido, que va de `0` a `length() - 1`. En esta implementación, el caso base evita ese acceso: cuando la cadena está vacía o tiene un carácter, el método retorna antes de ejecutar la expresión recursiva.
- **Usar un índice incorrecto en `substring`:** `substring(1)` es válido cuando la cadena tiene al menos un carácter. La condición del método garantiza que la llamada recursiva solo se hace cuando la longitud es mayor que `1`. Cambiar el índice sin revisar los límites puede causar `StringIndexOutOfBoundsException` o dejar de reducir la cadena como se espera.
- **No reducir la entrada:** si la llamada recursiva recibe la misma cadena, nunca alcanzará el caso base y las llamadas continuarán acumulándose hasta producir `StackOverflowError`.
- **Pasar `null`:** esta implementación supone que `palabra` no es `null`. Al evaluar `palabra.length()` sobre `null`, Java lanza `NullPointerException`; no es una `StringIndexOutOfBoundsException`. Para este ejercicio, se debe llamar al método con una cadena válida, incluso si está vacía.

## Resumen

El caso base devuelve cadenas de longitud cero o uno. Para las demás, `substring(1)` separa el resto y la llamada recursiva lo invierte; `charAt(0)` recupera el primer carácter para colocarlo al final. La pila guarda cada carácter pendiente y, al retornar desde el caso base, los agrega en orden inverso. Con `"hola"`, ese proceso produce `"aloh"`.
