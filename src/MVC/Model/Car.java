package MVC.Model;

import java.awt.*;

public abstract class Car extends Vehicle {

    public Car(Color color, String modelName, double enginePower, int nrDoors, int length, int width){
        super(color, modelName, enginePower, nrDoors, length, width);
    }

    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);
}
