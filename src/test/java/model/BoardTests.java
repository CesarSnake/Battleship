package model;

import model.ship.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    Board board;

    @BeforeEach
    void Setup() {
        board = new Board();
    }

    @Test
    void HideSymbolTest() {
        assertEquals('·', board.HIDE_SYMBOL);
    }

    @Test
    void WaterSymbolTest() {
        assertEquals('~', board.WATER_SYMBOL);
    }

    @Test
    void HitSymbolTest() {
        assertEquals('/', board.HIT_SYMBOL);
    }

    @Test
    void DestroyedSymbolTest() {
        assertEquals('X', board.DESTROYED_SYMBOL);
    }

    @Test
    void BoardSizeTest() {
        // the board includes the numbers and letters to know the coordinate
        assertEquals(10, board.BOARD_SIZE);
    }

    @Test
    void CoordinatesUsedTest() {
        assertNotNull(board.CoordinatesUsed());
        assertEquals(0, board.CoordinatesUsed().size());
    }

    @Test
    void ShipsTest() {
        assertNotNull(board.Ships());
        assertEquals(0, board.Ships().size());
    }

    @Test
    void AddNullShipTest() {
        assertThrowsExactly(
            NullPointerException.class,
            () -> board.AddShip(null),
            "Cannot add null ships");
    }

    @Test
    void AddShipTest() {
        assertEquals(0, board.Ships().size());
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);

        board.AddShip(carrier);
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));
    }

    @Test
    void AddSameShipTest() {
        Carrier carrierA = new Carrier(new Coordinate('A',1), Direction.East);
        Carrier carrierB = new Carrier(new Coordinate('J',10), Direction.West);

        board.AddShip(carrierA);
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrierA));

        assertThrowsExactly(
            UnsupportedOperationException.class,
            ()-> board.AddShip(carrierA),
            String.join("", "The ship Carrier was already placed"));

        assertThrowsExactly(
            UnsupportedOperationException.class,
            ()-> board.AddShip(carrierB),
            String.join("", "The ship Carrier was already placed"));
    }

    @Test
    void AddShipSamePlaceOtherTest() {
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);
        Cruiser cruiser = new Cruiser(new Coordinate('A', 3), Direction.South);

        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertFalse(board.AddShip(cruiser));
    }

    @Test
    void AddHitShipTest() {
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);

        for (int i = 1; i <= 5; i++) {
            carrier.Hit(new Coordinate('A', i));

            assertThrowsExactly(
                UnsupportedOperationException.class,
                ()-> board.AddShip(carrier),
                String.join("", "A ship hit or destroyed cannot be placed"));
        }
    }

    @Test
    void GetCellNullTest() {
        assertThrowsExactly(
            NullPointerException.class,
            ()-> board.GetCell(null),
            "Cannot get a cell with a null coordinate");
    }

    @Test
    void GetCellTest() {
        // the board must contain all the playable cells
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Cell cell = board.GetCell(new Coordinate(i,j));

                assertNotNull(cell);
                assertEquals(new Coordinate(i,j), cell.Coordinate());
                assertEquals(CellStatus.Hide, cell.Status());

                assertFalse(cell.HasShip());
            }
        }
    }

    @Test
    void GetCellWithShipTest() {
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);

        for (int i = 1; i <= 5; i++) {
            Cell emptyCell = board.GetCell(new Coordinate('A',i));
            assertFalse(emptyCell.HasShip());
        }

        board.AddShip(carrier);
        for (int i = 1; i <= 5; i++) {
            Cell shipCell = board.GetCell(new Coordinate('A',i));
            assertTrue(shipCell.HasShip());
        }
    }

    @Test
    void HitCellNullCoordinateTest() {
        assertThrowsExactly(
            NullPointerException.class,
            ()-> board.HitCell(null),
            "Cannot hit a cell with a null coordinate");
    }

    @Test
    void HitCellHideTest() {
        // all cells in an empty board are hide and get water after the hit

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                assertEquals(CellStatus.Watter, board.GetCell(cd).Status());
            }
        }
    }

    @Test
    void HitCellWatterTest() {
        // a cell cannot be hit twice

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                assertEquals(CellStatus.Watter, board.GetCell(cd).Status());

                assertThrowsExactly(
                    UnsupportedOperationException.class,
                    ()-> board.HitCell(cd),
                    String.join("", "Coordinate: ", cd.toString(), " already hit"));
            }
        }
    }

    @Test
    void HitCellHitTest() {
        Carrier carrier = new Carrier(new Coordinate('F',3), Direction.East);

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                if(carrier.Coordinates().contains(cd)) {
                    // hit
                    if (!cd.equals(new Coordinate('F', 7))) {
                        assertEquals(CellStatus.Hit, board.GetCell(cd).Status());
                        continue;
                    }

                    // destroyed
                    assertEquals(CellStatus.Destroyed, board.GetCell(cd).Status());

                } else {
                   assertEquals(CellStatus.Watter, board.GetCell(cd).Status());
                }
            }
        }
    }

    @Test
    void HitCellDestroyedTest() {
        Carrier carrier = new Carrier(new Coordinate('F',3), Direction.East);
        board.AddShip(carrier);

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                if(carrier.Coordinates().contains(cd)) {
                    // hit
                    if (!cd.equals(new Coordinate('F', 7))) {
                        assertTrue(board.GetCell(cd).HasShip());
                        assertEquals(CellStatus.Hit, board.GetCell(cd).Status());
                        continue;
                    }

                    // destroyed
                    assertTrue(board.GetCell(cd).HasShip());
                    assertEquals(CellStatus.Destroyed, board.GetCell(cd).Status());

                } else {
                    assertFalse(board.GetCell(cd).HasShip());
                    assertEquals(CellStatus.Watter, board.GetCell(cd).Status());
                }
            }
        }

        // All the cells of the ship must be destroyed
        for (int i = 3; i <= 7; i++) {
            Coordinate cd = new Coordinate('F', i);
            assertEquals(CellStatus.Destroyed, board.GetCell(cd).Status());
        }
    }

    @Test
    void DestroyShipTest() {
        Carrier carrier = new Carrier(new Coordinate('F',3), Direction.East);
        board.AddShip(carrier);

        // ship hit
        for (int i = 3; i < 7; i++) {
            Coordinate cd = new Coordinate('F', i);
            assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

            board.HitCell(cd);
            assertEquals(CellStatus.Hit, board.GetCell(cd).Status());
        }

        // ship destroyed
        for (int i = 3; i <= 7; i++) {
            Coordinate cd = new Coordinate('F', i);
            assertEquals(CellStatus.Destroyed, board.GetCell(cd).Status());
        }
    }

    // toString() tests
    
    // Empty Board:
    // # 1 2 3 4 5 6 7 8 9 10
    // A · · · · · · · · · ·
    // B · · · · · · · · · ·
    // C · · · · · · · · · ·
    // D · · · · · · · · · ·
    // E · · · · · · · · · ·
    // F · · · · · · · · · ·
    // G · · · · · · · · · ·
    // H · · · · · · · · · ·
    // I · · · · · · · · · ·
    // J · · · · · · · · · ·
    @Test
    void ToStringEmptyBoardTest() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("# 1 2 3 4 5 6 7 8 9 10");
        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                line.add("·");
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());
    }

    @Test
    void ToStringShipNotDiscoveredTest() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("# 1 2 3 4 5 6 7 8 9 10");
        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                line.add("·");
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());

        assertTrue(board.AddShip(new Carrier(new Coordinate('E', 5), Direction.South)));
        assertEquals(String.join("\n", columns), board.toString());
    }


    // Ship Hit Board:
    // # 1 2 3 4 5 6 7 8 9 10
    // A · · · · · · · · · ·
    // B · · · · · · · · · ·
    // C · · · · · · · · · ·
    // D · · · · · · · · · ·
    // E · · · · · · · · · ·
    // F · · · · / · · · · ·
    // G · · · · / · · · · ·
    // H · · · · / · · · · ·
    // I · · · · / · · · · ·
    // J · · · · · · · · · ·
    @Test
    void ToStringShipHitTest() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("# 1 2 3 4 5 6 7 8 9 10");
        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                line.add("·");
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());

        Carrier carrier = new Carrier(new Coordinate('E', 5), Direction.South);
        assertTrue(board.AddShip(carrier));


        columns.clear();
        columns.add("# 1 2 3 4 5 6 7 8 9 10");
        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                
                // We want to hit the ship but not destroy it
                if(carrier.Coordinates().contains(cd) &&
                    !cd.equals(new Coordinate('E', 5))) {
                        board.HitCell(cd);
                        line.add("\\");
                } else {
                    line.add("·");
                }
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());
    }

    // ShipDestroyed Board:
    // # 1 2 3 4 5 6 7 8 9 10
    // A · · · · · · · · · ·
    // B · · · · · · · · · ·
    // C · · · · · · · · · ·
    // D · · · · · · · · · ·
    // E · · · · X · · · · ·
    // F · · · · X · · · · ·
    // G · · · · X · · · · ·
    // H · · · · X · · · · ·
    // I · · · · X · · · · ·
    // J · · · · · · · · · ·
    @Test
    void ToStringShipDestroyedTest() {
        ArrayList<String> columns = new ArrayList<>();
        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                line.add("·");
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());


        Carrier carrier = new Carrier(new Coordinate('E', 5), Direction.South);
        assertTrue(board.AddShip(carrier));

        columns.clear();
        columns.add("# 1 2 3 4 5 6 7 8 9 10");
                        
        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));
            
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);

                if(carrier.Coordinates().contains(cd)) {
                    board.HitCell(cd);
                    line.add("X");
                } else {
                    line.add("·");
                }
            }
            columns.add(String.join(" ", line));
        }

        assertEquals(String.join("\n", columns), board.toString());
    }
}
