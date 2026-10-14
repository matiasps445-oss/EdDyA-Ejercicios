# Explicación de `SistemaRanking`

El programa construye un ranking de jugadores por puntaje. En vez de ordenar valores `int` sueltos, ordena referencias a objetos `Jugador`; cada objeto reúne un nombre y un puntaje. MergeSort compara el puntaje de esos objetos y reorganiza el arreglo para que el puntaje mayor quede primero.

## Cómo se representan y comparan los jugadores

`Jugador` está declarada como clase pública estática dentro de `SistemaRanking`. Tiene los campos `nombre` y `puntaje` marcados como `private final`: se inicializan en el constructor y no se exponen para modificación directa. Los métodos `getNombre()` y `getPuntaje()` permiten consultar sus valores. `toString()` proporciona la forma legible que `System.out.println` muestra para cada jugador, por ejemplo `Lucia (1500)`.

La comparación ocurre en `fusionar`, no por el nombre ni por la identidad del objeto:

```java
jugadores[indiceIzquierdo].getPuntaje()
        >= jugadores[indiceDerecho].getPuntaje()
```

Así, la decisión usa los datos del jugador para determinar su posición en el ranking. El nombre solo se utiliza para identificarlo al imprimir.

## Cómo funciona MergeSort en esta implementación

1. `ordenarRanking` crea un arreglo auxiliar con `jugadores.clone()` y llama al método recursivo para ordenar el rango completo.
2. `mergeSort` divide cada rango por su índice medio y llama primero a la mitad izquierda y luego a la derecha.
3. Cuando una mitad tiene un solo elemento, alcanza el caso base y la llamada retorna.
4. `fusionar` combina los rangos ya ordenados comparando los puntajes actuales de cada mitad. Copia primero al auxiliar el jugador que debe ir antes y avanza el índice correspondiente.
5. Cuando una mitad se agota, copia los elementos restantes de la otra. Finalmente, `System.arraycopy` devuelve el rango combinado al arreglo de jugadores.
6. `main` recorre el arreglo ordenado y `toString()` presenta cada entrada del ranking.

Para la entrada `[Ana (1200), Pedro (900), Lucia (1500)]`, la división separa `[Ana, Pedro]` de `[Lucia]`. La primera fusión deja `[Ana (1200), Pedro (900)]` porque 1200 es mayor que 900. La fusión final compara 1200 con 1500 y coloca primero a Lucia; luego agrega Ana y Pedro. El resultado es:

```text
Lucia (1500)
Ana (1200)
Pedro (900)
```

## El cambio que produce el orden descendente

En una fusión ascendente se elegiría primero el elemento izquierdo cuando su puntaje fuera menor o igual al derecho. Aquí la condición usa `>=`:

```java
puntajeIzquierdo >= puntajeDerecho
```

Esto hace que el jugador con el puntaje mayor se copie antes al resultado. No se invierte el arreglo una vez terminado: se cambia directamente la decisión en cada comparación de la fusión.

El caso de igualdad también es intencional. Si ambos puntajes son iguales, `>=` selecciona el jugador de la mitad izquierda. Como MergeSort divide manteniendo el orden original dentro de cada mitad, esta regla conserva el orden relativo de jugadores empatados y hace estable la ordenación.

## Por qué MergeSort es una elección sólida

- **Costo predecible:** MergeSort tiene tiempo `O(n log n)` en el mejor, promedio y peor caso. No cae en `O(n^2)` por una elección desafortunada de pivote, como puede pasar con Quicksort.
- **Estabilidad:** con la regla de empate descrita, los jugadores con igual puntaje mantienen su orden previo. En rankings esto puede ser útil cuando el orden de entrada representa una prioridad secundaria.
- **Adecuado para arreglos grandes:** escala mejor que Bubble, Selection o Insertion Sort en listas generales grandes, cuyas versiones simples tienen tiempo cuadrático en el peor caso.
- **Costo de memoria explícito:** utiliza un arreglo auxiliar `O(n)`, además de la pila recursiva `O(log n)` con divisiones equilibradas. Es un intercambio razonable para obtener estabilidad y tiempo predecible, pero puede importar en sistemas con memoria muy limitada.

MergeSort no es automáticamente la mejor opción para cualquier sistema profesional. En Java de producción conviene evaluar también `Arrays.sort` para arreglos de objetos, que usa una implementación estable optimizada de la biblioteca estándar, o `Arrays.parallelSort` para ciertos volúmenes y entornos. Para este ejercicio, MergeSort deja visible la estrategia, garantiza el comportamiento esperado y permite definir explícitamente la estabilidad del ranking.

## Errores comunes y valores nulos

- **Comparar los objetos directamente:** `Jugador` no tiene un orden natural por defecto. La implementación debe definir qué campo determina el ranking; aquí compara `getPuntaje()`. Si se usara un `Comparator`, también debería ordenar según ese campo.
- **Usar el sentido ascendente por error:** cambiar `>=` por `<=` dejaría primero los puntajes menores. Para ranking descendente, la fusión elige el mayor disponible.
- **Perder estabilidad en empates:** elegir siempre la mitad derecha en igualdad puede invertir el orden relativo de jugadores con el mismo puntaje. La condición `>=` elige la izquierda cuando empatan.
- **Pasar un arreglo `jugadores` nulo:** `jugadores.clone()` lanzaría `NullPointerException`. El código asume que se recibe un arreglo no nulo.
- **Incluir una referencia nula en el arreglo:** cuando `fusionar` intente llamar `getPuntaje()` sobre ese elemento, se producirá `NullPointerException`. El arreglo de ejemplo solo contiene instancias válidas.
- **Pasar un nombre nulo:** el constructor actual no valida `nombre`. La concatenación de cadenas de `toString()` representaría la referencia nula como el texto `null`; no causa por sí sola una excepción en esta implementación, pero produce un dato inválido para el dominio. Si otra operación invocara un método sobre `nombre`, sí podría aparecer `NullPointerException`.
- **Suponer que `puntaje` puede ser nulo:** el campo es de tipo primitivo `int`, por lo que no admite `null`. Si se cambiara a `Integer`, comparar tras auto-unboxing un valor nulo sí podría lanzar `NullPointerException`.
- **Calcular mal el rango medio o copiar datos fuera del rango:** puede omitir, repetir o sobrescribir jugadores. Esta implementación usa rangos inclusivos y calcula el medio como `inicio + (fin - inicio) / 2` para limitar el riesgo de desbordamiento de la suma.

En resumen, el ranking se ordena por puntaje porque `fusionar` consulta `getPuntaje()`; el operador `>=` coloca primero los puntajes altos y conserva la estabilidad en empates. MergeSort aporta tiempo `O(n log n)` predecible a cambio de memoria auxiliar, una propiedad útil al crecer la cantidad de jugadores.