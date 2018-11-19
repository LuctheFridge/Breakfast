import javax.swing.JFrame;
import java.awt.Dimension;

public class Frame extends JFrame {

    public Frame() {
        setTitle(Main.getTITLE());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Main.getDim(), Main.getDim()));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        Menu myMenu = new Menu(this);
        setJMenuBar(myMenu.bar);
        setVisible(true);
    }
}
