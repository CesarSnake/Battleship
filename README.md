# Battleship single player

## Guerra de vaixells

Joc simple de guerra de vaixells on es genera un tauler per consola i s'ha d'esbrinar en quina posició estan els vaixells en el mínim nombre de torns.
Una vegada el tauler està generat, l'usuari hi ha d'introduir coordenada i el joc visualitzara si ha donat a un vaixell, passarà torn i ha de continuar buscant fins a enfonsar tots els vaixells del tauler.
El tauler és una graella de 10x10 amb els números (1-10) a la primera fila i lletres (A-J) a la primera columna per a identificar les caselles, sent A1 la casella a la cantonada superior esquerra i J10 la casella a la cantonada inferior dreta.

### Objectiu del joc
Afonar els 9 vaixells que hi ha en el tauler. Existeixen 4 tipus:
- **1 Portaavions**: ocupa 4 caselles
- **2 Cuirassat**: ocupen 3 caselles (cada un)
- **3 Destructors**: ocupen 2 caselles (cada un)
- **4 Submarins**: ocupen 1 casella (cada un)

Els vaixells estaran col·locats en vertical o horitzontal, mai en diagonal.

### Simbologia per pantalla:
```
· Casella oculta
* Aigua
+ Vaixell tocat
X Vaixell enfonsat
```
### Condició de victòria
El joc finalitza una vegada el jugador a trobat tots els vaixells.
El joc mostrara la seua puntuació d'acord amb els torns que han transcorregut fins a arribar a la victòria.

### Condició de derrota
Al ser d'un jugador normes, no hi haurà condició de derrota.
