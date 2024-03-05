package MVC.Controller;

import MVC.Model.*;
import MVC.View.CarView;
import MVC.View.ControlPanel;
import MVC.View.ImageHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleAction  {
    private final int delay = 10;
    private Timer timer = new Timer(delay, new Action());
    CarView frame;
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private VehicleLogic vehicleLogic = new VehicleLogic();
    private Garage<Volvo240> volvo240Garage = new Garage<>("VolvoBrand", 4, 300, 300);

    public void initializer(){
        VehicleAction cc = new VehicleAction();

        Volvo240 v = new Volvo240();
        v.setPoint(0,300);
        cc.cars.add(v);
        Scania sa = new Scania();
        sa.setPoint(0,0);
        cc.cars.add(sa);
        Saab95 s = new Saab95();
        s.setPoint(0,200);
        cc.cars.add(s);


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

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    public void startCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    public void stopCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void raisePlatform() {
        for (Vehicle car : cars) {
            if (car.hasPlatform()) {
                ((Scania) car).raiseScania(70);
                System.out.println("Platform raised");
            }
        }
    }

    public void lowerPlatform() {
        for (Vehicle car : cars) {
            if (car.hasPlatform()) {
                ((Scania) car).lowerScania(70);
                System.out.println("Platform lowered");
            }
        }
    }

    public void addCar(){
        Vehicle randvehicle = vehicleLogic.chooseVehicle();
        int rPos = vehicleLogic.randPos();
        randvehicle.setPoint(0, rPos);
        vehicleLogic.addCarHelper(cars,randvehicle,frame, this);
    }

    public void removeCar(){
        if (!cars.isEmpty()){
            cars.getFirst().removeImage();
            cars.removeFirst();
            frame.drawPanel.repaint();
        }
    }
}
