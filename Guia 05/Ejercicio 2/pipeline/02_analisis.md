# Analisis tecnico: buscar un elemento en un vector desordenado

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara la implementacion en Java de un algoritmo que busque un elemento en un vector desordenado, informe el indice de la primera coincidencia y cuente las posiciones inspeccionadas. Este documento especifica el plan; no contiene la implementacion del algoritmo.

## Requisitos confirmados

- La estructura de entrada sera un arreglo Java `int[]`.
- El programa de demostracion solicitara por consola el tamano, los elementos del arreglo y el valor buscado.
- Ante una entrada que no sea un entero, el programa mostrara un mensaje y volvera a solicitar ese dato.
- El tamano solicitado debe ser positivo. El programa de consola debe volver a pedirlo si es cero o negativo.
- El metodo de busqueda reutilizable debe rechazar una referencia `null` o un arreglo vacio con `IllegalArgumentException`.
- Si el valor aparece varias veces, se usara la primera coincidencia desde el inicio del arreglo.
- Los indices seguiran la convencion de Java, comenzando en cero.
- El resultado debe incluir el indice hallado y la cantidad de elementos inspeccionados. Si el valor no existe, el indice sera `-1` y se inspeccionara el arreglo completo.
- La cantidad inspeccionada incluye el elemento coincidente: cada posicion se cuenta cuando su valor se compara con el objetivo.
- El analisis debe explicar mejor caso, peor caso y caso promedio, indicando explicitamente los supuestos usados para el promedio.
- El prompt debe pedir una justificacion de por que una busqueda secuencial es adecuada y por que no corresponde aplicar busqueda binaria a un arreglo desordenado.

## Estrategia tecnica

Implementar una busqueda secuencial de izquierda a derecha. Inicializar el contador en cero; por cada posicion, incrementar el contador antes o al momento de comparar el valor con el objetivo. Al encontrar una coincidencia, devolver inmediatamente su indice junto con el contador. Si se llega al final, devolver indice `-1` y el total inspeccionado.

Para devolver ambos datos de forma clara, se recomienda un tipo de resultado pequeno, por ejemplo `ResultadoBusqueda`, con campos para `indice` y `posicionesRecorridas`. El metodo debe validar la referencia y la longitud del arreglo antes de comenzar. El programa principal debe obtener los datos por consola y manejar entradas no enteras con reintentos y mensajes comprensibles.

La busqueda secuencial es apropiada porque el arreglo no esta ordenado: no hay informacion que permita descartar mitades del arreglo de forma segura. En el peor caso hay que inspeccionar todos los elementos. La busqueda binaria requeriria ordenar previamente los datos o una garantia de orden que el enunciado no ofrece.

## Complejidad y supuestos

Sea `n` la longitud del arreglo:

- Mejor caso: `O(1)`, cuando el objetivo esta en la primera posicion.
- Peor caso: `O(n)`, cuando esta en la ultima posicion o no aparece.
- Caso promedio: suponiendo que el objetivo esta presente y cada posicion tiene igual probabilidad de contener su primera coincidencia, se inspeccionan en promedio `(n + 1) / 2` posiciones; asintoticamente es `O(n)`. Si el objetivo no aparece, se inspeccionan exactamente `n` posiciones. Sin una probabilidad de exito y una distribucion de posiciones, no se debe presentar un unico promedio que mezcle ambos escenarios.
- Espacio auxiliar del algoritmo: `O(1)`. El arreglo leido por consola ocupa `O(n)` espacio, pero es almacenamiento de entrada, no memoria auxiliar de la busqueda.

## Flujo esperado del programa

1. Solicitar un tamano positivo y reintentar ante enteros no positivos o entradas no enteras.
2. Leer los elementos del arreglo, reintentando cualquier dato no entero.
3. Solicitar el objetivo y reintentar ante una entrada no entera.
4. Ejecutar el metodo de busqueda secuencial.
5. Informar si hubo coincidencia; si la hubo, mostrar el indice base cero. En ambos casos, informar las posiciones inspeccionadas.

## Alcance del prompt para OpenCode

El prompt debe solicitar una respuesta organizada de modo que:

1. Explique la busqueda secuencial y su adecuacion a un arreglo desordenado.
2. Describa la validacion de `null` y arreglo vacio, y el tratamiento de datos invalidos por consola.
3. Presente las complejidades y los supuestos del caso promedio.
4. Proporcione codigo Java comentado, con un metodo reutilizable, un resultado que contenga indice y conteo, y un `main` interactivo.
5. Explique por que la busqueda binaria no es valida sin ordenamiento.

No se solicita ordenar el arreglo, buscar todas las coincidencias ni crear una suite de pruebas automatizadas.

## Criterios de aceptacion

- La busqueda inspecciona las posiciones de izquierda a derecha y se detiene en la primera coincidencia.
- El contador coincide con el numero de elementos comparados: cuenta la coincidencia y, si no hay coincidencia, vale `n`.
- El resultado usa indice base cero; ante ausencia devuelve `-1`.
- Se rechazan `null` y arreglo vacio en el metodo; el flujo de consola valida un tamano positivo y reintenta entradas no enteras.
- Se informan el resultado y la cantidad de posiciones inspeccionadas.
- La explicacion cubre mejor caso `O(1)`, peor caso `O(n)` y promedio bajo el supuesto uniforme declarado, sin ocultar que la ausencia siempre requiere `n` inspecciones.
- La respuesta justifica que el vector desordenado obliga a usar busqueda secuencial y explica por que no se aplica busqueda binaria.
