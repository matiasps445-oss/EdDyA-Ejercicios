# Explicacion de `BusquedaBinariaVector`

El programa busca un entero en un arreglo ordenado de forma ascendente. Si encuentra el objetivo, devuelve un indice base cero; si no aparece, devuelve `-1`. La busqueda binaria no inspecciona necesariamente todos los elementos: compara el objetivo con el elemento del medio y descarta la mitad del intervalo que ya no puede contenerlo.

La implementacion tiene una API reutilizable, `buscarBinaria(int[] numeros, int objetivo)`, que devuelve el indice sin imprimir mensajes. `main` usa una variante privada que, ademas de hacer la busqueda, muestra cada intervalo y decision para que el algoritmo se pueda seguir paso a paso.

## Funcionamiento paso a paso

1. `main` crea el arreglo `{-8, -3, 0, 4, 9, 15, 22}` y establece `9` como objetivo. El arreglo debe estar ordenado de menor a mayor.
2. Imprime los datos de la demostracion y llama a la version privada de `buscarBinaria` con `mostrarTraza` igual a `true`.
3. El metodo valida que la referencia del arreglo no sea `null`. Si lo es, lanza `IllegalArgumentException`. Un arreglo vacio no es una entrada invalida: se inicializa `fin` en `-1`, por lo que el ciclo no se ejecuta y el metodo devuelve `-1`.
4. Para un arreglo no vacio, `inicio` empieza en `0` y `fin` en el indice de la ultima posicion, `numeros.length - 1`. Esos dos indices delimitan el intervalo donde todavia podria estar el objetivo.
5. Mientras `inicio <= fin`, calcula el indice central con `inicio + (fin - inicio) / 2` y guarda el valor de esa posicion en `valorMedio`.
6. Si la traza esta activa, muestra los limites actuales, el indice central y el valor comparado.
7. Si `valorMedio` es igual al objetivo, devuelve `medio`. La busqueda termina ahi. Cuando hay duplicados, esta version puede devolver cualquiera de las posiciones coincidentes que alcance primero; no promete encontrar la primera ni la ultima.
8. Si `valorMedio` es menor que el objetivo, todos los elementos hasta `medio` son demasiado pequenos porque el arreglo es ascendente. Se descartan y `inicio` pasa a `medio + 1`.
9. Si `valorMedio` es mayor que el objetivo, todos los elementos desde `medio` en adelante son demasiado grandes. Se descartan y `fin` pasa a `medio - 1`.
10. En cada caso sin coincidencia, el intervalo se hace mas pequeno. Si los limites se cruzan, `inicio > fin`: no quedan posiciones posibles, asi que devuelve `-1`.
11. `main` interpreta el resultado y muestra el indice o el mensaje de ausencia.

### Traza del ejemplo incluido

| Iteracion | `inicio` | `fin` | `medio` | `numeros[medio]` | Decision |
| --- | ---: | ---: | ---: | ---: | --- |
| 1 | 0 | 6 | 3 | 4 | `4 < 9`; se descarta la mitad izquierda y `inicio` pasa a 4. |
| 2 | 4 | 6 | 5 | 15 | `15 > 9`; se descarta la mitad derecha y `fin` pasa a 4. |
| 3 | 4 | 4 | 4 | 9 | Coincide; se devuelve el indice 4. |

La salida termina indicando que el valor se encontro en el indice `4`. En total se hicieron tres comparaciones, frente a una busqueda lineal que podria inspeccionar hasta los siete elementos de este ejemplo.

## Por que esta implementacion

La busqueda binaria es apropiada porque el arreglo esta ordenado. Al comparar el elemento central se sabe, por el orden, que un lado completo no puede contener el objetivo. Cada actualizacion descarta el punto medio y una mitad del intervalo, reduciendo aproximadamente a la mitad el trabajo restante.

El metodo es iterativo: los limites se guardan en variables y no se crean llamadas recursivas. Eso hace visible el progreso y mantiene constante la memoria auxiliar. El metodo publico delega en la variante privada con `mostrarTraza` desactivado, para que la logica pueda reutilizarse sin ensuciar la salida de consola. Solo la demostracion activa la traza.

El punto medio se calcula como `inicio + (fin - inicio) / 2`. La formula evita sumar directamente `inicio + fin`, suma que podria desbordar el rango de `int` si ambos indices fueran grandes. En Java, la division entera descarta la parte decimal; por ejemplo, `5 / 2` produce `2`.

## Complejidad

Sea `n` la cantidad de elementos:

- **Mejor caso: `O(1)`**. El primer valor del medio es el objetivo.
- **Promedio: `O(log n)`** bajo el supuesto usual de buscar en un arreglo ordenado y que el proceso descarta aproximadamente la mitad en cada comparacion.
- **Peor caso: `O(log n)`**. Aun si no se encuentra, el intervalo se reduce hasta quedar vacio en un numero de pasos proporcional al logaritmo de `n`.
- **Espacio auxiliar: `O(1)`**. Solo se mantienen unas pocas variables (`inicio`, `fin`, `medio`, `valorMedio` e `iteracion`); no se crea otro arreglo ni se usa recursion.

