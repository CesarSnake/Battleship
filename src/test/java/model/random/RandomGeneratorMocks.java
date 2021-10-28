package model.random;

import model.Coordinate;
import model.Direction;

import java.util.List;

class RandomGeneratorMockA1 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('A', 1);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.North;
    }
}

class RandomGeneratorMockB2 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('B', 2);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.North;
    }
}

class RandomGeneratorMockC3 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('C', 3);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.East;
    }
}

class RandomGeneratorMockD4 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('D', 4);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.East;
    }
}

class RandomGeneratorMockE5 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('E', 5);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.South;
    }
}

class RandomGeneratorMockF6 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('F', 6);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.South;
    }
}

class RandomGeneratorMockG7 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('G', 7);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.South;
    }
}

class RandomGeneratorMockH8 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('H', 8);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.West;
    }
}

class RandomGeneratorMockI9 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('I', 9);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.West;
    }
}

class RandomGeneratorMockJ10 extends RandomGenerator {
    @Override
    public Coordinate GetRandomCoordinate() {
        return new Coordinate('J', 10);
    }

    @Override
    public Direction GetRandomDirection() {
        return Direction.North;
    }
}

// it returns different coordinates and directions each time it is called (5 times max)
class RandomGeneratorMock5Times extends RandomGenerator {
    int timesCalled = 0;
    boolean stopIncrement = false;
    Coordinate defaultCoordinate = new Coordinate('A',1);
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