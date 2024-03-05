import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleLogic {
    Random rand = new Random();
    static void moveit(int x, int y, Vehicle car){
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
            garage.admitVehicle((Volvo240) car);
        }
    }

    public Vehicle chooseVehicle(ArrayList<Vehicle> cars){
        int index = rand.nextInt(cars.size()-1);
        return cars.get(index);
    }

    public boolean collision(Vehicle car1, Vehicle car2){
        return car1.getPoint() == car2.getPoint();
    }

    public int randPos(){
        int pos = rand.nextInt(CarView.gety()-CarView.getButtondisplayheight()-50);
        return pos;
    }
}
