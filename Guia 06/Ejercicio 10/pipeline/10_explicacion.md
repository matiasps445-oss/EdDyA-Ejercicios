# Explicación de `BusquedaRecursiva`

El programa busca un número en un arreglo de enteros y devuelve `true` cuando lo encuentra o `false` cuando llega al final sin encontrarlo. La búsqueda está implementada en el método `buscar`; `main` prepara los arreglos de prueba e inicia cada búsqueda desde el índice `0`.

## Cómo avanza la búsqueda

El método recibe tres datos: el arreglo, el valor `buscado` y el `indice` que debe revisar en esa llamada. Primero compara el índice con `arreglo.length`. Si todavía no llegó al final, compara el elemento actual (`arreglo[indice]`) con el valor buscado. Si no coinciden, vuelve a llamar a `buscar` con `indice + 1`.

Así se avanza sin `for` ni `while`: cada llamada recursiva se ocupa de una sola posición y delega la siguiente posición en una nueva llamada. El método no necesita actualizar el índice de la llamada anterior; cada llamada tiene su propio valor de `indice`.

## Simulación de la pila de llamadas

Para buscar `3` en `[1, 2, 3, 4, 5]`, `main` inicia `buscar(arreglo, 3, 0)`. La pila crece al entrar en cada llamada que todavía no encontró el valor:

```text
Entrada / llamadas pendientes

buscar(arreglo, 3, 0)  revisa arreglo[0] = 1; no coincide
└─ espera el resultado de buscar(arreglo, 3, 1)
   buscar(arreglo, 3, 1)  revisa arreglo[1] = 2; no coincide
   └─ espera el resultado de buscar(arreglo, 3, 2)
      buscar(arreglo, 3, 2)  revisa arreglo[2] = 3; coincide
```

La llamada con índice `2` devuelve `true` de inmediato. No crea una llamada para el índice `3`, porque ya encontró el valor. Luego se completa la llamada de índice `1`, que devuelve el mismo `true` que recibió de la llamada recursiva; finalmente, la llamada inicial de índice `0` también devuelve `true`.

```text
Retorno / la pila se desarma

buscar(arreglo, 3, 2) devuelve true
buscar(arreglo, 3, 1) recibe true y devuelve true
buscar(arreglo, 3, 0) recibe true y devuelve true
```

Las llamadas pendientes no vuelven a comparar elementos al retornar: simplemente propagan el resultado de la búsqueda hacia quien las llamó.

## Los dos casos base

1. **Encontró el valor:** si `arreglo[indice] == buscado`, el método devuelve `true`. Este caso detiene la búsqueda con éxito porque el objetivo ya se cumplió. No se revisan las posiciones posteriores.
2. **Llegó al límite:** si `indice == arreglo.length`, devuelve `false`. Este caso detiene la búsqueda sin éxito porque ya se revisaron todas las posiciones válidas.

El límite se comprueba antes de evaluar `arreglo[indice]`. Esto también permite el arreglo vacío: su longitud es `0` y la llamada inicial usa índice `0`, así que devuelve `false` sin intentar leer una posición inexistente.

## Por qué no se usa un bucle

En una solución iterativa, un bucle cambia el índice para pasar a la siguiente posición. Aquí, la llamada `buscar(arreglo, buscado, indice + 1)` cumple ese papel: crea una nueva llamada que continúa desde el siguiente índice. La recursión termina porque cada llamada aumenta el índice en uno y, al llegar a la longitud del arreglo, se activa el caso base de fin.

## Casos de prueba del `main`

- `[1, 2, 3, 4, 5]`, buscar `3`: devuelve `true` al revisar el índice `2`.
- `[1, 2, 3, 4, 5]`, buscar `6`: ninguna posición coincide; la llamada con índice `5` devuelve `false` y ese valor se propaga hasta la llamada inicial.
- `[]`, buscar `1`: el índice inicial y la longitud son ambos `0`; devuelve `false` sin acceder al arreglo.

## Errores comunes

- **Leer antes de validar el índice:** si se evalúa `arreglo[indice]` antes de comprobar el límite, al llegar a `indice == arreglo.length` se intenta acceder fuera del arreglo y Java lanza `ArrayIndexOutOfBoundsException`. La implementación evita ese error al comprobar primero el caso base de fin.
- **Usar `indice <= arreglo.length`:** el índice `arreglo.length` ya está fuera de las posiciones válidas. La última posición válida es `arreglo.length - 1`; por eso el caso de fin se reconoce con `indice == arreglo.length`.
- **No incrementar el índice:** volver a llamar con el mismo índice repetiría la misma comprobación para siempre y la recursión no avanzaría hacia el caso base.
- **Incrementar más de uno:** si se usara `indice + 2`, podrían omitirse elementos, incluida una coincidencia en una posición saltada.
- **Confundir índice con valor:** `indice` representa una posición; el elemento que se compara es `arreglo[indice]`.
- **Pasar un índice inicial inválido:** el flujo de `main` empieza en `0` y las llamadas avanzan de a uno, por lo que llegan exactamente a `arreglo.length`. Como la función compara con `==`, una llamada directa con un índice mayor que la longitud no activaría ese caso base y podría terminar en `ArrayIndexOutOfBoundsException`.
- **Pasar `null`:** el método supone que el arreglo no es nulo; consultar `arreglo.length` en ese caso produciría `NullPointerException`.

En resumen, cada llamada revisa una posición. Si no encuentra el valor, deja pendiente su resultado y delega la búsqueda a la siguiente posición. Uno de los dos casos base termina el proceso; luego el resultado vuelve por la pila hasta `main`.