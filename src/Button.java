import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;

public class Button extends JButton {
    private int xPos,yPos;
    private boolean buttonPressed;

    public Button(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    /* Image getters and buttoncheckers */
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}

    public ImageIcon getIconX() {return resizeIcon((createImageIcon("buttonX.png")));}
    public ImageIcon getIconO() {return resizeIcon((createImageIcon("buttonO.png")));}
    public boolean getbuttonPressed(){return buttonPressed;}
    public void setbuttonPressed(boolean buttonPressed){this.buttonPressed = buttonPressed;}



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
        int newWidth = Main.getDim() / Main.getBoardSize();
        int newHeight = Main.getDim() / Main.getBoardSize();
        Image newIMG = oldIMG.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        return new ImageIcon(newIMG);

    }
}