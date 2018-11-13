import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button extends JButton implements ActionListener {

    // No image = 0, buttonX = 1, buttonO = 2.
    private byte visible = 0;
    private ImageIcon buttonX;
    private ImageIcon buttonO;

    // Create JButton and initialize images.
    public Button() {
        System.out.println();
        buttonX = resizeIcon((createImageIcon("buttonX.png")));
        buttonO = resizeIcon((createImageIcon("buttonO.png")));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        visible++;
        visible%=3;
        switch(visible){
            case 0:
                setIcon(null);
                break;
            case 1:
                setIcon(buttonX);
                break;
            case 2:
                setIcon(buttonO);
                break;
        }
    }

    // Returns an image or null if the path was invalid.
    public ImageIcon createImageIcon(String path){
        ImageIcon imgURL = new ImageIcon(this.getClass().getResource(path));
        if (imgURL != null){
            return imgURL;
        } else{
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    // Resize the image to fit the button.
    private static ImageIcon resizeIcon(ImageIcon imgURL){
        Image oldIMG = imgURL.getImage();
        int newWidth = 150;
        int newHeight = 150;
        Image newIMG = oldIMG.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        return new ImageIcon(newIMG);

    }
}