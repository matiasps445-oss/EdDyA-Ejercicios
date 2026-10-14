# Ejercicio 7: Suma recursiva de dígitos

## ¿Qué hace el programa?

La clase `SumarDigitos` calcula y devuelve la suma de las cifras de un entero positivo. Su método `main` prueba los valores `8`, `45` y `1234`, cuyos resultados son `8`, `9` y `10`.

El método recursivo aplica esta regla:

```text
sumarDigitos(numero) = (numero % 10) + sumarDigitos(numero / 10)
```

En cada llamada separa la última cifra del número y suma esa cifra al resultado obtenido para las cifras que quedan.

## Módulo y división entera

Para un entero positivo, `numero % 10` calcula el resto de la división por diez. Ese resto es la última cifra decimal. Por ejemplo, `1234 % 10` da `4`.

Como ambos operandos de `numero / 10` son enteros, Java hace división entera: descarta la parte fraccionaria. Así, `1234 / 10` da `123`; la última cifra se elimina. En las llamadas siguientes, `123 / 10` da `12` y `12 / 10` da `1`.

Los dos operadores tienen tareas distintas: `% 10` extrae una cifra para sumarla, mientras que `/ 10` reduce el número para poder procesar las cifras restantes.

## Caso base: `numero < 10`

La condición del método es:

```java
if (numero < 10) {
    return numero;
}
```

Bajo la precondición de que el argumento es positivo, un valor menor que `10` está entre `1` y `9`. Tiene una sola cifra, así que su suma es el propio valor. Devolverlo detiene la recursión: no se realiza otra llamada.

El caso base es esencial para que las llamadas terminen. Dividir repetidamente un entero positivo de varias cifras entre `10` acaba dejando una sola cifra, que satisface `numero < 10`.

## Pila de llamadas para `sumarDigitos(1234)`

Cada llamada guarda la cifra que obtuvo con `% 10` y espera el resultado de la llamada más pequeña. Durante el descenso, la pila queda así, con la invocación más reciente al final:

```text
sumarDigitos(1234) guarda 4; espera sumarDigitos(123)
sumarDigitos(123)  guarda 3; espera sumarDigitos(12)
sumarDigitos(12)   guarda 2; espera sumarDigitos(1)
sumarDigitos(1)    caso base: devuelve 1
```

Luego las llamadas se completan en orden inverso. Cada una suma su cifra pendiente al resultado recibido:

```text
sumarDigitos(1)    devuelve 1
sumarDigitos(12)   devuelve 2 + 1 = 3
sumarDigitos(123)  devuelve 3 + 3 = 6
sumarDigitos(1234) devuelve 4 + 6 = 10
```

Por lo tanto, `sumarDigitos(1234)` devuelve `10`. El valor se construye al retornar desde el caso base hacia la primera llamada.

## Errores comunes

- **No acercar la entrada al caso base:** si la llamada no usara `numero / 10`, la cantidad de cifras no disminuiría y las llamadas podrían acumularse hasta producir `StackOverflowError`.
- **Intercambiar `/` y `%`:** `/ 10` reduce el número; `% 10` obtiene la última cifra. Si se usa el resto para la llamada recursiva, se pierde el resto de las cifras. Por ejemplo, `1234 % 10` produce `4`, no `123`.
- **Olvidar sumar la cifra extraída:** devolver solo `sumarDigitos(numero / 10)` omitiría la cifra actual. En el caso `1234`, se perdería el `4` de la primera llamada.
- **Usar un caso base que devuelva un valor incorrecto:** para una cifra positiva, devolver `0` en vez de `numero` haría que esa cifra no se incluyera en la suma final.
- **Pasar un número negativo sin adaptar el método:** la implementación supone entrada positiva y no valida el argumento. Por ejemplo, con `-45`, la condición `numero < 10` se cumple inmediatamente y el método devuelve `-45`, no la suma `9` de sus cifras. Java conserva el signo en el resto (`-45 % 10` es `-5`) y la división entera trunca hacia cero, por lo que habría que definir y tratar los negativos antes de aplicar esta lógica.
- **Aplicar `Math.abs()` directamente a cualquier `int`:** `Math.abs(Integer.MIN_VALUE)` sigue siendo negativo porque el valor positivo correspondiente no cabe en un `int`. Si se ampliara el contrato a negativos, ese límite requeriría tratar el valor con un tipo más amplio antes de obtener su magnitud.

## Resumen

El caso base devuelve la cifra que queda. Para números mayores, `% 10` obtiene la cifra actual y `/ 10` elimina esa cifra antes de la siguiente llamada. Al regresar, cada llamada suma su cifra al subtotal recibido. Para `1234`, los resultados parciales son `1`, `3`, `6` y finalmente `10`.