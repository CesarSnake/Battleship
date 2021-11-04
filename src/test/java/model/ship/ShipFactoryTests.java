package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipFactoryTests {
    @Test
    @Tag("decisionCoverage")
    @DisplayName("Create ship null parameters Test")
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
        "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), null),
            "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> ShipFactory.CreateShip(ShipType.Carrier, null, Direction.North),
            "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Create carrier Test")
    void CreateCarrierShipTest() {
        Ship carrier = ShipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), Direction.East);

        assertNotNull(carrier);
        assertTrue(carrier instanceof Carrier);
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Create battleship Test")
    void CreateBattleshipTest() {
        Ship battleship = ShipFactory.CreateShip(ShipType.Battleship, new Coordinate('A',1), Direction.East);

        assertNotNull(battleship);
        assertTrue(battleship instanceof Battleship);
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Create cruiser Test")
    void CreateCruiserTest() {
        Ship cruiser = ShipFactory.CreateShip(ShipType.Cruiser, new Coordinate('A',1), Direction.East);

        assertNotNull(cruiser);
        assertTrue(cruiser instanceof Cruiser);
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Create destroyer Test")
    void CreateDestroyerTest() {
        Ship destroyer = ShipFactory.CreateShip(ShipType.Destroyer, new Coordinate('A',1), Direction.East);

        assertNotNull(destroyer);
        assertTrue(destroyer instanceof Destroyer);
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Create submarine Test")
    void CreateSubmarineTest() {
        Ship submarine = ShipFactory.CreateShip(ShipType.Submarine, new Coordinate('A',1), Direction.East);

        assertNotNull(submarine);
        assertTrue(submarine instanceof Submarine);
    }
}
