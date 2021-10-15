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

class CarrierMock extends Carrier {
    CarrierMock() {
        _coordinates = List.of(
            new Coordinate("C",1),
            new Coordinate("C",2),
            new Coordinate("C",3),
            new Coordinate("C",4),
            new Coordinate("C",5)
        );
        _length = 5;
        _hits = new ArrayList<>();
    }
}
class BattleShipMock extends BattleShip {
    BattleShipMock() {
        _coordinates = List.of(
            new Coordinate("B",5),
            new Coordinate("B",6),
            new Coordinate("B",7),
            new Coordinate("B",8)
        );
        _length = 4;
        _hits = new ArrayList<>();
    }
}
class CruiserMock extends Cruiser {
    CruiserMock() {
        _coordinates = List.of(
            new Coordinate("C",8),
            new Coordinate("C",9),
            new Coordinate("C",10)
        );
        _length = 3;
        _hits = new ArrayList<>();
    }
}
class DestroyerMock extends Destroyer {
    DestroyerMock() {
        _coordinates = List.of(
            new Coordinate("D",1),
            new Coordinate("D",2)
        );
        _length = 2;
        _hits = new ArrayList<>();
    }
}
class SubmarineMock extends Submarine {
    SubmarineMock() {
        _coordinates = List.of(
            new Coordinate("H",8)
        );
        _length = 1;
        _hits = new ArrayList<>();
    }
}