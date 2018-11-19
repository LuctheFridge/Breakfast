import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetHandler implements ActionListener {
            private Frame myFrame;

        public ResetHandler(Frame myFrame) {
            this.myFrame = myFrame;
        }

        /* Method that handles when menu button is pressed */
        public void actionPerformed(ActionEvent e) {
            myFrame.getContentPane().removeAll();
            resetGame(myFrame);
        }

    /* Set up a new GameBoard on reset. */
    public static void resetGame(Frame myFrame) {
        Container c = myFrame.getContentPane();
        GameBoard myBoard = new GameBoard();
        c.add(myBoard);
        myFrame.setContentPane(c);
    }
}
