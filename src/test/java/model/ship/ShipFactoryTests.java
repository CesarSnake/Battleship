package model.ship;

import model.Coordinate;
import model.Direction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShipFactoryTests {
    ShipFactory shipFactory;

    @BeforeEach
    void Setup() {
        shipFactory = new ShipFactory();
    }

    @Test
    @Order(1)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Create ship null parameters Test")
    void CreateShipNullTest() {
        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(null, null, null),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(null, new Coordinate('F',4), null),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(null, null, Direction.South),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(null, new Coordinate('G', 5), Direction.South),
            "The are not ships of the shipType provided");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(ShipType.Carrier, null, null),
        "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), null),
            "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");

        assertThrowsExactly(NullPointerException.class,
            ()-> shipFactory.CreateShip(ShipType.Carrier, null, Direction.North),
            "Cannot create a ship because 'shipType' or 'coordinate' or 'direction' is null");
    }

    @Test
    @Order(2)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create carrier Test")
    void CreateCarrierShipTest() {
        Ship carrier = shipFactory.CreateShip(ShipType.Carrier, new Coordinate('A', 1), Direction.East);

        assertNotNull(carrier);
        assertTrue(carrier instanceof Carrier);
    }

    @Test
    @Order(3)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create battleship Test")
    void CreateBattleshipTest() {
        Ship battleship = shipFactory.CreateShip(ShipType.Battleship, new Coordinate('A',1), Direction.East);

        assertNotNull(battleship);
        assertTrue(battleship instanceof Battleship);
    }

    @Test
    @Order(4)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create cruiser Test")
    void CreateCruiserTest() {
        Ship cruiser = shipFactory.CreateShip(ShipType.Cruiser, new Coordinate('A',1), Direction.East);

        assertNotNull(cruiser);
        assertTrue(cruiser instanceof Cruiser);
    }

    @Test
    @Order(5)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create destroyer Test")
    void CreateDestroyerTest() {
        Ship destroyer = shipFactory.CreateShip(ShipType.Destroyer, new Coordinate('A',1), Direction.East);

        assertNotNull(destroyer);
        assertTrue(destroyer instanceof Destroyer);
    }

    @Test
    @Order(6)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionedEquivalence] - Create submarine Test")
    void CreateSubmarineTest() {
        Ship submarine = shipFactory.CreateShip(ShipType.Submarine, new Coordinate('A',1), Direction.East);

        assertNotNull(submarine);
        assertTrue(submarine instanceof Submarine);
    }

    /*
     * All the test of this class are a PathCoverage of the method CreateShip
     */

    @Test
    @Order(99)
    @Tag("PathCoverage")
    @DisplayName("[PathCoverage] - Path Coverage CreateShip Test")
    void PathCoverageCreateShipTest() {
        CreateShipNullTest();

        CreateCarrierShipTest();
        CreateBattleshipTest();
        CreateCruiserTest();
        CreateDestroyerTest();
        CreateSubmarineTest();
    }
}
