Quiero implementar en Java una función recursiva que calcule una multiplicación sin usar el operador *.
Este problema puede resolverse recursivamente porque la multiplicación de dos números (a y b) se puede representar como sumas repetidas, sumando 'a' un total de 'b' veces.
El caso base es cuando uno de los factores (por ejemplo, el parámetro b) vale 0, porque cualquier número multiplicado por 0 es 0, y esto detiene la recursión.
El caso recursivo es a + multiplicación(a, b-1), porque en cada llamada el parámetro 'b' se reduce restándole 1 para acercarse al caso base de 0.
La función debe devolver un número entero con el resultado del producto.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos: caso 1: a = 5, b = 0; caso 2: a = 5, b = 1; caso 3: a = 4, b = 3.
