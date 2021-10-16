package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CruiserTests {
    // Cruiser fills 3 cells
    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
                () -> new Cruiser(null, null),
                "Cannot create a Cruiser because \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
                () -> new Cruiser(null, null),
                "Cannot create a Cruiser because \"coordinate\" or \"direction\" is null");
    }

    @Test
    void SetCruiserInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'B'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join("",
                        "Cannot set a Cruiser Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCruiserDirectionNorthTest() {
        Direction dir = Direction.North;

        // Valid positions
        for (char i = 'C'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Cruiser Cruiser = new Cruiser(c, dir);

                assertNotNull(Cruiser);
                assertEquals(3, Cruiser.Length());
                assertEquals(3, Cruiser.Coordinates().size());
                assertTrue(Cruiser.Coordinates().contains(c));
                assertTrue(Cruiser.Hits().isEmpty());
                assertFalse(Cruiser.IsSunk());
            }
        }
    }

    @Test
    void SetCruiserInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (char i = 'I'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join("",
                        "Cannot set a Cruiser Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCruiserDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'H'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Cruiser Cruiser = new Cruiser(c, dir);

                assertNotNull(Cruiser);
                assertEquals(3, Cruiser.Length());
                assertEquals(3, Cruiser.Coordinates().size());
                assertTrue(Cruiser.Hits().isEmpty());
                assertFalse(Cruiser.IsSunk());
            }
        }
    }

    @Test
    void SetCruiserInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 9; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join("",
                        "Cannot set a Cruiser Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCruiserDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 8; j++) {
                Coordinate c = new Coordinate(i, j);
                Cruiser Cruiser = new Cruiser(c, dir);

                assertNotNull(Cruiser);
                assertEquals(3, Cruiser.Length());
                assertEquals(3, Cruiser.Coordinates().size());
                assertTrue(Cruiser.Hits().isEmpty());
                assertFalse(Cruiser.IsSunk());
            }
        }
    }

    @Test
    void SetCruiserInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 2; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join("",
                        "Cannot instantiate a Cruiser Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCruiserDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 3; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Cruiser Cruiser = new Cruiser(c, dir);

                assertNotNull(Cruiser);
                assertEquals(3, Cruiser.Length());
                assertEquals(3, Cruiser.Coordinates().size());
                assertTrue(Cruiser.Hits().isEmpty());
                assertFalse(Cruiser.IsSunk());
            }
        }
    }

    @Test
    void SinkCruiserTest() {
        Cruiser cruiser = new Cruiser(new Coordinate('C', 1), Direction.East);

        assertTrue(cruiser.Hits().isEmpty());
        assertFalse(cruiser.IsSunk());

        for (int i = 1; i <= 3; i++) {
            Coordinate c = new Coordinate('C',i);
            cruiser.Hit(c);
            assertTrue(cruiser.Hits().contains(c));

            if(i != 3) {
                assertFalse(cruiser.IsSunk());
            }
        }

        assertTrue(cruiser.IsSunk());
    }
}
