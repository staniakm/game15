package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final List<GameNode> gameBoard = new ArrayList<>();
    private GameNode topLeft;

    public Board() {
        prepareBoard();
        shuffleBoard();
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

    void move(int letterNumber, int number) {
        if (!topLeft.moveNode(letterNumber, number)) {

            System.out.println("Incorrect move");
        } else {
            if (!(topLeft.getLeftNode() != null && topLeft.getTopNode() != null)) {
                topLeft = topLeft.getNewTopleftNode();
            }
        }
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

    private void prepareBoard() {
        for (int i = 0; i < 16; i++) {
            gameBoard.add(new GameNode(i));
        }

        topLeft = gameBoard.get(1);

        gameBoard.get(2).setRightNode(gameBoard.get(3));
        gameBoard.get(2).setBottomNode(gameBoard.get(6));
        gameBoard.get(2).setLeftNode(gameBoard.get(1));

        gameBoard.get(8).setTopNode(gameBoard.get(4));
        gameBoard.get(8).setBottomNode(gameBoard.get(12));
        gameBoard.get(8).setLeftNode(gameBoard.get(7));

        gameBoard.get(15).setRightNode(gameBoard.get(0));
        gameBoard.get(15).setTopNode(gameBoard.get(11));
        gameBoard.get(15).setLeftNode(gameBoard.get(14));

        gameBoard.get(9).setRightNode(gameBoard.get(10));
        gameBoard.get(9).setTopNode(gameBoard.get(5));
        gameBoard.get(9).setBottomNode(gameBoard.get(13));

        gameBoard.get(4).setLeftNode(gameBoard.get(3));
        gameBoard.get(5).setTopNode(gameBoard.get(1));
        gameBoard.get(5).setRightNode(gameBoard.get(6));
        gameBoard.get(6).setRightNode(gameBoard.get(7));

        gameBoard.get(12).setLeftNode(gameBoard.get(11));
        gameBoard.get(11).setLeftNode(gameBoard.get(10));
        gameBoard.get(13).setRightNode(gameBoard.get(14));

        gameBoard.get(6).setBottomNode(gameBoard.get(10));
        gameBoard.get(10).setBottomNode(gameBoard.get(14));
        gameBoard.get(3).setBottomNode(gameBoard.get(7));
        gameBoard.get(11).setTopNode(gameBoard.get(7));
        gameBoard.get(12).setBottomNode(gameBoard.get(0));
    }

    private void shuffleBoard() {
        GameNode zero = gameBoard.get(0);
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

    private String getRight(StringBuilder sb, GameNode node) {

        if (node != null) {
            sb.append(String.format("%02d", node.getValue())).append("|");
            return getRight(sb, node.getRightNode());
        }
        return "";
    }
}
