package model;

import model.ship.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    Board board;
    Coordinate i2;
    Coordinate e8;
    Coordinate c4;
    Coordinate e4;
    Coordinate h10;
    Carrier carrier;
    Battleship battleship;
    Cruiser cruiser;
    Destroyer destroyer;
    Submarine submarine;
    String emptyBoard;

    @BeforeEach
    void Setup() {
        board = new Board();
        i2 = new Coordinate('I',2);
        e8 = new Coordinate('E',8);
        c4 = new Coordinate('C',4);
        e4 = new Coordinate('E',4);
        h10 = new Coordinate('H',10);

        carrier = new Carrier(i2, Direction.East);
        battleship = new Battleship(e8, Direction.North);
        cruiser = new Cruiser(c4, Direction.West);
        destroyer = new Destroyer(e4, Direction.South);
        submarine = new Submarine(h10, Direction.East);
        
        emptyBoard = TestUtils.GetOutputFromFile("emptyBoard.out");
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Hide symbol Test")
    void HideSymbolTest() {
        assertEquals('·', board.HIDE_SYMBOL);
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Water symbol Test")
    void WaterSymbolTest() {
        assertEquals('~', board.WATER_SYMBOL);
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Hit symbol Test")
    void HitSymbolTest() {
        assertEquals('/', board.HIT_SYMBOL);
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Destroyed symbol Test")
    void DestroyedSymbolTest() {
        assertEquals('X', board.DESTROYED_SYMBOL);
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Board size Test")
    void BoardSizeTest() {
        // the board is 10x10 (A-J, 1-10)
        assertEquals(10, board.BOARD_SIZE);
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Coordinates used list Test")
    void CoordinatesUsedTest() {
        assertNotNull(board.CoordinatesUsed());
        assertEquals(0, board.CoordinatesUsed().size());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Ships list Test")
    void ShipsTest() {
        assertNotNull(board.Ships());
        assertEquals(0, board.Ships().size());
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Add null ship parameter Test")
    void AddNullShipTest() {
        assertThrowsExactly(
            NullPointerException.class,
            () -> board.AddShip(null),
            "Cannot add null ship");
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Add the same ship twice Test")
    void AddSameShipTest() {
        Carrier carrierA = new Carrier(new Coordinate('A',1), Direction.East);
        Carrier carrierB = new Carrier(new Coordinate('J',10), Direction.West);

        board.AddShip(carrierA);
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrierA));

        assertThrowsExactly(
            UnsupportedOperationException.class,
            ()-> board.AddShip(carrierA),
           "The ship Carrier was already placed");

        assertThrowsExactly(
            UnsupportedOperationException.class,
            ()-> board.AddShip(carrierB),
            "The ship Carrier was already placed");
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Add a ship with hits Test")
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
    @Tag("conditionCoverage")
    @DisplayName("Add a ship in the same place than other ship Test")
    void AddShipSamePlaceOtherTest() {
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);
        Cruiser cruiser = new Cruiser(new Coordinate('A', 3), Direction.South);

        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertFalse(board.AddShip(cruiser));
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Add ship Test")
    void AddShipTest() {
        assertEquals(0, board.Ships().size());
        Carrier carrier = new Carrier(new Coordinate('A',1), Direction.East);

        board.AddShip(carrier);
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Get Cell null coordinate parameter Test")
    void GetCellNullTest() {
        assertThrowsExactly(
            NullPointerException.class,
            ()-> board.GetCell(null),
            "Cannot get a cell with a null coordinate");
    }

    @Test
    @Tag("partitionEquivalence")
    @Tag("loopTesting")
    @DisplayName("Get Cell Test")
    void GetCellTest() {
        // the board must contain all the playable cells
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Cell cell = board.GetCell(coordinate);

                assertNotNull(cell);
                assertEquals(coordinate, cell.Coordinate());
                assertEquals(CellStatus.Hide, cell.Status());

                assertFalse(cell.HasShip());
            }
        }
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Get shell filled by ship Test")
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
    @Tag("conditionCoverage")
    @DisplayName("Hit cell null coordinate parameter Test")
    void HitCellNullCoordinateTest() {
        assertThrowsExactly(
            NullPointerException.class,
            ()-> board.HitCell(null),
            "Cannot hit a cell with a null coordinate");
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Hit cell hide Test")
    void HitCellHideTest() {
        // all cells in an empty board are hide and get water after the hit
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                assertEquals(CellStatus.Water, board.GetCell(cd).Status());
            }
        }
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Hit cell watter Test")
    void HitCellWaterTest() {
        // a cell cannot be hit twice
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);
                assertEquals(CellStatus.Hide, board.GetCell(cd).Status());

                board.HitCell(cd);
                assertEquals(CellStatus.Water, board.GetCell(cd).Status());

                assertThrowsExactly(
                    UnsupportedOperationException.class,
                    ()-> board.HitCell(cd),
                    String.join("", "Coordinate: ", cd.toString(), " already hit"));
            }
        }
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Hit cell hit Test")
    void HitCellHitTest() {
        Carrier carrier = new Carrier(new Coordinate('F',3), Direction.East);
        board.AddShip(carrier);

        // place a ship and check all his cells are hit when it is hit (and destroyed when last one is hit)
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
                   assertEquals(CellStatus.Water, board.GetCell(cd).Status());
                }
            }
        }
    }

    @Test

    @Tag("partitionEquivalence")
    @DisplayName("Hit cell destroyed Test")
    void HitCellDestroyedTest() {
        Carrier carrier = new Carrier(new Coordinate('F',3), Direction.East);
        board.AddShip(carrier);

        // this test is to check that all the cells of a ship hit changes to destroyed
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
                    assertEquals(CellStatus.Water, board.GetCell(cd).Status());
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
    @Tag("partitionEquivalence")
    @DisplayName("Destroy a ship Test")
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

        // destroy ship
        Coordinate destroyCoordinate = new Coordinate('F',7);
        assertEquals(CellStatus.Destroyed, board.HitCell(destroyCoordinate));

        // check ship is destroyed
        for (int i = 3; i <= 7; i++) {
            Coordinate cd = new Coordinate('F', i);
            assertEquals(CellStatus.Destroyed, board.GetCell(cd).Status());
        }
    }

    // toString() Tests
    
    @Test
    @Tag("unitTest")
    @DisplayName("toString (empty board) Test")
    void ToStringEmptyBoardTest() {
        assertEquals(emptyBoard, board.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (ship not discovered) Test")
    void ToStringShipNotDiscoveredTest() {
        assertTrue(board.AddShip(new Carrier(new Coordinate('E', 5), Direction.South)));
        assertEquals(emptyBoard, board.toString());
    }


    @Test
    @Tag("unitTest")
    @DisplayName("toString (ship hit) Test")
    void ToStringShipHitTest() {
        assertEquals(emptyBoard, board.toString());

        Carrier carrier = new Carrier(new Coordinate('E', 5), Direction.South);
        assertTrue(board.AddShip(carrier));
        assertEquals(emptyBoard, board.toString());

        for (char i = 'F'; i <= 'I'; i++) {
            Coordinate cd = new Coordinate(i, 5);
            board.HitCell(cd);
        }

        assertEquals(TestUtils.GetOutputFromFile("boardTestShipHit.out"), board.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("toString (ship destroyed) Test")
    void ToStringShipDestroyedTest() {
        assertEquals(emptyBoard, board.toString());

        Carrier carrier = new Carrier(new Coordinate('E', 5), Direction.South);
        assertTrue(board.AddShip(carrier));

        carrier.Coordinates().forEach(coordinate -> board.HitCell(coordinate));
        assertEquals(TestUtils.GetOutputFromFile("boardTestShipDestroyed.out"), board.toString());
    }

    // Board with all the ships hide
    @Test
    @Tag("acceptanceTest")
    @DisplayName("Complete board with ships hide")
    void CompleteBoardHideTest() {
        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertTrue(board.AddShip(battleship));
        assertEquals(2, board.Ships().size());
        assertTrue(board.Ships().contains(battleship));

        assertTrue(board.AddShip(cruiser));
        assertEquals(3, board.Ships().size());
        assertTrue(board.Ships().contains(cruiser));

        assertTrue(board.AddShip(destroyer));
        assertEquals(4, board.Ships().size());
        assertTrue(board.Ships().contains(destroyer));

        assertTrue(board.AddShip(submarine));
        assertEquals(5, board.Ships().size());
        assertTrue(board.Ships().contains(submarine));

        assertEquals(emptyBoard, board.toString());
    }

    @Test
    @Tag("acceptanceTest")
    @DisplayName("Complete board water cells discovered")
    void CompleteBoardWaterDiscoveredTest() {
        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertTrue(board.AddShip(battleship));
        assertEquals(2, board.Ships().size());
        assertTrue(board.Ships().contains(battleship));

        assertTrue(board.AddShip(cruiser));
        assertEquals(3, board.Ships().size());
        assertTrue(board.Ships().contains(cruiser));

        assertTrue(board.AddShip(destroyer));
        assertEquals(4, board.Ships().size());
        assertTrue(board.Ships().contains(destroyer));

        assertTrue(board.AddShip(submarine));
        assertEquals(5, board.Ships().size());
        assertTrue(board.Ships().contains(submarine));

        // Board with all Water discovered
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i,j);

                if (board.Ships().stream().anyMatch(ship ->
                    ship.Coordinates().contains(cd))) {
                    continue;
                }

                assertEquals(CellStatus.Water, board.HitCell(cd));
            }
        }

        assertEquals(TestUtils.GetOutputFromFile("boardTestWaterDiscovered.out"), board.toString());
    }

    @Test
    @Tag("acceptanceTest")
    @DisplayName("Complete board all ships hit")
    void CompleteBoardShipsHitsTest() {
        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertTrue(board.AddShip(battleship));
        assertEquals(2, board.Ships().size());
        assertTrue(board.Ships().contains(battleship));

        assertTrue(board.AddShip(cruiser));
        assertEquals(3, board.Ships().size());
        assertTrue(board.Ships().contains(cruiser));

        assertTrue(board.AddShip(destroyer));
        assertEquals(4, board.Ships().size());
        assertTrue(board.Ships().contains(destroyer));

        assertTrue(board.AddShip(submarine));
        assertEquals(5, board.Ships().size());
        assertTrue(board.Ships().contains(submarine));

        // Board with all the ships hits
        List<Coordinate> coordinateNotHitList = List.of(i2, e8, c4, e4, h10);
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i,j);

                // we want to hit all the possible coordinates of the ship but not destroy it
                if (coordinateNotHitList.contains(cd)) {
                    continue;
                }

                if (board.Ships().stream().anyMatch(ship -> ship.Coordinates().contains(cd))) {
                    assertEquals(CellStatus.Hit, board.HitCell(cd));
                } else {
                    assertEquals(CellStatus.Water, board.HitCell(cd));
                }
            }
        }

        assertEquals(TestUtils.GetOutputFromFile("boardTestAllShipsHit.out"), board.toString());
    }

    @Test
    @Tag("acceptanceTest")
    @DisplayName("Complete board all ships destroyed")
    void CompleteBoardShipsDestroyedTest() {
        assertTrue(board.AddShip(carrier));
        assertEquals(1, board.Ships().size());
        assertTrue(board.Ships().contains(carrier));

        assertTrue(board.AddShip(battleship));
        assertEquals(2, board.Ships().size());
        assertTrue(board.Ships().contains(battleship));

        assertTrue(board.AddShip(cruiser));
        assertEquals(3, board.Ships().size());
        assertTrue(board.Ships().contains(cruiser));

        assertTrue(board.AddShip(destroyer));
        assertEquals(4, board.Ships().size());
        assertTrue(board.Ships().contains(destroyer));

        assertTrue(board.AddShip(submarine));
        assertEquals(5, board.Ships().size());
        assertTrue(board.Ships().contains(submarine));

        // Board with all the ships destroyed
        List<Coordinate> destroyedCoordinateList = List.of(c4, e8, h10,
            new Coordinate('F', 4), new Coordinate('I', 6));

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i,j);

                // we want to hit all the possible coordinates of the ship but not destroy it
                if (board.Ships().stream().anyMatch(ship -> ship.Coordinates().contains(cd))) {
                    if (destroyedCoordinateList.contains(cd)) {
                        assertEquals(CellStatus.Destroyed, board.HitCell(cd));
                    } else {
                        assertEquals(CellStatus.Hit, board.HitCell(cd));
                    }
                } else {
                    assertEquals(CellStatus.Water, board.HitCell(cd));
                }
            }
        }

        assertEquals(TestUtils.GetOutputFromFile("boardTestAllShipsDestroyed.out"), board.toString());
    }
}
