package model.ship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    @Tag("unitTest")
    @DisplayName("Ship carrier Test")
    void ShipCarrierTest() {
        assertEquals(ShipType.Carrier, shipCarrier);
        assertEquals("Carrier", shipCarrier.toString());
        assertEquals(ShipType.Carrier.toString(), shipCarrier.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Ship battleship Test")
    void ShipBattleshipTest() {
        assertEquals(ShipType.Battleship, shipBattleship);
        assertEquals("Battleship", shipBattleship.toString());
        assertEquals(ShipType.Battleship.toString(), shipBattleship.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Ship cruiser Test")
    void ShipCruiserTest() {
        assertEquals(ShipType.Cruiser, shipCruiser);
        assertEquals("Cruiser", shipCruiser.toString());
        assertEquals(ShipType.Cruiser.toString(), shipCruiser.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Ship destroyer Test")
    void ShipDestroyerTest() {
        assertEquals(ShipType.Destroyer, shipDestroyer);
        assertEquals("Destroyer", shipDestroyer.toString());
        assertEquals(ShipType.Destroyer.toString(), shipDestroyer.toString());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Ship submarine Test")
    void ShipSubmarineTest() {
        assertEquals(ShipType.Submarine, shipSubmarine);
        assertEquals("Submarine", shipSubmarine.toString());
        assertEquals(ShipType.Submarine.toString(), shipSubmarine.toString());
    }
}