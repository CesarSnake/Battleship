package model;

import model.ship.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CellTests {
    Coordinate a1, j1, a10, j10, e5;
    Cell cellA1, cellJ1, cellA10, cellJ10, cellE5;

    @BeforeEach
    void SetUp() {
        a1 = new Coordinate('A',1);
        j1 = new Coordinate('J',1);
        a10 = new Coordinate('A',10);
        j10 = new Coordinate('J',10);
        e5 = new Coordinate('E',5);

        cellA1 = new Cell(a1);
        cellJ1 = new Cell(j1);
        cellA10 = new Cell(a10);
        cellJ10 = new Cell(j10);
        cellE5 = new Cell(e5);
    }

    @Test
    void ConstructorNullTest() {
        assertThrowsExactly(NullPointerException.class,
            ()-> new Cell(null),
            "Cannot create a Cell because \"coordinate\" is null");
    }

    @Test
    void GettersTest() {
        Cell c1 = new Cell(a1);

        assertEquals(a1, c1.Coordinate());
        assertEquals(CellStatus.Hide, c1.Status());

        Cell c2 = new Cell(j1);
        assertEquals(j1, c2.Coordinate());
        assertEquals(CellStatus.Hide, c1.Status());
    }

    @Test
    void SetShipNullTest() {
        assertThrowsExactly(NullPointerException.class,
            ()-> cellA1.SetShip(null),
            "Cannot set a null ship");
    }

    @Test
    void SetInvalidShipTest() {
        Cruiser cruiser = new Cruiser(e5, Direction.East);

        assertThrowsExactly(UnsupportedOperationException.class,
            ()-> cellA1.SetShip(cruiser),
            String.join("",
            "The ship Cruiser has different coordinates than this Cell ", cellA1.toString()));
    }

    @Test
    void SetShipCellFilled() {
        Submarine submarine = new Submarine(a1, Direction.East);
        cellA1.SetShip(submarine);

        assertTrue(cellA1.HasShip());
        assertNotNull(cellA1.GetShip());

        assertThrowsExactly(UnsupportedOperationException.class,
            ()-> cellA1.SetShip(submarine),
            "This cell has already a ship");
    }

    @Test void SetShipTest() {
        Carrier carrier = new Carrier(a1, Direction.East);

        Cell cellA1 = new Cell(new Coordinate('A',1));
        Cell cellA2 = new Cell(new Coordinate('A',2));
        Cell cellA3 = new Cell(new Coordinate('A',3));
        Cell cellA4 = new Cell(new Coordinate('A',4));
        Cell cellA5 = new Cell(new Coordinate('A',5));

        cellA1.SetShip(carrier);
        cellA2.SetShip(carrier);
        cellA3.SetShip(carrier);
        cellA4.SetShip(carrier);
        cellA5.SetShip(carrier);

        assertTrue(cellA1.HasShip());
        assertTrue(cellA2.HasShip());
        assertTrue(cellA3.HasShip());
        assertTrue(cellA4.HasShip());
        assertTrue(cellA5.HasShip());
    }

    @Test
    void HasShipTest() {
        assertFalse(cellA1.HasShip());
        assertFalse(cellJ1.HasShip());
        assertFalse(cellA10.HasShip());
        assertFalse(cellJ10.HasShip());
        assertFalse(cellE5.HasShip());
    }

    @Test
    void GetShipTest() {
        Cell cellE5 = new Cell(new Coordinate('E',5));
        Cell cellE6 = new Cell(new Coordinate('E',6));

        assertNull(cellE5.GetShip());
        assertNull(cellE6.GetShip());

        Destroyer destroyer = new Destroyer(e5, Direction.East);

        cellE5.SetShip(destroyer);
        cellE6.SetShip(destroyer);

        assertNotNull(cellE5.GetShip());
        assertNotNull(cellE6.GetShip());

        assertTrue(cellE5.HasShip());
        assertTrue(cellE6.HasShip());
    }

    @Test
    void HitExceptionTest(){
        assertEquals(CellStatus.Hide, cellA1.Status());

        cellA1.Hit();
        assertEquals(CellStatus.Watter, cellA1.Status());

        assertThrowsExactly(UnsupportedOperationException.class,
            ()-> cellA1.Hit(),
            String.join("", "Coordinate: ", cellA1.toString(), " already hit"));
    }

    @Test
    void HitExceptionShipTest() {
        Cell cellE5 = new Cell(new Coordinate('E',5));
        Cell cellE6 = new Cell(new Coordinate('E',6));

        assertNull(cellE5.GetShip());
        assertNull(cellE6.GetShip());

        Destroyer destroyer = new Destroyer(e5, Direction.East);

        cellE5.SetShip(destroyer);
        cellE6.SetShip(destroyer);

        assertEquals(CellStatus.Hide, cellE5.Status());
        assertEquals(CellStatus.Hide, cellE6.Status());

        assertEquals(CellStatus.Hit, cellE5.Hit());
        assertEquals(CellStatus.Destroyed, cellE6.Hit());
    }

    @Test
    void HitTest() {
        assertEquals(CellStatus.Hide, cellA1.Status());
        assertEquals(CellStatus.Hide, cellJ1.Status());
        assertEquals(CellStatus.Hide, cellA10.Status());
        assertEquals(CellStatus.Hide, cellJ10.Status());
        assertEquals(CellStatus.Hide, cellE5.Status());

        cellA1.Hit();
        cellJ1.Hit();
        cellA10.Hit();
        cellJ10.Hit();
        cellE5.Hit();

        assertEquals(CellStatus.Watter, cellA1.Status());
        assertEquals(CellStatus.Watter, cellJ1.Status());
        assertEquals(CellStatus.Watter, cellA10.Status());
        assertEquals(CellStatus.Watter, cellJ10.Status());
        assertEquals(CellStatus.Watter, cellE5.Status());
    }

    @Test
    void SetShipCarrierTest() {
        Carrier carrier = new Carrier(a1, Direction.South);

        ArrayList<Cell> cells = new ArrayList<>();
        for (Coordinate coordinate: carrier.Coordinates()) {
            cells.add(new Cell(coordinate));
        }

        cells.forEach((cell)-> cell.SetShip(carrier));

        cells.forEach((cell)-> {
            assertTrue(cell.HasShip());
            assertNotNull(cell.GetShip());
            assertTrue(cell.GetShip() instanceof Carrier);
        });

        // Sink the ship
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(CellStatus.Hide, cells.get(i).Status());

            if (i != cells.size() - 1) {
                assertEquals(CellStatus.Hit, cells.get(i).Hit());
            } else {
                assertEquals(CellStatus.Destroyed, cells.get(i).Hit());
            }
        }
    }

    @Test
    void SetShipBattleshipTest() {
        Battleship battleship = new Battleship(j1, Direction.North);

        ArrayList<Cell> cells = new ArrayList<>();
        for (Coordinate coordinate: battleship.Coordinates()) {
            cells.add(new Cell(coordinate));
        }

        cells.forEach((cell)-> cell.SetShip(battleship));

        cells.forEach((cell)-> {
            assertTrue(cell.HasShip());
            assertNotNull(cell.GetShip());
            assertTrue(cell.GetShip() instanceof Battleship);
        });

        // Sink the ship
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(CellStatus.Hide, cells.get(i).Status());

            if (i != cells.size() - 1) {
                assertEquals(CellStatus.Hit, cells.get(i).Hit());
            } else {
                assertEquals(CellStatus.Destroyed, cells.get(i).Hit());
            }
        }
    }

    @Test
    void SetShipCruiserTest() {
        Cruiser cruiser = new Cruiser(a10, Direction.West);

        ArrayList<Cell> cells = new ArrayList<>();
        for (Coordinate coordinate: cruiser.Coordinates()) {
            cells.add(new Cell(coordinate));
        }

        cells.forEach((cell)-> cell.SetShip(cruiser));

        cells.forEach((cell)-> {
            assertTrue(cell.HasShip());
            assertNotNull(cell.GetShip());
            assertTrue(cell.GetShip() instanceof Cruiser);
        });

        // Sink the ship
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(CellStatus.Hide, cells.get(i).Status());

            if (i != cells.size() - 1) {
                assertEquals(CellStatus.Hit, cells.get(i).Hit());
            } else {
                assertEquals(CellStatus.Destroyed, cells.get(i).Hit());
            }
        }
    }

    @Test
    void SetShipDestroyerTest() {
        Destroyer destroyer = new Destroyer(e5, Direction.North);

        ArrayList<Cell> cells = new ArrayList<>();
        for (Coordinate coordinate: destroyer.Coordinates()) {
            cells.add(new Cell(coordinate));
        }

        cells.forEach((cell)-> cell.SetShip(destroyer));

        cells.forEach((cell)-> {
            assertTrue(cell.HasShip());
            assertNotNull(cell.GetShip());
            assertTrue(cell.GetShip() instanceof Destroyer);
        });

        // Sink the ship
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(CellStatus.Hide, cells.get(i).Status());

            if (i != cells.size() - 1) {
                assertEquals(CellStatus.Hit, cells.get(i).Hit());
            } else {
                assertEquals(CellStatus.Destroyed, cells.get(i).Hit());
            }
        }
    }

    @Test
    void SetShipSubmarineTest() {
        Submarine destroyer = new Submarine(j10, Direction.East);

        ArrayList<Cell> cells = new ArrayList<>();
        for (Coordinate coordinate: destroyer.Coordinates()) {
            cells.add(new Cell(coordinate));
        }

        cells.forEach((cell)-> cell.SetShip(destroyer));

        cells.forEach((cell)-> {
            assertTrue(cell.HasShip());
            assertNotNull(cell.GetShip());
            assertTrue(cell.GetShip() instanceof Submarine);
        });

        // Sink the ship
        for (int i = 0; i < cells.size(); i++) {
            assertEquals(CellStatus.Hide, cells.get(i).Status());

            if (i != cells.size() - 1) {
                assertEquals(CellStatus.Hit, cells.get(i).Hit());
            } else {
                assertEquals(CellStatus.Destroyed, cells.get(i).Hit());
            }
        }
    }
}
