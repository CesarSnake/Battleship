package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarrierTests {
    // Carrier fills 4 cells
    @Test
    void SetShipDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'D'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                        () -> new Carrier(c, dir),
                        String.join("","Cannot set a Carrier Direction ",
                            Direction.North.toString(), " at Coordinate ", c.toString())
                );
            }
        }

        // Valid positions
        for (char i = 'E'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }
    }

    @Test
    void SetShipDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'F'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }

        // Invalid positions
        for (char i = 'G'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                        () -> new Carrier(c, dir),
                        String.join("","Cannot set a Carrier Direction ",
                            Direction.North.toString(), " at Coordinate ", c.toString())
                );
            }
        }
    }

    @Test
    void SetShipDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 6; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 7; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                        () -> new Carrier(c, dir),
                        String.join("","Cannot set a Carrier Direction ",
                            Direction.North.toString(), " at Coordinate ", c.toString())
                );
            }
        }
    }

    @Test
    void SetShipDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 5; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Carrier carrier = new Carrier(c, dir);

                assertNotNull(carrier);
                assertEquals(5, carrier.Length());
                assertTrue(carrier.Hits().isEmpty());
                assertFalse(carrier.IsSunk());
            }
        }

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 4; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                        () -> new Carrier(c, dir),
                        String.join("","Cannot instantiate a Carrier Direction ",
                            Direction.North.toString(), " at Coordinate ", c.toString())
                );
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
            assertFalse(carrier.IsSunk());
            assertTrue(carrier.Hits().contains(c));
        }

        assertTrue(carrier.IsSunk());
    }
}
