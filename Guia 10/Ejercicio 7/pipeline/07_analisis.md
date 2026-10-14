# Plan técnico: verificador de paréntesis balanceados

## Objetivo

Implementar un verificador Java que reciba una expresión `String` y devuelva `true` si sus paréntesis `(` y `)` están balanceados, y `false` si no lo están. El control de aperturas pendientes se realizará con la pila enlazada genérica existente, parametrizada como `PilaEnlazada<Character>`.

## Dependencia de la pila

- Reutilizar la pila enlazada genérica implementada anteriormente; no utilizar `java.util.Stack` ni una colección de Java como almacenamiento.
- El verificador necesita `apilar(Character)`, `desapilar()` y `estaVacia()`.
- Al llamar `desapilar`, primero comprobar que la pila no esté vacía para identificar el cierre sobrante y no lanzar `EmptyStackException` por una expresión inválida.
- Si cada ejercicio debe compilarse de manera independiente, incluir o agregar al classpath la implementación de `PilaEnlazada<T>` de la Guía 10, sin duplicar una versión basada en colecciones.

## Algoritmo

Recorrer la expresión una sola vez, carácter por carácter:

1. Crear una pila vacía de `Character`.
2. Para cada carácter:
   - Si es `(`, apilarlo para registrar una apertura pendiente.
   - Si es `)`, comprobar primero `estaVacia()`. Si está vacía, devolver `false`: hay un cierre sin apertura correspondiente. Si no está vacía, desapilar una apertura para emparejarla.
   - Ignorar cualquier otro carácter; no afecta al balance de paréntesis.
3. Al terminar de recorrer la cadena, devolver si la pila está vacía. Si quedan elementos, hay aperturas sin cerrar y el resultado es `false`.

La expresión es válida únicamente cuando nunca aparece un cierre sin apertura previa y no quedan aperturas pendientes al final.

## Casos de prueba

- `(2+3)*(5-1)` debe devolver `true`: cada apertura encuentra su cierre correspondiente.
- `((a+b)*c)` debe devolver `true`: las aperturas anidadas se cierran en orden LIFO.
- `(2+3` debe devolver `false`: queda una apertura en la pila al terminar.
- `())(` debe devolver `false`: al procesar el segundo `)`, la pila ya está vacía.

En `main`, ejecutar los cuatro ejemplos e imprimir la expresión y su resultado booleano.

## Corrección y eficiencia

La pila representa las aperturas que todavía esperan cierre. LIFO es necesario para resolver aperturas anidadas: el paréntesis abierto más recientemente es el primero que debe cerrarse. La prueba de pila vacía antes de desapilar impide que un `)` sobrante provoque `EmptyStackException`.

- **Tiempo:** $O(n)$ para una expresión de longitud $n$, porque cada carácter se procesa una vez.
- **Espacio adicional:** $O(n)$ en el peor caso, si todos los caracteres relevantes son aperturas.

## Criterios de aceptación

- El verificador utiliza la pila enlazada genérica `PilaEnlazada<Character>`.
- Cada `(` se apila y cada `)` solo desapila después de comprobar que hay una apertura disponible.
- La respuesta final es `false` si hay un cierre sobrante o si quedan aperturas en la pila; en otro caso es `true`.
- Los caracteres distintos de `(` y `)` se ignoran.
- `main` evalúa los dos ejemplos válidos y los dos inválidos solicitados, mostrando `true` o `false`.
