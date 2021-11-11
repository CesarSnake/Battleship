package controller;

import model.Coordinate;
import model.random.RandomController;
import model.random.RandomGenerator;
import model.random.RandomGeneratorExtremeGameMock;
import model.random.RandomGeneratorGameMock;
import org.junit.jupiter.api.*;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameControllerTests {
    GameController gameController;

    @BeforeEach
    void Setup() {
        gameController = new GameController();
    }

    @Test
    @Order(1)
    @Tag("unitTest")
    @DisplayName("[UnitTest] - How to play Test")
    void HowToPlayTest() {
        String rules = TestUtils.GetOutputFromFile("rules.out");
        assertEquals(rules, gameController.HowToPlay());
    }

    @Test
    @Order(2)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Has Finish (not played) Test")
    void HasFinishTest() {
        assertFalse(gameController.HasFinish());
    }

    // each time that NewGame is called, we are doing a nester loop testing
    @Test
    @Order(3)
    @Tag("unitTest")
    @Tag("loopTesting")
    @DisplayName("[LoopTesting] - New Game (not played) Test")
    void NewGameTest() {
        assertEquals(TestUtils.GetOutputFromFile("gameNewGame.out"), gameController.NewGame());
        assertFalse(gameController.HasFinish());
    }


    @Test
    @Order(4)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Attack coordinate (game not created) Test")
    void AttackCoordinateGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(new Coordinate('A',1)),
            "Must start a game first");
    }

    @Test
    @Order(5)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Attack coordinate (game not created) null parameter Test")
    void AttackCoordinateNullGameNotCreated() {
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Must start a game first");
    }

    @Test
    @Order(6)
    @Tag("conditionCoverage")
    @DisplayName("[ConditionCoverage] - Attack coordinate null parameter Test")
    void AttackCoordinateNullTest() {
        gameController.NewGame();
        assertThrowsExactly(NullPointerException.class,
            ()-> gameController.AttackCoordinate(null),
            "Coordinate to attack cannot be null");
    }

    // Attack invalid Coordinates
    // We need to use mocks to know where are the ships
    @Test
    @Order(7)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionEquivalence] - Attack coordinates watter twice Test")
    void AttackCoordinateWatterAlreadyAttackedTest() {
        // We are going to hit the same water cells twice and check the error displayed and turn are correct
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
    @Order(8)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionEquivalence] - Attack coordinates hit twice Test")
    void AttackCoordinateHitAlreadyAttackedTest() {
        // We are going to hit the same hit cells twice and check the error displayed and turn are correct
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
    @Order(9)
    @Tag("partitionEquivalence")
    @DisplayName("[PartitionEquivalence] - Attack Coordinates destroyed twice Test")
    void AttackCoordinateDestroyedAlreadyAttackedTest() {
        // We are going to hit the same destroyed cells twice and check the error displayed and turn are correct
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

    // We are going to generate 2 different games scenarios
    // to simulate the interaction with the view

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
    @Order(10)
    @Tag("mock")
    @Tag("acceptanceTest")
    @Tag("pairwise") // we are checking specific cells, not all the board
    @DisplayName("[MockObject][PairWise][Acceptance] -  Real Game (normal) Test")
    void GameTest() {
        // we are going to simulate a game using the mock RandomGeneratorGameMock
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
    @Order(11)
    @Tag("mock")
    @Tag("acceptanceTest")
    @DisplayName("[Mock][Acceptance] - Real Game (extreme) Test")
    void GameExtremeTest() {
        // we are going to simulate a game using the mock RandomGeneratorExtremeGameMock
        // we need it as the real game generate random coordinates to place the ships
        // This test will attack all the coordinate in game twice to check real scenarios
        // one the check the board update and the second to check the correct error message
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
