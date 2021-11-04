package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestroyerTests {
    // Destroyer fills 2 cells
    @Test
    @Tag("decisionCoverage")
    @DisplayName("Constructor null parameters Test")
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Destroyer(new Coordinate('E', 5), null),
            "Cannot create a Destroyer because 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Destroyer(null, Direction.North),
            "Cannot create a Destroyer because 'coordinate' or 'direction' is null");
    }

    @Test
    @Tag("limit")
    @DisplayName("Create Destroyer invalid coordinates direction North Test")
    void SetDestroyerInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (int j = 1; j <= 10; j++) {
            Coordinate c = new Coordinate('A', j);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join(" ",
                    "Cannot set a Destroyer Direction",
                    Direction.North.toString(), "at Coordinate", c.toString()));
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Destroyer valid coordinates direction North Test")
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
    @Tag("limit")
    @DisplayName("Create Destroyer invalid coordinates direction South Test")
    void SetDestroyerInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (int j = 1; j <= 10; j++) {
            Coordinate c = new Coordinate('J', j);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join(" ",
                    "Cannot set a Destroyer Direction",
                    Direction.North.toString(), "at Coordinate", c.toString()));
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Destroyer valid coordinates direction South Test")
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
    @Tag("limit")
    @DisplayName("Create Destroyer invalid coordinates direction East Test")
    void SetDestroyerInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            Coordinate c = new Coordinate(i, 10);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join(" ",
                    "Cannot set a Destroyer Direction",
                    Direction.North.toString(), "at Coordinate", c.toString()));
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Destroyer valid coordinates direction East Test")
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
    @Tag("limit")
    @DisplayName("Create Destroyer invalid coordinates direction West Test")
    void SetDestroyerInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            Coordinate c = new Coordinate(i, 1);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Destroyer(c, dir),
                String.join(" ",
                    "Cannot instantiate a Destroyer Direction",
                    Direction.North.toString(), "at Coordinate", c.toString()));
        }
    }

    @Test
    @Tag("partitionedEquivalence")
    @DisplayName("Create Destroyer valid coordinates direction West Test")
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
    @Tag("partitionedEquivalence")
    @DisplayName("Sink destroyer Test")
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

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction North) Test")
    void DestroyerNorthToString() {
        Destroyer destroyer = new Destroyer(new Coordinate('G', 2), Direction.North);

        assertEquals("Ship not sunk, hits: []", destroyer.toString());

        destroyer.Hit(new Coordinate('G',2));
        assertEquals("Ship not sunk, hits: [G2]", destroyer.toString());

        destroyer.Hit(new Coordinate('F',2));
        assertEquals("Destroyer sunk, hits: [F2, G2]", destroyer.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction East) Test")
    void DestroyerEastToString() {
        Destroyer destroyer = new Destroyer(new Coordinate('F', 2), Direction.East);

        assertEquals("Ship not sunk, hits: []", destroyer.toString());

        destroyer.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [F2]", destroyer.toString());

        destroyer.Hit(new Coordinate('F',3));
        assertEquals("Destroyer sunk, hits: [F2, F3]", destroyer.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction South) Test")
    void DestroyerSouthToString() {
        Destroyer destroyer = new Destroyer(new Coordinate('B', 7), Direction.South);

        assertEquals("Ship not sunk, hits: []", destroyer.toString());

        destroyer.Hit(new Coordinate('B',7));
        assertEquals("Ship not sunk, hits: [B7]", destroyer.toString());

        destroyer.Hit(new Coordinate('C',7));
        assertEquals("Destroyer sunk, hits: [B7, C7]", destroyer.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (direction West) Test")
    void DestroyerWestToString() {
        Destroyer destroyer = new Destroyer(new Coordinate('G', 7), Direction.West);

        assertEquals("Ship not sunk, hits: []", destroyer.toString());

        destroyer.Hit(new Coordinate('G',7));
        assertEquals("Ship not sunk, hits: [G7]", destroyer.toString());

        destroyer.Hit(new Coordinate('G',6));
        assertEquals("Destroyer sunk, hits: [G6, G7]", destroyer.toString());
    }
}
