package game;

import java.util.*;

public class Game {

    private GameNode topLeft;
    //list used as help in board creation process
    private List<GameNode> node = new ArrayList<>();
    private Map<String, Integer> letterCoord = new HashMap<String, Integer>();

    public Game() {
        prepareLetterCoords();

        for (int i = 0; i < 16; i++) {
            node.add(new GameNode(i));
        }

        prepareBoard();

        shuffleBoard();
    }

    private void prepareLetterCoords() {
        letterCoord.put("a", 0);
        letterCoord.put("b", 1);
        letterCoord.put("c", 2);
        letterCoord.put("d", 3);
    }

    private void prepareBoard() {
        topLeft = node.get(1);

        node.get(2).setRightNode(node.get(3));
        node.get(2).setBottomNode(node.get(6));
        node.get(2).setLeftNode(node.get(1));

        node.get(8).setTopNode(node.get(4));
        node.get(8).setBottomNode(node.get(12));
        node.get(8).setLeftNode(node.get(7));

        node.get(15).setRightNode(node.get(0));
        node.get(15).setTopNode(node.get(11));
        node.get(15).setLeftNode(node.get(14));

        node.get(9).setRightNode(node.get(10));
        node.get(9).setTopNode(node.get(5));
        node.get(9).setBottomNode(node.get(13));

        node.get(4).setLeftNode(node.get(3));
        node.get(5).setTopNode(node.get(1));
        node.get(5).setRightNode(node.get(6));
        node.get(6).setRightNode(node.get(7));

        node.get(12).setLeftNode(node.get(11));
        node.get(11).setLeftNode(node.get(10));
        node.get(13).setRightNode(node.get(14));

        node.get(6).setBottomNode(node.get(10));
        node.get(10).setBottomNode(node.get(14));
        node.get(3).setBottomNode(node.get(7));
        node.get(11).setTopNode(node.get(7));
        node.get(12).setBottomNode(node.get(0));
    }

    private void shuffleBoard() {
        GameNode zero = node.get(0);
        Random random = new Random();

        int direction;
        for (int shuffleStep = 0; shuffleStep < 100; shuffleStep++) {
            direction = random.nextInt(4);
            if (direction == 0) {
                //left
                if (zero.getLeftNode() != null) {
                    zero.getLeftNode().getCoordinate(topLeft);
                }
            } else if (direction == 1) {
                //right
                if (zero.getRightNode() != null) {
                    zero.getRightNode().getCoordinate(topLeft);
                }
            } else if (direction == 2) {
                //top
                if (zero.getTopNode() != null) {
                    zero.getTopNode().getCoordinate(topLeft);
                }
            } else if (direction == 3) {
                //bottom
                if (zero.getBottomNode() != null) {
                    zero.getBottomNode().getCoordinate(topLeft);
                }
            }
            //check if there is a need to update top left node pointer
            if (!(topLeft.getLeftNode() != null && topLeft.getTopNode() != null)) {
                topLeft = topLeft.getNewTopleftNode();
            }

        }

    }

    public void printBoard() {

        StringBuilder sb = new StringBuilder("\n");
        sb.append("  |A |B |C |D").append("\n");
        //print first row
        sb.append("1.|");
        sb.append(getRight(sb, topLeft));
        sb.append("\n");
        //print second row
        sb.append("2.|");
        sb.append(getRight(sb, topLeft.getBottomNode()));
        sb.append("\n");
        //print third row
        sb.append("3.|");
        sb.append(getRight(sb, topLeft.getBottomNode().getBottomNode()));
        sb.append("\n");
        //print forth row
        sb.append("4.|");
        sb.append(getRight(sb, topLeft.getBottomNode().getBottomNode().getBottomNode()));

        System.out.println(sb.toString());
    }

    //helper for printing  board
    private String getRight(StringBuilder sb, GameNode node) {

        if (node != null) {
            sb.append(String.format("%02d", node.getValue())).append("|");
            return getRight(sb, node.getRightNode());
        }
        return "";
    }


    public boolean ifSuccess() {
        int check = 0;
        if (topLeft.getLeftNode() == null && topLeft.getTopNode() == null) {

            GameNode nr2 = topLeft.getRightNode();

            if (nr2.getValue() == 2) {
                if (nr2.getTopNode() == null && nr2.getLeftNode().getValue() == 1 && nr2.getRightNode().getValue() == 3
                        && nr2.getBottomNode().getValue() == 6) {
                    check++;
                }
            }
            GameNode nr9 = topLeft.getBottomNode().getBottomNode();
            if (check == 1) {

                if (nr9.getValue() == 9) {
                    if (nr9.getLeftNode() == null && nr9.getTopNode().getValue() == 5 && nr9.getRightNode().getValue() == 10
                            && nr9.getBottomNode().getValue() == 13) {
                        check++;
                    }
                }

            }

            GameNode nr15 = nr9.getBottomNode().getRightNode().getRightNode();

            if (check == 2) {
                if (nr15.getValue() == 15) {
                    if (nr15.getBottomNode() == null && nr15.getTopNode().getValue() == 11 && nr15.getRightNode().getValue() == 0
                            && nr15.getLeftNode().getValue() == 14) {
                        check++;
                    }
                }
            }

            GameNode nr8 = nr2.getBottomNode().getRightNode().getRightNode();

            if (check == 3) {
                if (nr8.getValue() == 8) {
                    if (nr8.getRightNode() == null && nr8.getTopNode().getValue() == 4 && nr8.getBottomNode().getValue() == 12
                            && nr8.getLeftNode().getValue() == 7) {
                        check++;
                    }
                }
            }

        }
        return check == 4;
    }

    public void move(String move) {
        if (move.length() != 2) return;

        String[] split = move.split("");
        if (isCorrectLetter(split[0]) && isCorrectNumber(split[1])) {
            int number = getCoordLetterToNumber(split[0]);

            if (!topLeft.moveNode(number, Integer.parseInt(split[1]) - 1)) {

                System.out.println("\n" + move + " is incorrect move");
            } else {
                if (!(topLeft.getLeftNode() != null && topLeft.getTopNode() != null)) {
                    topLeft = topLeft.getNewTopleftNode();
                }
            }
        } else {
            System.out.println("incorrect coordinate");
        }
    }

    private int getCoordLetterToNumber(String letter) {
        return letterCoord.get(letter);
    }

    private boolean isCorrectNumber(String numberCoord) {
        int i = Integer.parseInt(numberCoord);
        return i >= 1 && i <= 4;
    }

    private boolean isCorrectLetter(String letter) {
        return letterCoord.containsKey(letter);
    }
}
