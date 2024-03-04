import javax.swing.*;
import java.awt.*;

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;
    public static int getx(){return X;}
    public static int gety(){return Y;}
    DrawPanel drawPanel = new DrawPanel(X, Y-240);
    ControlPanel controlPanel;

    public CarView(String framename, ControlPanel cc){
        this.controlPanel = cc;
        initComponents(framename);
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);
        this.add(controlPanel);
        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}