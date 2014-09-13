BlackJack
==========
BlackJack es una práctica final para la materia *Fundamentos de Programación* de la carrera Ingeniería de Sistemas en EAFIT. El juego fue completamente desarrollado en **Java** y en el semestre 2012-1.

#Manual de usuario
---

Juego
---
####¿En qué consiste?
El blackjack es un juego de cartas que consiste en obtener 21 puntos mediante la suma de los valores de las cartas. Si bien existe un croupier (banca), y básicamente hay que conseguir mayor puntuación que él sin sobrepasar 21 puntos.
Si se consigue sumar 21 puntos con tan sólo dos cartas, se gana automáticamente, y a esto se le denomina BlackJack.

####Croupier (banca)
El croupier tiene distintas funciones en el juego, repartir es una de ellas, éste debe repartir dos cartas iniciales del mazo a cada jugador y a él mismo (el croupier debe tener sólo la primera carta tapada), y cada vez que un jugador pida otra carta, éste debe entregarla.

Cada uno de los jugadores debe obtener mayor suma que el croupier, para esto, una vez todos los jugadores estén plantados, el croupier debe cumplir la siguiente metodología:
Si su suma es menor que 17, debe tomar una carta obligatoriamente.
Si su suma es mayor o igual que 17, debe parar de tomar cartas y debe plantarse.

####Valores de las cartas

Las cartas numéricas suman la cantidad de puntos que les corresponde a cada carta, por ejemplo, el 2 suma 2 puntos, el 10 suma 10 puntos; las figuras J, Q y K suman 10 puntos, y el As vale 1 u 11 según convenga al jugador, es decir, si sumando 11 hace que el total de las cartas exceda 21, entonces suma 1.

Desarrollo
---
Una vez el croupier reparta las cartas iniciales, cada jugador tiene la opción de plantarse o pedir otra carta, si el jugador excede 21 perderá automáticamente.

Al finalizar la ronda de cada jugador, se determinará quién le ganó al croupier y se obtendrán los puntos necesarios.

#Capturas de pantalla
---

* Ingreso de jugadores

![alt img](https://github.com/svanegas/first_blackjack/blob/master/screenshots/players.png)

* Pantalla principal del juego

![alt img](https://github.com/svanegas/first_blackjack/blob/master/screenshots/game.png)

* Ronda normal

![alt img](https://github.com/svanegas/first_blackjack/blob/master/screenshots/round.png)

* Final de la ronda

![alt img](https://github.com/svanegas/first_blackjack/blob/master/screenshots/new_round.png)
