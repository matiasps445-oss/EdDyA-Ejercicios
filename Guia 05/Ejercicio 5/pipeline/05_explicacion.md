# Explicacion de `ContarOcurrenciasVector`

El programa cuenta cuantas veces aparece un valor objetivo en un arreglo `int[]`. El metodo reutilizable `contarOcurrencias` devuelve el total como un entero. `main` demuestra el metodo con el objetivo `4`, que aparece varias veces, y con el objetivo `9`, que no aparece.

## Funcionamiento paso a paso

1. `main` crea el arreglo `{4, -2, 4, 7, 4, 0}`.
2. Define `objetivoRepetido` como `4` y llama a `contarOcurrencias(numeros, objetivoRepetido)`.
3. El metodo valida que `numeros` no sea `null`. Si la referencia es nula, lanza `IllegalArgumentException` antes de intentar recorrerla.
4. Inicializa `cantidad` en cero. Este valor representa cuantas coincidencias se han encontrado hasta el momento.
5. El bucle `for-each` toma cada entero del arreglo en orden. Para cada elemento, compara `numero == objetivo`.
6. Si los valores son iguales, incrementa `cantidad` en uno; si no, deja el contador como esta.
7. El recorrido no termina al hallar una coincidencia: debe examinar todo el arreglo porque puede haber mas apariciones despues.
8. Al finalizar, devuelve el contador. En el arreglo de demostracion, `4` aparece en las posiciones `0`, `2` y `4`, por lo que devuelve `3`.
9. `main` imprime el resultado y repite la llamada para `objetivoAusente`, que vale `9`. Como ningun elemento coincide, el metodo devuelve `0`.

### Seguimiento del objetivo `4`

| Elemento revisado | Coincide con `4` | `cantidad` despues de revisar |
| ---: | :---: | ---: |
| 4 | Si | 1 |
| -2 | No | 1 |
| 4 | Si | 2 |
| 7 | No | 2 |
| 4 | Si | 3 |
| 0 | No | 3 |

La salida es:

```text
El valor 4 aparece 3 veces.
El valor 9 aparece 0 veces.
```

## Por que se implemento asi

El objetivo es obtener un total, no solo detectar la existencia de una coincidencia ni devolver una posicion. Una coincidencia al principio no permite saber si el mismo valor vuelve a aparecer mas adelante. Por eso el metodo necesita completar el recorrido y sumar uno por cada igualdad.

El `for-each` expresa directamente que se inspeccionan todos los elementos, sin necesitar el indice. El algoritmo no ordena ni cambia el arreglo: el orden no es necesario para comparar valores y preservar la entrada simplifica el comportamiento.

La validacion del arreglo esta dentro del metodo reutilizable, no solo en `main`. Asi, cualquier otro llamador obtiene el mismo comportamiento definido ante `null`. El arreglo vacio es valido: el bucle no ejecuta ninguna iteracion y el contador inicial, cero, se devuelve tal cual.

## Complejidad

Sea `n` la longitud del arreglo:

- **Tiempo: `O(n)`** en todos los casos no vacios, porque cada elemento debe inspeccionarse para garantizar el total. El mejor caso asintotico tambien es lineal: aunque la primera posicion coincida, el resto puede contener mas coincidencias.
- **Arreglo vacio: `O(1)`**. No hay elementos que revisar y se devuelve cero despues de la validacion.
- **Espacio auxiliar: `O(1)`**. El metodo mantiene el contador y la variable que recibe cada elemento; no reserva otra estructura proporcional al tamano de la entrada.

El contador es `int`, suficiente para este contrato: la cantidad de coincidencias no puede exceder la cantidad de posiciones del arreglo, y Java representa el tamano del arreglo como un `int`.

## Conceptos de Java utilizados

