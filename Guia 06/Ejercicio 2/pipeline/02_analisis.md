Quiero implementar en Java una función recursiva que sume los números desde n hasta 1.
Este problema puede resolverse recursivamente porque la suma de n números, es decir suma(n), puede pensarse como n + suma(n-1), por lo que cada llamada reduce el problema a una versión más pequeña hasta llegar a un caso trivial.
El caso base es cuando n es 0, porque la suma de 0 números es 0 y detiene la ejecución.
El caso recursivo es n + suma(n-1), porque en cada llamada el problema se reduce restando 1 a n, acercándose al caso base de 0.
La función debe devolver un número entero con el resultado de la suma.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos para validar que funciona correctamente: caso 1: n = 0; caso 2: n = 1; caso 3: n = 5.
