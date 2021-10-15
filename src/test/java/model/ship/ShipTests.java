package model.ship;

import model.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
        shipC2C6HitMock = new ShipC2C6HitMock();
    }

    @BeforeEach
    void SetUp() {
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
        emptyShip.Hit(new Coordinate("A",1));
        emptyShip.Hit(new Coordinate("A", 2));
        assertNull(emptyShip.Hits());

        assertNull(emptyShip.IsSunk());
    }

    @Test
    void ShipC2C6withHitTest() {
        Ship shipMock = shipC2C6HitMock;
        // coordinate already hit
        Coordinate c3 = new Coordinate("C",3);

        for (int i = 2; i < 7; i++) {
            assertTrue(shipMock.Coordinates().contains(new Coordinate("C", i)));
        }

        assertEquals(5, shipMock.Length());
        assertEquals(1, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c3));

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        Coordinate c2 = new Coordinate("C",2);
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

        Coordinate c4 = new Coordinate("C",4);
        shipMock.Hit(c4);
        assertEquals(3, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c4));
        assertFalse(shipMock.IsSunk());

        Coordinate c5 = new Coordinate("C",5);
        shipMock.Hit(c5);
        assertEquals(4, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c5));
        assertFalse(shipMock.IsSunk());

        Coordinate c6 = new Coordinate("C",6);
        shipMock.Hit(c6);
        assertEquals(5, shipMock.Hits().size());
        assertTrue(shipMock.Hits().contains(c6));
        
        assertTrue(shipMock.IsSunk());
    }

    @Test
    void ShipB8E8Test() {
        Ship shipMock = shipB8E8Mock;
        for (char i = 'B'; i <= 'E'; i++) {
            assertTrue(shipMock.Coordinates().contains(new Coordinate(String.valueOf(i), 8)));
        }

        assertEquals(4, shipMock.Length());
        assertTrue(shipMock.Hits().isEmpty());

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        int hits = 0;
        for (char i = 'B'; i <= 'E'; i++) {
            Coordinate cd = new Coordinate(String.valueOf(i),8);
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
            assertTrue(shipMock.Coordinates().contains(new Coordinate("G", i)));
        }

        assertEquals(3, shipMock.Length());
        assertTrue(shipMock.Hits().isEmpty());

        // Sink the ship
        assertFalse(shipMock.IsSunk());

        int hits = 0;
        for (int i = 4; i <= 6; i++)  {
            Coordinate cd = new Coordinate("G",i);
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
    void InvalidCoordinatesAttackTest() {
        // test with vertical ship and horizontal ship sunk
        Ship shipHorizontal = shipG4G6Mock;
        Ship shipVertical = shipB8E8Mock;

        // Sink ships
        shipHorizontal.Hit(new Coordinate("G",4));
        shipHorizontal.Hit(new Coordinate("G",5));
        shipHorizontal.Hit(new Coordinate("G",6));
        assertTrue(shipHorizontal.IsSunk());

        shipVertical.Hit(new Coordinate("B",8));
        shipVertical.Hit(new Coordinate("C",8));
        shipVertical.Hit(new Coordinate("D",8));
        shipVertical.Hit(new Coordinate("E",8));
        assertTrue(shipVertical.IsSunk());

        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cd = new Coordinate(String.valueOf(i), j);

                if (shipHorizontal.Hits().contains(cd)) {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipHorizontal.Hit(cd),
                        String.join("", "Coordinate: ", cd.toString(), " already hit"));
                } else {
                    assertThrowsExactly(UnsupportedOperationException.class,
                        () -> shipHorizontal.Hit(cd),
                        String.join("",shipHorizontal.getClass().getName(), " is not positioned on the coordinate: ", cd.toString()));
                }

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
}
