# Explicación: cola de impresión

## Spooling: el intermediario entre programas e impresora

Spooling significa *Simultaneous Peripheral Operations On-Line*. La idea es desacoplar a quien produce un trabajo (el usuario, una aplicación o el procesador) del dispositivo que lo atiende (la impresora), que suele operar mucho más despacio.

Cuando varias aplicaciones envían documentos, el sistema de impresión los registra en una cola. Las aplicaciones pueden continuar mientras el spooler conserva los trabajos pendientes y la impresora procesa uno a la vez. En sistemas reales, el spooler suele guardar los documentos en almacenamiento persistente para tolerar trabajos grandes o mantenerlos mientras la impresora está ocupada. La cola enlazada de este ejercicio es una representación en memoria simplificada de ese orden; no implementa el almacenamiento completo del sistema operativo.

FIFO asegura que el trabajo que llegó primero se atienda primero. En el ejemplo, `informe.pdf` de Ana va antes que `factura.pdf` de Luis y `presentacion.pdf` de Marta. La impresora no adelanta el último documento solo porque haya llegado después.

## Una cola genérica no inspecciona los detalles del trabajo

`ColaEnlazada<TrabajoImpresion>` almacena referencias a objetos `TrabajoImpresion`. Cada `Nodo<T>` tiene el dato genérico y el enlace al siguiente nodo; `ColaEnlazada<T>` solo administra `head` y `tail`.

`head` apunta al próximo trabajo que se retirará; `tail`, al último trabajo encolado. Al agregar un trabajo, la cola crea un nodo, enlaza el nodo anterior de `tail` con él y mueve `tail`. No necesita consultar `nombreArchivo`, abrir el documento ni contar sus páginas. Para la estructura, `TrabajoImpresion` es una referencia dentro del nodo.

Por eso el costo de enlazar un trabajo es $O(1)$ y no depende de que represente 10 o 1000 páginas. La cantidad de páginas puede afectar cuánto tarda la impresora en procesarlo; no cambia la cantidad de cambios de referencias que hace `encolar`. En un spooler real, copiar o guardar el archivo sí puede costar según su tamaño, pero esa tarea es distinta de insertarlo en la cola enlazada.

## Qué significa $O(1)$ en `encolar`

Si la cola no está vacía, el método realiza dos cambios principales:

```text
tail.siguiente = nodoNuevo
tail = nodoNuevo
```

No recorre los trabajos anteriores porque `tail` ya señala directamente el último. La cola tampoco copia el objeto `TrabajoImpresion` ni sus páginas: solo guarda su referencia. Por eso agregar el elemento toma tiempo constante respecto del número de trabajos pendientes.

Al imprimir la cola, en cambio, se sigue cada enlace desde `head` hasta `null`; esa operación visita todos los nodos y cuesta $O(n)$. La simulación de impresión también es independiente: `desencolar()` retira en $O(1)$ el objeto del frente, y el programa muestra sus datos como trabajo atendido.

## Recorrido de la prueba

Al encolar los tres objetos, la cadena queda:

```text
head -> informe.pdf -> factura.pdf -> presentacion.pdf <- tail
```

`consultarFrente()` devuelve `informe.pdf` sin retirarlo. Después `desencolar()` devuelve ese mismo trabajo y mueve `head` a `factura.pdf`:

```text
head -> factura.pdf -> presentacion.pdf <- tail
```

Así la prueba confirma que el primero que llegó es el primero que sale y que los trabajos pendientes mantienen su orden.
