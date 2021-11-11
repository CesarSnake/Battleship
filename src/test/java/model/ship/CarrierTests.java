package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrierTests {
    // Carrier fills 5 cells
    @Test
    @Order(1)
    @Tag("decisionCoverage")
    @DisplayName("[DecisionCoverage] - Constructor null parameters Test")
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Carrier(new Coordinate('E', 4), null),
            "Cannot create a Carrier because 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Carrier(null, Direction.East),
            "Cannot create a Carrier because 'coordinate' or 'direction' is null");
    }

    /*
     * We are going to check all the coordinates that the Carrier cannot be placed
     * and all the valid ones that can be placed
     */

    @Test
    @Order(2)
    @Tag("limit")
    @DisplayName("[Limit] - Create Carrier invalid coordinates direction North Test")
    void SetCarrierInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'D'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join(" ",
                        "Cannot set a Carrier Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Order(3)
    @Tag("partitionedEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create Carrier valid coordinates direction North Test")
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
    @Order(4)
    @Tag("limit")
    @DisplayName("[Limit] - Create Carrier invalid coordinates direction South Test")
    void SetCarrierInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (char i = 'G'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join(" ",
                        "Cannot set a Carrier Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Order(5)
    @Tag("partitionedEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create Carrier valid coordinates direction South Test")
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
    @Order(6)
    @Tag("limit")
    @DisplayName("[Limit] - Create Carrier invalid coordinates direction East Test")
    void SetCarrierInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 7; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join(" ",
                        "Cannot set a Carrier Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Order(7)
    @Tag("partitionedEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create Carrier valid coordinates direction East Test")
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
    @Order(8)
    @Tag("limit")
    @DisplayName("[Limit] - Create Carrier invalid coordinates direction West Test")
    void SetCarrierInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 4; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Carrier(c, dir),
                    String.join(" ",
                        "Cannot instantiate a Carrier Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Order(9)
    @Tag("partitionedEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create Carrier valid coordinates direction West Test")
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
    @Order(10)
    @Tag("partitionedEquivalence")
    @DisplayName("[PartitionedEquivalence] - Sink Cruiser Test")
    void SinkCarrierTest() {
        Carrier carrier = new Carrier(new Coordinate('A', 1), Direction.East);

        assertTrue(carrier.Hits().isEmpty());
        assertFalse(carrier.IsSunk());

        for (int i = 1; i <= 5; i++) {
            Coordinate c = new Coordinate('A',i);
            carrier.Hit(c);
            assertTrue(carrier.Hits().contains(c));

            if (i != 5) {
                assertFalse(carrier.IsSunk());
            }
        }

        assertTrue(carrier.IsSunk());
    }

    /*
     * To check all the possible values of toString() method we should place the ship on all the possible
     * coordinates and check each time it is hit and sunk, instead of that,
     * we are going to use Pairwise testing and check one per direction
     */

    @Test
    @Order(11)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction North) Test")
    void CarrierNorthToString() {
        Carrier carrier = new Carrier(new Coordinate('G', 2), Direction.North);

        assertEquals("Ship not sunk, hits: []", carrier.toString());

        carrier.Hit(new Coordinate('D',2));
        assertEquals("Ship not sunk, hits: [D2]", carrier.toString());

        carrier.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [D2, F2]", carrier.toString());

        carrier.Hit(new Coordinate('C',2));
        assertEquals("Ship not sunk, hits: [C2, D2, F2]", carrier.toString());

        carrier.Hit(new Coordinate('G',2));
        assertEquals("Ship not sunk, hits: [C2, D2, F2, G2]", carrier.toString());

        carrier.Hit(new Coordinate('E',2));
        assertEquals("Carrier sunk, hits: [C2, D2, E2, F2, G2]", carrier.toString());
    }

    @Test
    @Order(12)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction East) Test")
    void CarrierEastToString() {
        Carrier carrier = new Carrier(new Coordinate('F', 2), Direction.East);

        assertEquals("Ship not sunk, hits: []", carrier.toString());

        carrier.Hit(new Coordinate('F',6));
        assertEquals("Ship not sunk, hits: [F6]", carrier.toString());

        carrier.Hit(new Coordinate('F',4));
        assertEquals("Ship not sunk, hits: [F4, F6]", carrier.toString());

        carrier.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [F2, F4, F6]", carrier.toString());

        carrier.Hit(new Coordinate('F',3));
        assertEquals("Ship not sunk, hits: [F2, F3, F4, F6]", carrier.toString());

        carrier.Hit(new Coordinate('F',5));
        assertEquals("Carrier sunk, hits: [F2, F3, F4, F5, F6]", carrier.toString());
    }

    @Test
    @Order(13)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction South) Test")
    void CarrierSouthToString() {
        Carrier carrier = new Carrier(new Coordinate('B', 7), Direction.South);

        assertEquals("Ship not sunk, hits: []", carrier.toString());

        carrier.Hit(new Coordinate('F',7));
        assertEquals("Ship not sunk, hits: [F7]", carrier.toString());

        carrier.Hit(new Coordinate('D',7));
        assertEquals("Ship not sunk, hits: [D7, F7]", carrier.toString());

        carrier.Hit(new Coordinate('E',7));
        assertEquals("Ship not sunk, hits: [D7, E7, F7]", carrier.toString());

        carrier.Hit(new Coordinate('C',7));
        assertEquals("Ship not sunk, hits: [C7, D7, E7, F7]", carrier.toString());

        carrier.Hit(new Coordinate('B',7));
        assertEquals("Carrier sunk, hits: [B7, C7, D7, E7, F7]", carrier.toString());
    }

    @Test
    @Order(14)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction West) Test")
    void CarrierWestToString() {
        Carrier carrier = new Carrier(new Coordinate('G', 7), Direction.West);

        assertEquals("Ship not sunk, hits: []", carrier.toString());

        carrier.Hit(new Coordinate('G',6));
        assertEquals("Ship not sunk, hits: [G6]", carrier.toString());

        carrier.Hit(new Coordinate('G',4));
        assertEquals("Ship not sunk, hits: [G4, G6]", carrier.toString());

        carrier.Hit(new Coordinate('G',3));
        assertEquals("Ship not sunk, hits: [G3, G4, G6]", carrier.toString());

        carrier.Hit(new Coordinate('G',7));
        assertEquals("Ship not sunk, hits: [G3, G4, G6, G7]", carrier.toString());

        carrier.Hit(new Coordinate('G',5));
        assertEquals("Carrier sunk, hits: [G3, G4, G5, G6, G7]", carrier.toString());
    }
}
