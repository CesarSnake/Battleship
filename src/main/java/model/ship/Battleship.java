package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;

public class Battleship extends Ship {
    public Battleship(Coordinate coordinate, Direction direction) {
        if (coordinate == null || direction == null) {
            throw new NullPointerException("Cannot create a Battleship because \"coordinate\" or \"direction\" is null");
        }

        _length = 4;
        _hits = new ArrayList<>();

        try {
            _coordinates = GenerateCoordinates(coordinate, direction, _length-1);
        } catch (UnsupportedOperationException e) {
            throw new ExceptionInInitializerError(
                String.join("",
                    "Cannot set a Battleship Direction ",
                    direction.toString(), " at Coordinate ", coordinate.toString()));
        }
    }
}
