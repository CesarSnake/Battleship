package view;

import controller.GameController;
import model.Coordinate;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GameView {
    static GameController controller;

    public static void main(String[] args) {
        controller = new GameController();
        DisplayMainMenu();
    }

    private static void  DisplayMainMenu() {
        List<String> validOptions = List.of("1", "2", "3");
        Scanner userInput = new Scanner(System.in);
        String userOption;

        do{
            System.out.println(String.join("", Collections.nCopies(25, "-")));
            System.out.println("BattleShip single player");
            System.out.println(String.join("", Collections.nCopies(25, "-")));
            System.out.println("1. Start Game");
            System.out.println("2. How to Play");
            System.out.println("3. Exit Game");
            System.out.println();
            System.out.println("Select option [1, 2, 3]");
            userOption = userInput.nextLine();
        } while (!validOptions.contains(userOption));

        switch (userOption) {
            case "1":
                StartGame();
                break;
            case "2":
                DisplayHowToPlay();
                break;
            case "3":
            default:
                ExitGame();
        }
    }

    public static void StartGame() {
        String game = controller.NewGame();

        while (!controller.HasFinish()) {
            System.out.println(game);
            Coordinate userCoordinate = AskCoordinate();

            game = controller.AttackCoordinate(userCoordinate);
        }

        System.out.println(game);
        System.out.println("You won");
        PressKeyToContinue();

        DisplayMainMenu();

    }

    public static Coordinate AskCoordinate() {
        Scanner userInput = new Scanner(System.in);
        boolean invalidCoordinate = true;
        Coordinate coordinateToAttack = null;

        while (invalidCoordinate) {
            System.out.println("Attack to Coordinate:");
            String userCoordinate = userInput.nextLine();

            try {
                coordinateToAttack = CheckCoordinate(userCoordinate);
                invalidCoordinate = false;
            } catch(ExceptionInInitializerError invalidCoordinateException) {
                System.err.println(invalidCoordinateException.getMessage());
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Invalid Coordinate: " + userCoordinate);
            }
        }
        return coordinateToAttack;
    }

    public static Coordinate CheckCoordinate(String userCoordinate) {
        Coordinate validCoordinate;

        switch (userCoordinate.length()) {
            case 2:
            validCoordinate = new Coordinate(
                userCoordinate.toUpperCase(Locale.ROOT).charAt(0),
                Integer.valueOf(userCoordinate.substring(1,2)));
                break;
            case 3:
            validCoordinate = new Coordinate(
                userCoordinate.toUpperCase(Locale.ROOT).charAt(0),
                Integer.valueOf(userCoordinate.substring(1,3)));
                break;
            default:
                throw new ExceptionInInitializerError(String.join(" ","Invalid Coordinate:", userCoordinate));
        }

        return validCoordinate;
    }

    public static void DisplayHowToPlay() {
        System.out.println(controller.HowToPlay());
        System.out.println();
        PressKeyToContinue();

        DisplayMainMenu();
    }

    public static void ExitGame() {
        System.out.println("Thanks for playing.");
    }

    public static void PressKeyToContinue() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("press any key to back main menu");
        userInput.nextLine();
    }
}
