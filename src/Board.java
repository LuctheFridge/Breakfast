import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    // Number of tiles on the board.
    private static final int BOARDSIZE = 3;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    // Create an array of buttons add them to the board.
    private int numberofButtons = BOARDSIZE*BOARDSIZE;

    public JButton[] buttonArray = new Button[numberofButtons];
    public Board() {
            for(int i = 0; i < numberofButtons; i++) {
                buttonArray[i] = new Button();
                this.add(buttonArray[i]);
            }

    // Initiate first boardstate.
    this.setOpaque(true);
    this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

    // Makes all new components correspond to the layout.
    this.setLayout(new GridLayout(BOARDSIZE, BOARDSIZE));
    }

}
