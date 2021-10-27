package model;

import model.ship.ShipType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CellStatusTests {
    CellStatus hide;
    CellStatus Water;
    CellStatus hit;
    CellStatus destroyed;

    @BeforeAll
     void InitOnce() {
        hide = CellStatus.Hide;
        Water = CellStatus.Water;
        hit = CellStatus.Hit;
        destroyed = CellStatus.Destroyed;
    }

    @Test
    void StatusHideTest() {
        assertEquals(CellStatus.Hide, hide);
        assertEquals("Hide", hide.toString());
        assertEquals(CellStatus.Hide.toString(), hide.toString());
    }

    @Test
    void StatusWaterTest() {
        assertEquals(CellStatus.Water, Water);
        assertEquals("Water", Water.toString());
        assertEquals(CellStatus.Water.toString(), Water.toString());
    }

    @Test
    void StatusHitTest() {
        assertEquals(CellStatus.Hit, hit);
        assertEquals("Hit", hit.toString());
        assertEquals(CellStatus.Hit.toString(), hit.toString());

    }

    @Test
    void StatusDestroyedTest() {
        assertEquals(CellStatus.Destroyed, destroyed);
        assertEquals("Destroyed", destroyed.toString());
        assertEquals(CellStatus.Destroyed.toString(), destroyed.toString());
    }
}
