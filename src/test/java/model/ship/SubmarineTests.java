package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubmarineTests {
    // Submarine fills 1 cell, can be placed elsewhere
    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Submarine(null, null),
            "Cannot create a Submarine because \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Submarine(null, null),
            "Cannot create a Submarine because \"coordinate\" or \"direction\" is null");
    }

    @Test
    void SetSubmarineDirectionNorthTest() {
        Direction dir = Direction.North;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Submarine Submarine = new Submarine(c, dir);

                assertNotNull(Submarine);
                assertEquals(1, Submarine.Length());
                assertEquals(1, Submarine.Coordinates().size());
                assertTrue(Submarine.Coordinates().contains(c));
                assertTrue(Submarine.Hits().isEmpty());
                assertFalse(Submarine.IsSunk());
            }
        }
    }

    @Test
    void SetSubmarineDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Submarine Submarine = new Submarine(c, dir);

                assertNotNull(Submarine);
                assertEquals(1, Submarine.Length());
                assertEquals(1, Submarine.Coordinates().size());
                assertTrue(Submarine.Hits().isEmpty());
                assertFalse(Submarine.IsSunk());
            }
        }
    }

    @Test
    void SetSubmarineDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Submarine Submarine = new Submarine(c, dir);

                assertNotNull(Submarine);
                assertEquals(1, Submarine.Length());
                assertEquals(1, Submarine.Coordinates().size());
                assertTrue(Submarine.Hits().isEmpty());
                assertFalse(Submarine.IsSunk());
            }
        }
    }

    @Test
    void SetSubmarineDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Submarine Submarine = new Submarine(c, dir);

                assertNotNull(Submarine);
                assertEquals(1, Submarine.Length());
                assertEquals(1, Submarine.Coordinates().size());
                assertTrue(Submarine.Hits().isEmpty());
                assertFalse(Submarine.IsSunk());
            }
        }
    }

    @Test
    void SinkSubmarineTest() {
        Submarine submarine = new Submarine(new Coordinate('H', 1), Direction.East);

        assertTrue(submarine.Hits().isEmpty());
        assertFalse(submarine.IsSunk());

        Coordinate c = new Coordinate('H',1);
        submarine.Hit(c);
        assertTrue(submarine.Hits().contains(c));
    }

    @Test
    void SubmarineNorthToString() {
        Submarine submarine = new Submarine(new Coordinate('E', 2), Direction.North);

        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('E',2));
        assertEquals("Submarine sunk, hits: [E2]", submarine.toString());
    }

    @Test
    void SubmarineEastToString() {
        Submarine submarine = new Submarine(new Coordinate('F', 2), Direction.East);

        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('F',2));
        assertEquals("Submarine sunk, hits: [F2]", submarine.toString());
    }

    @Test
    void SubmarineSouthToString() {
        Submarine submarine = new Submarine(new Coordinate('B', 7), Direction.South);

        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('B',7));
        assertEquals("Submarine sunk, hits: [B7]", submarine.toString());
    }

    @Test
    void SubmarineWestToString() {
        Submarine submarine = new Submarine(new Coordinate('G', 7), Direction.West);

        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('G',7));
        assertEquals("Submarine sunk, hits: [G7]", submarine.toString());
    }
}
