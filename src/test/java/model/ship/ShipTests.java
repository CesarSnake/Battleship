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
    Ship shipC2C6Mock;

    @BeforeAll
    void InitOnce() {
        emptyShip = new ShipEmptyMock();
        shipC2C6Mock = new ShipC2C6Mock();
    }

    @BeforeEach
    void SetUp() {}

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
    void ShipC2C6MockTest() {
        // coordinate already hit
        Coordinate c3 = new Coordinate("C",3);

        for (int i = 2; i < 7; i++) {
            assertTrue(shipC2C6Mock.Coordinates().contains(new Coordinate("C", i)));
        }

        assertEquals(5, shipC2C6Mock.Length());
        assertEquals(1, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c3));

        //Sink the ship
        assertFalse(shipC2C6Mock.IsSunk());

        Coordinate c2 = new Coordinate("C",2);
        shipC2C6Mock.Hit(c2);
        assertEquals(2, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c2));
        assertFalse(shipC2C6Mock.IsSunk());

        // Coordinate already hit
        assertThrowsExactly(UnsupportedOperationException.class,
            () -> shipC2C6Mock.Hit(c3),
            String.join("", "Coordinate: ", c3.toString(), " already hit"));

        assertEquals(2, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c3));
        assertFalse(shipC2C6Mock.IsSunk());

        Coordinate c4 = new Coordinate("C",4);
        shipC2C6Mock.Hit(c4);
        assertEquals(3, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c4));
        assertFalse(shipC2C6Mock.IsSunk());

        Coordinate c5 = new Coordinate("C",5);
        shipC2C6Mock.Hit(c5);
        assertEquals(4, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c5));
        assertFalse(shipC2C6Mock.IsSunk());

        Coordinate c6 = new Coordinate("C",6);
        shipC2C6Mock.Hit(c6);
        assertEquals(5, shipC2C6Mock.Hits().size());
        assertTrue(shipC2C6Mock.Hits().contains(c6));
        
        assertTrue(shipC2C6Mock.IsSunk());
    }
}
