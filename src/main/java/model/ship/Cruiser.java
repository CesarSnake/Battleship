package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;

public class Cruiser extends Ship {
    public Cruiser(Coordinate coordinate, Direction direction) {
        if (coordinate == null || direction == null) {
            throw new NullPointerException("Cannot create a Cruiser because \"coordinate\" or \"direction\" is null");
        }

        _length = 3;
        _hits = new ArrayList<>();

        try {
            _coordinates = GenerateCoordinates(coordinate, direction, _length-1);
        } catch (UnsupportedOperationException e) {
            throw new ExceptionInInitializerError(
                String.join("",
                    "Cannot set a Cruiser Direction ",
                    direction.toString(), " at Coordinate ", coordinate.toString()));
        }
    }
}
