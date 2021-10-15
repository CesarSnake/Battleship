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

class ShipC2C6HitMock extends Ship {
    // it simulates a ship on the coordinates C2-C6 with 1 hit on C3
    ShipC2C6HitMock() {
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

class ShipB8E8Mock extends Ship {
    // it simulates a ship on the coordinates B8-E8 without hits
    ShipB8E8Mock() {
        _coordinates = List.of(
                new Coordinate("B",8),
                new Coordinate("C",8),
                new Coordinate("D",8),
                new Coordinate("E",8)
        );
        _length = _coordinates.size();
        _hits = new ArrayList<>();
    }
}

class ShipG4G6Mock extends Ship {
    // it simulates a ship on the coordinates B8-E8 without hits
    ShipG4G6Mock() {
        _coordinates = List.of(
                new Coordinate("G",4),
                new Coordinate("G",5),
                new Coordinate("G",6)
        );
        _length = _coordinates.size();
        _hits = new ArrayList<>();
    }
}
