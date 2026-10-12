# Analisis tecnico: buscar un elemento en un vector ordenado

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara codigo Java que implemente una busqueda binaria y explique por que es mas eficiente que la busqueda lineal cuando los datos estan ordenados. Tambien debera mostrar paso a paso como evolucionan las variables de busqueda.

## Supuestos adoptados

La consigna no especifica tipo de dato, orden, comportamiento ante duplicados, ni interaccion de consola. Para mantener una solucion didactica y acotada, se asume lo siguiente:

- El vector sera un arreglo Java `int[]` ordenado de forma ascendente.
- Se implementara un metodo reutilizable, por ejemplo `buscarBinaria(int[] numeros, int objetivo)`, que devolvera un indice base cero.
- Si el objetivo no existe, el metodo devolvera `-1`.
- Si hay valores repetidos, se permite devolver cualquier indice que contenga el objetivo; no se requiere localizar la primera ni la ultima aparicion.
- Una referencia `null` se rechazara con `IllegalArgumentException`. Un arreglo vacio es valido y devolvera `-1`.
- `main` usara un arreglo y un objetivo definidos en el codigo, sin solicitar datos por consola. Esto mantiene la demostracion enfocada en el algoritmo y permite presentar una traza reproducible.
- La traza didactica mostrara, en cada iteracion, los limites `inicio` y `fin`, el indice `medio`, el valor evaluado y la decision que actualiza uno de los limites.

## Estrategia tecnica

Implementar una busqueda binaria iterativa sobre un arreglo ordenado ascendentemente. Inicializar `inicio` en `0` y `fin` en `numeros.length - 1`. Mientras `inicio <= fin`:

1. Calcular el punto medio como `inicio + (fin - inicio) / 2`, evitando la posible suma desbordada de `(inicio + fin) / 2`.
2. Comparar `numeros[medio]` con el objetivo.
3. Si son iguales, devolver `medio`.
4. Si el valor del medio es menor que el objetivo, descartar la mitad izquierda, incluido `medio`, asignando `inicio = medio + 1`.
5. Si el valor del medio es mayor que el objetivo, descartar la mitad derecha, incluido `medio`, asignando `fin = medio - 1`.

Si los limites se cruzan, el objetivo no esta en el arreglo y se devuelve `-1`. La implementacion debe ser iterativa, para que el uso de memoria auxiliar sea constante y los limites puedan observarse claramente durante la explicacion.

El orden ascendente es una precondicion esencial: cada comparacion con el elemento medio permite descartar una mitad completa. En un vector sin ordenar, esa conclusion no es valida. La busqueda lineal necesita hasta `n` comparaciones, mientras que la binaria reduce aproximadamente a la mitad el rango restante en cada iteracion.

## Complejidad esperada

Sea `n` la longitud del arreglo:

- Mejor caso: `O(1)`, si el objetivo coincide con el primer punto medio evaluado.
- Caso promedio: `O(log n)` bajo la distribucion usual de objetivos dentro del arreglo.
- Peor caso: `O(log n)`, porque el intervalo se reduce aproximadamente a la mitad en cada iteracion.
- Espacio auxiliar: `O(1)` para la version iterativa.
- Como comparacion, la busqueda lineal es `O(n)` en promedio y peor caso. Para arreglos ordenados y suficientemente grandes, la busqueda binaria escala mejor.

Si el arreglo esta vacio, el bucle no se ejecuta y el resultado es `-1` en tiempo constante. El metodo no debe ordenar el arreglo: hacerlo cambiaria el dato recibido y agregaria un costo de ordenamiento que no forma parte de la busqueda solicitada.

## Ejemplo de traza para la explicacion

Usar el arreglo ascendente `{-8, -3, 0, 4, 9, 15, 22}` y buscar `9`:

| Iteracion | `inicio` | `fin` | `medio` | Valor en `medio` | Decision |
| --- | ---: | ---: | ---: | ---: | --- |
| 1 | 0 | 6 | 3 | 4 | `4 < 9`; descartar izquierda y mover `inicio` a 4. |
| 2 | 4 | 6 | 5 | 15 | `15 > 9`; descartar derecha y mover `fin` a 4. |
| 3 | 4 | 4 | 4 | 9 | Coincidencia; devolver el indice 4. |

La traza permite ver que no se recorren todos los elementos: en cada paso se descarta un rango que ya no puede contener el objetivo.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique la idea de busqueda binaria y la precondicion de que el arreglo este ordenado.
2. Proporcione codigo Java comentado con metodo reutilizable y un `main` demostrativo con datos fijos.
3. Valide `null`, acepte el arreglo vacio como busqueda sin resultado y devuelva `-1` cuando el objetivo no aparezca.
4. Solicite que el codigo o la explicacion muestre la evolucion de `inicio`, `fin` y `medio`, junto con el valor comparado y la decision en cada iteracion.
5. Explique por que es mas eficiente que la busqueda lineal en arreglos ordenados e indique complejidad temporal y espacial.
6. Justifique el calculo de `medio` con `inicio + (fin - inicio) / 2` y explique las condiciones de avance para evitar bucles infinitos.

No se requiere entrada por consola, ordenar los datos, devolver todas las posiciones duplicadas ni una suite de pruebas automatizadas.

## Criterios de aceptacion

- El metodo trabaja con un arreglo ascendente `int[]`, devuelve un indice base cero y devuelve `-1` ante ausencia.
- La condicion del bucle y las actualizaciones de limites cubren correctamente arreglos de longitud uno, objetivos en los extremos y objetivos ausentes sin bucles infinitos.
- El metodo rechaza `null`; el arreglo vacio termina correctamente con `-1`.
- El calculo del punto medio evita sumar directamente `inicio + fin`.
- La explicacion traza los valores de `inicio`, `fin` y `medio` en cada iteracion con un ejemplo concreto.
- Se indica tiempo `O(log n)` y espacio auxiliar `O(1)` para la version iterativa, y se compara de forma correcta con la busqueda lineal `O(n)`.
- Se explica que el orden ascendente es necesario y que el metodo no ordena ni modifica el arreglo.
