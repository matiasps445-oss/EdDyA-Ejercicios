# Plan técnico: ordenamiento de nombres con Selection Sort

## Objetivo

Implementar en Java un programa que ordene alfabéticamente un arreglo de nombres mediante Selection Sort y muestre el arreglo original y el arreglo final ordenado.

## Diseño

- **Arreglo de demostración:** `String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};`.
- **Método sugerido:** `ordenarSeleccion(String[] nombres)`, que ordena el arreglo recibido en el mismo lugar.
- **Comparación:** usar `String.compareTo()`; no aplicar `<` ni `>` a objetos `String`.
- **Salida:** imprimir el arreglo original antes de ordenar y el contenido final después de ordenar, por ejemplo con `Arrays.toString(...)`.
- **Suposición para este ejercicio:** los nombres son cadenas no nulas y comienzan con mayúscula tal como aparecen en el ejemplo.

## Estrategia de Selection Sort

1. Recorrer con el ciclo externo las posiciones desde el inicio hasta la penúltima.
2. Al comenzar cada pasada, considerar que el menor nombre de la zona no ordenada está en la posición actual.
3. Usar el ciclo interno para recorrer el resto de esa zona. Comparar cada nombre candidato con el nombre que actualmente se considera menor mediante:

   ```java
   nombres[candidato].compareTo(nombres[indiceMenor]) < 0
   ```

4. `compareTo()` compara las cadenas en orden lexicográfico y devuelve un entero: un valor negativo si la cadena receptora precede a la cadena argumento, cero si son iguales y un valor positivo si la precede en sentido inverso. Si el resultado es negativo, actualizar `indiceMenor` al candidato.
5. Cuando termina la búsqueda, intercambiar el nombre menor encontrado con el elemento de la posición actual, si no estaba ya allí.
6. Al terminar todas las pasadas, el arreglo queda ordenado ascendentemente.

## Consideraciones sobre el orden de `String`

`String.compareTo()` usa el orden lexicográfico de los valores de caracteres de Java; para las letras ASCII del ejemplo, ese orden coincide con el orden esperado por sus iniciales. La comparación distingue mayúsculas de minúsculas y no aplica reglas regionales de idioma. No se requiere normalizar cadenas ni utilizar un `Collator`, dado que todos los nombres de entrada tienen el mismo formato y empiezan con mayúscula.

## Seguimiento esperado

| Pasada | Menor localizado en la zona pendiente | Estado tras el intercambio |
|---|---|---|
| 1 | `Ana` | `[Ana, Lucia, Pedro, Juan]` |
| 2 | `Juan` | `[Ana, Juan, Pedro, Lucia]` |
| 3 | `Lucia` | `[Ana, Juan, Lucia, Pedro]` |

En la primera pasada, por ejemplo, se empieza considerando `Lucia` como el menor. `Ana`.compareTo(`Lucia`) da un valor negativo, por lo que el menor pasa a ser `Ana`. Se examinan los siguientes nombres de la misma manera; al final de la búsqueda, `Ana` se intercambia con el elemento de la primera posición.

## Comentarios requeridos en el código

- Explicar que Selection Sort busca el menor nombre de la parte aún no ordenada.
- Junto a la condición con `compareTo()`, indicar que un resultado menor que cero significa que el candidato debe aparecer antes del menor actual.
- Explicar que el intercambio se realiza después de completar la búsqueda, no cada vez que se encuentra un candidato menor.

## Criterios de aceptación

- Se utiliza Selection Sort sobre el arreglo de cadenas indicado.
- El programa muestra el arreglo original antes de modificarlo.
- La comparación entre nombres se realiza con `compareTo()` y se elige un candidato cuando el resultado es menor que cero.
- Se intercambia el menor encontrado con la primera posición de la zona pendiente al final de cada pasada.
- La salida final es `[Ana, Juan, Lucia, Pedro]`.
- Los comentarios explican el papel de `compareTo()` en la búsqueda del menor.