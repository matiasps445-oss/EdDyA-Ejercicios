# Ejercicio 4: Potencia recursiva

## ¿Qué hace el programa?

La clase `PotenciaRecursiva` calcula una potencia entera sin utilizar `Math.pow`. El método `potencia(base, exponente)` devuelve la base multiplicada por sí misma tantas veces como indica un exponente no negativo. Por ejemplo, `potencia(2, 3)` calcula `2 * 2 * 2`, cuyo resultado es `8`.

El método `main` ejecuta los tres casos solicitados:

- `potencia(2, 0)`, que devuelve `1`.
- `potencia(2, 1)`, que devuelve `2`.
- `potencia(2, 3)`, que devuelve `8`.

## Cómo funciona la recursión

El método primero comprueba si el exponente es negativo. Si lo es, lanza una `IllegalArgumentException`, porque esta implementación solo procesa exponentes mayores o iguales a cero. Si el exponente no es negativo, comprueba el caso base. Cuando no se cumple el caso base, ejecuta la llamada recursiva:

```java
return base * potencia(base, exponente - 1);
```

En cada paso el exponente disminuye en uno, mientras la base permanece igual. Cada llamada deja pendiente la multiplicación por la base hasta que la llamada siguiente devuelve un resultado.

## Caso base: exponente igual a cero

La condición `exponente == 0` devuelve `1`. Este valor corresponde a la identidad multiplicativa: multiplicar por uno no cambia el resultado. Por eso una potencia con exponente cero tiene resultado uno, y esa respuesta permite cerrar la recursión.

También es esencial para que el proceso termine: al reducir el exponente de uno en uno, un exponente no negativo eventualmente llega a cero. En ese momento ya no se realiza otra llamada recursiva.

## Caso recursivo y pila de llamadas

La regla implementada es:

```text
potencia(base, exponente) = base * potencia(base, exponente - 1)
```

Para `potencia(2, 3)`, primero se acumulan las llamadas. La última es la que encuentra el caso base:

```text
Llamadas (se apilan):
potencia(2, 3) espera el resultado de potencia(2, 2)
potencia(2, 2) espera el resultado de potencia(2, 1)
potencia(2, 1) espera el resultado de potencia(2, 0)
potencia(2, 0) devuelve 1
```

La pila se resuelve en orden inverso, desde la llamada más profunda hacia la primera:

```text
potencia(2, 0) = 1
potencia(2, 1) = 2 * 1 = 2
potencia(2, 2) = 2 * 2 = 4
potencia(2, 3) = 2 * 4 = 8
```

Cada llamada conserva sus propios valores de `base` y `exponente`, junto con la multiplicación que aún debe completar. La pila de llamadas permite recordar ese trabajo pendiente hasta que el caso base entrega el primer resultado.

## Errores comunes

- **Olvidar el caso base:** sin la condición `exponente == 0`, las llamadas no tendrían un punto de detención y acabarían provocando `StackOverflowError`.
- **No reducir el exponente:** si la llamada recursiva usara el mismo exponente, el problema no se acercaría al caso base y la recursión tampoco terminaría.
- **Reducir en la dirección equivocada:** con un exponente positivo, aumentarlo en lugar de restarle uno lo alejaría del caso base.
- **Aceptar exponentes negativos sin validarlos:** con esta regla recursiva, por ejemplo, `potencia(2, -1)` llamaría a `potencia(2, -2)`, luego a `potencia(2, -3)` y así sucesivamente. Nunca llegaría a cero; se acumularían llamadas hasta producir `StackOverflowError`. El código evita ese comportamiento lanzando `IllegalArgumentException` antes de llamar recursivamente.
- **Confundir el valor del caso base:** devolver `0` para exponente cero haría que toda potencia positiva acabara multiplicándose por cero.
- **Ignorar el límite de `int`:** el resultado se almacena en un entero de 32 bits. Una potencia suficientemente grande puede superar su rango y desbordarse, aunque la recursión esté correctamente implementada.

## Resumen

La solución termina porque cada llamada reduce el exponente hacia cero. El caso base devuelve `1`; luego, al desapilarse las llamadas, cada nivel multiplica ese resultado por la base. La validación inicial rechaza exponentes negativos, para los cuales esta estrategia no alcanzaría el caso base.
