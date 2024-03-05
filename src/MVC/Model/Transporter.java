package MVC.Model;

import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Transporter extends Truck{
    Random rand = new Random();
    private PlatformTransporter platformtrans;
    private boolean platform; //false = platform down
    private int maxCapacity;
    private final double distance = 5; // from transporter to car
    private boolean isWithinRange;
    protected Stack<Vehicle> vehicleStack;

    public Transporter() {
        super(Color.yellow, "Transporter02", 400, 2, 16000, 2600);
        platform = false;
        maxCapacity = 6;
        vehicleStack = new Stack<>();
        platformtrans = new PlatformTransporter();
        stopEngine();
        image = readImage(modelName);
    }

    protected void lowerTrans(Transporter trans){
        platformtrans.lower(trans);
    }

    protected void raiseTrans(Transporter trans){
        platformtrans.raise(trans);
    }

    protected boolean getPlatform() {
        return this.platform;
    }

    private double getMagnitude(double x, double y) {
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    private boolean isWithinRange(Vehicle car) {
        if (getMagnitude(car.currentxPos(), car.currentyPos()) <= this.distance) {
            isWithinRange = true;
        }
        return isWithinRange;
    }

    private boolean validCarSize(Vehicle car) {
        return car.getLength() <= 5200 && car.getWidth() <= 2600;
    }

    public void loadVehicle(Vehicle car, Transporter transporter) {
        transporter.lowerTrans(transporter); //asserts currentSpeed = 0
        if (!transporter.getPlatform() && isWithinRange(car)
                && vehicleStack.size() < transporter.maxCapacity && validCarSize(car) && car.isLoadable()) {
            vehicleStack.push(car);
            car.py = transporter.currentyPos();
            car.px = transporter.currentxPos();
        }
        transporter.raiseTrans(transporter);
    }

    public void unloadVehicle(Transporter transporter) {
        transporter.lowerTrans(transporter);
        if (!transporter.getPlatform()) {
            Vehicle car = vehicleStack.pop();
            car.px = Math.min(rand.nextDouble(), distance);
            car.py = Math.min(rand.nextDouble(), distance);
        }
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (this.platform) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }

    @Override
    public void decrementSpeed(double amount) {
        if (this.platform) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }

    @Override
    public boolean isLoadable(){
        return false;
    }

    @Override
    public boolean hasTurbo() { return false;}

    @Override
    public boolean hasPlatform() { return true;}
}
