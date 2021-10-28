package model.random;

import model.Coordinate;
import model.Direction;

import java.util.List;

// it returns different coordinates and directions each time it is called (5 times max)
public class RandomGeneratorMock5Times extends RandomGenerator {
    int timesCalled = 0;
    boolean stopIncrement = false;
    Coordinate defaultCoordinate = new Coordinate('A', 1);
    Direction defaultDirection = Direction.East;

    List<Coordinate> coordinateList = List.of(
        new Coordinate('B', 7),
        new Coordinate('C', 3),
        new Coordinate('E', 9),
        new Coordinate('H', 8),
        new Coordinate('J', 2)
    );

    List<Direction> directionList = List.of(
        Direction.East,
        Direction.South,
        Direction.North,
        Direction.West,
        Direction.North
    );


    @Override
    public Coordinate GetRandomCoordinate() {
        Coordinate simulatedRandomCoordinate;

        if (stopIncrement) {
            simulatedRandomCoordinate = defaultCoordinate;
        } else {
            simulatedRandomCoordinate = coordinateList.get(timesCalled);
            timesCalled++;

            if (timesCalled == 5) {
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
            simulatedDirection = directionList.get(timesCalled);
            timesCalled++;

            if (timesCalled == 5) {
                stopIncrement = true;
            }
        }

        return simulatedDirection;
    }
}
