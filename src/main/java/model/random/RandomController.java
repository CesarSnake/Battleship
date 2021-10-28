package model.random;

import model.Coordinate;

public class RandomController {
    private final RandomGenerator randomGenerator;

    public RandomController(RandomGenerator randomGenerator) {
        if(randomGenerator == null) {
            throw new NullPointerException("Cannot create a RandomController from a null RandomGenerator");
        }

        this.randomGenerator = randomGenerator;
    }

    public Coordinate RandomCoordinate() {
        return randomGenerator.GetRandomCoordinate();
    }
}
