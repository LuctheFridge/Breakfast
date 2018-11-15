import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    private ImageIcon buttonX;
    private ImageIcon buttonO;
    private boolean test;

    public ImageIcon getIconX() {return resizeIcon((createImageIcon("buttonX.png")));}
    public ImageIcon getIconO() {return resizeIcon((createImageIcon("buttonO.png")));}
    public boolean getTest(){return test;}
    public void setTest(boolean test){this.test = test;}

    /* Returns an image or null if the path was invalid. */
    public ImageIcon createImageIcon(String path){
        ImageIcon imgURL = new ImageIcon(this.getClass().getResource(path));
        if (imgURL != null){
            return imgURL;
        } else{
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    /* Resize the image to fit the button. */
    private static ImageIcon resizeIcon(ImageIcon imgURL){
        Image oldIMG = imgURL.getImage();
        int newWidth = 150;
        int newHeight = 150;
        Image newIMG = oldIMG.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        return new ImageIcon(newIMG);

    }
}