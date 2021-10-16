package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;

public class Carrier extends Ship {

    public Carrier(Coordinate coordinate, Direction direction) {
        _length = 5;
        _hits = new ArrayList<>();

        try {
            _coordinates = GenerateCoordinates(coordinate, direction, _length-1);
        } catch (UnsupportedOperationException e) {
            throw new ExceptionInInitializerError(
                String.join("",
                "Cannot set a Carrier Direction ",
                direction.toString(), " at Coordinate ", coordinate.toString()));
        }
    }
}
