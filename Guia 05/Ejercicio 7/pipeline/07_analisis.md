# Analisis tecnico: invertir un vector

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara dos implementaciones Java para invertir el orden de los elementos de un vector: una que construya un vector auxiliar y otra que invierta el arreglo recibido en el mismo sitio. Tambien debera comparar sus ventajas, desventajas y complejidades temporal y espacial.

## Supuestos adoptados

- El vector se representara como un arreglo Java `int[]`.
- Invertir significa que el ultimo elemento pasa a la primera posicion, el penultimo a la segunda, y asi sucesivamente; no significa ordenar los valores.
- Una referencia `null` se rechazara con `IllegalArgumentException` en ambos metodos.
- Un arreglo vacio o de un elemento es valido y permanece igual despues de invertirlo.
- La version auxiliar devolvera un nuevo arreglo invertido y no modificara el original.
- La version in situ modificara el arreglo recibido mediante intercambios y devolvera `void`, dejando claro que la entrada cambia.
- `main` usara arreglos fijos y demostrara ambas versiones. Para que los resultados sean comparables, cada version recibira una copia independiente del vector inicial.
- No se solicitara entrada por consola ni se creara una suite de pruebas automatizadas.

## Estrategias tecnicas

### Version con vector auxiliar

Validar la referencia y crear un arreglo de la misma longitud. Recorrer el arreglo original desde el final hacia el inicio, copiando cada valor a la siguiente posicion del arreglo auxiliar. Al terminar, devolver el arreglo auxiliar. La entrada original permanece intacta.

Una forma equivalente de indexar es tomar el elemento `numeros[indice]` y escribirlo en `invertido[n - 1 - indice]` recorriendo el original de principio a fin. Se debe elegir una de estas formas y explicar la correspondencia entre origen y destino.

### Version in situ

Validar la referencia y usar dos indices: uno al inicio y otro al final. Mientras el indice izquierdo sea menor que el derecho, intercambiar ambos elementos, avanzar el indice izquierdo y retroceder el derecho. Al cruzarse o encontrarse, el vector esta invertido.

Cada intercambio coloca dos elementos en su posicion final. Un elemento central de un arreglo con longitud impar no se mueve.

## Complejidad esperada

Sea `n` la longitud del arreglo:

| Version | Tiempo | Espacio auxiliar | Efecto sobre la entrada |
| --- | --- | --- | --- |
| Con vector auxiliar | `O(n)` | `O(n)` | Conserva el arreglo original y devuelve otro. |
| In situ | `O(n)` | `O(1)` | Modifica el arreglo recibido; realiza hasta `floor(n / 2)` intercambios. |

Ambas versiones necesitan recorrer o procesar una cantidad proporcional a `n`, por lo que su tiempo es lineal. La diferencia principal es el espacio adicional y si la entrada se conserva. Para longitud cero o uno, no hacen falta asignaciones/cambios por iteracion; la version auxiliar aun crea el arreglo resultado de longitud cero o uno.

El arreglo que `main` puede copiar para ejecutar una estrategia no debe contabilizarse como espacio auxiliar del metodo al comparar las estrategias: es preparacion de la demostracion y debe mencionarse por separado si se analiza el programa completo.

## Flujo esperado de la demostracion

1. Definir un arreglo original con varios valores en orden reconocible, por ejemplo `{1, 2, 3, 4, 5}`.
2. Mostrar el arreglo original.
3. Ejecutar la version auxiliar sobre el original y mostrar el nuevo arreglo invertido; verificar que el original sigue igual.
4. Clonar el vector original y ejecutar la version in situ sobre la copia; mostrar que esa copia quedo invertida.
5. Incluir al menos un ejemplo de longitud par o impar, y explicar el caso vacio o unitario en la explicacion.

La demostracion no debe presentarse como una medicion de rendimiento. Su objetivo es mostrar la diferencia entre preservar la entrada y mutarla.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique la diferencia entre devolver un nuevo vector invertido y modificar el arreglo recibido.
2. Proporcione codigo Java comentado con dos metodos reutilizables y un `main` que compare ambas estrategias con entradas independientes.
3. Valide referencias `null` con `IllegalArgumentException` y maneje arreglos vacios y unitarios sin errores.
4. Aclare que la version in situ usa dos indices y swaps, mientras que la auxiliar copia elementos en orden inverso.
5. Compare ventajas, desventajas, tiempo `O(n)` para ambas, espacio `O(n)` para la auxiliar y `O(1)` para la in situ.
6. Aclare que invertir cambia el orden, no ordena los valores, y que el efecto de mutacion de la version in situ es intencional.

No se solicita lectura por consola, ordenar el arreglo, devolver una copia desde la version in situ ni crear pruebas automatizadas.

## Criterios de aceptacion

- Ambas implementaciones producen el orden inverso correcto para arreglos de longitudes pares e impares.
- La version auxiliar devuelve un arreglo nuevo y deja intacta la entrada.
- La version in situ modifica el mismo arreglo mediante intercambios y no crea otro arreglo proporcional a la entrada.
- Ambas validan `null`; arreglos vacios y unitarios se manejan correctamente.
- `main` usa entradas independientes para evitar que una estrategia afecte la demostracion de la otra.
- Se comparan con precision las complejidades: tiempo `O(n)` en ambas, espacio auxiliar `O(n)` en la version auxiliar y `O(1)` en la in situ.
- La explicacion distingue invertir de ordenar y describe la diferencia de efectos secundarios.
