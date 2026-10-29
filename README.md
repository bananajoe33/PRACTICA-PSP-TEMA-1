# Informe de ventas en paralelo

Realiza el informe de ventas de 8 tiendas en paralelo: el padre (SalesReport) lanza un hijo ShopSummary por tienda, recoge sus resultados y compara el tiempo uno a uno con el de todos a la vez.
Incluye además una caja: dos procesos unidos por una tubería, una caja (CashRegister) que emite tickets y un registro (Ledger) que los suma.

## Grupo

| Nombre | Qué ha hecho |
|---|---|
| [Nombre y apellidos] | [Clases, fases o partes del README de las que se ha encargado] |
| Farah Mouaouia Chaarou | CashRegister y Ledger (fase 4, productor y consumidor de la caja en directo) |
| [Nombre y apellidos] | [...] |

## Cómo se ejecuta

[Cómo se compila y qué clases se lanzan, desde la terminal y desde IntelliJ IDEA. Versión de
Java.]

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
| [Informe de las 8 tiendas] | [El total que da el enunciado] | [...] |
| [Una tienda que no existe] | [...] | [...] |

## Tiempos

[Tiempo del informe uno a uno y todos a la vez, en vuestro equipo, con el número de núcleos.
¿Cuánto más rápido va? ¿Por qué no va 8 veces más rápido?]

## Caja en directo

[Las trazas de los dos escenarios y qué demuestran: quién espera en cada uno y por qué.]

## Depuración

[Una captura de IntelliJ IDEA detenido en un punto de ruptura del proceso padre, con la vista
Variables, y qué habéis comprobado con ella. Si habéis encontrado algún error depurando,
contadlo.]
