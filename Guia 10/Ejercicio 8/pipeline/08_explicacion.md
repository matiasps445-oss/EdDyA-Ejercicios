# Explicación: invertir una palabra con una pila

## Por qué LIFO invierte cualquier secuencia

LIFO significa “último en entrar, primero en salir”. Una pila conserva el elemento más reciente en el tope, por lo que el orden de salida es el inverso del orden de entrada, sea que se almacenen letras, números o acciones.

Con la palabra `abc`, la primera fase apila de izquierda a derecha:

```text
apilar 'a'   tope -> a
apilar 'b'   tope -> b -> a
apilar 'c'   tope -> c -> b -> a
```

En la segunda fase cada `desapilar()` devuelve el tope: primero `c`, después `b` y finalmente `a`. Al agregarlos en ese orden, el resultado queda `cba`. No hace falta calcular posiciones: el propio orden LIFO produce la inversión.

## Las dos fases del método

`invertirPalabra` crea una `PilaEnlazada<Character>`. Primero recorre la palabra con un índice desde `0` hasta `length - 1` y apila cada carácter. Al acabar, el último carácter original está en el tope.

Después crea un `StringBuilder` y, mientras la pila no está vacía, desapila un carácter y lo añade al resultado. Al terminar, todos los caracteres fueron retirados y el método devuelve `resultado.toString()`.

La pila del archivo se construye con nodos enlazados: cada nodo guarda un `Character` y una referencia al nodo que estaba debajo. `head` funciona como tope; tanto apilar como desapilar modifican ese extremo en tiempo constante.

## Eficiencia temporal: $O(n)$

Si la palabra tiene $n$ caracteres:

- La primera fase visita y apila cada carácter una vez: $n$ operaciones.
- La segunda fase desapila cada carácter una vez y lo añade al `StringBuilder`: otras $n$ operaciones.
- La conversión final a `String` recorre el resultado una vez.

El trabajo total crece proporcionalmente a la longitud de la palabra, por lo que el tiempo es $O(n)$. El espacio adicional también es $O(n)$: la pila conserva hasta $n$ nodos y el resultado contiene $n$ caracteres.

## Por qué se usa `StringBuilder`

Los objetos `String` son inmutables: una concatenación no modifica una cadena existente, sino que crea otra con el contenido combinado. Si dentro del ciclo se hiciera `resultado = resultado + caracter`, en cada paso habría que copiar el prefijo ya construido. Para $n$ caracteres, el trabajo de esas copias puede sumar aproximadamente $1 + 2 + \cdots + n$, es decir, crecer como $O(n^2)$.

`StringBuilder` es mutable y añade cada carácter al mismo acumulador. Su capacidad puede crecer cuando hace falta y, en promedio, cada `append` tiene costo amortizado constante; por eso formar el resultado completo se mantiene lineal. El código usa `new StringBuilder(palabra.length())` para reservar desde el inicio una capacidad adecuada.

## Resultado de la prueba

Con `palabraOriginal = "algoritmo"`, el método apila las letras en ese orden y luego las desapila desde la última. El programa muestra:

```text
Palabra original: algoritmo
Palabra invertida: omtirogla
```

El resultado correcto, sin espacios, es `omtirogla`.
