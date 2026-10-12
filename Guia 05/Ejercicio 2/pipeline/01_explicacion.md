# Explicacion de `BusquedaSecuencialVector`

El programa busca un entero dentro de un arreglo `int[]` que no necesariamente esta ordenado. Informa el indice de la primera coincidencia y cuantas posiciones inspecciono. Si no encuentra el valor, el indice guardado en el resultado es `-1` y el contador indica que se reviso todo el arreglo.

La clase separa dos responsabilidades: `buscar` contiene la logica reutilizable de busqueda, y `main` se encarga de pedir y mostrar datos por consola.

## Recorrido paso a paso

1. `main` crea un `Scanner` conectado a `System.in` dentro de un bloque `try` con recursos.
2. `leerTamanoPositivo` solicita un tamano. Si el texto no representa un entero, `leerEntero` informa el error y vuelve a pedirlo. Si el numero es cero o negativo, se muestra otro mensaje y se repite la solicitud.
3. Con el tamano valido, `main` crea `new int[tamano]` y lee cada elemento. El texto del prompt indica la posicion para una persona usando numeracion desde uno, aunque el arreglo internamente usa indices desde cero.
4. El programa solicita el valor objetivo y llama a `buscar(numeros, objetivo)`.
5. `buscar` primero rechaza arreglos `null` o vacios con `IllegalArgumentException`.
6. El bucle visita los indices desde `0` hasta `numeros.length - 1`. En cada iteracion aumenta `posicionesRecorridas` y luego compara el elemento con el objetivo.
7. Si encuentra una coincidencia, devuelve inmediatamente un `ResultadoBusqueda` con ese indice y el contador. Por eso una repeticion del valor mas adelante no reemplaza la primera coincidencia.
8. Si el bucle termina sin coincidencias, devuelve un resultado con indice `-1` y con todas las posiciones contadas.
9. `main` consulta el resultado: imprime el indice si se encontro el valor o un mensaje de ausencia en caso contrario. Siempre imprime el numero de posiciones inspeccionadas.

Por ejemplo, con `[4, 7, 4, 9]` y objetivo `4`, se devuelve indice `0` y conteo `1`: el algoritmo termina tan pronto como compara el primer elemento. Con `[10, 20, 30]` y objetivo `99`, devuelve indice `-1` y conteo `3`.

## Por que se implemento asi

El arreglo no esta ordenado. Por tanto, al mirar un elemento no se puede concluir que los elementos anteriores o posteriores sean mayores o menores que el objetivo. Una busqueda secuencial es directa y correcta: compara los elementos en orden hasta hallar el objetivo o llegar al final.

La busqueda binaria no seria valida para estos datos porque depende de que el arreglo este ordenado para descartar una mitad en cada paso. Ordenar primero cambiaria el orden de los datos y agregaria trabajo; ademas, si se necesitara conservar el indice original, tambien habria que mantener la correspondencia con las posiciones previas.

El contador se incrementa justo antes de cada comparacion. Asi cuenta exactamente los elementos inspeccionados, incluida la coincidencia. Si el objetivo no existe, cada elemento se compara una vez y el contador termina en la longitud del arreglo.

La logica de busqueda es un metodo estatico y separado de la consola. Esto permite llamarlo desde otro metodo o probarlo sin simular preguntas interactivas. `ResultadoBusqueda` agrupa las dos salidas relacionadas en un solo objeto, evitando usar valores paralelos o arreglos temporales sin significado claro.

## Complejidad

Sea `n` la cantidad de elementos del arreglo:

- **Mejor caso: `O(1)`**. El objetivo coincide con el primer elemento; se inspecciona una posicion.
- **Peor caso: `O(n)`**. El objetivo esta al final o no aparece; se inspeccionan hasta `n` posiciones.
- **Caso promedio: `O(n)`** bajo el supuesto de que el objetivo esta presente y su primera aparicion es igualmente probable en cualquiera de las `n` posiciones. En ese modelo se inspeccionan en promedio `(n + 1) / 2` posiciones. Si el objetivo esta ausente, siempre se inspeccionan exactamente `n` posiciones. Sin una distribucion de probabilidad sobre la presencia y ubicacion del objetivo, no existe un promedio unico que combine ambos casos.
- **Espacio auxiliar de `buscar`: `O(1)`**. El metodo solo usa el indice y el contador; el objeto de resultado ocupa espacio constante. El arreglo en si necesita `O(n)` memoria, pero es la entrada almacenada por `main`, no espacio auxiliar del recorrido.

## Conceptos de Java utilizados

