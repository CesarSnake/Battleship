package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;

public class Carrier extends Ship {

    public Carrier(Coordinate coordinate, Direction direction) {
        if (coordinate == null || direction == null) {
            throw new NullPointerException("Cannot create a Carrier because 'coordinate' or 'direction' is null");
        }

        length = 5;
        hits = new ArrayList<>();

        try {
            coordinates = GenerateCoordinates(coordinate, direction, length-1);
        } catch (UnsupportedOperationException e) {
            throw new ExceptionInInitializerError(
                String.join("",
                "Cannot set a Carrier Direction ",
                direction.toString(), " at Coordinate ", coordinate.toString()));
        }
    }
}
