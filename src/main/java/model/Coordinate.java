package model;

import java.util.List;

public class Coordinate {
    private Character _letter;
    private Integer _number;

    private List<Character> _validLetters = List.of('A','B','C','D','E','F','G','H','I','J');
    private List<Integer> _validNumbers = List.of(1,2,3,4,5,6,7,8,9,10);

    public Coordinate(Character letter, Integer number) {
        if (!_validLetters.contains(letter) ||
            !_validNumbers.contains(number)) {
            throw new ExceptionInInitializerError(String.join("",
                "Invalid Coordinate: ",
                String.valueOf(letter), Integer.toString(number)));
        }

        _letter = letter;
        _number = number;
    }

    public Character Letter() {
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
        return (String.join("", String.valueOf(_letter), Integer.toString(_number)));
    }
}
