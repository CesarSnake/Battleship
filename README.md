# Battleship single player

## Guerra de vaixells

Joc simple de guerra de vaixells on es genera un tauler per consola i s'ha d'esbrinar en quina posició estan els vaixells en el mínim nombre de torns.
Una vegada el tauler està generat, l'usuari hi ha d'introduir coordenada i el joc visualitzara si ha donat a un vaixell, passarà torn i ha de continuar buscant fins a enfonsar tots els vaixells del tauler.
El tauler és una graella de 10x10 amb els números (1-10) a la primera fila i lletres (A-J) a la primera columna per a identificar les caselles, sent A1 la casella a la cantonada superior esquerra i J10 la casella a la cantonada inferior dreta.

### Objectiu del joc
Afonar els 5 vaixells que hi ha en el tauler.
- **Portaavions**: ocupa 5 caselles
- **Blindat**: ocupa 4 caselles
- **Creuer**: ocupa 3 caselles
- **Destructors**: ocupa 2 caselles
- **Submarins**: ocupa 1 casella

### Simbologia per pantalla:
```
· Casella oculta
+ Aigua
/ Vaixell tocat
X Vaixell enfonsat
```
### Condició de victòria
El joc finalitza una vegada el jugador a trobat tots els vaixells.
El joc mostrarà la seua puntuació d'acord amb els torns que han transcorregut fins a arribar a la victòria.

### Condició de derrota
Al ser d'un jugador sol, no hi haurà condició de derrota.



## Disseny

El joc a l'iniciar-se ha de mostrar un menú principal amb les opcions:
- Start Game: inicia el joc.
- How to play: mostra una pantalla amb les regles del joc
- Exit Game: tanca el programa.

```
-------------------------
BattleShip single player
-------------------------
1. Start Game.
2. How to Play.
3. Exit Game.
```

### Start Game:
El joc ha de crear un taulell de 10x10 e introduir **aleatòriament** els 5 tipus de vaixells diferents:
- **Portaavions** de 5 caselles
- **Blindat** de 4 caselles
- **Creuer** de 3 caselles
- **Destructors** de  2 caselles
- **Submarins** de 1 casella

En la pantalla de joc, apareixerà el torn actual i el taulell amb totes les caselles ocultes amb el símbol '·', baix del taulell apareixerà el missatge "Attack to coordinate".
L'usuari haurà d'introduir coordenada i polsar enter.
El joc incrementara torn i mostrara de nou el taulell modificant la coordenada introduïda acorde a l'atac del jugador.
La coordenada introduïda modificara la casella amb el símbol:
```
~ Si la casella és aigua (no hi ha cap vaixell).
/ Si ha tocat un vaixell (coordenada amb una part d'un vaixell).
X Si el vaixell ha sigut tocat i enfonsat (coordenada amb una part d'un vaixell amb les altres parts tocades, aquestes es modificaran també amb aquest símbol)
```

El joc acaba quan el jugador enfonsa tots els vaixells, el programa mostrara una pantalla amb el missatge "You won in XX turns".

#### Game Screen:
```
Turn: 0
# 1 2 3 4 5 6 7 8 9 10
A · · · · · · · · · ·
B · · · · · · · · · ·
C · · · · · · · · · ·
D · · · · · · · · · ·
E · · · · · · · · · ·
F · · · · · · · · · ·
G · · · · · · · · · ·
H · · · · · · · · · ·
I · · · · · · · · · ·
J · · · · · · · · · ·
Atack to coordinate:
```

### How to play
El joc mostrarà la següent pantalla:
```
Rules:
The objective of the game is discover where are the ships.
You must atack the ships and sink all of them to win.
To atack a ship you must insert a coordinate and pres enter.

Simbols:
· unknown, place not discovered.
~ water, you miss your shot.
/ hit, you hit one ship but it is not sink
X sink, you sink the ship.

Ships in the game:
Carrier, which has 5 holes
Battleship wich has 4 holes
Cruiser, which has 3 holes
Destroyer, which has 2 holes
Submarine, which has 1 holes

Press any key to back main menu
```



## Funcionalitats
- El programa ha de mostrar el menú en iniciar-se.
- L'usuari podrà elegir iniciar el joc, mostrar regles o eixir del joc.
- El joc sempre mostrara el torn, l'estat actual del taulell i esperarà que l'usuari introduïsca coordenada.
- A l'introduir coordenada, incrementarà torn, actualitzarà el taulell i esperarà següent coordenada. Si la coordenada introduïda és incorrecta el programa llançarà una excepció i preguntara de nou.
- El joc finalitza quan el jugador introduïsca totes les coordenades on hi ha un vaixell.

### Classes

#### Coordinates
- La Coordenada estarà formada per una lletra majúscula de l'A fins a la J i un número de l'1 al 10.
- Altre tipus de símbol, llançarà una excepció.

#### Ship & sons (Carrier, Battleship, Cruiser, Destroyer, Submarine)
- Ship actua com a classe pare per als tipus de vaixells del joc, per si mateixa es buida i no jugable al taulell
- Un vaixell pot rebre tants atacs com tantes coordenades ocupe, si rep un atac en una coordenada lliure, aquesta passa a ser atacada.
- Si un vaixell rep un altre atac a una coordenada que ja ha sigut atacada, llançarà una excepció.
- El vaixell és enfonsat quan totes les coordenades reben un atac.

#### ShipFactory
- És una classe que retorna el tipus de vaixell desitjat.

#### Cell
- La casella estarà formada per una coordenada i un estat (aigua, tocat o enfonsat).
- La casella ha de retornar una llista de les seues caselles veïnes d'acord a la coordenada.
- Cada vaixell del joc estarà associat a X caselles, per tanta cada casella podra tant ser associada, com retornar a quin vaixell és associada.
