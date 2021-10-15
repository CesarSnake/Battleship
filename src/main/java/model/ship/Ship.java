package model.ship;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    List<Coordinate> _coordinates;
    Integer _length;
    ArrayList<Coordinate> _hits;

    Ship() { /* Ship itself is empty, acts as an interface for the ship types */  }
    public List<Coordinate> Coordinates() { return _coordinates; }
    public Integer Length() { return _length; }
    public List<Coordinate> Hits() { return _hits;}

    public void Hit(Coordinate coordinate) {
        // TODO
    }

    public Boolean IsSunk() {
        // TODO
        return false;
    }
}
