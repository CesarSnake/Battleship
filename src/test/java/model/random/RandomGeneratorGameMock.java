package model.random;

import model.Coordinate;
import model.Direction;

import java.util.List;

// it returns different coordinates and directions each time it is called (5 times max)
public class RandomGeneratorGameMock extends RandomGenerator {
    int timesCalledCoordinate = 0;
    int timesCalledDirection = 0;
    boolean stopIncrement = false;
    Coordinate defaultCoordinate = new Coordinate('A', 1);
    Direction defaultDirection = Direction.East;

    List<Coordinate> coordinateList = List.of(
        new Coordinate('C', 3),
        new Coordinate('H', 8),
        new Coordinate('B', 7),
        new Coordinate('J', 2),
        new Coordinate('E', 9)
    );

    List<Direction> directionList = List.of(
        Direction.South,
        Direction.West,
        Direction.East,
        Direction.North,
        Direction.North
    );


    @Override
    public Coordinate GetRandomCoordinate() {
        Coordinate simulatedRandomCoordinate;

        if (stopIncrement) {
            simulatedRandomCoordinate = defaultCoordinate;
        } else {
            simulatedRandomCoordinate = coordinateList.get(timesCalledCoordinate);
            timesCalledCoordinate++;

            if (timesCalledCoordinate == 5) {
                stopIncrement = true;
            }
        }

        return simulatedRandomCoordinate;
    }

    @Override
    public Direction GetRandomDirection() {
        Direction simulatedDirection;

        if (stopIncrement) {
            simulatedDirection = defaultDirection;
        } else {
            simulatedDirection = directionList.get(timesCalledDirection);
            timesCalledDirection++;

            if (timesCalledDirection == 5) {
                stopIncrement = true;
            }
        }

        return simulatedDirection;
    }
}
