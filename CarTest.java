import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class CarTest extends Car {

    @Test
    public void testMove() {
        Car c = new Car();
        c.px = 3;
        c.currentSpeed = 10;
        c.d = Car.Direction.W;
        double result = c.px - c.currentSpeed;
        c.move();
        assertEquals(-7, result, 0.001);
    }

    @Test
    public void testStartEngine() {
        Car c = new Car();
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStopEngine() {
        Car c = new Car();
        c.startEngine();
        c.stopEngine();
        assertEquals(0, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testTurnLeft() {
        Car c = new Car();
        d = Direction.N;
        c.turnLeft();
        assertEquals(Direction.W, c.getDirection());
    }

    @Test
    public void testSetColor() {
        Car c = new Car();
        c.setColor(Color.black);
        assertEquals(Color.black, c.getColor());
    }

    @Test
    public void testSpeedFactor() {
        Car c = new Car();
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testgas() {
        Car c = new Volvo240();
        double speed = c.getCurrentSpeed();
        c.gas(1);
        assert speed < c.getCurrentSpeed();
    }

    @Test
    public void testbrake() {
        Car c = new Saab95();
        c.currentSpeed = 40;
        double speed = c.getCurrentSpeed();
        c.brake(0.75);
        assert speed > c.getCurrentSpeed();
    }

    @Test
    public void testbrake1() {
        Car c = new Saab95();
        c.brake(1);
        assert c.getCurrentSpeed() == 0;
    }
}
