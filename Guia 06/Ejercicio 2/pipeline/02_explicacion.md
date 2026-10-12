# Explicacion de `SumaRecursiva`

El programa implementa una funcion recursiva que calcula la suma de los numeros desde `n` hasta `1`. Es decir, si se llama con `n = 5`, la funcion debe devolver:

```text
5 + 4 + 3 + 2 + 1 = 15
```

La idea principal es que la suma puede describirse de forma recursiva de la siguiente manera:

```java
suma(n) = n + suma(n - 1)
```

Esto significa que el problema grande se reduce a un problema mas pequeno del mismo tipo. La recursion se usa porque cada llamada depende del resultado de otra llamada con un valor menor.

## Funcionamiento paso a paso

La funcion es esta:

```java
public static int suma(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("La suma solo está definida para números no negativos.");
    }

    if (n == 0) {
        return 0;
    }

    return n + suma(n - 1);
}
```

### 1. Validacion inicial

La primera condicion es:

```java
if (n < 0) {
    throw new IllegalArgumentException(...);
}
```

Esto evita que la funcion trabaje con numeros negativos. Matemáticamente, el problema de sumar desde `n` hasta `1` no se define de la misma forma para valores negativos, por lo que es mejor rechazar la entrada de manera clara.

### 2. Caso base

Cuando `n` vale `0`, se ejecuta:

```java
if (n == 0) {
    return 0;
}
```

Este es el caso base. Es el punto donde la recursividad se detiene. La suma de cero numeros es `0`, por eso se devuelve ese valor y no se hace ninguna llamada adicional.

Sin este caso, la funcion continuaria llamandose a si misma con valores cada vez menores y eventualmente se acumularian demasiadas llamadas en la pila.

### 3. Caso recursivo

Cuando `n > 0`, la funcion hace:

```java
return n + suma(n - 1);
```

Esto expresa la propiedad matematica de la suma:

```text
suma(5) = 5 + suma(4)
```

Luego:

```text
suma(4) = 4 + suma(3)
```

Y asi sucesivamente hasta llegar a:

```text
suma(0) = 0
```

Es decir, cada llamada reduce el problema restando 1 a `n`, acercandose al caso base. Eso hace que el problema se vuelva cada vez mas pequeno hasta ser trivial.

## Simulacion visual de la pila de llamadas para suma(5)

Cuando llamamos:

```java
suma(5)
```

Java crea un marco de llamada en la pila:

```text
suma(5)
```

Como `5` no es `0`, entra al caso recursivo y hace:

```java
5 + suma(4)
```

La pila queda asi:

```text
suma(5)
  -> suma(4)
```

Luego `suma(4)` hace:

```text
suma(5)
  -> suma(4)
      -> suma(3)
```

Luego:

```text
suma(5)
  -> suma(4)
      -> suma(3)
          -> suma(2)
```

Luego:

```text
suma(5)
  -> suma(4)
      -> suma(3)
          -> suma(2)
              -> suma(1)
```

Y finalmente:

```text
suma(5)
  -> suma(4)
      -> suma(3)
          -> suma(2)
              -> suma(1)
                  -> suma(0)
```

Cuando `suma(0)` se ejecuta, entra al caso base y devuelve `0`:

```text
suma(0) -> devuelve 0
```

Entonces Java empieza a resolver la pila desde abajo hacia arriba:

```text
suma(1) devuelve 1 + 0 = 1
suma(2) devuelve 2 + 1 = 3
suma(3) devuelve 3 + 3 = 6
suma(4) devuelve 4 + 6 = 10
suma(5) devuelve 5 + 10 = 15
```

Resultado final:

```text
suma(5) = 15
```

Esto es lo que se entiende por "deshacer la recursividad": la ultima llamada resuelve el caso base y luego cada llamada anterior completa su calculo usando ese resultado.

## Por que es una buena forma de pensar la recursividad

La recursividad es apropiada cuando un problema puede definirse en terminos de una version mas pequena del mismo problema. En este caso:

```text
suma(n) = n + suma(n-1)
```

La diferencia entre una llamada y la siguiente es que `n` disminuye en 1. Eso hace que el problema converja hacia `0`, que es el punto de parada y la base del algoritmo.

## Errores comunes al implementar recursividad

### 1. Omitir el caso base

Si se hace solo:

```java
return n + suma(n - 1);
```

sin validar `n == 0`, la recursividad nunca termina. El programa continuara llamandose a si mismo hasta agotarse la memoria y producir `StackOverflowError`.

### 2. No reducir el problema

Si en cada llamada se usa el mismo valor de `n`, entonces la recursion no se acerca al caso base y se vuelve infinita.

### 3. No validar valores negativos

La suma desde `n` hasta `1` normalmente se define para `n >= 0`. Si se llama con `n < 0`, la funcion no tiene una definicion matematica clara para este contexto, por eso conviene lanzar una excepcion con `IllegalArgumentException`.

### 4. Confundir recursividad con una suma directa

Aunque la idea parece simple, el programa no hace "una sola cuenta"; cada llamada guarda su contexto en la pila de llamadas y espera la resolucion de la siguiente llamada. Comprender esta pila es clave para dominar la recursividad.

## Resumen

La funcion `suma(int n)` funciona porque cada llamada representa un problema mas pequeno: `n + suma(n - 1)`. El caso base `n == 0` devuelve `0`, deteniendo la recursion. Cuando `n = 5`, la pila de llamadas se desarrolla asi:

```text
suma(5) -> suma(4) -> suma(3) -> suma(2) -> suma(1) -> suma(0)
```

y luego se resuelve de vuelta hasta devolver:

```text
15
```

Eso demuestra como la recursividad combina la reduccion del problema y la resolucion en cascada por medio de la pila de llamadas.
