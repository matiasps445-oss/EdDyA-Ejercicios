# Explicación de `OrdenamientoNombres`

El programa ordena el arreglo `{"Lucia", "Ana", "Pedro", "Juan"}` en orden lexicográfico ascendente mediante Selection Sort. Primero imprime el arreglo original. Después, el método `ordenarSeleccion` modifica el mismo arreglo: busca el nombre menor de la zona pendiente y, al terminar cada búsqueda, lo intercambia con el nombre que ocupa el inicio de esa zona.

## Selection Sort aplicado a estos nombres

El ciclo externo usa `indiceActual` para marcar dónde comienza la parte todavía no ordenada. En cada pasada, `indiceMenor` empieza en esa misma posición. El ciclo interno examina los nombres restantes y actualiza `indiceMenor` cuando encuentra uno que debe ir antes. Solo después de recorrer toda la zona pendiente se realiza el intercambio.

| Pasada | Búsqueda del menor | Intercambio al final de la pasada | Arreglo resultante |
|---|---|---|---|
| 1 | Se parte de `Lucia`. `Ana` es menor; `Pedro` y `Juan` no son menores que `Ana`. | `Ana` con `Lucia` | `[Ana, Lucia, Pedro, Juan]` |
| 2 | Se parte de `Lucia`. `Pedro` no es menor; `Juan` sí lo es. | `Juan` con `Lucia` | `[Ana, Juan, Pedro, Lucia]` |
| 3 | Se parte de `Pedro`. `Lucia` es menor. | `Lucia` con `Pedro` | `[Ana, Juan, Lucia, Pedro]` |

La primera posición queda fijada después de la primera pasada, por lo que las siguientes búsquedas comienzan una posición más a la derecha. La última posición restante queda ordenada automáticamente; no hace falta una pasada adicional.

## Cómo compara `String.compareTo()`

Con números primitivos, una condición como `a < b` permite decidir cuál es menor. Los objetos `String` no admiten esos operadores relacionales; el método `compareTo()` expresa el orden lexicográfico entre dos cadenas. La condición del programa es:

```java
nombres[candidato].compareTo(nombres[indiceMenor]) < 0
```

Se lee así: «¿el nombre candidato aparece antes que el nombre que hasta ahora se considera menor?». Si la respuesta es sí, se guarda el índice del candidato como el nuevo `indiceMenor`.

La comparación es lexicográfica: Java examina los caracteres en orden desde el primero. El primer par de caracteres diferentes determina cuál cadena va antes según sus valores de carácter. Si una cadena es prefijo de la otra, la más corta va primero. El contrato de `compareTo()` garantiza el signo del resultado:

- **Negativo:** la cadena que invoca `compareTo()` va antes que la cadena argumento.
- **Cero:** las cadenas tienen el mismo contenido en el orden comparado.
- **Positivo:** la cadena que invoca `compareTo()` va después.

El algoritmo solo necesita comprobar si el resultado es menor que cero. No debe depender de que el método devuelva exactamente `-1` o `1`; cualquier valor negativo o positivo cumple la misma función.

### Comparaciones concretas

En este ejemplo, todos los nombres empiezan con mayúscula. Las iniciales tienen estos valores ASCII: `A` = 65, `J` = 74, `L` = 76 y `P` = 80. Por eso las comparaciones iniciales bastan para decidir el orden entre nombres distintos:

1. **Primera pasada:** `"Ana".compareTo("Lucia")` es negativo porque `A` precede a `L`; `indiceMenor` pasa a apuntar a `Ana`. `Pedro` y `Juan` dan resultados positivos frente a `Ana`, así que no reemplazan al menor. El intercambio coloca `Ana` al inicio.
2. **Segunda pasada:** `"Pedro".compareTo("Lucia")` es positivo, pero `"Juan".compareTo("Lucia")` es negativo porque `J` precede a `L`. El menor de la zona es `Juan`, que se intercambia con `Lucia`.
3. **Tercera pasada:** `"Lucia".compareTo("Pedro")` es negativo porque `L` precede a `P`. Se intercambian y queda el arreglo completamente ordenado.

La salida final es:

```text
Arreglo original: [Lucia, Ana, Pedro, Juan]
Arreglo ordenado: [Ana, Juan, Lucia, Pedro]
```

## Precisión sobre “alfabético” y ASCII

Para estas iniciales ASCII en mayúscula, el orden de `compareTo()` coincide con el esperado. Técnicamente, Java compara lexicográficamente los valores de caracteres UTF-16, no aplica un alfabeto regional ni reglas de idioma. Además, `compareTo()` distingue mayúsculas de minúsculas: por ejemplo, una cadena que empieza con `A` y otra que empieza con `a` no se consideran equivalentes y pueden quedar en un orden distinto al de un diccionario que ignore el caso.

Si el requisito fuera ignorar mayúsculas y minúsculas, `compareToIgnoreCase()` sería una opción para una comparación sencilla sin distinción de caso. Si se necesitara un orden lingüístico correcto para una región —por ejemplo, reglas específicas para tildes o letras propias de un idioma— convendría usar `Collator` con el `Locale` apropiado.

## Errores comunes

- **Usar `==` para comparar el contenido:** en Java, `==` entre dos referencias `String` comprueba si apuntan al mismo objeto, no si contienen los mismos caracteres. Para ordenar se usa `compareTo()`; para igualdad exacta de contenido se usa `equals()`.
- **Pensar que `compareTo()` solo devuelve `-1`, `0` o `1`:** el contrato relevante es el signo. Se debe comprobar `< 0`, `== 0` o `> 0`, no depender de un valor exacto distinto de cero.
- **Invertir el orden de los operandos:** `candidato.compareTo(menor) < 0` significa que el candidato precede al menor actual. Cambiar los operandos invierte el sentido de la decisión.
- **Suponer que `compareTo()` ignora mayúsculas:** no lo hace. Las cadenas se comparan distinguiendo el caso.
- **Confundir orden lexicográfico con orden numérico o lingüístico:** la comparación se decide por caracteres, no por el significado de los nombres ni necesariamente por reglas de diccionario regionales.
- **Intercambiar cada vez que aparece un candidato menor:** Selection Sort conserva primero su índice, termina la búsqueda y realiza como máximo un intercambio al final de la pasada.
- **Pasar `null`:** `compareTo()` no puede comparar una referencia nula; el ejercicio asume que todos los nombres del arreglo son cadenas no nulas.

En síntesis, Selection Sort organiza el arreglo fijando un menor por pasada. `compareTo()` hace posible decidir el orden entre textos y la condición `< 0` permite actualizar el índice del menor sin convertir las cadenas ni compararlas con operadores relacionales.