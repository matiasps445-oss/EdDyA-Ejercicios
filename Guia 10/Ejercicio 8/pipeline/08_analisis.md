# Plan técnico: invertir una palabra con una pila

## Objetivo

Implementar un método Java que reciba una palabra `String` y devuelva sus caracteres en orden inverso, utilizando la pila enlazada existente. La inversión debe surgir del comportamiento LIFO de la pila, no de cálculos de índices sobre la palabra.

## Dependencia de la pila

- Reutilizar `PilaEnlazada<Character>` de la Guía 10; no utilizar `java.util.Stack`, arreglos ni colecciones para reemplazar la pila enlazada.
- La pila debe ofrecer `apilar(T dato)`, `desapilar()` y `estaVacia()`.
- Si cada ejercicio se compila de forma independiente, incluir `PilaEnlazada.java` en la compilación o configurar esa dependencia en el classpath/source path.

## Algoritmo en dos fases

Implementar un método, por ejemplo `String invertirPalabra(String palabra)`:

1. Crear una `PilaEnlazada<Character>` vacía.
2. Recorrer la palabra desde el primer carácter hasta el último y apilar cada `char` como `Character`.
3. Crear un `StringBuilder` vacío para el resultado.
4. Mientras la pila no esté vacía, desapilar un carácter y agregarlo al `StringBuilder`.
5. Devolver el resultado como `String`.

La última letra apilada queda en el tope y sale primero al desapilar. Repetirlo para todos los caracteres produce el orden inverso. Una palabra vacía recorre cero caracteres y devuelve otra cadena vacía.

## Invariantes y eficiencia

- Después de la primera fase, la pila contiene todos los caracteres de la palabra; el tope es el último carácter original.
- Cada carácter se apila y desapila exactamente una vez.
- La pila queda vacía al terminar la segunda fase.
- **Tiempo:** $O(n)$ para una palabra de longitud $n$.
- **Espacio adicional:** $O(n)$, debido a los nodos de la pila y al resultado.

Usar `StringBuilder` evita crear repetidamente nuevas cadenas al concatenar caracteres dentro del ciclo; no cambia el uso de la pila para obtener el orden inverso.

## Prueba de demostración en `main`

1. Definir la palabra original `algoritmo`.
2. Llamar a `invertirPalabra`.
3. Imprimir la palabra original y el resultado.
4. Confirmar que el resultado esperado es `omtirogla`.

## Criterios de aceptación

- El método procesa la palabra de izquierda a derecha y apila cada carácter.
- La segunda fase desapila hasta vaciar la pila y construye el resultado en ese orden.
- Se reutiliza la pila enlazada genérica con `Character`; no se sustituye por una estructura de colecciones de Java.
- `main` muestra `algoritmo` y `omtirogla`.
