package model.random;

import model.Coordinate;

import java.util.Random;

public class RandomGenerator {
    public RandomGenerator() { }

    public Coordinate GetRandomCoordinate() {
        return new Coordinate(GetRandomLetter(), GetRandomNumber());
    }

    private char GetRandomLetter() {
        String validLetters = String.join("","A","C","D","E","F","G","H","I","J");
        return validLetters.charAt(new Random().nextInt(validLetters.length()));
    }

    private int GetRandomNumber() {
        return new Random().nextInt(9) + 1;
    }
}
