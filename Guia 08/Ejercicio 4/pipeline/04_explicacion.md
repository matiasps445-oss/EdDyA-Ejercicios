# Explicación de `HistorialNavegacion`

El simulador representa el historial con una pila LIFO guardada en el arreglo fijo `String[] urls`. La variable `top` señala el índice de la página actual; empieza en `-1`, que indica que todavía no hay ninguna URL guardada.

## Por qué el historial es un ejemplo de LIFO

LIFO significa **Last In, First Out**: lo último que entra es lo primero que sale. Cada nueva visita se coloca encima de las anteriores y es la página visible en ese momento. Al presionar “Atrás”, se retira la página actual y vuelve a quedar visible la que se visitó inmediatamente antes.

Con la secuencia de la prueba, `google.com` queda debajo de `github.com`, y `stackoverflow.com` queda arriba. `stackoverflow.com` es, por tanto, la primera URL que se retira al volver atrás.

## Cómo cambian el arreglo y el tope

Con capacidad `3`, el arreglo empieza vacío. La tabla muestra el estado físico y el estado lógico; solo cuentan las posiciones desde `0` hasta `top`:

| Acción | Arreglo `[0, 1, 2]` | `top` | Página actual / estado lógico |
|---|---|---:|---|
| Inicio | `[null, null, null]` | -1 | historial vacío |
| Visitar `google.com` | `[google.com, null, null]` | 0 | `google.com` |
| Visitar `github.com` | `[google.com, github.com, null]` | 1 | `github.com` |
| Visitar `stackoverflow.com` | `[google.com, github.com, stackoverflow.com]` | 2 | `stackoverflow.com` |
| Consultar actual | sin cambios | 2 | devuelve `stackoverflow.com` |
| Volver atrás | la celda 2 puede conservar el valor retirado | 1 | `github.com` |

En `visitarPagina`, primero se comprueba que haya espacio, luego `top` aumenta en uno y la URL se escribe en esa posición. En `volverAtras`, se guarda y devuelve la URL de `urls[top]` y después se decrementa `top`. La celda que quedó fuera del tope puede conservar físicamente la URL retirada, pero ya no forma parte del historial lógico y se sobrescribirá si se visita otra página. `consultarPaginaActual` solo lee `urls[top]`; no cambia `top` ni elimina la página.

La salida de la prueba es:

```text
Página actual: stackoverflow.com
Página retirada: stackoverflow.com
Página actual después de Atrás: github.com
```

## Volver atrás repetidamente hasta vaciar el historial

Después de retirar `stackoverflow.com`, `top` vale `1` y la página actual es `github.com`. Otro `volverAtras()` retiraría `github.com`, dejaría `top` en `0` y haría actual a `google.com`. Un tercer retroceso retiraría `google.com` y dejaría `top` en `-1`, es decir, el historial vacío.

Si se intenta un retroceso adicional, `volverAtras()` llama primero a `estaVacio()`. Al ser verdadero, lanza `IllegalStateException` antes de leer `urls[top]`; así evita intentar acceder a `urls[-1]` y no modifica el estado. `consultarPaginaActual()` también verifica si está vacío antes de acceder al arreglo. De forma análoga, `visitarPagina()` comprueba `estaLleno()` antes de incrementar `top`, evitando escribir después del último índice permitido.

La clase implementa el retroceso eliminando la URL superior; no mantiene una pila de “Adelante”. Ese comportamiento adicional no forma parte del ejercicio.

## Reflexión sobre el prompt

El prompt inicial explicó perfectamente la relación entre el historial y el principio LIFO: la última URL visitada debe ser la primera que se retira al usar “Atrás”. También especificó las operaciones y los límites, por lo que no fue necesario realizar ningún ajuste al prompt para obtener el comportamiento esperado.