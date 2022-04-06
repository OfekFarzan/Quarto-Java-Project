import java.util.ArrayList;

// Represents a player's turn as part of the AI's tree
public class TurnNode {
     int spot; // The piece's position in this turn
     int passedPiece; // The passed piece id in this turn
     char player; // Indicates whether it's the computer or the player
     ArrayList<TurnNode> children; // A list of all possible turns
     boolean isLeaf;
     boolean isQuarto; // Holds a boolean that indicates if the game is over

    /**
     * The function builds a TurnNode item
     *
     * @param s  - The wanted piece's position in this turn
     * @param pp - The wanted passed piece id in this turn
     * @param p  -  Indicates whether it's the computer or the player
     */
    public TurnNode(int s, int pp, char p) {
        spot = s;
        passedPiece = pp;
        player = p;
        children = new ArrayList<TurnNode>();
        isLeaf = true;
        isQuarto = false;
    }

    /**
     * @param node - A TurnNode item
     * @return a boolean that indicates if it's adding the item was executed
     */
    public boolean addChild(TurnNode node) {
        if (children.add(node)) {
            isLeaf = false;
            return true;
        } else {
            System.out.println("Error adding child in TurnNode.addChild");
            return false;
        }
    }

    /**
     * @return a string format of the turn
     */
    public String getString() {
        String str = "<" + spot + "," + passedPiece + "," + player;
        if (isQuarto)
            str += ",Q!";
        str += ">";
        return str;
    }
}
