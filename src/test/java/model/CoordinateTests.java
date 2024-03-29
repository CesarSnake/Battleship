package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoordinateTests {
    Coordinate c;

    @BeforeEach
    void SetUp() {
        c = new Coordinate('F', 5);
    }

    @Test
    @Order(1)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - Getters Test")
    void GettersTest() {
        assertEquals('F', c.Letter());
        assertEquals(5, c.Number());
    }

    @Test
    @Order(2)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Constructor Null parameters Test")
    void ConstructorNullTest() {
        String exceptionMessage = "Cannot create a Coordinate because 'letter' or 'number' is null";

        assertThrowsExactly(NullPointerException.class,
                () -> new Coordinate(null, 1),
                exceptionMessage);

        assertThrowsExactly(NullPointerException.class,
                () -> new Coordinate('A', null),
                exceptionMessage);

        assertThrowsExactly(NullPointerException.class,
                () -> new Coordinate(null, null),
                exceptionMessage);
    }

    @Test
    @Order(3)
    @Tag("partitionedEquivalence")
    @DisplayName("[partitionedEquivalence] - Constructor Test")
    void ConstructorTest() {
        for (char i = 'A'; i <= 'J'; i++) { // Valid letters
            for (int j = 1; j <= 10; j++) { // Valid numbers
                Coordinate a = new Coordinate(i, j);
                assertNotNull(a);
            }
        }
    }

    @Test
    @Order(4)
    @Tag("partitionedEquivalence")
    @DisplayName("[partitionedEquivalence] - Constructor invalid parameter values Test")
    void ConstructorInvalid() {
        char letter = '0';
        assertThrowsExactly(ExceptionInInitializerError.class,
            () -> new Coordinate(letter, 1),
            String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(1)));

        char ch = '2';
        assertThrowsExactly(ExceptionInInitializerError.class,
            () -> new Coordinate(ch, 1),
            String.join("", "Invalid Coordinate: ", String.valueOf(ch), Integer.toString(1)));

        // test numbers
        for (int i = 11; i < 100; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(5)
    @Tag("PathCoverage")
    @DisplayName("[PathCoverage] - Constructor Test")
    void PathCoverageConstructorTest() {
        ConstructorNullTest();
        ConstructorTest();
        ConstructorInvalid();
    }

    @Test
    @Order(6)
    @Tag("partitionedEquivalence")
    @DisplayName("[partitionedEquivalence] - Constructor invalid letter values Test")
    void ConstructorInvalidLetterTest() {
        for (char i = 'K'; i < 'Z'; i++) {
            char letter = i;
            int number = 1;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(7)
    @Tag("partitioningEquivalence")
    @DisplayName("[PartitioningEquivalence] - Constructor invalid letter symbols values Test")
    void ConstructorInvalidSymbolTest() {
        String symbols = "!·$%&/()=?¿|@#~€¡^+*[]¨Ç{},;.:-_<>ºª\\";
        for (char symbol : symbols.toCharArray()) {
            int number = 2;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(symbol, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(symbol), Integer.toString(number)));
        }
    }

    @Test
    @Order(8)
    @Tag("partitionedEquivalence")
    @DisplayName("[partitionedEquivalence] - Constructor invalid letter as numbers values Test")
    void ConstructorInvalidNumberAsLetterTest() {
        for (int i = 0; i < 10; i++) {
            char letter = String.valueOf(i).charAt(0);
            int number = 3;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(9)
    @Tag("partitioningEquivalence")
    @DisplayName("[PartitioningEquivalence] - Constructor invalid letter lowercase values Test")
    void ConstructorInvalidLowerLetterTest() {
        for (char i = 'a'; i < 'z'; i++) {
            char letter = i;
            int number = 1;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(10)
    @Tag("partitioningEquivalence")
    @DisplayName("[PartitioningEquivalence] - Constructor invalid number values Test")
    void ConstructorInvalidNegativeNumberTest() {
        for (int i = 0; i > -50; i--) {
            char letter = 'B';
            int number = i;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(11)
    @Tag("limit")
    @DisplayName("[Limit] - Constructor limit letter values Test")
    void ConstructorLimitLetterTest() {
        // Letters must be between A and J

        // limit left A
        for (char i = ('A'-50); i < 'A'; i++) {
            char letter =i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, 1),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(1)));
        }

        // letters between A-J are tested on ConstructorTest

        // limit right J
        for (char i = 'K'; i < ('Z'+50); i++) {
            char letter = i;
            int number = 1;

            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate(letter, number),
                String.join("", "Invalid Coordinate: ", String.valueOf(letter), Integer.toString(number)));
        }
    }

    @Test
    @Order(12)
    @Tag("limit")
    @DisplayName("[Limit] - Constructor limit number values Test")
    void ConstructorLimitsNumberTest() {
        // Numbers must be between 1 and 10

        // limit left 1
        for (int i = -50; i < 1; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate('A', number),
                String.join("", "Invalid Coordinate: ", "A", Integer.toString(number)));
        }

        // numbers between 1-10 are tested on ConstructorTest

        // limit right 10
        for (int i = 11; i < 50; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                () -> new Coordinate('A', number),
                String.join("", "Invalid Coordinate: ", "A", Integer.toString(number)));
        }
    }

    @Test
    @Order(13)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - equals Test")
    void EqualsTest() {
        Coordinate F5 = new Coordinate('F',5);
        Coordinate H9 = new Coordinate('H',9);
        boolean result;

        result = c.equals(null);
        assertFalse(result);

        result = c.equals(new Object());
        assertFalse(result);

        result = c.equals(H9);
        assertFalse(result);

        result = c.equals(F5);
        assertTrue(result);
    }

    @Test
    @Order(14)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - toString Test")
    void ToStringTest() {
        Coordinate a1 = new Coordinate('A',1);
        Coordinate j10 = new Coordinate('J',10);

        assertEquals("F5", c.toString());
        assertEquals("A1", a1.toString());
        assertEquals("J10", j10.toString());
    }
}
