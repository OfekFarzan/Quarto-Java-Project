public class GameBoard {
    GameLogic qb;

    /**
     *
     * @param board - the QuatroBoard
     */
    public GameBoard(GameLogic board) {
        qb = board;
    }

    /**
     *
     * @return board in string format
     */
    public String displayBoard() {
        String b = "";
        int p;

        for (int i = 0; i < 16; i++) {
            p = qb.getPieceAt(i);
            if (p == 16)
                b += "-" + ((i < 10) ? "0" : "") + i + "-" + "  ";
            else
                b += Piece.getStringFromIndex(p) + "  ";

            if ((i + 1) % 4 == 0 && i != 15) {
                b += "\n";
            }
        }

        return b;
    }

    /*
    public String displayNumberingScheme() {
        String q = " 0   1   2   3 \n"
                +  " 4   5   6   7 \n"
                +  " 8   9   10  11 \n"
                +  " 12  13  14  15";

        return q;
    }
    */

    /**
     *
     * @return a string of remaining pieces
     */
    public String displayRemainingPieces() {
        String pieces = "";
        int[] remaningPieces = qb.getRemainingPieces();

        for (int i = 0; i < remaningPieces.length; i++) {
            pieces += Piece.getStringFromIndex(remaningPieces[i]) + ((i < (remaningPieces.length - 1)) ? ", " : " ");
        }

        return pieces;
    }

    /**
     *
     * @return a string of remaining spots
     */
    public String displayRemainingSpots() {
        String spots = "";
        int[] remainingspots = qb.getRemainingSpots();

        for (int i = 0; i < remainingspots.length; i++) {
            spots += remainingspots[i] + ((i < (remainingspots.length - 1)) ? ", " : " ");
        }

        return spots;
    }

    /**
     *
     * @return a string format of the passed piece
     */
    public String displayPassedPiece() {
        return Piece.getStringFromIndex(qb.getPassedPiece());
    }
}
