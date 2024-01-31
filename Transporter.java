import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Transporter extends Scania {
    Random rand = new Random();
    private boolean platform; //false = platform down
    private int maxCapacity;
    private final double distance = 5; // from transporter to car
    private boolean isWithinRange;
    protected Stack<Car> carStack;

    public Transporter() {
        platform = false;
        maxCapacity = 6;
        carStack = new Stack<>();
        nrDoors = 2;
        color = Color.white;
        enginePower = 400;
        modelName = "Transporter02";
        stopEngine();
    }

    protected boolean getPlatform() {
        return this.platform;
    }

    protected void raisePlatform() {
        if (this.currentSpeed != 0) {
            throw new IllegalArgumentException("Transporter must be stationary");
        }
        platform = true;
    }

    protected void lowerPlatform() {
        if (this.currentSpeed != 0) {
            throw new IllegalArgumentException("Transporter must be stationary");
        }
        platform = false;
    }

    private double getMagnitude(double x, double y) {
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    private boolean isWithinRange(Car car) {
        if (getMagnitude(car.currentxPos(), car.currentyPos()) <= this.distance) {
            isWithinRange = true;
        }
        return isWithinRange;
    }

    private boolean validCarSize(Car car) {
        return car.getLength() <= 5200 && car.getWidth() <= 2600;
    }

    public void loadCar(Car car, Transporter transporter) {
        transporter.lowerPlatform(); //asserts currentSpeed = 0
        if (!transporter.getPlatform() && isWithinRange(car)
                && carStack.size() < transporter.maxCapacity && validCarSize(car)) {
            if (!(car instanceof Scania)) { //Asserts car cannot be transporter as well
                carStack.push(car);
                car.py = transporter.currentyPos();
                car.px = transporter.currentxPos();
            }
            transporter.raisePlatform();
        }
    }

    public void unloadCar(Transporter transporter) {
        transporter.lowerPlatform();
        if (!transporter.getPlatform()) {
            Car car = carStack.pop();
            car.px = Math.min(rand.nextDouble(), distance);
            car.py = Math.min(rand.nextDouble(), distance);
        }
    }
}
