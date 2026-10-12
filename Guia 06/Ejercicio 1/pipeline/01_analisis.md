Quiero implementar en Java una función recursiva que calcule el factorial de un número.
Este problema puede resolverse recursivamente porque el factorial de un número n es n multiplicado por el factorial de n-1, por lo que cada llamada reduce el tamaño del problema hasta llegar a un caso trivial.
El caso base es cuando n vale 0 o 1, porque por definición matemática, el factorial de 0 y 1 es 1, y en ese punto la recursión debe detenerse.
El caso recursivo es n * factorial(n-1), porque en cada llamada el problema se reduce restando 1 a n y acercándose al caso base.
La función debe devolver un número entero con el resultado del factorial calculado.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos: caso 1: n = 0; caso 2: n = 1; caso 3: n = 5.
