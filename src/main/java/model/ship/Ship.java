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
        if (coordinate == null) {
            throw new NullPointerException("Cannot hit the ship because \"coordinate\" is null");
        }

        if (!_coordinates.contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join("",
                getClass().getName(), " is not positioned on the coordinate: ", coordinate.toString()));
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

    protected List<Coordinate> GenerateCoordinates(Coordinate coordinate, Direction direction, int increase) {
        if (coordinate == null || direction == null) {
            throw new NullPointerException(
                "Cannot create a GenerateCoordinates because \"coordinate\" or \"direction\" is null");
        }

        List<Coordinate> coordinates = null;
        try {
            switch (direction) {
                case North:
                    coordinates = IncreaseLetter((char) (coordinate.Letter()-increase), increase, coordinate.Number());
                    break;

                case South:
                    coordinates = IncreaseLetter(coordinate.Letter(), increase, coordinate.Number());
                    break;

                case East:
                    coordinates = IncreaseNumber(coordinate.Number(), increase, coordinate.Letter());
                    break;

                case West:
                    coordinates = IncreaseNumber(coordinate.Number() - increase, increase, coordinate.Letter());
                    break;
            }
        } catch (ExceptionInInitializerError e) {
            throw new UnsupportedOperationException(
                String.join("",
                "Cannot generate ", String.valueOf(increase), " coordinates direction", direction.toString()));
        }
        return coordinates;
    }

    private List<Coordinate> IncreaseLetter(char letter, int increase, int number) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (char i = letter; i <= (letter+increase); i++) {
            coordinates.add(new Coordinate(i, number));
        }
        return  coordinates;
    }

    private  List<Coordinate> IncreaseNumber(int number, int increase, char letter) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int i = number; i <= (number+increase); i++) {
            coordinates.add(new Coordinate(letter, i));
        }
        return coordinates;
    }
}
