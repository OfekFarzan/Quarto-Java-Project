import javax.swing.*;

public class GameLogic {

    private JButton[][] matrix;
    private Piece[] pieces;

    /**
     * Builds the JButtons matrix and initializes the action command of each cell to empty
     * Builds an array of pieces
     */
    public GameLogic() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                this.matrix[i][j] = new JButton();
                this.matrix[i][j].setActionCommand("empty");
            }
        }

        for (i = 0; i < 16; i++) {
            setPieces(pieces, i);
        }
    }

    public static final int RED = 1, BLUE = 2, BIG = 3, SMALL = 4, HOLLOW = 5, FULL = 6, SQUARE = 7, CIRCLE = 8;
    public static final String property1 = "RED", property2 = "BLUE", property3 = "BIG", property4 = "SMALL", property5 = "HOLLOW",
            property6 = "FULL", property7 = "SQUARE", property8 = "CIRCLE";

    /**
     * @param piece - what kind of piece to be placed on the board
     * @param spot  - placement on board
     * @param who   - The player or the AI
     * @throws Exception - if the chosen spot is not empty or out of range 1- 16
     */
    public void placePiece(Piece piece, int spot, String who) throws Exception {
        int index;
        if (spot > 0 && spot <= 16) {
            if (spot % 4 == 0)
                this.matrix[spot / 4 - 1][spot / 4 - 1].setActionCommand(piece.getProperties().toString());
            else
                this.matrix[spot / 4][spot % 4 - 1].setActionCommand(piece.getProperties().toString());
            if (who.equals("AI"))
                who = "Player";
            else
                who = "AI";
            this.pieces[spot - 1] = null;
            for (index = 0; index < 16; index++) {
                if (pieces[index].getProperties().equals(piece.getProperties())) {
                    pieces[index] = null;
                    break;
                }
            }

        } else if (!isSpotEmpty(spot))
            throw new Exception("The chosen spot is not empty");
        else
            throw new Exception("The chosen spot is out of range");


    }

    /**
     * @param spot
     * @return True if the chosen spot of the matrix (1-16) is empty,else returns False
     */
    public boolean isSpotEmpty(int spot) {
        if (spot % 4 == 0) {
            return this.matrix[spot / 4 - 1][spot / 4 - 1].getActionCommand().equals("empty");
        }
        return this.matrix[spot / 4][spot % 4 - 1].getActionCommand().equals("empty");
    }

    /**
     * @param matrix
     * @return True - GAME OVER if there is a case on the matrix-board in which there are 4 pieces with at least 1 common property
     * and arranged in the main diagonal or the secondary diagonal or in a row or in a column of the matrix, else returns False
     */

    public boolean gameOver(JButton[][] matrix) {
        return doneInColumn(this.matrix) || doneInMainDiagonal(this.matrix) || doneInRow(this.matrix) || doneInSecondaryDiagonal(this.matrix);
    }

    /**
     * @param matrix
     * @return True if there is a case on the matrix-board in which there are 4 pieces with at least 1 common property
     * and arranged in a column, else returns False
     */
    public boolean doneInColumn(JButton[][] matrix) {
        int i, j;

        for (i = 0; i < 4; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.matrix[i][i].getActionCommand().equals(property1) &&
                                this.matrix[1][i].getActionCommand().equals(property1) &&
                                this.matrix[2][i].getActionCommand().equals(property1) &&
                                this.matrix[3][i].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.matrix[i][i].getActionCommand().equals(property2) &&
                                this.matrix[1][i].getActionCommand().equals(property2) &&
                                this.matrix[2][i].getActionCommand().equals(property2) &&
                                this.matrix[3][i].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.matrix[i][i].getActionCommand().equals(property3) &&
                                this.matrix[1][i].getActionCommand().equals(property3) &&
                                this.matrix[2][i].getActionCommand().equals(property3) &&
                                this.matrix[3][i].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.matrix[i][i].getActionCommand().equals(property4) &&
                                this.matrix[1][i].getActionCommand().equals(property4) &&
                                this.matrix[2][i].getActionCommand().equals(property4) &&
                                this.matrix[3][i].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.matrix[i][i].getActionCommand().equals(property5) &&
                                this.matrix[1][i].getActionCommand().equals(property5) &&
                                this.matrix[2][i].getActionCommand().equals(property5) &&
                                this.matrix[3][i].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.matrix[i][i].getActionCommand().equals(property6) &&
                                this.matrix[1][i].getActionCommand().equals(property6) &&
                                this.matrix[2][i].getActionCommand().equals(property6) &&
                                this.matrix[3][i].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.matrix[i][i].getActionCommand().equals(property7) &&
                                this.matrix[1][i].getActionCommand().equals(property7) &&
                                this.matrix[2][i].getActionCommand().equals(property7) &&
                                this.matrix[3][i].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.matrix[i][i].getActionCommand().equals(property8) &&
                                this.matrix[1][i].getActionCommand().equals(property8) &&
                                this.matrix[2][i].getActionCommand().equals(property8) &&
                                this.matrix[3][i].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    /**
     * @param matrix
     * @return True if there is a case on the matrix-board in which there are 4 pieces with at least 1 common property
     * and arranged in a row, else returns False
     */
    public boolean doneInRow(JButton[][] matrix) {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.matrix[i][i].getActionCommand().equals(property1) &&
                                this.matrix[i][1].getActionCommand().equals(property1) &&
                                this.matrix[i][2].getActionCommand().equals(property1) &&
                                this.matrix[i][3].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.matrix[i][i].getActionCommand().equals(property2) &&
                                this.matrix[i][1].getActionCommand().equals(property2) &&
                                this.matrix[i][2].getActionCommand().equals(property2) &&
                                this.matrix[i][3].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.matrix[i][i].getActionCommand().equals(property3) &&
                                this.matrix[i][1].getActionCommand().equals(property3) &&
                                this.matrix[i][2].getActionCommand().equals(property3) &&
                                this.matrix[i][3].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.matrix[i][i].getActionCommand().equals(property4) &&
                                this.matrix[i][1].getActionCommand().equals(property4) &&
                                this.matrix[i][2].getActionCommand().equals(property4) &&
                                this.matrix[i][3].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.matrix[i][i].getActionCommand().equals(property5) &&
                                this.matrix[i][1].getActionCommand().equals(property5) &&
                                this.matrix[i][2].getActionCommand().equals(property5) &&
                                this.matrix[i][3].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.matrix[i][i].getActionCommand().equals(property6) &&
                                this.matrix[i][1].getActionCommand().equals(property6) &&
                                this.matrix[i][2].getActionCommand().equals(property6) &&
                                this.matrix[i][3].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.matrix[i][i].getActionCommand().equals(property7) &&
                                this.matrix[i][1].getActionCommand().equals(property7) &&
                                this.matrix[i][2].getActionCommand().equals(property7) &&
                                this.matrix[i][3].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.matrix[i][i].getActionCommand().equals(property8) &&
                                this.matrix[i][1].getActionCommand().equals(property8) &&
                                this.matrix[i][2].getActionCommand().equals(property8) &&
                                this.matrix[i][3].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }


    /**
     * @param matrix
     * @return True if there is a case on the matrix-board in which there are 4 pieces with at least 1 common property
     * and arranged in the main diagonal, else returns False
     */
    public boolean doneInMainDiagonal(JButton[][] matrix) {
        int i, j;

        for (i = 0; i < 1; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.matrix[i][i].getActionCommand().equals(property1) &&
                                this.matrix[1][1].getActionCommand().equals(property1) &&
                                this.matrix[2][2].getActionCommand().equals(property1) &&
                                this.matrix[3][3].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.matrix[i][i].getActionCommand().equals(property2) &&
                                this.matrix[1][1].getActionCommand().equals(property2) &&
                                this.matrix[2][2].getActionCommand().equals(property2) &&
                                this.matrix[3][3].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.matrix[i][i].getActionCommand().equals(property3) &&
                                this.matrix[1][1].getActionCommand().equals(property3) &&
                                this.matrix[2][2].getActionCommand().equals(property3) &&
                                this.matrix[3][3].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.matrix[i][i].getActionCommand().equals(property4) &&
                                this.matrix[1][1].getActionCommand().equals(property4) &&
                                this.matrix[2][2].getActionCommand().equals(property4) &&
                                this.matrix[3][3].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.matrix[i][i].getActionCommand().equals(property5) &&
                                this.matrix[1][1].getActionCommand().equals(property5) &&
                                this.matrix[2][2].getActionCommand().equals(property5) &&
                                this.matrix[3][3].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.matrix[i][i].getActionCommand().equals(property6) &&
                                this.matrix[1][1].getActionCommand().equals(property6) &&
                                this.matrix[2][2].getActionCommand().equals(property6) &&
                                this.matrix[3][3].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.matrix[i][i].getActionCommand().equals(property7) &&
                                this.matrix[1][1].getActionCommand().equals(property7) &&
                                this.matrix[2][2].getActionCommand().equals(property7) &&
                                this.matrix[3][3].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.matrix[i][i].getActionCommand().equals(property8) &&
                                this.matrix[1][1].getActionCommand().equals(property8) &&
                                this.matrix[2][2].getActionCommand().equals(property8) &&
                                this.matrix[3][3].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    /**
     * @param matrix
     * @return True if there is a case on the matrix-board in which there are 4 pieces with at least 1 common property
     * and arranged in the secondary diagonal, else returns False
     */
    public boolean doneInSecondaryDiagonal(JButton[][] matrix) {
        int i, j;

        for (i = 0; i < 1; i++) {
            for (j = 1; j <= 8; j++) {
                switch (j) {
                    case RED:
                        if (this.matrix[i][3].getActionCommand().equals(property1) &&
                                this.matrix[1][2].getActionCommand().equals(property1) &&
                                this.matrix[2][1].getActionCommand().equals(property1) &&
                                this.matrix[3][i].getActionCommand().equals(property1))
                            return true;
                        break;
                    case BLUE:
                        if (this.matrix[i][3].getActionCommand().equals(property2) &&
                                this.matrix[1][2].getActionCommand().equals(property2) &&
                                this.matrix[2][1].getActionCommand().equals(property2) &&
                                this.matrix[3][i].getActionCommand().equals(property2))
                            return true;
                        break;
                    case BIG:
                        if (this.matrix[i][3].getActionCommand().equals(property3) &&
                                this.matrix[1][2].getActionCommand().equals(property3) &&
                                this.matrix[2][1].getActionCommand().equals(property3) &&
                                this.matrix[3][i].getActionCommand().equals(property3))
                            return true;
                        break;
                    case SMALL:
                        if (this.matrix[i][3].getActionCommand().equals(property4) &&
                                this.matrix[1][2].getActionCommand().equals(property4) &&
                                this.matrix[2][1].getActionCommand().equals(property4) &&
                                this.matrix[3][i].getActionCommand().equals(property4))
                            return true;
                        break;
                    case HOLLOW:
                        if (this.matrix[i][3].getActionCommand().equals(property5) &&
                                this.matrix[1][2].getActionCommand().equals(property5) &&
                                this.matrix[2][1].getActionCommand().equals(property5) &&
                                this.matrix[3][i].getActionCommand().equals(property5))
                            return true;
                        break;
                    case FULL:
                        if (this.matrix[i][3].getActionCommand().equals(property6) &&
                                this.matrix[1][2].getActionCommand().equals(property6) &&
                                this.matrix[2][1].getActionCommand().equals(property6) &&
                                this.matrix[3][i].getActionCommand().equals(property6))
                            return true;
                        break;
                    case SQUARE:
                        if (this.matrix[i][3].getActionCommand().equals(property7) &&
                                this.matrix[1][2].getActionCommand().equals(property7) &&
                                this.matrix[2][1].getActionCommand().equals(property7) &&
                                this.matrix[3][i].getActionCommand().equals(property7))
                            return true;
                        break;
                    case CIRCLE:
                        if (this.matrix[i][3].getActionCommand().equals(property8) &&
                                this.matrix[1][2].getActionCommand().equals(property8) &&
                                this.matrix[2][1].getActionCommand().equals(property8) &&
                                this.matrix[3][i].getActionCommand().equals(property8))
                            return true;
                        break;
                    default:

                        throw new IllegalStateException("Unexpected value: " + j);
                }
            }
        }

        return false;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * The function sets each piece's properties to each cell of the array of pieces
     *
     * @param pieces - The array of pieces
     * @param i      - Index in the array of pieces
     */
    private void setPieces(Piece[] pieces, int i) {
        String[] properties = new String[4];
        switch (i) {
            case 0:
                properties[0] = property1;
                properties[1] = property3;
                properties[2] = property5;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 1:
                properties[0] = property1;
                properties[1] = property4;
                properties[2] = property5;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 2:
                properties[0] = property1;
                properties[1] = property3;
                properties[2] = property6;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 3:
                properties[0] = property1;
                properties[1] = property3;
                properties[2] = property5;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 4:
                properties[0] = property2;
                properties[1] = property3;
                properties[2] = property5;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 5:
                properties[0] = property2;
                properties[1] = property4;
                properties[2] = property5;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 6:
                properties[0] = property2;
                properties[1] = property3;
                properties[2] = property6;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 7:
                properties[0] = property2;
                properties[1] = property3;
                properties[2] = property5;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 8:
                properties[0] = property1;
                properties[1] = property3;
                properties[2] = property6;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 9:
                properties[0] = property1;
                properties[1] = property4;
                properties[2] = property6;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 10:
                properties[0] = property1;
                properties[1] = property4;
                properties[2] = property5;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 11:
                properties[0] = property1;
                properties[1] = property4;
                properties[2] = property6;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
            case 12:
                properties[0] = property2;
                properties[1] = property3;
                properties[2] = property6;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 13:
                properties[0] = property2;
                properties[1] = property4;
                properties[2] = property6;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 14:
                properties[0] = property2;
                properties[1] = property4;
                properties[2] = property5;
                properties[3] = property8;
                pieces[i].setProperties(properties);
                break;
            case 15:
                properties[0] = property2;
                properties[1] = property4;
                properties[2] = property6;
                properties[3] = property7;
                pieces[i].setProperties(properties);
                break;
        }
    }

}
