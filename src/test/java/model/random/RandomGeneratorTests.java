package model.random;

import model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void GetRandomCoordinateTest () {
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
    void GetRandomCoordinateTestA1 () {
        Coordinate cd = randomGeneratorMockA1.GetRandomCoordinate();
        assertEquals(new Coordinate('A',1), cd);
        assertEquals('A', cd.Letter());
        assertEquals(1, cd.Number());
        assertEquals("A1", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestB2 () {
        Coordinate cd = randomGeneratorMockB2.GetRandomCoordinate();
        assertEquals(new Coordinate('B',2), cd);
        assertEquals('B', cd.Letter());
        assertEquals(2, cd.Number());
        assertEquals("B2", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestC3 () {
        Coordinate cd = randomGeneratorMockC3.GetRandomCoordinate();
        assertEquals(new Coordinate('C',3), cd);
        assertEquals('C', cd.Letter());
        assertEquals(3, cd.Number());
        assertEquals("C3", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestD4 () {
        Coordinate cd = randomGeneratorMockD4.GetRandomCoordinate();
        assertEquals(new Coordinate('D',4), cd);
        assertEquals('D', cd.Letter());
        assertEquals(4, cd.Number());
        assertEquals("D4", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestE5 () {
        Coordinate cd = randomGeneratorMockE5.GetRandomCoordinate();
        assertEquals(new Coordinate('E',5), cd);
        assertEquals('E', cd.Letter());
        assertEquals(5, cd.Number());
        assertEquals("E5", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestF6 () {
        Coordinate cd = randomGeneratorMockF6.GetRandomCoordinate();
        assertEquals(new Coordinate('F',6), cd);
        assertEquals('F', cd.Letter());
        assertEquals(6, cd.Number());
        assertEquals("F6", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestG7 () {
        Coordinate cd = randomGeneratorMockG7.GetRandomCoordinate();
        assertEquals(new Coordinate('G',7), cd);
        assertEquals('G', cd.Letter());
        assertEquals(7, cd.Number());
        assertEquals("G7", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestH8 () {
        Coordinate cd = randomGeneratorMockH8.GetRandomCoordinate();
        assertEquals(new Coordinate('H',8), cd);
        assertEquals('H', cd.Letter());
        assertEquals(8, cd.Number());
        assertEquals("H8", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestI9 () {
        Coordinate cd = randomGeneratorMockI9.GetRandomCoordinate();
        assertEquals(new Coordinate('I',9), cd);
        assertEquals('I', cd.Letter());
        assertEquals(9, cd.Number());
        assertEquals("I9", cd.toString());
    }
    @Test
    void GetRandomCoordinateTestJ10 () {
        Coordinate cd = randomGeneratorMockJ10.GetRandomCoordinate();
        assertEquals(new Coordinate('J',10), cd);
        assertEquals('J', cd.Letter());
        assertEquals(10, cd.Number());
        assertEquals("J10", cd.toString());
    }
}
