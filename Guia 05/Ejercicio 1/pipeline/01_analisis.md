# Analisis tecnico: encontrar el minimo de un vector

## Objetivo

Redactar un prompt para OpenCode que solicite la implementacion en Java de un algoritmo para encontrar el valor minimo de un vector de numeros enteros. Esta etapa define los requisitos y la estrategia; no implementa el algoritmo ni redacta aun el prompt final.

## Requisitos confirmados

- La solucion solicitada debe estar escrita en Java.
- Debe trabajar con un arreglo `int[]` y exponer un metodo reutilizable que devuelva solamente el minimo, no su indice.
- Debe incluir un `main` de demostracion que invoque el metodo usando un arreglo declarado en el codigo; no debe solicitar datos por consola.
- El metodo debe rechazar una referencia `null` y un arreglo vacio con `IllegalArgumentException`.
- El codigo que OpenCode genere debe estar comentado.
- La respuesta solicitada debe explicar la estrategia antes de presentar el codigo, justificar por que es adecuada e indicar complejidad temporal y espacial.
- Debe explicar por que no hace falta ordenar el arreglo para encontrar el minimo.
- No se solicitan pruebas automatizadas ni una suite de casos de ejemplo aparte del arreglo usado por el `main`.

## Estrategia tecnica

Recorrer el arreglo una sola vez. Tras validar que la referencia no sea `null` y que el arreglo contenga elementos, inicializar el minimo con el primer elemento. Comparar cada elemento restante con el minimo actual y actualizarlo cuando se encuentre un valor menor. Devolver el minimo al terminar el recorrido.

Inicializar con el primer elemento evita usar un valor centinela que pudiera fallar con los extremos del tipo `int`, incluido `Integer.MIN_VALUE`. La estrategia tambien maneja naturalmente valores negativos y repetidos; en un arreglo de un solo elemento devuelve ese elemento.

Ordenar no es necesario: basta con inspeccionar cada valor y conservar el menor visto hasta el momento. El ordenamiento agrega trabajo sin aportar informacion necesaria para obtener el minimo.

## Complejidad esperada

- Temporal: `O(n)`, donde `n` es la longitud del arreglo, porque cada elemento se inspecciona una vez.
- Espacial: `O(1)`, porque el recorrido solo requiere una variable para conservar el minimo y no crea estructuras auxiliares.

## Alcance del prompt para OpenCode

El prompt debe pedir una respuesta organizada en este orden:

1. Explicacion de la estrategia de recorrido lineal y su justificacion.
2. Complejidades temporal y espacial.
3. Codigo Java comentado, con metodo reutilizable para `int[]` y un `main` que lo demuestre con datos fijos.
4. Explicacion de por que ordenar el arreglo no es necesario.

El metodo debe definir claramente el comportamiento ante `null` y arreglo vacio, lanzando `IllegalArgumentException` en ambos casos. No se requiere lectura interactiva, devolver la posicion del minimo ni implementar pruebas automatizadas.

## Criterios de aceptacion

- El prompt deja claro que el resultado es el valor minimo y que la entrada es un `int[]`.
- La explicacion aparece antes del codigo y justifica la eleccion de una pasada lineal.
- Se indican correctamente las complejidades `O(n)` temporal y `O(1)` espacial.
- El codigo solicitado valida `null` y arreglo vacio, y su `main` usa datos fijos.
- La respuesta solicita comentarios en el codigo y una explicacion explicita de por que no se ordena.
- No se amplian los requisitos con entrada por consola, retorno del indice ni una suite de pruebas.