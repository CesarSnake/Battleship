package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CruiserTests {
    // Cruiser fills 3 cells
    @Test
    @Tag("decisionCoverage")
    @DisplayName("Constructor null parameters Test")
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Cruiser(new Coordinate('E', 6), null),
            "Cannot create a Cruiser because 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Cruiser(null, Direction.West),
            "Cannot create a Cruiser because 'coordinate' or 'direction' is null");
    }

    @Test
    @Tag("limit")
    @DisplayName("Create cruiser invalid coordinates direction North Test")
    void SetCruiserInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'B'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join(" ",
                        "Cannot set a Cruiser Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Cruiser valid coordinates direction North Test")
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
    @Tag("limit")
    @DisplayName("Create Cruiser invalid coordinates direction South Test")
    void SetCruiserInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (char i = 'I'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join(" ",
                        "Cannot set a Cruiser Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Cruiser valid coordinates direction South Test")
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
    @Tag("limit")
    @DisplayName("Create Cruiser invalid coordinates direction East Test")
    void SetCruiserInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 9; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join(" ",
                        "Cannot set a Cruiser Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Cruiser valid coordinates direction East Test")
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
    @Tag("limit")
    @DisplayName("Create Cruiser invalid coordinates direction West Test")
    void SetCruiserInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 2; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Cruiser(c, dir),
                    String.join(" ",
                        "Cannot instantiate a Cruiser Direction",
                        Direction.North.toString(), "at Coordinate", c.toString()));
            }
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Cruiser valid coordinates direction West Test")
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
    @Tag("partitionedEquivalence")
    @DisplayName("Sink Cruiser Test")
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

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction North) Test")
    void CruiserNorthToString() {
        Cruiser cruiser = new Cruiser(new Coordinate('G', 2), Direction.North);

        assertEquals("Ship not sunk, hits: []", cruiser.toString());

        cruiser.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [F2]", cruiser.toString());

        cruiser.Hit(new Coordinate('G',2));
        assertEquals("Ship not sunk, hits: [F2, G2]", cruiser.toString());

        cruiser.Hit(new Coordinate('E',2));
        assertEquals("Cruiser sunk, hits: [E2, F2, G2]", cruiser.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction East) Test")
    void CruiserEastToString() {
        Cruiser cruiser = new Cruiser(new Coordinate('F', 2), Direction.East);

        assertEquals("Ship not sunk, hits: []", cruiser.toString());

        cruiser.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [F2]", cruiser.toString());

        cruiser.Hit(new Coordinate('F',3));
        assertEquals("Ship not sunk, hits: [F2, F3]", cruiser.toString());

        cruiser.Hit(new Coordinate('F',4));
        assertEquals("Cruiser sunk, hits: [F2, F3, F4]", cruiser.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction South) Test")
    void CruiserSouthToString() {
        Cruiser cruiser = new Cruiser(new Coordinate('B', 7), Direction.South);

        assertEquals("Ship not sunk, hits: []", cruiser.toString());

        cruiser.Hit(new Coordinate('D',7));
        assertEquals("Ship not sunk, hits: [D7]", cruiser.toString());

        cruiser.Hit(new Coordinate('C',7));
        assertEquals("Ship not sunk, hits: [C7, D7]", cruiser.toString());

        cruiser.Hit(new Coordinate('B',7));
        assertEquals("Cruiser sunk, hits: [B7, C7, D7]", cruiser.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction West) Test")
    void CruiserWestToString() {
        Cruiser cruiser = new Cruiser(new Coordinate('G', 7), Direction.West);

        assertEquals("Ship not sunk, hits: []", cruiser.toString());

        cruiser.Hit(new Coordinate('G',6));
        assertEquals("Ship not sunk, hits: [G6]", cruiser.toString());

        cruiser.Hit(new Coordinate('G',7));
        assertEquals("Ship not sunk, hits: [G6, G7]", cruiser.toString());

        cruiser.Hit(new Coordinate('G',5));
        assertEquals("Cruiser sunk, hits: [G5, G6, G7]", cruiser.toString());
    }
}
