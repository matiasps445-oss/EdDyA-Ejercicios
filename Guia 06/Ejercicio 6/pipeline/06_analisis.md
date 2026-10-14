# Plan técnico: contar dígitos recursivamente

## Objetivo

Implementar en Java una función recursiva que cuente cuántos dígitos tiene un número entero positivo.

## Diseño de la función

- **Nombre sugerido:** `contarDigitos`.
- **Parámetro:** un entero positivo `numero`.
- **Tipo de retorno:** `int`, para devolver el total de dígitos.
- **Precondición:** `numero` debe ser mayor que cero. El enunciado define entradas positivas; cero y los números negativos quedan fuera del alcance de esta solución.

## Lógica recursiva

1. **Caso base:** si `numero < 10`, devolver `1`, porque un entero positivo menor que `10` tiene un solo dígito. Este caso detiene las llamadas recursivas.
2. **Caso recursivo:** devolver `1 + contarDigitos(numero / 10)`. La división entera por `10` elimina el último dígito; cada llamada cuenta ese dígito con el `1` y delega los restantes a la siguiente llamada.
3. En cada paso el argumento se hace más pequeño y, para un entero positivo, eventualmente queda entre `1` y `9`, alcanzando el caso base.

## Terminación y pila de llamadas

La división entera por `10` reduce la cantidad de dígitos de un número positivo de más de una cifra, por lo que la recursión avanza hasta un valor menor que `10`.

Para `numero = 1048`, las llamadas se apilan así:

```text
contarDigitos(1048) espera 1 + contarDigitos(104)
contarDigitos(104)  espera 1 + contarDigitos(10)
contarDigitos(10)   espera 1 + contarDigitos(1)
contarDigitos(1)    caso base: devuelve 1
```

Al retornar desde el caso base, se resuelven las operaciones pendientes: `contarDigitos(10) = 1 + 1 = 2`, `contarDigitos(104) = 1 + 2 = 3` y `contarDigitos(1048) = 1 + 3 = 4`.

## Comentarios requeridos en el código

- Explicar por qué `numero < 10` es el caso base y devuelve `1`.
- Explicar que el caso recursivo suma `1` y divide el número entre `10`.
- Describir cómo las llamadas se apilan y cómo se suman los resultados al retornar.

## Pruebas manuales

| Caso | Entrada | Resultado esperado |
|---|---:|---:|
| 1 | `numero = 7` | `1` |
| 2 | `numero = 42` | `2` |
| 3 | `numero = 1048` | `4` |

## Criterios de aceptación

- La función recibe un entero positivo y devuelve un entero con la cantidad de dígitos.
- El caso base devuelve `1` cuando `numero < 10`.
- El caso recursivo aplica `1 + contarDigitos(numero / 10)` usando división entera.
- Las tres pruebas producen los resultados esperados y la explicación del código cubre el caso base, el caso recursivo y la pila de llamadas.