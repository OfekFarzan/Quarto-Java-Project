public class AiComputer {
    GameLogic board;
    Thinker thinker;
    AiThinker ai;

    boolean debug = true;

    /**
     * The function initializes the aiComputer
     * @param qb
     */
    public AiComputer(GameLogic qb) {
        this.board = qb;
        this.thinker = new Thinker();
        this.ai = new AiThinker();
    }

    /**
     * The function sets the ai's debug status
     * @param status
     */
    public void setDebug(boolean status) {
        debug = status;
        ai.setDebug(status);
    }

    /**
     *
     * @return a TurnNode item's move
     */
    public TurnNode getMove() {
        TurnNode result;
        int nRemSpots = board.getNumRemSpots();

        if (nRemSpots > 12) {
            // thinker
            result = thinker.getMove(board);
        } else if (nRemSpots <= 12 && nRemSpots > 7) {
            // minimax with depth restriction
            result = ai.getMove(board, 1);
        } else if (nRemSpots <= 7 && nRemSpots > 1) {
            // minimax with no depth restriction
            result = ai.getMove(board, 8);
        } else {
            // only one play

            int spot = board.getRemainingSpots()[0];
            result = new TurnNode(spot, 16, 'C');

            // QuartoConsole will check if it's Quarto
        }

        return result;
    }
}
