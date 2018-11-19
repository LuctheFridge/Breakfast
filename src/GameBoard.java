import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel {

    /* Initialize a field of buttons and variables for clarity */
    private JButton[][] myButtons;

    /* A board of 3x3 buttons in a 500x500 frame */
    private int boardSize = Main.getBoardSize();
    private int dimension = Main.getDim();

    public GameBoard() {
        setSize(dimension, dimension);
        setLayout(new GridLayout(boardSize, boardSize));

        // Create all buttons and place them on the board.
        myButtons = new Button[boardSize][boardSize];
        for (int u = 0; u < boardSize; u++) {
            for (int i = 0; i < boardSize; i++) {
                myButtons[i][u] = new Button(i, u);

                ActionListener ButtonHandler = new ButtonHandler();
                myButtons[i][u].addActionListener(ButtonHandler);

                this.add(myButtons[i][u]);
            }
        }
    }
}
