# Analisis tecnico: comparar dos vectores

## Objetivo

Definir los requisitos para redactar un prompt dirigido a OpenCode. El prompt solicitara codigo Java que determine si dos vectores son iguales y explique los casos mejor, peor y promedio, destacando que la comparacion puede terminar apenas se detecta una diferencia.

## Supuestos adoptados

- Los vectores se representaran como arreglos Java `int[]`.
- Dos vectores son iguales si tienen la misma longitud y los mismos valores en cada posicion correspondiente. El orden importa.
- El metodo reutilizable devolvera `boolean`: `true` si los arreglos son iguales y `false` si difieren.
- Una referencia `null` en cualquiera de los parametros se rechazara con `IllegalArgumentException`.
- Dos arreglos vacios son iguales. Si las longitudes difieren, los arreglos no son iguales y se puede devolver `false` sin comparar elementos.
- Para longitudes iguales, se compararan los elementos desde el indice cero y el metodo retornara `false` en la primera diferencia. Si todas las posiciones coinciden, retornara `true`.
- `main` usara arreglos fijos para mostrar al menos un par igual y otro diferente. No solicitara datos por consola.
- La solucion no modificara ni ordenara los arreglos, ni intentara determinar si ambos parametros apuntan al mismo objeto antes de comparar el contenido.

## Estrategia tecnica

Implementar un metodo reutilizable, por ejemplo `sonIguales(int[] primero, int[] segundo)`:

1. Validar que ninguna referencia sea `null`.
2. Comparar las longitudes. Si son distintas, devolver `false` de inmediato.
3. Recorrer los indices desde `0` hasta `length - 1`.
4. Si los elementos correspondientes son distintos, devolver `false` inmediatamente.
5. Si termina el recorrido sin diferencias, devolver `true`.

La comparacion debe ser elemento a elemento. En Java, aplicar `==` directamente a dos arreglos compararia si son la misma referencia, no si contienen los mismos valores. Para esta implementacion manual, se compara cada entero en las posiciones correspondientes.

## Complejidad esperada

Sea `n` la longitud comun, cuando ambas longitudes coinciden:

- **Mejor caso: `O(1)`** si la primera posicion comparada ya difiere. Tambien se obtiene tiempo constante si las longitudes difieren, pues no se inspeccionan elementos.
- **Peor caso: `O(n)`** si los arreglos son iguales, o si su unica diferencia esta en la ultima posicion. Se comparan todas las posiciones.
- **Caso promedio: `O(n)`** bajo el supuesto de que, cuando existe una diferencia, su primera posicion de aparicion se distribuye uniformemente entre las `n` posiciones. En ese modelo se realizan alrededor de `(n + 1) / 2` comparaciones. Si los arreglos son iguales, se realizan exactamente `n`; por lo tanto, el promedio real depende de la proporcion de pares iguales y de la distribucion de la primera diferencia.
- **Espacio auxiliar: `O(1)`**. El algoritmo solo necesita el indice del recorrido; no crea copias ni estructuras auxiliares.

Si ambas longitudes son cero, se devuelve `true` despues de validar las referencias y comparar longitudes; no se ejecuta el bucle.

## Flujo esperado de la demostracion

1. Crear dos arreglos distintos pero con los mismos valores y en el mismo orden, para demostrar que igualdad de contenido no requiere identidad de referencia.
2. Crear dos arreglos con una diferencia, idealmente en una posicion temprana, para ilustrar la terminacion anticipada.
3. Opcionalmente mostrar longitudes diferentes y arreglos vacios para ilustrar esos casos limite.
4. Imprimir el resultado booleano de cada comparacion.

La demostracion no debe presentarse como benchmark. Su objetivo es hacer visible el comportamiento y verificar ejemplos representativos.

## Alcance del prompt para OpenCode

El prompt debera solicitar una respuesta organizada que:

1. Explique el criterio de igualdad por contenido y orden, incluyendo la comprobacion previa de longitudes.
2. Presente codigo Java comentado con un metodo reutilizable para `int[]` y un `main` con datos fijos.
3. Valide referencias `null`, acepte dos arreglos vacios como iguales y retorne `false` para longitudes distintas.
4. Compare los elementos desde el inicio y finalice en la primera diferencia.
5. Analice los casos mejor, peor y promedio, explicitando el supuesto utilizado para hablar del promedio, e indique espacio `O(1)`.
6. Explique por que `==` aplicado directamente a arreglos no compara su contenido y por que el metodo manual evita modificar las entradas.

No se solicita entrada por consola, devolver el indice de la primera diferencia, comparar arreglos multidimensionales, ordenar los datos ni crear una suite de pruebas automatizadas.

## Criterios de aceptacion

- El metodo devuelve `true` solo cuando las longitudes y todos los elementos en las posiciones correspondientes coinciden.
- Si las longitudes difieren, devuelve `false` sin recorrer elementos.
- Si las longitudes coinciden, termina en la primera diferencia; si no encuentra ninguna, devuelve `true` despues de recorrer el arreglo completo.
- Rechaza `null` y considera iguales dos arreglos vacios.
- `main` presenta al menos un par igual en contenido y otro par diferente.
- La explicacion distingue el caso mejor `O(1)`, el peor `O(n)` y el promedio `O(n)` bajo un supuesto declarado.
- La implementacion no modifica ni ordena los arreglos.
