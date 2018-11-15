import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetHandler implements ActionListener {
            private Frame myFrame;

        public ResetHandler(Frame myFrame) {
            super();
            this.myFrame = myFrame;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            myFrame.getContentPane().removeAll();
            myFrame.resetGame();
        }
    }
