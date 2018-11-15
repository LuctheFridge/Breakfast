import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private JMenuBar menuBar;
    private GameBoard myBoard;
    private static final String TITLE = "A Game of Breakfast";

    /* Open up an instance of the board */
    public Frame() {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();
        this.setResizable(true);
        initMenu();
        resetGame();
        this.setVisible(true);
    }

    /* Method to set up a menu to control the game */
    private void initMenu() {
        ActionListener resetHandler = new ResetHandler(this);

        JMenuBar myMenu = new JMenuBar();
        JMenuItem itemNew = new JMenuItem("New Game");
        itemNew.addActionListener(resetHandler);
        JMenuItem itemHelp = new JMenuItem("X begint. Maak drie op een rij.");
        myMenu.add(itemNew);
        myMenu.add(itemHelp);
        setJMenuBar(myMenu);

    }

    public void resetGame() {
        Container c = getContentPane();
        myBoard = new GameBoard();
        c.add(myBoard);
        setContentPane(c);
    }

}
