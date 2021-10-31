package controller;

import model.Coordinate;
import model.random.RandomController;
import model.random.RandomGenerator;
import model.random.RandomGeneratorForGameMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTests {
    GameController gameController;

    @BeforeEach
    void Setup() {
        gameController = new GameController();
    }

    @Test
    void HowToPlayTest() {
        String rules = "Rules:\n" +
            "The objective of the game is discover where are the ships.\n" +
            "You must attack the ships and sink all of them to win.\n" +
            "To attack a ship you must insert a coordinate and pres enter.\n" +
            "\n" +
            "Symbols:\n" +
            "· unknown, place not discovered.\n" +
            "~ water, you miss your shot.\n" +
            "/ hit, you hit one ship but it is not sink\n" +
            "X sink, you sink the ship.\n" +
            "\n" +
            "Ships in the game:\n" +
            "Carrier, which has 5 holes\n" +
            "Battleship which has 4 holes\n" +
            "Cruiser, which has 3 holes\n" +
            "Destroyer, which has 2 holes\n" +
            "Submarine, which has 1 holes";

        assertEquals(rules, gameController.HowToPlay());
    }

    @Test
    void HasFinishTest() {
        assertFalse(gameController.HasFinish());
    }

    @Test
    void NewGameTest() {
        String expectedBoard = "Turn: 0\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";

        assertEquals(expectedBoard, gameController.NewGame());
        assertFalse(gameController.HasFinish());
    }

    @Test
    void AttackCoordinateGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(new Coordinate('A',1)),
            "Must start a game first");
    }

    @Test
    void AttackCoordinateNullGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Must start a game first");
    }

    @Test
    void AttackCoordinateNullTest() {
        gameController.NewGame();
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Coordinate to attack cannot be null");
    }

    // Simulated game:
    // # 1 2 3 4 5 6 7 8 9 10
    // A · · · · · · · · · ·
    // B · · · · · · X X X ·
    // C · · X · · · · · · ·
    // D · · X · · · · · · ·
    // E · · X · · · · · X ·
    // F · · X · · · · · · ·
    // G · · X · · · · · · ·
    // H · · · · X X X X · ·
    // I · X · · · · · · · ·
    // J · X · · · · · · · ·
    @Test
    void GameTest() {
        // we are going to simulate a game using the mock RandomCoordinatesMockTest
        // we need it as the real game generate random coordinates to place the ships
        RandomGenerator randomGeneratorMock = new RandomGeneratorForGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);

        // The game starts when the player request a new game
        String boardStatus = game.NewGame();
        String boardExpected = "Turn: 0\n" +
                "# 1 2 3 4 5 6 7 8 9 10\n" +
                "A · · · · · · · · · ·\n" +
                "B · · · · · · · · · ·\n" +
                "C · · · · · · · · · ·\n" +
                "D · · · · · · · · · ·\n" +
                "E · · · · · · · · · ·\n" +
                "F · · · · · · · · · ·\n" +
                "G · · · · · · · · · ·\n" +
                "H · · · · · · · · · ·\n" +
                "I · · · · · · · · · ·\n" +
                "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        game.AttackCoordinate(new Coordinate('A',1));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('A',10));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('J',1));
        assertFalse(game.HasFinish());

        boardStatus = game.AttackCoordinate(new Coordinate('A',10));
        boardExpected = "Turn: 4\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Hit all the ships without destroy them
        game.AttackCoordinate(new Coordinate('C',3));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('D',3));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('E',3));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('F',3));
        assertFalse(game.HasFinish());

        game.AttackCoordinate(new Coordinate('H',5));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('H',6));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('H',7));
        assertFalse(game.HasFinish());

        game.AttackCoordinate(new Coordinate('B',7));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('B',8));
        assertFalse(game.HasFinish());

        boardStatus = game.AttackCoordinate(new Coordinate('I',2));
        boardExpected = "Turn: 14\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · / / · ·\n" +
            "C · · / · · · · · · ·\n" +
            "D · · / · · · · · · ·\n" +
            "E · · / · · · · · · ·\n" +
            "F · · / · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · / / / · · ·\n" +
            "I · / · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Hit some watter more
        game.AttackCoordinate(new Coordinate('D',6));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('D',7));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('D',8));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('E',5));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('E',6));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('E',8));
        assertFalse(game.HasFinish());

        boardStatus = game.AttackCoordinate(new Coordinate('F',8));
        boardExpected = "Turn: 21\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · / / · ·\n" +
            "C · · / · · · · · · ·\n" +
            "D · · / · · ~ ~ ~ · ·\n" +
            "E · · / · ~ ~ · ~ · ·\n" +
            "F · · / · · · · ~ · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · / / / · · ·\n" +
            "I · / · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());


        // Destroy Carrier
        boardStatus = game.AttackCoordinate(new Coordinate('G',3));
        boardExpected = "Turn: 22\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · / / · ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · ~ ~ ~ · ·\n" +
            "E · · X · ~ ~ · ~ · ·\n" +
            "F · · X · · · · ~ · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · / / / · · ·\n" +
            "I · / · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Battleship
        boardStatus = game.AttackCoordinate(new Coordinate('H',8));
        boardExpected = "Turn: 23\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · / / · ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · ~ ~ ~ · ·\n" +
            "E · · X · ~ ~ · ~ · ·\n" +
            "F · · X · · · · ~ · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · X X X X · ·\n" +
            "I · / · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Cruiser
        boardStatus = game.AttackCoordinate(new Coordinate('B',9));
        boardExpected = "Turn: 23\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · X X X ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · ~ ~ ~ · ·\n" +
            "E · · X · ~ ~ · ~ · ·\n" +
            "F · · X · · · · ~ · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · X X X X · ·\n" +
            "I · / · · · · · · · ·\n" +
            "J ~ · · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Destroyer
        boardStatus = game.AttackCoordinate(new Coordinate('J',2));
        boardExpected = "Turn: 24\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · X X X ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · ~ ~ ~ · ·\n" +
            "E · · X · ~ ~ · ~ · ·\n" +
            "F · · X · · · · ~ · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · X X X X · ·\n" +
            "I · X · · · · · · · ·\n" +
            "J ~ X · · · · · · · ~";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Submarine
        boardStatus = game.AttackCoordinate(new Coordinate('E',9));
        boardExpected = "Turn: 25\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ · · · · · · · · ~\n" +
            "B · · · · · · X X X ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · ~ ~ ~ · ·\n" +
            "E · · X · ~ ~ · ~ X ·\n" +
            "F · · X · · · · ~ · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · X X X X · ·\n" +
            "I · X · · · · · · · ·\n" +
            "J ~ X · · · · · · · ~";
        assertTrue(boardStatus.contains(boardExpected));
        assertTrue(game.HasFinish());

        // Once the game has finished, the controller returns the last status of the party
        game.AttackCoordinate(new Coordinate('E', 10));
        assertEquals(boardStatus, boardExpected);
        assertTrue(game.HasFinish());


        // it only changes when a new game is created
        boardStatus = game.NewGame();
        boardExpected = "Turn: 0\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());
    }

    // using the same simulated board than the previous test
    @Test
    void AttackCoordinateWatterAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorForGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        String boardExpected = "Turn: 0\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Attack all the column A (it has no ships)
        for (int i = 1; i <= 10; i++) {
            boardStatus = game.AttackCoordinate(new Coordinate('A',i));
        }

        boardExpected = "Turn: 10\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (int i = 1; i <= 10; i++) {
            Coordinate coordinate = new Coordinate('A',i);
            boardStatus= game.AttackCoordinate(coordinate);

            boardExpected = "Turn: 10\n" +
                "# 1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B · · · · · · · · · ·\n" +
                "C · · · · · · · · · ·\n" +
                "D · · · · · · · · · ·\n" +
                "E · · · · · · · · · ·\n" +
                "F · · · · · · · · · ·\n" +
                "G · · · · · · · · · ·\n" +
                "H · · · · · · · · · ·\n" +
                "I · · · · · · · · · ·\n" +
                "J · · · · · · · · · ·\n"+
                "Coordinate: " + coordinate + " already hit";

            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }

    @Test
    void AttackCoordinateHitAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorForGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        String boardExpected = "Turn: 0\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Hit the Carrier without sink
        for (char i = 'C'; i < 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);
        }

        boardExpected = "Turn: 4\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · / · · · · · · ·\n" +
            "D · · / · · · · · · ·\n" +
            "E · · / · · · · · · ·\n" +
            "F · · / · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (char i = 'C'; i < 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);

            boardExpected = "Turn: 4\n" +
                "# 1 2 3 4 5 6 7 8 9 10\n" +
                "A · · · · · · · · · ·\n" +
                "B · · · · · · · · · ·\n" +
                "C · · / · · · · · · ·\n" +
                "D · · / · · · · · · ·\n" +
                "E · · / · · · · · · ·\n" +
                "F · · / · · · · · · ·\n" +
                "G · · · · · · · · · ·\n" +
                "H · · · · · · · · · ·\n" +
                "I · · · · · · · · · ·\n" +
                "J · · · · · · · · · ·\n" +
                "Coordinate: " + coordinate + " already hit";
            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }

    @Test
    void AttackCoordinateDestroyedAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorForGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        String boardExpected = "Turn: 0\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · · · · · · · · ·\n" +
            "D · · · · · · · · · ·\n" +
            "E · · · · · · · · · ·\n" +
            "F · · · · · · · · · ·\n" +
            "G · · · · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Hit the Carrier without sink
        for (char i = 'C'; i <= 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);
        }

        boardExpected = "Turn: 5\n" +
            "# 1 2 3 4 5 6 7 8 9 10\n" +
            "A · · · · · · · · · ·\n" +
            "B · · · · · · · · · ·\n" +
            "C · · X · · · · · · ·\n" +
            "D · · X · · · · · · ·\n" +
            "E · · X · · · · · · ·\n" +
            "F · · X · · · · · · ·\n" +
            "G · · X · · · · · · ·\n" +
            "H · · · · · · · · · ·\n" +
            "I · · · · · · · · · ·\n" +
            "J · · · · · · · · · ·";
        assertEquals(boardExpected, boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (char i = 'C'; i <= 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);

            boardExpected = "Turn: 5\n" +
                "# 1 2 3 4 5 6 7 8 9 10\n" +
                "A · · · · · · · · · ·\n" +
                "B · · · · · · · · · ·\n" +
                "C · · X · · · · · · ·\n" +
                "D · · X · · · · · · ·\n" +
                "E · · X · · · · · · ·\n" +
                "F · · X · · · · · · ·\n" +
                "G · · X · · · · · · ·\n" +
                "H · · · · · · · · · ·\n" +
                "I · · · · · · · · · ·\n" +
                "J · · · · · · · · · ·\n" +
                "Coordinate: " + coordinate + " already hit";
            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }
}