- **Arreglo primitivo (`int[]`)**: almacena enteros y tiene longitud fija. `numeros.length` da el numero de elementos; esta solucion no necesita consultar esa propiedad porque usa `for-each`.
- **Metodo estatico (`static`)**: `contarOcurrencias` se puede invocar desde `main` sin crear un objeto `ContarOcurrenciasVector`.
- **Parametros y valor de retorno**: el metodo recibe el arreglo y el objetivo, y devuelve un `int` que el codigo llamador puede imprimir o reutilizar.
- **Bucle `for-each`**: en `for (int numero : numeros)`, Java asigna cada elemento sucesivamente a `numero`. No se obtiene el indice, pero tampoco hace falta para contar.
- **Condicional `if`**: comprueba la igualdad y decide si se incrementa el contador.
- **Operador `==` para primitivos**: aqui compara los valores numericos de dos `int`. No compara referencias de objetos.
- **Incremento `cantidad++`**: equivale a sumar uno al contador cada vez que se encuentra una coincidencia.
- **Excepcion**: `throw new IllegalArgumentException(...)` comunica que la referencia recibida no cumple el contrato del metodo.
- **Concatenacion de cadenas**: el operador `+` combina texto, objetivo y resultado para construir los mensajes de salida.
- **Metodo `main`**: `public static void main(String[] args)` es el punto de entrada de la aplicacion. `args` no se usa porque los datos se definieron dentro del metodo.
- **Javadoc**: los comentarios `/** ... */` describen clase y metodo, sus parametros, retorno, excepcion y complejidad.

## Mejoras o alternativas posibles

- **Vector ordenado**: si existiera una garantia de orden ascendente, se podria localizar con busquedas binarias el primer y el ultimo indice del objetivo y calcular la cantidad a partir del intervalo. Esa variante podria ser `O(log n)`, pero requiere un contrato distinto y mas codigo; este metodo acepta datos sin ordenar.
- **Entrada por consola**: para una aplicacion interactiva se podria leer el arreglo y el objetivo con `Scanner`. El ejemplo actual usa valores fijos para que sea facil reproducir y comprobar el resultado.
- **Pruebas automatizadas**: se pueden agregar pruebas para arreglo vacio, longitud uno, todos los elementos iguales, varios valores repetidos, objetivo ausente y `null`. La demostracion de `main` no es una suite de pruebas.
- **Contar otros tipos**: una version generica podria contar objetos usando `equals`, pero tendria que definir como tratar elementos `null` y que significa igualdad para el tipo. Para enteros, `int[]` mantiene la solucion sencilla y evita boxing.
- **Devolver mas informacion**: si se solicitaran tambien posiciones o frecuencias de varios valores, podria convenir acumular resultados en otra estructura. Eso cambiaria el alcance y el uso de memoria; no es necesario para devolver un unico conteo.

## Errores comunes

- **Retornar `1` al hallar la primera coincidencia**: eso responde si hay coincidencia o cuenta solo la primera; no calcula el total.
- **Usar `return cantidad` dentro del bucle tras la primera igualdad**: el metodo dejaria de revisar posibles apariciones posteriores. El retorno debe ocurrir despues de finalizar el ciclo.
- **Inicializar `cantidad` en `-1` o incrementarlo en cada iteracion**: el total debe empezar en cero y aumentar unicamente cuando el elemento sea igual al objetivo.
- **Confundir cantidad con indice**: `cantidad` es el numero de coincidencias, no la posicion donde se encuentra un elemento. Si aparece en los indices `0`, `2` y `4`, el resultado es `3`, no `4`.
- **Olvidar validar `null`**: intentar recorrer una referencia nula provocaria `NullPointerException`. La comprobacion ocurre antes del bucle.
- **Tratar el arreglo vacio como error**: en este problema es una entrada valida y el total de apariciones es cero.
- **Modificar u ordenar el arreglo**: no aporta nada para comparar igualdad y puede sorprender a quien llama al metodo.
- **Usar `==` para comparar objetos numericos**: en este codigo ambos valores son primitivos `int`, por lo que la comparacion es numerica. Con objetos como `Integer`, `==` puede comparar identidad; se debe usar `equals` para comparar contenido.
