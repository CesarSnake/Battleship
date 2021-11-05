package model.random;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RandomControllerTests {
    RandomController randomController;

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Get Random Direction (mock A1) Test")
    void RandomControllerConstructorNull() {
        assertThrowsExactly(NullPointerException.class,
            ()-> randomController = new RandomController(null),
            "Cannot create a RandomController from a null RandomGenerator");
    }

    /* To test property the RandomController class, we need to use mocks to check the expected value
     * We are going to test with 10 values (the diagonal of the board) */

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock A1) Test")
    void RandomCoordinateTestA1() {
        RandomGenerator randomGeneratorMockA1 = new RandomGeneratorMockA1();
        randomController = new RandomController(randomGeneratorMockA1);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock B2) Test")
    void RandomCoordinateTestB2() {
        RandomGenerator randomGeneratorMockB2 = new RandomGeneratorMockB2();
        randomController = new RandomController(randomGeneratorMockB2);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('B',2), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock C3) Test")
    void RandomCoordinateTestC3() {
        RandomGenerator randomGeneratorMockC3 = new RandomGeneratorMockC3();
        randomController = new RandomController(randomGeneratorMockC3);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('C',3), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock D4) Test")
    void RandomCoordinateTestD4() {
        RandomGenerator randomGeneratorMockD4 = new RandomGeneratorMockD4();
        randomController = new RandomController(randomGeneratorMockD4);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('D',4), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock E5) Test")
    void RandomCoordinateTestE5() {
        RandomGenerator randomGeneratorMockE5 = new RandomGeneratorMockE5();
        randomController = new RandomController(randomGeneratorMockE5);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('E',5), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock F6) Test")
    void RandomCoordinateTestF6() {
        RandomGenerator randomGeneratorMockF6 = new RandomGeneratorMockF6();
        randomController = new RandomController(randomGeneratorMockF6);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('F',6), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock G7) Test")
    void RandomCoordinateTestG7() {
        RandomGenerator randomGeneratorMockG7 = new RandomGeneratorMockG7();
        randomController = new RandomController(randomGeneratorMockG7);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('G',7), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock H8) Test")
    void RandomCoordinateTestH8() {
        RandomGenerator randomGeneratorMockH8 = new RandomGeneratorMockH8();
        randomController = new RandomController(randomGeneratorMockH8);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('H',8), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock I9) Test")
    void RandomCoordinateTestI9() {
        RandomGenerator randomGeneratorMockI9 = new RandomGeneratorMockI9();
        randomController = new RandomController(randomGeneratorMockI9);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('I',9), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random coordinate (mock J10) Test")
    void RandomCoordinateTestJ10() {
        RandomGenerator randomGeneratorMockJ10 = new RandomGeneratorMockJ10();
        randomController = new RandomController(randomGeneratorMockJ10);

        assertNotNull(randomController.RandomCoordinate());
        assertEquals(new Coordinate ('J',10), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock A1) Test")
    void GetRandomDirectionA1Test() {
        RandomGenerator randomGeneratorDirectionA1 = new RandomGeneratorMockA1();
        randomController = new RandomController(randomGeneratorDirectionA1);
        Direction dir = randomGeneratorDirectionA1.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock B2) Test")
    void GetRandomDirectionB2Test() {
        RandomGenerator randomGeneratorDirectionB2 = new RandomGeneratorMockB2();
        randomController = new RandomController(randomGeneratorDirectionB2);
        Direction dir = randomGeneratorDirectionB2.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock C3) Test")
    void GetRandomDirectionC3Test() {
        RandomGenerator randomGeneratorDirectionC3 = new RandomGeneratorMockC3();
        randomController = new RandomController(randomGeneratorDirectionC3);
        Direction dir = randomGeneratorDirectionC3.GetRandomDirection();

        assertEquals(Direction.East ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock D4) Test")
    void GetRandomDirectionD4Test() {
        RandomGenerator randomGeneratorDirectionD4 = new RandomGeneratorMockD4();
        randomController = new RandomController(randomGeneratorDirectionD4);
        Direction dir = randomGeneratorDirectionD4.GetRandomDirection();

        assertEquals(Direction.East ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock E5) Test")
    void GetRandomDirectionE5Test() {
        RandomGenerator randomGeneratorDirectionE5 = new RandomGeneratorMockE5();
        randomController = new RandomController(randomGeneratorDirectionE5);
        Direction dir = randomGeneratorDirectionE5.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock F6) Test")
    void GetRandomDirectionF6Test() {
        RandomGenerator randomGeneratorDirectionF6 = new RandomGeneratorMockF6();
        randomController = new RandomController(randomGeneratorDirectionF6);
        Direction dir = randomGeneratorDirectionF6.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock G7) Test")
    void GetRandomDirectionG7Test() {
        RandomGenerator randomGeneratorDirectionG7 = new RandomGeneratorMockG7();
        randomController = new RandomController(randomGeneratorDirectionG7);
        Direction dir = randomGeneratorDirectionG7.GetRandomDirection();

        assertEquals(Direction.South ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock H8) Test")
    void GetRandomDirectionH8Test() {
        RandomGenerator randomGeneratorDirectionH8 = new RandomGeneratorMockH8();
        randomController = new RandomController(randomGeneratorDirectionH8);
        Direction dir = randomGeneratorDirectionH8.GetRandomDirection();

        assertEquals(Direction.West ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock I9) Test")
    void GetRandomDirectionI9Test() {
        RandomGenerator randomGeneratorDirectionI9 = new RandomGeneratorMockI9();
        randomController = new RandomController(randomGeneratorDirectionI9);
        Direction dir = randomGeneratorDirectionI9.GetRandomDirection();

        assertEquals(Direction.West ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Get random direction (mock J10) Test")
    void GetRandomDirectionJ10Test() {
        RandomGenerator randomGeneratorDirectionJ10 = new RandomGeneratorMockJ10();
        randomController = new RandomController(randomGeneratorDirectionJ10);
        Direction dir = randomGeneratorDirectionJ10.GetRandomDirection();

        assertEquals(Direction.North ,dir);
    }

    @Test
    @Tag("mock")
    @DisplayName("Random coordinates (GameMock) for a real Game Test")
    void RandomCoordinatesMockTest() {
        RandomGenerator randomGeneratorForGame = new RandomGeneratorGameMock();
        randomController = new RandomController(randomGeneratorForGame);

        assertEquals(new Coordinate('C', 3), randomController.RandomCoordinate());
        assertEquals(new Coordinate('H', 8), randomController.RandomCoordinate());
        assertEquals(new Coordinate('B', 7), randomController.RandomCoordinate());
        assertEquals(new Coordinate('J', 2), randomController.RandomCoordinate());
        assertEquals(new Coordinate('E', 9), randomController.RandomCoordinate());


        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Random direction (GameMock) for a real Game Test")
    void RandomDirectionMockTest() {
        RandomGenerator randomGeneratorForGame = new RandomGeneratorGameMock();
        randomController = new RandomController(randomGeneratorForGame);

        assertEquals(Direction.South, randomController.RandomDirection());
        assertEquals(Direction.West, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.North, randomController.RandomDirection());
        assertEquals(Direction.North, randomController.RandomDirection());


        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
    }

    @Test
    @Tag("mock")
    @DisplayName("Random coordinates (ExtremeGameMock) for a real game Test")
    void RandomCoordinatesExtremeGameMockTest() {
        RandomGenerator randomGeneratorForExtremeGame = new RandomGeneratorExtremeGameMock();
        randomController = new RandomController(randomGeneratorForExtremeGame);

        assertEquals(new Coordinate('J', 10), randomController.RandomCoordinate());
        assertEquals(new Coordinate('J', 1), randomController.RandomCoordinate());
        assertEquals(new Coordinate('I', 8), randomController.RandomCoordinate());
        assertEquals(new Coordinate('I', 3), randomController.RandomCoordinate());
        assertEquals(new Coordinate('I', 1), randomController.RandomCoordinate());


        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
        assertEquals(new Coordinate('A',1), randomController.RandomCoordinate());
    }

    @Test
    @Tag("mock")
    @DisplayName("Random direction (ExtremeGameMock) for a real game Test")
    void RandomDirectionExtremeGameMockTest() {
        RandomGenerator randomGeneratorForExtremeGame = new RandomGeneratorExtremeGameMock();
        randomController = new RandomController(randomGeneratorForExtremeGame);

        assertEquals(Direction.West, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.West, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.North, randomController.RandomDirection());


        assertEquals(Direction.East, randomController.RandomDirection());
        assertEquals(Direction.East, randomController.RandomDirection());
    }
}
