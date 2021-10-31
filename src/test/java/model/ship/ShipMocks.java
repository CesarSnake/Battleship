package model.ship;

import model.Coordinate;
import model.Direction;

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
        coordinates = List.of(
            new Coordinate('C',2),
            new Coordinate('C',3),
            new Coordinate('C',4),
            new Coordinate('C',5),
            new Coordinate('C',6)
        );
        length = coordinates.size();
        hits = new ArrayList<>();
        hits.add(new Coordinate('C', 3));
    }
}

class ShipB8E8Mock extends Ship {
    // it simulates a ship on the coordinates B8-E8 without hits
    ShipB8E8Mock() {
        coordinates = List.of(
                new Coordinate('B',8),
                new Coordinate('C',8),
                new Coordinate('D',8),
                new Coordinate('E',8)
        );
        length = coordinates.size();
        hits = new ArrayList<>();
    }
}

class ShipG4G6Mock extends Ship {
    // it simulates a ship on the coordinates B8-E8 without hits
    ShipG4G6Mock() {
        coordinates = List.of(
                new Coordinate('G',4),
                new Coordinate('G',5),
                new Coordinate('G',6)
        );
        length = coordinates.size();
        hits = new ArrayList<>();
    }
}

class CarrierMock extends Carrier {
    CarrierMock(Coordinate cd, Direction d) {
        // As we are using a mock to testing, it doesn't matter the value of cd and d

        // Also, we will not use the values from the parent class constructor
        super(new Coordinate('E', 1), Direction.North);

        coordinates = List.of(
            new Coordinate('C',1),
            new Coordinate('C',2),
            new Coordinate('C',3),
            new Coordinate('C',4),
            new Coordinate('C',5)
        );
        length = 5;
        hits = new ArrayList<>();
    }
}
class BattleshipMock extends Battleship {
    BattleshipMock(Coordinate cd, Direction d) {
        // As we are using a mock to testing, it doesn't matter the value of cd and d

        // Also, we will not use the values from the parent class constructor
        super(new Coordinate('H', 1), Direction.North);

        coordinates = List.of(
            new Coordinate('B',5),
            new Coordinate('B',6),
            new Coordinate('B',7),
            new Coordinate('B',8)
        );
        length = 4;
        hits = new ArrayList<>();
    }
}
class CruiserMock extends Cruiser {
    CruiserMock(Coordinate cd, Direction d) {
        // As we are using a mock to testing, it doesn't matter the value of cd and d

        // Also, we will not use the values from the parent class constructor
        super(new Coordinate('G', 1), Direction.North);

        coordinates = List.of(
            new Coordinate('C',8),
            new Coordinate('C',9),
            new Coordinate('C',10)
        );
        length = 3;
        hits = new ArrayList<>();
    }
}
class DestroyerMock extends Destroyer {
    DestroyerMock(Coordinate cd, Direction d) {
        // As we are using a mock to testing, it doesn't matter the value of cd and d

        // Also, we will not use the values from the parent class constructor
        super(new Coordinate('J', 1), Direction.North);

        coordinates = List.of(
            new Coordinate('D',1),
            new Coordinate('D',2)
        );
        length = 2;
        hits = new ArrayList<>();
    }
}
class SubmarineMock extends Submarine {
    SubmarineMock(Coordinate cd, Direction d) {
        // As we are using a mock to testing, it doesn't matter the value of cd and d

        // Also, we will not use the values from the parent class constructor
        super(new Coordinate('F', 1), Direction.North);

        coordinates = List.of(
            new Coordinate('H',8)
        );
        length = 1;
        hits = new ArrayList<>();
    }
}