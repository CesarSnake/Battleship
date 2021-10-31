package view;

import controller.GameController;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameView {
    static GameController controller;

    public static void main(String[] args) {
        controller = new GameController();
        DisplayMainMenu();
    }

    private static void  DisplayMainMenu(){
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
            System.out.println("Select option [1, 2, 3]");
            userOption = userInput.nextLine();
        } while (!validOptions.contains(userOption));

        switch (userOption){
            case "1":
                System.out.println("Option not implemented");
                break;
            case "2":
                DisplayHowToPlay();
                break;
            default:
                break;
        }
    }

    public static void DisplayHowToPlay() {
        Scanner userInput = new Scanner(System.in);
        System.out.println(controller.HowToPlay());
        System.out.println();
        System.out.println("press any key to back main menu");
        userInput.nextLine();

        DisplayMainMenu();
    }
}
