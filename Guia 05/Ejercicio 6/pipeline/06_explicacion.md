# Explicacion de `CompararVectores`

La clase compara dos arreglos `int[]` por contenido y orden. El metodo `sonIguales` devuelve `true` si tienen la misma longitud y cada elemento ocupa la misma posicion en ambos arreglos. Si las longitudes difieren o encuentra una diferencia, devuelve `false`. Una referencia `null` no es valida y provoca `IllegalArgumentException`.

La comparacion es por contenido, no por identidad: dos arreglos distintos en memoria pueden considerarse iguales si contienen los mismos valores en el mismo orden.

## Funcionamiento paso a paso

1. `main` crea arreglos para demostrar cuatro situaciones: dos arreglos separados con los mismos valores, una diferencia temprana, longitudes distintas y dos arreglos vacios.
2. `mostrarComparacion` imprime una descripcion, representa ambos arreglos como texto y llama a `sonIguales` para obtener el resultado.
3. `sonIguales` comprueba las referencias. Si cualquiera es `null`, lanza `IllegalArgumentException` antes de acceder a `length`.
4. Compara `primero.length` con `segundo.length`. Si los tamaños son diferentes, retorna `false` de inmediato: dos arreglos de longitudes distintas no pueden tener el mismo contenido completo.
5. Si las longitudes coinciden, el bucle compara `primero[indice]` con `segundo[indice]`, avanzando desde la primera posicion.
6. Si encuentra una diferencia, retorna `false` en ese momento. No hace falta revisar las posiciones restantes para saber que los arreglos no son iguales.
7. Si termina el bucle sin diferencias, retorna `true`.

### Ejemplo de diferencia temprana

Se comparan `{3, 7, 8, 4}` y `{3, -1, 8, 4}`:

| Indice | Primer arreglo | Segundo arreglo | Resultado |
| ---: | ---: | ---: | --- |
| 0 | 3 | 3 | Coinciden; continuar. |
| 1 | 7 | -1 | Difieren; retornar `false`. |

Los indices 2 y 3 no se comparan. En cambio, para dos arreglos con los mismos cuatro valores, el metodo comprueba las cuatro posiciones y retorna `true`.

Dos arreglos vacios tienen la misma longitud (`0`). El bucle no se ejecuta y el metodo devuelve `true`, que es coherente con la regla: no existe ninguna posicion donde difieran.

## Por que se implemento asi

Comprobar primero las longitudes permite rechazar pares incompatibles sin hacer comparaciones elemento a elemento. Cuando las longitudes coinciden, el orden de las posiciones forma parte de la igualdad: `{1, 2}` y `{2, 1}` no son iguales.

La salida temprana aprovecha que una sola diferencia basta para responder `false`. En contraste, para devolver `true` es necesario confirmar que todas las posiciones coinciden, por lo que el caso de arreglos iguales recorre toda la entrada.

La comparacion se realiza manualmente para mostrar claramente el algoritmo y su coste. El metodo no modifica los arreglos ni crea copias. Tampoco comprueba si ambos parametros son la misma referencia: aun en ese caso ejecuta las comprobaciones normales de longitud y contenido.

## Complejidad

Sea `n` la longitud comun cuando ambos arreglos tienen igual longitud:

- **Mejor caso: `O(1)`**. Una diferencia en la primera posicion permite retornar tras una sola comparacion. Si las longitudes difieren, tambien se retorna en tiempo constante sin comparar elementos.
- **Peor caso: `O(n)`**. Si son iguales o solo difieren en la ultima posicion, se inspeccionan todas las posiciones.
- **Caso promedio: `O(n)`** bajo el supuesto de que, cuando existe una diferencia, su primera posicion se distribuye uniformemente entre las `n` posiciones. En ese modelo se realizan en promedio aproximadamente `(n + 1) / 2` comparaciones. Si los arreglos son iguales, se inspeccionan las `n` posiciones; el promedio efectivo depende de la proporcion de pares iguales y de donde aparezcan las diferencias.
- **Espacio auxiliar: `O(1)`**. Solo se mantiene el indice del bucle. `Arrays.toString` en `mostrarComparacion` crea una representacion textual para imprimir la demostracion, pero no forma parte del algoritmo `sonIguales` ni cambia su complejidad auxiliar.

## Conceptos de Java utilizados

