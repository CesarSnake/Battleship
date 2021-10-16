package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShipTests {
    Ship emptyShip;
    Ship shipC2C6HitMock;
    Ship shipB8E8Mock;
    Ship shipG4G6Mock;

    @BeforeAll
    void InitOnce() {
        emptyShip = new ShipEmptyMock();

    }

    @BeforeEach
    void SetUp() {
        shipC2C6HitMock = new ShipC2C6HitMock();
        shipB8E8Mock = new ShipB8E8Mock();
        shipG4G6Mock = new ShipG4G6Mock();
    }

    @Test
    void InvalidShipTest() {
        assertNotNull(emptyShip);
        assertNull(emptyShip.Coordinates());
        assertNull(emptyShip.Length());
        assertNull(emptyShip.Hits());

        // Hit is not implemented on the mock, it must do nothing
        emptyShip.Hit(new Coordinate('A',1));
        emptyShip.Hit(new Coordinate('A', 2));
        assertNull(emptyShip.Hits());

        assertNull(emptyShip.IsSunk());
    }

    @Test
    void HitNullCoordinateTest() {
        assertThrowsExactly(NullPointerException.class,
            () -> shipC2C6HitMock.Hit(null),
            "Cannot hit the ship because \"coordinate\" is null");
    }

    @Test
    void ShipC2C6withHitTest() {
        Ship shipMock = shipC2C6HitMock;
        // coordinate already hit
        Coordinate c3 = new Coordinate('C',3);

        for (int i = 2; i < 7; i++) {
            assertTrue(shipMock.Coordinates().contains(new Coordinate('C', i)));
        }

        assertEquals(5, shipMock.Length());
        assertEquals(1, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c3));

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        Coordinate c2 = new Coordinate('C',2);
        shipMock.Hit(c2);
        assertEquals(2, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c2));
        assertFalse(shipMock.IsSunk());

        // Coordinate already hit
        assertThrowsExactly(UnsupportedOperationException.class,
            () -> shipMock.Hit(c3),
            String.join("", "Coordinate: ", c3.toString(), " already hit"));

        assertEquals(2, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c3));
        assertFalse(shipMock.IsSunk());

        Coordinate c4 = new Coordinate('C',4);
        shipMock.Hit(c4);
        assertEquals(3, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c4));
        assertFalse(shipMock.IsSunk());

        Coordinate c5 = new Coordinate('C',5);
        shipMock.Hit(c5);
        assertEquals(4, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c5));
        assertFalse(shipMock.IsSunk());

        Coordinate c6 = new Coordinate('C',6);
        shipMock.Hit(c6);
        assertEquals(5, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c6));
        
        assertTrue(shipMock.IsSunk());
    }

    @Test
    void ShipB8E8Test() {
        Ship shipMock = shipB8E8Mock;
        for (char i = 'B'; i <= 'E'; i++) {
            assertTrue(shipMock.Coordinates().contains(new Coordinate(i, 8)));
        }

        assertEquals(4, shipMock.Length());
        assertTrue(shipMock.Hits().isEmpty());

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        int hits = 0;
        for (char i = 'B'; i <= 'E'; i++) {
            Coordinate cd = new Coordinate(i,8);
            shipMock.Hit(cd);
            hits++;

            assertEquals(hits, shipMock.Hits().size());
            assertTrue(shipMock.Hits().contains(cd));
            if (i != 'E') {
                assertFalse(shipMock.IsSunk());
                continue;
            }

            assertTrue(shipMock.IsSunk());
        }
    }

    @Test
    void ShipG4G6Test() {
        Ship shipMock = shipG4G6Mock;
        // Horizontal ship surrounded with "watter"

        for (int i = 4; i <= 6; i++) {
            assertTrue(shipMock.Coordinates().contains(new Coordinate('G', i)));
        }

        assertEquals(3, shipMock.Length());
        assertTrue(shipMock.Hits().isEmpty());

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        int hits = 0;
        for (int i = 4; i <= 6; i++)  {
            Coordinate cd = new Coordinate('G',i);
            shipMock.Hit(cd);
            hits++;

            assertEquals(hits, shipMock.Hits().size());
            assertTrue(shipMock.Hits().contains(cd));
            if (i != 6) {
                assertFalse(shipMock.IsSunk());
                continue;
            }

            assertTrue(shipMock.IsSunk());
        }
    }

    @Test
    void InvalidCoordinatesHorizontalShipAttackTest() {
        // test horizontal ship sunk
        Ship shipHorizontal = shipG4G6Mock;

        // Sink ship
        shipHorizontal.Hit(new Coordinate('G',4));
        shipHorizontal.Hit(new Coordinate('G',5));
        shipHorizontal.Hit(new Coordinate('G',6));
        assertTrue(shipHorizontal.IsSunk());

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);

                if (shipHorizontal.Hits().contains(cd)) {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipHorizontal.Hit(cd),
                        String.join("", "Coordinate: ", cd.toString(), " already hit"));
                } else {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipHorizontal.Hit(cd),
                        String.join("",shipHorizontal.getClass().getName(), " is not positioned on the coordinate: ", cd.toString()));
                }
            }
        }
    }

    @Test
    void InvalidCoordinatesVerticalShipAttackTest() {
        // test horizontal ship sunk
        Ship shipVertical = shipB8E8Mock;

        // Sink ship
        shipVertical.Hit(new Coordinate('B',8));
        shipVertical.Hit(new Coordinate('C',8));
        shipVertical.Hit(new Coordinate('D',8));
        shipVertical.Hit(new Coordinate('E',8));
        assertTrue(shipVertical.IsSunk());

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(i, j);

                if (shipVertical.Hits().contains(cd)) {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipVertical.Hit(cd),
                        String.join("", "Coordinate: ", cd.toString(), " already hit"));
                } else {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipVertical.Hit(cd),
                        String.join("",shipVertical.getClass().getName(), " is not positioned on the coordinate: ", cd.toString()));
                }
            }
        }
    }

    @Test
    void CarrierMockTest() {
        Carrier carrierMock = new CarrierMock(null, null);
        for(int i = 1; i <= 5; i++) {
            assertTrue(carrierMock.Coordinates().contains(new Coordinate('C', i)));
        }
        assertEquals(5, carrierMock.Length());
        assertEquals(carrierMock.Length(), carrierMock.Coordinates().size());
        assertTrue(carrierMock.Hits().isEmpty());
        assertFalse(carrierMock.IsSunk());

        for(int i = 1; i <= 5; i++) {
            carrierMock.Hit(new Coordinate('C', i));
        }
        assertTrue(carrierMock.IsSunk());

        assertTrue(carrierMock instanceof Ship);
        assertTrue(carrierMock instanceof Carrier);
    }

    @Test
    void BattleShipMockTest() {
        Battleship battleShipMock = new BattleshipMock(null, null);

        for(int i = 5; i <= 8; i++) {
            assertTrue(battleShipMock.Coordinates().contains(new Coordinate('B', i)));
        }
        assertEquals(4, battleShipMock.Length());
        assertEquals(battleShipMock.Length(), battleShipMock.Coordinates().size());
        assertTrue(battleShipMock.Hits().isEmpty());
        assertFalse(battleShipMock.IsSunk());

        for(int i = 5; i <= 8; i++) {
            battleShipMock.Hit(new Coordinate('B', i));
        }
        assertTrue(battleShipMock.IsSunk());

        assertTrue(battleShipMock instanceof Ship);
        assertTrue(battleShipMock instanceof Battleship);
    }

    @Test
    void CruiserMockTest() {
        Cruiser cruiserMock = new CruiserMock(null, null);

        for(int i = 8; i<= 10; i++) {
            assertTrue(cruiserMock.Coordinates().contains(new Coordinate('C', i)));
        }
        assertEquals(3, cruiserMock.Length());
        assertEquals(cruiserMock.Length(), cruiserMock.Coordinates().size());
        assertTrue(cruiserMock.Hits().isEmpty());
        assertFalse(cruiserMock.IsSunk());

        for(int i = 8; i<= 10; i++) {
            cruiserMock.Hit(new Coordinate('C', i));
        }
        assertTrue(cruiserMock.IsSunk());

        assertTrue(cruiserMock instanceof Ship);
        assertTrue(cruiserMock instanceof Cruiser);
    }

    @Test
    void DestroyerMockTest() {
        Destroyer destroyerMock = new DestroyerMock(null, null);

        for(int i = 1; i <= 2; i++) {
            assertTrue(destroyerMock.Coordinates().contains(new Coordinate('D', i)));
        }
        assertEquals(2, destroyerMock.Length());
        assertEquals(destroyerMock.Length(), destroyerMock.Coordinates().size());
        assertTrue(destroyerMock.Hits().isEmpty());
        assertFalse(destroyerMock.IsSunk());

        for(int i = 1; i <= 2; i++) {
            destroyerMock.Hit(new Coordinate('D', i));
        }
        assertTrue(destroyerMock.IsSunk());

        assertTrue(destroyerMock instanceof Ship);
        assertTrue(destroyerMock instanceof Destroyer);
    }

    @Test
    void SubmarineMockTest() {
        Submarine submarineMock = new SubmarineMock(null, null);

        assertTrue(submarineMock.Coordinates().contains(new Coordinate('H', 8)));
        assertEquals(1, submarineMock.Length());
        assertEquals(submarineMock.Length(), submarineMock.Coordinates().size());
        assertTrue(submarineMock.Hits().isEmpty());
        assertFalse(submarineMock.IsSunk());

        submarineMock.Hit(new Coordinate('H', 8));
        assertTrue(submarineMock.IsSunk());

        assertTrue(submarineMock instanceof Ship);
        assertTrue(submarineMock instanceof Submarine);
    }

    @Test
    void GenerateCoordinatesNullTest() {
        assertThrowsExactly(NullPointerException.class,
            ()-> new Ship().GenerateCoordinates(null, null, -1),
            "Cannot create a GenerateCoordinates because \"coordinate\" or \"direction\" is null");
}

    @Test
    void GenerateCoordinatesExceptionNorthTest() {
        // North
        for (int i = 1; i <= 10; i++) {
            int number = i;
            assertThrowsExactly(UnsupportedOperationException.class,
                () -> new Ship().GenerateCoordinates(new Coordinate('A', number), Direction.North, 1),
                String.join("",
                        "Cannot generate ", String.valueOf(1), " coordinates direction", Direction.North.toString()));
        }
    }

    @Test
    void GenerateCoordinatesExceptionEastTest() {
        // East
        for (char i = 'A'; i <= 'J'; i++) {
            char letter = i;
            assertThrowsExactly(UnsupportedOperationException.class,
                () -> new Ship().GenerateCoordinates(new Coordinate(letter, 10), Direction.East, 1),
                String.join("",
                        "Cannot generate ", String.valueOf(1), " coordinates direction", Direction.East.toString()));
        }
    }

    @Test
    void GenerateCoordinatesExceptionSouthTest() {
        // Shout
        for (int i = 1; i <= 10; i++) {
            int number = i;
            assertThrowsExactly(UnsupportedOperationException.class,
                () -> new Ship().GenerateCoordinates(new Coordinate('J', number), Direction.South, 1),
                String.join("",
                        "Cannot generate ", String.valueOf(1), " coordinates direction", Direction.South.toString()));
        }
    }

    @Test
    void GenerateCoordinatesExceptionWestTest() {
        // West
        for (char i = 'A'; i <='J' ; i++) {
            char letter = i;
            assertThrowsExactly(UnsupportedOperationException.class,
                ()-> new Ship().GenerateCoordinates(new Coordinate(letter,1), Direction.West, 1),
                String.join("",
                        "Cannot generate ", String.valueOf(1), " coordinates direction", Direction.West.toString()));
        }
    }

    @Test
    void GenerateCoordinatesNorthTest() {
        int increase = 3;
        for (int i = 1; i <= 10; i++) {
            List<Coordinate> generated = new Ship().GenerateCoordinates(new Coordinate('E', i), Direction.North, increase);

            assertEquals(increase+1, generated.size());
            for (char j = (char)('E'-increase); j <= 'E'; j++) {
                assertTrue(generated.contains(new Coordinate(j, i)));
            }
        }
    }

    @Test
    void GenerateCoordinatesEastTest() {
        int increase = 3;
        for (char i = 'A'; i <= 'J'; i++) {
            List<Coordinate> generated = new Ship().GenerateCoordinates(new Coordinate(i, 5), Direction.East, increase);

            assertEquals(increase+1, generated.size());
            for (int j = 5; j <= 5+increase; j++) {
                assertTrue(generated.contains(new Coordinate(i, j)));
            }
        }
    }

    @Test
    void GenerateCoordinatesSouthTest() {
        int increase = 3;
        for (int i = 1; i <= 10; i++) {
            List<Coordinate> generated = new Ship().GenerateCoordinates(new Coordinate('E', i), Direction.South, increase);

            assertEquals(increase+1, generated.size());
            for (char j = 'E'; j <= 'E'+increase; j++) {
                assertTrue(generated.contains(new Coordinate(j, i)));
            }
        }
    }

    @Test
    void GenerateCoordinatesWestTest() {
        int increase = 3;
        for (char i = 'A'; i <= 'J'; i++) {
            List<Coordinate> generated = new Ship().GenerateCoordinates(new Coordinate(i, 5), Direction.West, increase);

            assertEquals(increase+1, generated.size());
            for (int j = 5-increase; j <= 5; j++) {
                assertTrue(generated.contains(new Coordinate(i, j)));
            }
        }
    }
}
