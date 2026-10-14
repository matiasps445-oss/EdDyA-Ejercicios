# Ejercicio 5: Conteo regresivo recursivo

## ¿Qué hace el programa?

La clase `ConteoRegresivo` imprime una secuencia de números enteros desde el valor recibido hasta `0`, en orden descendente. El método `main` muestra un rótulo para cada prueba y llama al método recursivo con `5`, `1` y `0`.

Las secuencias que imprime el método son:

- Para `n = 5`: `5`, `4`, `3`, `2`, `1`, `0`.
- Para `n = 1`: `1`, `0`.
- Para `n = 0`: `0`.

Cada número aparece en una línea. Los rótulos de los casos también se imprimen desde `main`.

## Cómo funciona paso a paso

El método `conteoRegresivo` sigue este orden:

1. Comprueba si `n < 0`. Si se cumple, ejecuta `return;` y termina esa llamada sin imprimir.
2. Si `n` no es negativo, imprime su valor con `System.out.println(n)`.
3. Llama al mismo método con `n - 1`.
4. Cuando esa llamada termina, la llamada actual también termina. No queda ningún cálculo adicional por hacer.

Por ejemplo, al comenzar con `5`, se imprime `5` antes de invocar `conteoRegresivo(4)`. Este orden hace que la salida aparezca de mayor a menor mientras se profundiza en la recursión.

## Simulación visual de la pila de llamadas para `n = 5`

En cada paso se crea un marco de llamada que conserva el valor de `n` de esa invocación. `main` permanece debajo de ellos en la pila.

```text
Inicio: main llama a conteoRegresivo(5)

La pila crece (la llamada más reciente queda arriba):

  conteoRegresivo(5)   imprime 5; llama a conteoRegresivo(4)
  conteoRegresivo(4)   imprime 4; llama a conteoRegresivo(3)
  conteoRegresivo(3)   imprime 3; llama a conteoRegresivo(2)
  conteoRegresivo(2)   imprime 2; llama a conteoRegresivo(1)
  conteoRegresivo(1)   imprime 1; llama a conteoRegresivo(0)
  conteoRegresivo(0)   imprime 0; llama a conteoRegresivo(-1)
  conteoRegresivo(-1)  cumple n < 0 y retorna sin imprimir

La pila se vacía al retornar:

  conteoRegresivo(-1) retorna a conteoRegresivo(0)
  conteoRegresivo(0)  termina y retorna a conteoRegresivo(1)
  conteoRegresivo(1)  termina y retorna a conteoRegresivo(2)
  conteoRegresivo(2)  termina y retorna a conteoRegresivo(3)
  conteoRegresivo(3)  termina y retorna a conteoRegresivo(4)
  conteoRegresivo(4)  termina y retorna a conteoRegresivo(5)
  conteoRegresivo(5)  termina y retorna a main
```

Las llamadas de `5` a `0` imprimen antes de crear la siguiente llamada. La llamada con `-1` es la primera que no imprime; su retorno permite que las anteriores vayan finalizando en orden inverso. A diferencia de un método que calcula un valor, estas llamadas no necesitan esperar un resultado para combinarlo con otra operación.

## ¿Qué significa `void` en Java?

La declaración `public static void conteoRegresivo(int n)` indica que el método no devuelve un valor al código que lo invocó. Su propósito es producir un efecto observable: imprimir números en la consola.

Un método `void` puede finalizar al llegar al cierre de su bloque o utilizar `return;` para salir antes, como sucede con el caso base. Ese `return;` no lleva expresión ni valor. Al regresar de una llamada recursiva, el control vuelve a la llamada anterior, pero no se entrega un resultado que esa llamada tenga que almacenar o procesar. Por eso el método no usa una expresión como `return conteoRegresivo(n - 1);`; sencillamente invoca el método y luego termina.

## Caso base: `n < 0`

La condición `if (n < 0)` es el caso base. Cuando el conteo llega a `0`, esa llamada imprime `0` y realiza una última llamada con `-1`. Como `-1 < 0`, esta última llamada retorna inmediatamente y no muestra ningún número negativo.

El caso base detiene la recursión porque evita que se haga otra llamada. Sin una condición que termine la cadena, las llamadas seguirían acumulándose en la pila del hilo y la ejecución acabaría normalmente con `StackOverflowError`.

## Por qué se usa `n - 1` y qué pasaría con `n + 1`

La llamada `conteoRegresivo(n - 1)` reduce el argumento en cada paso: `5`, `4`, `3`, `2`, `1`, `0`, `-1`. Así se acerca de forma directa a la condición `n < 0`.

Si se escribiera accidentalmente `conteoRegresivo(n + 1)`, un valor inicial como `5` produciría `5`, `6`, `7` y seguiría aumentando en lugar de alcanzar pronto el caso base. Cada invocación nueva ocuparía otro marco de pila y mantendría pendientes las llamadas anteriores. La pila del hilo tiene capacidad limitada; al agotarla, Java lanzaría `StackOverflowError`. Por eso toda llamada recursiva debe avanzar hacia una condición de terminación.

## Resumen

El método imprime el valor actual y delega el resto del conteo a una llamada con un argumento menor. El caso base en `n < 0` impide imprimir números negativos y corta la recursión. Como el método es `void`, las llamadas solo realizan la impresión y luego retornan; no calculan ni propagan un resultado.