package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarrierTests {
    // Carrier fills 5 cells
    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Carrier(null, null),
            "Cannot create a Carrier because \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Carrier(null, null),
            "Cannot create a Carrier because \"coordinate\" or \"direction\" is null");
    }

    @Test
    void SetCarrierInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'D'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join("",
                        "Cannot set a Carrier Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCarrierDirectionNorthTest() {
        Direction dir = Direction.North;

        // Valid positions
        for (char i = 'E'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertEquals(5, carrier.Coordinates().size());
                assertTrue(carrier.Coordinates().contains(c));
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }
    }

    @Test
    void SetCarrierInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (char i = 'G'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join("",
                        "Cannot set a Carrier Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCarrierDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'F'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertEquals(5, carrier.Coordinates().size());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }
    }

    @Test
    void SetCarrierInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 7; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join("",
                        "Cannot set a Carrier Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCarrierDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 6; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertEquals(5, carrier.Coordinates().size());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }
    }

    @Test
    void SetCarrierInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 4; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join("",
                        "Cannot instantiate a Carrier Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetCarrierDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 5; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertEquals(5, carrier.Coordinates().size());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }
    }

    @Test
    void SinkCarrierTest() {
        Carrier carrier = new Carrier(new Coordinate('A', 1), Direction.East);

        assertTrue(carrier.Hits().isEmpty());
        assertFalse(carrier.IsSunk());

        for (int i = 1; i <= 5; i++) {
            Coordinate c = new Coordinate('A',i);
            carrier.Hit(c);
            assertTrue(carrier.Hits().contains(c));

            if(i != 5) {
                assertFalse(carrier.IsSunk());
            }
        }

        assertTrue(carrier.IsSunk());
    }
}
