package MVC.Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.
        junit.Assert.assertEquals;

public class VehicleTest {
    Volvo240 volvo_test;
    Volvo240 volvo_test2;
    Saab95 saab_test;
    Scania scania_test;
    Transporter transporter_test;
    Garage<Volvo240> volvo240Garage;


    @Before
    public void SetUp() {
        volvo240Garage = new Garage<>("VolvoG", 5, 300, 300);
        volvo_test = new Volvo240();
        saab_test = new Saab95();
        scania_test = new Scania();
        volvo_test2 = new Volvo240();
        transporter_test = new Transporter();
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
        volvo_test.d = Vehicle.Direction.W;
        double result = volvo_test.px - volvo_test.currentSpeed;
        volvo_test.move();

        assertEquals(-7, result, 0.001);
    }

    @Test
    public void testMove2() {
        saab_test.py = 4;
        saab_test.currentSpeed = 20;
        saab_test.d = Vehicle.Direction.S;
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
        volvo_test.d = Vehicle.Direction.N;
        volvo_test.turnLeft();

        assertEquals(Vehicle.Direction.W, volvo_test.getDirection());
    }

    @Test
    public void testTurnRight() {
        saab_test.d = Vehicle.Direction.N;
        saab_test.turnRight();

        assertEquals(Vehicle.Direction.E, saab_test.getDirection());
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
        volvo_test.startEngine();
        volvo_test.gas(1);

        assert speed < volvo_test.getCurrentSpeed();
    }

    @Test
    public void testDecrementSpeed() {
        volvo_test.currentSpeed = 100;
        double speed = volvo_test.currentSpeed;
        volvo_test.decrementSpeed(0.5);

        assert speed > volvo_test.getCurrentSpeed();
    }

    @Test
    public void testIncrementSpeed() {
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
    public void testSetTurbo() {
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

    @Test
    public void testRaisePlatform() {
        scania_test.raiseScania(30);

        assertEquals(30, scania_test.getAngle(), 0.001);
    }

    @Test
    public void testLowerPlatform() {
        scania_test.setAngle(45);
        scania_test.lowerScania(20);

        assertEquals(25, scania_test.getAngle(), 0.001);
    }

    @Test
    public void testIncrementSpeedScania() {
        scania_test.currentSpeed = 40;
        scania_test.setAngle(25);
        scania_test.incrementSpeed(30);

        assertEquals(40, scania_test.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testLoadVehicle() {
        saab_test.px = 2;
        saab_test.py = 3;
        transporter_test.loadVehicle(saab_test, transporter_test);

        assert saab_test == transporter_test.vehicleStack.peek();
    }

    @Test
    public void testUnloadVehicle() {
        saab_test.px = 2;
        saab_test.py = 3;
        volvo_test.px = 4;
        volvo_test.py = 1;
        transporter_test.loadVehicle(saab_test, transporter_test);
        transporter_test.loadVehicle(volvo_test, transporter_test);
        int stackSize = transporter_test.vehicleStack.size();
        transporter_test.unloadVehicle(transporter_test);

        assert stackSize > transporter_test.vehicleStack.size();
    }

    @Test
    public void testAdmitVehicle(){
        volvo240Garage.admitVehicle(volvo_test);
        assert 1 == volvo240Garage.vehicles.size();
    }
    @Test
    public void testPickUpVehicle() {
        volvo240Garage.admitVehicle(volvo_test);
        volvo240Garage.admitVehicle(volvo_test2);
        volvo240Garage.pickUpVehicle(volvo_test2);
        assert !volvo240Garage.vehicles.contains(volvo_test2);
    }

}
