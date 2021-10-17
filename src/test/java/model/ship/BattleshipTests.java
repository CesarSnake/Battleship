package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTests {
    // Battleship fills 4 cells
    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> new Battleship(null, null),
            "Cannot create a Battleship because \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            () -> new Battleship(null, null),
            "Cannot create a Battleship because \"coordinate\" or \"direction\" is null");
    }

    @Test
    void SetBattleshipInvalidDirectionNorthTest() {
        Direction dir = Direction.North;

        // Invalid positions
        for (char i = 'A'; i <= 'C'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Battleship(c, dir),
                String.join("",
                    "Cannot set a Battleship Direction ",
                    Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetBattleshipDirectionNorthTest() {
        Direction dir = Direction.North;

        // Valid positions
        for (char i = 'D'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Battleship Battleship = new Battleship(c, dir);

                assertNotNull(Battleship);
                assertEquals(4, Battleship.Length());
                assertEquals(4, Battleship.Coordinates().size());
                assertTrue(Battleship.Coordinates().contains(c));
                assertTrue(Battleship.Hits().isEmpty());
                assertFalse(Battleship.IsSunk());
            }
        }
    }

    @Test
    void SetBattleshipInvalidDirectionSouthTest() {
        Direction dir = Direction.South;

        // Invalid positions
        for (char i = 'H'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Battleship(c, dir),
                    String.join("",
                        "Cannot set a Battleship Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetBattleshipDirectionSouthTest() {
        Direction dir = Direction.South;

        // Valid positions
        for (char i = 'A'; i <= 'G'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Battleship Battleship = new Battleship(c, dir);

                assertNotNull(Battleship);
                assertEquals(4, Battleship.Length());
                assertEquals(4, Battleship.Coordinates().size());
                assertTrue(Battleship.Hits().isEmpty());
                assertFalse(Battleship.IsSunk());
            }
        }
    }

    @Test
    void SetBattleshipInvalidDirectionEastTest() {
        Direction dir = Direction.East;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 8; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Battleship(c, dir),
                    String.join("",
                        "Cannot set a Battleship Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetBattleshipDirectionEastTest() {
        Direction dir = Direction.East;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 7; j++) {
                Coordinate c = new Coordinate(i, j);
                Battleship Battleship = new Battleship(c, dir);

                assertNotNull(Battleship);
                assertEquals(4, Battleship.Length());
                assertEquals(4, Battleship.Coordinates().size());
                assertTrue(Battleship.Hits().isEmpty());
                assertFalse(Battleship.IsSunk());
            }
        }
    }

    @Test
    void SetBattleshipInvalidDirectionWestTest() {
        Direction dir = Direction.West;

        // Invalid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 3; j++) {
                Coordinate c = new Coordinate(i, j);

                assertThrowsExactly(ExceptionInInitializerError.class,
                    () -> new Battleship(c, dir),
                    String.join("",
                        "Cannot instantiate a Battleship Direction ",
                        Direction.North.toString(), " at Coordinate ", c.toString()));
            }
        }
    }

    @Test
    void SetBattleshipDirectionWestTest() {
        Direction dir = Direction.West;

        // Valid positions
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 4; j <= 10; j++) {
                Coordinate c = new Coordinate(i, j);
                Battleship Battleship = new Battleship(c, dir);

                assertNotNull(Battleship);
                assertEquals(4, Battleship.Length());
                assertEquals(4, Battleship.Coordinates().size());
                assertTrue(Battleship.Hits().isEmpty());
                assertFalse(Battleship.IsSunk());
            }
        }
    }

    @Test
    void SinkBattleshipTest() {
        Battleship battleship = new Battleship(new Coordinate('B', 1), Direction.East);

        assertTrue(battleship.Hits().isEmpty());
        assertFalse(battleship.IsSunk());

        for (int i = 1; i <= 4; i++) {
            Coordinate c = new Coordinate('B',i);
            battleship.Hit(c);
            assertTrue(battleship.Hits().contains(c));

            if(i != 4) {
                assertFalse(battleship.IsSunk());
            }
        }

        assertTrue(battleship.IsSunk());
    }

    @Test
    void BattleshipNorthToString() {
        Battleship battleship = new Battleship(new Coordinate('G', 2), Direction.North);

        assertEquals("Ship not sunk, hits: []", battleship.toString());

        battleship.Hit(new Coordinate('D',2));
        assertEquals("Ship not sunk, hits: [D2]", battleship.toString());

        battleship.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [D2, F2]", battleship.toString());

        battleship.Hit(new Coordinate('G',2));
        assertEquals("Ship not sunk, hits: [D2, F2, G2]", battleship.toString());

        battleship.Hit(new Coordinate('E',2));
        assertEquals("Battleship sunk, hits: [D2, E2, F2, G2]", battleship.toString());
    }

    @Test
    void BattleshipEastToString() {
        Battleship battleship = new Battleship(new Coordinate('F', 2), Direction.East);

        assertEquals("Ship not sunk, hits: []", battleship.toString());

        battleship.Hit(new Coordinate('F',3));
        assertEquals("Ship not sunk, hits: [F3]", battleship.toString());

        battleship.Hit(new Coordinate('F',4));
        assertEquals("Ship not sunk, hits: [F3, F4]", battleship.toString());

        battleship.Hit(new Coordinate('F',2));
        assertEquals("Ship not sunk, hits: [F2, F3, F4]", battleship.toString());

        battleship.Hit(new Coordinate('F',5));
        assertEquals("Battleship sunk, hits: [F2, F3, F4, F5]", battleship.toString());
    }

    @Test
    void BattleshipSouthToString() {
        Battleship battleship = new Battleship(new Coordinate('B', 7), Direction.South);

        assertEquals("Ship not sunk, hits: []", battleship.toString());

        battleship.Hit(new Coordinate('D',7));
        assertEquals("Ship not sunk, hits: [D7]", battleship.toString());

        battleship.Hit(new Coordinate('E',7));
        assertEquals("Ship not sunk, hits: [D7, E7]", battleship.toString());

        battleship.Hit(new Coordinate('C',7));
        assertEquals("Ship not sunk, hits: [C7, D7, E7]", battleship.toString());

        battleship.Hit(new Coordinate('B',7));
        assertEquals("Battleship sunk, hits: [B7, C7, D7, E7]", battleship.toString());
    }

    @Test
    void BattleshipWestToString() {
        Battleship battleship = new Battleship(new Coordinate('G', 7), Direction.West);

        assertEquals("Ship not sunk, hits: []", battleship.toString());

        battleship.Hit(new Coordinate('G',6));
        assertEquals("Ship not sunk, hits: [G6]", battleship.toString());

        battleship.Hit(new Coordinate('G',4));
        assertEquals("Ship not sunk, hits: [G4, G6]", battleship.toString());

        battleship.Hit(new Coordinate('G',7));
        assertEquals("Ship not sunk, hits: [G4, G6, G7]", battleship.toString());

        battleship.Hit(new Coordinate('G',5));
        assertEquals("Battleship sunk, hits: [G4, G5, G6, G7]", battleship.toString());
    }
}
