package model;

import model.ship.Ship;

public class Cell {
    Coordinate _coordinate;
    CellStatus _status;
    Ship _ship = null;

    public Cell(Coordinate coordinate) {
        if (coordinate == null) {
            throw new NullPointerException("Cannot create a Cell because \"coordinate\" is null");
        }

        _coordinate = coordinate;
        _status = CellStatus.Hide;
    }

    public Coordinate Coordinate() { return _coordinate; }
    public CellStatus Status() { return _status; }

    public void SetShip(Ship ship) {
        if (ship == null) {
            throw new NullPointerException("Cannot set a null ship");
        }

        if (_ship != null) {
            throw new UnsupportedOperationException("This cell has already a ship");
        }

        if (!ship.Coordinates().contains(_coordinate)) {
            throw new UnsupportedOperationException(
                String.join("",
                    "The ship ", ship.getClass().getSimpleName(), "has different coordinates than this Cell ", _coordinate.toString()));
        }
        _ship = ship;
    }

    public boolean HasShip() {
        return  _ship != null;
    }

    public Ship GetShip() {
        return _ship;
    }

    public CellStatus Hit() {
        // Was already hit
        if (_status == CellStatus.Watter) {
            throw new UnsupportedOperationException(
                String.join("", "Coordinate: ", _coordinate.toString(), " already hit"));
        }

        if (_ship == null) {
            // cell is free
            _status = CellStatus.Watter;
        } else {
            // cell has a ship
            _ship.Hit(_coordinate);
            _status = CellStatus.Hit;

            if (_ship.IsSunk()) {
                _status = CellStatus.Destroyed;
            }
        }
        return _status;
    }
}
