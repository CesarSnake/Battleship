package controller;

import model.Board;
import model.Coordinate;
import model.Direction;
import model.random.RandomController;
import model.random.RandomGenerator;
import model.ship.Ship;
import model.ship.ShipFactory;
import model.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    RandomController randomController;
    Board board;
    Integer turn;

    public GameController() {
        randomController = new RandomController(new RandomGenerator());
    }

    // this method is to use the class on a test environment due the RandomGenerator returns random coordinates
    public GameController(RandomController randomController) {
        this.randomController = randomController;
    }

    public String HowToPlay() {
        List<String> rules = List.of(
            "Rules:",
            "The objective of the game is discover where are the ships.",
            "You must attack the ships and destroy all of them to win.",
            "To attack a ship you must insert a coordinate and press enter.",
            "",
            "Symbols:",
            "· unknown, place not discovered.",
            "~ water, you miss your shot.",
            "/ hit, you hit one ship but it is not destroyed",
            "X destroyed, you destroyed the ship.",
            "",
            "Ships in the game:",
            "Carrier, which has 5 holes",
            "Battleship, which has 4 holes",
            "Cruiser, which has 3 holes",
            "Destroyer, which has 2 holes",
            "Submarine, which has 1 holes");

        return String.join("\n", rules);
    }

    public boolean HasFinish() {
        return (board != null && board.Ships().stream().allMatch(Ship::IsSunk));
    }

    public String NewGame() {
        board = new Board();
        turn = 0;

        ShipFactory shipyard = new ShipFactory();
        for (ShipType shipType: ShipType.values()) {
            boolean shipNotAdded = true;

            // try to add the ship until get a free coordinate (not placed by other ship)
            while (shipNotAdded) {
                Coordinate shipCoordinate = randomController.RandomCoordinate();
                Direction shipDirection = randomController.RandomDirection();

                /* as coordinates and direction are random,
                 * it is quite possible that a ship cannot be placed on the coordinate and direction provided
                 * each ship constructor launches an "ExceptionInInitializerError" exception
                 * when it is created on a coordinate and direction that the ship cannot be placed due his length
                 * also board.AddShip launches the same exception if any coordinates of the ship is filled
                 */
                try {
                    Ship ship = shipyard.CreateShip(shipType, shipCoordinate, shipDirection);
                    shipNotAdded = !board.AddShip(ship);
                } catch (ExceptionInInitializerError ignored) { }
            }
        }

        return GetGameStatus();
    }

    public String AttackCoordinate(Coordinate coordinate) {
        if (board == null) {
            throw new NullPointerException("Must start a game first");
        }

        if (coordinate == null) {
            throw new NullPointerException("Coordinate to attack cannot be null");
        }

        if (HasFinish()) {
            return GetGameStatus();
        }

        // Update the board
        ArrayList<String> gameStatus = new ArrayList<>();
        String errorMessage = null;

        try {
            // if the coordinate was already attack, it will return an "UnsupportedOperationException"
            board.HitCell(coordinate);
            turn++;
        } catch (UnsupportedOperationException exception) {
            errorMessage = exception.getMessage();
        }

        gameStatus.add(GetGameStatus());
        if (errorMessage != null) {
            gameStatus.add(errorMessage);
        }

        return String.join("\n", gameStatus);
    }

    private String GetGameStatus() {
        List<String> gameStatus = List.of(
            String.join(" ","Turn:", String.valueOf(turn)),
            board.toString());

        return String.join("\n", gameStatus);
    }
}
