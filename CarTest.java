import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class CarTest extends Car {

    @Before


    @Test
    public void testNrDoors() {
        Car c = new Volvo240();
        int doors = c.getNrDoors();
        assertEquals(4, doors, 0.001);
    }

    @Test
    public void testGetEnginePower() {
        Car c = new Volvo240();
        double engineP = c.getEnginePower();
        assertEquals(100, engineP, 0.001);
    }

    @Test
    public void testMove() {
        Car c = new Volvo240();
        c.px = 3;
        c.currentSpeed = 10;
        c.d = Car.Direction.W;
        double result = c.px - c.currentSpeed;
        c.move();
        assertEquals(-7, result, 0.001);
    }

    @Test
    public void testMove2() {
        Car c = new Saab95();
        c.py = 4;
        c.currentSpeed = 20;
        c.d = Car.Direction.S;
        double result = c.py - c.currentSpeed;
        c.move();
        assertEquals(-16, result, 0.001);
    }

    @Test
    public void testStartEngine() {
        Car c = new Saab95();
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStopEngine() {
        Car c = new Saab95();
        c.startEngine();
        c.stopEngine();
        assertEquals(0, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testTurnLeft() {
        Car c = new Volvo240();
        d = Direction.N;
        c.turnLeft();
        assertEquals(Direction.W, c.getDirection());
    }

    @Test
    public void testTurnRight() {
        Car c = new Saab95();
        d = Direction.N;
        c.turnRight();
        assertEquals(Direction.E, c.getDirection());
    }

    @Test
    public void testSetColor() {
        Car c = new Saab95();
        c.setColor(Color.black);
        assertEquals(Color.black, c.getColor());
    }

    @Test
    public void testSpeedFactor() {
        Car c = new Volvo240();
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testGas() {
        Car c = new Volvo240();
        double speed = c.getCurrentSpeed();
        c.gas(1);
        assert speed < c.getCurrentSpeed();
    }

    @Test
    public void testDecrementSpeed(){
        Car c = new Volvo240();
        c.currentSpeed = 100;
        double speed = c.currentSpeed;
        c.decrementSpeed(0.5);
        assert speed > c.getCurrentSpeed();
    }

    @Test
    public void testIncrementSpeed(){
        Car c = new Saab95();
        c.currentSpeed = 50;
        double speed = c.currentSpeed;
        c.incrementSpeed(0.3);
        assert speed < c.getCurrentSpeed();
    }

    @Test
    public void testBrake() {
        Car c = new Saab95();
        c.currentSpeed = 40;
        double speed = c.getCurrentSpeed();
        c.brake(0.75);
        assert speed > c.getCurrentSpeed();
    }

    @Test
    public void testBrake1() {
        Car c = new Saab95();
        c.brake(1);
        assert c.getCurrentSpeed() == 0;
    }


    @Test
    public void testSetTurbo(){
        Saab95 saab = new Saab95();
        saab.currentSpeed = 30;
        saab.setTurboOn();
        saab.gas(0.3);
        double speedWithTurbo = saab.getCurrentSpeed();
        saab.brake(0.3);
        saab.setTurboOff();
        saab.gas(0.3);
        double speedWithoutTurbo = saab.getCurrentSpeed();
        assert speedWithTurbo > speedWithoutTurbo;
    }

    @Override
    public double speedFactor() {
        return 0;
    }

    @Override
    public void decrementSpeed(double amount) {
    }

    @Override
    public void incrementSpeed(double amount) {
    }



}
