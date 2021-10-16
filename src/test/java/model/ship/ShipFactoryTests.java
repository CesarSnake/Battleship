package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipFactoryTests {
    @Test
    void CreateShipNullTest() {
        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(null, null, null),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(null, new Coordinate('F',4), null),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(null, null, Direction.South),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(null, new Coordinate('G', 5), Direction.South),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(ShipType.Carrier, null, null),
        "Cannot create a ship because \"shipType\" or \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), null),
            "Cannot create a ship because \"shipType\" or \"coordinate\" or \"direction\" is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(ShipType.Carrier, null, Direction.North),
            "Cannot create a ship because \"shipType\" or \"coordinate\" or \"direction\" is null");
    }

    @Test
    void CreateCarrierShipTest() {
        Ship carrier = ShipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), Direction.East);

        assertNotNull(carrier);
        assertTrue(carrier instanceof Carrier);
    }

    @Test
    void CreateBattleshipTest() {
        Ship battleship = ShipFactory.CreateShip(ShipType.Battleship, new Coordinate('A',1), Direction.East);

        assertNotNull(battleship);
        assertTrue(battleship instanceof Battleship);
    }

    @Test
    void CreateCruiserTest() {
        Ship cruiser = ShipFactory.CreateShip(ShipType.Cruiser, new Coordinate('A',1), Direction.East);

        assertNotNull(cruiser);
        assertTrue(cruiser instanceof Cruiser);
    }

    @Test
    void CreateDestroyerTest() {
        Ship destroyer = ShipFactory.CreateShip(ShipType.Destroyer, new Coordinate('A',1), Direction.East);

        assertNotNull(destroyer);
        assertTrue(destroyer instanceof Destroyer);
    }

    @Test
    void CreateSubmarineTest() {
        Ship submarine = ShipFactory.CreateShip(ShipType.Submarine, new Coordinate('A',1), Direction.East);

        assertNotNull(submarine);
        assertTrue(submarine instanceof Submarine);
    }
}
