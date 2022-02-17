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


}
