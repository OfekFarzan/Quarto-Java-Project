
import java.util.Arrays;


public class GameLogic implements Cloneable{

    // Initializing the board
    private int[] board = {16, 16, 16, 16,
                           16, 16, 16, 16,
                           16, 16, 16, 16,
                           16, 16, 16, 16};

    private int[] pieceState = new int[16];      // piece status, initially all zero

    // Initializing the number of remaining spots, pieces and pieces that can be passed
    private int nRemSpots = 16;
    private int nRemPieces = 16;
    private int passedPiece = 16;

    public static final int OFF_BOARD = 0, IN_LIMBO = 1, IN_PLAY = 2;   // codes for pieceState

    public GameLogic() {
    }

    @Override
    /**
     * Clones the board and the piece status
     */
    public Object clone() {
        // deep copy clone

        GameLogic c = null;

        try {
            c = (GameLogic) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("GameLogic not cloneable!");
        }

        c.board = board.clone();
        c.pieceState = pieceState.clone();

        return c;
    }

    /**
     *
     * @param piece - the piece that will be passed to the other player(computer or human)
     * @return true if the piece can be placed on the board, else false
     */
    public boolean pass(int piece) {
        // sets the passed piece

        if (piece < 0 || piece > 15) {
            System.out.println("Error: this piece doesn't exist.");
            return false;
        }

        if (pieceState[piece] == OFF_BOARD) {
            passedPiece = piece;
            pieceState[piece] = IN_LIMBO;      // put this piece in limbo
            nRemPieces--;

            return true;
        } else {
            System.out.println("Error passing " + piece + ". Piece is " + ((pieceState[piece] == IN_LIMBO) ? "in-limbo" : "in-play") + ".");
            return false;
        }
    }

    /**
     *
     * @param spot - position in which the piece is asked to be placed
     * @return true if the piece is placed at the wanted spot, else false
     */
    public boolean play(int spot) {
        // sets the board at spot to the current passed piece

        if (spot < 16 && spot >= 0) {
            if (board[spot] != 16) {
                // this spot not empty
                System.out.println("Error: Spot " + spot + " not empty (contains piece " + board[spot] + ").");
                return false;
            } else if (pieceState[passedPiece] == OFF_BOARD || pieceState[passedPiece] == IN_PLAY) {
                // problem with the passedPiece
                System.out.println("Error: Piece " + passedPiece + " status is " + ((pieceState[passedPiece] == OFF_BOARD) ? "off-board" : "in-play") + ".");
                return false;
            } else {
                board[spot] = passedPiece;
                pieceState[passedPiece] = IN_PLAY;
                nRemSpots--;
                passedPiece = 16;

                return true;
            }
        } else {
            System.out.println("Bad arg passed to move(): " + spot + " out of bounds.");
            return false;
        }
    }

    /**
     *
     * @param spot - position in which the piece is placed
     * @return true if the game is over
     */
    public boolean isWin(int spot) {
        boolean checkRow = true;
        boolean checkCol = true;

        int rowhead = spot - (spot % 4);
        int colhead = spot % 4;

        int firstRow = board[rowhead], secondRow = board[rowhead + 1],
                thirdRow = board[rowhead + 2], forthRow = board[rowhead + 3];

        int firstColumn = board[colhead], secondColumn = board[colhead + 4],
                thirdColumn = board[colhead + 8], forthColumn = board[colhead + 12];

        // check for empty spots in row or column
        if (firstRow == 16 || secondRow == 16 ||
                thirdRow == 16 || forthRow == 16) {
            checkRow = false;
        }
        if (firstColumn == 16 || secondColumn == 16 ||
                thirdColumn == 16 || forthColumn == 16) {
            checkCol = false;
        }
        if (!checkRow && !checkCol) {
            return false;
        }

        // no empty spots, start comparing pieces in row and col
        int result;

        // check rows
        if (checkRow) {
            result = Piece.comparePieces(firstRow, secondRow, thirdRow, forthRow);
            if (result < 4)
                return true;
        }

        // check columns
        if (checkCol) {
            result = Piece.comparePieces(firstColumn, secondColumn, thirdColumn, forthColumn);
            if (result < 4)
                return true;
        }

        // nothing found - game isn't over
        return false;
    }

    /**
     *
     * @return an array of the remaining spots
     */
    public int[] getRemainingSpots() {
        int[] remainingSpotsArray = new int[nRemSpots];

        int m = 0;
        for (int i = 0; i < 16; i++) {
            if (board[i] == 16) {
                remainingSpotsArray[m] = i;
                m++;
            }
        }

        return remainingSpotsArray;
    }

    /**
     *
     * @return an array of the remaining pieces id
     */
    public int[] getRemainingPieces() {
        int[] remainingPiecesArray = new int[nRemPieces];

        int m = 0;
        for (int i = 0; i < 16; i++) {
            if (pieceState[i] == OFF_BOARD) {
                remainingPiecesArray[m] = i;
                m++;
            }
        }

        return remainingPiecesArray;
    }
    /**
     *
     * @return passed piece id
     */
    public int getPassedPiece() {
        return passedPiece;
    }

    /**
     *
     * @return number of remaining spots
     */
    public int getNumRemSpots() {
        return nRemSpots;
    }

    /**
     *
     * @return number of remaining pieces
     */
    public int getNumRemPieces() {
        return nRemPieces;
    }

    /**
     *
     * @param spot - Piece's position on board
     * @return Piece's id
     */
    public int getPieceAt(int spot) {
        return board[spot];
    }

    /**
     *
     * @param piece - Piece's id
     * @return the Piece's status
     */
    public int getPieceStatus(int piece) {
        return pieceState[piece];
    }

    public void clearBoard() {
        Arrays.fill(board, 16);   // 16 -> empty
        Arrays.fill(pieceState, OFF_BOARD);
        nRemSpots = 16;
        nRemPieces = 16;
        passedPiece = 16;
    }

    public void initRandomBoard() {
        // init random board and update pmat and counters
        // user has just played piece 5 to spot 4, and must choose a piece to pass

        // random board (with no quartos!)
        int[] randboard = {12, 16, 3, 16,
                5, 8, 16, 9,
                16, 7, 1, 13,
                0, 11, 16, 4};
        System.arraycopy(randboard, 0, board, 0, 16);

        // update pieceState - all pieces in play except 2, 10, 14, 15
        for (int i = 0; i < 16; i++) {
            pieceState[i] = IN_PLAY;
        }
        pieceState[2] = 0;
        pieceState[6] = 0;
        pieceState[10] = 0;
        pieceState[14] = 0;
        pieceState[15] = 0;

        // update counters
        nRemSpots = 5;
        nRemPieces = 5;

        // user has not passed yet
        passedPiece = 16;
    }
}
