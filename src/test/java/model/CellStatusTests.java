package model;

import model.ship.ShipType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CellStatusTests {
    CellStatus watter;
    CellStatus hit;
    CellStatus destroyed;

    @BeforeAll
     void InitOnce() {
        watter = CellStatus.Watter;
        hit = CellStatus.Hit;
        destroyed = CellStatus.Destroyed;
    }

    @Test
    void StatusWatterTest() {
        assertEquals(CellStatus.Watter, watter);
        assertEquals("Watter", watter.toString());
        assertEquals(CellStatus.Watter.toString(), watter.toString());
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
