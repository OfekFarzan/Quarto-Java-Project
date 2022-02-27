import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBoardGui implements Cloneable {
    private JButton[][] board;
    private JFrame frame;
    private JPanel panel;

    public GameBoardGui() {
        int i, j;
        board = new JButton[4][4];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                this.board[i][j] = new JButton("X");
                this.board[i][j].setActionCommand("empty");
            }
        }

        panel = new JPanel(new GridLayout(4,4));
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                panel.add(this.board[i][j]);
            }
        }

        frame = new JFrame();
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(770, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public JButton[][] getBoard() {
        return board;
    }

    public void setBoard(JButton[][] board) {
        this.board = board;
    }


}
