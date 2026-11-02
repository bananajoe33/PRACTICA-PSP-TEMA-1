# Informe de ventas en paralelo

Realiza el informe de ventas de 8 tiendas en paralelo: el padre (SalesReport) lanza un hijo ShopSummary por tienda, recoge sus resultados y compara el tiempo uno a uno con el de todos a la vez.
Incluye además una caja: dos procesos unidos por una tubería, una caja (CashRegister) que emite tickets y un registro (Ledger) que los suma.

## Grupo

| Nombre | Qué ha hecho |
|---|---|
| Adrián De Francisco  | SalesReport (Fases 1,2,3,5 lanzando todos a la vez y uno a uno) |
| Farah Mouaouia Chaarou | CashRegister y Ledger (fase 4, productor y consumidor de la caja en directo) |
| [Carlos Hurtado Cortes] | [LiveCashRegister( fase 4, unir 2 procesos por una tubería y lanzar los 2 escenarios planteados] |

## Cómo se ejecuta

![Captura de pantalla 2026-11-01 154422.png](../Captura%20de%20pantalla%202026-11-01%20154422.png)

![Captura de pantalla 2026-11-01 161038.png](../Captura%20de%20pantalla%202026-11-01%20161038.png)

![Captura de pantalla 2026-11-01 161746.png](../Captura%20de%20pantalla%202026-11-01%20161746.png)
## Procesos

| Clase | Papel | Entrada | Salida | Códigos de salida |
|---|---|---|---|---|
| [`SalesReport`] | [Padre / hijo; qué hace] | [Argumentos, entrada estándar...] | [Salida estándar, salida de error...] | [0: ...] |
| `CashRegister` | Hijo, productor: emite tickets | Argumentos: número de tickets y pausa en ms | Estándar: un ticket por línea, número;céntimos. Error: hora de cada ticket | 0: bien; 1: argumentos que faltan o no son números |
| `Ledger` | Hijo, consumidor: suma los tickets | Argumento: pausa en ms. Entrada estándar: los tickets | Estándar: número de tickets y total en euros. Error: hora de cada lectura | 0: bien; 1: argumento que falta o no es un número |

- LiveCashRegister lanza CashRegister y Ledger con startPipeline(): la salida estándar de la caja es la entrada estándar del registro

## Pruebas realizadas

| Prueba | Resultado esperado | Resultado obtenido |
|---|---|---|
| Informe de las 8 tiendas (`SHOPS = 8`) | 8 líneas, una por tienda, con su PID. El total que da el enunciado. Tiendas con problemas: 0 | [...] |
| Una tienda que no existe (`SHOPS = 9`) | La tienda 9 falla con un código de salida distinto de 0 y se escribe su error. Tiendas con problemas: 1. El total es el mismo que con 8 tiendas | El hijo escribe "No existe la tienda 9: hay de la 1 a la 8" y termina con código 1. El padre escribe "Tienda 9 (PID 12793): ha fallado con código 1". Tiendas con problemas: 1. Total: 1.200.014.349 unidades y 3.381.671.938,70 €, la suma de las tiendas 1 a 8. Tiempo: 1341 ms |
| Un hijo que no responde a tiempo (`TIMEOUT = 0`) | Todos los hijos se terminan por tiempo. Tiendas con problemas: 8. Total: 0 | [...] |
| Uno a uno frente a todos a la vez | El mismo total en los dos. El tiempo de todos a la vez es menor | [...] |

![Captura de pantalla 2026-11-01 161810.png](../Captura%20de%20pantalla%202026-11-01%20161810.png)
## Tiempos

## Tiempos del informe

Los informes de las 8 tiendas (uno a uno y todos a la vez), con sus tiempos y el número de núcleos del equipo, se muestran en las capturas de pantalla adjuntas.

## ¿Por qué no va 8 veces más rápido?

Porque solo pueden ejecutarse de verdad a la vez tantos procesos como núcleos tiene el equipo, y cada hijo tiene que arrancar su propia JVM. Además, el padre lanza los hijos y lee sus resultados uno tras otro, y esa parte no se paraleliza. Por eso la mejora real queda por debajo del máximo teórico de 8.

## Caja en directo

[Las trazas de los dos escenarios y qué demuestran: quién espera en cada uno y por qué.]

## Depuración

![Captura de pantalla 2026-11-01 162302.png](../Captura%20de%20pantalla%202026-11-01%20162302.png)
