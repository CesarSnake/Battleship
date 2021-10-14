package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTests {
    Coordinate c;

    @BeforeEach
    void SetUp(){
        c = new Coordinate("F", 5);
    }

    @Test
    void GettersTest(){
        assertEquals("F", c.Letter());
        assertEquals(5, c.Number());
    }


    @Test
    void ConstructorTest() {
        for (char i = 'A'; i <= 'J'; i++) { // Valid letters
            for (int j = 1; j <= 10; j++) { // Valid numbers
                String letter = String.valueOf(i);
                Coordinate a = new Coordinate(letter, j);

                assertNotNull(a);
            }
        }
    }


    @Test
    void ConstructorInvalid() {
        String letter = "AA";
        assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, 1); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(1)));

        String sentence = "this is a test";
        assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, 1); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(1)));

        // test numbers
        for (int i = 11; i < 100; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, number); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }
    }

    @Test
    void ConstructorInvalidLetterTest(){
        for (char i = 'K'; i < 'Z'; i++) {
            String letter = String.valueOf(i);
            int number = 1;

            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, number); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }
    }

    @Test
    void ConstructorInvalidSymbolTest(){
        String symbols = "!·$%&/()=?¿|@#~€¡^+*[]¨Ç{},;.:-_<>ºª\\";
        for(char symbol : symbols.toCharArray()) {
            String letter = String.valueOf(symbol);
            int number = 2;

            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, number); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }
    }

    @Test
    void ConstructorInvalidNumberAsLetterTest(){
        for (int i = 0; i < 10; i++) {
            String letter = String.valueOf(i);
            int number = 3;

            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate(letter, number); },
                String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }
    }


    @Test
    void ConstructorLimitLetterTest(){
        //Letters must be between A and J

        // limit left A
        for (int i = ('A'-25); i > ('A'-1); i++) {
            String letter = String.valueOf(i);
            assertThrowsExactly(ExceptionInInitializerError.class,
                    ()->{ Coordinate c3 = new Coordinate(letter, 1); },
                    String.join("", "Invalid Coordinate: ", letter, Integer.toString(1)));
        }

        // numbers between A-J are tested on ConstructorTest

        // limit right 10
        for (char i = 'K'; i < 'Z'+25; i++) {
            String letter = String.valueOf(i);
            int number = 1;

            assertThrowsExactly(ExceptionInInitializerError.class,
                    ()->{ Coordinate c3 = new Coordinate(letter, number); },
                    String.join("", "Invalid Coordinate: ", letter, Integer.toString(number)));
        }
    }

    @Test
    void ConstructorLimitsNumberTest() {
        //Numbers must be between 1 and 10

        // limit left 1
        for (int i = -50; i > 0; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate("A", number); },
                String.join("", "Invalid Coordinate: ", "A", Integer.toString(number)));
        }

        // numbers between 1-10 are tested on ConstructorTest

        // limit right 10
        for (int i = 11; i < 50; i++) {
            int number = i;
            assertThrowsExactly(ExceptionInInitializerError.class,
                ()->{ Coordinate c3 = new Coordinate("A", number); },
                String.join("", "Invalid Coordinate: ", "A", Integer.toString(number)));
        }
    }

    @Test
    void EqualsTest() {
        Coordinate f5 = new Coordinate("F",5);
        Coordinate H9 = new Coordinate("H",9);

        assertFalse(c.equals(null));
        assertFalse(c.equals(new String()));
        assertFalse(c.equals("F5"));

        assertFalse(c.equals(H9));
        assertTrue(c.equals(c));
        assertTrue(c.equals(f5));
    }

    @Test
    void ToStringTest() {
        Coordinate a1 = new Coordinate("A",1);
        Coordinate j10 = new Coordinate("J",10);

        assertEquals("F5",c.toString());
        assertEquals("A1", a1.toString());
        assertEquals("J10", j10.toString());
    }
}
