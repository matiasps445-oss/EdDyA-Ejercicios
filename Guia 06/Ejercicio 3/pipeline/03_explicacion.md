# Explicacion de `MultiplicacionRecursiva`

El programa implementa una funcion recursiva que calcula el producto de dos enteros sin usar el operador `*`. La idea es reemplazar la multiplicacion por sumas repetidas:

```java
multiplicacion(a, b) = a + multiplicacion(a, b - 1)
```

Esto quiere decir que multiplicar `a` por `b` equivale a sumar `a` exactamente `b` veces. Por ejemplo, `4 * 3` es igual a `4 + 4 + 4`.

## Funcionamiento paso a paso

La funcion es esta:

```java
public static int multiplicacion(int a, int b) {
    if (b < 0) {
        throw new IllegalArgumentException("Los factores deben ser no negativos para esta versión recursiva.");
    }

    if (b == 0) {
        return 0;
    }

    return a + multiplicacion(a, b - 1);
}
```

### 1. Validacion inicial

La primera condicion es:

```java
if (b < 0) {
    throw new IllegalArgumentException(...);
}
```

Esto evita que el programa reciba valores negativos en el segundo parametro. Para esta version funcional, se asume que solo se aceptan factores no negativos, porque la multiplicacion se define como sumas repetidas y reducir un numero negativo no tiene sentido para este enfoque pedagogico.

### 2. Caso base

La condicion mas importante es:

```java
if (b == 0) {
    return 0;
}
```

Este es el caso base. Si uno de los factores vale `0`, el resultado es `0` por definicion matematica. Es decir, cualquier numero multiplicado por cero da cero. Al retornar `0`, la recursividad termina y no se siguen haciendo llamadas.

### 3. Caso recursivo

Cuando `b > 0`, la funcion hace:

```java
return a + multiplicacion(a, b - 1);
```

Esto expresa que la multiplicacion se convierte en una suma sucesiva. Por ejemplo:

```text
multiplicacion(4, 3)
= 4 + multiplicacion(4, 2)
= 4 + (4 + multiplicacion(4, 1))
= 4 + (4 + (4 + multiplicacion(4, 0)))
= 4 + (4 + (4 + 0))
= 12
```

La clave es que cada llamada reduce el parametro `b` en 1, acercandose al caso base de 0.

## Simulacion visual de la pila de llamadas para multiplicacion(4, 3)

Cuando se ejecuta:

```java
multiplicacion(4, 3)
```

Java crea el primer marco en la pila:

```text
multiplicacion(4, 3)
```

Como `b` no es 0, entra al caso recursivo y hace:

```text
4 + multiplicacion(4, 2)
```

La pila queda asi:

```text
multiplicacion(4, 3)
  -> multiplicacion(4, 2)
```

Luego se llama a `multiplicacion(4, 2)`, que hace:

```text
4 + multiplicacion(4, 1)
```

Y la pila se ve asi:

```text
multiplicacion(4, 3)
  -> multiplicacion(4, 2)
      -> multiplicacion(4, 1)
```

Luego:

```text
multiplicacion(4, 3)
  -> multiplicacion(4, 2)
      -> multiplicacion(4, 1)
          -> multiplicacion(4, 0)
```

Finalmente, `multiplicacion(4, 0)` llega al caso base y devuelve `0`:

```text
multiplicacion(4, 0) -> devuelve 0
```

Entonces Java resuelve la pila de abajo hacia arriba:

```text
multiplicacion(4, 1) devuelve 4 + 0 = 4
multiplicacion(4, 2) devuelve 4 + 4 = 8
multiplicacion(4, 3) devuelve 4 + 8 = 12
```

Eso demuestra como la recursividad construye el resultado al volver hacia la llamada inicial.

## Por que la recursividad funciona aqui

La idea es que la multiplicacion puede definirse como una suma repetida. En lugar de usar el operador `*`, el algoritmo acumula el valor `a` una vez por cada unidad que queda en `b`.

Cada llamada hace exactamente lo siguiente:

```java
return a + multiplicacion(a, b - 1);
```

Eso hace que el problema se vuelva mas pequeno de manera consistentemente: `b` va reduciendose hasta llegar a `0`.

## Errores comunes en este tipo de recursividad

### 1. Omitir el caso base

Si no se comprueba `b == 0`, la funcion seguira llamandose con `b - 1` para siempre. El resultado es una recusion infinita y eventualmente `StackOverflowError`.

### 2. Reducir el parametro equivocado

Si en lugar de reducir `b` se reduce `a`, el problema puede no acercarse al caso base valido para la definicion propuesta. En este enfoque, la reduccion debe hacerse sobre `b` porque se esta sumando `a` repetidas veces.

### 3. No contemplar negativos

Si se permite `b < 0` sin validacion, la recursion no tiene una terminacion clara dentro del caso de uso planteado. Por eso se lanza `IllegalArgumentException` cuando el factor es negativo.

### 4. Confundir la estructura de la recursividad

Esto no es una suma simple de dos valores: hay que entender que cada llamada espera el resultado de la siguiente. Eso es precisamente lo que se observa en la pila de llamadas.

## Resumen

La funcion `multiplicacion(a, b)` funciona porque reemplaza la multiplicacion por sumas repetidas. El caso base `b == 0` devuelve `0`, deteniendo la recursion. El caso recursivo `a + multiplicacion(a, b - 1)` reduce `b` en 1 cada vez y acumula `a` para construir el producto final. Para `multiplicacion(4, 3)`, la pila de llamadas se desarrolla como:

```text
multiplicacion(4, 3) -> multiplicacion(4, 2) -> multiplicacion(4, 1) -> multiplicacion(4, 0)
```

y luego se resuelve de abajo hacia arriba para devolver `12`.
