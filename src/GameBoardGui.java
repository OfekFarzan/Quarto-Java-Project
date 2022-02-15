import javax.swing.*;
import java.awt.image.BufferedImage;

public class GameBoardGui implements Cloneable {
    private JButton[][] board;


    public GameBoardGui() {
        int i, j;
        BufferedImage icon;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                this.board[i][j] = new JButton();
                this.board[i][j].setActionCommand("empty");
            }
        }
    }

    public JButton[][] getBoard() {
        return board;
    }

    public void setBoard(JButton[][] board) {
        this.board = board;
    }

    public static final int RED = 1, BLUE = 2, BIG = 3, SMALL = 4, HOLLOW = 5, FULL = 6, SQUARE = 7, CIRCLE = 8;
    public static final String property1 = "RED", property2 = "BLUE", property3 = "BIG", property4 = "SMALL", property5 = "HOLLOW",
            property6 = "FULL", property7 = "SQUARE", property8 = "CIRCLE";

    public void placePiece(Piece piece, int spot, String who) throws Exception {
        if (spot > 0 && spot <= 16) {
            if(spot % 4 == 0)
                this.board[spot / 4 - 1][spot / 4 - 1].setActionCommand(piece.getProperties().toString());
            else
                this.board[spot / 4][spot % 4 - 1].setActionCommand(piece.getProperties().toString());
        } else if (!isSpotEmpty(spot))
            throw new Exception("The chosen spot is not empty");
        else
            throw new Exception("The chosen spot is out of range");


    }


    public boolean isSpotEmpty(int spot) {
        if (spot % 4 == 0) {
            return this.board[spot / 4 - 1][spot / 4 - 1].getActionCommand().equals("empty");
        }
        return this.board[spot / 4][spot % 4 - 1].getActionCommand().equals("empty");
    }

    public boolean gameOver(JButton[][] board) {
        return doneInColumn(this.board) || doneInMainDiagonal(this.board) || doneInRow(this.board) || doneInSecondaryDiagonal(this.board);
    }


    public boolean doneInColumn(JButton[][] board) {
        int i, j;

        for (i = 0; i < 4; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.board[i][i].getActionCommand().equals(property1) &&
                                this.board[1][i].getActionCommand().equals(property1) &&
                                this.board[2][i].getActionCommand().equals(property1) &&
                                this.board[3][i].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.board[i][i].getActionCommand().equals(property2) &&
                                this.board[1][i].getActionCommand().equals(property2) &&
                                this.board[2][i].getActionCommand().equals(property2) &&
                                this.board[3][i].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.board[i][i].getActionCommand().equals(property3) &&
                                this.board[1][i].getActionCommand().equals(property3) &&
                                this.board[2][i].getActionCommand().equals(property3) &&
                                this.board[3][i].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.board[i][i].getActionCommand().equals(property4) &&
                                this.board[1][i].getActionCommand().equals(property4) &&
                                this.board[2][i].getActionCommand().equals(property4) &&
                                this.board[3][i].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.board[i][i].getActionCommand().equals(property5) &&
                                this.board[1][i].getActionCommand().equals(property5) &&
                                this.board[2][i].getActionCommand().equals(property5) &&
                                this.board[3][i].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.board[i][i].getActionCommand().equals(property6) &&
                                this.board[1][i].getActionCommand().equals(property6) &&
                                this.board[2][i].getActionCommand().equals(property6) &&
                                this.board[3][i].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.board[i][i].getActionCommand().equals(property7) &&
                                this.board[1][i].getActionCommand().equals(property7) &&
                                this.board[2][i].getActionCommand().equals(property7) &&
                                this.board[3][i].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.board[i][i].getActionCommand().equals(property8) &&
                                this.board[1][i].getActionCommand().equals(property8) &&
                                this.board[2][i].getActionCommand().equals(property8) &&
                                this.board[3][i].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    public boolean doneInRow(JButton[][] board) {
        int i, j;

        for (i = 0; i < 4; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.board[i][i].getActionCommand().equals(property1) &&
                                this.board[i][1].getActionCommand().equals(property1) &&
                                this.board[i][2].getActionCommand().equals(property1) &&
                                this.board[i][3].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.board[i][i].getActionCommand().equals(property2) &&
                                this.board[i][1].getActionCommand().equals(property2) &&
                                this.board[i][2].getActionCommand().equals(property2) &&
                                this.board[i][3].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.board[i][i].getActionCommand().equals(property3) &&
                                this.board[i][1].getActionCommand().equals(property3) &&
                                this.board[i][2].getActionCommand().equals(property3) &&
                                this.board[i][3].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.board[i][i].getActionCommand().equals(property4) &&
                                this.board[i][1].getActionCommand().equals(property4) &&
                                this.board[i][2].getActionCommand().equals(property4) &&
                                this.board[i][3].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.board[i][i].getActionCommand().equals(property5) &&
                                this.board[i][1].getActionCommand().equals(property5) &&
                                this.board[i][2].getActionCommand().equals(property5) &&
                                this.board[i][3].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.board[i][i].getActionCommand().equals(property6) &&
                                this.board[i][1].getActionCommand().equals(property6) &&
                                this.board[i][2].getActionCommand().equals(property6) &&
                                this.board[i][3].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.board[i][i].getActionCommand().equals(property7) &&
                                this.board[i][1].getActionCommand().equals(property7) &&
                                this.board[i][2].getActionCommand().equals(property7) &&
                                this.board[i][3].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.board[i][i].getActionCommand().equals(property8) &&
                                this.board[i][1].getActionCommand().equals(property8) &&
                                this.board[i][2].getActionCommand().equals(property8) &&
                                this.board[i][3].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    public boolean doneInMainDiagonal(JButton[][] board) {
        int i, j;

        for (i = 0; i < 1; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.board[i][i].getActionCommand().equals(property1) &&
                                this.board[1][1].getActionCommand().equals(property1) &&
                                this.board[2][2].getActionCommand().equals(property1) &&
                                this.board[3][3].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.board[i][i].getActionCommand().equals(property2) &&
                                this.board[1][1].getActionCommand().equals(property2) &&
                                this.board[2][2].getActionCommand().equals(property2) &&
                                this.board[3][3].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.board[i][i].getActionCommand().equals(property3) &&
                                this.board[1][1].getActionCommand().equals(property3) &&
                                this.board[2][2].getActionCommand().equals(property3) &&
                                this.board[3][3].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.board[i][i].getActionCommand().equals(property4) &&
                                this.board[1][1].getActionCommand().equals(property4) &&
                                this.board[2][2].getActionCommand().equals(property4) &&
                                this.board[3][3].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.board[i][i].getActionCommand().equals(property5) &&
                                this.board[1][1].getActionCommand().equals(property5) &&
                                this.board[2][2].getActionCommand().equals(property5) &&
                                this.board[3][3].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.board[i][i].getActionCommand().equals(property6) &&
                                this.board[1][1].getActionCommand().equals(property6) &&
                                this.board[2][2].getActionCommand().equals(property6) &&
                                this.board[3][3].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.board[i][i].getActionCommand().equals(property7) &&
                                this.board[1][1].getActionCommand().equals(property7) &&
                                this.board[2][2].getActionCommand().equals(property7) &&
                                this.board[3][3].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.board[i][i].getActionCommand().equals(property8) &&
                                this.board[1][1].getActionCommand().equals(property8) &&
                                this.board[2][2].getActionCommand().equals(property8) &&
                                this.board[3][3].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    public boolean doneInSecondaryDiagonal(JButton[][] board) {
        int i, j;

        for (i = 0; i < 1; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.board[i][3].getActionCommand().equals(property1) &&
                                this.board[1][2].getActionCommand().equals(property1) &&
                                this.board[2][1].getActionCommand().equals(property1) &&
                                this.board[3][i].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.board[i][3].getActionCommand().equals(property2) &&
                                this.board[1][2].getActionCommand().equals(property2) &&
                                this.board[2][1].getActionCommand().equals(property2) &&
                                this.board[3][i].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.board[i][3].getActionCommand().equals(property3) &&
                                this.board[1][2].getActionCommand().equals(property3) &&
                                this.board[2][1].getActionCommand().equals(property3) &&
                                this.board[3][i].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.board[i][3].getActionCommand().equals(property4) &&
                                this.board[1][2].getActionCommand().equals(property4) &&
                                this.board[2][1].getActionCommand().equals(property4) &&
                                this.board[3][i].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.board[i][3].getActionCommand().equals(property5) &&
                                this.board[1][2].getActionCommand().equals(property5) &&
                                this.board[2][1].getActionCommand().equals(property5) &&
                                this.board[3][i].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.board[i][3].getActionCommand().equals(property6) &&
                                this.board[1][2].getActionCommand().equals(property6) &&
                                this.board[2][1].getActionCommand().equals(property6) &&
                                this.board[3][i].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.board[i][3].getActionCommand().equals(property7) &&
                                this.board[1][2].getActionCommand().equals(property7) &&
                                this.board[2][1].getActionCommand().equals(property7) &&
                                this.board[3][i].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.board[i][3].getActionCommand().equals(property8) &&
                                this.board[1][2].getActionCommand().equals(property8) &&
                                this.board[2][1].getActionCommand().equals(property8) &&
                                this.board[3][i].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }
}
