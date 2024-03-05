package MVC.Model;

import MVC.View.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public abstract class Vehicle implements Movable, Loadable, Turbo, Platform, ImageHandler {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    protected String modelName; // The car model name
    protected double px;
    protected double py;
    protected Direction d = Direction.E;
    protected int length; //in mm
    protected int width; //in mm
    protected BufferedImage image;
    protected enum Direction {
        N, E, W, S
    }

    public Vehicle(Color color, String modelName, double enginePower, int nrDoors, int length, int width){
        this.color = color;
        this.modelName = modelName;
        this.enginePower = enginePower;
        this.nrDoors = nrDoors;
        this.length = length;
        this.width = width;
    }

    protected int getNrDoors() {
        return nrDoors;
    }
    protected double getEnginePower() {
        return enginePower;
    }
    protected double getCurrentSpeed() {
        return currentSpeed;
    }
    protected Color getColor() {
        return color;
    }
    protected void setColor(Color clr) {
        color = clr;
    }
    public void startEngine() {
        currentSpeed = 0.1 * speedFactor();
    }
    public void stopEngine() {currentSpeed = 0;}
    public double currentxPos() {
        return this.px;
    }
    public double currentyPos() {
        return this.py;
    }
    protected int getLength(){return this.length;}
    protected int getWidth(){return this.width;}
    protected Direction getDirection() {
        return this.d;
    }
    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);

    @Override
    public BufferedImage readImage(String modelName) {
        try {
            return ImageIO.read(Vehicle.class.getResourceAsStream("pics/" + modelName + ".jpg"));
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
    public void setPoint(int x, int y){
        this.px = x;
        this.py = y;
    }

    @Override
    public void removeImage(){
        this.image = null;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Gas out of bounds");
        }
        if(currentSpeed > 0){ incrementSpeed(amount);}

    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Brake out of bounds");
        }
        decrementSpeed(amount);
    }

    public void move() {
        switch (d) {
            case S:
                py -= currentSpeed;
                break;

            case W:
                px -= currentSpeed;
                break;

            case N:
                py += currentSpeed;
                break;

            case E:
                px += currentSpeed;
                break;

        }
    }

    public void turnLeft() {
        switch (d) {
            case S:
                d = Direction.E;
                break;

            case W:
                d = Direction.S;
                break;

            case N:
                d = Direction.W;
                break;

            case E:
                d = Direction.N;
                break;
        }
    }

    public void turnRight() {
        switch (d) {
            case S:
                d = Direction.W;
                break;

            case W:
                d = Direction.N;
                break;

            case N:
                d = Direction.E;
                break;

            case E:
                d = Direction.S;
                break;
        }
    }

}
