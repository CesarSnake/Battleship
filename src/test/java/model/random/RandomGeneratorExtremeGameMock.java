package model.random;

import model.Coordinate;
import model.Direction;

import java.util.List;

public class RandomGeneratorExtremeGameMock extends RandomGenerator {
    int timesCalledCoordinate = 0;
    int timesCalledDirection = 0;
    boolean stopIncrementCoordinate = false;
    boolean stopIncrementDirection = false;

    Coordinate defaultCoordinate = new Coordinate('A', 1);
    Direction defaultDirection = Direction.East;

    List<Coordinate> coordinateList = List.of(
        new Coordinate('J', 10),
        new Coordinate('J', 1),
        new Coordinate('I', 8),
        new Coordinate('I', 3),
        new Coordinate('I', 1)
    );

    List<Direction> directionList = List.of(
        Direction.West,
        Direction.East,
        Direction.West,
        Direction.East,
        Direction.North
    );


    @Override
    public Coordinate GetRandomCoordinate() {
        Coordinate simulatedRandomCoordinate;

        if (stopIncrementCoordinate) {
            simulatedRandomCoordinate = defaultCoordinate;
        } else {
            simulatedRandomCoordinate = coordinateList.get(timesCalledCoordinate);
            timesCalledCoordinate++;

            if (timesCalledCoordinate == 5) {
                stopIncrementCoordinate = true;
            }
        }

        return simulatedRandomCoordinate;
    }

    @Override
    public Direction GetRandomDirection() {
        Direction simulatedDirection;

        if (stopIncrementDirection) {
            simulatedDirection = defaultDirection;
        } else {
            simulatedDirection = directionList.get(timesCalledDirection);
            timesCalledDirection++;

            if (timesCalledDirection == 5) {
                stopIncrementDirection = true;
            }
        }

        return simulatedDirection;
    }
}
