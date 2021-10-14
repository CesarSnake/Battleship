package model;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    private String _letter;
    private Integer _number;

    private List<String> _validLetters = List.of("A","B","C","D","E","F","G","H","I","J");
    private List<Integer> _validNumbers = List.of(1,2,3,4,5,6,7,8,9,10);

    public Coordinate(String letter, Integer number) {
        if (!_validLetters.contains(letter) ||
            !_validNumbers.contains(number)) {
            throw new ExceptionInInitializerError(String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }

        _letter = letter;
        _number = number;
    }

    public String Letter() {
        return _letter;
    }

    public Integer Number() {
        return _number;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate c = (Coordinate) o;
        return _letter.equals(c.Letter()) &&
            _number.equals(c.Number());
    }

    @Override
    public String toString() {
        return (String.join("", _letter, Integer.toString(_number)));
    }
}