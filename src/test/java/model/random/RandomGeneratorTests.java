package model.random;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RandomGeneratorTests {
    RandomGenerator randomGenerator;
    RandomGenerator randomGeneratorMockA1;
    RandomGenerator randomGeneratorMockB2;
    RandomGenerator randomGeneratorMockC3;
    RandomGenerator randomGeneratorMockD4;
    RandomGenerator randomGeneratorMockE5;
    RandomGenerator randomGeneratorMockF6;
    RandomGenerator randomGeneratorMockG7;
    RandomGenerator randomGeneratorMockH8;
    RandomGenerator randomGeneratorMockI9;
    RandomGenerator randomGeneratorMockJ10;


    @BeforeEach
    void Setup() {
        randomGenerator = new RandomGenerator();
        randomGeneratorMockA1 = new RandomGeneratorMockA1();
        randomGeneratorMockB2 = new RandomGeneratorMockB2();
        randomGeneratorMockC3 = new RandomGeneratorMockC3();
        randomGeneratorMockD4 = new RandomGeneratorMockD4();
        randomGeneratorMockE5 = new RandomGeneratorMockE5();
        randomGeneratorMockF6 = new RandomGeneratorMockF6();
        randomGeneratorMockG7 = new RandomGeneratorMockG7();
        randomGeneratorMockH8 = new RandomGeneratorMockH8();
        randomGeneratorMockI9 = new RandomGeneratorMockI9();
        randomGeneratorMockJ10 = new RandomGeneratorMockJ10();
    }

    @Test
    @Order(1)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionEquivalence] - Get Random Coordinate Test")
    void GetRandomCoordinateTest () {
        // We are going to check that the coordinates provided are valid
        // as we will not know the exact values, but we know the valid ranges

        // As a board has 100 cells, lets check several tries
        for (int i = 0; i < 1000; i++) {
            Coordinate cd = randomGenerator.GetRandomCoordinate();
            assertNotNull(cd);
            assertNotNull(cd.Letter());
            assertNotNull(cd.Number());

            assertTrue(cd.Letter() >= 'A');
            assertTrue(cd.Letter() <= 'J');

            assertTrue(cd.Number() >= 1);
            assertTrue(cd.Number() <= 10);

            assertNotEquals("", cd.toString());
        }
    }

    @Test
    @Order(2)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionEquivalence] - Get Random Direction Test")
    void GetRandomDirectionTest() {
        // We are going to check that the directions provided are valid
        // as we will not know the exact values, but we know the valid ranges

        // There are 4 directions
        for (int i = 0; i < 1000; i++) {
            Direction dir = randomGenerator.GetRandomDirection();
            assertNotNull(dir);
            assertTrue(Arrays.stream(Direction.values()).anyMatch(direction ->
                dir.toString().equals(direction.toString())));
        }
    }

    /* As the class return random coordinates or directions, we need mocks to check with an expected value
     * we are going to try one mock per column (A-J) */

    @Test
    @Order(3)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock A1) Test")
    void GetRandomCoordinateA1Test() {
        Coordinate cd = randomGeneratorMockA1.GetRandomCoordinate();
        assertEquals(new Coordinate('A',1), cd);
        assertEquals('A', cd.Letter());
        assertEquals(1, cd.Number());
        assertEquals("A1", cd.toString());
    }

    @Test
    @Order(4)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock B2) Test")
    void GetRandomCoordinateB2Test() {
        Coordinate cd = randomGeneratorMockB2.GetRandomCoordinate();
        assertEquals(new Coordinate('B',2), cd);
        assertEquals('B', cd.Letter());
        assertEquals(2, cd.Number());
        assertEquals("B2", cd.toString());
    }

    @Test
    @Order(5)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock C3) Test")
    void GetRandomCoordinateC3Test() {
        Coordinate cd = randomGeneratorMockC3.GetRandomCoordinate();
        assertEquals(new Coordinate('C',3), cd);
        assertEquals('C', cd.Letter());
        assertEquals(3, cd.Number());
        assertEquals("C3", cd.toString());
    }

    @Test
    @Order(6)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock D4) Test")
    void GetRandomCoordinateD4Test() {
        Coordinate cd = randomGeneratorMockD4.GetRandomCoordinate();
        assertEquals(new Coordinate('D',4), cd);
        assertEquals('D', cd.Letter());
        assertEquals(4, cd.Number());
        assertEquals("D4", cd.toString());
    }

    @Test
    @Order(7)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock E5) Test")
    void GetRandomCoordinateE5Test() {
        Coordinate cd = randomGeneratorMockE5.GetRandomCoordinate();
        assertEquals(new Coordinate('E',5), cd);
        assertEquals('E', cd.Letter());
        assertEquals(5, cd.Number());
        assertEquals("E5", cd.toString());
    }

    @Test
    @Order(8)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock F6) Test")
    void GetRandomCoordinateF6Test() {
        Coordinate cd = randomGeneratorMockF6.GetRandomCoordinate();
        assertEquals(new Coordinate('F',6), cd);
        assertEquals('F', cd.Letter());
        assertEquals(6, cd.Number());
        assertEquals("F6", cd.toString());
    }

    @Test
    @Order(9)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock G7) Test")
    void GetRandomCoordinateG7Test() {
        Coordinate cd = randomGeneratorMockG7.GetRandomCoordinate();
        assertEquals(new Coordinate('G',7), cd);
        assertEquals('G', cd.Letter());
        assertEquals(7, cd.Number());
        assertEquals("G7", cd.toString());
    }

    @Test
    @Order(10)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock H8) Test")
    void GetRandomCoordinateH8Test() {
        Coordinate cd = randomGeneratorMockH8.GetRandomCoordinate();
        assertEquals(new Coordinate('H',8), cd);
        assertEquals('H', cd.Letter());
        assertEquals(8, cd.Number());
        assertEquals("H8", cd.toString());
    }

    @Test
    @Order(11)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock I9) Test")
    void GetRandomCoordinateI9Test() {
        Coordinate cd = randomGeneratorMockI9.GetRandomCoordinate();
        assertEquals(new Coordinate('I',9), cd);
        assertEquals('I', cd.Letter());
        assertEquals(9, cd.Number());
        assertEquals("I9", cd.toString());
    }

    @Test
    @Order(12)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Coordinate (mock J10) Test")
    void GetRandomCoordinateJ10Test() {
        Coordinate cd = randomGeneratorMockJ10.GetRandomCoordinate();
        assertEquals(new Coordinate('J',10), cd);
        assertEquals('J', cd.Letter());
        assertEquals(10, cd.Number());
        assertEquals("J10", cd.toString());
    }

    @Test
    @Order(13)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock A1) Test")
    void GetRandomDirectionA1Test() {
        Direction dir = randomGeneratorMockA1.GetRandomDirection();
        assertEquals(Direction.North ,dir);
    }

    @Test
    @Order(14)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock B2) Test")
    void GetRandomDirectionB2Test() {
        Direction dir = randomGeneratorMockB2.GetRandomDirection();
        assertEquals(Direction.North ,dir);
    }

    @Test
    @Order(15)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock C3) Test")
    void GetRandomDirectionC3Test() {
        Direction dir = randomGeneratorMockC3.GetRandomDirection();
        assertEquals(Direction.East ,dir);
    }

    @Test
    @Order(16)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock D4) Test")
    void GetRandomDirectionD4Test() {
        Direction dir = randomGeneratorMockD4.GetRandomDirection();
        assertEquals(Direction.East ,dir);
    }

    @Test
    @Order(17)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock E5) Test")
    void GetRandomDirectionE5Test() {
        Direction dir = randomGeneratorMockE5.GetRandomDirection();
        assertEquals(Direction.South ,dir);
    }

    @Test
    @Order(18)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock F6) Test")
    void GetRandomDirectionF6Test() {
        Direction dir = randomGeneratorMockF6.GetRandomDirection();
        assertEquals(Direction.South ,dir);
    }

    @Test
    @Order(19)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock G7) Test")
    void GetRandomDirectionG7Test() {
        Direction dir = randomGeneratorMockG7.GetRandomDirection();
        assertEquals(Direction.South ,dir);
    }

    @Test
    @Order(20)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock H8) Test")
    void GetRandomDirectionH8Test() {
        Direction dir = randomGeneratorMockH8.GetRandomDirection();
        assertEquals(Direction.West ,dir);
    }

    @Test
    @Order(21)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock I9) Test")
    void GetRandomDirectionI9Test() {
        Direction dir = randomGeneratorMockI9.GetRandomDirection();
        assertEquals(Direction.West ,dir);
    }

    @Test
    @Order(22)
    @Tag("mock")
    @DisplayName("[Mock] - Get Random Direction (mock J10) Test")
    void GetRandomDirectionJ10Test() {
        Direction dir = randomGeneratorMockJ10.GetRandomDirection();
        assertEquals(Direction.North ,dir);
    }
}
