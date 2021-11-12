package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirectionTests {
    Direction north;
    Direction south;
    Direction east;
    Direction west;

    @BeforeAll
    void Setup() {
        north = Direction.North;
        south = Direction.South;
        east = Direction.East;
        west = Direction.West;
    }


    @Test
    @Order(1)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Direction North Test")
    void DirectionNorthTest() {
        assertEquals(Direction.North, north);
        assertEquals("North", north.toString());
        assertEquals(Direction.North.toString(), north.toString());
    }

    @Test
    @Order(2)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Direction South Test")
    void DirectionSouthTest() {
        assertEquals(Direction.South, south);
        assertEquals("South", south.toString());
        assertEquals(Direction.South.toString(), south.toString());
    }

    @Test
    @Order(3)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Direction East Test")
    void DirectionEastTest() {
        assertEquals(Direction.East, east);
        assertEquals("East", east.toString());
        assertEquals(Direction.East.toString(), east.toString());
    }

    @Test
    @Order(4)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Direction West Test")
    void DirectionWestTest() {
        assertEquals(Direction.West, west);
        assertEquals("West", west.toString());
        assertEquals(Direction.West.toString(), west.toString());
    }
}