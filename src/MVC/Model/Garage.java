package MVC.Model;

import MVC.View.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Garage<T extends Vehicle> implements ImageHandler {
    private String name;
    private int maxAmount;
    public List<T> vehicles;
    private BufferedImage image;
    private double px;
    private double py;

    public Garage(String name, int maxAmount, double px, double py) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.vehicles = new ArrayList<T>();
        this.image = readImage(name);
        this.px = px;
        this.py = py;
    }

    protected void admitVehicle(T car) {
        if ((vehicles.size() < maxAmount)) {
            vehicles.add(car);
        }
    }
    protected void pickUpVehicle(T car) {
        vehicles.remove(car);
    }

    public double getpx(){
        return this.px;
    }

    public double getpy(){
        return this.py;
    }
    @Override
    public BufferedImage readImage(String imagename) {
        try {
            return ImageIO.read(Garage.class.getResourceAsStream("pics/"+imagename+".jpg"));
        } catch (IOException ex) {ex.printStackTrace();}
        return null;
    }

    @Override
    public BufferedImage getImage(){
        return image;
    }

    @Override
    public Point getPoint(){
        return new Point((int)px, (int)py);
    }

    @Override
    public void setPoint(int x, int y) {
        this.px = x;
        this.py = y;
    }

    @Override
    public void removeImage() {
        this.image = null;
    }

}
