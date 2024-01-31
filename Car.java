import java.awt.*;

public abstract class Car implements Movable { //
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double px;
    protected double py;
    protected Direction d;
    protected int length; //in mm
    protected int width; //in mm

    protected enum Direction {
        N, E, W, S
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
    protected void startEngine() {
        currentSpeed = 0.1;
    }
    protected void stopEngine() {
        currentSpeed = 0;
    }
    protected double currentxPos() {
        return this.px;
    }
    protected double currentyPos() {
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

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Gas out of bounds");
        }
        incrementSpeed(amount);
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
                py = currentyPos() - currentSpeed;
                break;

            case W:
                px = currentxPos() - currentSpeed;
                break;

            case N:
                py = currentyPos() + currentSpeed;
                break;

            case E:
                px = currentyPos() + currentSpeed;
                break;

            default:
                px = currentxPos();
                py = currentyPos();
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
