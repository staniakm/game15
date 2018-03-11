
import game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.printBoard();
        boolean endGame = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter index or 'K' for exit");
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("k")) {
                endGame = true;
            } else {
                try {
                    g.move(s);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input data");
                }
            }

            if (g.ifSuccess())
                endGame = true;

            g.printBoard();
        } while (!endGame);

        if (g.ifSuccess()) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }

    }
}
