package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    List<Coordinate> _coordinates;
    Integer _length;
    ArrayList<Coordinate> _hits;

    Ship() { /* Ship itself is empty, acts as an interface for the ship types */ }
    public List<Coordinate> Coordinates() { return _coordinates; }
    public Integer Length() { return _length; }
    public List<Coordinate> Hits() { return _hits; }

    public void Hit(Coordinate coordinate) {
        if (!_coordinates.contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join("",getClass().getName(), " is not positioned on the coordinate: ", coordinate.toString()));
        }
        if (_hits.contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join("", "Coordinate: ", coordinate.toString(), " already hit"));
        }

        _hits.add(coordinate);
    }

    public Boolean IsSunk() {
        return _hits.size() == _length;
    }

    protected void ThrowInitException(ShipType type, Coordinate coordinate, Direction direction) {
        throw new ExceptionInInitializerError(String.join("",
            "Cannot set a ", type.toString(),
            " Direction ", direction.toString(), " at Coordinate ", coordinate.toString()));
    }
}
