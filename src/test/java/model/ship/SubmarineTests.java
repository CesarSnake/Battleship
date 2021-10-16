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
}
