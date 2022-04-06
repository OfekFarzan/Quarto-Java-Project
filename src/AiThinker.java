import java.util.Iterator;

public class AiThinker {
    public boolean debug = true;

    public AiThinker() {
    }

    /**
     * @param board
     * @param depth - depth in decision tree
     * @return the current move from the tree
     */
    public TurnNode getMove(GameLogic board, int depth) {
        int z = board.getNumRemSpots() * board.getNumRemPieces();
        TurnNode[] rootnodes = new TurnNode[z];

        // build tree
        int[] remainingSpots = board.getRemainingSpots();
        int[] remainingPieces = board.getRemainingPieces();
        int passedPiece = board.getPassedPiece();
        int m = 0;
        for (int i = 0; i < board.getNumRemSpots(); i++) {
            for (int j = 0; j < board.getNumRemPieces(); j++) {
                rootnodes[m] = buildTree(passedPiece, remainingSpots[i], remainingPieces[j], board, 'C', depth);
                m++;
            }
        }

        // display tree (debug)
        if (debug) {
            for (int k = 0; k < m; k++) {
                System.out.println(" ROOT:" + displayTree(rootnodes[k]));
            }
        }

        // compute minimax
        if (debug) System.out.print(" Computing minimax: ");
        int[] outcomes = new int[z];
        int res = 0;

        for (int x = 0; x < z; x++) {
            outcomes[x] = computeAi(rootnodes[x]);

            if (debug) System.out.print(rootnodes[x].getString() + "=" + outcomes[x] + ((x < (z - 1)) ? "," : " "));

            // continues to upgrade, otherwise keeps res=0
            if (x > 0 && outcomes[x] > outcomes[res]) {
                res = x;
            }
        }
        if (debug) System.out.println();

        return rootnodes[res];
    }

    /**
     * @param passedPiece - the chosen piece
     * @param spot        - the spot in which the piece will be placed
     * @param nextPiece   - next piece to be placed
     * @param boardstate  - full or not
     * @param player      - player or computer
     * @param depth       - the depth in the tree
     * @return decision tree
     */
    public TurnNode buildTree(int passedPiece, int spot, int nextPiece, GameLogic boardstate, char player, int depth) {
        TurnNode node = new TurnNode(spot, nextPiece, player);
        GameLogic cboard = (GameLogic) boardstate.clone();

        if (!cboard.play(spot))
            System.out.println("Error testing play in buildTree(). (MinimaxThinker)");

        if (cboard.isWin(spot)) {
            node.isLeaf = true;
            node.isQuarto = true;
            return node;
        }

        // check if this is a leaf before we continue
        if (nextPiece == 16) {
            node.isLeaf = true;
            return node;
        }

        if (depth == 0) {
            // we have reached desired depth
            node.isLeaf = true;
            return node;
        }

        // begin to build child nodes...

        cboard.pass(nextPiece);

        int[] remspots = cboard.getRemainingSpots();
        int[] rempieces = cboard.getRemainingPieces();

        for (int i = 0; i < cboard.getNumRemSpots(); i++) {
            if (rempieces.length > 0) {
                for (int j = 0; j < cboard.getNumRemPieces(); j++) {
                    node.addChild(buildTree(nextPiece, remspots[i], rempieces[j], cboard, (player == 'C') ? 'U' : 'C', depth - 1));
                }
            } else  // no pieces left = leaf node, use a placeholder for nextPiece
                node.addChild(buildTree(nextPiece, remspots[i], 16, cboard, (player == 'C') ? 'U' : 'C', depth - 1));
        }

        return node;
    }

    /**
     * @param node -The TurnNode item
     * @return ans
     */
    private int computeAi(TurnNode node) {
        if (node.isLeaf) {
            if (node.isQuarto)
                return (node.player == 'C') ? 1 : -1;
            else
                return 0;
        }

        int tans, ans = 0;
        Iterator i = node.children.iterator();
        while (i.hasNext()) {
            tans = computeAi((TurnNode) i.next());
            if (node.player == 'C')
                ans = Math.min(ans, tans);
            else
                ans = Math.max(ans, tans);
        }

        return ans;
    }

    /**
     * @param root - The TurnNode root
     * @return a string of the decision tree
     */
    public String displayTree(TurnNode root) {
        // returns decision tree in String form

        String str = "";

        str += root.getString();

        if (!root.isLeaf) {
            str += "-(";
            Iterator ci = root.children.iterator();
            while (ci.hasNext()) {
                str += displayTree((TurnNode) ci.next());
            }
            str += ")";
        }

        return str;
    }

    /**
     * The function sets the debug status, true if it's on
     *
     * @param status
     */
    public void setDebug(boolean status) {
        debug = status;
    }

}
