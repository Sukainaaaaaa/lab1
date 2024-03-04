import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel{
    public List<ImageHandler> drawable = new ArrayList<>();

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black);
    }

    public BufferedImage returnimage(ImageHandler ImageHandler){
       return ImageHandler.getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ImageHandler ImageHandler : drawable) {
            g.drawImage(returnimage(ImageHandler), ImageHandler.getPoint().x, ImageHandler.getPoint().y, null); // see javadoc for more info on the parameters
        }
    }
}
