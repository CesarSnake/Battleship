package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestroyerTests {
    // Destroyer fills 2 cells
    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Destroyer(null, null),
            "Cannot create a Destroyer because \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Destroyer(null, null),
            "Cannot create a Destroyer because \"coordinate\" or \"direction\" is null");
    }

    @Test
    void SetDestroyerInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (int j = 1; j <= 10; j++) {
            Coordinate c = new Coordinate('A', j);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join("",
                    "Cannot set a Destroyer Direction ",
                    Direction.North.toString(), " at Coordinate ", c.toString()));
        }
    }

    @Test
    void SetDestroyerDirectionNorthTest() {
        Direction dir = Direction.North;

        // Valid positions
        for (char i = 'B'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Destroyer Destroyer = new Destroyer(c, dir);

                assertNotNull(Destroyer);
                assertEquals(2, Destroyer.Length());
                assertEquals(2, Destroyer.Coordinates().size());
                assertTrue(Destroyer.Coordinates().contains(c));
                assertTrue(Destroyer.Hits().isEmpty());
                assertFalse(Destroyer.IsSunk());
            }
        }
    }

    @Test
    void SetDestroyerInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (int j = 1; j <= 10; j++) {
            Coordinate c = new Coordinate('J', j);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join("",
                    "Cannot set a Destroyer Direction ",
                    Direction.North.toString(), " at Coordinate ", c.toString()));
        }
    }

    @Test
    void SetDestroyerDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'I'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Destroyer Destroyer = new Destroyer(c, dir);

                assertNotNull(Destroyer);
                assertEquals(2, Destroyer.Length());
                assertEquals(2, Destroyer.Coordinates().size());
                assertTrue(Destroyer.Hits().isEmpty());
                assertFalse(Destroyer.IsSunk());
            }
        }
    }

    @Test
    void SetDestroyerInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            Coordinate c = new Coordinate(i, 10);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join("",
                    "Cannot set a Destroyer Direction ",
                    Direction.North.toString(), " at Coordinate ", c.toString()));
        }
    }

    @Test
    void SetDestroyerDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 9; j++) {
                Coordinate c = new Coordinate(i, j);
                Destroyer Destroyer = new Destroyer(c, dir);

                assertNotNull(Destroyer);
                assertEquals(2, Destroyer.Length());
                assertEquals(2, Destroyer.Coordinates().size());
                assertTrue(Destroyer.Hits().isEmpty());
                assertFalse(Destroyer.IsSunk());
            }
        }
    }

    @Test
    void SetDestroyerInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            Coordinate c = new Coordinate(i, 1);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join("",
                    "Cannot instantiate a Destroyer Direction ",
                    Direction.North.toString(), " at Coordinate ", c.toString()));
        }
    }

    @Test
    void SetDestroyerDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 2; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Destroyer Destroyer = new Destroyer(c, dir);

                assertNotNull(Destroyer);
                assertEquals(2, Destroyer.Length());
                assertEquals(2, Destroyer.Coordinates().size());
                assertTrue(Destroyer.Hits().isEmpty());
                assertFalse(Destroyer.IsSunk());
            }
        }
    }

    @Test
    void SinkDestroyerTest() {
        Destroyer destroyer = new Destroyer(new Coordinate('D', 1), Direction.East);

        assertTrue(destroyer.Hits().isEmpty());
        assertFalse(destroyer.IsSunk());

        for (int i = 1; i <= 2; i++) {
            Coordinate c = new Coordinate('D',i);
            destroyer.Hit(c);
            assertTrue(destroyer.Hits().contains(c));

            if(i != 2) {
                assertFalse(destroyer.IsSunk());
            }
        }

        assertTrue(destroyer.IsSunk());
    }
}
