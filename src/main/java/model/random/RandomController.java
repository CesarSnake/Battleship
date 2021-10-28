package model.random;

import model.Coordinate;
import model.Direction;

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

    public Direction RandomDirection() {
        return randomGenerator.GetRandomDirection();
    }
}
