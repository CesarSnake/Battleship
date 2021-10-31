package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;
import java.util.List;

public class Submarine extends Ship {
    public Submarine(Coordinate coordinate, Direction direction) {
        if (coordinate == null || direction == null) {
            throw new NullPointerException("Cannot create a Submarine because \"coordinate\" or \"direction\" is null");
        }

        length = 1;
        hits = new ArrayList<>();
        coordinates = List.of(coordinate);
    }
}
