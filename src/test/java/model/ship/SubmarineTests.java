package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubmarineTests {
    // Submarine fills 1 cell, can be placed elsewhere
    @Test
    @Order(1)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Constructor null parameters Test")
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Submarine(null, Direction.South),
            "Cannot create a Submarine because 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Submarine(new Coordinate('E', 5), null),
            "Cannot create a Submarine because 'coordinate' or 'direction' is null");
    }

    /*
     * As the Submarine only fill one Cell, it can be placed anywhere
     */

    @Test
    @Order(2)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Set direction North Test")
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
    @Order(3)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Set direction South Test")
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
    @Order(4)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Set direction East Test")
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
    @Order(5)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Set direction West Test")
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
    @Order(6)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Sink submarine Test")
    void SinkSubmarineTest() {
        Submarine submarine = new Submarine(new Coordinate('H', 1), Direction.East);

        assertTrue(submarine.Hits().isEmpty());
        assertFalse(submarine.IsSunk());

        Coordinate c = new Coordinate('H',1);
        submarine.Hit(c);
        assertTrue(submarine.Hits().contains(c));
    }

    /*
     * To check all the possible values of toString() method we should place the ship on all the possible
     * coordinates and check each time it is hit and sunk, instead of that,
     * we are going to use Pairwise testing and check one per direction
     */

    @Test
    @Order(7)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction North) Test")
    void SubmarineNorthToString() {
        Submarine submarine = new Submarine(new Coordinate('E', 2), Direction.North);
        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('E',2));
        assertEquals("Submarine sunk, hits: [E2]", submarine.toString());
    }

    @Test
    @Order(8)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction East) Test")
    void SubmarineEastToString() {
        Submarine submarine = new Submarine(new Coordinate('F', 2), Direction.East);
        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('F',2));
        assertEquals("Submarine sunk, hits: [F2]", submarine.toString());
    }

    @Test
    @Order(9)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction South) Test")
    void SubmarineSouthToString() {
        Submarine submarine = new Submarine(new Coordinate('B', 7), Direction.South);
        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('B',7));
        assertEquals("Submarine sunk, hits: [B7]", submarine.toString());
    }

    @Test
    @Order(10)
    @Tag("pairwise")
    @DisplayName("[Pairwise] - toString (direction West) Test")
    void SubmarineWestToString() {
        Submarine submarine = new Submarine(new Coordinate('G', 7), Direction.West);
        assertEquals("Ship not sunk, hits: []", submarine.toString());

        submarine.Hit(new Coordinate('G',7));
        assertEquals("Submarine sunk, hits: [G7]", submarine.toString());
    }
}
