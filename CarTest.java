import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class CarTest extends Car {
    Volvo240 volvo_test;
    Saab95 saab_test;
    @Before
    public void SetUp(){
        volvo_test = new Volvo240();
        saab_test = new Saab95();
    }

    @Test
    public void testNrDoors() {
        int doors = volvo_test.getNrDoors();
        assertEquals(4, doors, 0.001);
    }

    @Test
    public void testGetEnginePower() {
        double engineP = volvo_test.getEnginePower();
        assertEquals(100, engineP, 0.001);
    }

    @Test
    public void testMove() {
        volvo_test.px = 3;
        volvo_test.currentSpeed = 10;
        volvo_test.d = Car.Direction.W;
        double result = volvo_test.px - volvo_test.currentSpeed;
        volvo_test.move();
        assertEquals(-7, result, 0.001);
    }

    @Test
    public void testMove2() {
        saab_test.py = 4;
        saab_test.currentSpeed = 20;
        saab_test.d = Car.Direction.S;
        double result = saab_test.py - saab_test.currentSpeed;
        saab_test.move();
        assertEquals(-16, result, 0.001);
    }

    @Test
    public void testStartEngine() {
        saab_test.startEngine();
        assertEquals(0.1, saab_test.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStopEngine() {
        saab_test.startEngine();
        saab_test.stopEngine();
        assertEquals(0, saab_test.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testTurnLeft() {
        volvo_test.d = Direction.N;
        volvo_test.turnLeft();
        assertEquals(Direction.W, volvo_test.getDirection());
    }

    @Test
    public void testTurnRight() {
        saab_test.d = Direction.N;
        saab_test.turnRight();
        assertEquals(Direction.E, saab_test.getDirection());
    }

    @Test
    public void testSetColor() {
        saab_test.setColor(Color.black);
        assertEquals(Color.black, saab_test.getColor());
    }

    @Test
    public void testSpeedFactor() {
        volvo_test.startEngine();
        assertEquals(0.1, volvo_test.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testGas() {
        double speed = volvo_test.getCurrentSpeed();
        volvo_test.gas(1);
        assert speed < volvo_test.getCurrentSpeed();
    }

    @Test
    public void testDecrementSpeed(){
        volvo_test.currentSpeed = 100;
        double speed = volvo_test.currentSpeed;
        volvo_test.decrementSpeed(0.5);
        assert speed > volvo_test.getCurrentSpeed();
    }

    @Test
    public void testIncrementSpeed(){
        saab_test.currentSpeed = 50;
        double speed = saab_test.currentSpeed;
        saab_test.incrementSpeed(0.3);
        assert speed < saab_test.getCurrentSpeed();
    }

    @Test
    public void testBrake() {
        saab_test.currentSpeed = 40;
        double speed = saab_test.getCurrentSpeed();
        saab_test.brake(0.75);
        assert speed > saab_test.getCurrentSpeed();
    }

    @Test
    public void testBrake1() {
        saab_test.brake(1);
        assert saab_test.getCurrentSpeed() == 0;
    }

    @Test
    public void testSetTurbo(){
        saab_test.currentSpeed = 30;
        saab_test.setTurboOn();
        saab_test.gas(0.3);
        double speedWithTurbo = saab_test.getCurrentSpeed();
        saab_test.brake(0.3);
        saab_test.setTurboOff();
        saab_test.gas(0.3);
        double speedWithoutTurbo = saab_test.getCurrentSpeed();
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
