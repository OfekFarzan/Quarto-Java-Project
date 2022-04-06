import java.util.Random;

public class Thinker {
    public Thinker() {
    }

    public TurnNode getMove(GameLogic board) {
        TurnNode result;
        Random rgen = new Random();
        int[] remainingSpots = board.getRemainingSpots();
        int winner = -1;
        boolean isQ = false;
        int randPiece;

        // choose spot
        for (int i = 0; i < remainingSpots.length; i++) {
            if (testPlay(board, board.getPassedPiece(), remainingSpots[i])) {
                winner = i;
                isQ = true;
                break;
            }
        }
        if (winner == -1)
            winner = rgen.nextInt(remainingSpots.length);

        // choose piece to pass
        randPiece = board.getRemainingPieces()[rgen.nextInt(board.getNumRemPieces())];

        // build TurnNode
        result = new TurnNode(remainingSpots[winner], randPiece, 'C');
        if (isQ)
            result.isQuarto = true;

        return result;
    }

    public boolean testPlay(GameLogic boardstate, int piece, int spot) {
        GameLogic cboard = (GameLogic) boardstate.clone();

        if (!cboard.play(spot))
            System.out.println("Error testing play. (Thinker)");

        if (cboard.isWin(spot))
            return true;
        else
            return false;
    }


}
