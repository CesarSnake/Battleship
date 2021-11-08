package model.random;

import model.Coordinate;
import model.Direction;

import java.util.Random;

public class RandomGenerator {
    public RandomGenerator() { }

    public Coordinate GetRandomCoordinate() {
        return new Coordinate(GetRandomLetter(), GetRandomNumber());
    }

    public Direction GetRandomDirection() {
        int dir = new Random().nextInt(Direction.values().length);
        return Direction.values()[dir];
    }

    private char GetRandomLetter() {
        String validLetters = String.join("","A","C","D","E","F","G","H","I","J");
        return validLetters.charAt(new Random().nextInt(validLetters.length()));
    }

    private int GetRandomNumber() {
        // coordinate numbers are between 1 and 10
        return new Random().nextInt(9) + 1;
    }
}
