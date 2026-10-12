# Analisis tecnico: contar ocurrencias de un valor en un vector

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara la implementacion en Java de un algoritmo que cuente cuantas veces aparece un valor objetivo dentro de un vector y que justifique por que debe recorrerlo por completo, junto con el analisis de complejidad.

## Supuestos adoptados

- El vector se representara como un arreglo Java `int[]`, y el valor buscado tambien sera `int`.
- Se implementara un metodo reutilizable, por ejemplo `contarOcurrencias(int[] numeros, int objetivo)`, que devolvera un `int` con el total de coincidencias.
- Una referencia `null` se rechazara con `IllegalArgumentException`.
- Un arreglo vacio es valido y devuelve `0`; un arreglo de un elemento devuelve `1` si coincide con el objetivo y `0` si no.
- Se permiten valores negativos, positivos y repetidos; el orden del vector no tiene relevancia.
- `main` usara un arreglo y un objetivo definidos en el codigo, sin solicitar datos por consola. La demostracion debe incluir un objetivo repetido y uno ausente para mostrar resultados no nulos y cero.
- El metodo no modificara ni ordenara el arreglo.

## Estrategia tecnica

Validar que la referencia no sea `null`, inicializar un contador en cero y recorrer cada elemento del arreglo una sola vez. Incrementar el contador cuando el elemento actual sea igual al objetivo. Al finalizar el recorrido, devolver el contador.

El recorrido completo es necesario porque el problema pide el numero total de apariciones, no solo saber si existe una coincidencia. Una coincidencia temprana no permite concluir que no haya mas coincidencias despues. Por ello no se debe retornar al encontrar el primer valor igual.

El orden del arreglo no permite acortar el recorrido: no se exige que este ordenado, y aun si lo estuviera, contar las apariciones de un valor sin informacion adicional sobre la distribucion requeriria revisar todas las posiciones (o definir otra estrategia, como dos busquedas binarias, solo si se garantiza orden y se cambia el enfoque).

## Complejidad esperada

Sea `n` la longitud del arreglo:

- Tiempo: `O(n)` en todos los casos, ya que cada elemento debe inspeccionarse exactamente una vez para garantizar el conteo total.
- Espacio auxiliar: `O(1)`, porque basta un contador y el indice del recorrido.
- En un arreglo vacio, el ciclo no realiza iteraciones y devuelve `0` en tiempo constante.

El contador puede ser `int`: el numero de coincidencias no puede superar la cantidad de elementos del propio arreglo, cuyo tamano Java expone como `int`.

## Flujo esperado de la demostracion

1. Crear un vector fijo que contenga varias apariciones del mismo valor.
2. Invocar el metodo para ese objetivo e imprimir el total.
3. Invocar el metodo con un valor que no aparezca e imprimir `0`.
4. Explicar que ambos casos recorren el arreglo completo, incluso si las coincidencias aparecen al inicio.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique por que contar ocurrencias exige revisar todas las posiciones y por que no se debe finalizar al hallar la primera coincidencia.
2. Presente codigo Java comentado con un metodo reutilizable para `int[]` y un `main` de demostracion con datos fijos.
3. Valide `null`, devuelva `0` para el arreglo vacio y maneje correctamente objetivos ausentes, repetidos y arreglos de un elemento.
4. Indique complejidad temporal `O(n)` y espacial auxiliar `O(1)`, justificando ambas.
5. Aclare que el algoritmo no modifica ni ordena el vector.

No se solicita entrada por consola, devolver indices, usar `HashMap`, listar posiciones coincidentes ni crear una suite de pruebas automatizadas.

## Criterios de aceptacion

- El metodo devuelve el total de coincidencias, no una posicion ni un valor booleano.
- El contador comienza en cero, aumenta una vez por cada igualdad y el recorrido cubre todos los elementos.
- No existe retorno temprano al encontrar una coincidencia.
- Se rechaza `null` con `IllegalArgumentException`; los arreglos vacios devuelven `0`.
- `main` demuestra al menos un valor presente varias veces y un valor ausente.
- La explicacion justifica el recorrido completo y declara tiempo `O(n)` y espacio auxiliar `O(1)`.
- El arreglo original permanece sin cambios.
