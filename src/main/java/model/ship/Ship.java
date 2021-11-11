package model.ship;

import model.Coordinate;
import model.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ship {
    List<Coordinate> coordinates;
    Integer length;
    ArrayList<Coordinate> hits;

    Ship() { /* Ship itself is empty, acts as an interface for the ship types */ }
    
    public List<Coordinate> Coordinates() {
        return coordinates;
    }
    
    public Integer Length() {
        return length;
    }

    public List<Coordinate> Hits() {
        return hits;
    }

    public void Hit(Coordinate coordinate) {
        // A Ship only can be hit on the coordinates that is placed
        if (coordinate == null) {
            throw new NullPointerException("Cannot hit the ship because 'coordinate' is null");
        }

        if (!coordinates.contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join(" ",
                getClass().getSimpleName(), "is not positioned on the coordinate:", coordinate.toString()));
        }
        
        if (hits.contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join(" ", "Coordinate:", coordinate.toString(), "already hit"));
        }

        hits.add(coordinate);
    }

    public Boolean IsSunk() {
        return hits.size() == length;
    }

    @Override
    public String toString() {
        // this method returns the hits of the ship in order
        // the name of the ship is not provided until the ship is sunk
        ArrayList<String> stringList = new ArrayList<>();

        // ship type
        if (IsSunk()) {
            stringList.add(getClass().getSimpleName());
            stringList.add( "sunk,");
        } else {
            stringList.add(getClass().getSuperclass().getSimpleName());
            stringList.add("not sunk,");
        }

        // coordinates (sorted)
        stringList.add("hits:");

        List<String> hitsSorted = hits
            .stream()
            .sorted(Comparator
                .comparing(Coordinate::Letter)
                .thenComparing(Coordinate::Number))
            .map(Coordinate::toString)
            .collect(Collectors.toList());

        stringList.add(hitsSorted.toString());

        return String.join(" ", stringList);
    }

    protected List<Coordinate> GenerateCoordinates(Coordinate coordinate, Direction direction, int increase) {
        // The ship needs a coordinate and a direction to know witch coordinates will use,
        // but depending on the size of the ship, it could not be placed due an invalid origin coordinate,
        // this method returns the list of valid coordinates for the length and the direction of the ship,
        // if the origin coordinate received is invalid for the direction and the length of the ship it throws an exception

        if (coordinate == null || direction == null) {
            throw new NullPointerException(
                "Cannot create a GenerateCoordinates because 'coordinate' or 'direction' is null");
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
                String.join(" ",
                "Cannot generate", String.valueOf(increase), "coordinates direction", direction.toString()));
        }

        return coordinates;
    }

    private List<Coordinate> IncreaseLetter(char letter, int increase, int number) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for (char i = letter; i <= (letter+increase); i++) {
            coordinates.add(new Coordinate(i, number));
        }

        return coordinates;
    }

    private List<Coordinate> IncreaseNumber(int number, int increase, char letter) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for (int i = number; i <= (number+increase); i++) {
            coordinates.add(new Coordinate(letter, i));
        }

        return coordinates;
    }
}
