# Analisis tecnico: detectar elementos duplicados

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara dos implementaciones Java que determinen si un vector contiene valores duplicados y comparen sus complejidades temporales y espaciales: una solucion con ciclos anidados y otra que utilice `HashSet`.

## Supuestos adoptados

- El vector se representara como un arreglo `int[]`; se permiten valores positivos, negativos y repetidos.
- La pregunta que resolvera cada implementacion sera si existe al menos un duplicado. Cada metodo devolvera `boolean`: `true` si encuentra uno y `false` si no.
- No se solicita enumerar todos los valores repetidos ni contar cuantas veces aparece cada uno.
- La referencia `null` se rechazara con `IllegalArgumentException`. Un arreglo vacio o de un elemento no contiene duplicados y devolvera `false`.
- Las dos implementaciones recibiran el mismo vector, no lo modificaran y estaran disponibles como metodos reutilizables.
- `main` demostrara ambos metodos con un vector con duplicados y otro sin duplicados, definidos en el codigo. No se solicitara entrada por consola.
- Cada solucion puede terminar tan pronto como encuentre un duplicado, ya que la respuesta requerida es solo su existencia.

## Estrategias tecnicas

### Solucion 1: dos ciclos anidados

Recorrer cada posicion `i` y compararla con las posiciones posteriores, desde `j = i + 1` hasta el final. Si `numeros[i] == numeros[j]`, devolver `true` inmediatamente. Si se agotan todas las parejas sin coincidencias, devolver `false`.

Iniciar `j` en `i + 1` evita comparar un elemento consigo mismo y evita revisar dos veces cada pareja. No se requiere ordenar el vector ni crear estructuras auxiliares.

### Solucion 2: `HashSet`

Crear un `HashSet<Integer>` vacio y recorrer el arreglo una sola vez. Para cada valor, intentar agregarlo al conjunto. El metodo `add` devuelve `false` si ese valor ya estaba presente; en tal caso, devolver `true`. Si se termina el recorrido sin repetidos, devolver `false`.

El conjunto conserva los valores vistos y ofrece operaciones de insercion y consulta con costo promedio constante. No hace falta consultar con `contains` y luego insertar: `add` realiza ambas operaciones de manera efectiva en una sola llamada.

Ambos metodos validaran `null` antes del procesamiento. La version con ciclos anidados no debe modificar el arreglo; la version con `HashSet` modifica unicamente su estructura auxiliar.

## Complejidad esperada

Sea `n` la longitud del arreglo:

| Estrategia | Tiempo promedio/esperado | Tiempo peor caso | Espacio auxiliar |
| --- | --- | --- | --- |
| Ciclos anidados | `O(n^2)` | `O(n^2)` | `O(1)` |
| `HashSet` | `O(n)` esperado, suponiendo operaciones de conjunto promedio `O(1)` | `O(n^2)` en el limite teorico si muchas claves colisionan; implementaciones modernas de Java pueden mitigar ciertos casos mediante buckets arbolados | `O(n)` |

En la solucion cuadratica, si no hay duplicados se comparan todas las parejas: `n(n - 1) / 2`. El mejor caso es `O(1)` si la primera pareja comparada ya coincide.

En la solucion con `HashSet`, el mejor caso tambien es `O(1)` si el segundo elemento ya duplica al primero. En el caso de ausencia de duplicados se procesan todos los elementos, con tiempo esperado `O(n)` y almacenamiento de hasta `n` valores.

Para un vector vacio o de un elemento, ambas soluciones devuelven `false`; las operaciones de validacion y verificacion de longitud son constantes.

## Flujo esperado de la demostracion

1. Crear un vector con al menos un valor repetido y otro sin valores repetidos.
2. Invocar ambas implementaciones con cada vector.
3. Mostrar resultados legibles que permitan comparar ambas respuestas y comprobar que coinciden.
4. Mantener la comparacion de complejidad en la explicacion, sin presentar la ejecucion de `main` como una medicion de rendimiento.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Presente brevemente las dos estrategias y el contrato de retorno `boolean`.
2. Incluya codigo Java comentado con una clase, los dos metodos reutilizables y un `main` de demostracion con datos fijos.
3. Valide `null`, trate correctamente los arreglos vacios y de un solo elemento, y retorne temprano al detectar una repeticion.
4. Compare tiempo y espacio de ambos enfoques, dejando claro que `HashSet` tiene `O(n)` esperado y usa `O(n)` espacio, mientras que los ciclos anidados usan `O(1)` espacio y `O(n^2)` tiempo.
5. Explique por que el doble ciclo compara solo parejas distintas y por que `HashSet.add` permite detectar si el valor ya habia aparecido.
6. Indique que el arreglo original no debe ordenarse ni modificarse.

No se solicita entrada por consola, listar todos los duplicados, devolver indices, contar frecuencias, ordenar los datos ni crear pruebas automatizadas.

## Criterios de aceptacion

- Las dos implementaciones devuelven el mismo resultado booleano para cualquier vector valido.
- La version con ciclos anidados compara cada pareja distinta una sola vez y no compara un elemento consigo mismo.
- La version con `HashSet` detecta una repeticion cuando `add` devuelve `false`, sin una consulta previa redundante a `contains`.
- Ambas implementaciones validan `null`, devuelven `false` para arreglos vacios o de un elemento, y no modifican el vector.
- `main` muestra casos con y sin duplicados para las dos implementaciones.
- La comparacion de complejidad distingue `O(n^2)`/`O(1)` para ciclos anidados y `O(n)` esperado/`O(n)` para `HashSet`, explicando el supuesto de hashing.
- El codigo y la explicacion se limitan a detectar existencia; no enumeran duplicados ni agregan interaccion por consola.
