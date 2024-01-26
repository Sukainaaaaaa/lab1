import java.awt.*;

import static java.lang.System.out;

public class Car implements Movable { //
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double px;
    protected double py;
    protected Direction d;

    protected enum Direction {
        N, E, W, S
    }


    public Car() { //Could also make a constructor with parameters.
        this.color = color;
        this.currentSpeed = currentSpeed;
        this.modelName = modelName;
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.d = Direction.N;
        this.px = 0;
        this.py = 0;
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

    protected double currentxpos() {
        return this.px;
    }

    protected double currentypos() {
        return this.py;
    }

    protected Direction getDirection() {
        return this.d;
    }

    public double speedFactor() {
        return enginePower * 0.01;
    }

    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

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
                py = currentypos() - currentSpeed;
                break;

            case W:
                px = currentxpos() - currentSpeed;
                break;

            case N:
                py = currentypos() + currentSpeed;
                break;

            case E:
                px = currentypos() + currentSpeed;
                break;

            default:
                px = currentxpos();
                py = currentypos();
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