- **Arreglo de primitivos (`int[]`)**: almacena enteros en posiciones consecutivas y se accede a ellos con indices desde cero. La propiedad `length` indica el tamano; no es un metodo y se escribe sin parentesis.
- **Clase anidada estatica (`static class`)**: `ResultadoBusqueda` se declara dentro de `BusquedaSecuencialVector` porque representa el resultado de esta operacion. Al ser estatica, no necesita una instancia de la clase externa.
- **Clase `final` y campos `private final`**: una vez construido el resultado, sus dos valores no pueden cambiar. Los campos privados protegen su estado y los getters permiten consultarlo.
- **Constructor privado**: la clase externa puede crear resultados desde `buscar`, pero el resto del codigo no puede construir libremente combinaciones de indice y conteo que contradigan la salida del algoritmo.
- **Metodo estatico**: `buscar` puede invocarse como `BusquedaSecuencialVector.buscar(...)` sin crear primero un objeto de la clase principal.
- **Retorno temprano (`return`)**: al encontrar el objetivo, el metodo termina de inmediato. Esto evita comparar elementos que no pueden cambiar la respuesta de primera coincidencia.
- **Excepciones**: `IllegalArgumentException` comunica que el arreglo no cumple el contrato del metodo. `NumberFormatException` se captura al convertir una linea que no es un entero. Si se termina la entrada, se lanza `IllegalStateException` para no repetir indefinidamente una lectura que ya no puede completarse.
- **Cortocircuito de `||`**: en `numeros == null || numeros.length == 0`, Java no evalua la segunda parte cuando la primera es verdadera. Asi se comprueba `null` sin provocar una `NullPointerException` al leer `length`.
- **Bucle `for`**: controla el indice y recorre el arreglo una vez. `indice++` avanza a la siguiente posicion.
- **Bucle `while (true)`**: permite insistir en la lectura hasta obtener un dato valido. La salida ocurre mediante `return` cuando la conversion y las reglas de validacion se cumplen.
- **`Scanner` y `System.in`**: `Scanner` lee lineas de la entrada estandar. `nextLine()` consume una linea completa y `trim()` elimina espacios externos antes de convertirla.
- **`Integer.parseInt` y `try/catch`**: convierten texto a `int`. Si el texto no es numerico o excede el rango de `int`, se produce `NumberFormatException`, que se captura para mostrar el mensaje y volver a solicitar el valor.
- **Try-with-resources**: el bloque `try (Scanner scanner = ...)` cierra el `Scanner` al terminar, incluso si ocurre una excepcion. Como este scanner envuelve `System.in`, tambien cierra la entrada estandar al finalizar `main`; en esta aplicacion de una sola ejecucion es adecuado.
- **`main`**: `public static void main(String[] args)` es el punto de entrada de la aplicacion. `args` no se usa porque los datos se solicitan interactivamente.
- **Javadoc**: los comentarios `/** ... */` describen las clases y el contrato publico del metodo, incluidos parametros, retorno, excepciones y complejidad.

## Alternativas y mejoras posibles

- **Usar un `record` para el resultado**: en Java 16 o posterior, `ResultadoBusqueda` podria declararse como `record ResultadoBusqueda(int indice, int posicionesRecorridas)`. Reduce codigo repetitivo y genera accesores, aunque el constructor dejaria de ser privado salvo que se definiera un constructor compacto con las validaciones deseadas. La clase actual funciona en versiones anteriores de Java.
- **Agregar un metodo `encontrado()`**: podria expresar `indice >= 0` y evitar repetir esa condicion donde se consume el resultado. Para este programa pequeno, la comparacion actual es facil de entender.
- **Usar un tipo de resultado estandar o una clase generica**: `OptionalInt` puede representar si se encontro un indice, pero no incluye por si solo el contador. Una clase de resultado propia es razonable mientras se necesiten ambos datos.
- **Separar la lectura de consola en otra clase**: en una aplicacion mas grande, la interfaz por consola podria separarse de la logica y el tipo de resultado. Para este ejercicio, la clase unica mantiene el ejemplo sencillo y el metodo de busqueda sigue siendo independiente.
- **Pruebas automatizadas**: se podrian probar coincidencia al inicio, en el medio y al final; valor ausente; duplicados; arreglo de un elemento; y las excepciones para `null` y vacio. Las pruebas unitarias complementarian la demostracion interactiva.
- **Limitar el tamano solicitado**: se valida que sea positivo, pero no se establece un maximo. Un entero positivo muy grande podria causar `OutOfMemoryError` al crear el arreglo. Si el programa se usara fuera del ejercicio, convendria fijar un limite acorde al entorno y validarlo antes de reservar memoria.

## Errores comunes

- **Contar mal las posiciones**: si se incrementa el contador despues de una coincidencia y se retorna antes de ese incremento, el conteo queda una unidad corto. Aqui se incrementa antes de comparar.
- **Continuar despues de hallar el objetivo**: seguir recorriendo puede devolver la ultima coincidencia o contar elementos de mas. El `return` inmediato conserva la primera coincidencia y el conteo solicitado.
- **Usar `-1` como si fuera un indice valido**: los indices validos van de `0` a `length - 1`. En esta solucion `-1` es un indicador especial de ausencia; quien use el metodo debe comprobarlo antes de acceder al arreglo.
- **Confundir indice con posicion humana**: el arreglo usa base cero. El indice `0` es el primer elemento; el texto de entrada enumera elementos comenzando en uno solo para facilitar la conversacion con el usuario.
- **Aplicar busqueda binaria a datos desordenados**: puede omitir una coincidencia porque las comparaciones no permiten descartar mitades sin un orden establecido.
- **Leer un arreglo nulo o vacio antes de validarlo**: usar `numeros.length` o `numeros[0]` sin comprobar la referencia y el contenido puede producir `NullPointerException` o `ArrayIndexOutOfBoundsException`. `buscar` valida ambos casos antes del bucle.
- **No consumir la entrada incorrecta**: con ciertos usos de `Scanner`, repetir `nextInt()` sin descartar el token invalido provoca que el mismo error reaparezca. Esta implementacion lee una linea completa y luego vuelve a solicitarla.
- **Olvidar limites de `int`**: `Integer.parseInt` acepta solo valores representables como `int`; valores fuera del rango se tratan como entrada no valida y se vuelven a pedir.
- **Creer que la validacion de consola reemplaza la del metodo**: aunque `main` siempre construya un arreglo con tamano positivo, `buscar` se puede llamar desde otros lugares. Por eso conserva sus propias validaciones de `null` y arreglo vacio.
