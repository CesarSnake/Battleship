package model;

import java.util.List;

public class Coordinate {
    private final Character letter;
    private final Integer number;

    public Coordinate(Character letter, Integer number) {
        if (letter == null || number == null) {
            throw new NullPointerException("Cannot create a Coordinate because \"letter\" or \"number\" is null");
        }

        List<Character> validLetters = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        if (!validLetters.contains(letter) ||
            !validNumbers.contains(number)) {
            throw new ExceptionInInitializerError(
                String.join("",
                "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }

        this.letter = letter;
        this.number = number;
    }

    public Character Letter() {
        return letter;
    }

    public Integer Number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate c = (Coordinate) o;
        return letter.equals(c.Letter()) &&
            number.equals(c.Number());
    }

    @Override
    public String toString() {
        return (String.join("",
            String.valueOf(letter), Integer.toString(number)));
    }
}
