package model.ship;

import model.Coordinate;
import model.Direction;

public class ShipFactory {
    public static Ship CreateShip(ShipType shipType, Coordinate coordinate, Direction direction) {
        if(shipType == null || coordinate == null || direction == null) {
            throw new NullPointerException("Cannot create a ship because \"shipType\" or \"coordinate\" or \"direction\" is null");
        }

        Ship requestedShip = null;
        switch (shipType) {
            case Carrier:
                requestedShip = new Carrier(coordinate, direction);
                break;
            case Battleship:
                requestedShip = new Battleship(coordinate, direction);
                break;
            case Cruiser:
                requestedShip = new Cruiser(coordinate, direction);
                break;
            case Destroyer:
                requestedShip = new Destroyer(coordinate, direction);
                break;
            case Submarine:
                requestedShip = new Submarine(coordinate, direction);
                break;
        }
        return requestedShip;
    }
}
