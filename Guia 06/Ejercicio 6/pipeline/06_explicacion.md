# Ejercicio 6: Contar dígitos con recursividad

## ¿Qué hace el programa?

La clase `ContarDigitos` calcula la cantidad de dígitos de un entero positivo. El método `contarDigitos` devuelve un `int`, y el método `main` muestra el resultado para los valores `7`, `42` y `1048`:

```text
contarDigitos(7) = 1
contarDigitos(42) = 2
contarDigitos(1048) = 4
```

La idea consiste en contar el último dígito como `1` y pedir recursivamente el conteo del número restante. Por ejemplo, dividir `1048` entre `10` deja `104`, que contiene los dígitos restantes.

## Caso base: `numero < 10`

El método contiene esta condición:

```java
if (numero < 10) {
    return 1;
}
```

Con la precondición de que `numero` sea positivo, cualquier valor menor que `10` está entre `1` y `9` y tiene exactamente un dígito. Devolver `1` da por terminada la cadena de llamadas: ya no se invoca otra vez a `contarDigitos`.

El caso base no solo aporta el conteo del último dígito que queda; también garantiza la terminación. Cada división recursiva reduce un número positivo de varias cifras hasta que queda un valor de una sola cifra.

## Caso recursivo y división entera

Cuando `numero` es `10` o más, se ejecuta:

```java
return 1 + contarDigitos(numero / 10);
```

En Java, al dividir dos valores `int`, se realiza división entera: se descarta la parte fraccionaria. Para números positivos, dividir por `10` quita el último dígito decimal:

```text
1048 / 10 = 104
104  / 10 = 10
10   / 10 = 1
```

El `1 +` cuenta el dígito que se quitó en ese nivel. La llamada recursiva cuenta los dígitos que quedaron. Así, cada nivel contribuye con uno al resultado final.

## Pila de llamadas para `contarDigitos(1048)`

Cada invocación conserva su propio valor de `numero` y deja pendiente la suma `1 + ...` hasta recibir el resultado de la llamada siguiente. La pila crece hacia arriba en este esquema:

```text
Se apilan las llamadas:

contarDigitos(1048) espera 1 + resultado de contarDigitos(104)
  contarDigitos(104) espera 1 + resultado de contarDigitos(10)
    contarDigitos(10) espera 1 + resultado de contarDigitos(1)
      contarDigitos(1) alcanza el caso base y devuelve 1
```

Luego las llamadas se resuelven al revés, desde la más profunda:

```text
contarDigitos(1)    devuelve 1                 (caso base)
contarDigitos(10)   devuelve 1 + 1 = 2
contarDigitos(104)  devuelve 1 + 2 = 3
contarDigitos(1048) devuelve 1 + 3 = 4
```

Por tanto, el resultado final de `contarDigitos(1048)` es `4`. A diferencia de un método `void`, este método sí devuelve un valor: cada llamada recibe el entero de la llamada más profunda, le suma `1` y entrega la suma a su llamador.

## Errores comunes

- **Olvidar o cambiar incorrectamente el caso base:** sin un retorno para los valores de una cifra, las llamadas no tendrían una condición que las detenga y podrían acumularse hasta causar `StackOverflowError`.
- **No reducir el número:** volver a llamar con el mismo `numero` no acercaría la ejecución al caso base. La llamada debe usar `numero / 10`.
- **Confundir `/` con `%`:** `/ 10` elimina el último dígito de un entero positivo, mientras que `% 10` obtiene el resto, es decir, el último dígito. Por ejemplo, `1048 / 10` es `104`, pero `1048 % 10` es `8`. Si se usara `% 10` en la llamada recursiva, se conservaría el último dígito en lugar del resto del número; `contarDigitos(1048)` terminaría contando `8` y daría `2`, no `4`.
- **No respetar la precondición positiva:** el método no valida la entrada. Si se le pasa `-42`, la condición `numero < 10` se cumple de inmediato y el método devuelve `1`, que no es la cantidad de cifras de `-42` sin contar el signo. Para admitir negativos habría que definir ese comportamiento y adaptar la implementación; el plan actual solo contempla enteros positivos.
- **Olvidar la división entera:** el parámetro y el resultado de `/ 10` son `int`, por lo que el resultado no incluye decimales. Esa propiedad es justamente la que elimina una cifra en cada paso para los números positivos.

## Resumen

`contarDigitos` devuelve `1` al alcanzar un número positivo menor que `10`. Para cualquier número mayor o igual a `10`, cuenta una cifra y llama con `numero / 10`. Las llamadas se apilan mientras el número se reduce y, al retornar, cada nivel suma su `1` pendiente. Para `1048`, los retornos son `1`, `2`, `3` y finalmente `4`.