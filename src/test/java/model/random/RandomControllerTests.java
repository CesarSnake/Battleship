package model.random;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RandomControllerTests {
    RandomController randomController;

    @Test
    void RandomControllerConstructorNull() {
        assertThrowsExactly(NullPointerException.class,
            ()-> randomController = new RandomController(null),
            "Cannot create a RandomController from a null RandomGenerator");
    }

    @Test
    void RandomCoordinateTestorA1() {
        RandomGenerator randomGeneratorMockA1 = new RandomGeneratorMockA1();
        randomController = new RandomController(randomGeneratorMockA1);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestB2() {
        RandomGenerator randomGeneratorMockB2 = new RandomGeneratorMockB2();
        randomController = new RandomController(randomGeneratorMockB2);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('B',2), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestC3() {
        RandomGenerator randomGeneratorMockC3 = new RandomGeneratorMockC3();
        randomController = new RandomController(randomGeneratorMockC3);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('C',3), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestD4() {
        RandomGenerator randomGeneratorMockD4 = new RandomGeneratorMockD4();
        randomController = new RandomController(randomGeneratorMockD4);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('D',4), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestE5() {
        RandomGenerator randomGeneratorMockE5 = new RandomGeneratorMockE5();
        randomController = new RandomController(randomGeneratorMockE5);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('E',5), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestF6() {
        RandomGenerator randomGeneratorMockF6 = new RandomGeneratorMockF6();
        randomController = new RandomController(randomGeneratorMockF6);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('F',6), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestG7() {
        RandomGenerator randomGeneratorMockG7 = new RandomGeneratorMockG7();
        randomController = new RandomController(randomGeneratorMockG7);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('G',7), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestH8() {
        RandomGenerator randomGeneratorMockH8 = new RandomGeneratorMockH8();
        randomController = new RandomController(randomGeneratorMockH8);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('H',8), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestI9() {
        RandomGenerator randomGeneratorMockI9 = new RandomGeneratorMockI9();
        randomController = new RandomController(randomGeneratorMockI9);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('I',9), randomController.RandomCoordinate());
    }

    @Test
    void RandomCoordinateTestJ10() {
        RandomGenerator randomGeneratorMockJ10 = new RandomGeneratorMockJ10();
        randomController = new RandomController(randomGeneratorMockJ10);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('J',10), randomController.RandomCoordinate());
    }

    @Test
    void GetRandomDirectionA1Test() {
        RandomGenerator randomGeneratorDirectionA1 = new RandomGeneratorMockA1();
        randomController = new RandomController(randomGeneratorDirectionA1);
        Direction dir = randomGeneratorDirectionA1.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    void GetRandomDirectionB2Test() {
        RandomGenerator randomGeneratorDirectionB2 = new RandomGeneratorMockB2();
        randomController = new RandomController(randomGeneratorDirectionB2);
        Direction dir = randomGeneratorDirectionB2.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    void GetRandomDirectionC3Test() {
        RandomGenerator randomGeneratorDirectionC3 = new RandomGeneratorMockC3();
        randomController = new RandomController(randomGeneratorDirectionC3);
        Direction dir = randomGeneratorDirectionC3.GetRandomDirection();

        assertEquals(Direction.East ,dir);
    }

    @Test
    void GetRandomDirectionD4Test() {
        RandomGenerator randomGeneratorDirectionD4 = new RandomGeneratorMockD4();
        randomController = new RandomController(randomGeneratorDirectionD4);
        Direction dir = randomGeneratorDirectionD4.GetRandomDirection();

        assertEquals(Direction.East ,dir);
    }

    @Test
    void GetRandomDirectionE5Test() {
        RandomGenerator randomGeneratorDirectionE5 = new RandomGeneratorMockE5();
        randomController = new RandomController(randomGeneratorDirectionE5);
        Direction dir = randomGeneratorDirectionE5.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    void GetRandomDirectionF6Test() {
        RandomGenerator randomGeneratorDirectionF6 = new RandomGeneratorMockF6();
        randomController = new RandomController(randomGeneratorDirectionF6);
        Direction dir = randomGeneratorDirectionF6.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    void GetRandomDirectionG7Test() {
        RandomGenerator randomGeneratorDirectionG7 = new RandomGeneratorMockG7();
        randomController = new RandomController(randomGeneratorDirectionG7);
        Direction dir = randomGeneratorDirectionG7.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    void GetRandomDirectionH8Test() {
        RandomGenerator randomGeneratorDirectionH8 = new RandomGeneratorMockH8();
        randomController = new RandomController(randomGeneratorDirectionH8);
        Direction dir = randomGeneratorDirectionH8.GetRandomDirection();

        assertEquals(Direction.West ,dir);
    }

    @Test
    void GetRandomDirectionI9Test() {
        RandomGenerator randomGeneratorDirectionI9 = new RandomGeneratorMockI9();
        randomController = new RandomController(randomGeneratorDirectionI9);
        Direction dir = randomGeneratorDirectionI9.GetRandomDirection();

        assertEquals(Direction.West ,dir);
    }

    @Test
    void GetRandomDirectionJ10Test() {
        RandomGenerator randomGeneratorDirectionJ10 = new RandomGeneratorMockJ10();
        randomController = new RandomController(randomGeneratorDirectionJ10);
        Direction dir = randomGeneratorDirectionJ10.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    void RandomCoordinate5TimesMockTest() {
        RandomGenerator randomGenerator5Times = new RandomGeneratorMock5Times();
        randomController = new RandomController(randomGenerator5Times);

        assertEquals(new Coordinate('B', 7), randomController.RandomCoordinate());
        assertEquals(new Coordinate('C', 3), randomController.RandomCoordinate());
        assertEquals(new Coordinate('E', 9), randomController.RandomCoordinate());
        assertEquals(new Coordinate('H', 8), randomController.RandomCoordinate());
        assertEquals(new Coordinate('J', 2), randomController.RandomCoordinate());


        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
    }

    @Test
    void RandomDirection5TimesMockTest() {
        RandomGenerator randomGenerator5Times = new RandomGeneratorMock5Times();
        randomController = new RandomController(randomGenerator5Times);

        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.South, randomController.RandomDirection());
        assertEquals(Direction.North, randomController.RandomDirection());
        assertEquals(Direction.West, randomController.RandomDirection());
        assertEquals(Direction.North, randomController.RandomDirection());


        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
    }
}
