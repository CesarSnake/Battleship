package model.ship;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

class ShipEmptyMock extends Ship {
    // Empty ship mock
    @Override
    public void Hit(Coordinate coordinate) {
        // do nothing
    }

    @Override
    public Boolean IsSunk() {
        return null;
    }
}

class ShipC2C6Mock extends Ship {
    // it simulates a ship on the coordinates C2-C6 with 1 hit on C3
    ShipC2C6Mock() {
        _coordinates = List.of(
            new Coordinate("C",2),
            new Coordinate("C",3),
            new Coordinate("C",4),
            new Coordinate("C",5),
            new Coordinate("C",6)
        );
        _length = _coordinates.size();
        _hits = new ArrayList<>();
        _hits.add(new Coordinate("C", 3));
    }
}
