import javax.swing.JFrame;

public class Frame extends JFrame {
    public Board board;
    private static final String TITLE = "Breakfast game";

    public Frame() {
        super(TITLE);
        board = new Board();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(board);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
