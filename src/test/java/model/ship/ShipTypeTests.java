package model.ship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShipTypeTests{
    ShipType shipCarrier;
    ShipType shipBattleship;
    ShipType shipCruiser;
    ShipType shipDestroyer;
    ShipType shipSubmarine;

    @BeforeAll
    void Setup() {
        shipCarrier = ShipType.Carrier;
        shipBattleship = ShipType.Battleship;
        shipCruiser = ShipType.Cruiser;
        shipDestroyer = ShipType.Destroyer;
        shipSubmarine = ShipType.Submarine;
    }


    @Test
    @Order(1)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Ship carrier Test")
    void ShipCarrierTest() {
        assertEquals(ShipType.Carrier, shipCarrier);
        assertEquals("Carrier", shipCarrier.toString());
        assertEquals(ShipType.Carrier.toString(), shipCarrier.toString());
    }

    @Test
    @Order(2)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Ship battleship Test")
    void ShipBattleshipTest() {
        assertEquals(ShipType.Battleship, shipBattleship);
        assertEquals("Battleship", shipBattleship.toString());
        assertEquals(ShipType.Battleship.toString(), shipBattleship.toString());
    }

    @Test
    @Order(3)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Ship cruiser Test")
    void ShipCruiserTest() {
        assertEquals(ShipType.Cruiser, shipCruiser);
        assertEquals("Cruiser", shipCruiser.toString());
        assertEquals(ShipType.Cruiser.toString(), shipCruiser.toString());
    }

    @Test
    @Order(4)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Ship destroyer Test")
    void ShipDestroyerTest() {
        assertEquals(ShipType.Destroyer, shipDestroyer);
        assertEquals("Destroyer", shipDestroyer.toString());
        assertEquals(ShipType.Destroyer.toString(), shipDestroyer.toString());
    }

    @Test
    @Order(5)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Ship submarine Test")
    void ShipSubmarineTest() {
        assertEquals(ShipType.Submarine, shipSubmarine);
        assertEquals("Submarine", shipSubmarine.toString());
        assertEquals(ShipType.Submarine.toString(), shipSubmarine.toString());
    }
}