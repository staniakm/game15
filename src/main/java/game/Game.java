package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private final Scanner sc;
    private final Board board;

    private final Map<String, Integer> letterValues = new HashMap<>();

    public Game(Scanner sc) {
        this.sc = sc;
        prepareLetterCoords();
        board = new Board();
    }

    public boolean play() {
        boolean endGame = false;

        do {
            board.printBoard();
            System.out.println("Enter index or 'K' for exit");
            String selectedField = sc.nextLine();

            if (selectedField.equalsIgnoreCase("K")) {
                endGame = true;
            } else {
                makeMove(selectedField);
            }

            if (board.ifSuccess())
                endGame = true;

        } while (!endGame);
        return board.ifSuccess();
    }

    private void makeMove(String selectedField) {
        if (selectedField.length() != 2) {
            System.out.println("Incorrect coordinates");
            return;
        }

        String[] split = selectedField.split("");
        if (isCorrectCoordinates(split)) {
            int letterNumber = getCoordLetterToNumber(split[0]);
            int number = Integer.parseInt(split[1]) - 1;
            board.move(letterNumber, number);
        }
    }

    private boolean isCorrectCoordinates(String[] split) {
        return isCorrectLetter(split[0]) && isCorrectNumber(split[1]);
    }

    private void prepareLetterCoords() {
        letterValues.put("a", 0);
        letterValues.put("b", 1);
        letterValues.put("c", 2);
        letterValues.put("d", 3);
    }

    private int getCoordLetterToNumber(String letter) {
        return letterValues.get(letter);
    }

    private boolean isCorrectNumber(String numberCoord) {
        try {
            int i = Integer.parseInt(numberCoord);
            boolean correctNumberCoordinate = i >= 1 && i <= 4;
            if (!correctNumberCoordinate) {
                System.out.println("Incorrect number coordinate");
            }
            return correctNumberCoordinate;
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect number coordinate");
            return false;
        }
    }

    private boolean isCorrectLetter(String letter) {
        if (!letterValues.containsKey(letter)) {
            System.out.println("Incorrect letter coordinate");
            return false;
        }
        return true;
    }

}
