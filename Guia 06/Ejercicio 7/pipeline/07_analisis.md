# Plan técnico: sumar dígitos recursivamente

## Objetivo

Implementar en Java una función recursiva que calcule la suma de los dígitos de un número entero positivo.

## Diseño de la función

- **Nombre sugerido:** `sumarDigitos`.
- **Parámetro:** un entero positivo `numero`.
- **Tipo de retorno:** `int`, para devolver la suma de los dígitos.
- **Precondición:** `numero` debe ser mayor que cero. El planteo está definido para enteros positivos; cero y los números negativos quedan fuera del alcance.

## Lógica recursiva

1. **Caso base:** si `numero < 10`, devolver `numero`, porque para un entero positivo de una sola cifra la suma de sus dígitos es el mismo número. Este retorno detiene la recursión.
2. **Caso recursivo:** devolver `(numero % 10) + sumarDigitos(numero / 10)`. El módulo por `10` obtiene el último dígito, y la división entera por `10` elimina ese dígito para continuar con el resto.
3. En cada llamada el número se reduce; para una entrada positiva eventualmente queda entre `1` y `9`, alcanzando el caso base.

## Terminación y pila de llamadas

La división entera por `10` reduce el número de cifras en cada paso. La pila conserva el último dígito obtenido mediante `% 10` mientras espera la suma de los dígitos restantes.

Para `numero = 1234`, las llamadas pendientes se forman así:

```text
sumarDigitos(1234) espera 4 + sumarDigitos(123)
sumarDigitos(123)  espera 3 + sumarDigitos(12)
sumarDigitos(12)   espera 2 + sumarDigitos(1)
sumarDigitos(1)    caso base: devuelve 1
```

Al retornar desde el caso base, se completan las sumas: `sumarDigitos(12) = 2 + 1 = 3`, `sumarDigitos(123) = 3 + 3 = 6` y `sumarDigitos(1234) = 4 + 6 = 10`.

## Comentarios requeridos en el código

- Explicar por qué `numero < 10` es el caso base y devuelve `numero`.
- Explicar que `% 10` obtiene el último dígito y `/ 10` reduce el número.
- Describir cómo se apilan las llamadas y cómo se acumulan las sumas al retornar.

## Pruebas manuales

| Caso | Entrada | Resultado esperado |
|---|---:|---:|
| 1 | `numero = 8` | `8` |
| 2 | `numero = 45` | `9` |
| 3 | `numero = 1234` | `10` |

## Criterios de aceptación

- La función recibe un entero positivo y devuelve un entero con la suma de sus dígitos.
- El caso base devuelve el mismo número cuando `numero < 10`.
- El caso recursivo aplica `(numero % 10) + sumarDigitos(numero / 10)`.
- Las tres pruebas producen los resultados esperados y los comentarios explican el caso base, el caso recursivo y la pila de llamadas.