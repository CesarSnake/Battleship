package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CellStatusTests {
    CellStatus hide;
    CellStatus Water;
    CellStatus hit;
    CellStatus destroyed;

    @BeforeAll
     void Setup() {
        hide = CellStatus.Hide;
        Water = CellStatus.Water;
        hit = CellStatus.Hit;
        destroyed = CellStatus.Destroyed;
    }

    @Test
    @Order(1)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Status Hide Test")
    void StatusHideTest() {
        assertEquals(CellStatus.Hide, hide);
        assertEquals("Hide", hide.toString());
        assertEquals(CellStatus.Hide.toString(), hide.toString());
    }

    @Test
    @Order(2)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Status Water Test")
    void StatusWaterTest() {
        assertEquals(CellStatus.Water, Water);
        assertEquals("Water", Water.toString());
        assertEquals(CellStatus.Water.toString(), Water.toString());
    }

    @Test
    @Order(3)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Status Hit Test")
    void StatusHitTest() {
        assertEquals(CellStatus.Hit, hit);
        assertEquals("Hit", hit.toString());
        assertEquals(CellStatus.Hit.toString(), hit.toString());

    }

    @Test
    @Order(4)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Status Destroyed Test")
    void StatusDestroyedTest() {
        assertEquals(CellStatus.Destroyed, destroyed);
        assertEquals("Destroyed", destroyed.toString());
        assertEquals(CellStatus.Destroyed.toString(), destroyed.toString());
    }
}
