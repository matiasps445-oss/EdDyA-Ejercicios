# Explicacion de `FactorialRecursivo`

El programa implementa la funcion recursiva `factorial(int n)` para calcular el factorial de un numero entero no negativo. La idea principal es que el factorial se puede definir de forma recursiva:

```java
factorial(n) = n * factorial(n - 1)
```

Esto significa que el problema grande se transforma en una version mas pequena del mismo problema. La recursividad se usa porque el factorial no es un calculo aislado: cada resultado depende del resultado anterior, pero con un valor menor.

## Funcionamiento paso a paso

La funcion es esta:

```java
public static int factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("El factorial solo está definido para números no negativos.");
    }

    if (n == 0 || n == 1) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

### 1. Validacion inicial

Cuando se llama a la funcion, primero se comprueba si `n` es negativo:

```java
if (n < 0) {
    throw new IllegalArgumentException(...);
}
```

Esto evita que se intente calcular factorial de un numero que no tiene sentido matematicamente. En Java, si no se validara esto, podrimos terminar con resultados absurdos o con una recursion no definida.

### 2. Caso base

Si `n` vale `0` o `1`, se ejecuta esta parte:

```java
if (n == 0 || n == 1) {
    return 1;
}
```

Esto es el caso base. Es la condicion que detiene la recursion. Sin este caso, la funcion seguiria llamandose a si misma hasta que eventualmente Java agotara la memoria de la pila.

### 3. Caso recursivo

Si `n > 1`, la funcion hace esto:

```java
return n * factorial(n - 1);
```

Es decir, llama a la misma funcion con un valor mas pequeno: `n - 1`. El problema se reduce de forma consistente hasta llegar a `1` o `0`, donde la recursion termina.

## Simulacion visual de la pila de llamadas para n = 5

Cuando ejecutamos:

```java
factorial(5)
```

la llamada inicial empuja un marco en la pila de llamadas:

```text
factorial(5)
```

Como `5` no es caso base, entra al caso recursivo y ejecuta:

```java
5 * factorial(4)
```

Ahora la pila queda asi:

```text
factorial(5)
  -> factorial(4)
```

Luego `factorial(4)` hace:

```text
factorial(5)
  -> factorial(4)
      -> factorial(3)
```

Y sigue:

```text
factorial(5)
  -> factorial(4)
      -> factorial(3)
          -> factorial(2)
```

Luego:

```text
factorial(5)
  -> factorial(4)
      -> factorial(3)
          -> factorial(2)
              -> factorial(1)
```

Finalmente, `factorial(1)` llega al caso base y devuelve `1`:

```text
factorial(5)
  -> factorial(4)
      -> factorial(3)
          -> factorial(2)
              -> factorial(1) -> devuelve 1
```

Entonces la pila se va resolviendo de abajo hacia arriba:

```text
factorial(2) devuelve 2 * 1 = 2
factorial(3) devuelve 3 * 2 = 6
factorial(4) devuelve 4 * 6 = 24
factorial(5) devuelve 5 * 24 = 120
```

Resultado final:

```text
factorial(5) = 120
```

Esto es exactamente lo que se conoce como deshacer la recursividad: cuando la llamada base devuelve un valor, cada llamada anterior lo usa para terminar su calculo.

## Por que el caso base detiene la recursividad

La condicion:

```java
if (n == 0 || n == 1)
```

es crucial porque es el punto donde el problema deja de reducirse. En lugar de seguir llamandose a si misma, devuelve un valor fijo:

```java
return 1;
```

Eso hace que la recursividad tenga una terminacion garantizada. Si no existiera este caso, la funcion seguiria llamandose con `n - 1` hasta llegar a valores negativos, y en algun momento Java lanzaria `StackOverflowError` por exceso de llamadas anidadas.

## Caso recursivo y reduccion del problema

El caso recursivo es:

```java
return n * factorial(n - 1);
```

La clave es que cada llamada usa un argumento menor: `n - 1`. Es decir, el conjunto de trabajo se hace mas pequeño en cada nivel.

Por ejemplo:

```text
factorial(5)
factorial(4)
factorial(3)
factorial(2)
factorial(1)
```

Cada llamada se acerca al caso base, y esa progresion es lo que hace posible completar el calculo.

## Errores comunes en recursividad

### 1. Omitir el caso base

Si se escribe algo como:

```java
return n * factorial(n - 1);
```

sin comprobar `n == 0 || n == 1`, el programa se volvera infinito y terminará con:

```text
StackOverflowError
```

### 2. No reducir el problema

Si en cada llamada se usa el mismo valor de `n`, la recursion nunca se acerca al final. Eso produce un bucle infinito de llamadas.

### 3. No validar valores negativos

Un numero negativo no tiene factorial matematico. Si la funcion permite `n < 0`, puede entrar en una secuencia no definida o producir resultados sin sentido.

### 4. Confundir la recursion con un ciclo

Aunque la funcion parece repetirse, cada llamada conserva su propio contexto en la pila. Es importante entender que cada instancia de la funcion espera a la siguiente llamada para continuar con la multiplicacion.

## Resumen

La recursividad funciona aqui porque el factorial se define en terminos de si mismo con un problema mas pequeno. El caso base `n == 0 || n == 1` evita la recursion infinita y devuelve `1`, mientras que el caso recursivo disminuye `n` en cada llamada hasta llegar a ese punto. Para `n = 5`, la pila de llamadas se ensambla de la siguiente forma:

```text
factorial(5) -> factorial(4) -> factorial(3) -> factorial(2) -> factorial(1)
```

y luego se deshace de abajo hacia arriba para devolver `120`.
