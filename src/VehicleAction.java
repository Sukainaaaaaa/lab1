import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleAction  {
    private final int delay = 50;
    private Timer timer = new Timer(delay, new Action());
    CarView frame;
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private VehicleLogic vehicleLogic = new VehicleLogic();
    private Garage<Volvo240> volvo240Garage = new Garage<>("VolvoBrand", 4, 300, 300);

    public void initializer(){
        VehicleAction cc = new VehicleAction();
        Volvo240 v = new Volvo240();
        v.py = 300;
        cc.cars.add(v);
        Saab95 s = new Saab95();
        s.py = 200;
        cc.cars.add(s);
        Scania sa = new Scania();
        cc.cars.add(sa);
        cc.frame = new CarView("CarSim 1.0", new ControlPanel(cc, CarView.getx(), CarView.gety()));
        cc.frame.drawPanel.drawable.add(v);
        cc.frame.drawPanel.drawable.add(s);
        cc.frame.drawPanel.drawable.add(sa);
        cc.frame.drawPanel.drawable.add(volvo240Garage);
        cc.timer.start();
    }

    private class Action implements ActionListener {
        
        public void actionPerformed (ActionEvent e){
            for (Vehicle car : cars) {
                car.move();
                vehicleLogic.bounds(car);
                int x = (int) Math.round(car.currentxPos());
                int y = (int) Math.round(car.currentyPos());
                VehicleLogic.moveit(x, y, car);
                frame.drawPanel.repaint();
                vehicleLogic.park(car, volvo240Garage);
            }
        }
    }

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
                System.out.println("MVC.Model.Platform raised");
            }
        }
    }

    void lowerPlatform() {
        for (Vehicle car : cars) {
            if (car.hasPlatform()) {
                ((Scania) car).lowerScania(70);
                System.out.println("MVC.Model.Platform lowered");
            }
        }
    }

}