La busqueda lineal tiene tiempo `O(n)` en promedio y peor caso, porque puede necesitar revisar todos los elementos. La ventaja asintotica de la binaria depende de que los datos ya esten ordenados. Este metodo no ordena ni modifica el arreglo.

## Conceptos de Java utilizados

- **Arreglos de primitivos (`int[]`)**: guardan enteros y usan indices desde cero. `numeros.length` es la cantidad de elementos.
- **Metodos estaticos (`static`)**: tanto `buscarBinaria` como `main` pertenecen a la clase y se pueden llamar sin crear un objeto `BusquedaBinariaVector`.
- **Sobrecarga de metodos**: hay dos metodos llamados `buscarBinaria`, pero con listas de parametros diferentes: el publico recibe el arreglo y el objetivo; el privado recibe ademas un `boolean` que indica si imprime la traza. La version publica delega con `false`; `main` llama con `true`.
- **Modificadores de acceso**: `public` hace que la busqueda principal este disponible para otros codigos; `private` oculta la variante pensada solo para la demostracion.
- **Bucle `while`**: se repite mientras quede un intervalo posible (`inicio <= fin`).
- **Condicionales `if` / `else`**: el primer `if` detecta la coincidencia; el segundo decide que mitad descartar segun la relacion entre `valorMedio` y `objetivo`.
- **Retorno temprano**: `return medio` termina la busqueda en cuanto encuentra el objetivo, sin seguir inspeccionando el arreglo.
- **Excepciones**: `throw new IllegalArgumentException(...)` expresa que `null` incumple el contrato de entrada. No se lanza excepcion por arreglo vacio.
- **Salida formateada**: `System.out.printf` inserta los valores en una plantilla. `%d` representa un entero y `%n` agrega un salto de linea independiente de la plataforma. `System.out.println` imprime las decisiones y resultados.
- **Javadoc**: los comentarios `/** ... */` documentan clase y metodos, incluyendo parametros, retorno, excepciones y complejidad.
- **Punto de entrada `main`**: `public static void main(String[] args)` es el metodo que Java ejecuta al iniciar esta aplicacion. El parametro `args` no se necesita porque los datos del ejemplo estan definidos en el metodo.

## Alternativas y mejoras posibles

- **Mostrar u ocultar la traza con una abstraccion distinta**: el parametro booleano resulta sencillo para este ejercicio. En un programa mas grande se podria separar la logica de la presentacion usando un consumidor de eventos, un callback o una clase de salida; eso evita que un booleano controle dos comportamientos.
- **Validar que el arreglo este ordenado**: el metodo documenta el requisito, pero no lo comprueba. Una validacion completa requiere recorrer el arreglo, lo que agrega `O(n)` al costo y elimina la ventaja asintotica de hacer solo la busqueda. Es preferible que el llamador garantice el orden o validar al construir los datos.
- **Buscar la primera coincidencia**: si se necesitara la primera posicion entre duplicados, al encontrar el objetivo se podria guardar el indice y continuar buscando hacia la izquierda. Esta variante cambia el algoritmo y debe formar parte del requisito antes de implementarse.
- **Usar una implementacion de biblioteca**: `Arrays.binarySearch` de `java.util.Arrays` ofrece busqueda binaria para arreglos ordenados. La implementacion manual es mas extensa, pero aqui permite observar cada limite y decision, que es el objetivo didactico.
- **Pruebas automatizadas**: convendria cubrir arreglo vacio, un elemento, objetivo en los extremos, valor ausente, negativos y duplicados; tambien comprobar la excepcion para `null`. La demostracion de `main` no reemplaza pruebas automatizadas.

## Errores comunes

- **Usar la busqueda binaria en datos desordenados**: la comparacion central ya no permite saber que mitad descartar y se pueden pasar por alto coincidencias.
- **Usar `inicio < fin` como condicion**: cuando queda una sola posicion, los limites son iguales y el ciclo terminaria sin revisarla. `inicio <= fin` incluye correctamente ese ultimo candidato.
- **No descartar `medio`**: si se actualiza `inicio = medio` o `fin = medio`, el intervalo puede no cambiar y el ciclo quedar atrapado. `medio + 1` y `medio - 1` garantizan progreso.
- **Actualizar el limite en la direccion incorrecta**: si `numeros[medio] < objetivo`, hay que buscar a la derecha; si es mayor, a la izquierda.
- **Confundir indice y valor**: `medio` es una posicion, mientras que `numeros[medio]` es el valor almacenado ahi. El metodo devuelve el indice, no el valor.
- **Olvidar que `-1` es un resultado especial**: no es un indice valido. Antes de usar el resultado para acceder al arreglo, hay que comprobar que no sea `-1`.
- **Calcular el medio con `(inicio + fin) / 2`**: la suma puede desbordar para indices grandes. La expresion usada reduce ese riesgo.
- **Interpretar `-1` como el resultado de un arreglo vacio sin seguir el flujo**: en este caso es correcto porque `fin` comienza en `-1` y el bucle no se ejecuta. Para `null`, en cambio, se lanza una excepcion antes de consultar `length`.
- **Suponer que siempre devuelve el primer duplicado**: el retorno inmediato encuentra una coincidencia, no necesariamente la primera. Para esa garantia se necesita una variante especifica.
