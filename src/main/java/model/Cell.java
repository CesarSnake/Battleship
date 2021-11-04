package model;

import model.ship.Ship;

public class Cell {
    Coordinate coordinate;
    CellStatus status;
    Ship ship;

    public Cell(Coordinate coordinate) {
        if (coordinate == null) {
            throw new NullPointerException("Cannot create a Cell because 'coordinate' is null");
        }

        this.coordinate = coordinate;
        this.status = CellStatus.Hide;
        this.ship = null;
    }

    public Coordinate Coordinate() { 
        return coordinate;
    }
    
    public CellStatus Status() {
        return status;
    }

    public void SetStatus(CellStatus status) {
        if (status == null) {
            throw new NullPointerException("Cannot set a null status");
        }

        this.status = status;
    }

    public void SetShip(Ship ship) {
        if (ship == null) {
            throw new NullPointerException("Cannot set a null ship");
        }

        if (this.ship != null) {
            throw new UnsupportedOperationException("This cell has already a ship");
        }

        if (!ship.Coordinates().contains(coordinate)) {
            String shipName = ship.getClass().getSimpleName();

            throw new UnsupportedOperationException(
                String.join(" ", "The ship", shipName, "has different coordinates than this Cell", coordinate.toString()));
        }

        this.ship = ship;
    }

    public boolean HasShip() {
        return ship != null;
    }

    public Ship GetShip() {
        return ship;
    }

    public CellStatus Hit() {
        // Was already hit
        if (status == CellStatus.Water) {
            throw new UnsupportedOperationException(
                String.join(" ", "Coordinate:", coordinate.toString(), "already hit"));
        }

        if (ship == null) {
            // cell is free
            status = CellStatus.Water;
        } else {
            // cell has a ship
            ship.Hit(coordinate);
            status = CellStatus.Hit;

            if (ship.IsSunk()) {
                status = CellStatus.Destroyed;
            }
        }

        return status;
    }
}
