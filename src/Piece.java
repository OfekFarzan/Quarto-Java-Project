/*
 * QPiece.java
 * Utility class for handling information of a Quarto piece
 */

//package quarto;

import java.lang.IllegalArgumentException;

public class Piece {

    // Each structure's property has a mask in order to indicate it's content
    public static int SIZE_MASK = 8, COLOR_MASK = 4, SHAPE_MASK = 2, INSIDE_MASK = 1;

    // Each property's content has an indicator - 1 or 0
    public static int BIG = 1, BLUE = 1, SQUARE = 1, FULL = 1,
            SMALL = 0, RED = 0, CIRCLE = 0, HOLLOW = 0;

    // 1 - Game is in progress
    public static int OFF_BOARD = 0,
            IN_PLAY = 1;

    /**
     * @param index - the index in which the piece will be putted in
     * @return - the chosen piece in a string format
     * @throws IllegalArgumentException
     */
    public static String getStringFromIndex(int index) throws IllegalArgumentException {
        if (index == 16)
            return "----";

        if (index < 0 || index > 16)
            throw new IllegalArgumentException("Piece index out of bounds.");

        String piece = "";

        //Executes mask on each property
        int height = (index & SIZE_MASK) >> 3,
                color = (index & COLOR_MASK) >> 2,
                shape = (index & SHAPE_MASK) >> 1,
                inside = (index & INSIDE_MASK);

        //Builds the string format piece
        if (height == BIG) {
            piece += "BI";
        } else {
            piece += "SM";
        }
        if (color == BLUE) {
            piece += "BL";
        } else {
            piece += "RE";
        }
        if (shape == SQUARE) {
            piece += "SQ";
        } else {
            piece += "CI";
        }
        if (inside == FULL) {
            piece += "FU";
        } else {
            piece += "HO";
        }

        return piece;
    }


    /**
     * @param piece - the string format piece
     * @return piece's index on the board
     * @throws IllegalArgumentException
     */
    public static int getIndexFromString(String piece) throws IllegalArgumentException {
        piece = piece.toUpperCase().trim();

        //Checks if the string formatted piece is out of range
        if (piece.length() != 8) {
            throw new IllegalArgumentException("The piece \"" + piece + "\" isn't recognized, wrong length (" + piece.length() + ").");
        }

        int size, color, shape, inside;

        //Checks the properties of the piece and puts them in each variable
        if (piece.substring(0, 2).contains("BI")) {
            size = BIG;
        } else {
            size = SMALL;
        }

        if (piece.substring(2, 4).contains("BL")) {
            color = BLUE;
        } else {
            color = RED;
        }
        if (piece.substring(4, 6).contains("SQ")) {
            shape = SQUARE;
        } else {
            shape = CIRCLE;
        }

        if (piece.substring(6, 8).contains("FU")) {
            inside = FULL;
        } else {
            inside = HOLLOW;
        }

        // index guaranteed between 0-15
        int index = size * 8 + color * 4 + shape * 2 + inside;

        return index;
    }

    /**
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return attribute that made the first found quarto
     */
    public static int comparePieces(int p1, int p2, int p3, int p4) {

        int tot = 0;

        // loop on type
        for (int j = 0; j < 4; j++) {
            tot = ((p1 & (1 << j)) >> j) + ((p2 & (1 << j)) >> j) + ((p3 & (1 << j)) >> j) + ((p4 & (1 << j)) >> j);
            if (tot == 4 || tot == 0) {
                return j;
            }
            tot = 0;
        }

        return 4;
    }

    /**
     * @param att
     * @return the attribute of each property
     */
    public static String getAttributeAsString(int att) {
        if (att == 0) return "solidity";
        else if (att == 1) return "shape";
        else if (att == 2) return "color";
        else if (att == 3) return "size";
        else return "unknown";
    }
}
