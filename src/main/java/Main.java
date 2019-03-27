
import game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();

        playGame(sc, game);

        if (game.ifSuccess()) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }

    }

    private static void playGame(Scanner sc, Game game) {

        game.printBoard();
        boolean endGame = false;

        do {
            System.out.println("Enter index or 'K' for exit");
            String tableCoord = sc.nextLine();
            if (tableCoord.equalsIgnoreCase("K")) {
                endGame = true;
            } else {
                try {
                    game.move(tableCoord);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input data");
                }
            }

            if (game.ifSuccess())
                endGame = true;

            game.printBoard();
        } while (!endGame);
    }
}