- **Arreglos primitivos (`int[]`)**: contienen valores enteros y su longitud se consulta con el campo `length`. Las posiciones empiezan en cero.
- **Metodo estatico (`static`)**: `sonIguales` puede invocarse desde `main` sin instanciar `CompararVectores`.
- **Tipo de retorno `boolean`**: comunica uno de dos resultados: igualdad (`true`) o diferencia (`false`).
- **Validacion con `||`**: `primero == null || segundo == null` comprueba ambas referencias. El operador `||` usa cortocircuito: si `primero` ya es `null`, Java no necesita evaluar la segunda expresion para decidir que la condicion es verdadera.
- **Excepcion `IllegalArgumentException`**: expresa que los argumentos no respetan el contrato requerido por el metodo.
- **Comparacion de primitivos**: `primero[indice] != segundo[indice]` compara los valores `int`, no referencias.
- **Bucle `for` y salida temprana**: el indice recorre las posiciones; `return false` termina el metodo al primer desacuerdo.
- **Metodo privado auxiliar**: `mostrarComparacion` organiza la salida de `main` y no expone detalles de presentacion como parte de la API publica.
- **Biblioteca `java.util.Arrays`**: `Arrays.toString` genera texto legible para mostrar el contenido. Si se concatenara directamente un arreglo con una cadena, Java mostraria una representacion de su identidad, no sus elementos.
- **Concatenacion de cadenas**: el operador `+` combina la descripcion, los arreglos convertidos a texto y el resultado booleano.
- **Javadoc**: los comentarios `/** ... */` documentan clase y metodo, parametros, retorno, excepcion y complejidad.

## Mejoras o alternativas posibles

- **Usar `Arrays.equals(primero, segundo)`**: la biblioteca estandar ya compara los elementos de dos arreglos primitivos y devuelve un booleano. Es una alternativa concisa y menos propensa a errores. La version manual resulta mas didactica y hace explicita la salida temprana. La politica para `null` se tendria que definir aparte si se quisiera conservar el contrato actual de lanzar `IllegalArgumentException`.
- **Evitar trabajo si ambas referencias son identicas**: despues de validar `null`, se podria comprobar `primero == segundo` y retornar `true`, ya que un arreglo es igual a si mismo por contenido. La implementacion actual no incorpora esa optimizacion porque el plan pide comparar contenido sin apoyarse en identidad de referencia.
- **Devolver el indice de la primera diferencia**: seria util para diagnostico, pero cambiaria la firma y el contrato de `boolean`.
- **Pruebas automatizadas**: conviene verificar arreglos iguales de referencias distintas, primera diferencia, ultima diferencia, longitudes distintas, dos arreglos vacios y entradas `null`. La salida de `main` es una demostracion, no una suite de pruebas.
- **Generalizar para otros tipos**: para arreglos de objetos, la igualdad depende de `equals` y puede requerir tratar elementos `null`. El algoritmo actual esta limitado a `int[]`, cuyos elementos son primitivos.

## Errores comunes

- **Comparar arreglos directamente con `==`**: en Java, `==` entre arreglos comprueba si ambos nombres apuntan al mismo objeto, no si sus elementos coinciden. Por eso arreglos distintos con el mismo contenido normalmente dan `false` al compararlos directamente.
- **Olvidar comparar las longitudes**: recorrer una cantidad distinta de posiciones o acceder a un indice que no existe puede causar errores y no demuestra que los arreglos completos sean iguales.
- **Comparar valores sin respetar el indice**: igualdad de contenido en este problema requiere que cada valor aparezca en la misma posicion; comprobar solo que contienen los mismos numeros en cualquier orden seria otro problema.
- **No validar `null` antes de leer `length`**: produciria `NullPointerException`. La comprobacion se hace primero para ambos argumentos.
- **Retornar `true` al hallar una pareja coincidente**: una coincidencia aislada no prueba que los arreglos completos sean iguales; deben coincidir todas las posiciones.
- **Seguir comparando despues de una diferencia**: no altera el resultado, pero hace trabajo innecesario. La salida inmediata preserva el mejor caso constante.
- **Suponer que dos arreglos vacios son diferentes por no tener datos**: con la definicion adoptada, tienen igual longitud y ninguna posicion que contradiga la igualdad; el resultado correcto es `true`.
- **Confundir `Arrays.toString` con la comparacion**: ese metodo solo formatea el arreglo para mostrarlo; `sonIguales` es quien decide si los contenidos coinciden.
