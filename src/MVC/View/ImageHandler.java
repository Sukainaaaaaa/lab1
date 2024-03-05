package MVC.View;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageHandler {
    BufferedImage readImage(String imagename);
    BufferedImage getImage();
    Point getPoint();
    void setPoint(int x, int y);
    void removeImage();
}
