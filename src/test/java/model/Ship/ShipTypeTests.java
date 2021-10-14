package model.Ship;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShipTypeTests{
    ShipType shipCarrier;
    ShipType shipBattleship;
    ShipType shipCruiser;
    ShipType shipDestroyer;
    ShipType shipSubmarine;

    @BeforeAll
    void InitOnce(){
        shipCarrier = ShipType.Carrier;
        shipBattleship = ShipType.Battleship;
        shipCruiser = ShipType.Cruiser;
        shipDestroyer = ShipType.Destroyer;
        shipSubmarine = ShipType.Submarine;
    }


    @Test
    void ShipCarrierTest(){
        assertEquals(shipCarrier, ShipType.Carrier);
        assertEquals("Carrier", shipCarrier.toString());
    }

    @Test
    void ShipBattleshipTest(){
        assertEquals(shipBattleship, ShipType.Battleship);
        assertEquals("Battleship", shipBattleship.toString());
    }

    @Test
    void ShipCruiserTest(){
        assertEquals(shipCruiser, ShipType.Cruiser);
        assertEquals("Cruiser", shipCruiser.toString());
    }

    @Test
    void ShipDestroyerTest(){
        assertEquals(shipDestroyer, ShipType.Destroyer);
        assertEquals("Destroyer", shipDestroyer.toString());
    }

    @Test
    void ShipSubmarineTest(){
        assertEquals(shipSubmarine, ShipType.Submarine);
        assertEquals("Submarine", shipSubmarine.toString());
    }
}