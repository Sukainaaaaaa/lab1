import java.awt.*;

public abstract class Truck extends Vehicle{

    public Truck(Color color, String modelName, double enginePower, int nrDoors, int length, int width){
        super(color, modelName, enginePower, nrDoors, length, width);
    }

    public abstract void raisePlatform(double amount);
    public abstract void lowerPlatform(double amount);
    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);
}
