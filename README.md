# Battleship single player

## Battleship

Classic battle ship game, it generates a console board and the player must discover where the ships are placed, trying to use the minimal number of turns.
Once the board is generates, the user must to introduce a coordinate and the game will display if the player has shot a ship, will pass the next turn and the game continues till all the ships would be destroyed.
The board is a 10x10 matrix using the numbers (1-10) for rows and the letters (A-J) for columns in order to identify the cells, being A1 the first cell on the top left corner and J10 the cell on the bottom right corner.

### Game objective
Destroy the 6 ships placed on the board.
- **Carrier**: fill 5 cells
- **Battleship**: fill 4 cells
- **Cruiser**: fill 3 cells
- **Destroyer**: fill 2 cells
- **Submarine**: fill 1 cell

### Symbols:
```
· Unknow cell
+ Water
/ Hit ship
X Destroyed ship
```
### Victory condition
The game finishes once the player has found all the ships.
The game will display the player's score acording to the number of turns.

### Losing condition
As there is only one player game, there is not losing condition :D



## Design

When the game begins, it will display the main menu with the options:
- Start Game: will start the game.
- How to play: will display the game rules.
- Exit Game: will close the program.

```
-------------------------
BattleShip single player
-------------------------
1. Start Game.
2. How to Play.
3. Exit Game.
```

### Start Game:
The game creates a 10x10 board and introduces **randomly** the 5 kind of ships:
- **Carrier** of 5 cells
- **Battleship** of 4 cells
- **Cruiser** of 3 cells
- **Destroyer** of 2 cells
- **Submarine** of 1 cell

On the game screen, will appears the current turn and the board with all the cells hidden displaying the symbol '·', on the bottom of the board will apper the message "Attack to coordinate".
The user should introduce the coordinate and press enter.
The game will increase the turn and will display again the board updated acording to the user's attack.
The given coordinate will modify the cell with the symbol:
```
~ If the cell is water (no ship).
/ If a ship has been hit (cell with a ship part).
X If the ship has been hit and destroyed (cell with a ship part who has all the other parts hit, this ones will be replaced with this symbol also).
```

The game finishes once the player destrois all the ships, the program will display the message "You won in XX turns".

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
The game will display the next screen:
```
Rules:
The objective of the game is discover where are the ships.
You must atack the ships and destroy all of them to win.
To attack a ship you must insert a coordinate and press enter.

Symbols:
· unknown, place not discovered.
~ water, you miss your shot.
/ hit, you hit one ship but it is not destroyed
X destroyed, you destroyed the ship.

Ships in the game:
Carrier, which has 5 holes
Battleship which has 4 holes
Cruiser, which has 3 holes
Destroyer, which has 2 holes
Submarine, which has 1 holes

Press any key to back main menu
```



## Functionality
- The program mush display the main menu when is launched.
- The user will choose start the game, display the rules or close the game.
- The game always will display the turn, the actual board state and will wait a coordinate introduced by the user.
- When a coordinte is introduced, the turn will be increased, the board will be updated and the game will wait for the next coordinate. If the coordinate introduced is incorrect, the program will launch an exceptrion and will ask again.
- The game will finish when the player will introduce all the coordinate where a ship is placed.

### Classes

#### Coordinates
- The coordinate is a capital letter (from A to J) and a number (from 1 to 10).
- Other kind of symbol will launch an exception.

#### Ship & sons (Carrier, Battleship, Cruiser, Destroyer, Submarine)
- The ship acts as parent class for the other ships, but it is empty and not playable on the board.
- The ship can be attack on the cells that is filled, if it get an attack on a coordinate this one changes to hit.
- If a ship is attacked again on a coordinate already hit, it will launch an exeption.
- The ship is destroyed when all the coordinates has been hit.

#### ShipFactory
- It is a class that returns the kind of ship that we request.

#### Cell
- The cell is a coordinate and a state (water, hit or destroyed).
- Each ship of the game will be placed to X cells, the cell can be empty or return the ship that is placed.

#### Board
- The board is form by 100 cells in a 10 x 10 matrix, being A-J the columns and 1-10 the rows.
- The board only places an each kind of ship, it cannot be a ship twice.
- The board will get an attack on a cell and will be updated.
- Each cell in the board will display a symbol, the symbols are updated after each attack of the player.

#### Game Controller
- The controller only will get the commands (sent by the view) and will return the requested message.

#### View
- Will display a menu with all the available options.
- Will ask to the player the action to do and it will be sent to the controller.
- Will display the messages received by the controller.