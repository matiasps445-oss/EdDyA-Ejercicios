# Explicación: contar ocurrencias de un valor

## Diferencia entre contar y buscar

Los dos métodos recorren nodos con una referencia auxiliar y usan la condición `actual != null`, pero el trabajo que realizan al encontrar una coincidencia es distinto.

En `buscar(int dato)`, el código devuelve `true` inmediatamente cuando encuentra el dato:

```java
while (actual != null) {
    if (actual.getDato() == dato) {
        return true;
    }
    actual = actual.getSiguiente();
}
return false;
```

Ese `return` termina el método y el recorrido. Si el valor está en la cabeza, `buscar` puede terminar en el primer paso; si no aparece, debe llegar hasta `null` para devolver `false`.

En `contarOcurrencias(int dato)`, una coincidencia solo incrementa `contador`. El método avanza al nodo siguiente tanto si coincidió como si no:

```java
while (actual != null) {
    if (actual.getDato() == dato) {
        contador++;
    }
    actual = actual.getSiguiente();
}
return contador;
```

El `return contador` está después del ciclo, no dentro de la condición de coincidencia. De ese modo, las apariciones repetidas más adelante también se cuentan.

## Por qué el conteo siempre recorre toda la lista

Para asegurar que no quedan más apariciones, el método examina cada nodo hasta que `actual` sea `null`. En cualquier lista no vacía de $n$ nodos, visita los $n$ nodos incluso si el primero coincide o el valor no aparece. Su costo es $\Theta(n)$ (y, por tanto, el peor caso es $O(n)$). `buscar`, en cambio, puede terminar antes si encuentra el dato al principio; su peor caso también es $O(n)$, pero su caso favorable puede ser $O(1)$.

## Lista vacía: resultado cero sin excepción

Al empezar, `contador` vale `0` y `actual` recibe el valor de `head`. Si la lista está vacía, `head == null`, por lo que la condición `actual != null` es falsa desde la primera comprobación. El cuerpo no se ejecuta: el método no intenta llamar a `getDato()` ni a `getSiguiente()` sobre `null`.

El ciclo termina normalmente y el método devuelve el contador que no cambió: `0`. No hace falta un caso especial para la lista vacía ni se produce un `NullPointerException`.

## Seguimiento del ejemplo

Para `10 -> 20 -> 10 -> 30 -> 10`, al contar el valor `10`:

| Nodo visitado | ¿Coincide con `10`? | `contador` después de revisar |
|---|---:|---:|
| `10` | Sí | `1` |
| `20` | No | `1` |
| `10` | Sí | `2` |
| `30` | No | `2` |
| `10` | Sí | `3` |

Tras visitar el último nodo, `actual` pasa a `null`, el ciclo termina y el método devuelve `3`. La operación es de solo lectura: no cambia los datos ni los enlaces de la lista.
