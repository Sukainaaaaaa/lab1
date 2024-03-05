package MVC.Model;

import MVC.Controller.VehicleAction;
import MVC.View.CarView;

import java.util.ArrayList;
import java.util.Random;

public class VehicleLogic {
    Random rand = new Random();
    VehicleFactory vehicleFactory = new VehicleFactory();
    public static void moveit(int x, int y, Vehicle car){
        if(car.hasTurbo()){
            car.getPoint().x = x;
            car.getPoint().y = y;
            return;
        }
        if(car instanceof Scania){
            car.getPoint().x = x;
            car.getPoint().y= y;
            return;
        }
        if (car instanceof Volvo240){
            car.getPoint().x = x;
            car.getPoint().y = y;
        }
    }

    public void bounds(Vehicle car){
        if(CarView.getx()-110 <= car.currentxPos() && car.d == Vehicle.Direction.E ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.W;
            car.startEngine();
        }

        if (0 > car.currentxPos() && car.d == Vehicle.Direction.W ) {
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.E;
            car.startEngine();
        }

        if(CarView.gety()-70 <= car.currentxPos() && car.d == Vehicle.Direction.S ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.N;
            car.startEngine();
        }

        if(0 > car.currentxPos() && car.d == Vehicle.Direction.N ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.S;
            car.startEngine();
        }
    }
    
    public void park(Vehicle car, Garage garage) {
        if ((car.currentxPos() >= garage.getpx()) && (car.currentyPos() >= garage.getpy()) && (car.getClass() == Volvo240.class)) {
            car.stopEngine();
            garage.admitVehicle(car);
        }
    }

    public Vehicle chooseVehicle(){
        Vehicle[] vehicles = {vehicleFactory.createSaab(), vehicleFactory.createScania(), vehicleFactory.createVolvo()};
        int index = rand.nextInt(vehicles.length - 1);
        return vehicles[index];
    }

    public boolean collision(Vehicle car1, Vehicle car2){
        return car1.getPoint() == car2.getPoint();
    }

    public int randPos(){
        return rand.nextInt(CarView.gety() - CarView.getButtondisplayheight() - 50);
    }

    public void addCarHelper(ArrayList<Vehicle> cars, Vehicle randvehicle, CarView frame, VehicleAction vehicleAction){
        if(cars.isEmpty()){
            cars.add(randvehicle);
            frame.drawPanel.drawable.add(randvehicle);
        }

        if (cars.size() < 10) {
            for (Vehicle v : cars) {
                if (!collision(randvehicle, v)) {
                    cars.add(randvehicle);
                    frame.drawPanel.drawable.add(randvehicle);
                }
                else vehicleAction.addCar();
            }
        }
    }
}
