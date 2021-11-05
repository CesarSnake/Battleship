package controller;

import model.Coordinate;
import model.random.RandomController;
import model.random.RandomGenerator;
import model.random.RandomGeneratorExtremeGameMock;
import model.random.RandomGeneratorGameMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTests {
    GameController gameController;

    @BeforeEach
    void Setup() {
        gameController = new GameController();
    }

    @Test
    @Tag("unitTest")
    @DisplayName("How to play Test")
    void HowToPlayTest() {
        String rules = TestUtils.GetOutputFromFile("rules.out");
        assertEquals(rules, gameController.HowToPlay());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("Has Finish (not played) Test")
    void HasFinishTest() {
        assertFalse(gameController.HasFinish());
    }

    @Test
    @Tag("unitTest")
    @DisplayName("New Game (not played) Test")
    void NewGameTest() {
        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), gameController.NewGame());
        assertFalse(gameController.HasFinish());
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Attack coordinate (game not created) Test")
    void AttackCoordinateGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(new Coordinate('A',1)),
            "Must start a game first");
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Attack coordinate (game not created) null parameter Test")
    void AttackCoordinateNullGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Must start a game first");
    }

    @Test
    @Tag("conditionCoverage")
    @DisplayName("Attack coordinate null parameter Test")
    void AttackCoordinateNullTest() {
        gameController.NewGame();
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Coordinate to attack cannot be null");
    }

    // Attack invalid Coordinates
    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Attack coordinates watter twice Test")
    void AttackCoordinateWatterAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Attack all the column A (it has no ships)
        for (int i = 1; i <= 10; i++) {
            boardStatus = game.AttackCoordinate(new Coordinate('A',i));
        }

        assertEquals(TestUtils.GetOutputFromFile("gameControllerTest01.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (int i = 1; i <= 10; i++) {
            Coordinate coordinate = new Coordinate('A',i);
            boardStatus = game.AttackCoordinate(coordinate);

            String boardExpected = String.join("\n",
                TestUtils.GetOutputFromFile("gameControllerTest01.out"),
                "Coordinate: " + coordinate + " already hit");

            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Attack coordinates hit twice Test")
    void AttackCoordinateHitAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Hit the Carrier without sink
        for (char i = 'C'; i < 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);
        }

        assertEquals(TestUtils.GetOutputFromFile("gameControllerTest02.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (char i = 'C'; i < 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);

            String boardExpected = String.join("\n",
                TestUtils.GetOutputFromFile("gameControllerTest02.out"),
                "Coordinate: " + coordinate + " already hit");

            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }

    @Test
    @Tag("partitionEquivalence")
    @DisplayName("Attack Coordinates destroyed twice Test")
    void AttackCoordinateDestroyedAlreadyAttackedTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Hit the Carrier without sink
        for (char i = 'C'; i <= 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);
        }

        assertEquals(TestUtils.GetOutputFromFile("gameControllerTest03.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Attack again the same cells
        for (char i = 'C'; i <= 'G'; i++) {
            Coordinate coordinate = new Coordinate(i, 3);
            boardStatus = game.AttackCoordinate(coordinate);

            String boardExpected = String.join("\n",
                TestUtils.GetOutputFromFile("gameControllerTest03.out"),
                "Coordinate: " + coordinate + " already hit");

            assertEquals(boardExpected, boardStatus);
            assertFalse(game.HasFinish());
        }
    }

    // Real game scenarios for GameController (simulates the interaction with the view)

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
    @Tag("mock")
    @Tag("acceptanceTest")
    @DisplayName("Real Game (normal) Test")
    void GameTest() {
        // we are going to simulate a game using the mock RandomCoordinatesMockTest
        // we need it as the real game generate random coordinates to place the ships
        RandomGenerator randomGeneratorMock = new RandomGeneratorGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);
        GameController game = new GameController(randomControllerMock);

        // The game starts when the player request a new game
        String boardStatus = game.NewGame();
        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Attack some water cells coordinates
        game.AttackCoordinate(new Coordinate('A',1));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('A',10));
        assertFalse(game.HasFinish());
        game.AttackCoordinate(new Coordinate('J',1));
        assertFalse(game.HasFinish());

        boardStatus = game.AttackCoordinate(new Coordinate('J',10));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn4.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Hit all the ships cells without destroy them
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
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn14.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Hit some watter cells more
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
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn21.out"), boardStatus);
        assertFalse(game.HasFinish());


        // Destroy Carrier
        boardStatus = game.AttackCoordinate(new Coordinate('G',3));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn22.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Battleship
        boardStatus = game.AttackCoordinate(new Coordinate('H',8));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn23.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Cruiser
        boardStatus = game.AttackCoordinate(new Coordinate('B',9));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn24.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Destroyer
        boardStatus = game.AttackCoordinate(new Coordinate('J',2));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn25.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Destroy Submarine
        boardStatus = game.AttackCoordinate(new Coordinate('E',9));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn26.out"), boardStatus);
        assertTrue(game.HasFinish());

        // Once the game has finished, the controller returns the last status of the party
        boardStatus = game.AttackCoordinate(new Coordinate('E', 10));
        assertEquals(TestUtils.GetOutputFromFile("gameNormalTurn26.out"), boardStatus);
        assertTrue(game.HasFinish());


        // it only changes when a new game is created
        game = new GameController(); // it must be other controller as our mock only returned 5 coordinates and after the same
        boardStatus = game.NewGame();
        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());
    }

    // Simulated game:
    // # 1 2 3 4 5 6 7 8 9 10
    // A · · · · · · · · · ·
    // B · · · · · · · · · ·
    // C · · · · · · · · · ·
    // D · · · · · · · · · ·
    // E · · · · · · · · · ·
    // F · · · · · · · · · ·
    // G · · · · · · · · · ·
    // H · · · · · · · · · ·
    // I X · X X · X X X · ·
    // J X X X X · X X X X X
    @Test
    @Tag("mock")
    @Tag("acceptanceTest")
    @DisplayName("Real Game (extreme) Test")
    void GameExtremeTest() {
        RandomGenerator randomGeneratorMock = new RandomGeneratorExtremeGameMock();
        RandomController randomControllerMock = new RandomController(randomGeneratorMock);

        GameController game = new GameController(randomControllerMock);
        String boardStatus = game.NewGame();

        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), boardStatus);
        assertFalse(game.HasFinish());

        // Hit all the Watter cells
        for (char i = 'A'; i <='H' ; i++) {
            for (int j = 1; j <=10 ; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                boardStatus = game.AttackCoordinate(coordinate);
            }
        }

        assertEquals(TestUtils.GetOutputFromFile("gameExtremeTurn080.out"), boardStatus);
        assertFalse(game.HasFinish());


        // Attack again all the watter cells
        for (char i = 'A'; i <='H' ; i++) {
            for (int j = 1; j <=10 ; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                boardStatus = game.AttackCoordinate(coordinate);

                String boardExpected = String.join("\n",
                    TestUtils.GetOutputFromFile("gameExtremeTurn080.out"),
                    "Coordinate: " + coordinate + " already hit");

                assertEquals(boardExpected, boardStatus);
                assertFalse(game.HasFinish());
            }
        }


        // Hit the ship cells without sink one
        Coordinate lastCoordinate = new Coordinate('J',10);
        for (char i = 'I'; i <='J' ; i++) {
            for (int j = 1; j <=10 ; j++) {
                Coordinate coordinate = new Coordinate(i,j);

                if (coordinate.equals(lastCoordinate)) {
                    continue;
                }

                boardStatus = game.AttackCoordinate(coordinate);
            }
        }

        assertEquals(TestUtils.GetOutputFromFile("gameExtremeTurn099.out"), boardStatus);
        assertFalse(game.HasFinish());


        //Hit again the same cells
        for (char i = 'I'; i <='J' ; i++) {
            for (int j = 1; j <=10 ; j++) {
                Coordinate coordinate = new Coordinate(i,j);

                if (coordinate.equals(lastCoordinate)) {
                    continue;
                }

                boardStatus = game.AttackCoordinate(coordinate);

                String boardExpected = String.join("\n",
                    TestUtils.GetOutputFromFile("gameExtremeTurn099.out"),
                    "Coordinate: " + coordinate + " already hit");

                assertEquals(boardExpected, boardStatus);
                assertFalse(game.HasFinish());
            }
        }

        // Finish the game
        boardStatus = game.AttackCoordinate(lastCoordinate);
        assertEquals(TestUtils.GetOutputFromFile("gameExtremeTurn100.out"), boardStatus);
        assertTrue(game.HasFinish());

        //Hit again the same coordinate
        boardStatus = game.AttackCoordinate(lastCoordinate);
        assertEquals(TestUtils.GetOutputFromFile("gameExtremeTurn100.out"), boardStatus); // game is not updated anymore
        assertTrue(game.HasFinish());
    }
}
