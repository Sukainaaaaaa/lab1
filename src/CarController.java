import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private Garage<Volvo240> volvo240Garage = new Garage<>("Volvo Garage", 4);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Volvo240 v = new Volvo240();
        v.py = 300;
        cc.cars.add(v);
        Saab95 s = new Saab95();
        s.py = 200;
        cc.cars.add(s);
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", new ControlPanel(cc, CarView.getx(), CarView.gety()));

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.currentxPos());
                int y = (int) Math.round(car.currentyPos());
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                if ((car.currentxPos() >= 300) && (car.currentyPos() >= 300) && (car.getClass() == Volvo240.class)) {
                    car.stopEngine();
                    volvo240Garage.admitVehicle((Volvo240) car);
                }
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void startCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stopCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void raisePlatform() {
        for (Vehicle car : cars) {
            if (car.hasPlatform()) {
                ((Scania) car).raiseScania(70);
                System.out.println("Platform raised");
            }
        }
    }

    void lowerPlatform() {
        for (Vehicle car : cars) {
            if (car.hasPlatform()) {
                ((Scania) car).lowerScania(70);
                System.out.println("Platform lowered");
            }
        }
    }
}
