
import game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game(sc);

        boolean isSuccessful = game.play();

        if (isSuccessful) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
        sc.close();
    }
}
